/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DBUtils;

/**
 *
 * @author Miniks
 */
public class ComponentDao implements Serializable {
    List<ComponentDto> comp;

    public List<ComponentDto> getComponent() {
        return comp;
    }

    public void searchComponent(String searchValue)
            throws SQLException, ClassNotFoundException {

        if (this.comp == null) comp = new ArrayList<>();
        else this.comp.clear();

        String sql = "SELECT * FROM tblComponents "
                + "WHERE itemName LIKE ? OR category LIKE ?";

        try (Connection con = DBUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setString(1, "%" + searchValue + "%");
                pstm.setString(2, "%" + searchValue + "%");

                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        String itemID = rs.getString("itemID");
                        String itemName = rs.getString("itemName");
                        String category = rs.getString("category");
                        String manufacturer = rs.getString("manufacturer");
                        String specification = rs.getString("specification");
                        String warehouseLocation = rs.getString("warehouseLocation");
                        Date importDate = rs.getDate("importDate");
                        int quantity = rs.getInt("quantity");
                        float unitPrice = rs.getFloat("unitPrice");
                        int warrantyMonth = rs.getInt("warrantyMonth");
                        boolean isAvailable = rs.getBoolean("isAvailable");
                        String note = rs.getString("note");

                        ComponentDto dto = new ComponentDto(
                                itemID, itemName, category, manufacturer, 
                                specification, warehouseLocation, importDate, quantity, 
                                unitPrice, warrantyMonth, isAvailable, note
                        );
                        this.comp.add(dto);
                    }
                }
            }
        }
    }
    
    public boolean updateComponent(ComponentDto dto)
            throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tblComponents SET itemName = ?, category = ?, manufacturer = ?, "
                + "specification = ?, warehouseLocation = ?, importDate = ?, quantity = ?, "
                + "unitPrice = ?, warrantyMonth = ?, isAvailable = ?, note = ? "
                + "WHERE itemID = ?";

        try (Connection con = DBUtils.getConnection(); 
             PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setString(1, dto.getItemName());
                pstm.setString(2, dto.getCategory());
                pstm.setString(3, dto.getManufacturer());
                pstm.setString(4, dto.getSpecification());
                pstm.setString(5, dto.getWarehouseLocation());
                pstm.setDate(6, dto.getImportDate());
                pstm.setInt(7, dto.getQuantity());
                pstm.setFloat(8, dto.getUnitPrice());
                pstm.setInt(9, dto.getWarrantyMonth());
                pstm.setBoolean(10, dto.isIsAvailable());
                pstm.setString(11, dto.getNote());
                pstm.setString(12, dto.getItemID());
                
                return pstm.executeUpdate() > 0;
            }
        }
        return false;
    }
}