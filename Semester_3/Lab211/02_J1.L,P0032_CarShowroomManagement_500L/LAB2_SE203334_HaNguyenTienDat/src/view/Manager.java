package view;

import services.BrandList;
import services.CarList;
import mylib.Inputter;

public class Manager {
    public static void main(String[] args) {
        // khoi tao danh sach Brand
        BrandList brandList = new BrandList();
        brandList.loadFromFile("brands.txt");
        // khoi tao danh sach Car
        CarList carList = new CarList(brandList);
        carList.loadFromFile("cars.txt");
        
        String choice = "";
        boolean saved = true; // Mac dinh la da save (vi vua load tu file)
        
        System.out.println("\n============ Michael BMW CAR SHOWROOM MANAGEMENT SYSTEM ===========");
        
        do {
            try {
                System.out.println("\n------------------------- MAIN MENU ------------------------------");
                System.out.println("1. List all brands");
                System.out.println("2. Add a new brand");
                System.out.println("3. Search for a brand by ID");
                System.out.println("4. Update a brand by ID");
                System.out.println("5. List all brands with prices less than or equal to an input value");
                System.out.println("6. List all cars in ascending order of brand names");
                System.out.println("7. Search cars by partial brand name match");
                System.out.println("8. Add a new car");
                System.out.println("9. Remove a car by ID");
                System.out.println("10. Update a car by ID");
                System.out.println("11. List all cars by a specific color");
                System.out.println("12. Save data to files");
                System.out.println("13. Quit program");
                System.out.println("-------------------------------------------------------------------");
                
                // Kiem tra choice co hop le khong (rong hoac khong dung format)
                while (true) {
                    choice = Inputter.readString("Enter choice: ").trim();
                    
                    if (choice.matches("^([1-9]|1[0-3])$")) break;
                    
                    System.err.println("Invalid Input! Please enter a number from 1 to 13.");
                }
                
                switch (choice) {
                    case "1":
                        brandList.listBrands();
                        break;
                    case "2":
                        brandList.addBrand();
                        saved = false; // Danh dau chua save
                        break;
                    case "3":
                        brandList.searchBrand();
                        break;
                    case "4":
                        brandList.updateBrand();
                        saved = false;
                        break;
                    case "5":
                        brandList.listBrandsLessThanPrice();
                        break;
                    case "6":
                        carList.listCars();
                        break;
                    case "7":
                        carList.printBasedBrandName();
                        break;
                    case "8":
                        carList.addCar();
                        saved = false;
                        break;
                    case "9":
                        carList.removeCar();
                        saved = false;
                        break;
                    case "10":
                        carList.updateCar();
                        saved = false;
                        break;
                    case "11":
                        carList.listCarsWithColor();
                        break;
                    case "12":
                        brandList.saveToFile("brands.txt");
                        carList.saveToFile("cars.txt");
                        saved = true;
                        System.out.println("-------------------------------------------------------------------");
                        System.out.println("\nAll data saved successfully! ✓");
                        break;
                    case "13":
                        if (saved) {
                            System.out.println("Exiting program...");
                            System.exit(0);
                        } else {
                            boolean confirm = Inputter.confirmation("\nDo you want to save before exit? (Y/N): ");
                            if (confirm) {
                                brandList.saveToFile("brands.txt");
                                carList.saveToFile("cars.txt");
                                System.out.println("-------------------------------------------------------------------");
                                System.out.print("All data saved successfully! ");
                                System.out.println("Exiting program...");
                                System.exit(0);
                            } else {
                                System.out.println("Exiting without saving...");
                                System.exit(0);
                            }
                        }
                        break;
                }
            } catch (Exception e) {
                System.err.println("\nAn error occurred: " + e.getMessage());
                choice = ""; // Reset choice
            }
        } while (!choice.equals("13"));
    }
}