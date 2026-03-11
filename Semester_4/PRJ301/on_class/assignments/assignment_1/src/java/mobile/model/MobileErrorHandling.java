/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mobile.model;

import java.io.Serializable;

/**
 *
 * @author datto
 */
public class MobileErrorHandling implements Serializable {
    private String mobileIdError;
    private String mobileIdDuplicatedError;
    private String mobileNameError;
    private String priceError;
    private String yearOfProductionError;
    private String quantityError;

    public MobileErrorHandling() {
    }

    public MobileErrorHandling(String mobileIdError, String mobileIdDuplicatedError, String mobileNameError, String priceError, String yearOfProductionError, String quantityError) {
        this.mobileIdError = mobileIdError;
        this.mobileIdDuplicatedError = mobileIdDuplicatedError;
        this.mobileNameError = mobileNameError;
        this.priceError = priceError;
        this.yearOfProductionError = yearOfProductionError;
        this.quantityError = quantityError;
    }

    /**
     * @return the mobileIdError
     */
    public String getMobileIdError() {
        return mobileIdError;
    }

    /**
     * @param mobileIdError the mobileIdError to set
     */
    public void setMobileIdError(String mobileIdError) {
        this.mobileIdError = mobileIdError;
    }

    /**
     * @return the mobileIdDuplicatedError
     */
    public String getMobileIdDuplicatedError() {
        return mobileIdDuplicatedError;
    }

    /**
     * @param mobileIdDuplicatedError the mobileIdDuplicatedError to set
     */
    public void setMobileIdDuplicatedError(String mobileIdDuplicatedError) {
        this.mobileIdDuplicatedError = mobileIdDuplicatedError;
    }

    /**
     * @return the mobileNameError
     */
    public String getMobileNameError() {
        return mobileNameError;
    }

    /**
     * @param mobileNameError the mobileNameError to set
     */
    public void setMobileNameError(String mobileNameError) {
        this.mobileNameError = mobileNameError;
    }

    /**
     * @return the priceError
     */
    public String getPriceError() {
        return priceError;
    }

    /**
     * @param priceError the priceError to set
     */
    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    /**
     * @return the yearOfProductionError
     */
    public String getYearOfProductionError() {
        return yearOfProductionError;
    }

    /**
     * @param yearOfProductionError the yearOfProductionError to set
     */
    public void setYearOfProductionError(String yearOfProductionError) {
        this.yearOfProductionError = yearOfProductionError;
    }

    /**
     * @return the quantityError
     */
    public String getQuantityError() {
        return quantityError;
    }

    /**
     * @param quantityError the quantityError to set
     */
    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    
}
