/*
    NAME: Lana Mahmoud Ali Musaffer
    ID: 1210455
    Dr. Bashar Tahayna
 */
/////////////////////////////////////////[PART 1: FCFS]///////////////////////////////////////
class ProcessFCFS {
    int processID;
    int arrivalTime;
    int burstTime;
    int comesBackAfter;
    int CompletionTime;
    int turnaroundTime;
    int waitingTime;

    // ProcessFCFS Constructor
    public ProcessFCFS(int processID, int arrivalTime, int burstTime, int comesBackAfter, int CompletionTime,
                       int turnaroundTime, int waitingTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.comesBackAfter = comesBackAfter;
        this.CompletionTime = CompletionTime;
        this.turnaroundTime = turnaroundTime;
        this.waitingTime = waitingTime;
    }
}

class NodeFCFS {
    ProcessFCFS process;
    NodeFCFS next;
}

class QueueFCFS {
    NodeFCFS front;
    NodeFCFS rear;

    void enqueue(ProcessFCFS process) {
        NodeFCFS newNode = new NodeFCFS();
        newNode.process = process;
        newNode.next = null;

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    ProcessFCFS dequeue() {
        if (front == null) {
            return null; //QUEUE IS EMPTY
        }

        NodeFCFS frontNode = front;
        ProcessFCFS dequeuedProcess = frontNode.process;

        front = frontNode.next;

        if (front == null) {
            rear = null; //LAST ELEMENT REMOVED
        }

        return dequeuedProcess;
    }

    boolean queueIsNotEmpty() {
        return front != null;
    }
}


///////////////////////////////////////[PART 2: SJF]/////////////////////////////////////////////////
class ProcessSJF {
    int processID;
    int arrivalTime;
    int burstTime;
    int comesBackAfter;
    int completionTime;
    int turnaroundTime;
    int waitingTime;

    // ProcessSJF Constructor
    public ProcessSJF(int processID, int arrivalTime, int burstTime, int comesBackAfter,
                      int completionTime, int turnaroundTime, int waitingTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.comesBackAfter = comesBackAfter;
        this.completionTime = completionTime;
        this.turnaroundTime = turnaroundTime;
        this.waitingTime = waitingTime;
    }
}

class NodeSJF {
    ProcessSJF process;
    NodeSJF next;

    NodeSJF(ProcessSJF process) {
        this.process = process;
        this.next = null;
    }
}

class QueueSJF {
    NodeSJF front;
    NodeSJF rear;

    void enqueue(ProcessSJF process) {
        NodeSJF newNode = new NodeSJF(process);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    ProcessSJF dequeue() {
        if (front == null) {
            return null; //QUEUE IS EMPTY
        }

        NodeSJF frontNode = front;
        ProcessSJF dequeuedProcess = frontNode.process;

        front = frontNode.next;
        if (front == null) {
            rear = null; //LAST ELEMENT REMOVED
        }

        return dequeuedProcess;
    }

    boolean isEmpty() {
        return front == null;
    }
}
/////////////////////////////////[PART 3]/////////////////////////////////////////////////
class ProcessSRTF {
    int processID;
    int arrivalTime;
    int burstTime;
    int comesBackAfter;
    int completionTime;
    int turnaroundTime;
    int waitingTime;
    int remainingTime;  // Initialize with burst time

    public ProcessSRTF(int processID, int arrivalTime, int burstTime, int comesBackAfter, int completionTime, int turnaroundTime, int waitingTime, int remainingTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.comesBackAfter = comesBackAfter;
        this.completionTime = completionTime;
        this.turnaroundTime = turnaroundTime;
        this.waitingTime = waitingTime;
        this.remainingTime = remainingTime;
    }
}

class NodeSRTF {
    ProcessSRTF process;
    NodeSRTF next;

    NodeSRTF(ProcessSRTF process) {
        this.process = process;
        this.next = null;
    }
}

class QueueSRTF {
    NodeSRTF front;
    NodeSRTF rear;

