package PRODUCER_CONSUMER.src;

import java.util.Random;

/**
 * The Consumer class represents a consumer thread that removes items from the shared buffer.
 * It sleeps for a random period between consumption attempts.
 */
public class Consumer extends Thread {

    /** Shared buffer between producers and consumers. */
    private final Buffer buffer;

    /** Unique identifier for the consumer thread. */
    private final int id;

    /** Random number generator for sleep intervals. */
    private final Random random = new Random();

    /**
     * Constructs a Consumer with a reference to the shared buffer and a unique ID.
     *
     * @param buffer the shared buffer to consume items from
     * @param id the identifier for this consumer
     */
    public Consumer(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    /**
     * The main logic of the consumer thread.
     * Continuously removes items from the buffer and sleeps for a random amount of time
     * until the running flag is set to false.
     */
    @Override
    public void run() {
        try {
            while (buffer.running) {  // continue while the system is running
                buffer.removeItem();
                Thread.sleep(random.nextInt(1000)); // sleep for a random time
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer " + id + " interrupted.");
        }
    }
}
