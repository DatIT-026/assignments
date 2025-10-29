class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class MyStack {
    private Node head;

    // constructor
    public MyStack() {
        head = null;
    }

    // check xem stack co rong khong?
    public boolean isEmpty() {
        return head == null;
    }

    // push phan tu vao dinh stack
    public void push(int value) {
        Node p = new Node(value);
        p.next = head;
        head = p;
    }

    // pop phan tu ra khoi dinh stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        
        int value = head.data;
        head = head.next;
        return value;
    }

    // xem phan tu o dinh stack
    public int top() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return head.data;
    }

    // xoa tat ca phan tu trong stack
    public void clear() {
        head = null;
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.print("Stack: ");
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }
}

public class Main_2 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        stack.printStack(); // 30 20 10

        System.out.println("Pop: " + stack.pop());
        stack.printStack(); // 20 10

        System.out.println("Top: " + stack.top());

        stack.pop();
        stack.pop();
        stack.pop(); // báo rỗng

        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