    void enqueue(ProcessSRTF process) {
        NodeSRTF newNode = new NodeSRTF(process);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    ProcessSRTF dequeue() {
        if (front == null) {
            return null; //QUEUE IS EMPTY
        }

        NodeSRTF frontNode = front;
        ProcessSRTF dequeuedProcess = frontNode.process;

        front = frontNode.next;
        if (front == null) {
            rear = null; //LAST ELEMENT REMOVED
        }

        return dequeuedProcess;
    }

    boolean isEmptyQueue() {
        return front == null;
    }
}
/////////////////////////////////[PART 4]/////////////////////////////////////////////////
class ProcessRR {
    int processID;
    int arrivalTime;
    int burstTime;
    int comesBackAfter;
    int completionTime;
    int turnaroundTime;
    int waitingTime;
    int remainingTime;

    // ProcessRR Constructor
    public ProcessRR(int processID, int arrivalTime, int burstTime, int comesBackAfter,
                     int completionTime, int turnaroundTime, int waitingTime) {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.comesBackAfter = comesBackAfter;
        this.completionTime = completionTime;
        this.turnaroundTime = turnaroundTime;
        this.waitingTime = waitingTime;
        this.remainingTime = burstTime;
    }
}

class NodeRR {
    ProcessRR process;
    NodeRR next;

    NodeRR(ProcessRR process) {
        this.process = process;
        this.next = null;
    }
}

class QueueRR {
    NodeRR front;
    NodeRR rear;

    void enqueue(ProcessRR process) {
        NodeRR newNode = new NodeRR(process);

        if (rear == null) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
    }

    ProcessRR dequeue() {
        if (front == null) {
            return null; // QUEUE IS EMPTY
        }

        NodeRR frontNode = front;
        ProcessRR dequeuedProcess = frontNode.process;

        front = frontNode.next;
        if (front == null) {
            rear = null; // LAST ELEMENT REMOVED
        }

        return dequeuedProcess;
    }

    boolean isEmptyQueueRR() {
        return front == null;
    }
}
/////////////////////////////////////////////////////////////////////////////////////////

public class Main {
    public static final int END_TIME = 200;
    public static final int NUM_OF_PROCESSES = 7;
    public static final int QUANTUM = 5;

    ////////////////////////////////////////////////////////////////////////////

    static void printAveragesFCFS(ProcessFCFS[] processes) {
        int total_wt = 0, total_tat = 0;

        //TOTAL WAITING TIME & TURNAROUND TIME
        for (int i = 0; i < NUM_OF_PROCESSES; i++) {
            total_wt += processes[i].waitingTime;
            total_tat += processes[i].turnaroundTime;
        }

        //PRINT THE AVERAGE WAITING TIME AND THE AVERAGE TURNAROUND TIME
        float avgWaitingTime = (float) total_wt / NUM_OF_PROCESSES;
        float avgTurnaroundTime = (float) total_tat / NUM_OF_PROCESSES;

        System.out.println("_____________");

        System.out.printf("\nAVERAGE WAITING TIME: %.2f\n", avgWaitingTime);
        System.out.printf("AVERAGE TURNAROUND TIME: %.2f\n", avgTurnaroundTime);

        System.out.println("\n**************");
    }

