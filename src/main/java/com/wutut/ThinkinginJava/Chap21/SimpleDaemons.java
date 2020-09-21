package com.wutut.ThinkinginJava.Chap21;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {
    private int count;

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(count++);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("all daemons start");
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
