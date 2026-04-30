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
import pe.dto.UserDTO;
import pe.util.DBUtil;

/**
 *
 * @author Miniks
 */
public class UserDAO implements Serializable {

    public UserDTO checkLogin(String userID, String password)
            throws SQLException, ClassNotFoundException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "Select fullName, roleID, status "
                        + "From tblUsers "
                        + "Where userID = ? "
                        + "And password = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, userID);
                pstm.setString(2, password);
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String fullname = rs.getString("fullName");
                    String roleID = rs.getString("RoleID");
                    int status = rs.getInt("status");
                    user = new UserDTO(userID, password, fullname, roleID, status);
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
