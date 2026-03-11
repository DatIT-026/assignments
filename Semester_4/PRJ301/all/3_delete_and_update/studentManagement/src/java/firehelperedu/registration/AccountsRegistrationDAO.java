/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firehelperedu.registration;

import firehelperedu.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author datto
 */
public class AccountsRegistrationDAO implements Serializable {

    public AccountsRegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        AccountsRegistrationDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection(); // connect db

            // model queries data from db
            if (con != null) {
                // create sql string
                String sql = "SELECT studentID, fullName, userName, password, addressVN, createdAt, isAdmin "
                        + "FROM studentList "
                        + "WHERE username = ? "
                        + "AND password = ?";
                // load sql into the db
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                rs = stm.executeQuery(); // execute queries

                if (rs.next()) {
                    // model loads data from db to model
                    // model process and return result
                    int id = rs.getInt("studentID");
                    String fullname = rs.getString("fullName");
                    String address = rs.getString("addressVN");
                    String dateCreated = rs.getString("createdAt");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new AccountsRegistrationDTO(id, fullname, username, password, address, dateCreated, role);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    List<AccountsRegistrationDTO> accounts;

    public List<AccountsRegistrationDTO> getAccount() {
        return accounts;
    }

    public void listAccount()
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection(); // connect db

            // model queries data from db
            if (con != null) {
                // create sql string
                String sql = "SELECT studentID, fullName, userName, password, addressVN, createdAt, isAdmin "
                        + "FROM studentList";
                // load sql into the db
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery(); // execute queries

                while (rs.next()) {
                    // model loads data from db to model
                    // model process and return result
                    int id = rs.getInt("studentID");
                    String fullname = rs.getString("fullName");
                    String username = rs.getString("userName");
                    String password = rs.getString("password");
                    String address = rs.getString("addressVN");
                    String dateCreated = rs.getString("createdAt");
                    boolean role = rs.getBoolean("isAdmin");
                    AccountsRegistrationDTO dto = new AccountsRegistrationDTO(id, fullname, username, password, address, dateCreated, role);

                    if (this.accounts == null) {
                        accounts = new ArrayList<>();
                    }
                    this.accounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean updateAccount(int id, String fullName, String password, String address, boolean isAdmin)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE studentList SET fullName = ?, password = ?, addressVN = ?, isAdmin = ? "
                        + "WHERE studentID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, fullName);
                stm.setString(2, password);
                stm.setString(3, address);
                stm.setBoolean(4, isAdmin);
                stm.setInt(5, id);

                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public boolean deleteAccount(String username)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "DELETE FROM studentList "
                        + "Where userName = ? ";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                int effectedRows = pstm.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                }
            }
        } finally {
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
