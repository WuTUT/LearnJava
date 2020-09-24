package com.wutut.ThinkinginJava.Chap21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTickets {
    static Random rand = new Random();

    public static void main(String[] args) {
        List<Integer> amountList = new Vector<>();
        List<Thread> threadList = new ArrayList<>();
        TicketsWindow window = new TicketsWindow(8000);
        for (int i = 0; i < 400; i++) {
            Thread t = new Thread(() -> {
                amountList.add(window.sell(randomamount()));
                Thread.yield();
            });
            threadList.add(t);
            t.start();
        }
        for (Thread i : threadList) {
            try {
                i.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(8000 - window.getCount());
        System.out.println(amountList.stream().mapToInt(i -> i).sum());
    }

    private static int randomamount() {
        return rand.nextInt(5) + 1;
    }
}

class TicketsWindow {
    // private static int count;
    // private Lock lock = new ReentrantLock();
    private int count;

    public TicketsWindow(int count) {
        this.count = count;
    }

    public int sell(int amount) {
        // synchronized (this) {
        synchronized (this) {
        }
        if (count >= amount) {
            count -= amount;
            return amount;
        } else {

            return 0;
        }

        // }
        // public static synchronized int sell(int amount) {
        // lock.lock();
        // try {
        // if (count >= amount) {
        // count -= amount;
        // return amount;
        // } else {
        // return 0;
        // }
        // } finally {
        // lock.unlock();
        // }

    }

    public int getCount() {
        return count;
    }
}