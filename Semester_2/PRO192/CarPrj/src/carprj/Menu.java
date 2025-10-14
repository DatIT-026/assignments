package carprj;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    
    private ArrayList<String> options = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);
   
    public Menu() {
        options.add("1. List all brands");
        options.add("2. Add a new brand");
        options.add("3. Search a brand based on its ID");
        options.add("4. Update a brand");
        options.add("5. Save brands to the file");
        options.add("6. List all cars in ascending order of brand names");
        options.add("7. List cars based on a part of input brand name");
        options.add("8. Add a car");
        options.add("9. Remove a car based on its ID");
        options.add("10. Update a car based on its ID");
        options.add("11. Save cars to file");
        options.add("0. Exit");
    }
    
    public int getChoice() {
        int n = options.size();
        
        for(int i = 0; i < n; i++) System.out.println(options.get(i));
           
        System.out.print("Your choice: ");
        
        int response;
        try {
            response = Integer.parseInt(sc.nextLine());
            while (response < 0 || response > 11) {
                System.out.print("Your choice: ");
                response = Integer.parseInt(sc.nextLine());
            }
            return response;
        } catch (NumberFormatException e) {
            System.out.print("Your choice: ");
            return getChoice();
        }
    }
    
    // Static method to get choice from brand list
    public static int getChoice(ArrayList<Brand> brandList) {
        Scanner sc = new Scanner(System.in);
        
        if (brandList.isEmpty()) {
            return -1;
        }
        
        System.out.println("== Available Brands ==");
        for (int i = 0; i < brandList.size(); i++) {
            System.out.println((i + 1) + ". " + brandList.get(i));
        }
        
        System.out.print("Choose a brand (1-" + brandList.size() + "): ");
        
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
            while (choice < 1 || choice > brandList.size()) {
                System.out.print("Choose a brand (1-" + brandList.size() + "): ");
                choice = Integer.parseInt(sc.nextLine());
            }
            return choice;
        } catch (NumberFormatException e) {
            System.out.print("Choose a brand (1-" + brandList.size() + "): ");
            return getChoice(brandList);
        }
    }
}