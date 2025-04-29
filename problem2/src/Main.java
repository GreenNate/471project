package problem2.src;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class to run the Producer-Consumer simulation.
 * 
 * The program accepts three command-line arguments:
 * 1. Sleep time (how long the main thread waits before stopping the program) in seconds.
 * 2. Number of producer threads to create.
 * 3. Number of consumer threads to create.
 *
 * After starting the producers and consumers, the main thread sleeps for the given time,
 * signals the threads to stop by setting a running flag to false, releases any stuck threads
 * by signaling semaphores, and joins all threads before exiting.
 */
public class Main {

    /**
     * Entry point for the Producer-Consumer simulation.
     * 
     * @param args Command-line arguments: &lt;sleepTime&gt; &lt;numProducers&gt; &lt;numConsumers&gt;
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Main <sleepTime> <numProducers> <numConsumers>");
            return;
        }

        int sleepTime = Integer.parseInt(args[0]) * 1000; // seconds to milliseconds
        int numProducers = Integer.parseInt(args[1]);
        int numConsumers = Integer.parseInt(args[2]);

        List<Thread> threads = new ArrayList<>();
        Buffer buffer = new Buffer();

        // Create and start producer threads
        for (int i = 0; i < numProducers; i++) {
            Thread producer = new Producer(buffer, i + 1);
            threads.add(producer);
            producer.start();
        }
        
        // Create and start consumer threads
        for (int i = 0; i < numConsumers; i++) {
            Thread consumer = new Consumer(buffer, i + 1);
            threads.add(consumer);
            consumer.start();
        }

        // Main thread sleeps for the specified time
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Main thread stopping threads...");
        buffer.running = false; // Signal threads to stop
        
        // Release semaphores to unblock any stuck threads
        for (int i = 0; i < numProducers; i++) buffer.empty.release();
        for (int i = 0; i < numConsumers; i++) buffer.full.release();
        
        // Wait for all threads to exit
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("All threads exited cleanly.");
    }
}
