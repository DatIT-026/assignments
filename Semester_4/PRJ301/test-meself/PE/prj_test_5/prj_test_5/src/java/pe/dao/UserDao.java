/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.dto.UserDto;
import pe.utils.DBUtils;

/**
 *
 * @author datto
 */
public class UserDao implements Serializable {

    public UserDto checkLogin(String userId)
            throws SQLException, ClassNotFoundException {
        UserDto user = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select fullName, password, roleID, status "
                        + "From tblUsers "
                        + "Where userId = ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, userId);

                rs = pstm.executeQuery();

                if (rs.next()) {
                    String fullname = rs.getString("fullName");
                    String password = rs.getString("password");
                    String roleID = rs.getString("roleID");
                    int status = rs.getInt("status");

                    user = new UserDto(userId, fullname, password, roleID, status);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return user;
    }
}

