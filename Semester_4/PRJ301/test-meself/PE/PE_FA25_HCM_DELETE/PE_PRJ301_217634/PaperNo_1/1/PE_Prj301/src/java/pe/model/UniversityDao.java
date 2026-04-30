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
public class UniversityDao implements Serializable {

    //-----            your code here   --------------------------------
    List<UniversityDto> university;

    public List<UniversityDto> getUniversity() {
        return university;
    }

    public void searchUniversity(String searchValue)
            throws SQLException, ClassNotFoundException {

        if (this.university == null) university = new ArrayList<>();
        else this.university.clear();

        String sql = "SELECT id, name, shortName, description, foundedYear, "
                + "address, city, region, type, "
                + "totalStudents, totalFaculties, isDraft "
                + "FROM tblUniversity "
                + "WHERE name LIKE ?";

        try (Connection con = DbUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setNString(1, "%" + searchValue + "%");

                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String name = rs.getString("name");
                        String shortName = rs.getString("shortName");
                        String description = rs.getString("description");
                        int foundedYear = rs.getInt("foundedYear");
                        String address = rs.getString("address");
                        String city = rs.getString("city");
                        String region = rs.getString("region");
                        String type = rs.getString("type");
                        int totalStudents = rs.getInt("totalStudents");
                        int totalFaculties = rs.getInt("totalFaculties");
                        Boolean isDraft = rs.getBoolean("isDraft");

                        UniversityDto dto = new UniversityDto(
                            id, name, shortName, description, foundedYear,
                            address, city, region, type,
                            totalStudents, totalFaculties, isDraft
                        );

                        this.university.add(dto);
                    }
                }
            }
        }
    }

    public UniversityDto getUniversityByID(String id)
            throws SQLException, ClassNotFoundException {

        String sql = "SELECT id, name, shortName, description, foundedYear, "
                + "address, city, region, type, "
                + "totalStudents, totalFaculties, isDraft "
                + "FROM tblUniversity "
                + "WHERE id = ?";

        try (Connection con = DbUtils.getConnection(); 
            PreparedStatement pstm = con.prepareStatement(sql)) {
            if (pstm != null) {
                pstm.setString(1, id);

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        return new UniversityDto(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("shortName"),
                            rs.getString("description"),
                            rs.getInt("foundedYear"),
                            rs.getString("address"),
                            rs.getString("city"),
                            rs.getString("region"),
                            rs.getString("type"),
                            rs.getInt("totalStudents"),
                            rs.getInt("totalFaculties"),
                            rs.getBoolean("isDraft")
                        );
                    }
                }
            }
        }
        return null;
    }

    public boolean deleteUniversity(String id)
            throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM tblUniversity "
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
