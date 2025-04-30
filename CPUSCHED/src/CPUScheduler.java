package CPUSCHED.src;

import java.io.*;
import java.util.*;

/**
 * A CPU Scheduler that simulates two scheduling algorithms: 
 * FIFO (First-In-First-Out) and SJF (Shortest Job First, non-preemptive).
 * 
 * This class provides methods to:
 * - Read process data from a file
 * - Simulate FIFO and SJF scheduling
 * - Print performance statistics after simulation
 */
public class CPUScheduler {

    /**
     * Reads a list of processes from a given input file.
     * Each process has an arrival time and a CPU burst time.
     *
     * @param filename the path to the input file
     * @return a list of processes read from the file
     * @throws IOException if the file cannot be read
     */
    public static List<Process> readProcessesFromFile(String filename) throws IOException {
        List<Process> processes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine(); // skip header
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length >= 2) {
                int arrival = Integer.parseInt(parts[0]);
                int burst = Integer.parseInt(parts[1]);
                processes.add(new Process(arrival, burst));
            }
        }
        reader.close();
        return processes;
    }

    /**
     * Simulates the FIFO (First-In-First-Out) scheduling algorithm.
     * Processes are sorted by arrival time and executed in that order.
     *
     * @param processes the list of processes to schedule
     */
    public static void runFIFO(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0;
        for (Process p : processes) {
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            p.startTime = currentTime;
            p.responseTime = p.startTime - p.arrivalTime;
            p.waitingTime = p.startTime - p.arrivalTime;
            p.finishTime = currentTime + p.burstTime;
            p.turnaroundTime = p.finishTime - p.arrivalTime;
            currentTime += p.burstTime;
        }
    }

    /**
     * Simulates the SJF (Shortest Job First, non-preemptive) scheduling algorithm.
     * Among the processes that have arrived, the one with the shortest burst time is selected next.
     *
     * @param processes the list of processes to schedule
     */
    public static void runSJF(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        PriorityQueue<Process> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.burstTime));
        int currentTime = 0;
        int index = 0;
        List<Process> scheduled = new ArrayList<>();

        while (scheduled.size() < processes.size()) {
            while (index < processes.size() && processes.get(index).arrivalTime <= currentTime) {
                pq.offer(processes.get(index++));
            }

            if (pq.isEmpty()) {
                currentTime = processes.get(index).arrivalTime;
                continue;
            }

            Process p = pq.poll();
            p.startTime = currentTime;
            p.responseTime = p.startTime - p.arrivalTime;
            p.waitingTime = p.startTime - p.arrivalTime;
            p.finishTime = currentTime + p.burstTime;
            p.turnaroundTime = p.finishTime - p.arrivalTime;
            currentTime += p.burstTime;
            scheduled.add(p);
        }
    }

    /**
     * Prints performance statistics of the scheduled processes.
     * Displays number of processes, total elapsed time, throughput, 
     * CPU utilization, average waiting time, average turnaround time, and average response time.
     *
     * @param processes the list of processes after scheduling
     */
    public static void printStatistics(List<Process> processes) {
        int n = processes.size();
        int totalElapsedTime = processes.stream().mapToInt(p -> p.finishTime).max().orElse(0);
        int totalBurstTime = processes.stream().mapToInt(p -> p.burstTime).sum();
        double throughput = (double) n / totalElapsedTime;
        double cpuUtil = (double) totalBurstTime / totalElapsedTime * 100;
        double avgWait = processes.stream().mapToInt(p -> p.waitingTime).average().orElse(0);
        double avgTurnaround = processes.stream().mapToInt(p -> p.turnaroundTime).average().orElse(0);
        double avgResponse = processes.stream().mapToInt(p -> p.responseTime).average().orElse(0);

        System.out.println("Number of processes: " + n);
        System.out.println("Total elapsed time: " + totalElapsedTime);
        System.out.printf("Throughput: %.4f\n", throughput);
        System.out.printf("CPU utilization: %.2f%%\n", cpuUtil);
        System.out.printf("Average waiting time: %.2f\n", avgWait);
        System.out.printf("Average turnaround time: %.2f\n", avgTurnaround);
        System.out.printf("Average response time: %.2f\n", avgResponse);
    }

    /**
     * Main function for running the scheduler directly from the command line.
     * Usage: java CPUScheduler &lt;fifo|sjf&gt; &lt;input_file&gt;
     *
     * @param args command-line arguments: scheduling type and input filename
     * @throws IOException if reading the file fails
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java CPUScheduler <fifo|sjf> <input_file>");
            return;
        }

        List<Process> processes = readProcessesFromFile(args[1]);

        if (args[0].equalsIgnoreCase("fifo")) {
            runFIFO(processes);
        } else if (args[0].equalsIgnoreCase("sjf")) {
            runSJF(processes);
        } else {
            System.out.println("Invalid scheduler type. Use 'fifo' or 'sjf'");
            return;
        }

        printStatistics(processes);
    }
}
