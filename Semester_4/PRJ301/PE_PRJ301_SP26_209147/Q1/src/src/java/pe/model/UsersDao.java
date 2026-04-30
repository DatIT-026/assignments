/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class UsersDao implements Serializable {
    //-----            your code here   --------------------------------
    public UsersDto checkLogin(String userName, String password)
            throws ClassNotFoundException, SQLException {
        
        String sql = "SELECT fullName, role "
                + "FROM tblUsers "
                + "WHERE userName = ? "
                + "AND password = ?";
        
        try (Connection con = DbUtils.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, userName);
            pstm.setString(2, password);
            
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new UsersDto(
                            userName,
                            rs.getString("fullName"),
                            password,
                            rs.getInt("role")
                    );
                }
            }
        }
        return null;
    }
}
