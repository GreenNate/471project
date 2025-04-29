# CPU Scheduling and Producer-Consumer Simulation

This project contains two distinct problems implemented in Java:

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