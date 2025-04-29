package problem1.src;

import java.util.List;

/**
 * Main class for running the CPU Scheduling simulation.
 * 
 * This program reads a list of processes from an input file and simulates two CPU scheduling
 * algorithms: FIFO (First-In-First-Out) and SJF (Shortest Job First, non-preemptive).
 * After running each scheduler, it prints scheduling statistics to the console.
 */
public class Main {

    /**
     * The entry point of the application.
     * 
     * @param args command-line arguments
     *             args[0] = path to the input file (e.g., input/Datafile1-txt.txt)
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java problem1.src.Main <input_file>");
            return;
        }

        String filePath = args[0];

        try {
            // Run FIFO scheduling
            List<Process> processes = CPUScheduler.readProcessesFromFile(filePath);

            System.out.println("---- Running FIFO ----");
            CPUScheduler.runFIFO(processes);
            CPUScheduler.printStatistics(processes);

            // Re-read process list to avoid reusing modified processes
            processes = CPUScheduler.readProcessesFromFile(filePath);
            System.out.println("\n---- Running SJF ----");
            CPUScheduler.runSJF(processes);
            CPUScheduler.printStatistics(processes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
