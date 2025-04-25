# Process Scheduling Algorithms

This project implements various **Process Scheduling Algorithms** in Java, demonstrating how different methods operate in a CPU scheduling environment. These algorithms are implemented to explore their behavior, advantages, and limitations in managing process execution.

## Features
### File 1: Core Scheduling Algorithms
- **First-Come, First-Served (FCFS)**: Implements the simplest scheduling algorithm where processes are executed in the order they arrive.
- **Shortest Job Next (SJN)**: Executes processes with the shortest burst time first.
- **Priority Scheduling**: Prioritizes processes based on their assigned priority.
- **Round-Robin (RR)**: Allocates a fixed time slice to each process in a cyclic manner.

### File 2: Preemptive and Non-Preemptive Scheduling
- **Preemptive Scheduling**: Allows interruption of ongoing processes to allocate resources to higher-priority processes dynamically.
- **Non-Preemptive Scheduling**: Executes processes to completion once they start, without interruption.

## Tech Stack
- **Java**: 100% of the project is implemented in Java.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed on your system.
- A Java IDE or text editor (e.g., IntelliJ IDEA, Eclipse, or VS Code).

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Lanamahd/Process-Scheduling-Algorithms.git
   cd Process-Scheduling-Algorithms

2. Compile the Java files:
   ```bash
    javac -d bin src/*.java

3. Run the application:
   ```bash
    java -cp bin Main

  ## Usage
  1. Run the main program to choose a scheduling algorithm.
  2. Input process details (e.g., process ID, burst time, priority, arrival time).
  3. View the results, including:
      - Execution order of processes.
      - Metrics like average waiting time, turnaround time, and response time.
   

## Conclusion

This project provides a comprehensive implementation of various **Process Scheduling Algorithms**, offering insights into how different scheduling techniques work in managing CPU processes. By exploring FCFS, SJN, Priority, Round-Robin, preemptive, and non-preemptive approaches, the project highlights the strengths and trade-offs of each method. It serves as a valuable resource for understanding scheduling concepts and their practical applications in operating systems.

