/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab02_1;

import java.util.Scanner;

/**
 *
 * @author datto
 */
public class Program {
    static void printMenu() {
        System.out.println("---------***---------");
        System.out.println("1. Print all items");
        System.out.println("2. Add new items");
        System.out.println("3. Update item");
        System.out.println("4. Remove item");
        System.out.println("Others: Exit");
        System.out.print("Enter choice:");
    }
    
    public static void main(String[] args) {
        try {
            String itemID, itemName;
            int quantity, choice;
            ItemDAO dao = new ItemDAO();
            Scanner sc = new Scanner(System.in);
            
            printMenu();
            choice = Integer.parseInt(sc.nextLine());
            
            while (choice >= 1 && choice <= 4) {
                if (choice == 1) dao.printItems();
                else if (choice == 2) {
                    System.out.println("Enter ItemID:");
                    itemID = sc.nextLine();
                    System.out.println("Enter ItemName:");
                    itemName = sc.nextLine();
                    System.out.println("Enter Quantity:");
                    quantity = Integer.parseInt(sc.nextLine());
                    
                    dao.AddNewItems(itemID, itemName, quantity);
                }
                else if (choice == 3) {
                    System.out.println("Enter ItemID:");
                    itemID = sc.nextLine();
                    System.out.println("Enter ItemName:");
                    itemName = sc.nextLine();
                    System.out.println("Enter Quantity:");
                    quantity = Integer.parseInt(sc.nextLine());
                    
                    dao.UpdateItem(itemID, itemName, quantity);
                }
                else if (choice == 4) {
                    System.out.println("Enter ItemID:");
                    itemID = sc.nextLine();
                    
                    dao.RemoveItem(itemID);
                }
                else System.exit(0);
                
                printMenu();
                choice = Integer.parseInt(sc.nextLine());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
