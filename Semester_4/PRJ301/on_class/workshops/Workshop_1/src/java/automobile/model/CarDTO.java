/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automobile.model;

/**
 *
 * @author datto
 */
public class CarDTO {
    private int CarID;
    private String CarName;
    private String Manufacturer;
    private float Price;
    private int ReleasedYear;

    public CarDTO() {
    }

    public CarDTO(int CarID, String CarName, String Manufacturer, float Price, int ReleasedYear) {
        this.CarID = CarID;
        this.CarName = CarName;
        this.Manufacturer = Manufacturer;
        this.Price = Price;
        this.ReleasedYear = ReleasedYear;
    }

    /**
     * @return the CarID
     */
    public int getCarID() {
        return CarID;
    }

    /**
     * @param CarID the CarID to set
     */
    public void setCarID(int CarID) {
        this.CarID = CarID;
    }

    /**
     * @return the CarName
     */
    public String getCarName() {
        return CarName;
    }

    /**
     * @param CarName the CarName to set
     */
    public void setCarName(String CarName) {
        this.CarName = CarName;
    }

    /**
     * @return the Manufacturer
     */
    public String getManufacturer() {
        return Manufacturer;
    }

    /**
     * @param Manufacturer the Manufacturer to set
     */
    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    /**
     * @return the Price
     */
    public float getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(float Price) {
        this.Price = Price;
    }

    /**
     * @return the ReleasedYear
     */
    public int getReleasedYear() {
        return ReleasedYear;
    }

    /**
     * @param ReleasedYear the ReleasedYear to set
     */
    public void setReleasedYear(int ReleasedYear) {
        this.ReleasedYear = ReleasedYear;
    }
    
}
