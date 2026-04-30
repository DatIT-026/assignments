/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DBUtils;

/**
 *
 * @author datto
 */
public class UserDao implements Serializable {
    public UserDto checkLogin(String userID, String password)
            throws ClassNotFoundException, SQLException {

        String sql = "SELECT name FROM tblUser "
                + "WHERE userID = ? "
                + "AND password = ?";

        try (Connection con = DBUtils.getConnection(); 
                PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, userID);
            pstm.setString(2, password);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new UserDto(
                        userID,
                        rs.getString("name"),
                        password
                    );
                }
            }
        }
        return null;
    }
}