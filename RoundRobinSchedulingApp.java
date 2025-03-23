class Process {
    int processId;
    int burstTime;
    int priority;
    Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head;
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.head = null;
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(int processId, int burstTime, int priority) {
        Process newProcess = new Process(processId, burstTime, priority);
        if (head == null) {
            head = newProcess;
            newProcess.next = head;
        } else {
            Process temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newProcess;
            newProcess.next = head;
        }
    }

    public void removeProcess(int processId) {
        if (head == null) return;

        Process temp = head;
        Process prev = null;
        do {
            if (temp.processId == processId) {
                if (prev == null) {
                    // Remove head
                    if (head.next == head) {
                        head = null;
                    } else {
                        prev = head;
                        while (prev.next != head) {
                            prev = prev.next;
                        }
                        head = head.next;
                        prev.next = head;
                    }
                } else {
                    prev.next = temp.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    public void execute() {
        if (head == null) return;

        Process temp = head;
        do {
            System.out.println("Executing Process ID: " + temp.processId);
            if (temp.burstTime > timeQuantum) {
                temp.burstTime -= timeQuantum;
                temp = temp.next;
            } else {
                removeProcess(temp.processId);
                temp = temp.next;
            }
        } while (head != null);
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes to display.");
            return;
        }

        Process temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
}

// Example usage
public class RoundRobinSchedulingApp {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(4);
        scheduler.addProcess(1, 10, 1);
        scheduler.addProcess(2, 5, 2);
        scheduler.addProcess(3, 7, 1);
        scheduler.displayProcesses();
        scheduler.execute();
    }
}