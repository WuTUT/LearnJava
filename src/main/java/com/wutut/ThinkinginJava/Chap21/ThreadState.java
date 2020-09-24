package com.wutut.ThinkinginJava.Chap21;

import java.util.concurrent.TimeUnit;

public class ThreadState {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                System.out.println("t1 running...");
            }
        };
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true) {

                }

            }
        };
        t2.start();
        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                System.out.println("t3 running...");
                // then terminated
            }
        };
        t3.start();
        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized (ThreadState.class) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(4000);// timed_wating
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
                }

            }
        };
        t4.start();
        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                try {
                    t2.join();// waiting
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t5.start();
        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                synchronized (ThreadState.class) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }
        };
        t6.start();
        System.out.println("t1 is " + t1.getState());
        System.out.println("t2 is " + t2.getState());
        System.out.println("t3 is " + t3.getState());
        System.out.println("t4 is " + t4.getState());
        System.out.println("t5 is " + t5.getState());
        System.out.println("t6 is " + t6.getState());
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("t1 is " + t1.getState());
        System.out.println("t2 is " + t2.getState());
        System.out.println("t3 is " + t3.getState());
        System.out.println("t4 is " + t4.getState());
        System.out.println("t5 is " + t5.getState());
        System.out.println("t6 is " + t6.getState());
    }
}
