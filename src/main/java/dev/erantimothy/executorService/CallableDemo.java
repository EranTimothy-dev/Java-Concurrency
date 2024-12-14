package dev.erantimothy.executorService;

import java.util.Scanner;
import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(ExecutorService service = Executors.newFixedThreadPool(2)){
            Future<Integer> result = service.submit(new ReturnValueTask());
            System.out.println(result.get());
            System.out.println("Main thread finished");
        }
    }
}

class ReturnValueTask implements Callable<Integer> {
    public Integer call() throws Exception{
        Thread.sleep(2000);
        return 12;
    }
}
