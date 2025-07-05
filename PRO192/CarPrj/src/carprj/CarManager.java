package carprj;

import java.util.Scanner;

public class CarManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        BrandList brandList = new BrandList();
        brandList.loadFromFile("Brands.txt");
        
        CarList carList = new CarList(brandList);
        carList.loadFromFile("Cars.txt");
        
        Menu menu = new Menu();
        
        int choice;
        
        do {
            choice = menu.getChoice();
            
            switch (choice) {
                case 1: // List all brands
                    brandList.listBrands();
                    System.out.println("---------------------------");
                    break;
                case 2: // Add a new brand
                    brandList.addBrand();
                    System.out.println("---------------------------");
                    break;
                case 3: // Search a brand based on its ID
                    System.out.print("Enter Brand ID to search: ");
                    String searchID = sc.nextLine();
                    int pos = brandList.searchID(searchID);
                    if (pos >= 0) {
                        System.out.println("Brand found at position " + (pos + 1) + ":");
                        System.out.println(brandList.get(pos));
                        System.out.println("---------------------------");
                    } else {
                        System.out.println("Brand not found!");
                        System.out.println("---------------------------");
                    }
                    break;
                case 4: // Update a brand
                    brandList.updateBrand();
                    System.out.println("---------------------------");
                    break;
                case 5: // Save brands to the file
                    brandList.saveToFile("brands.txt");
                    System.out.println("Brands saved successfully!");
                    System.out.println("---------------------------");
                    break;
                case 6: // List all cars in ascending order of brand names
                    carList.listCars();
                    System.out.println("---------------------------");
                    break;
                case 7: // List cars based on a part of input brand name
                    carList.printBasedBrandName();
                    System.out.println("---------------------------");
                    break;
                case 8: // Add a car
                    carList.addCar();
                    System.out.println("---------------------------");
                    break;
                case 9: // Remove a car based on its ID
                    carList.removeCar();
                    System.out.println("---------------------------");
                    break;
                case 10: // Update a car based on its ID
                    carList.updateCar();
                    System.out.println("---------------------------");
                    break;
                case 11: // Save cars to file
                    carList.saveToFile("cars.txt");
                    System.out.println("Cars saved successfully!");
                    System.out.println("---------------------------");
                    break;
                case 0: // Exit
                    brandList.saveToFile("brands.txt");
                    carList.saveToFile("cars.txt");
                    System.out.println("Data saved.");
                    System.out.println("---------------------------");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    System.out.println("---------------------------");
            }
        } while (choice != 0);
    }
}