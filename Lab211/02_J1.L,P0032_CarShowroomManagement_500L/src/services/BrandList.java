package services;

import java.io.*;
import java.util.ArrayList;
import model.Brand;
import mylib.Inputter;

// lop BrandList ke thua tu ArrayList de quan ly danh sach Brand
public class BrandList extends ArrayList<Brand> {
    // constructor rong: goi construtor cua ArrayList
    public BrandList() { 
        super();
    }

    // tim vi tri brand trong danh sach theo brandID (không phan biet chu hoa/thuong)
    public int searchID(String bID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getBrandID().equalsIgnoreCase(bID)) return i;
        }
        return -1; // neu không tim thay => tra ve -1
    }

    // ktra xem id co duy nhat (chua ton tai) trong danh sach
    public boolean isUnique(String id) {
        for (Brand brand : this) { // duyet tung brand
            if (brand.getBrandID().equalsIgnoreCase(id)) return false; // neu trung -> không la duy nhat
        }
        return true; // neu không trung -> la duy nhat
    }

    // goi Menu de cho nguoi dung chon 1 brand tu danh sach
    public Brand getUserChoice() {
        if (this.isEmpty()) {
            System.out.println("No brands available.");
            return null;
        }
        
        System.out.println("\nChoose a brand:");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println(String.format("| %-3s | %-8s | %-30s | %-16s | %-8s |", "No.", "Brand ID", "Brand Name", "Sound Brand", "Price"));
        System.out.println("|-------------------------------------------------------------------------------|");
        int n = this.size();
        for (int i = 0; i < n; i++) {
            System.out.println(String.format("| %2d  %s", (i + 1), this.get(i)));
        }
        System.out.println("|-------------------------------------------------------------------------------|");
        
        int choice = Inputter.getInt("Please choose an option (1-" + n + "): ", 1, n);
        
        return this.get(choice - 1);
    }
    
    // Hien thi danh sach brands
    public void displayBrands(ArrayList<Brand> brands) {
        if (brands.isEmpty()) {
            System.out.println("\nNo brands available.");
            return;
        }
        
        System.out.println("\n|============================== BRANDS LIST ==============================|");
        System.out.println(String.format("| %-8s | %-30s | %-16s | %-8s |", "BRAND ID", "BRAND NAME", "SOUND BRAND", "PRICE"));
        System.out.println("|=========================================================================|");
        for (Brand brand : brands) {
            System.out.println(brand.toString());
            System.out.println("|-------------------------------------------------------------------------|");
        }
        System.out.println("\nTOTAL: " + brands.size() + " brand(s).");
    }
    
    // Function 1: List all brands
    public void listBrands() { displayBrands(this); }
    
    // Function 2: Add a new brand
    public void addBrand() {
        String id = Inputter.readUniqueStringWithPattern(
            "Enter Brand ID: ",
            iConstants.CasualString,
            "Invalid format!",
            "Brand ID already exists! Please enter a different ID.",
            brandId -> searchID(brandId) >= 0
        );
        
        String name = Inputter.getString("Enter brand name: ", iConstants.CasualString); // vd: Mercedes_Benz (C-Class)
        String sound = Inputter.getString("Enter Sound Brand: ", iConstants.StringButOnlyAlphabetAllowed); // vd: Land Rover
        double price = Inputter.readPositiveDouble("Enter Price (e.g: 3.0): ", "Price must be positive!");

        Brand newBrand = new Brand(id.toUpperCase(), name, Inputter.capitalizeWords(sound), price);
        this.add(newBrand);

        System.out.println("-----------------------------------------");
        System.out.println("\nBrand added successfully!");
    }
    
    // Function 3: Search for a brand by ID
    public void searchBrand() {
        String brandID = Inputter.getString("Enter Brand ID to search: ", iConstants.CasualString);
        int pos = searchID(brandID); // tim vi tri brand trong danh sach
        
        if (brandID.isEmpty() || !brandID.matches(iConstants.CasualString)) {
            System.err.println("Invalid Input!\n");
            return;
        }
        
        if (pos < 0) System.out.println("\nThis brand does not exist!");
            else {
                Brand found = this.get(pos);
                System.out.print("\nBrand found:");
                System.out.println("\n--------------------------------------------------------------------");
                System.out.printf("BrandID: %s\nBrand Name: %s\nSound Brand: %s\nPrice: %s", 
                        found.getBrandID(), found.getBrandName(), found.getSoundBrand(), found.getPrice());
                System.out.println("");
            }
    }

    // Function 4: Update a brand by ID
    public void updateBrand() {
        String brandID = Inputter.getString("Enter Brand ID to update: ", iConstants.CasualString);
        int pos = searchID(brandID); // tim vi tri brand trong danh sach
        
        if (pos < 0) {
            System.out.println("\nThis brand does not exist!");
            return;
        }
        
        System.out.println("\nNote: Press Enter to keep current value.\n");

        // Update Brand Name
        String name = Inputter.getStringUpdate("Enter new Brand Name: ", iConstants.CasualString);
        if (!name.isEmpty()) {
            this.get(pos).setBrandName(name);
        }

        // Update Sound Brand
        String sound = Inputter.getStringUpdate("Enter new Sound Brand: ", iConstants.StringButOnlyAlphabetAllowed);
        if (!sound.isEmpty()) {
            this.get(pos).setSoundBrand(Inputter.capitalizeWords(sound));
        }

        // Update Price
        String priceStr = Inputter.getStringUpdate("Enter new Price (e.g: 3.0): ", iConstants.thePrice);
        if (!priceStr.isEmpty()) {
            this.get(pos).setPrice(Double.parseDouble(priceStr));
        }

        System.out.println("\nBrand updated successfully!");
    }
    
    // Function 5: List all brands with prices less than or equal to an input value
    public void listBrandsLessThanPrice() {
        double price = Inputter.readPositiveDouble("Enter Price (e.g: 3.0): ", "Price must be positive!");

        ArrayList<Brand> result = new ArrayList<>();

        for (Brand b : this) {
            if (b.getPrice() <= price) result.add(b);
        }
        
        System.out.println("List of brands less than price:");
        displayBrands(result);
    }
    
    // Ham nay de mo file brand.txt doc,
    // doc du lieu tung dong,
    // va luu ket qua vao arrayList list
        public void loadFromFile(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                System.err.println("File " + filename + " does not exist! Try creating a new empty file.");
                f.createNewFile();
                return;
            }
            
            BufferedReader r = new BufferedReader(new FileReader(f));
            String line;
            
            // Doc tung dong trong file cho den khi doc het (khi readLine() tra ve null)
            while ((line = r.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String brandID = parts[0].trim();
                    String brandName = parts[1].trim();
                    String[] soundPrice = parts[2].trim().split(":");
                    String soundBrand = soundPrice[0].trim();
                    double price = Double.parseDouble(soundPrice[1].trim().split("B")[0]);
                    
                    if (brandName.isEmpty() || soundBrand.isEmpty() || !isUnique(brandID)) continue;
                    
                    Brand brand = new Brand(brandID, brandName, soundBrand, price);
                    this.add(brand);
                }
            }
            r.close();
        } catch (Exception e) {
            System.out.println("Error loading brands: " + e.getMessage());
        }
    }
    
    public void saveToFile(String filename) {
        try {
            PrintWriter w = new PrintWriter(new FileWriter(filename));
            for (Brand brand : this) {
                w.println(brand.getBrandID() + ", " + brand.getBrandName() + ", " + 
                          brand.getSoundBrand() + ":" + brand.getPrice() + "B");
            }
            w.close();
            System.out.println("Brands saved to file successfully!");
        } catch (Exception e) {
            System.out.println("Error saving brands: " + e.getMessage());
        }
    }
}