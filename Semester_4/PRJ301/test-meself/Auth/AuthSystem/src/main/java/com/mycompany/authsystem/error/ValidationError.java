/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.authsystem.error;

import java.io.Serializable;

/**
 *
 * @author datto
 */
public class ValidationError implements Serializable {

    private static long serialVersionUID = 1L;

    private String usernameError;
    private String passwordError;
    private String emailError;
    private String fullNameError;
    private String confirmPasswordError;
    private String verificationCodeError;
    private String resetCodeError;
    private String generalError;

    public ValidationError() {
    }

    /**
     * @return the serialVersiionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersiionUID the serialVersiionUID to set
     */
    public static void setSerialVersiionUID(long aSerialVersiionUID) {
        serialVersionUID = aSerialVersiionUID;
    }

    /**
     * @return the usernameError
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * @param usernameError the usernameError to set
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * @return the passwordError
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * @param passwordError the passwordError to set
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * @return the emailError
     */
    public String getEmailError() {
        return emailError;
    }

    /**
     * @param emailError the emailError to set
     */
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    /**
     * @return the fullNameError
     */
    public String getFullNameError() {
        return fullNameError;
    }

    /**
     * @param fullNameError the fullNameError to set
     */
    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    /**
     * @return the confirmPasswordError
     */
    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    /**
     * @param confirmPasswordError the confirmPasswordError to set
     */
    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }

    /**
     * @return the verificationCodeError
     */
    public String getVerificationCodeError() {
        return verificationCodeError;
    }

    /**
     * @param verificationCodeError the verificationCodeError to set
     */
    public void setVerificationCodeError(String verificationCodeError) {
        this.verificationCodeError = verificationCodeError;
    }

    /**
     * @return the resetCodeError
     */
    public String getResetCodeError() {
        return resetCodeError;
    }

    /**
     * @param resetCodeError the resetCodeError to set
     */
    public void setResetCodeError(String resetCodeError) {
        this.resetCodeError = resetCodeError;
    }

    /**
     * @return the generalError
     */
    public String getGeneralError() {
        return generalError;
    }

    /**
     * @param generalError the generalError to set
     */
    public void setGeneralError(String generalError) {
        this.generalError = generalError;
    }

    /**
     * Check if there are any validation errors
     *
     * @return true if at least
     */
    public boolean hasErrors() {
        return usernameError != null
                || passwordError != null
                || emailError != null
                || fullNameError != null
                || confirmPasswordError != null
                || verificationCodeError != null
                || resetCodeError != null
                || generalError != null;
    }

    /**
     * Clear all errors
     */
    public void cleanErrors() {
        usernameError = null;
        passwordError = null;
        emailError = null;
        fullNameError = null;
        confirmPasswordError = null;
        verificationCodeError = null;
        resetCodeError = null;
        generalError = null;
    }
}
