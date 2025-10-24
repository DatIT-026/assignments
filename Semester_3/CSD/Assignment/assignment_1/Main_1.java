import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    LinkedList() { head = null; }

    // insert at the beginning
    public void addFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // insert at the end, before null
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) current = current.next;
        current.next = newNode;
    }

    // insert at the given positition (0-based index)
    public void insertAtPosition(int position, int data) {
        if (position == 0) {
            addFirst(data); 
            return;
        }

        Node newNode = new Node(data);
        Node current = head;

        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) return;
        newNode.next = current.next;
        current.next = newNode;
    }

    public void sort() {
        if (head == null || head.next == null) return;

        Node current, index;

        for (current = head; current.next != null; current = current.next) {
            for (index = current.next; index != null; index = index.next) {
                if (current.data > index.data) {
                    // Swap values
                    int temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
            }
        }
    }

    // insert in a sorted list
    public void insertSorted(int data) {
        Node newNode = new Node(data);

        sort();

        if (head == null || data < head.data) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data < data) current = current.next;

        newNode.next = current.next;
        current.next = newNode;
    }

    // delete first node
    public void deleteFirst() {
        if (head != null) head = head.next;
    }

    // delete last node
    public void deleteLast() {
        Node current = head;
        while (current.next.next != null) current = current.next;
        current.next = null;
    }

    // delete at position
    public void deleteAtPosition(int position) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        // Deleting head (position 0)
        if (position == 0) {
            head = head.next;
            return;
        }

        Node current = head;

        // Traverse to the node before the one to delete
        for (int i = 0; i < position - 1; i++) {
            if (current == null || current.next == null) {
                System.out.println("Position out of bounds.");
                return;
            }
            current = current.next;
        }

        // If position is invalid (e.g., greater than list size)
        if (current.next == null) {
            System.out.println("Position out of bounds.");
            return;
        }

        // Delete the node
        current.next = current.next.next;
    }

    public void printList() {
        Node current = head;
        System.out.println("\n--------------------------------------");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.print("null\n");
        System.out.print("--------------------------------------\n");
    }
}

public class Main_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList list = new LinkedList();

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Insertion");
            System.out.println("2. Deletion");
            System.out.println("3. Display List");
            System.out.print("Choose an option: ");
            int mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1:
                    System.out.println("\n-- Insertion Menu --");
                    System.out.println("1. Insert at Beginning");
                    System.out.println("2. Insert at End");
                    System.out.println("3. Insert at Position");
                    System.out.println("4. Insert then Sort");
                    System.out.print("Choose insertion type: ");
                    int insChoice = sc.nextInt();

                    int data, pos;
                    switch (insChoice) {
                        case 1:
                            System.out.print("Enter data to insert at beginning: ");
                            data = sc.nextInt();
                            list.addFirst(data);
                            list.printList();
                            break;
                        case 2:
                            System.out.print("Enter data to insert at end: ");
                            data = sc.nextInt();
                            list.addLast(data);
                            list.printList();
                            break;
                        case 3:
                            System.out.print("Enter position (0-based index): ");
                            pos = sc.nextInt();
                            System.out.print("Enter data to insert: ");
                            data = sc.nextInt();
                            list.insertAtPosition(pos, data);
                            list.printList();
                            break;
                        case 4:
                            System.out.print("Enter data to insert and sort: ");
                            data = sc.nextInt();
                            list.insertSorted(data);
                            list.printList();
                            break;
                        default:
                            System.out.println("Invalid insertion choice.");
                    }
                    break;

                case 2:
                    System.out.println("\n-- Deletion Menu --");
                    System.out.println("1. Delete First Node");
                    System.out.println("2. Delete Last Node");
                    System.out.println("3. Delete at Position");
                    System.out.print("Choose deletion type: ");
                    int delChoice = sc.nextInt();

                    int item;
                    switch (delChoice) {
                        case 1:
                            list.deleteFirst();
                            list.printList();
                            break;
                        case 2:
                            list.deleteLast();
                            list.printList();
                            break;
                        case 3:
                            System.out.print("Enter item to delete: ");
                            pos = sc.nextInt();
                            list.deleteAtPosition(pos);
                            list.printList();
                            break;
                        default:
                            System.out.println("Invalid deletion choice.");
                    }
                    break;

                case 3:
                    System.out.println("\nCurrent Linked List:");
                    list.printList();
                    break;

                default:
                    System.out.println("Invalid main menu choice.");
            }
        }
    }
}