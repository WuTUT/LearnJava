package com.wutut.ThinkinginJava.Chap21;

import java.util.concurrent.TimeUnit;

public class SychronizedQA {
    public static void main(String[] args) {
        Number n1 = new Number();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n1.b();
        }).start();
        new Thread(() -> {
            n1.c();
        }).start();
    }
}

class Number {
    public synchronized void a() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("1");
    }

    public synchronized void b() {
        System.out.println("2");
    }

    public void c() {// c不会等待 它什么都不管
        System.out.println("3");
    }

    public synchronized static void d() {// 锁的是类锁，与对象锁锁的不是一个东西 n1.d()实际上是Number.d()不针对具体实例，所以不发生互斥
        System.out.println("4");
    }
}
