package com.wutut.ThinkinginJava.Chap21;

class Worker implements Runnable {
    private Thread t;
    private String threadname;

    Worker(String name) {
        threadname = name;
    }

    @Override
    public void run() {
        System.out.println("Running" + threadname);

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(" " + threadname + " Iters" + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        System.out.println("Starting " + threadname);
        if (t == null) {
            t = new Thread(this, threadname);
            t.start();
        }
    }
}

public class RunnableDemo {

    public static void main(String[] args) {
        Worker w1 = new Worker("Thread-1");
        Worker w2 = new Worker("Thread-2");
        w1.start();
        w2.start();

    }
}
