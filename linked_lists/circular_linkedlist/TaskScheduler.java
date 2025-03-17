import java.util.*;

class Task {
    int taskID;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskID, String taskName, int priority, String dueDate) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    Task head;
    Task tail;

    public TaskScheduler() {
        head = null;
        tail = null;
    }

    public void addTaskAtBeginning(int taskID, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskID, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    public void addTaskAtEnd(int taskID, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskID, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            newTask.next = head;
        } else {
            tail.next = newTask;
            newTask.next = head;
            tail = newTask;
        }
    }

    public void addTaskAtPosition(int taskID, String taskName, int priority, String dueDate, int position) {
        Task newTask = new Task(taskID, taskName, priority, dueDate);
        if (position == 0) {
            addTaskAtBeginning(taskID, taskName, priority, dueDate);
            return;
        }
        Task current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) return;
        newTask.next = current.next;
        current.next = newTask;
        if (newTask.next == head) {
            tail = newTask;
        }
    }

    public void removeTaskByID(int taskID) {
        if (head == null) return;
        if (head.taskID == taskID) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            return;
        }
        Task current = head;
        while (current.next != head) {
            if (current.next.taskID == taskID) {
                current.next = current.next.next;
                if (current.next == head) {
                    tail = current;
                }
                return;
            }
            current = current.next;
        }
    }

    public void viewCurrentTask() {
        if (head != null) {
            System.out.println("Task ID: " + head.taskID + ", Task Name: " + head.taskName + ", Priority: " + head.priority + ", Due Date: " + head.dueDate);
        } else {
            System.out.println("No tasks available.");
        }
    }

    public void moveToNextTask() {
        if (head != null) {
            head = head.next;
            viewCurrentTask();
        } else {
            System.out.println("No tasks available.");
        }
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        Task current = head;
        do {
            System.out.println("Task ID: " + current.taskID + ", Task Name: " + current.taskName + ", Priority: " + current.priority + ", Due Date: " + current.dueDate);
            current = current.next;
        } while (current != head);
    }

    public Task searchTaskByPriority(int priority) {
        if (head == null) return null;
        Task current = head;
        do {
            if (current.priority == priority) {
                return current;
            }
            current = current.next;
        } while (current != head);
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTaskAtBeginning(1, "Task A", 3, "2025-03-20");
        scheduler.addTaskAtEnd(2, "Task B", 1, "2025-03-21");
        scheduler.addTaskAtEnd(3, "Task C", 2, "2025-03-22");
        scheduler.addTaskAtPosition(4, "Task D", 4, "2025-03-23", 2);

        System.out.println("All Tasks:");
        scheduler.displayAllTasks();

        System.out.println("\nCurrent Task:");
        scheduler.viewCurrentTask();

        System.out.println("\nMove to Next Task:");
        scheduler.moveToNextTask();

        System.out.println("\nRemove Task with ID 2:");
        scheduler.removeTaskByID(2);
        scheduler.displayAllTasks();

        System.out.println("\nSearch Task with Priority 3:");
        Task foundTask = scheduler.searchTaskByPriority(3);
        if (foundTask != null) {
            System.out.println("Task found: " + foundTask.taskName + ", Due Date: " + foundTask.dueDate);
        } else {
            System.out.println("No task found with the specified priority.");
        }
    }
}
