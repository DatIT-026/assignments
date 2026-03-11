/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtils;

/**
 *
 * @author Admin
 */
public class UserDao implements Serializable {
    public UserDto checkLogin(String userID, String password) 
            throws SQLException, ClassNotFoundException  {
        UserDto user = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select fullName, roleID, status "
                        + "From Users "
                        + "Where userID = ? "
                        + "And password = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, userID);
                pstm.setString(2, password);
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String fullname = rs.getString("fullName");
                    String roleID = rs.getString("RoleID");
                    boolean status = rs.getBoolean("status");
                    user = new UserDto(userID, fullname, roleID, password, status);
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
