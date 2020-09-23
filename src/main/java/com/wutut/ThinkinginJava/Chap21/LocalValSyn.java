package com.wutut.ThinkinginJava.Chap21;

import java.util.ArrayList;

public class LocalValSyn {
    public static void main(String[] args) {
        ThreadUnsafe test = new ThreadUnsafe();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                test.method1(200);
            }, "Thread" + (i + 1)).start();
        }
        System.out.println("OK");
        ThreadSafe test1 = new ThreadSafe();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                test1.method1(200);
            }, "Thread" + (i + 1)).start();
        }
        System.out.println("OK");
    }
}

class ThreadUnsafe {
    ArrayList<String> list = new ArrayList<>();// 只要是list 不管装的是什么 都是线程不安全 add不是说一定会加上去，执行到一半就切换到下一个线程的remove了

    public synchronized void method1(int loopNum) {// 必须加synchronized

        for (int i = 0; i < loopNum; i++) {
            method2();
            method3();
        }
    }

    private void method2() {
        list.add("1");
    }

    private void method3() {
        list.remove(0);
    }
}

class ThreadSafe {
    // 局部变量没有暴露给外部
    public void method1(int loopNum) {
        ArrayList<String> list = new ArrayList<>();// 每个线程都有一个局部list 使用局部变量可以保证线程安全
        // 堆里有两个list对象
        for (int i = 0; i < loopNum; i++) {
            method2(list);
            method3(list);
        }
    }

    private void method2(ArrayList<String> list) {// 所以需要private
        list.add("1");
    }

    public void method3(ArrayList<String> list) {// 这样就暴露给子类，会被乱搞 可以private 或者final
        list.remove(0);
    }

    public void method4(ArrayList<String> list) {
        // 没问题
        list.add("1");
    }
}

class ThreadSubSafe extends ThreadSafe {
    @Override
    public void method3(ArrayList<String> list) {
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}