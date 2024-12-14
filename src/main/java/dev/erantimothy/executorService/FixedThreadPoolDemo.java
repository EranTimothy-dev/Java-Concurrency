package dev.erantimothy.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {

    public static void main(String[] args) {
        Thread vendor = new Thread(new Runnable() {
            @Override
            public void run() {
                try(ExecutorService service = Executors.newFixedThreadPool(12)){
                    for (int i = 0; i < 200; i++) {
                        service.execute(new Work(i, "1"));
                    }
                }
                System.out.println("Completed task 1");
                Thread.currentThread().interrupt();
            }
        });
        Thread customer = new Thread(new Runnable() {
            @Override
            public void run() {
                try(ExecutorService service = Executors.newFixedThreadPool(6)){
                    for (int i = 0; i < 100; i++) {
                        service.execute(new Work(i,"2"));
                    }
                }
                System.out.println("Completed task 2");
            }
        });

        vendor.start();
        customer.start();


    }

}


class Work implements Runnable{

    private final int workId;
    private final String taskNo;

    public Work(int workId, String taskNo) {
        this.workId = workId;
        this.taskNo = taskNo;
    }

    @Override
    public void run() {
        System.out.println("TaskNo: "+ taskNo+ " " + "Task ID " + workId + " being executed by thread " + Thread.currentThread().getName());

        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
