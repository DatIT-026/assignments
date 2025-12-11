package model;

import java.io.Serializable;

// Serializable la qua trinh convert object -> byte
public class Student implements Serializable {
    private String stuid;
    private String name;
    private String email;
    private String phone;
    private String mountainCode; // vi 1 mountain co nhieu sv dky, nen ta khai bao mountainCode trong sv
    private double price;

    public Student() {
        this.stuid = "";
        this.name = "";
        this.email = "";
        this.phone = "";
        this.mountainCode = "";
        this.price = 0.0;
    }
    
    public Student(String stuid, String name, String email, String phone, String mountainCode, double price) {
        this.stuid = stuid;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.mountainCode = mountainCode;
        this.price = price;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-24s | %-10s | %-13s | %,10.0f", stuid, name, phone, mountainCode, price);
    }
}
