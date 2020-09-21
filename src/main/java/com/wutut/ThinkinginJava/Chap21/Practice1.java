package com.wutut.ThinkinginJava.Chap21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Practice1 {
    public static void main(final String[] args) {
        // for (int i = 0; i < 15; i++) {
        // new Thread(new MyWorker("worker " + i)).start();
        // }
        // System.out.println("Wating");
        // ExecutorService exec = Executors.newCachedThreadPool();
        // for (int i = 0; i < 5; i++) {
        // exec.execute(new FibRunnable());
        // }
        // exec.shutdown();
        // ExecutorService exec = Executors.newSingleThreadExecutor();
        // for (int i = 0; i < 5; i++) {
        // exec.execute(new FibRunnable());
        // }
        // exec.shutdown();
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec.execute(new FibRunnable());
        }
        exec.shutdown();
    }
}

class MyWorker implements Runnable {
    String name;

    MyWorker() {
    }

    MyWorker(final String name) {
        this.name = name;
        System.out.println(name + " init");
    }

    @Override
    public void run() {
        System.out.println(name + " doing");
        Thread.yield();
        Thread.yield();
        Thread.yield();
        System.out.println(name + " done");
    }
}

class FibRunnable extends FibGenerator implements Runnable {
    String name = "Fib ";
    private static int num = 0;

    FibRunnable() {
        num++;
        name = name + num;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " " + next());
        }
    }
}