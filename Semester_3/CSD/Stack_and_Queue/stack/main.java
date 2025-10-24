import java.util.LinkedList;
import java.util.Scanner;

class MyStack {
    private LinkedList<String> stack = new LinkedList<>();

    public void push(String value) { stack.addLast(value); }; // them vao cuoi

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        return stack.removeLast(); // lay va xoa phan tu cuoi
    }

    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }

        return stack.getLast(); // xem phan tu cuoi
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyStack stack = new MyStack();

        System.out.print("Enter your expression: ");
        String input = sc.nextLine();

        for (String i : input.split(" ")) {
            if (i.matches("-?\\d+")) { // neu la so => push vao stack
                stack.push(i);
                System.out.println("Push " + i);
            } else { // neu la toan tu => lay 2 so tren dinh de thuc hien tinh toan
                String b = stack.pop();
                String a = stack.pop();

                int val = 0;
                switch (i) {
                    case "+": val = Integer.parseInt(a) + Integer.parseInt(b); break;
                    case "-": val = Integer.parseInt(a) - Integer.parseInt(b); break;
                    case "*": val = Integer.parseInt(a) * Integer.parseInt(b); break;
                    case "/": val = Integer.parseInt(a) / Integer.parseInt(b); break;
                }

                // dua new value tro lai sau khi tinh toan xong
                // vi du: sau khi tinh xong 2 so tren dinh la 8 -> stack se la [5, 8]
                stack.push(String.valueOf(val));
            }
        }
        System.out.println("Result = " + stack.pop()); // pop de lay value trong stack ra va in
    }
}