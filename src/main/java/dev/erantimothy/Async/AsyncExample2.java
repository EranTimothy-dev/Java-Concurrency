package dev.erantimothy.Async;

import javax.lang.model.type.NullType;
import java.util.Scanner;
import java.util.concurrent.*;

import static java.lang.System.exit;

public class AsyncExample2 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadpool = Executors.newFixedThreadPool(1);
        Future<NullType> futureTask = threadpool.submit(new Callable<NullType>() {
            @Override
            public NullType call() throws Exception {
                System.out.println("Enter any key to stop program.");
                scanner.nextLine();
                return null;
            }

        });

        while (!futureTask.isDone()) {
            System.out.println("Waiting for program to stop!");
            TimeUnit.SECONDS.sleep(2);
        }


        threadpool.shutdown();
        Runtime.getRuntime().exit(0);
        exit(0);
    }
}
