package com.wutut.ThinkinginJava.Chap21;

class ThreadUseSth {
    private int sth = 0;

    public synchronized void f() {
        sth++;
    }

    public void g() {
        sth -= 2;
    }

    public int getSth() {
        return sth;
    }
}

public class ThreadUse {
    public static void main(String[] args) {
        ThreadUseSth tus = new ThreadUseSth();
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                try {
                    sleep(3000);// do work 1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tus.f();
                System.out.println("work 1 done");
            }
        };
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                try {
                    sleep(5000);// do work 2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tus.f();
                System.out.println("work 2 done");
            }
        };
        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    t2.join();
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    sleep(4000);// do work 3 after 1 and 2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tus.g();
                System.out.println("work 3 done");
            }
        };
        t1.start();
        t2.start();
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(tus.getSth());
    }
}
