/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercise01.model;

import exercise01.utils.DBUtils;
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
public class ProductDAO implements Serializable {

    //SOLID
    List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void listProducts()
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT ProductID, ProductName, UnitPrice, Quantity "
                        + "FROM Products";
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    int ProductID = rs.getInt("ProductID");
                    String ProductName = rs.getString("ProductName");
                    float UnitPrice = rs.getFloat("UnitPrice");
                    int Quantity = rs.getInt("Quantity");
                    ProductDTO dto = new ProductDTO(ProductID, ProductName, UnitPrice, Quantity);
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
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean createProduct(ProductDTO product)
            throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.getConnection();

            if (con != null) {
                String sql = "INSERT INTO Products("
                        + "ProductName, UnitPrice, Quantity) "
                        + "VALUES(?, ?, ?)";

                stm = con.prepareStatement(sql);
                stm.setString(1, product.getProductName());
                stm.setFloat(2, product.getUnitPrice());
                stm.setInt(3, product.getQuantity());

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

    public ProductDTO getProductByID(int productID) 
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT ProductID, ProductName, UnitPrice, Quantity "
                        + "FROM Products "
                        + "WHERE ProductID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setInt(1, productID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return new ProductDTO(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getFloat("UnitPrice"),
                            rs.getInt("Quantity")
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
    
    
}
