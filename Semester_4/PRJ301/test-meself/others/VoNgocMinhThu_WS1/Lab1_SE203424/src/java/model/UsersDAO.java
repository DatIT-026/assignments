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
import utils.DButil;

/**
 *
 * @author C00kies
 */
public class UsersDAO implements Serializable {

    public UsersDTO checklogin(String userID, String password)
            throws SQLException, ClassNotFoundException {
        UsersDTO result = null;

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DButil.getConnection();
            if (con != null) {
                String sql = "Select fullName, roleID "
                        + "from tblUsers "
                        + "where userID = ? "
                        + "and password = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, userID);
                pstm.setString(2, password);

                rs = pstm.executeQuery();

                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    result = new UsersDTO(userID, fullName, roleID, null);
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
        return result;
    }

}
