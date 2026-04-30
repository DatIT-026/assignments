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
public class ItemDao implements Serializable {

    //-----            your code here   --------------------------------
    List<ItemDto> item;

    public List<ItemDto> getItem() {
        return item;
    }

    public void searchItem(float minPrice, float maxPrice)
            throws SQLException, ClassNotFoundException {

        if (this.item == null) item = new ArrayList<>();
        else this.item.clear();

        String sql = "SELECT id, name, price, quantity "
                + "FROM tblitems "
                + "WHERE price >= ? AND price <= ?";
        
        try (Connection con = DbUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setFloat(1, minPrice);
                pstm.setFloat(2, maxPrice);
                
                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String name = rs.getString("name");
                        float price = rs.getFloat("price");
                        int quantity = rs.getInt("quantity");

                        ItemDto dto = new ItemDto(id, name, price, quantity);

                        this.item.add(dto);
                    }
                }
            }
        }
    }

    public boolean removeItem(String id)
            throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM tblitems "
                + "WHERE id = ?";

        try (Connection con = DbUtils.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setString(1, id);
                return pstm.executeUpdate() > 0;
            }
        }
        return false;
    }
}