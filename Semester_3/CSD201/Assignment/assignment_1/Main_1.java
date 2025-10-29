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

    // insert at the end
    public void addLast(int data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
            return;
        }

        // duyet node
        Node curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = newNode;
    }

    // insert at the given positition (0-based index)
    public void insertAtPosition(int pos, int data) {
        // neu insert vao dau
        if (pos == 0) {
            addFirst(data); 
            return;
        }

        Node newNode = new Node(data);
        Node curr = head;

        // di den truoc vi tri can chen
        for (int i = 0; i < pos - 1 && curr != null; i++) {
            curr = curr.next;
        }

        if (curr == null) {
            System.out.println("Position out of bounds.");   
            return;
        }

        // insert new Node at this pos
        newNode.next = curr.next;
        curr.next = newNode;
    }

    public void sort() {
        if (head == null || head.next == null) return;

        Node i, j;

        for (i = head; i.next != null; i = i.next) {
            for (j = i.next; j != null; j = j.next) {
                if (i.data > j.data) {
                    // Swap values
                    int temp = i.data;
                    i.data = j.data;
                    j.data = temp;
                }
            }
        }
    }

    // insert in a sorted list
    public void insertSorted(int data) {
        sort(); // sort list first
        
        Node newNode = new Node(data);

        if (head == null || data < head.data) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node curr = head;
        while (curr.next != null && curr.next.data < data) {
            curr = curr.next;
        }

        newNode.next = curr.next;
        curr.next = newNode;
    }

    // delete first node
    public void deleteFirst() {
        if (head == null) return;
        head = head.next;
    }

    // delete last node
    public void deleteLast() {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        // if there is only 1 node
        if (head.next == null) {
            head = null;
            return;
        }

        Node curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }
        curr.next = null;
    }

    // delete at position
    public void deleteAtPosition(int pos) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }

        // deleting head (position 0)
        if (pos == 0) {
            head = head.next;
            return;
        }

        Node curr = head;

        // traverse to the node before the one to delete
        for (int i = 0; i < pos - 1; i++) {
            if (curr == null || curr.next == null) {
                System.out.println("Position out of bounds.");
                return;
            }
            curr = curr.next;
        }

        // if position is invalid
        if (curr.next == null) {
            System.out.println("Position out of bounds.");
            return;
        }

        // delete the node
        curr.next = curr.next.next;
    }

    public void printList() {
        if (head == null) {
            System.out.println("\nList is empty!");
            return;
        }
        
        Node curr = head;
        System.out.println("\n--------------------------------------");
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
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
            System.out.print("Your choice: ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n-- Insertion --");
                    System.out.println("1. at the beginning");
                    System.out.println("2. at the end");
                    System.out.println("3. at the given location");
                    System.out.println("4. at the sorted list");
                    System.out.print("Your choice: ");
                    
                    int addChoice = sc.nextInt();

                    int data, pos;
                    switch (addChoice) {
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
                    System.out.println("\n-- Deletion --");
                    System.out.println("1. of first node");
                    System.out.println("2. of last Node");
                    System.out.println("3. of given item");
                    System.out.print("Your choice: ");

                    int delChoice = sc.nextInt();

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