    static void FCFS(ProcessFCFS[] processes) {
        QueueFCFS readyQueue = new QueueFCFS();
        QueueFCFS waitingQueue = new QueueFCFS();
        int currentTime = 0;

        System.out.println("                        1. FCFS SCHEDULING ALGORITHM:");

        System.out.println("\nGANTT CHART:");
        System.out.print("->0 ");

        //ALL PROCESSES ARE INITIALLY ENQUEUED IN THE READY QUEUE
        for (int i = 0; i < NUM_OF_PROCESSES; i++) {
            readyQueue.enqueue(processes[i]);
        }

        //CONTINUE SCHEDULING PROCESSES UNTIL BOTH AF THE QUEUES ARE EMPTY
        while (readyQueue.queueIsNotEmpty() || waitingQueue.queueIsNotEmpty()) {
            while (waitingQueue.queueIsNotEmpty() && waitingQueue.front.process.arrivalTime <= currentTime) {
                ProcessFCFS waitingProcess = waitingQueue.dequeue();
                readyQueue.enqueue(waitingProcess);
            }

            ProcessFCFS currentProcess = readyQueue.dequeue(); //NEXT PROCESS TO BE EXECUTED

            //CHECKS IF THERE IS NO AVAILABLE PROCESS TO BE EXECUTED AT THE CURRENT TIME
            //TO HANDLE THE CASE WHERE WE HAVE NO PROCESS TO EXECUTE AT THE CURRENT TIME
            if (currentProcess == null) {
                currentTime++;  //MOVE FORWARD IN TIME
                continue;
            }

            //TO HANDLE THE CASE WHERE THE CURRENT TIME IS LESS THAN THE ARRIVAL TIME OF THE NEXT PROCESS
            if (currentTime < currentProcess.arrivalTime) {
                currentTime = currentProcess.arrivalTime;
            }

            System.out.printf("| P%d", currentProcess.processID);
            int completionTime = currentTime + currentProcess.burstTime;

            //KEEP EXECUTION EACH PROCESS UNTIL IT REACHED THE END TIME (200)
            if (completionTime < END_TIME) {
                //CALCULATE THE TURNAROUND TIME AND WAITING TIME FOR EACH PROCESS
                currentProcess.CompletionTime = completionTime;
                currentProcess.turnaroundTime += currentProcess.CompletionTime - currentProcess.arrivalTime;
                currentProcess.waitingTime += currentProcess.CompletionTime - currentProcess.arrivalTime - currentProcess.burstTime;

                //AFTER EACH PROCESS FINISHES ITS EXECUTION, IT WILL GO TO THE WAITING QUEUE
                if (currentProcess.comesBackAfter > 0) {
                    currentProcess.arrivalTime = currentProcess.CompletionTime + currentProcess.comesBackAfter;
                    waitingQueue.enqueue(currentProcess);
                }

                currentTime = completionTime; //TO KEEP UPDATING THE CURRENT TIME

                //PRINTING THE COMPLETION TIME IN THE GANTT CHART
                System.out.printf("->%d ", currentProcess.CompletionTime);
            } else {
                //FINISH THE PROCESS EXECUTION AT TIME = 200
                System.out.printf("->%d ", END_TIME);
                break;
            }
        }
        System.out.println("|\n");

        printAveragesFCFS(processes);
    }
    ///////////////////////////////////////////////////////////////////////////////////

    //SORTING THE PROCESSES USING BUBBLE SORT BASED ON THEIR BURST TIME
    static void sortReadyQueueByBurstTime(QueueSJF queue) {
        NodeSJF current = queue.front;
        while (current != null) {
            NodeSJF next = current.next;
            while (next != null) {
                if (current.process.burstTime > next.process.burstTime) {
                    // SWAP
                    ProcessSJF temp = current.process;
                    current.process = next.process;
                    next.process = temp;
                }
                next = next.next;
            }
            current = current.next;
        }
    }

    static void printAveragesSJF(ProcessSJF[] processes) {
        int total_wt = 0, total_tat = 0;
        int currentTime = 0;

        //TOTAL WAITING TIME & TURNAROUND TIME
        for (int i = 0; i < NUM_OF_PROCESSES; i++) {
            total_wt += processes[i].waitingTime;
            total_tat += processes[i].turnaroundTime;

            currentTime = Math.max(processes[i].completionTime, currentTime);
        }

        //PRINT THE AVERAGE WAITING TIME AND THE AVERAGE TURNAROUND TIME
        float avgWaitingTime = (float) total_wt / NUM_OF_PROCESSES;
        float avgTurnaroundTime = (float) total_tat / NUM_OF_PROCESSES;
        System.out.println("_____________");

        System.out.printf("\nAVERAGE WAITING TIME: %.2f\n", avgWaitingTime);
        System.out.printf("AVERAGE TURNAROUND TIME: %.2f\n", avgTurnaroundTime);

        System.out.println("\n**************");
    }

