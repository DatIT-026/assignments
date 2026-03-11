/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shop.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import shop.utils.DBUtils;

/**
 *
 * @author datto
 */
public class UserDAO implements Serializable {
    public boolean registerUser(String userID, String fullName, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        
        boolean result = false;
        
        try {
            con = DBUtils.getConnection();
            
            String sql = "INSERT INTO dbo.tblUsers(userID, fullName, password, roleID, status) "
                    + "VALUES(?, ?, ?, 'MB', 1)";
            stm = con.prepareStatement(sql);
            
            stm.setString(1, userID);
            stm.setString(2, fullName);
            stm.setString(3, password);
            
            int effectRow = stm.executeUpdate();
            if (effectRow > 0) result = true;
            
        } finally {
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        
        return result;
    }
    
    public boolean checkLogin(String userID, String password) 
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        boolean result = false;
        
        try {
            con = DBUtils.getConnection();
            
            String sql = "SELECT userID "
                    + "FROM dbo.tblUsers "
                    + "WHERE userID = ? "
                    + "AND password = ?";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, userID);
            stm.setString(2, password);
            
            rs = stm.executeQuery();
            
            if (rs.next()) result = true;
            
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
        
        return result;
    }
}
