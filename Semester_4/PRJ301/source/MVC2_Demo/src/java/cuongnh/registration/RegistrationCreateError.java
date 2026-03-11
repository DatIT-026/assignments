/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cuongnh.registration;

import java.io.Serializable;

/**
 *
 * @author datto
 */
public class RegistrationCreateError implements Serializable {
    private String userNameLongErr;
    private String passwordLongErr;
    private String lastNameLongErr;
    private String confirmNotMatch;
    private String duplicatedUsername;

    public RegistrationCreateError() {
    }
    
    public RegistrationCreateError(String userNameLongErr, String passwordLongErr, String lastNameLongErr, String confirmNotMatch, String duplicatedUsername) {
        this.userNameLongErr = userNameLongErr;
        this.passwordLongErr = passwordLongErr;
        this.lastNameLongErr = lastNameLongErr;
        this.confirmNotMatch = confirmNotMatch;
        this.duplicatedUsername = duplicatedUsername;
    }

    /**
     * @return the userNameLongErr
     */
    public String getUserNameLongErr() {
        return userNameLongErr;
    }

    /**
     * @param userNameLongErr the userNameLongErr to set
     */
    public void setUserNameLongErr(String userNameLongErr) {
        this.userNameLongErr = userNameLongErr;
    }

    /**
     * @return the passwordLongErr
     */
    public String getPasswordLongErr() {
        return passwordLongErr;
    }

    /**
     * @param passwordLongErr the passwordLongErr to set
     */
    public void setPasswordLongErr(String passwordLongErr) {
        this.passwordLongErr = passwordLongErr;
    }

    /**
     * @return the lastNameLongErr
     */
    public String getLastNameLongErr() {
        return lastNameLongErr;
    }

    /**
     * @param lastNameLongErr the lastNameLongErr to set
     */
    public void setLastNameLongErr(String lastNameLongErr) {
        this.lastNameLongErr = lastNameLongErr;
    }

    /**
     * @return the confirmNotMatch
     */
    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    /**
     * @param confirmNotMatch the confirmNotMatch to set
     */
    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    /**
     * @return the duplicatedUsername
     */
    public String getDuplicatedUsername() {
        return duplicatedUsername;
    }

    /**
     * @param duplicatedUsername the duplicatedUsername to set
     */
    public void setDuplicatedUsername(String duplicatedUsername) {
        this.duplicatedUsername = duplicatedUsername;
    }
    
    
}
