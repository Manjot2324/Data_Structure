import java.util.*;

class Process {
    int processID;
    int burstTime;
    int remainingBurstTime;
    int priority;
    Process next;

    public Process(int processID, int burstTime, int priority) {
        this.processID = processID;
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    Process head;
    Process tail;
    int totalProcesses;

    public RoundRobinScheduler() {
        head = null;
        tail = null;
        totalProcesses = 0;
    }

    public void addProcess(int processID, int burstTime, int priority) {
        Process newProcess = new Process(processID, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
        totalProcesses++;
    }

    public void removeProcessByID(int processID) {
        if (head == null) return;

        if (head.processID == processID) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            totalProcesses--;
            return;
        }

        Process current = head;
        while (current.next != head) {
            if (current.next.processID == processID) {
                current.next = current.next.next;
                if (current.next == head) {
                    tail = current;
                }
                totalProcesses--;
                return;
            }
            current = current.next;
        }
    }

    public void scheduleRoundRobin(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        int totalWaitTime = 0;
        int totalTurnaroundTime = 0;
        int numProcesses = totalProcesses;

        Process current = head;
        while (numProcesses > 0) {
            if (current.remainingBurstTime > timeQuantum) {
                current.remainingBurstTime -= timeQuantum;
                totalWaitTime += current.remainingBurstTime;
            } else {
                totalWaitTime += current.remainingBurstTime;
                totalTurnaroundTime += current.burstTime;
                current.remainingBurstTime = 0;
                numProcesses--;
                System.out.println("Process " + current.processID + " completed.");
                removeProcessByID(current.processID);
            }

            current = current.next;
            displayProcesses();
        }

        double avgWaitTime = (double) totalWaitTime / totalProcesses;
        double avgTurnaroundTime = (double) totalTurnaroundTime / totalProcesses;

        System.out.println("\nAverage Waiting Time: " + avgWaitTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        Process current = head;
        do {
            System.out.println("Process ID: " + current.processID + ", Burst Time: " + current.burstTime + ", Remaining Burst Time: " + current.remainingBurstTime + ", Priority: " + current.priority);
            current = current.next;
        } while (current != head);
    }
}

public class Main {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();

        scheduler.addProcess(1, 10, 1);
        scheduler.addProcess(2, 5, 2);
        scheduler.addProcess(3, 8, 3);
        scheduler.addProcess(4, 6, 4);

        System.out.println("Initial Process List:");
        scheduler.displayProcesses();

        int timeQuantum = 3;
        System.out.println("\nStarting Round Robin Scheduling with Time Quantum: " + timeQuantum);
        scheduler.scheduleRoundRobin(timeQuantum);
    }
}
