package dev.erantimothy.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {
    public static void main(String[] args) {
        // schedule task to start after 1 sec and run every 2 seconds until it reaches 10 seconds
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new ProbeTask(), 1000, 2000, TimeUnit.MILLISECONDS);
        try{
            if(!service.awaitTermination(10000, TimeUnit.MILLISECONDS)){
                service.shutdownNow();
            }
        } catch (InterruptedException e){
            service.shutdownNow();

        }
    }
}


class ProbeTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Probing end point for updates");
    }
}
