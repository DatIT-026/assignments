class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// stack su dung linked list
class Stack {
    private Node top; // khoi tao dinh cua stack

    // constructor
    public Stack() {
        top = null;
    }

    // co che them 1 phan tu vao stack
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        System.out.println("Pushed: " + data);
    }

    // co che lay 1 phan tu khoi stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow - Cannot pop from empty stack");
            return -1;
        }

        int poppedData = top.data;
        top = top.next;
        System.out.println("Popped: " + poppedData);
        return poppedData;
    }

    // ktra neu stack co rong
    public boolean isEmpty() {
        return top == null;
    }

    // ham in cac phan tu trong stack
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        System.out.print("Stack elements (top to bottom): ");
        Node current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

// Main class to demonstrate stack operations
public class Main_2 {
    public static void main(String[] args) {
        Stack stack = new Stack();

        // them phan tu
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // in stack
        stack.printStack();

        // xoa phan tu
        stack.pop();

        stack.printStack();

        stack.pop();
        stack.pop();

        stack.pop();

        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}