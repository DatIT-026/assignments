/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author datto
 */
public class ComponentDto implements Serializable {
    private String itemID;
    private String itemName;
    private String category;
    private String manufacturer;
    private String specification;
    private String warehouseLocation;
    private Date importDate;
    private int quantity;
    private float unitPrice;
    private int warrantyMonth;
    private boolean isAvailable;
    private String note;

    public ComponentDto() {
    }

    public ComponentDto(String itemID, String itemName, String category, String manufacturer, String specification, String warehouseLocation, Date importDate, int quantity, float unitPrice, int warrantyMonth, boolean isAvailable, String note) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.category = category;
        this.manufacturer = manufacturer;
        this.specification = specification;
        this.warehouseLocation = warehouseLocation;
        this.importDate = importDate;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.warrantyMonth = warrantyMonth;
        this.isAvailable = isAvailable;
        this.note = note;
    }

    /**
     * @return the itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @return the specification
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * @param specification the specification to set
     */
    public void setSpecification(String specification) {
        this.specification = specification;
    }

    /**
     * @return the warehouseLocation
     */
    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    /**
     * @param warehouseLocation the warehouseLocation to set
     */
    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    /**
     * @return the importDate
     */
    public Date getImportDate() {
        return importDate;
    }

    /**
     * @param importDate the importDate to set
     */
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the unitPrice
     */
    public float getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the warrantyMonth
     */
    public int getWarrantyMonth() {
        return warrantyMonth;
    }

    /**
     * @param warrantyMonth the warrantyMonth to set
     */
    public void setWarrantyMonth(int warrantyMonth) {
        this.warrantyMonth = warrantyMonth;
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

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    
}
