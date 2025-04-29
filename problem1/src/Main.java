package problem1.src;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileReader;
import java.io.BufferedReader;
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
            // create output directory if it doesn't exist
            File outputDir = new File("problem1/output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            // save the original System.out (console)
            PrintStream consoleOut = System.out;

            // --------- FIFO Phase ---------
            PrintStream fifoOut = new PrintStream(new FileOutputStream("problem1/output/fifo_output.txt"));
            System.setOut(fifoOut);

            List<Process> processes = CPUScheduler.readProcessesFromFile(filePath);

            System.out.println("---- Running FIFO ----");
            CPUScheduler.runFIFO(processes);
            CPUScheduler.printStatistics(processes);

            fifoOut.close();

            // restore console
            System.setOut(consoleOut);

            // print FIFO output to console
            System.out.println("\nContents of FIFO output:");
            BufferedReader fifoReader = new BufferedReader(new FileReader("problem1/output/fifo_output.txt"));
            String line;
            while ((line = fifoReader.readLine()) != null) {
                System.out.println(line);
            }
            fifoReader.close();

            // --------- SJF Phase ---------
            PrintStream sjfOut = new PrintStream(new FileOutputStream("problem1/output/sjf_output.txt"));
            System.setOut(sjfOut);

            processes = CPUScheduler.readProcessesFromFile(filePath);

            System.out.println("---- Running SJF ----");
            CPUScheduler.runSJF(processes);
            CPUScheduler.printStatistics(processes);

            sjfOut.close();

            // restore console
            System.setOut(consoleOut);

            // print SJF output to console
            System.out.println("\nContents of SJF output:");
            BufferedReader sjfReader = new BufferedReader(new FileReader("problem1/output/sjf_output.txt"));
            while ((line = sjfReader.readLine()) != null) {
                System.out.println(line);
            }
            sjfReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}