    static void SJF(ProcessSJF[] processes) {
        QueueSJF readyQueue = new QueueSJF();
        QueueSJF waitingQueue = new QueueSJF();
        int currentTime = 0;

        for (int i = 0; i < NUM_OF_PROCESSES; i++) {
            waitingQueue.enqueue(processes[i]);
        }

        System.out.println("                        2. SJF SCHEDULING ALGORITHM:");

        System.out.println("\nGANTT CHART:");
        System.out.print("->0 ");

        while (currentTime < END_TIME) {
            while (!waitingQueue.isEmpty() && waitingQueue.front.process.arrivalTime <= currentTime) {
                //MOVE THE PROCESS FROM THE WAITING QUEUE TO THE READY QUEUE
                ProcessSJF waitingProcess = waitingQueue.dequeue();
                readyQueue.enqueue(waitingProcess);
            }

            if (readyQueue.isEmpty()) {
                //FOR THE PROCESSES THAT HAVEN'T ARRIVED TO THE READY QUEUE YET
                if (!waitingQueue.isEmpty()) {
                    int nextArrival = waitingQueue.front.process.arrivalTime;
                    currentTime = Math.max(nextArrival, currentTime);
                } else {
                    //HANDLE THE CASE WHERE BOTH QUEUES ARE EMPTY
                    break;
                }
                continue;
            }

            //SHORTEST BURST TIME WILL BE AT THE FRONT OF THE READY QUEUE
            sortReadyQueueByBurstTime(readyQueue);

            //TAKING THE PROCESS WITH MINIMUM BURST TIME
            ProcessSJF currentProcess = readyQueue.dequeue();

            //CHECKS IF THERE IS NO AVAILABLE PROCESS TO BE EXECUTED AT THE CURRENT TIME
            //TO HANDLE THE CASE WHERE WE HAVE NO PROCESS TO EXECUTE AT THE CURRENT TIME
            if (currentProcess == null) {
                currentTime++;  //MOVE FORWARD IN TIME
                continue;
            }

            //TO HANDLE THE CASE WHERE THE CURRENT TIME IS LESS THAN THE ARRIVAL TIME OF THE NEXT PROCESS
            if (currentTime < currentProcess.arrivalTime) {
                currentTime = currentProcess.arrivalTime;
            }

            System.out.printf("| P%d", currentProcess.processID);
            int completionTime = currentTime + currentProcess.burstTime;

            //KEEP EXECUTION EACH PROCESS UNTIL IT REACHED THE END TIME (200)
            if (completionTime <= END_TIME) {
                //CALCULATE THE TURNAROUND TIME AND WAITING TIME FOR EACH PROCESS
                currentProcess.completionTime = completionTime;
                currentProcess.turnaroundTime += currentProcess.completionTime - currentProcess.arrivalTime;
                currentProcess.waitingTime += currentProcess.completionTime - currentProcess.arrivalTime - currentProcess.burstTime;

                //AFTER EACH PROCESS FINISHES ITS EXECUTION, IT WILL GO TO THE WAITING QUEUE
                if (currentProcess.comesBackAfter > 0) {
                    currentProcess.arrivalTime = currentProcess.completionTime + currentProcess.comesBackAfter;
                    waitingQueue.enqueue(currentProcess);
                }

                currentTime = completionTime; //TO KEEP UPDATING THE CURRENT TIME
                System.out.printf("->%d ", currentProcess.completionTime);
            } else {
                //FINISH THE PROCESS EXECUTION AT TIME = 200
                System.out.printf("->%d ", END_TIME);
                break;
            }
        }
        System.out.println("|\n");

        printAveragesSJF(processes);
    }
    //////////////////////////////////////////////////////////////////////////////////
    static void srtf(ProcessSRTF[] processes) {
        QueueSRTF readyQueue = new QueueSRTF();
        QueueSRTF waitingQueue = new QueueSRTF();
        int currentTime = 0;
        int totalProcessesExecuted = 0;
        ProcessSRTF currentProcess = null;
        int lastPrintedProcessID = -1; // Initialize with an invalid ID

        // Initialize remaining time for all processes and enqueue them into the waiting queue
        for (int i = 0; i < NUM_OF_PROCESSES; i++) {
            waitingQueue.enqueue(processes[i]);
        }

        System.out.println("                        3. SRTF SCHEDULING ALGORITHM:");

        System.out.println("\nGANTT CHART:");

        while (currentTime < END_TIME) {
            // Check for new arrivals during each time unit
            while (!waitingQueue.isEmptyQueue() && waitingQueue.front.process.arrivalTime <= currentTime) {
                ProcessSRTF arrivingProcess = waitingQueue.dequeue();
                readyQueue.enqueue(arrivingProcess);

                // If there is no current process or a process re-enters, get the next process from the ready queue
                if (currentProcess == null || arrivingProcess.remainingTime < currentProcess.remainingTime) {
                    if (currentProcess != null) {
                        // Re-enqueue the current process to the ready queue
                        readyQueue.enqueue(currentProcess);
                    }
                }
            }

            // Sort the ready queue by remaining time after processing all new arrivals
            sortReadyQueueByRemainingTime(readyQueue);

            // Update current process to be the process at the front of the ready queue
            if (!readyQueue.isEmptyQueue()) {
                currentProcess = readyQueue.front.process;
            }

            // Execute the current process for one time unit
            if (currentProcess != null) {
                printGanttChartSRTF(currentProcess, currentTime, lastPrintedProcessID);
                currentTime++; // Increment the current time after processing each time unit
                currentProcess.remainingTime--; // Update remaining time

                // Check if the process is completed
                if (currentProcess.remainingTime == 0) {
                    currentProcess.completionTime = currentTime;
                    currentProcess.turnaroundTime += currentProcess.completionTime - currentProcess.arrivalTime;
                    currentProcess.waitingTime += currentProcess.completionTime - currentProcess.arrivalTime - currentProcess.burstTime;

                    //totalProcessesExecuted++;
                    //currentProcess = null; // Clear the current process
                    if (currentProcess.comesBackAfter > 0) {
                        currentProcess.arrivalTime = currentTime + currentProcess.comesBackAfter;

                        // Restore remaining time to the original burst time
                        currentProcess.remainingTime = currentProcess.burstTime;

                        ProcessSRTF comebackProcess = readyQueue.dequeue();
                        waitingQueue.enqueue(comebackProcess);
                    }
                } else {
                    // Check if there's a process with shorter remaining time, and preempt the current process
                    if (!readyQueue.isEmptyQueue() && readyQueue.front.process.remainingTime < currentProcess.remainingTime) {
                        // Save the remaining time before enqueueing
                        int tempRemainingTime = currentProcess.remainingTime;
                        readyQueue.enqueue(currentProcess);
                        currentProcess = readyQueue.front.process; // Update current process
                        currentProcess.remainingTime = tempRemainingTime; // Restore remaining time
                    }
                }
            }
        }

        System.out.println("|\n\n");

        printAveragesSRTF(processes);
    }

