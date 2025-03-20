public class StudentRecordManagement {
    public static void main(String[] args) {
        StudentList list = new StudentList();

        // Adding students
        list.addStudent(101, "dhruv", 20, 'A', 0);
        list.addStudent(102, "naam", 21, 'B', 1);
        list.addStudent(103, "vidit", 22, 'C', 2);

        // Display all students
        System.out.println("Student Records:");
        list.displayStudents();

        // Search for a student
        System.out.println("\nSearching for Roll Number 102:");
        list.searchStudent(102);

        // Update a student's grade
        System.out.println("\nUpdating grade for Roll Number 101 to 'A+':");
        list.updateGrade(101, 'A');
        list.displayStudents();

        // Delete a student
        System.out.println("\nDeleting student with Roll Number 103:");
        list.deleteStudent(103);
        list.displayStudents();
    }
}

class Node {
    int rollNumber;
    String name;
    int age;
    char grade;
    Node next;

    public Node(int rollNumber, String name, int age, char grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    private Node head;

    public StudentList() {
        head = null;
    }

    // Add a student to a specific position
    public void addStudent(int rollNumber, String name, int age, char grade, int position) {
        Node newNode = new Node(rollNumber, name, age, grade);
        if (position == 0 || head == null) {
            newNode.next = head;
            head = newNode;
            System.out.println("Student added at position " + position);
            return;
        }

        Node temp = head;
        int index = 0;
        while (temp != null && index < position - 1) {
            temp = temp.next;
            index++;
        }

        if (temp == null) {
            System.out.println("Invalid position. Student not added.");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        System.out.println("Student added at position " + position);
    }

    // Delete a student by Roll Number
    public void deleteStudent(int rollNumber) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return;
        }

        if (head.rollNumber == rollNumber) {
            head = head.next;
            System.out.println("Student with Roll Number " + rollNumber + " deleted.");
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.rollNumber != rollNumber) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Student with Roll Number " + rollNumber + " deleted.");
        }
    }

    // Search for a student by Roll Number
    public void searchStudent(int rollNumber) {
        Node temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                System.out.println("Student Found: " + temp.rollNumber + " " + temp.name + " " + temp.age + " " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Update student grade by Roll Number
    public void updateGrade(int rollNumber, char grade) {
        Node temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                temp.grade = grade;
                System.out.println("Grade updated for Roll Number " + rollNumber + " to " + grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }

    // Display all students
    public void displayStudents() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.rollNumber + " " + temp.name + " " + temp.age + " " + temp.grade);
            temp = temp.next;
        }
    }
}
