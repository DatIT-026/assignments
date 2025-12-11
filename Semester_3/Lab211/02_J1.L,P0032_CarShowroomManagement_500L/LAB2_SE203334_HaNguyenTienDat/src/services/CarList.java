package services;

import java.io.*;
import java.util.ArrayList;
import model.Brand;
import model.Car;
import mylib.Inputter;

// class CarList ke thua tu ArrayList<Car> de quan ly danh sach cac Car
// viec ke thua giup CarList tu dong co san tat ca methods cua ArrayList
// nhu add(), remove(), size(), get()... ma khong can phai viet lai
public class CarList extends ArrayList<Car> {
    // Reference den BrandList de co the truy xuat thong tin brand khi can
    private BrandList brandList;
    
    // constructor
    // vi moi Car phai co Brand, nen can BrandList de validate va lay thong tin brand
    public CarList(BrandList bList) {
        super();
        this.brandList = bList; // luu reference den BrandList
    }
    
    // tim vi tri car trong danh sach theo carID (không phan biet chu hoa/thuong)
    public int searchID(String carID) {
        // Duyet qua tung car trong danh sach
        for (int i = 0; i < this.size(); i++) {
            // so sanh Car ID cua car hien tai voi ID dang tim
            if (this.get(i).getCarID().equalsIgnoreCase(carID)) return i;
        }
        return -1; // neu không tim thay => tra ve -1
    }
    
    // tim car theo frameID
    public int searchFrame(String fID) {
        for (int i = 0; i < this.size(); i++) {
            // So sanh Frame ID
            if (this.get(i).getFrameID().equalsIgnoreCase(fID)) return i;
        }
        return -1;
    }
    
    // tim car theo engine
    public int searchEngine(String eID) {
        for (int i = 0; i < this.size(); i++) {
            // So sanh Engine ID
            if (this.get(i).getEngineID().equalsIgnoreCase(eID)) return i;
        }
        return -1;
    }
    
    // ktra xem id co duy nhat (chua ton tai) trong danh sach
    public boolean isUnique(String id, String frameID, String engineID) {
        for (Car car: this) { // duyet tung car
            // chi can 1 trong 3 IDs trung thi ko khong unique
            if (car.getCarID().equalsIgnoreCase(id) || 
                car.getFrameID().equalsIgnoreCase(frameID) || 
                car.getEngineID().equalsIgnoreCase(engineID)) {
                return false;
            }
        }
        return true; // neu không trung -> la duy nhat
    }
    
    // Hien thi danh sach cars
    // Nhan vao mot ArrayList<Car> bat ky (co the la danh sach goc hoac danh sach da filter)
    public void displayCars(ArrayList<Car> cars) {
        if (cars.isEmpty()) {
            System.out.println("\nNo car available.");
            return; // thoat khoi method neu khong co car nao
        }
        
        System.out.println("\n|==================================================== CARS LIST ====================================================|");
        System.out.println(String.format("| %-6s | %-8s | %-30s | %-16s | %-8s | %-7s | %-8s | %-9s |",
                "CAR ID", "BRAND ID", "BRAND NAME", "SOUND BRAND", "PRICE", "COLOR", "FRAME ID", "ENGINE ID"));
        System.out.println("|===================================================================================================================|");
        
        // Duyet va in tung car
        for (Car car : cars) {
            System.out.println(car.toString());
            System.out.println("|-------------------------------------------------------------------------------------------------------------------|");
        }
        System.out.println("\nTOTAL: " + cars.size() + " car(s).");
    }
    
    public void displayCarInfo(Car car) {
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("Car ID: %s\nBrand ID: %s\nColor: %s\nFrame ID: %s\nEngine ID: %s\n", 
            car.getCarID(),
            car.getBrand().getBrandID(),
            car.getColor(),
            car.getFrameID().toUpperCase(),
            car.getEngineID().toUpperCase()
        );
        System.out.println("--------------------------------------------------------------------");
    }
    
    // Function 6: List all cars in ascending order of brand names
    // neu trung ten brand thi sap xep gia giam dan
    public void listCars() {
        // tao bang sao cua danh sach car -> tranh anh huong danh sach goc
        ArrayList<Car> cars = new ArrayList<>(this);
        
        // sap xep voi lambda expression
        cars.sort((c1, c2) -> {
            // so sanh ten brand khong phan biet chu hoa, thuong
            // cau truc kieu: c1.compareTo(c2) đe sort ASCENDING (A→Z)
            int brandCompare = c1.getBrand().getBrandName().compareToIgnoreCase(c2.getBrand().getBrandName());
            
            // neu brand khac nhau -> return ket qua so sanh brand
            if (brandCompare != 0) return brandCompare;
            
            // neu brand trung nhau, sap xep theo price giam dan
            return Double.compare(c2.getBrand().getPrice(), c1.getBrand().getPrice());
        });

        displayCars(cars);
    }
    
