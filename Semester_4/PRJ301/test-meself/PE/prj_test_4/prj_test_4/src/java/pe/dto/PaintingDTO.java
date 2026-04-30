/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.dto;

import java.io.Serializable;

/**
 *
 * @author datto
 */
public class PaintingDTO implements Serializable {
    private String paintingID;
    private String description;
    private String creator;
    private float height;
    private float width;
    private double price;
    private boolean isAvailable;

    public PaintingDTO() {
    }

    public PaintingDTO(String paintingID, String description, String creator, float height, float width, double price, boolean isAvailable) {
        this.paintingID = paintingID;
        this.description = description;
        this.creator = creator;
        this.height = height;
        this.width = width;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    /**
     * @return the paintingID
     */
    public String getPaintingID() {
        return paintingID;
    }

    /**
     * @param paintingID the paintingID to set
     */
    public void setPaintingID(String paintingID) {
        this.paintingID = paintingID;
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
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the isAvailable
     */
    public boolean isIsAvailable() {
        return isAvailable;
    }

    /**
     * @param isAvailable the isAvailable to set
     */
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    
}
