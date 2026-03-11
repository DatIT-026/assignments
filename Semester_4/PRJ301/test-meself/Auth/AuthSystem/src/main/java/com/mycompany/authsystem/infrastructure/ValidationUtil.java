/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.authsystem.infrastructure;

import com.mycompany.authsystem.error.ValidationError;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author datto
 */
public class ValidationUtil {

    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    // Username validation pattern (alphanumeric and underscore, 3-20 characters)
    private static final Pattern USERNAME_PATTERN = Pattern.compile(
        "^[A-Za-z0-9_]{3,20}$"
    );

    // Password validation pattern 
    // Password must contain: min 8 chars, 1 uppercase, 1 lowercase, 1 number, 1 special char
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$"
    );
    
    /**
     * Validate login input
     * @param username
     * @param password
     * @return
     */
    public static ValidationError validationLogin(String username, String password) {
        ValidationError errors = new ValidationError();
        
        if (StringUtils.isBlank(username)) {
            errors.setUsernameError("Username is required");
        } else if (StringUtils.isBlank(password)) {
            errors.setPasswordError("Password is required");
        }
        
        return errors;
    }
    
    /**
     * Validate registration form
     */
    public static ValidationError validateRegistration(
            String username, String email, String fullName, String password, String confirmPassword) {
        ValidationError errors = new ValidationError();
        
        // validate username
        if (StringUtils.isBlank(username)) {
            errors.setUsernameError("Username is required!");
        } else if (!USERNAME_PATTERN.matcher(username).matches()) {
            errors.setUsernameError("Username must be 3-20 characters (letters, numbers, underscore only)");
        }
        
        // validate email
        if (StringUtils.isBlank(email)) {
            errors.setEmailError("Email is required!");
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            errors.setEmailError("Invalid email format");
        }
        
        // validate fullName
        if (StringUtils.isBlank(fullName)) {
            errors.setFullNameError("Name is required!");
        } else if (fullName.trim().length() < 2) {
            errors.setFullNameError("Name must be at least 2 characters");
        }
        
        // validate password
        if (StringUtils.isBlank(password)) {
            errors.setPasswordError("Password is required!");
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            errors.setPasswordError("Password must contain the following format: min 8 chars, at least 1 uppercase, 1 number, 1 special char");
        }
        
        // validate confirm password
        if (StringUtils.isBlank(confirmPassword)) {
            errors.setConfirmPasswordError("Please confirm password!");
        } else if (!password.equals(confirmPassword)) {
            errors.setConfirmPasswordError("The confirm password is not match with your password");
        }
        
        return errors;
    }
    
    /**
     * Generate random verification code (otp)
     */
    public static String generateVerificationCode() {
        return String.format("%06d", (int)(Math.random() * 100000));
    }
    
    /**
     * Sanitize input to prevent XSS
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        
        return input.trim()
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot")
                .replaceAll("'", "&#x27");
    }
}
