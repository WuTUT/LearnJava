package com.wutut.ThinkinginJava.Chap21;

public class Practice1 {
    public static void main(final String[] args) {
        for (int i = 0; i < 15; i++) {
            new Thread(new MyWorker("worker " + i)).start();
        }
        System.out.println("Wating");
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