    public static void sortReadyQueueByRemainingTime(QueueSRTF queue) {
        // If the queue is empty or has only one element, it is already sorted
        if (queue.front == null || queue.front == queue.rear) {
            return;
        }

        // Use Insertion Sort to sort the ready queue based on remaining time
        NodeSRTF sorted = null;
        NodeSRTF current = queue.front;

        while (current != null) {
            NodeSRTF next = current.next;

            if (sorted == null || sorted.process.remainingTime > current.process.remainingTime) {
                current.next = sorted;
                sorted = current;
            } else {
                NodeSRTF temp = sorted;
                while (temp.next != null && temp.next.process.remainingTime <= current.process.remainingTime) {
                    temp = temp.next;
                }
                current.next = temp.next;
                temp.next = current;
            }

            current = next;
        }

        // Update front and rear pointers after sorting
        queue.front = sorted;
        while (sorted != null && sorted.next != null) {
            sorted = sorted.next;
        }
        queue.rear = sorted;
    }

    public static void printAveragesSRTF(ProcessSRTF[] processes) {
        int total_wt = 0, total_tat = 0;
        int currentTime = 0;

        //TOTAL WAITING TIME & TURNAROUND TIME
        for (int i = 0; i < NUM_OF_PROCESSES; i++) {
            total_wt += processes[i].waitingTime;
            total_tat += processes[i].turnaroundTime;

            currentTime = Math.max(processes[i].completionTime, currentTime);
        }

        //PRINT THE AVERAGE WAITING TIME AND THE AVERAGE TURNAROUND TIME
        float avgWaitingTime = (float) total_wt / NUM_OF_PROCESSES;
        float avgTurnaroundTime = (float) total_tat / NUM_OF_PROCESSES;
        System.out.println("_____________");

        System.out.printf("\nAVERAGE WAITING TIME: %.2f\n", avgWaitingTime);
        System.out.printf("AVERAGE TURNAROUND TIME: %.2f\n", avgTurnaroundTime);

        System.out.println("\n**************");
    }

