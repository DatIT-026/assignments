package q1;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) throws Exception {
        Assignment assignment = new Assignment();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n--- Task Manager Exam Menu ---");
        System.out.println("1. Test f1");
        System.out.println("2. Test f2");
        System.out.println("3. Test f3");
        System.out.println("4. Test f4");
        System.out.print("Enter your choice (1-4): ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                assignment.f1();
                System.out.println("\nOUTPUT:");
                Lib.viewFile("f1.txt");
                break;
            case 2:
                assignment.f2();
                System.out.println("\nOUTPUT:");
                Lib.viewFile("f2.txt");
                break;
            case 3:
                assignment.f3();
                System.out.println("\nOUTPUT:");
                Lib.viewFile("f3.txt");
                break;
            case 4:
                assignment.f4();
                System.out.println("\nOUTPUT:");
                Lib.viewFile("f4.txt");
                break;
            default:
                System.out.println("Invalid choice.");
        }
        sc.close();
    }
}