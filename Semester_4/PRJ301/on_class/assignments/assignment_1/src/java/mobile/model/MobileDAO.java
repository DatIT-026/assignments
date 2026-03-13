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
import java.util.ArrayList;
import java.util.List;
import mobile.util.DBUtils;

/**
 *
 * @author datto
 */
public class MobileDAO implements Serializable {
    List<MobileDTO> mobile;

    // getter
    public List<MobileDTO> getMobiles() {
        return mobile;
    }
    
    public void searchMobileByPriceRange(double minPrice, double maxPrice)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearOfProduction, quantity, notSale "
                        + "FROM Mobiles "
                        + "WHERE price >= ? AND price <= ?";

                pstm = con.prepareStatement(sql);
                pstm.setDouble(1, minPrice);
                pstm.setDouble(2, maxPrice);

                rs = pstm.executeQuery();

                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");

                    MobileDTO dto = new MobileDTO(
                            mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);

                    if (this.mobile == null) {
                        this.mobile = new ArrayList<>();
                    }
                    
                    this.mobile.add(dto);
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

    public void searchMobile(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearOfProduction, quantity, notSale "
                        + "FROM Mobiles "
                        + "WHERE mobileId LIKE ? OR mobileName LIKE ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, "%" + searchValue + "%");
                pstm.setString(2, "%" + searchValue + "%");

                rs = pstm.executeQuery();

                while (rs.next()) {
                    String mobileId = rs.getString("mobileId");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    String mobileName = rs.getString("mobileName");
                    int yearOfProduction = rs.getInt("yearOfProduction");
                    int quantity = rs.getInt("quantity");
                    boolean notSale = rs.getBoolean("notSale");

                    MobileDTO dto = new MobileDTO(
                            mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);

                    if (this.mobile == null) {
                        mobile = new ArrayList<>();
                    }
                    this.mobile.add(dto);
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
    
    public MobileDTO getMobileByID(String mobileID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT mobileId, description, price, mobileName, yearOfProduction, quantity, notSale "
                        + "FROM Mobiles "
                        + "WHERE mobileId = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, mobileID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return new MobileDTO(
                        rs.getString("mobileId"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("mobileName"),
                        rs.getInt("yearOfProduction"),
                        rs.getInt("quantity"),
                        rs.getBoolean("notSale")
                    );
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
        return null;
    }
    
    public boolean createMobile(MobileDTO mobile)
            throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "INSERT INTO Mobiles("
                        + "mobileId, description, price, mobileName, yearOfProduction, quantity, notSale) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);
                
                stm.setString(1, mobile.getMobileId());
                stm.setString(2, mobile.getDescription());
                stm.setDouble(3, mobile.getPrice());
                stm.setString(4, mobile.getMobileName());
                stm.setInt(5, mobile.getYearOfProduction());
                stm.setInt(6, mobile.getQuantity());
                stm.setBoolean(7, mobile.isNotSale());

                int effectedRows = stm.executeUpdate();

                if (effectedRows > 0) {
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

    public boolean deleteMobile(String mobileId)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "DELETE FROM Mobiles "
                        + "WHERE mobileId = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, mobileId);

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

    public boolean updateMobile(MobileDTO mobile)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Mobiles SET "
                        + "description = ?, "
                        + "price = ?, "
                        + "mobileName = ?, "
                        + "yearOfProduction = ?, "
                        + "quantity = ?, "
                        + "notSale = ? "
                        + "WHERE mobileId = ?";
                
                pstm = con.prepareStatement(sql);
                
                pstm.setString(1, mobile.getDescription());
                pstm.setDouble(2, mobile.getPrice());
                pstm.setString(3, mobile.getMobileName());
                pstm.setInt(4, mobile.getYearOfProduction());
                pstm.setInt(5, mobile.getQuantity());
                pstm.setBoolean(6, mobile.isNotSale());
                pstm.setString(7, mobile.getMobileId());

                int row = pstm.executeUpdate();
                if (row > 0) return true;
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return false;
    }
}