    // Function 7: Search cars by partial brand name match
    public void printBasedBrandName() {
        String partOfBrandName = Inputter.getString("Enter part of brand name: ", iConstants.CasualString);
        ArrayList<Car> cars = new ArrayList<>(); // Tao ArrayList de chua ket qua tim kiem
        
        // duyet tung car trong danh sach
        for (Car c : this) {
            // Lay ten brand cua xe hien tai va chuyen thanh chu thuong
            // Vd: "BMW 530i (2019)" → "bmw 530i (2019)"
            String brandName = c.getBrand().getBrandName().toLowerCase();
            
            // Kiem tra xem ten brand co chua phan text ma user nhap khong
            // Chuyen ca input cua user thanh chu thuong de khong phan biet hoa/thuong
            if (brandName.contains(partOfBrandName.toLowerCase())) cars.add(c);
        }
        
        displayCars(cars);
    }
    
    // Function 8: Add a new car
    public void addCar() {
        // nhap Car ID (phai unique, chi chua chu cai va so)
        // lambda expression: id -> searchID(id) >= 0
        // tra ve true neu ID da ton tai (reject), false neu chua ton tai (accept)
        String carID = Inputter.readUniqueStringWithPattern(
            "Enter Car ID: ",
            iConstants.StringButAlphabetAndNumberAllowed,
            "Invalid Input!",
            "Car ID already exists! Please enter a different ID.",
            id -> searchID(id) >= 0
        );
        
        // chon brand tu danh sach co san
        // getUserChoice() hien thi menu va tra ve Brand ma user chon
        Brand brand = brandList.getUserChoice();
        
        // kiem tra xem co brand nao trong danh sach khong
        if (brand == null) {
            System.err.println("No brand available. Car addition cancelled.");
            return; // huy thao tac them car neu khong co brand
        }
        
        String color = Inputter.getString("Enter color: ", iConstants.StringButOnlyAlphabetAllowed);
        
        // Nhap Frame ID (format: F00000, phai unique)
        // dung lambda kiem tra ca format va uniqueness
        // !fId.matches(...) => sai format => tra ve true (reject)
        // searchFrame(fId) >= 0 => da ton tai => tra ve true (reject)
        // Chi accept khi ca hai dieu kien deu false (dung format va chua ton tai)
        String frameID = Inputter.readUniqueStringWithPattern(
            "Enter Frame ID (F00000 format): ",
            iConstants.StringOnlyForFrameFormat,
            "Invalid Input!",
            "Frame ID must be in F00000 format and unique!",
            fId -> !fId.matches(iConstants.StringOnlyForFrameFormat) || searchFrame(fId) >= 0
        );
        
        // nhap Engine ID (format: E00000, phai unique)
        // logic tuong tu Frame ID
        String engineID = Inputter.readUniqueStringWithPattern(
            "Enter Engine ID (E00000 format): ",
            iConstants.StringOnlyForEngineFormat,
            "Invalid Input!",
            "Engine ID must be in E00000 format and unique!",
            eId -> !eId.matches(iConstants.StringOnlyForEngineFormat) || searchEngine(eId) >= 0
        );
        
        // tao Object Car moi
        Car newCar = new Car(carID.toUpperCase(), brand, color.toLowerCase(), frameID, engineID);
        this.add(newCar); // them car moi vao danh sach
        
        System.out.println("\nCar added successfully!");
    }
    
    // Function 9: remove a car
    public void removeCar() {
        String removeID = Inputter.readString("Enter car id to remove: ");
        int pos = searchID(removeID); // tim vi tri car trong danh sach
        
        // kiem tra car co ton tai khong
        if (pos < 0) {
            System.out.println("\nThis car does not exist!");
            return;
        }
            
        Car removedCar = this.get(pos); // Lay thong tin car can xoa
        
        // hien thi thong tin car de user xac nhan
        System.out.println("\nCar Information:");
        displayCarInfo(removedCar);
        
        boolean confirm = Inputter.confirmation("\nAre you sure you want to remove this car? (Y/N): ");

        if (confirm) {
            this.remove(pos); // User xac nhan => thuc hien xoa
            System.out.println("\nCar '" + removedCar.getCarID() + "' has been removed successfully!");
        } else System.out.println("\nCar removal cancelled.");
    }
    
