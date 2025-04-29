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
            // save original System.out
            PrintStream consoleOut = System.out;

            // redirect output to file
            PrintStream fileOut = new PrintStream(new FileOutputStream("problem1/output/scheduling_output.txt"));
            System.setOut(fileOut);

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

            // close file output when done
            fileOut.close();

            // set System.out back to console
            System.setOut(consoleOut);
            
            // print the output file to console
            BufferedReader reader = new BufferedReader(new FileReader("problem1/output/scheduling_output.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
