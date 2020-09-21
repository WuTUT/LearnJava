package com.wutut.ThinkinginJava.Chap21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Practice5 {
    public static void main(String[] args) {
        HashMap<String, Future<Integer>> arr = new HashMap<>();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            SleepTask t = new SleepTask("Task " + i);
            arr.put(t.toString(), exec.submit(t));
        }
        for (Future<Integer> i : arr.values()) {
            try {
                System.out.println(i.get());
            } catch (InterruptedException e) {
                System.out.println(e);
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
        }
        for (String key : arr.keySet()) {
            System.out.println(key);

        }
    }
}

class SleepTask implements Callable<Integer> {
    private String name;
    private Random rand = new Random();

    SleepTask(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Integer call() throws Exception {

        Integer ret = rand.nextInt(1000);
        TimeUnit.MILLISECONDS.sleep(ret);
        return ret;
    }
}