package dev.erantimothy.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newCachedThreadPool()){
            for (int i = 0; i <= 1000; i++) {
                service.execute(new TaskOne(i));
                service.execute(new TaskOne(i+1));
            }
        }
    }

}

class TaskOne implements Runnable{

    private final int taskId;

    public TaskOne(int taskId) {
        this.taskId = taskId;

    }

    @Override
    public void run() {
        System.out.println("Task: " + taskId + " being executed by " + Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
