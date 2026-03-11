/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thu.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import thu.utils.DBUtil;

/**
 *
 * @author C00kies
 */
public class UserDAO implements Serializable {

    public UserDTO checkLogin(int userID, String password) 
            throws ClassNotFoundException, SQLException {
        UserDTO res = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String url = "Select username, role "
                        + "from Users "
                        + "where userID = ? "
                        + "and password = ?";
                pstm = con.prepareStatement(url);
                pstm.setInt(1, userID);
                pstm.setString(2, password);
            }
            rs = pstm.executeQuery();
            
            if (rs.next()) {
                String username = rs.getString("username");
                String role = rs.getString("role");
                res = new UserDTO(userID, username, null, role);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (con != null) con.close();
        }
        return res;
    }
}
