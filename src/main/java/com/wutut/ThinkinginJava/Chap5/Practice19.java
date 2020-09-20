package com.wutut.ThinkinginJava.Chap5;

// Practice 19~20
public class Practice19 {

    public static void f(String... args) {// 可变参数列表既可以接收String[] ，也可以接收逗号分隔的几个String
        for (String i : args) {
            System.out.println(i);
        }
    }

    public static void main(String... args) {
        // f(new String[] { "hello", "world", "yes", "no" });
        // f("hello", "world", "aaa");
        f(args);
        Practice17 practice17 = new Practice17(); // 同一个包内可以使用另一个文件内的class
        practice17.main(new String[] { "" });
    }

}
