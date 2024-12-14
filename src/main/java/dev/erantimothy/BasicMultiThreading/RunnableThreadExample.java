package dev.erantimothy.BasicMultiThreading;

public class RunnableThreadExample {

    public static void main(String[] args) {
        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());
        // using anonymous class
        Thread three = new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Anonymous Thread: " + i);
                }
            }
        });
        // using lambda
        Thread four = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("lambda Thread: " + i);
            }
        });
        one.start();
        two.start();
        three.start();
        four.start();
    }
}

class ThreadOne implements Runnable {

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadOne " + i);
        }
    }
}

class ThreadTwo implements Runnable {

    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadTwo " + i);
        }
    }
}
