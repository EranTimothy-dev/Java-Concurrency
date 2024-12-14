package dev.erantimothy.Async;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class AsyncExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        AtomicBoolean running = new AtomicBoolean(true);  // Flag to control the background task

        // Asynchronous task that will run until user input is received
        Future<?> future = executor.submit(() -> {
            while (running.get()) {
                System.out.println("Background task is running...");
                try {
                    Thread.sleep(1000); // Simulating periodic work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Background task interrupted.");
                }
            }
            System.out.println("Background task stopped.");
        });

        // Main thread waits for user input
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Press Enter to stop the background task...");
            scanner.nextLine();  // Waits until user hits Enter
        }

        // Signal the background task to stop
        running.set(false);

        // Shutdown the executor service
        executor.shutdown();
        System.out.println("Executor service shut down.");
    }
}
