package com.wutut.ThinkinginJava.Chap21;

public class FibGenerator implements Generator<Integer> {
    private int count;

    @Override
    public Integer next() {
        return fib(count++);
    }

    private Integer fib(int n) {
        if (n < 2)
            return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public static void main(String[] args) {
        FibGenerator gen = new FibGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.print(gen.next() + " ");
        }
        System.out.println();
    }
}
