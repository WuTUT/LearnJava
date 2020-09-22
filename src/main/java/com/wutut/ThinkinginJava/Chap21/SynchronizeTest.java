package com.wutut.ThinkinginJava.Chap21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Store {
    private int a = 0;
    private int b = 0;

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public synchronized void printa() {
        a++;
        // Thread.yield();
        // a--;
        System.out.println("a = " + a);
        // Thread.yield(); //but it still has the lock so still this thread
        System.out.println("a = " + a);
    }

    public void printb() {
        // synchronized(this){} //ok

        b++;
        Thread.yield();
        // b--;
        System.out.println("b = " + b);
        System.out.println("b = " + b);
    }
}

public class SynchronizeTest implements Runnable {
    Store s;
    static int count = 0;
    String id;

    SynchronizeTest(Store s) {
        count++;
        id = "Thread : " + count;
        this.s = s;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            // synchronized (this) {
            s.printa(); // 最后输出结果 a是递增的 是确定性输出吧
            System.out.println();// 2\n2\n 和 空行连续 这两个连续是凑巧的吧
            // }

            // synchronized (this) { //2. 加了这个是不是啥用没有 因为这个只会被一个线程访问
            // // synchronized (this.s) { 这个是对的 ，相当于把Store类给锁上了
            // s.printb();
            // System.out.println();
            // }

            Thread.yield();
        }
        System.out.println(s.getA());
        // System.out.println(s.getB());

    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Store s = new Store();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SynchronizeTest(s));
        }
        exec.shutdown();

    }
}
