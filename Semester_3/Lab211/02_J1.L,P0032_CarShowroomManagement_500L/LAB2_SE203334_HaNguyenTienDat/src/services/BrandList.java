package services;

import java.io.*;
import java.util.ArrayList;
import model.Brand;
import mylib.Inputter;

// class BrandList ke thua tu ArrayList<Brand> de quan ly danh sach cac Brand
// viec ke thua giup BrandList tu dong co san tat ca methods cua ArrayList
// nhu add(), remove(), size(), get()... ma khong can phai viet lai
public class BrandList extends ArrayList<Brand> {
    // constructor rong: goi construtor cua ArrayList
    public BrandList() { 
        super();
    }

    // tim vi tri brand trong danh sach theo brandID (không phan biet chu hoa/thuong)
    public int searchID(String bID) {
        for (int i = 0; i < this.size(); i++) {
            if(this.get(i).getBrandID().equalsIgnoreCase(bID))  return i;
        }
        return -1;
    }
    
    // Kiem tra xem brandID co duy nhat (chua ton tai) trong danh sach khong
    // Tra ve true neu ID chua ton tai (unique), false neu da ton tai (duplicate)   
    public boolean isUnique(String id) {
        for (Brand brand : this) { // duyet tung brand
            if (brand.getBrandID().equalsIgnoreCase(id)) return false; // neu trung -> không la duy nhat
        }
        return true; // neu không trung -> la duy nhat
    }

    // hien thi menu de user chon 1 brand tu danh sach
    // tra ve Brand object ma user chon, hoac null neu khong co brand nao
    public Brand getUserChoice() {
        if (this.isEmpty()) {
            System.out.println("No brands available.");
            return null; // Tra ve null neu khong co brand nao
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
    
    // hien thi danh sach brands
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
        // nhap Brand ID voi validation (phai unique va match pattern)
        // lambda expression (brandId -> searchID(brandId) >= 0) la Predicate
        // tra ve true neu ID da ton tai (reject), false neu chua ton tai (accept)
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
        
        // kiem tra ket qua tim kiem
        if (pos < 0) System.out.println("\nThis brand does not exist!"); // Khong tim thay brand
            else {
                // Tim thay brand
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
            this.get(pos).setBrandName(name); // Chi update neu user nhap gia tri moi
        }

        // Update Sound Brand
        String sound = Inputter.getStringUpdate("Enter new Sound Brand: ", iConstants.StringButOnlyAlphabetAllowed);
        if (!sound.isEmpty()) {
            this.get(pos).setSoundBrand(Inputter.capitalizeWords(sound)); // viet hoa chu dau moi tu truoc khi set
        }

        // Update Price
        String priceStr = Inputter.getStringUpdate("Enter new Price (e.g: 3.0): ", iConstants.thePrice);
        if (!priceStr.isEmpty()) {
            this.get(pos).setPrice(Double.parseDouble(priceStr)); // Parse String thanh double truoc khi set
        }

        System.out.println("\nBrand updated successfully!");
    }
    
    // Function 5: List all brands with prices less than or equal to an input value
    public void listBrandsLessThanPrice() {
        double price = Inputter.readPositiveDouble("Enter Price (e.g: 3.0): ", "Price must be positive!");

        ArrayList<Brand> result = new ArrayList<>(); // tao ArrayList moi de chua ket qua loc

        // duyet tung brand trong danh sach
        for (Brand b : this) {
            if (b.getPrice() <= price) result.add(b); // neu gia brand <= gia user nhap => them vao result
        }
        
        System.out.println("List of brands less than price:");
        displayBrands(result);
    }
    
    // ham nay de mo file brand.txt doc,
    // doc du lieu tung dong,
    // va luu ket qua vao arrayList list
        public void loadFromFile(String filename) {
        try {
            File f = new File(filename); // tao doi tuong File
            if (!f.exists()) {
                System.err.println("File " + filename + " does not exist! Try creating a new empty file.");
                f.createNewFile();
                return;
            }
            
            // tao BufferedReader de doc file
            BufferedReader r = new BufferedReader(new FileReader(f));
            String line;
            
            // doc tung dong trong file cho den khi doc het (khi readLine() tra ve null)
            while ((line = r.readLine()) != null) {
                // tach dong thanh cac phan (split theo dau phay)
                // vi du: "B7-2018, BMW 530i (2019), Harman Kardon:3.0B"
                //     => ["B7-2018", " BMW 530i (2019)", " Harman Kardon:3.0B"]
                String[] parts = line.split(",");
                
                // kiem tra dong co du 3 phan khong
                if (parts.length == 3) {
                    String brandID = parts[0].trim(); // lay Brand ID va loai bo khoang trang thua
                    String brandName = parts[1].trim(); // lay Brand Name va loai bo khoang trang thua
                    String[] soundPrice = parts[2].trim().split(":"); // split ":" => dam bao soundBrand tach rieng voi price
                    String soundBrand = soundPrice[0].trim();
                    // roi moi split "B" trong phan price => de dam bao chi cat 1 chu B o cuoi thay vi cat het B o ca 2
                    double price = Double.parseDouble(soundPrice[1].trim().split("B")[0]);
                    
                    if (brandName.isEmpty() || soundBrand.isEmpty() || !isUnique(brandID)) continue;
                    
                    Brand brand = new Brand(brandID, brandName, soundBrand, price);
                    this.add(brand);
                }
            }
            r.close(); // dong BufferedReader sau khi doc xong
        } catch (Exception e) {
            System.out.println("Error loading brands: " + e.getMessage());
        }
    }
    
    public void saveToFile(String filename) {
        try {
            // tao PrintWriter de ghi file
            PrintWriter w = new PrintWriter(new FileWriter(filename));
            
            // duyet tung brand trong danh sach
            for (Brand brand : this) {
                w.println(brand.getBrandID() + ", " + brand.getBrandName() + ", " + 
                          brand.getSoundBrand() + ":" + brand.getPrice() + "B");
            }
            
            w.close(); // dong PrintWriter
            System.out.println("Brands saved to file successfully!");
        } catch (Exception e) {
            System.out.println("Error saving brands: " + e.getMessage());
        }
    }
}