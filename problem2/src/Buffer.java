package problem2.src;

import java.util.concurrent.Semaphore;

/**
 * The Buffer class implements a bounded buffer shared between producer and consumer threads.
 * It uses semaphores to coordinate access and ensures safe concurrent operations.
 */
public class Buffer {

    /** The size of the buffer. */
    public static final int BUFFER_SIZE = 5;

    /** The array representing the buffer. */
    private final int[] buffer = new int[BUFFER_SIZE];

    /** Index for inserting the next item. */
    private int in = 0;

    /** Index for removing the next item. */
    private int out = 0;

    /** Mutex semaphore to protect critical sections. */
    private final Semaphore mutex = new Semaphore(1);

    /** Semaphore counting empty slots in the buffer. */
    final Semaphore empty = new Semaphore(BUFFER_SIZE);

    /** Semaphore counting full slots in the buffer. */
    final Semaphore full = new Semaphore(0);

    /** Flag to indicate if the buffer is still running (for clean shutdown). */
    public volatile boolean running = true;

    /**
     * Inserts an item into the buffer.
     * If the buffer is full, the producer waits until space becomes available.
     *
     * @param item the item to insert into the buffer
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public void insertItem(int item) throws InterruptedException {
        if (!running) return;

        // check if buffer is full BEFORE acquire
        if (empty.availablePermits() == 0) {
            System.out.println("Buffer full. Producer waiting...");
        }

        empty.acquire();
        if (!running) return;
        mutex.acquire();

        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        System.out.println("Producer produced: " + item);

        mutex.release();
        full.release();
    }

    /**
     * Removes an item from the buffer.
     * If the buffer is empty, the consumer waits until an item is available.
     *
     * @return the item removed from the buffer, or -1 if the buffer is shutting down
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public int removeItem() throws InterruptedException {
        if (!running) return -1;

        // check if buffer is empty BEFORE acquire
        if (full.availablePermits() == 0) {
            System.out.println("Buffer empty. Consumer waiting...");
        }

        full.acquire();
        if (!running) return -1;
        mutex.acquire();

        int item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        System.out.println("Consumer consumed: " + item);

        mutex.release();
        empty.release();
        return item;
    }
}
