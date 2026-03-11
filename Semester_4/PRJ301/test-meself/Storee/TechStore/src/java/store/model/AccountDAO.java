/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import store.utils.DBUtils;

/**
 *
 * @author datto
 */
public class AccountDAO implements Serializable {
    public AccountDTO checkLogin(String username, String password) 
            throws ClassNotFoundException, SQLException {
        AccountDTO user = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT UserID, Role "
                        + "FROM Users "
                        + "WHERE Username = ? "
                        + "AND Password = ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, password);
                rs = pstm.executeQuery();
                
                if (rs.next()) {
                    int id = rs.getInt("UserID");
                    String role = rs.getString("Role");
                    user = new AccountDTO(id, username, password, role);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        }
        return user;
    }
}
