package dev.erantimothy.Test;

import java.util.concurrent.*;

public class TicketSystem2 {
    private static final int TICKET_RELEASE_RATE = 10;         // Speed of adding tickets
    private static final int CUSTOMER_RETRIEVAL_RATE = 5;      // Speed of retrieving tickets
    private static final int MAX_CAPACITY = 100;               // Total event ticket capacity
    private static final int TOTAL_TICKETS = 20;               // Tickets in each batch
    private static final BlockingQueue<Ticket> ticketQueue = new LinkedBlockingQueue<>(MAX_CAPACITY);
    private static volatile int totalTicketsProduced = 0;      // Tracks total tickets added to respect maxCapacity

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Vendor task to add tickets up to the batch size and max capacity
        scheduler.scheduleAtFixedRate(() -> {
            int ticketsToAdd = Math.min(TICKET_RELEASE_RATE, TOTAL_TICKETS);  // Limit per batch
            for (int i = 0; i < ticketsToAdd; i++) {
                if (totalTicketsProduced >= MAX_CAPACITY) {
                    System.out.println("\nMax capacity reached; no more tickets will be added.");
                    break;
                }
                try {
                    ticketQueue.put(new Ticket());   // Add ticket to the queue
                    totalTicketsProduced++;
                    System.out.print("\rTicket added. Total tickets produced: " + totalTicketsProduced);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);

        // Customer task to retrieve tickets at the specified rate
        scheduler.scheduleAtFixedRate(() -> {
            for (int i = 0; i < CUSTOMER_RETRIEVAL_RATE; i++) {
                try {
                    if (ticketQueue.isEmpty() && totalTicketsProduced >= MAX_CAPACITY) {
                        System.out.println("\nNo tickets left to retrieve; all tickets are sold.");
                        scheduler.shutdown();
                        return;
                    }
                    Ticket ticket = ticketQueue.take();  // Retrieve ticket from queue
                    System.out.print("\rTicket retrieved. Tickets left: " + ticketQueue.size());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
