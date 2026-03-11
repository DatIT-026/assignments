/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cuongnh.registration;

import cuongnh.util.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        RegistrationDTO result = null;
        //bao va gan null
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            //1. Connect db
            con = DBUtils.getConnection();
            if (con != null) {
                //2.Model queries data from db
                //2.1. Create sql string
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";

                //2.2. Load sql into stm object
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
                pstm.setString(2, password);

                //2.3. Execute queries
                rs = pstm.executeQuery();

                if (rs.next()) {
                    //3.Model loads data from db to model
                    //4.Model process and return result
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, lastname, role);
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

    //SOLID
    List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue)
            throws SQLException, ClassNotFoundException {
        //bao va gan null
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            //1. Connect db
            con = DBUtils.getConnection();
            if (con != null) {

                //2.Model queries data from db
                //2.1. Create sql string    
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";

                //2.2. Load sql into stm object
                pstm = con.prepareStatement(sql);
                pstm.setString(1, "%" + searchValue + "%");

                //2.3. Execute queries
                rs = pstm.executeQuery();

                while (rs.next()) {
                    //2.3.1 Model load data from db
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    //2.3.2 Model store data from db to itself
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
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        //bao va gan null
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            //1. Connect db
            con = DBUtils.getConnection();
            if (con != null) {
                //2.Model queries data from db
                //2.1. Create sql string
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //2.2. Load sql into stm object
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);

                //2.3. Execute queries
                int effectedRows = pstm.executeUpdate();
                if (effectedRows > 0) {
                    //3.Model loads data from db to model
                    //4.Model process and return result
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
    
    public boolean updateAccount(String username, String password, boolean role)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        //bao va gan null
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            //1. Connect db
            con = DBUtils.getConnection();
            if (con != null) {
                //2.Model queries data from db
                //2.1. Create sql string
                String sql = "Update Registration "
                        + "Set password = ?, "
                        + "isAdmin = ? "
                        + "Where username = ?";
                //2.2. Load sql into stm object
                pstm = con.prepareStatement(sql);
                pstm.setString(1, password);
                pstm.setBoolean(2, role);
                pstm.setString(3, username);

                //2.3. Execute queries
                int effectedRows = pstm.executeUpdate();
                if (effectedRows > 0) {
                    //3.Model loads data from db to model
                    //4.Model process and return result
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
    
    public boolean createAccount(RegistrationDTO account) 
            throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBUtils.getConnection();
            
            if (con != null) {
                String sql = "INSERT INTO Registration("
                    + "username, password, lastname, isAdmin) "
                    + "VALUES(?, ?, ?, ?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                
                int effectedRows = stm.executeUpdate();
                
                if (effectedRows > 0) result = true;
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
}
