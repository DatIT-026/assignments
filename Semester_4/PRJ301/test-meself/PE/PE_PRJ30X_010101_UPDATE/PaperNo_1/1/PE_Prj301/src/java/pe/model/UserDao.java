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
public class UserDao implements Serializable {
    //-----            your code here   --------------------------------
    public UserDto checkLogin(String userID, String password)
            throws ClassNotFoundException, SQLException {

        String sql = "SELECT fullName, roleID, status "
                + "FROM tblUsers "
                + "WHERE userID = ? "
                + "AND password = ?";

        try (Connection con = DbUtils.getConnection(); 
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, userID);
            pstm.setString(2, password);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new UserDto(
                        userID,
                        rs.getString("fullName"),
                        password,
                        rs.getString("roleID"), 
                        rs.getBoolean("status")
                    );
                }
            }
        }
        return null;
    }
}


