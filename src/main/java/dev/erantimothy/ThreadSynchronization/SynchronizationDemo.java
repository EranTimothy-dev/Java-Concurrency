package dev.erantimothy.ThreadSynchronization;

public class SynchronizationDemo {
    private static int counter = 0;
    public static void main(String[] args) {
        Thread one = new Thread(() ->{
            for (int i = 0; i < 10000; i++) {
                increment();
//                counter++;
            }
        });

        Thread two = new Thread(()->{
            for (int i =0; i < 10000; i++){
//                counter++;
                increment();
            }
        });

        one.start();
        two.start();

        try{
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        System.out.println("Counter: " + counter);
    }

    private synchronized static void increment(){
        counter++;
    }
}

/*
 * The output given by the above method is not 20000 and that is due to something that is known as a
 * non-atomic operation
 *
 * The reason we don't get 20000 as the final answer is because the two threads are not synchronized.
 *
 * When the thread is called the process is:
 * 1. load counter
 * 2. increment counter
 * 3. Set back the value
 *
 * counter = 0; incrementValue = 1; <- Thread1
 * counter = 0; incrementValue = 1; <- Thread2
 *
 * even though thread 2 should get the counter as 1 it reads it as 0 because thread 1 hasn't set the value
 * (switched to thread 2 before setting the value) back to counter yet therefore
 * an increment was not added to counter.
 *
 * This is known as a race condition.
 * This can be solved by using the synchronized keyword
 */

class SynchronizationDemo2{
    private static int counter1 = 0;
    private static int counter2 = 0;

    public static void main(String[] args) {
        Thread one = new Thread(() ->{
            for (int i = 0; i < 10000; i++) {
                increment1();
            }
        });

        Thread two = new Thread(()->{
            for (int i =0; i < 10000; i++){
                increment2();
            }
        });

        one.start();
        two.start();

        try{
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        System.out.println("Counter: " + counter1 + " -- " + counter2);
    }

    private synchronized static void increment1(){
        counter1++;
    }

    private synchronized static void increment2(){
        counter2++;
    }
    /*
    Even though increment2 does not work with counter2 when increment1 is being executed increment2 will be
    locked till increment1 finishes, which is the issue in calling synchronized in method level.

    To address this it is better to use explicit locking instead.
     */

}
