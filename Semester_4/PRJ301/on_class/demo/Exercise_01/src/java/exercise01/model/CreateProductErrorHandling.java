/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercise01.model;

import java.io.Serializable;

/**
 *
 * @author datto
 */
public class CreateProductErrorHandling implements Serializable {
    private String productNameLengthErr;
    private String unitPriceFormatErr;
    private String quantityFormatErr;

    public CreateProductErrorHandling() {
    }

    public CreateProductErrorHandling(String productNameLengthErr, String unitPriceFormatErr, String quantityFormatErr) {
        this.productNameLengthErr = productNameLengthErr;
        this.unitPriceFormatErr = unitPriceFormatErr;
        this.quantityFormatErr = quantityFormatErr;
    }

    /**
     * @return the productNameLengthErr
     */
    public String getProductNameLengthErr() {
        return productNameLengthErr;
    }

    /**
     * @param productNameLengthErr the productNameLengthErr to set
     */
    public void setProductNameLengthErr(String productNameLengthErr) {
        this.productNameLengthErr = productNameLengthErr;
    }

    /**
     * @return the unitPriceFormatErr
     */
    public String getUnitPriceFormatErr() {
        return unitPriceFormatErr;
    }

    /**
     * @param unitPriceFormatErr the unitPriceFormatErr to set
     */
    public void setUnitPriceFormatErr(String unitPriceFormatErr) {
        this.unitPriceFormatErr = unitPriceFormatErr;
    }

    /**
     * @return the quantityFormatErr
     */
    public String getQuantityFormatErr() {
        return quantityFormatErr;
    }

    /**
     * @param quantityFormatErr the quantityFormatErr to set
     */
    public void setQuantityFormatErr(String quantityFormatErr) {
        this.quantityFormatErr = quantityFormatErr;
    }
}
