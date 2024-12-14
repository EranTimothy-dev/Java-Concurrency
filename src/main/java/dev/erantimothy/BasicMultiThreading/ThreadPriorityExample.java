package dev.erantimothy.BasicMultiThreading;

public class ThreadPriorityExample {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " says Hi.");

        Thread one = new Thread(() -> {
            System.out.println("Thread one says Hi as well!");
        });

        one.setPriority(Thread.MAX_PRIORITY);
        one.start();
        // even though one has been set to max priority the main thread executes first because at the start
        // of the program the main thread has the highest priority and afterward the highest priority is set to
        // thread one.
    }
}

class Example1{
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println(Thread.currentThread().getPriority());
    }
}
