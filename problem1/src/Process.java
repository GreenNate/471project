package problem1.src;

/**
 * Represents a simulated process with various scheduling attributes.
 * 
 * A process has an arrival time, a CPU burst time, and fields for 
 * start time, finish time, waiting time, turnaround time, and response time,
 * which are filled in during scheduling simulations.
 */
public class Process {
    /** The time when the process arrives in the ready queue. */
    public int arrivalTime;

    /** The amount of CPU time required by the process (burst time). */
    public int burstTime;

    /** The time when the process first starts executing on the CPU. */
    public int startTime;

    /** The time when the process finishes execution. */
    public int finishTime;

    /** Total time the process spent waiting in the ready queue. */
    public int waitingTime;

    /** Total time from arrival to completion of the process. */
    public int turnaroundTime;

    /** Time from arrival until the process first starts execution. */
    public int responseTime;

    /**
     * Constructs a new process with specified arrival and burst times.
     * Initializes all scheduling fields to default values.
     *
     * @param arrivalTime the time the process arrives
     * @param burstTime the amount of CPU time needed by the process
     */
    public Process(int arrivalTime, int burstTime) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.startTime = -1;        // uninitialized
        this.finishTime = -1;       // uninitialized
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.responseTime = 0;
    }
}
