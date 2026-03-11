/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBUtils;

/**
 *
 * @author Admin
 */
public class PetDao implements Serializable {
    List<PetDto> pet;

    public List<PetDto> getPets() {
        return pet;
    }

    public void searchPet(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT petID, petName, breed, age, price, description "
                        + "FROM Pets "
                        + "WHERE petName LIKE ? OR breed LIKE ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, "%" + searchValue + "%");
                pstm.setString(2, "%" + searchValue + "%");

                rs = pstm.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("petID");
                    String name = rs.getString("petName");
                    String breed = rs.getString("breed");
                    int age = rs.getInt("age");
                    float price = rs.getFloat("price");
                    String description = rs.getString("description");

                    PetDto dto = new PetDto(id, name, breed, age, price, description);

                    if (this.pet == null) {
                        pet = new ArrayList<>();
                    }
                    this.pet.add(dto);
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

    public boolean updatePet(PetDto dto)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement pstm = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Pets SET "
                        + "petName = ?, breed = ?, age = ?, price = ?, description = ? "
                        + "WHERE petID = ?";

                pstm = con.prepareStatement(sql);
                pstm.setString(1, dto.getPetName());
                pstm.setString(2, dto.getBreed());
                pstm.setInt(3, dto.getAge());
                pstm.setFloat(4, dto.getPrice());
                pstm.setString(5, dto.getDescription());
                pstm.setInt(6, dto.getPetID());

                int row = pstm.executeUpdate();
                if (row > 0) return true;
            }
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return false;
    }

}
