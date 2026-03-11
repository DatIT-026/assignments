/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firehelperedu.registration;

import firehelperedu.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author datto
 */
public class RegistrationDAO implements Serializable {
    public boolean checkLogin(String username, String password) 
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null; // bien de dung cau lenh sql
        ResultSet rs = null; // bien chua ket qua tra ve

        boolean result = false;

        try {
            // 1. ket noi voi database
            con = DBUtils.getConnection();
            
            String sql = "SELECT userName "
                    + "FROM studentList "
                    + "WHERE userName = ? "
                    + "AND password = ?";
            
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
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
