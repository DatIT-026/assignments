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
import pe.dto.PaintingDTO;
import pe.util.DBUtil;

/**
 *
 * @author datto
 */
public class PaintingDAO implements Serializable {

    List<PaintingDTO> paints;

    public List<PaintingDTO> getPaints() {
        return paints;
    }

    public void searchPainting(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM tblPaintings "
                        + "WHERE paintingID LIKE ? OR creator LIKE ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, "%" + searchValue + "%");
                pstm.setString(2, "%" + searchValue + "%");

                rs = pstm.executeQuery();

                while (rs.next()) {
                    String paintingID = rs.getString("paintingID");
                    String description = rs.getString("description");
                    String creator = rs.getString("creator");
                    int height = rs.getInt("height");
                    int width = rs.getInt("width");
                    double price = rs.getDouble("price");
                    boolean isAvailable = rs.getBoolean("isAvailable");

                    PaintingDTO dto = new PaintingDTO(paintingID, description, creator, height, width, price, isAvailable);

                    if (this.paints == null) {
                        paints = new ArrayList<>();
                    }
                    this.paints.add(dto);
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

    public boolean updatePainting(PaintingDTO dto)
            throws SQLException, ClassNotFoundException {

        String sql = "UPDATE tblPainting SET "
                + "creator = ?, description = ?, height = ?, width = ?, price = ? "
                + "WHERE id = ?";

        try (Connection con = DBUtil.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, dto.getCreator());
            pstm.setString(2, dto.getDescription());
//            pstm.setInt(3, dto.getHeight());
//            pstm.setInt(4, dto.getWidth());
//            pstm.setDouble(5, dto.getPrice());
//            pstm.setString(6, dto.getId());

            int row = pstm.executeUpdate();
            if (row > 0) {
                return true;
            }
        }

        return false;
    }
}
