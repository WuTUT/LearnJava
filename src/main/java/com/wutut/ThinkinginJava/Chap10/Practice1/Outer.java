package com.wutut.ThinkinginJava.Chap10.Practice1;

public class Outer {
    private String s;
    private static String s2 = "helo";

    public Outer(String s) {
        this.s = s;
    }

    class Inner {
        private int n = 0;

        // static int fn = 0;//The field fn cannot be declared static in a non-static

        // inner type
        @Override
        public String toString() {
            return s;
        }

        class InnerIner {
            private int a = 8;
        }
    }

    static class Inner3 {
        private int n2 = 1;

        @Override
        public String toString() {
            return s2;
        }

        static class InnerIner {
            private int a = 9;
        }
    }

    static class Inner2 {
        private int n2 = 1;

        @Override
        public String toString() {
            return s2;
        }

        static class InnerIner {
            private int a = 9;
        }
    }

    public static void main(String[] args) {
        // Outer.Inner inner = new Outer.Inner();// No enclosing instance of type Outer
        // is accessible.
        // Outer.Inner inner = new Outer("now").new Outer.Inner(); //cannot new
        // Outer.Inner()
        Outer.Inner inner = new Outer("now").new Inner();
        System.out.println(inner.n);
        System.out.println("============");
        Outer.Inner2 inner2 = new Inner2();
        System.out.println(inner2.n2);
        System.out.println("============");
        System.out.println(inner.toString());
        System.out.println("============");
        System.out.println(inner2.toString());
        System.out.println("============");
        Outer.Inner.InnerIner inner3 = inner.new InnerIner();
        System.out.println(inner3.a);
        System.out.println("============");
        Outer.Inner2.InnerIner inner4 = new Inner2.InnerIner();
        System.out.println(inner4.a);
        System.out.println("============");
        Outer.Inner3 inner5 = new Inner3();
        System.out.println(inner5.n2);

    }

}
