package model;

import mylib.Inputter;

public class Car {
    private String carID;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    // constructor
    public Car() {
        this.carID = "";
        this.brand = null;
        this.color = "";
        this.frameID = "";
        this.engineID = "";
    }

    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }

    @Override
    public String toString() {
        return String.format("| %-6s %s %-7s | %-8s | %-9s |",
                carID, brand.toString(), Inputter.capitalizeWords(color), frameID.toUpperCase(), engineID.toUpperCase());
    }
}