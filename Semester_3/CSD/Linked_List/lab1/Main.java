class Node {
    int data; // node data
    Node next; // pointer to next node

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// List management
class MyLinkedList {
    Node head; // first node

    // constructor
    MyLinkedList() {
        head = null;
    }

    // add new node at the first of list
    public void addFirst(int data) {
        Node newNode = new Node(data); // create a new node
        newNode.next = head; // connect new node before old node
        head = newNode; // update head
    }

    // add new node at the last of list
    public void addLast(int data) {     
        Node newNode = new Node(data);  // create new node
        if (head == null) {             // if list is empty
            head = newNode;             // new node becomes the head
            return;
        }

        Node current = head;            // start from head
        while (current.next != null) { current = current.next; } // check til the last node
        current.next = newNode;
    }

    public void printList() {
        Node current = head;            // start from head
        while (current != null) {       // check
            System.out.print(current.data + " -> ");
            current = current.next;     // next node
        }

        System.out.print("null"); // end of list
    }
}

public class Main {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        list.addFirst(10);
        list.addFirst(20);
        list.addLast(5);
        list.addLast(2);

        list.printList();
    }
}