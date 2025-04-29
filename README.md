# CPU Scheduling and Producer-Consumer Problem

This project contains the solutions for two problems implemented in Java:

1. **CPU Scheduling Simulation**: Simulates two CPU scheduling algorithms: FIFO (First-In-First-Out) and SJF (Shortest Job First, non-preemptive).
2. **Producer-Consumer Problem**: Implements a multi-threaded simulation of the Producer-Consumer problem using semaphores.


---

## Problem 1: CPU Scheduling Simulation

### Description
Simulates two CPU scheduling algorithms:
- **FIFO (First-In-First-Out)**: Processes are executed in the order of their arrival.
- **SJF (Shortest Job First)**: Among the processes that have arrived, the one with the shortest burst time is executed next.

### Input
The input file (`problem1/input/Datafile1-txt.txt`) contains process data in the following format: 

ArrivalTime CPUBurstlength

### How to Run
1. Compile the code:
   ```bash
   make compile1

2. Run the code:
    ```bash
    make run1

Alternatively, you can run it directly:
    ```bash
    java -cp problem1/classes problem1.src.Main problem1/input/Datafile1-txt.txt
    ```

### Output
The program prints scheduling statistics for both FIFO and SJF algorithms, including:

1. Total elapsed time
2. Throughput
3. CPU utilization
4. Average waiting time
5. Average turnaround time
6. Average response time

---

## Problem 2: Producer-Consumer Problem

### Description
Simulates the Producer-Consumer problem using semaphores. Multiple producer and consumer threads interact with a shared bounded buffer:

Producers generate random integers and insert them into the buffer.
Consumers remove integers from the buffer.
Command-Line Arguments
The program accepts three arguments:

1. Sleep time (in seconds) for the main thread before stopping the simulation.
2. Number of producer threads.
3. Number of consumer threads.

### How to Run
1. Compile the code:
```bash
make compile2
```

2. Run the simulation:
```bash
make run2 ARGS="<sleepTime> <numProducers> <numConsumers>"
```

Example:
```bash
make run2 ARGS="5 2 3"
```

Alternatively, you can run it directly:
```bash
java -cp problem2/classes problem2.src.Main <sleepTime> <numProducers> <numConsumers>
```

### Output
The program prints messages indicating:

1. Items produced by producers.
2. Items consumed by consumers.
3. Buffer status (e.g., full or empty).

### Testing

To run the predefined test cases use:
```bash
make test
```

Test results are saved in the problem2/outputs/ directory.

---

## Documentation and Clean

### Documentation
Generate Javadocs for both problems:
```bash
make docs
```

The documentation will be generated in problem1Docs/ and problem2Docs/.

The documents can be accessed by opening problem1Docs/index.html or problem2Docs/index.html with a browser.
    
### Clean
To remove compiled files, docs, and outputs:
```bash
make clean
```
