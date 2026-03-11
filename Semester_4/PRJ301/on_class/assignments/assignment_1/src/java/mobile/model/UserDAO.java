/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mobile.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mobile.util.DBUtils;

/**
 *
 * @author datto
 */
public class UserDAO implements Serializable {
    public UserDTO checkLogin(String userId, int password) 
            throws SQLException, ClassNotFoundException  {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "Select fullName, role "
                        + "From Users "
                        + "Where userId = ? "
                        + "And password = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, userId);
                pstm.setInt(2, password);
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String fullname = rs.getString("fullName");
                    int role = rs.getInt("role");
                    user = new UserDTO(userId, password, fullname, role);
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

