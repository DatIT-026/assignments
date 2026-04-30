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
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class FashionDao implements Serializable {
    //-----            your code here   --------------------------------
    List<FashionDto> fashion;

    public List<FashionDto> getFashion() {
        return fashion;
    }

    public void searchFashion(String searchValue)
            throws SQLException, ClassNotFoundException {

        if (this.fashion == null) fashion = new ArrayList<>();
        else this.fashion.clear();

        String sql = "SELECT id, name, description, price, size, status "
                + "FROM tblFashion "
                + "WHERE name LIKE ?";

        try (Connection con = DbUtils.getConnection(); 
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

                        FashionDto dto = new FashionDto(id, name, description, price, size, status);

                        this.fashion.add(dto);
                    }
                }
            }
        }
    }

    public FashionDto getFashionByID(String id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT id, name, description, price, size, status "
                + "FROM tblFashion "
                + "WHERE id = ?";

        try (Connection con = DbUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setString(1, id);

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        return new FashionDto(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getFloat("price"),
                            rs.getString("size"),
                            rs.getBoolean("status")
                        );
                    }
                }
            }
        }
        return null;
    }
    
    public boolean updateFashion(FashionDto dto)
            throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tblFashion SET name = ?, description = ?, price = ?, size = ?, status = ? "
                + "WHERE id = ?";

        try (Connection con = DbUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setString(1, dto.getName());
                pstm.setString(2, dto.getDescription());
                pstm.setFloat(3, dto.getPrice());
                pstm.setString(4, dto.getSize());
                pstm.setBoolean(5, dto.isStatus());
                
                pstm.setString(6, dto.getId());
                
                return pstm.executeUpdate() > 0;
            }
        }
        return false;
    }
}

