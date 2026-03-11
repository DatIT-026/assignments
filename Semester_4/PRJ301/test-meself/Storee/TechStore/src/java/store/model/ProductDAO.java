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
import java.util.ArrayList;
import java.util.List;
import store.utils.DBUtils;

/**
 *
 * @author datto
 */
public class ProductDAO implements Serializable {
    private ProductDTO mapRow(ResultSet rs) throws SQLException {
        return new ProductDTO(
            rs.getInt("ProductID"),
            rs.getNString("ProductName"),
            rs.getBigDecimal("Price").doubleValue(),
            rs.getInt("StockQuantity"),
            rs.getString("Category"),
            rs.getBoolean("Status")
        );
    }
    
    List<ProductDTO> products;

    public List<ProductDTO> getProduct() {
        return products;
    }

    public void searchProducts(String searchValue, String role) throws Exception {
        Connection conn = DBUtils.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Products "
                    + "WHERE ProductName LIKE ? ";
            if ("CUSTOMER".equals(role)) {
                sql += " AND Status = 1";
            }

            pstm = conn.prepareStatement(sql);
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
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public ProductDTO getProductByID(int productID) 
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM Products "
                        + "WHERE ProductID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, productID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return new ProductDTO(
                            rs.getInt("productID"),
                            rs.getString("productName"),
                            rs.getDouble("price"),
                            rs.getInt("stockQuantity"),
                            rs.getString("category"),
                            rs.getBoolean("status")
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
    
    public int getStockQuantity(int productID) 
            throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
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

    public boolean deleteProduct(int productID) 
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;
        
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Products "
                        + "SET Status = 0 "
                        + "WHERE ProductID = ?";
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

    public boolean updateProduct(ProductDTO p) 
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Products "
                        + "SET Price = ?, "
                        + "StockQuantity = ?, "
                        + "Status = ? "
                        + "WHERE ProductID = ?";
                stm = con.prepareStatement(sql);
                
                stm.setDouble(1, p.getPrice());
                stm.setInt(2, p.getStockQuantity());
                
//                stm.setBoolean(3, p.isStatus()); // cho debug ay ma
                
                stm.setInt(4, p.getProductID());
                
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
}
