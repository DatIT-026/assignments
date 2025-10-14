package model;

public class Brand {
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;

    // constructor
    public Brand() {
        this.brandID = "";
        this.brandName = "";
        this.soundBrand = "";
        this.price = 0.0;
    }

    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-30s | %-16s | %-8s |",
                getBrandID(), getBrandName(),
                getSoundBrand(), getPrice() + "B");
    }
}