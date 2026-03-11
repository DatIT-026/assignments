/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

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
public class RoomForRentDao {

    //-----            your code here   --------------------------------
    List<RoomForRentDTO> room;

    public List<RoomForRentDTO> getRooms() {
        return room;
    }

    public void searchRooms(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DbUtils.getConnection();
            if (con != null) {
                String sql = "SELECT id, title, price, location, description, postedDate, status, username "
                        + "FROM RoomForRent "
                        + "WHERE location LIKE ? AND status != -2";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    double price = rs.getDouble("price");
                    String location = rs.getString("location");
                    String description = rs.getString("description");
                    String postedDate = rs.getString("postedDate");
                    int status = rs.getInt("status");
                    String username = rs.getString("username");

                    RoomForRentDTO dto = new RoomForRentDTO(id, title, price, location, description, postedDate, status, username);

                    if (this.room == null) {
                        room = new ArrayList<>();
                    }
                    this.room.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteRoom(String username)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DbUtils.getConnection();
            if (con != null) {
                String sql = "Delete From RoomForRent "
                        + "Where username = ?";
                pstm = con.prepareStatement(sql);
                pstm.setString(1, username);
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
