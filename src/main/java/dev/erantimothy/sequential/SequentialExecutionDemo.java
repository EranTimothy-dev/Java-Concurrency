package dev.erantimothy.sequential;

public class SequentialExecutionDemo {
    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        for (int i = 0; i < 4; i++) {
            System.out.println(i);
        }
    }

    private static void demo2() {
        for (int i = 0; i < 4; i++) {
            System.out.println(i);
        }
    }
}
