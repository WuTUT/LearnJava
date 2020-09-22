package com.wutut.ThinkinginJava.Chap21;

public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    boolean isCanceld() {
        return canceled;
    }
}
