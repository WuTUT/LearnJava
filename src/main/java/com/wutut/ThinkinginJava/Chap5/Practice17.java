package com.wutut.ThinkinginJava.Chap5;

//Pracitce 17~18
class StringContainer {
    String s;

    static int count;

    StringContainer(String s) {
        System.out.println(s);
        ++count;
        this.s = s + count;
    }

    @Override
    public String toString() {
        return s;
    }
}

public class Practice17 {
    public static void main(String[] args) {
        StringContainer[] SC = new StringContainer[5];
        System.out.println("------");
        for (int i = 0; i < SC.length; i++) {
            SC[i] = new StringContainer("hello ");
        }
        for (StringContainer i : SC) {
            System.out.println(i);
        }
    }

}
