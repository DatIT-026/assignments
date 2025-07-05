package carprj;

public class Brand {
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;
    
    // Default constructor
    public Brand() {
        this.brandID = "";
        this.brandName = "";
        this.soundBrand = "";
        this.price = 0.0;
    }
    
    // Parameterized constructor
    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }
    
    // Getter methods
    public String getBrandID() {
        return brandID;
    }
    
    public String getBrandName() {
        return brandName;
    }
    
    public String getSoundBrand() {
        return soundBrand;
    }
    
    public double getPrice() {
        return price;
    }
    
    // Setter methods
    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }
    
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    // Return a string in the template: < brandID, brandName, soundBrand: price>
    @Override
    public String toString() {
        return brandID + ", " + brandName + ", " + soundBrand + ": " + String.format("%.3f", price);
    }
}