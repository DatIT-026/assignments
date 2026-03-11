/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author C00kies
 */
public class HomestaysDTO implements Serializable{
    private String homeID;
    private String description;
    private int price;
    private String address;
    private String userID;

    public HomestaysDTO() {
    }

    public HomestaysDTO(String homeID, String description, int price, String address, String userID) {
        this.homeID = homeID;
        this.description = description;
        this.price = price;
        this.address = address;
        this.userID = userID;
    }

    /**
     * @return the homeID
     */
    public String getHomeID() {
        return homeID;
    }

    /**
     * @param homeID the homeID to set
     */
    public void setHomeID(String homeID) {
        this.homeID = homeID;
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

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}
