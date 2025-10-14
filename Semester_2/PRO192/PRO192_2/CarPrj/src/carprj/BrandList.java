package carprj;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BrandList {
    // BrandList must be existed in advance
    private ArrayList<Brand> brandList;
    private Scanner sc = new Scanner(System.in);
    
    public BrandList() {
        brandList = new ArrayList<>();
    }
    
    public BrandList(ArrayList<Brand> bList) {
        this.brandList = bList;
    }
    
    // Load brand data from file
    public void loadFromFile(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                System.out.println("File does not exist: " + filename);
                return;
            }
            
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            
            while ((line = br.readLine()) != null) {
                // Split the read line into parts
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String brandID = parts[0].toUpperCase().trim();
                    String brandName = parts[1].toUpperCase().trim();
                    
                    // handle the `:` instead of `,`
                    String thirdPart = parts[2].toUpperCase().trim();
                    String[] Sound_and_Price_Parts = thirdPart.split(":");
                    String soundBrand = Sound_and_Price_Parts[0].trim();
                    
                    double price = 0.0;
                
                    if (Sound_and_Price_Parts.length > 1) {
                        try {
                            String priceStr = Sound_and_Price_Parts[1].trim().replace(",", ".");
                            price = Double.parseDouble(priceStr);
                        } catch (NumberFormatException e) {
                            price = 0.0;
                        }
                }
                        
                    
                    // Create a brand from input data
                    Brand brand = new Brand(brandID, brandName, soundBrand, price);
                    // Add the brand to this list
                    brandList.add(brand);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // Save brand data to file
    public void saveToFile(String filename) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));

            // For each brand in the list
            for (Brand brand : brandList) {
                bw.write(brand.getBrandID() + ", " + brand.getBrandName() + ", " + 
                        brand.getSoundBrand() + ": " + brand.getPrice() + "\n");
            }

            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
    
    // Search a brand based on brand ID, return the existence position
    public int searchID(String bID) {
        int n = brandList.size();
        for (int i = 0; i < n; i++) {
            if (brandList.get(i).getBrandID().equals(bID)) {
                return i;
            }
        }
        return -1;
    }
    
    // Transform the list to a menu, the user will choose a brand from this menu
    public Brand getUserChoice() {
        if (brandList.isEmpty()) {
            System.out.println("No brands available.");
            return null;
        }
        
        int choice = Menu.getChoice(brandList);
        return brandList.get(choice - 1);
    }
    
    // Add a new Brand to the list
    public void addBrand() {
        System.out.println("Enter brand details:");
        
        // Receive String ID, constraint: input ID can not exist in the list
        String brandID;
        do {
            System.out.print("Brand ID: ");
            brandID = sc.nextLine();
            if (searchID(brandID) != -1) {
                System.out.println("Brand ID already exists. Please enter a different ID.");
            }
        } while (searchID(brandID) != -1);
        
        // Receive String brandName, the brand name is not blank
        String brandName;
        do {
            System.out.print("Brand Name: ");
            brandName = sc.nextLine();
            if (brandName == null || brandName.trim().isEmpty()) {
                System.out.println("Brand name cannot be blank.");
            }
        } while (brandName == null || brandName.trim().isEmpty());
        
        // Receive String soundBrand, the sound brand is not blank
        String soundBrand;
        do {
            System.out.print("Sound Brand: ");
            soundBrand = sc.nextLine();
            if (soundBrand == null || soundBrand.trim().isEmpty()) {
                System.out.println("Sound brand cannot be blank.");
            }
        } while (soundBrand == null || soundBrand.trim().isEmpty());
        
        // Receive double price, price >0
        double price;
        do {
            System.out.print("Price: ");
            try {
                price = Double.parseDouble(sc.nextLine());
                if (price <= 0) {
                    System.out.println("Price must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format.");
                price = -1;
            }
        } while (price <= 0);
        
        // Create a new brand from inputted data
        Brand newBrand = new Brand(brandID, brandName, soundBrand, price);
        // Add a new brand to the list
        brandList.add(newBrand);
        System.out.println("Brand added successfully!");
    }
    
    // Update brand name, sound brand, price of an existed brand
    public void updateBrand() {
        System.out.print("Enter Brand ID to update: ");
        String brandID = sc.nextLine();
        
        // pos = searchID(brandID)
        int pos = searchID(brandID);
        if (pos < 0) {
            System.out.println("Brand not found!");
        } else {
            Brand brand = brandList.get(pos);
            
            // Receive String brandName, the brand name is not blank
            String brandName;
            do {
                System.out.print("New Brand Name: ");
                brandName = sc.nextLine();
                if (brandName == null || brandName.trim().isEmpty()) {
                    System.out.println("Brand name cannot be blank.");
                }
            } while (brandName == null || brandName.trim().isEmpty());
            
            // Receive String soundBrand, the sound brand is not blank
            String soundBrand;
            do {
                System.out.print("New Sound Brand: ");
                soundBrand = sc.nextLine();
                if (soundBrand == null || soundBrand.trim().isEmpty()) {
                    System.out.println("Sound brand cannot be blank.");
                }
            } while (soundBrand == null || soundBrand.trim().isEmpty());
            
            // Receive double price, price >0
            double price;
            do {
                System.out.print("New Price: ");
                try {
                    price = Double.parseDouble(sc.nextLine());
                    if (price <= 0) {
                        System.out.println("Price must be greater than 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format.");
                    price = -1;
                }
            } while (price <= 0);
            
            // Update new brandName, new sound brand, new price to the pos(th) brand
            brand.setBrandName(brandName);
            brand.setSoundBrand(soundBrand);
            brand.setPrice(price);
            
            System.out.println("Brand updated successfully!");
        }
    }
    
    // List all brands
    public void listBrands() {
        int n = brandList.size();
        if (n == 0) {
            System.out.println("No brands available.");
            return;
        }
        
        System.out.println("=== Brand List ===");
        for (int i = 0; i < n; i++) {
            System.out.println(brandList.get(i));
        }
        
        System.out.println("Total: " + n + " brand(s).");
    }
    
    // Getter for the brand list
    public ArrayList<Brand> getBrandList() {
        return brandList;
    }
    
    // Get brand by index
    public Brand get(int index) {
        if (index >= 0 && index < brandList.size()) {
            return brandList.get(index);
        }
        return null;
    }
    
    // Get size of the brand list
    public int size() {
        return brandList.size();
    }
}