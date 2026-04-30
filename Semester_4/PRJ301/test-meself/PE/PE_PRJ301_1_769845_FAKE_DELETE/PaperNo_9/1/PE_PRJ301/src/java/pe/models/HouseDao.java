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
public class HouseDao implements Serializable {
    List<HouseDto> house;

    public List<HouseDto> getHouse() {
        return house;
    }

    public void searchHouse(String searchValue)
            throws SQLException, ClassNotFoundException {

        if (this.house == null) house = new ArrayList<>();
        else this.house.clear();

        String sql = "SELECT id, name, description, price, size, status "
                + "FROM tblHouse "
                + "WHERE name LIKE ?";

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
                        float size = rs.getFloat("size");
                        boolean status = rs.getBoolean("status");

                        HouseDto dto = new HouseDto(id, name, description, price, size, status);

                        this.house.add(dto);
                    }
                }
            }
        }
    }
    
    public boolean removeHouse(String id)
            throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tblHouse SET status = 0 "
                + "WHERE id = ?";

        try (Connection con = DBUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setString(1, id);
                return pstm.executeUpdate() > 0;
            }
        }
        return false;
    }
}