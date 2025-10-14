package carprj;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CarList {
    // BrandList must be existed in advance
    private BrandList brandList;
    private ArrayList<Car> carList;
    private Scanner sc = new Scanner(System.in);
    
    public CarList(BrandList bList) {
        this.brandList = bList;
        this.carList = new ArrayList<>();
    }
    
    // Load car data from file
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
                if (parts.length >= 5) {
                    String carID = parts[0].toUpperCase().trim();
                    String brandID = parts[1].toUpperCase().trim();
                    String color = parts[2].toUpperCase().trim();
                    String frameID = parts[3].toUpperCase().trim();
                    String engineID = parts[4].toUpperCase().trim();
                    
                    // Extract parts to carID, brandID, color, frameID, engineID
                    int pos = brandList.searchID(brandID);
                    if (pos >= 0) {
                        Brand b = brandList.get(pos);
                        // Create new car with data above
                        Car car = new Car(carID, b, color, frameID, engineID);
                        // Add new car to the list
                        carList.add(car);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    // Save car data to file
    public void saveToFile(String filename) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            
            // For each car in the list
            for (Car car : carList) {
                // Write the car to file + "\n"
                bw.write(car.getCarID() + ", " + car.getBrand().getBrandID() + ", " + 
                        car.getColor() + ", " + car.getFrameID() + ", " + car.getEngineID() + "\n");
            }
            
            bw.close();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
    
    // Search a car based on car ID, return the existence position
    public int searchID(String carID) {
        int n = carList.size();
        for (int i = 0; i < n; i++) {
            if (carList.get(i).getCarID().equals(carID)) {
                return i;
            }
        }
        return -1;
    }
    
    // Search a car by its frame ID. Use in checking frames are not duplicated.
    public int searchFrame(String fID) {
        int n = carList.size();
        for (int i = 0; i < n; i++) {
            if (carList.get(i).getFrameID().equals(fID)) {
                return i;
            }
        }
        return -1;
    }
    
    // Search a car by its engine ID. Use in checking engines are not duplicated.
    public int searchEngine(String eID) {
        int n = carList.size();
        for (int i = 0; i < n; i++) {
            if (carList.get(i).getEngineID().equals(eID)) {
                return i;
            }
        }
        return -1;
    }
    
    // Add a new Car to the list
    public void addCar() {
        System.out.println("== Enter car details ==");
        
        // Receive carID, carID must be not duplicated
        String carID;
        do {
            System.out.print("Car ID: ");
            carID = sc.nextLine();
            if (searchID(carID) != -1) {
                System.out.println("Car ID already exists. Please enter a different ID.");
            }
        } while (searchID(carID) != -1);
        
        // For choosing a brand
        Brand b = brandList.getUserChoice();
        if (b == null) {
            System.out.println("No brands available. Cannot add car.");
            return;
        }
        
        // Receive color, color can not be blank
        String color;
        do {
            System.out.print("Color: ");
            color = sc.nextLine();
            if (color == null || color.trim().isEmpty()) {
                System.out.println("Color cannot be blank.");
            }
        } while (color == null || color.trim().isEmpty());
        
        // Receive frameID. It must be in the "F00000" format and not be duplicated
        String frameID;
        do {
            System.out.print("Frame ID (F00000 format): ");
            frameID = sc.nextLine();
            if (!frameID.matches("F\\d{5}")) {
                System.out.println("Frame ID must be in F00000 format.");
            } else if (searchFrame(frameID) != -1) {
                System.out.println("Frame ID already exists. Please enter a different ID.");
            }
        } while (!frameID.matches("F\\d{5}") || searchFrame(frameID) != -1);
        
        // Receive engineID. It must be in the "E00000" format and not be duplicated
        String engineID;
        do {
            System.out.print("Engine ID (E00000 format): ");
            engineID = sc.nextLine();
            if (!engineID.matches("E\\d{5}")) {
                System.out.println("Engine ID must be in E00000 format.");
            } else if (searchEngine(engineID) != -1) {
                System.out.println("Engine ID already exists. Please enter a different ID.");
            }
        } while (!engineID.matches("E\\d{5}") || searchEngine(engineID) != -1);
        
        // Create a new car with inputted data
        Car newCar = new Car(carID, b, color, frameID, engineID);
        // Add a new car to the list
        carList.add(newCar);
        System.out.println("Car added successfully!");
    }
    
    // Print cars based on a part of brand name
    public void printBasedBrandName() {
        System.out.print("Enter a part of brand name: ");
        String aPartOfBrandName = sc.nextLine();
        
        int n = carList.size();
        int count = 0;
        
        System.out.println("Cars with brand name containing '" + aPartOfBrandName + "':");
        for (int i = 0; i < n; i++) {
            Car c = carList.get(i);
            if (c.getBrand().getBrandName().toLowerCase().contains(aPartOfBrandName.toLowerCase())) {
                System.out.println(c);
                count++;
            }
        }
        
        if (count == 0) {
            System.out.println("\nNo cars found with brand name containing '" + aPartOfBrandName + "'.");
        }
    }
    
    // Remove a car from the list
    public void removeCar() {
        System.out.print("Enter Car ID to remove: ");
        String carID = sc.nextLine();
        
        int pos = searchID(carID);
        if (pos >= 0) {
            carList.remove(pos);
            System.out.println("Car removed successfully!");
        } else {
            System.out.println("Car not found!");
        }
    }
    
    // Update a car
    public void updateCar() {
        System.out.print("Enter Car ID to update: ");
        String carID = sc.nextLine();
        
        int pos = searchID(carID);
        if (pos < 0) {
            System.out.println("Car not found!");
        } else {
            Car car = carList.get(pos);
            
            // For choosing a brand
            Brand b = brandList.getUserChoice();
            if (b == null) {
                System.out.println("No brands available. Cannot update car.");
                return;
            }
            
            // Receive color, color can not be blank
            String color;
            do {
                System.out.print("New Color: ");
                color = sc.nextLine();
                if (color == null || color.trim().isEmpty()) {
                    System.out.println("Color cannot be blank.");
                }
            } while (color == null || color.trim().isEmpty());
            
            // Receive frameID. It must be in the "F00000" format and not be duplicated
            String frameID;
            do {
                System.out.print("New Frame ID (F00000 format): ");
                frameID = sc.nextLine();
                if (!frameID.matches("F\\d{5}")) {
                    System.out.println("Frame ID must be in F00000 format.");
                } else if (searchFrame(frameID) != -1 && !frameID.equals(car.getFrameID())) {
                    System.out.println("Frame ID already exists. Please enter a different ID.");
                }
            } while (!frameID.matches("F\\d{5}") || (searchFrame(frameID) != -1 && !frameID.equals(car.getFrameID())));
            
            // Receive engineID. It must be in the "E00000" format and not be duplicated
            String engineID;
            do {
                System.out.print("New Engine ID (E00000 format): ");
                engineID = sc.nextLine();
                if (!engineID.matches("E\\d{5}")) {
                    System.out.println("Engine ID must be in E00000 format.");
                } else if (searchEngine(engineID) != -1 && !engineID.equals(car.getEngineID())) {
                    System.out.println("Engine ID already exists. Please enter a different ID.");
                }
            } while (!engineID.matches("E\\d{5}") || (searchEngine(engineID) != -1 && !engineID.equals(car.getEngineID())));
            
            // Update the car
            car.setBrand(b);
            car.setColor(color);
            car.setFrameID(frameID);
            car.setEngineID(engineID);
            
            System.out.println("Car updated successfully!");
        }
    }
    
    // List all cars in ascending order of brand names
    public void listCars() {
        if (carList.isEmpty()) {
            System.out.println("No cars available.");
            return;
        }
        
        // Sort cars by brand name (using compareTo method)
        Collections.sort(carList);
        
        System.out.println("== Car List ==");
        
        for (Car car : carList) {
            System.out.println(car.getCarID() + ", " + car.getBrand().getBrandID() + ", " + 
                             car.getBrand().getBrandName() + ", " + car.getBrand().getSoundBrand() + 
                             ": " + car.getBrand().getPrice() + ", " + car.getColor() + ", " + 
                             car.getFrameID() + ", " + car.getEngineID());
        }
        
        System.out.println("Total: " + carList.size() + " cars(s).");
    }
    
    // Get size of the car list
    public int size() {
        return carList.size();
    }
    
    // Get car by index
    public Car get(int index) {
        if (index >= 0 && index < carList.size()) {
            return carList.get(index);
        }
        return null;
    }
}