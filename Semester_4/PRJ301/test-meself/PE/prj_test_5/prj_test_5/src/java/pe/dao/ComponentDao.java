/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.dto.ComponentDto;
import pe.utils.DBUtils;

/**
 *
 * @author datto
 */
public class ComponentDao implements Serializable {

    List<ComponentDto> comp;

    public List<ComponentDto> getComponentList() {
        return comp;
    }

    public void searchComponent(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT itemID, itemName, category, manufacturer, specification, "
                        + "warehouseLocation, importDate, quantity, unitPrice, warrantyMonth, "
                        + "isAvailable, note "
                        + "FROM tblComponents "
                        + "WHERE itemName LIKE ? OR category LIKE ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, "%" + searchValue + "%");
                pstm.setString(2, "%" + searchValue + "%");

                rs = pstm.executeQuery();

                while (rs.next()) {
                    ComponentDto dto = new ComponentDto(
                            rs.getString("itemID"),
                            rs.getString("itemName"),
                            rs.getString("category"),
                            rs.getString("manufacturer"),
                            rs.getString("specification"),
                            rs.getString("warehouseLocation"),
                            rs.getDate("importDate"),
                            rs.getInt("quantity"),
                            rs.getDouble("unitPrice"),
                            rs.getInt("warrantyMonth"),
                            rs.getBoolean("isAvailable"),
                            rs.getString("note")
                    );

                    if (this.comp == null) {
                        this.comp = new ArrayList<>();
                    }
                    this.comp.add(dto);
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

    public ComponentDto getComponentByID(String itemID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT itemID, itemName, category, manufacturer, specification, "
                        + "warehouseLocation, importDate, quantity, unitPrice, warrantyMonth, "
                        + "isAvailable, note "
                        + "FROM tblComponents "
                        + "WHERE itemID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, itemID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    return new ComponentDto(
                            rs.getString("itemID"),
                            rs.getString("itemName"),
                            rs.getString("category"),
                            rs.getString("manufacturer"),
                            rs.getString("specification"),
                            rs.getString("warehouseLocation"),
                            rs.getDate("importDate"),
                            rs.getInt("quantity"),
                            rs.getDouble("unitPrice"),
                            rs.getInt("warrantyMonth"),
                            rs.getBoolean("isAvailable"),
                            rs.getString("note")
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
    
    public boolean deleteComponent(String itemID)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE tblComponents SET isAvailable = 0, quantity = 0 WHERE itemID = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, itemID);
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
}
