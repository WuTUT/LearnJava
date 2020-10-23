package com.wutut.course;

public class Pitfall {
    public static void main(String[] args) {
        System.out.println(true ? Integer.valueOf(1) : Double.valueOf(2));
        System.out.println(true ? 1 : 2.0);
    }
}