    public static void printGanttChartSRTF(ProcessSRTF currentProcess, int currentTime, int lastPrintedProcessID) {
        if (currentProcess.processID != lastPrintedProcessID) {
            if (lastPrintedProcessID != -1) {
                System.out.print(" " + currentTime);
            }
            System.out.printf(" | P%d %d-%d\n", currentProcess.processID, currentTime, currentTime + 1);
            lastPrintedProcessID = currentProcess.processID;
        } else {
            System.out.printf(" %d-%d", currentTime, currentTime + 1);
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
    static void printAveragesRR(ProcessRR[] processes) {
        int total_wt = 0, total_tat = 0;
        int currentTime = 0;

        //TOTAL WAITING TIME & TURNAROUND TIME
        for (int i = 0; i < NUM_OF_PROCESSES; i++) {
            total_wt += processes[i].waitingTime;
            total_tat += processes[i].turnaroundTime;

            currentTime = Math.max(processes[i].completionTime, currentTime);
        }

        //PRINT THE AVERAGE WAITING TIME AND THE AVERAGE TURNAROUND TIME
        float avgWaitingTime = (float) total_wt / NUM_OF_PROCESSES;
        float avgTurnaroundTime = (float) total_tat / NUM_OF_PROCESSES;
        System.out.println("_____________");

        System.out.printf("\nAVERAGE WAITING TIME: %.2f\n", avgWaitingTime);
        System.out.printf("AVERAGE TURNAROUND TIME: %.2f\n", avgTurnaroundTime);

        System.out.println("\n**************");
    }
    static void RoundRobin(ProcessRR[] processes) {
        QueueRR readyQueue = new QueueRR(); //FOR PROCESSES THAT ARE READY TO EXECUTE
        QueueRR waitingQueue = new QueueRR(); //FOR PROCESSES THAT ARE WAITING TO BE EXECUTED
        int currentTime = 0;

        //ENQUEUEING ALL PROCESSES BASED ON THEIR ARRIVAL TIME
        for (ProcessRR process : processes) {
            waitingQueue.enqueue(process);
        }

        System.out.println("                        4. RR SCHEDULING ALGORITHM:");

        System.out.println("\nGANTT CHART:");
        System.out.print("->0 ");

        while ((!readyQueue.isEmptyQueueRR() || !waitingQueue.isEmptyQueueRR()) && currentTime < END_TIME) {
            while (!waitingQueue.isEmptyQueueRR() && waitingQueue.front.process.arrivalTime <= currentTime) {
                //DEQUEUE THE PROCESS FROM WAITING QUEUE AND ENQUEUE IT IN THE READY QUEUE
                //IF IT HAS REMAINING TIME
                ProcessRR waitingProcess = waitingQueue.dequeue();
                readyQueue.enqueue(waitingProcess);
            }

            if (!readyQueue.isEmptyQueueRR()) {
                ProcessRR currentProcess = readyQueue.dequeue();
                int executionTime = Math.min(QUANTUM, currentProcess.remainingTime);
                currentProcess.remainingTime -= executionTime;
                currentTime += executionTime;

                //PRINTING THE GANTT CHART
                System.out.printf("| P%d->%d ", currentProcess.processID, currentTime);

                if (currentProcess.remainingTime > 0) {
                    ProcessRR storeCURRENT = currentProcess; //TEMP VARIABLE TO STORE THE CURRENT PROCESS

                    while (!waitingQueue.isEmptyQueueRR() && waitingQueue.front.process.arrivalTime <= currentTime) {
                        //DEQUEUE THE PROCESS FROM WAITING QUEUE AND ENQUEUE IT IN THE READY QUEUE
                        //IF IT HAS REMAINING TIME
                        ProcessRR waitingProcess = waitingQueue.dequeue();
                        readyQueue.enqueue(waitingProcess);

                    }

                    readyQueue.enqueue(storeCURRENT);
                } else {
                    //WHEN THE PROCESS FINISHES ITS EXECUTION, START CALCULATE
                    currentProcess.completionTime = currentTime;
                    currentProcess.turnaroundTime += currentProcess.completionTime - currentProcess.arrivalTime;
                    currentProcess.waitingTime += currentProcess.completionTime - currentProcess.arrivalTime - currentProcess.burstTime;

                    if (currentProcess.comesBackAfter > 0) {
                        currentProcess.arrivalTime = currentProcess.completionTime + currentProcess.comesBackAfter;
                        currentProcess.remainingTime = currentProcess.burstTime;

                        waitingQueue.enqueue(currentProcess);
                    }
                }
            }
        }

        System.out.println("|");

        printAveragesRR(processes);
    }

    public static void main(String[] args) {
        ProcessFCFS[] processes = {
                new ProcessFCFS(1, 0, 10, 2, 0, 0, 0),
                new ProcessFCFS(2, 1, 8, 4, 0, 0, 0),
                new ProcessFCFS(3, 3, 14, 6, 0, 0, 0),
                new ProcessFCFS(4, 4, 7, 8, 0, 0, 0),
                new ProcessFCFS(5, 6, 5, 3, 0, 0, 0),
                new ProcessFCFS(6, 7, 4, 6, 0, 0, 0),
                new ProcessFCFS(7, 8, 6, 9, 0, 0, 0)
        };

        FCFS(processes);

        ProcessSJF[] processes2 = {
                new ProcessSJF(1, 0, 10, 2, 0, 0, 0),
                new ProcessSJF(2, 1, 8, 4, 0, 0, 0),
                new ProcessSJF(3, 3, 14, 6, 0, 0, 0),
                new ProcessSJF(4, 4, 7, 8, 0, 0, 0),
                new ProcessSJF(5, 6, 5, 3, 0, 0, 0),
                new ProcessSJF(6, 7, 4, 6, 0, 0, 0),
                new ProcessSJF(7, 8, 6, 9, 0, 0, 0)
        };

        SJF(processes2);

        ProcessSRTF[] processes3 = {
                new ProcessSRTF(1, 0, 10, 2, 0, 0, 0, 10),
                new ProcessSRTF(2, 1, 8, 4, 0, 0, 0, 8),
                new ProcessSRTF(3, 3, 14, 6, 0, 0, 0, 14),
                new ProcessSRTF(4, 4, 7, 8, 0, 0, 0, 7),
                new ProcessSRTF(5, 6, 5, 3, 0, 0, 0, 5),
                new ProcessSRTF(6, 7, 4, 6, 0, 0, 0, 4),
                new ProcessSRTF(7, 8, 6, 9, 0, 0, 0, 6)
        };

        srtf(processes3);

        ProcessRR[] processes4 = {
                new ProcessRR(1, 0, 10, 2, 0, 0, 0),
                new ProcessRR(2, 1, 8, 4, 0, 0, 0),
                new ProcessRR(3, 3, 14, 6, 0, 0, 0),
                new ProcessRR(4, 4, 7, 8, 0, 0, 0),
                new ProcessRR(5, 6, 5, 3, 0, 0, 0),
                new ProcessRR(6, 7, 4, 6, 0, 0, 0),
                new ProcessRR(7, 8, 6, 9, 0, 0, 0)
        };

        RoundRobin(processes4);
    }
}