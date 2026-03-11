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
public class AccountDao implements Serializable {

    //-----            your code here   --------------------------------
    public AccountDTO checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException {
        AccountDTO user = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DbUtils.getConnection();
            if (con != null) {
                String sql = "Select fullName, phone, email, status, role "
                        + "From Account "
                        + "Where username = ? "
                        + "And password = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, password);
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    boolean status = rs.getBoolean("status");
                    int role = rs.getInt("role");
                    user = new AccountDTO(username, password, fullName, phone, email, status, role);
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
