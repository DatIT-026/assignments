/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DBUtils;

/**
 *
 * @author datto
 */
public class ProductDao implements Serializable {

    List<ProductDto> product;

    public List<ProductDto> getProduct() {
        return product;
    }

    public void searchProduct(String searchValue)
            throws SQLException, ClassNotFoundException {

        if (this.product == null) product = new ArrayList<>();
        else this.product.clear();

        String sql = "SELECT id, name, description, price, size, status "
                + "FROM tblProduct "
                + "WHERE description LIKE ?";

        try (Connection con = DBUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setString(1, "%" + searchValue + "%");

                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        float price = rs.getFloat("price");
                        String size = rs.getString("size");
                        boolean status = rs.getBoolean("status");

                        ProductDto dto = new ProductDto(id, name, description, price, size, status);

                        this.product.add(dto);
                    }
                }
            }
        }
    }
    
    public boolean updateProduct(ProductDto dto)
            throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tblProduct SET name = ?, description = ?, price = ?, size = ? "
                + "WHERE id = ?";

        try (Connection con = DBUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setString(1, dto.getName());
                pstm.setString(2, dto.getDescription());
                pstm.setFloat(3, dto.getPrice());
                pstm.setString(4, dto.getSize());
                
                pstm.setString(5, dto.getId());
                
                return pstm.executeUpdate() > 0;
            }
        }
        return false;
    }

}
