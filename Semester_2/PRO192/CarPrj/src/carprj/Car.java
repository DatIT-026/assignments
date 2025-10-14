package carprj;

public class Car implements Comparable<Car> {
    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;
    
    // Default constructor
    public Car() {
        this.carID = "";
        this.brand = new Brand();
        this.color = "";
        this.frameID = "";
        this.engineID = "";
    }
    
    // Parameterized constructor
    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }
    
    // Getter methods
    public String getCarID() {
        return carID;
    }
    
    public Brand getBrand() {
        return brand;
    }
    
    public String getColor() {
        return color;
    }
    
    public String getFrameID() {
        return frameID;
    }
    
    public String getEngineID() {
        return engineID;
    }
    
    // Setter methods
    public void setCarID(String carID) {
        this.carID = carID;
    }
    
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }
    
    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }
    
    // Used in the operation ops listing cars in ascending order of brand names
    @Override
    public int compareTo(Car c) {
        int d = this.brand.getBrandName().compareTo(c.brand.getBrandName());
        if (d != 0) return d;
        // If they are in the same brand, compare based on their ID
        return this.carID.compareTo(c.carID);
    }
    
    // Return a string in the template: < carID, brand, color, frameID, engineID>
    @Override
    public String toString() {
        return carID + ", " + brand.getBrandName() + ", " + color + ", " + frameID + ", " + engineID;
    }
}