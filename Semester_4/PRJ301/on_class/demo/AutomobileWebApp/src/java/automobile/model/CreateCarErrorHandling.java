/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package automobile.model;

import java.io.Serializable;

/**
 *
 * @author datto
 */
public class CreateCarErrorHandling implements Serializable {
    private String carIDFormatErr;
    private String carNameLengthErr;
    private String manufacturerLengthErr;
    private String priceFormatErr;
    private String releasedYearFormatErr;

    public CreateCarErrorHandling() {
    }

    public CreateCarErrorHandling(String carIDFormatErr, String carNameLengthErr, String manufacturerLengthErr, String priceFormatErr, String releasedYearFormatErr) {
        this.carIDFormatErr = carIDFormatErr;
        this.carNameLengthErr = carNameLengthErr;
        this.manufacturerLengthErr = manufacturerLengthErr;
        this.priceFormatErr = priceFormatErr;
        this.releasedYearFormatErr = releasedYearFormatErr;
    }

    /**
     * @return the carIDFormatErr
     */
    public String getCarIDFormatErr() {
        return carIDFormatErr;
    }

    /**
     * @param carIDFormatErr the carIDFormatErr to set
     */
    public void setCarIDFormatErr(String carIDFormatErr) {
        this.carIDFormatErr = carIDFormatErr;
    }

    /**
     * @return the carNameLengthErr
     */
    public String getCarNameLengthErr() {
        return carNameLengthErr;
    }

    /**
     * @param carNameLengthErr the carNameLengthErr to set
     */
    public void setCarNameLengthErr(String carNameLengthErr) {
        this.carNameLengthErr = carNameLengthErr;
    }

    /**
     * @return the manufacturerLengthErr
     */
    public String getManufacturerLengthErr() {
        return manufacturerLengthErr;
    }

    /**
     * @param manufacturerLengthErr the manufacturerLengthErr to set
     */
    public void setManufacturerLengthErr(String manufacturerLengthErr) {
        this.manufacturerLengthErr = manufacturerLengthErr;
    }

    /**
     * @return the priceFormatErr
     */
    public String getPriceFormatErr() {
        return priceFormatErr;
    }

    /**
     * @param priceFormatErr the priceFormatErr to set
     */
    public void setPriceFormatErr(String priceFormatErr) {
        this.priceFormatErr = priceFormatErr;
    }

    /**
     * @return the releasedYearFormatErr
     */
    public String getReleasedYearFormatErr() {
        return releasedYearFormatErr;
    }

    /**
     * @param releasedYearFormatErr the releasedYearFormatErr to set
     */
    public void setReleasedYearFormatErr(String releasedYearFormatErr) {
        this.releasedYearFormatErr = releasedYearFormatErr;
    }

    
}
