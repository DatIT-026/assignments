/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.DTO;

/**
 *
 * @author datto
 */
public class UserError {
    private String userNameError;
    private String lastNameError;
    private String passwordError;
    private String duplicateUserName;

    public UserError() {
    }

    public UserError(String userNameError, String lastNameError, String passwordError, String duplicateUserName) {
        this.userNameError = userNameError;
        this.lastNameError = lastNameError;
        this.passwordError = passwordError;
        this.duplicateUserName = duplicateUserName;
    }

    /**
     * @return the userNameError
     */
    public String getUserNameError() {
        return userNameError;
    }

    /**
     * @param userNameError the userNameError to set
     */
    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    /**
     * @return the lastNameError
     */
    public String getLastNameError() {
        return lastNameError;
    }

    /**
     * @param lastNameError the lastNameError to set
     */
    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
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
     * @return the duplicateUsername
     */
    public String getDuplicateUserName() {
        return duplicateUserName;
    }

    /**
     * @param duplicateUsername the duplicateUsername to set
     */
    public void setDuplicateUserName(String duplicateUserName) {
        this.duplicateUserName = duplicateUserName;
    }
    
    
}
