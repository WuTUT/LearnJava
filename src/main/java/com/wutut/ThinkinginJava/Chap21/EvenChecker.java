package com.wutut.ThinkinginJava.Chap21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    public IntGenerator generator;
    private final int id;

    EvenChecker(IntGenerator generator, int ident) {
        this.generator = generator;
        id = ident;

    }

    @Override
    public void run() {
        while (!generator.isCanceld()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(id + " " + val + " not even!");
                generator.cancel();

            } else {
                System.out.println(id + " " + val + "  even!");
            }
        }
    }

    public static void test(IntGenerator gp, int count) {
        System.out.println("Press control-c to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++)
            exec.execute(new EvenChecker(gp, i));
        exec.shutdown();
    }

    public static void test(IntGenerator gp) {
        test(gp, 2);

    }
}
