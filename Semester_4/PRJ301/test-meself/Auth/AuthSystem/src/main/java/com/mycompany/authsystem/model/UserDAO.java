/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.authsystem.model;

import com.authsystem.authsystem.model.UserDTO;
import com.mycompany.authsystem.infrastructure.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;
import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory;


/**
 *
 * @author datto
 */
public class UserDAO {
    private Connection connection;
    
    public UserDAO() throws NamingException, SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public UserDTO checkLogin(String username, String password) 
            throws SQLException {
          // try with resources
          String sql = "SELECT * "
                    + "FROM Users "
                    + "WHERE username = ? "
                    + "AND password = ? "
                    + "AND is_active = 1";
          try (PreparedStatement stm = connection.prepareStatement(sql)) {
              stm.setString(1, username);
              stm.setString(2, password);
              
              try (ResultSet rs = stm.executeQuery()) {
                  if (rs.next()) {
                      return extractUserFromResultSet(rs);
                  }
              }
          }
          return null;
    }
    
    /**
     * Update last login timeStamp
     */
    public boolean updateLastLogin(int userID) 
            throws SQLException {
          String sql = "UPDATE Users "
                    + "SET last_login = GETDATE() "
                    + "WHERE user_id = ?";
          try (PreparedStatement stm = connection.prepareStatement(sql)) {
              stm.setInt(1, userID);
              return stm.executeUpdate() > 0;
          }
    }
    
    /**
     * Find user by Social id (Google, ...)
     */
    public UserDTO findBySocialID(String socialId) 
            throws SQLException {
        String sql = "SELECT * "
                + "FROM Users "
                + "WHERE social_id = ? "
                + "AND is_active = 1";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, socialId);
              
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                } 
            }
        }
        return null;
    }
    
    /**
     * Find User by email
     */
    public UserDTO findByEmail(String email) throws SQLException {
        String sql = "SELECT * "
                + "FROM Users "
                + "WHERE email = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, email);
              
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                } 
            }
        }
        
        return null;
    }
    
    /**
     * Find user by username
     */
    public UserDTO findByUsername(String username) 
            throws SQLException {
        String sql = "SELECT * FROM Users "
                + "WHERE username = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, username);
            
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        }
        
        return null;
    }
    
    /**
     * Update verification code for password recovery
     */
    public boolean updateVerificationCode(String email, String code, Timestamp expiry) 
            throws SQLException {
        String sql = "UPDATE Users SET verification_code = ?, "
                + "code_expiry = ? "
                + "WHERE email = ? AND is_active = 1";
        
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, code);
            pstm.setTimestamp(2, expiry);
            pstm.setString(3, email);
            
            return pstm.executeUpdate() > 0;
        }
    }
    
    /**
     * Create new user account
     */
    public boolean createUser(UserDTO user) 
            throws SQLException {
        String sql = "INSERT INTO Users ("
                + "username, password, email, full_name, verification_code, is_verified, social_id, profile_picture) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
        try (PreparedStatement pstm = connection.prepareStatement(sql) ) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getEmail());
            pstm.setString(4, user.getFullName());
            pstm.setString(5, user.getVerificationCode());
            pstm.setBoolean(6, user.isIsVerified());
            pstm.setString(7, user.getSocialId());
            pstm.setString(8, user.getProfilePicture());
            
            return pstm.executeUpdate() > 0;
        }
    }
    
    
    /**
     * Extract UserDTO from result set
     */
    private UserDTO extractUserFromResultSet(ResultSet rs) throws SQLException {
        UserDTO user = new UserDTO();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setFullName(rs.getString("full_name"));
        user.setIsAdmin(rs.getBoolean("is_admin"));
        user.setIsVerified(rs.getBoolean("is_verified"));
        user.setVerificationCode(rs.getString("verification_code"));
        user.setResetCode(rs.getString("reset_code"));
        user.setResetCodeExpiry(rs.getTimestamp("code_expiry"));
        user.setCreatedDate(rs.getTimestamp("created_date"));
        user.setLastLogin(rs.getTimestamp("last_login"));
        user.setIsActive(rs.getBoolean("is_active"));
        user.setSocialId(rs.getString("social_id"));
        user.setProfilePicture(rs.getString("profile_picture"));
        return user;
    }
    
    /**
     * Close database connection
     */
    public void close() {
        DatabaseConnection.closeConnection(connection);
    }
}
