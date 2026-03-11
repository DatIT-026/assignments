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
import java.util.ArrayList;
import java.util.List;
import thu.utils.DBUtil;

/**
 *
 * @author C00kies
 */
public class ProductDAO implements Serializable {

    List<ProductDTO> products;

    public List<ProductDTO> getProduct() {
        return products;
    }

    public void searchProductName(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "Select * "
                        + "from Products "
                        + "where productName like ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, "%" + searchValue + "%");

                rs = pstm.executeQuery();

                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    float price = rs.getFloat("price");
                    int stockQuantity = rs.getInt("stockQuantity");
                    String category = rs.getString("category");
                    boolean status = rs.getBoolean("status");

                    ProductDTO dto = new ProductDTO(productID, productName, price, stockQuantity, category, status);

                    if (this.products == null) {
                        products = new ArrayList<>();
                    }
                    this.products.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            };
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean update(int productID, float price, int stockQuantity, boolean status)
            throws ClassNotFoundException, SQLException {
        boolean result = false;

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "Update Products "
                        + "set price = ?, "
                        + "stockQuantity = ?, "
                        + "status = ? "
                        + "where productID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setFloat(1, price);
                pstm.setInt(2, stockQuantity);
                pstm.setBoolean(3, status);
                pstm.setInt(4, productID);

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

    public boolean delete(int productID)
            throws ClassNotFoundException, SQLException {
        boolean result = false;

        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "Update Products "
                        + "set status = 0"
                        + "where productID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, productID);

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

    public int getStockQuantity(int productID)
            throws ClassNotFoundException, SQLException {
        int result = 0;

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "select stockQuantity "
                        + "from Products "
                        + "where productID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, productID);
                rs = pstm.executeQuery();

                if (rs.next()) {
                    result = rs.getInt("stockQuantity");
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
