package dev.erantimothy.Test;

import java.util.concurrent.*;

public class TicketSystem {
    private static final int TICKET_RELEASE_RATE = 10;
    private static final int CUSTOMER_RETRIEVAL_RATE = 5;
    private static final BlockingQueue<Ticket> ticketQueue = new LinkedBlockingQueue<>(100);  // Queue for tickets

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Vendor task to add tickets at the specified rate
        scheduler.scheduleAtFixedRate(() -> {
            for (int i = 0; i < TICKET_RELEASE_RATE; i++) {
                try {
                    ticketQueue.put(new Ticket());  // Add ticket to queue
                    System.out.println("Ticket added");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

        // Customer task to retrieve tickets at the specified rate
        scheduler.scheduleAtFixedRate(() -> {
            for (int i = 0; i < CUSTOMER_RETRIEVAL_RATE; i++) {
                try {
                    Ticket ticket = ticketQueue.take();  // Retrieve ticket from queue
                    System.out.println("Ticket retrieved");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
