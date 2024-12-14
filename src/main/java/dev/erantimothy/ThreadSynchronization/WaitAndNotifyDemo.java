package dev.erantimothy.ThreadSynchronization;

public class WaitAndNotifyDemo {

    public static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    one();
                }catch(InterruptedException e){
                    throw new RuntimeException(e);
                }

            }
        });

        Thread two = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    two();
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }

            }

        });

        one.start();
        two.start();
    }

    private static void one() throws InterruptedException {
        synchronized (LOCK){
            System.out.println("Hello from method one...");
            LOCK.wait();// this thread will wait and the lock will be acquired by thread two
            System.out.println("Back again in the method one");
        }
    }


    private static void two() throws InterruptedException {
        synchronized (LOCK){
            System.out.println("Hello from method two...");
            LOCK.notify(); // the thread will notify that other threads can access the lock
            // but it will be accessed after the rest of the lines in the synchronized block is executed
            System.out.println("Hello from method two even after notifying..");
        }
    }

}
