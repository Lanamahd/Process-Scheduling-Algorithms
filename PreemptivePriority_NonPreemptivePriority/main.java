/*
LANA MUSAFFER
1210455
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ProcessPP {
    int processID;
    int arrivalTime;
    int burstTime;
    int comesBackAfter;
    int priority;
    int originalPriority;
    int completionTime;
    int turnaroundTime;
    int waitingTime;
    int remainingTime;
    int timeInReadyQueue;
    int waitPriority;

    public ProcessPP(int processID, int arrivalTime, int burstTime, int comesBackAfter, int priority,
                     int completionTime, int turnaroundTime, int waitingTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.comesBackAfter = comesBackAfter;
        this.priority = priority;
        this.originalPriority = priority;  // Store the original priority
        this.completionTime = completionTime;
        this.turnaroundTime = turnaroundTime;
        this.waitingTime = waitingTime;
        this.remainingTime = burstTime;
        this.timeInReadyQueue = 0;
    }
}

class SimplePriorityQueue<T> {
    private List<T> queue;
    private Comparator<T> comparator;

    public SimplePriorityQueue(Comparator<T> comparator) {
        this.queue = new ArrayList<>();
        this.comparator = comparator;
    }

    public boolean isEmptyQueuePP() {
        return queue.isEmpty();
    }

    public void sort() {
        queue.sort(comparator);
    }

    public void add(T element) {
        queue.add(element);
        queue.sort(comparator);
    }

    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.remove(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public List<T> getQueue() {
        return new ArrayList<>(queue);
    }

    // New method to add multiple elements
    public void addAll(List<T> elements) {
        queue.addAll(elements);
        queue.sort(comparator);
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

class NodePP {
    ProcessPP process;
    int priority;
    NodePP next;

    NodePP(ProcessPP process, int priority) {
        this.process = process;
        this.priority = priority;
        this.next = null;
    }
}

class QueuePP {
    NodePP front;
    NodePP rear;

    void enqueue(ProcessPP process, int priority) {
        NodePP newNode = new NodePP(process, priority);

        if (rear == null) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
    }

    ProcessPP dequeue() {
        if (front == null) {
            return null;
        }

        NodePP frontNode = front;
        ProcessPP dequeuedProcess = frontNode.process;

        front = frontNode.next;
        if (front == null) {
            rear = null;
        }

        return dequeuedProcess;
    }

    boolean isEmptyQueuePP() {
        return front == null;
    }
}

public class Main {
    public static final int END_TIME = 200;
    public static final int NUM_OF_PROCESSES = 7;
    public static final int QUANTUM = 5;
    public static final int AGING_THRESHOLD = 5;

    static void sortProcessesByPriority(QueuePP queue) {
        NodePP current = queue.front;

        while (current != null) {
            NodePP next = current.next;

            while (next != null) {
                // Swap processes based on priority
                if (current.process.priority > next.process.priority) {
                    ProcessPP temp = current.process;
                    current.process = next.process;
                    next.process = temp;
                }
                // If priorities are equal, sort by arrival time (FCFS)
                else if (current.process.priority == next.process.priority &&
                        current.process.arrivalTime > next.process.arrivalTime) {
                    // Swap processes based on arrival time
                    ProcessPP temp = current.process;
                    current.process = next.process;
                    next.process = temp;
                }

                next = next.next;
            }

            current = current.next;
        }

        // Update front and rear pointers after sorting
        if (queue.front != null) {
            NodePP temp = queue.front;
            while (temp.next != null) {
                temp = temp.next;
            }
            queue.rear = temp;
        }
    }

    static void restoreOriginalPriorities(ProcessPP[] processes) {
        for (ProcessPP process : processes) {
            process.priority = process.originalPriority;
        }
    }

    class PreemptivePriorityScheduler {
        private static final int QUANTUM = 5;
        private static final int AGING_THRESHOLD = 5;

        static void schedule(ProcessPP[] processes) {
            SimplePriorityQueue<ProcessPP> readyQueue = new SimplePriorityQueue<>(
                    Comparator.comparingInt((ProcessPP p) -> p.priority).thenComparingInt(p -> p.arrivalTime));
            QueuePP waitingQueue = new QueuePP();
            int currentTime = 0;

            // Declare and initialize elapsedTimeCounter
            int elapsedTimeCounter = 0;
            int INTERVAL_TO_RESTORE_PRIORITIES = 5;  // Set your desired interval

            for (ProcessPP process : processes) {
                waitingQueue.enqueue(process, process.priority);
            }

            System.out.println("                       5. Preemptive Priority Scheduling Algorithm:");

            System.out.println("\nGANTT CHART:");
            System.out.print("->0 ");

            ProcessPP currentProcess = null;

            while ((!readyQueue.isEmpty() || !waitingQueue.isEmptyQueuePP()) && currentTime < Main.END_TIME) {
                boolean processExecuted = false;

                while (!waitingQueue.isEmptyQueuePP() && waitingQueue.front.process.arrivalTime <= currentTime) {
                    ProcessPP waitingProcess = waitingQueue.dequeue();
                    readyQueue.add(waitingProcess);
                }

                if (!readyQueue.isEmpty()) {
                    ProcessPP topPriorityProcess = readyQueue.poll();

                    if (currentProcess == null || topPriorityProcess.priority < currentProcess.priority) {
                        if (currentProcess != null) {
                            readyQueue.add(currentProcess);
                        }
                        currentProcess = topPriorityProcess;
                        printGanttChartPP(currentProcess, currentTime);
                        processExecuted = true;
                    } else {
                        readyQueue.add(topPriorityProcess);
                    }

                    int executionTime = Math.min(QUANTUM, currentProcess.remainingTime);
                    currentProcess.remainingTime -= executionTime;
                    currentTime += executionTime;

                    if (currentProcess.remainingTime == 0) {
                        currentProcess.completionTime = currentTime;
                        currentProcess.turnaroundTime += currentProcess.completionTime - currentProcess.arrivalTime;
                        currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;

                        if (currentProcess.comesBackAfter > 0) {
                            currentProcess.arrivalTime = currentProcess.completionTime + currentProcess.comesBackAfter;
                            currentProcess.remainingTime = currentProcess.burstTime;

                            if (currentProcess.arrivalTime <= currentTime) {
                                waitingQueue.enqueue(currentProcess, currentProcess.priority);
                            } else {
                                readyQueue.add(currentProcess);
                            }

                            ProcessPP comebackProcess = readyQueue.poll();
                            waitingQueue.enqueue(comebackProcess, comebackProcess.priority);
                        }

                        currentProcess = null;
                    }
                    currentTime++;
                    elapsedTimeCounter++;
                } else {
                    // If no process is executed at the current time, just increment the time
                    System.out.print("| IDLE (" + currentTime + ") ");
                    currentTime++;
                }

                // Aging: Increment the time spent in the ready queue and update priorities
                for (ProcessPP process : readyQueue.getQueue()) {
                    process.timeInReadyQueue++;
                    if (process.timeInReadyQueue >= AGING_THRESHOLD) {
                        process.priority--;  // Aging: Decrement priority if a process has been in the ready queue for 5 time units
                        process.timeInReadyQueue = 0;  // Reset time in ready queue after aging
                    }
                }

                // Check if there are 5 or more processes in the waiting queue, then decrement priority
                if (!waitingQueue.isEmptyQueuePP()) {
                    int countWaitingProcesses = 0;
                    NodePP currentNode = waitingQueue.front;

                    while (currentNode != null) {
                        countWaitingProcesses++;
                        currentNode = currentNode.next;
                    }

                    if (countWaitingProcesses >= 5) {
                        currentNode = waitingQueue.front;
                        while (currentNode != null) {
                            currentNode.process.priority--;
                            currentNode = currentNode.next;
                        }
                    }
                }

                // Check if it's time to restore original priorities
                if (elapsedTimeCounter >= INTERVAL_TO_RESTORE_PRIORITIES) {
                    restoreOriginalPriorities(processes);
                    elapsedTimeCounter = 0;  // Reset the counter
                }
            }

            System.out.println("|\n");

            printAveragesPP(processes);
        }

        private static void printGanttChartPP(ProcessPP currentProcess, int currentTime) {
            int completionTime = currentTime + currentProcess.remainingTime;
            System.out.printf("| P%d->%d ", currentProcess.processID, completionTime);
        }

        private static void restoreOriginalPriorities(ProcessPP[] processes) {
            for (ProcessPP process : processes) {
                process.priority = process.originalPriority;
            }
        }

        private static void printAveragesPP(ProcessPP[] processes) {
            int total_wt = 0, total_tat = 0;

            for (int i = 0; i < Main.NUM_OF_PROCESSES; i++) {
                total_wt += processes[i].waitingTime;
                total_tat += processes[i].turnaroundTime;
            }

            float avgWaitingTime = (float) total_wt / Main.NUM_OF_PROCESSES;
            float avgTurnaroundTime = (float) total_tat / Main.NUM_OF_PROCESSES;

            System.out.println("_");
            System.out.printf("\nAVERAGE WAITING TIME: %.2f\n", avgWaitingTime);
            System.out.printf("AVERAGE TURNAROUND TIME: %.2f\n", avgTurnaroundTime);
            System.out.println("\n**");
        }
    }

    class NonPreemptivePriorityScheduler {
        static void schedule(ProcessPP[] processes) {
            SimplePriorityQueue<ProcessPP> readyQueue = new SimplePriorityQueue<>(
                    Comparator.comparingInt((ProcessPP p) -> p.priority).thenComparingInt(p -> p.arrivalTime));
            QueuePP waitingQueue = new QueuePP();
            int currentTime = 0;

            // Declare and initialize elapsedTimeCounter
            int elapsedTimeCounter = 0;
            int INTERVAL_TO_RESTORE_PRIORITIES = 5;  // Set your desired interval

            for (ProcessPP process : processes) {
                waitingQueue.enqueue(process, process.priority);
            }

            System.out.println("                        Non-preemptive Priority Scheduling Algorithm:");

            System.out.println("\nGANTT CHART:");
            System.out.print("->0 ");

            ProcessPP currentProcess = null;

            while ((!readyQueue.isEmpty() || !waitingQueue.isEmptyQueuePP()) && currentTime < Main.END_TIME) {
                boolean processExecuted = false;

                while (!waitingQueue.isEmptyQueuePP() && waitingQueue.front.process.arrivalTime <= currentTime) {
                    ProcessPP waitingProcess = waitingQueue.dequeue();
                    readyQueue.add(waitingProcess);
                }

                if (!readyQueue.isEmpty()) {
                    ProcessPP topPriorityProcess = readyQueue.poll();

                    if (currentProcess == null) {
                        currentProcess = topPriorityProcess;
                        printGanttChartPP(currentProcess, currentTime);
                        processExecuted = true;
                    }

                    int executionTime = Math.min(topPriorityProcess.remainingTime, Main.END_TIME - currentTime);
                    topPriorityProcess.remainingTime -= executionTime;
                    currentTime += executionTime;

                    if (topPriorityProcess.remainingTime == 0) {
                        topPriorityProcess.completionTime = currentTime;
                        topPriorityProcess.turnaroundTime += topPriorityProcess.completionTime - topPriorityProcess.arrivalTime;
                        topPriorityProcess.waitingTime = topPriorityProcess.turnaroundTime - topPriorityProcess.burstTime;

                        if (topPriorityProcess.comesBackAfter > 0) {
                            topPriorityProcess.arrivalTime = topPriorityProcess.completionTime + topPriorityProcess.comesBackAfter;
                            topPriorityProcess.remainingTime = topPriorityProcess.burstTime;

                            if (topPriorityProcess.arrivalTime <= currentTime) {
                                waitingQueue.enqueue(topPriorityProcess, topPriorityProcess.priority);
                            } else {
                                readyQueue.add(topPriorityProcess);
                            }
                        }

                        currentProcess = null;
                    }
                    currentTime++;
                    elapsedTimeCounter++;
                } else {
                    // If no process is executed at the current time, just increment the time
                    System.out.print("| IDLE (" + currentTime + ") ");
                    currentTime++;
                }

                // Aging: Increment the time spent in the ready queue and update priorities
                for (ProcessPP process : readyQueue.getQueue()) {
                    process.timeInReadyQueue++;
                    if (process.timeInReadyQueue >= AGING_THRESHOLD) {
                        process.priority--;  // Aging: Decrement priority if a process has been in the ready queue for 5 time units
                        process.timeInReadyQueue = 0;  // Reset time in ready queue after aging
                    }
                }

                // Check if there are 5 or more processes in the waiting queue, then decrement priority
                if (!waitingQueue.isEmptyQueuePP()) {
                    int countWaitingProcesses = 0;
                    NodePP currentNode = waitingQueue.front;

                    while (currentNode != null) {
                        countWaitingProcesses++;
                        currentNode = currentNode.next;
                    }

                    if (countWaitingProcesses >= 5) {
                        currentNode = waitingQueue.front;
                        while (currentNode != null) {
                            currentNode.process.priority--;
                            currentNode = currentNode.next;
                        }
                    }
                }

                // Check if it's time to restore original priorities
                if (elapsedTimeCounter >= INTERVAL_TO_RESTORE_PRIORITIES) {
                    restoreOriginalPriorities(processes);
                    elapsedTimeCounter = 0;  // Reset the counter
                }
            }

            System.out.println("|\n");

            printAveragesPP(processes);
        }

        private static void printGanttChartPP(ProcessPP currentProcess, int currentTime) {
            int completionTime = currentTime + currentProcess.remainingTime;
            System.out.printf("| P%d->%d ", currentProcess.processID, completionTime);
        }

        private static void restoreOriginalPriorities(ProcessPP[] processes) {
            for (ProcessPP process : processes) {
                process.priority = process.originalPriority;
            }
        }

        private static void printAveragesPP(ProcessPP[] processes) {
            int total_wt = 0, total_tat = 0;

            for (int i = 0; i < Main.NUM_OF_PROCESSES; i++) {
                total_wt += processes[i].waitingTime;
                total_tat += processes[i].turnaroundTime;
            }

            float avgWaitingTime = (float) total_wt / Main.NUM_OF_PROCESSES;
            float avgTurnaroundTime = (float) total_tat / Main.NUM_OF_PROCESSES;

            System.out.println("_");
            System.out.printf("\nAVERAGE WAITING TIME: %.2f\n", avgWaitingTime);
            System.out.printf("AVERAGE TURNAROUND TIME: %.2f\n", avgTurnaroundTime);
            System.out.println("\n**");
        }
    }

        public static void main(String[] args) {
            ProcessPP[] processes = {
                    new ProcessPP(1, 0, 10, 2, 3, 0, 0, 0),
                    new ProcessPP(2, 1, 8, 4, 2, 0, 0, 0),
                    new ProcessPP(3, 3, 14, 6, 3, 0, 0, 0),
                    new ProcessPP(4, 4, 7, 8, 1, 0, 0, 0),
                    new ProcessPP(5, 6, 5, 3, 0, 0, 0, 0),
                    new ProcessPP(6, 7, 4, 6, 1, 0, 0, 0),
                    new ProcessPP(7, 8, 6, 9, 2, 0, 0, 0)
            };

            PreemptivePriorityScheduler.schedule(processes.clone());

            NonPreemptivePriorityScheduler.schedule(processes.clone());
        }
    }