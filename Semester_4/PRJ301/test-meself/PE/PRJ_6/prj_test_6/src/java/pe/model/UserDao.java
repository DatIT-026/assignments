/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DBUtils;

/**
 *
 * @author Miniks
 */
public class UserDao implements Serializable {
    public UserDto checkLogin(String userID, String password)
            throws ClassNotFoundException, SQLException {

        String sql = "SELECT fullName, password, roleID, status "
                + "FROM tblUsers "
                + "WHERE userID = ?";

        try (Connection con = DBUtils.getConnection(); 
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, userID);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new UserDto(
                        userID,
                        rs.getString("fullName"),
                        rs.getString("password"),
                        rs.getString("roleID"), 
                        rs.getInt("status")
                    );
                }
            }
        }
        return null;
    }
}
