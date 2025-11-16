/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        ComputerStore t = new ComputerStore();
        int choice;
        Scanner sca = new Scanner(System.in);
        System.out.println();
        System.out.println(" 1. Test f1");
        System.out.println(" 2. Test f2");
        System.out.println(" 3. Test f3");
        System.out.println(" 4. Test f4");
        System.out.print("    Your selection (1 -> 4): ");
        
        // Input validation loop
        while (!sca.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
            System.out.print("    Your selection (1 -> 4): ");
            sca.next();
        }
        choice = sca.nextInt();
        sca.nextLine();

        switch(choice) {
            case 1: 
                t.f1();
                System.out.println("\nOUTPUT (f1.txt):");
                Lib.viewFile("f1.txt");
                break;
            case 2: 
                t.f2();
                System.out.println("\nOUTPUT (f2.txt):");
                Lib.viewFile("f2.txt");
                break;
            case 3: 
                t.f3();
                System.out.println("\nOUTPUT (f3.txt):");
                Lib.viewFile("f3.txt");
                break;
            case 4: 
                t.f4();
                System.out.println("\nOUTPUT (f4.txt):");
                Lib.viewFile("f4.txt");
                break;
            default: 
                System.out.println("Wrong selection. Please choose a number from 1 to 4.");
        }
        System.out.println();
        sca.close();
    }
    
}
