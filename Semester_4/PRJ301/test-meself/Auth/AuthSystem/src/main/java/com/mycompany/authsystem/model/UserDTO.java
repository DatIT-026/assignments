/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.authsystem.authsystem.model;

import java.io.Serializable;
import java.util.Date;
// import java.sql.Timestamp;
// import java.sql.Date;

/**
 *
 * @author Admin
 */
public class UserDTO implements Serializable{
    
    private static long serialVersionUID = 1L;
    
    private int userId;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private boolean isAdmin;
    private boolean isVerified;
    private String verificationCode;
    private String resetCode;
    private Date resetCodeExpiry;
    private Date createdDate;
    private Date lastLogin;
    private boolean isActive;
    private String socialId;
    private String profilePicture;

    public UserDTO() {
    }

    public UserDTO(String username, String email, String fullName) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
    }

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the isVerified
     */
    public boolean isIsVerified() {
return isVerified;
    }

    /**
     * @param isVerified the isVerified to set
     */
    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * @return the verificationCode
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * @param verificationCode the verificationCode to set
     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    /**
     * @return the resetCode
     */
    public String getResetCode() {
        return resetCode;
    }

    /**
     * @param resetCode the resetCode to set
     */
    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    /**
     * @return the resetCodeExpiry
     */
    public Date getResetCodeExpiry() {
        return resetCodeExpiry;
    }

    /**
     * @param resetCodeExpiry the resetCodeExpiry to set
     */
    public void setResetCodeExpiry(Date resetCodeExpiry) {
        this.resetCodeExpiry = resetCodeExpiry;
    }

    /**
     * @return the createdCode
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdCode the createdCode to set
     */
    public void setCreatedDate(Date createdCode) {
        this.createdDate = createdCode;
    }

    /**
     * @return the lastLogin
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * @return the isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return the socialId
     */
    public String getSocialId() {
        return socialId;
    }

    /**
     * @param socialId the socialId to set
     */
    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    /**
     * @return the profilePicture
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * @param profilePicture the profilePicture to set
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId + 
                ", username=" + username + 
                ", email=" + email + 
                ", fullName=" + fullName + 
                ", isAdmin=" + isAdmin + 
                ", isVerified=" + isVerified + 
                ", isActive=" + isActive + 
                ", socialId=" + socialId + '}';
    }
    
}