    // Function 10: Update a car by ID
    public void updateCar() {
        String carID = Inputter.readString("Enter Car ID to update: ");
        int pos = searchID(carID); // Tim vi tri car trong danh sach
        
        if (pos < 0) {
            System.err.println("Car ID '" + carID + "' does not exist!\n");
            return;
        }
        
        Car currentCar = this.get(pos); // Lay reference den car can update
        
        // hien thi thong tin hien tai de user biet can update gi
        System.out.println("\nCurrent Car Information:");
        displayCarInfo(currentCar);
        
        System.out.println("Note: Press Enter to keep current value.\n");
        
        // vi brand la thuoc tinh quan trong, nen can confirm truoc
        boolean updateBrand = Inputter.confirmation("Do you want to update the brand? (Y/N): ");
        if (!updateBrand) {
            System.out.println("\nCar update cancelled.");
            return;
        }
        
        // 1. Update brand
        Brand newBrand = brandList.getUserChoice();
        if (newBrand != null) currentCar.setBrand(newBrand); // Set brand moi neu user chon

        // 2. Update Color
        String newColor = Inputter.getStringUpdate("Enter new Color: ", iConstants.StringButOnlyAlphabetAllowed);
        if (!newColor.isEmpty()) currentCar.setColor(Inputter.capitalizeWords(newColor));

        // 3. Update Frame ID (phai unique)
        // framePos >= 0: Frame ID da ton tai
        // framePos != pos: Frame ID thuoc ve car khac (khong phai chinh no)
        // Neu ca 2 dieu kien deu true => Frame ID da duoc dung boi car khac => khong cho update
        String newFrameID = Inputter.getStringUpdate("Enter new Frame ID (F00000 format): ", iConstants.StringOnlyForFrameFormat);
        if (!newFrameID.isEmpty()) {
            int framePos = searchFrame(newFrameID);
            if (framePos >= 0 && framePos != pos) System.err.println("Frame ID already exists in another car!");
                else currentCar.setFrameID(newFrameID.toUpperCase());
        }

        // 4. Update Engine ID (phai unique)
        // tuong tu Frame ID
        String newEngineID = Inputter.getStringUpdate("Enter new Engine ID (E00000 format): ", iConstants.StringOnlyForEngineFormat);
        if (!newEngineID.isEmpty()) {
            int enginePos = searchEngine(newEngineID);
            if (enginePos >= 0 && enginePos != pos) System.err.println("Engine ID already exists in another car!");
                else currentCar.setEngineID(newEngineID.toUpperCase());
        }
        
        System.out.println("Car '" + carID + "' has been updated successfully!");
    }
    
    // Function 11: List all cars by a specific color
    public void listCarsWithColor() {
        String color = Inputter.getString("Enter color: ", iConstants.StringButOnlyAlphabetAllowed);
        ArrayList<Car> cars = new ArrayList<>(); // Tao ArrayList de chua ket qua
        
        for (Car c : this) {
            if (c.getColor().equalsIgnoreCase(color)) cars.add(c);
        }

        if (cars.isEmpty()) System.out.print("\nNo car found with color: " + color + "\n");
            else displayCars(cars);
    }
    
    // ham nay de mo file cars.txt doc,
    // doc du lieu tung dong,
    // va luu ket qua vao arrayList list
    public void loadFromFile(String filename) {
        try {
            File f = new File(filename); // Tao doi tuong File
            
            // kiem tra file co ton tai khong
            if (!f.exists()) {
                System.err.println("File " + filename + " does not exist! Try creating a new empty file.\n");
                f.createNewFile();
                return; // thoat khoi method
            }
            
            // tao BufferedReader de doc file
            BufferedReader r = new BufferedReader(new FileReader(f));
            String line;
            
            // doc tung dong trong file cho den khi doc het (khi readLine() tra ve null)
            while ((line = r.readLine()) != null) {
                String[] parts = line.split(","); // Tach dong thanh cac phan (split theo dau phay)
                
                // kiem tra dong co du 5 phan khong (Car ID, Brand ID, Color, Frame ID, Engine ID)
                if (parts.length >= 5) {
                    // parse du lieu tu moi phan (loai bo khoang trang thua)
                    String carID = parts[0].trim();
                    String brandID = parts[1].trim();
                    String color = parts[2].trim();
                    String frameID = parts[3].trim();
                    String engineID = parts[4].trim();
                    if (color.isEmpty() || !frameID.matches(iConstants.StringOnlyForFrameFormat) || 
                            !engineID.matches(iConstants.StringOnlyForEngineFormat) || !isUnique(carID, frameID, engineID)) {
                        continue;
                    }
                    
                    // tim brand tuong ung trong BrandList
                    int pos = brandList.searchID(brandID);
                    
                    // chi tao car neu brand ton tai
                    // neu brand khong ton tai => bo qua dong nay (khong tao car)
                    if (pos >= 0) {
                        Brand brand = brandList.get(pos); // lay Brand object tu BrandList
                        Car car = new Car(carID, brand, color, frameID, engineID);
                        this.add(car);
                    }
                }
            }
            r.close(); // dong BufferedReader sau khi doc xong
        } catch (Exception e) {
            System.out.println("Error loading cars: " + e.getMessage());
        }
    }
    
    public void saveToFile(String filename) {
        try {
            // tao PrintWriter de ghi file
            PrintWriter w = new PrintWriter(new FileWriter(filename));
            for (Car car : this) {
                w.println(car.getCarID() + ", " + car.getBrand().getBrandID() + ", " + 
                          car.getColor() + ", " + car.getFrameID().toUpperCase() + ", " + car.getEngineID().toUpperCase());
            }
            
            // dong PrintWriter (neu khong dong thi du lieu co the khong duoc ghi)
            w.close();
            System.out.println("Cars saved to file successfully!");
        } catch (Exception e) {
            System.err.println("Error saving cars: " + e.getMessage());
        }
    }
}