/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author datto
 */
public class PetDto {
    private int petID;
    private String petName;
    private String breed;
    private int age;
    private float price;
    private String description;

    public PetDto() {
    }

    public PetDto(int petID, String petName, String breed, int age, float price, String description) {
        this.petID = petID;
        this.petName = petName;
        this.breed = breed;
        this.age = age;
        this.price = price;
        this.description = description;
    }

    /**
     * @return the petID
     */
    public int getPetID() {
        return petID;
    }

    /**
     * @param petID the petID to set
     */
    public void setPetID(int petID) {
        this.petID = petID;
    }

    /**
     * @return the petName
     */
    public String getPetName() {
        return petName;
    }

    /**
     * @param petName the petName to set
     */
    public void setPetName(String petName) {
        this.petName = petName;
    }

    /**
     * @return the breed
     */
    public String getBreed() {
        return breed;
    }

    /**
     * @param breed the breed to set
     */
    public void setBreed(String breed) {
        this.breed = breed;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
