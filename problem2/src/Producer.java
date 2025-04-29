package problem2.src;

import java.util.Random;

/**
 * Producer thread that generates random integers and inserts them into the shared buffer.
 * 
 * The producer continuously produces items while the {@code running} flag in {@link Buffer}
 * is set to true. After producing an item, the producer sleeps for a random amount of time
 * (up to 1 second) before producing the next item.
 */
public class Producer extends Thread {
    private final Buffer buffer;
    private final int id;
    private final Random random = new Random();

    /**
     * Constructs a Producer thread.
     * 
     * @param buffer the shared buffer where items are inserted
     * @param id the identifier for this producer thread
     */
    public Producer(Buffer buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    /**
     * The main execution loop for the producer.
     * 
     * Continuously produces random integers and inserts them into the buffer
     * while the buffer's {@code running} flag is true. Sleeps for a random time
     * between productions. Gracefully exits when interrupted.
     */
    @Override
    public void run() {
        try {
            while (buffer.running) {
                int item = random.nextInt(1000);
                buffer.insertItem(item);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
            System.out.println("Producer " + id + " interrupted.");
        }
    }
}
