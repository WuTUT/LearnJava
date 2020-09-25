package com.wutut.ThinkinginJava.Chap21;

import java.util.concurrent.TimeUnit;

public class WaitNotifyTest {
    static final Object lock = new Object();
    static int i = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("T1 running");
                while (i == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("t1 other code");
            }

        }, "t1").start();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("T2 running");
                while (i == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 other code");
            }

        }, "t2").start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("huan xing");
        synchronized (lock) {
            // lock.notify();
            lock.notifyAll();

            i = 1;
            // lock.notifyAll();
        }
    }
}
