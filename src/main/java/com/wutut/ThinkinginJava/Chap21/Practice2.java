package com.wutut.ThinkinginJava.Chap21;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Practice2 {
    public static void main(final String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arr.add(exec.submit(new FibTask("Fib " + i, i + 2)));
        }
        for (Future<Integer> i : arr) {
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
    }
}

class FibTask implements Callable<Integer>, Generator<Integer> {
    private int total;
    private final int n;
    String name;
    private int count = 0;

    FibTask(final String name, final int n) {
        this.name = name;
        this.n = n;
    }

    @Override
    public Integer next() {
        return fib(count++);
    }

    int fib(final int n) {
        if (n < 2)
            return 1;
        return fib(n - 2) + fib(n - 1);
    }

    @Override
    public Integer call() throws Exception {// must return Object
        for (int i = 0; i < n; i++) {
            total += next();
        }
        return total;
    }

}
