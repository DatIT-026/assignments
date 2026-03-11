/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firehelperedu.registration;

import firehelperedu.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author datto
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement stm = null; // bien de dung cau lenh sql
        ResultSet rs = null; // bien chua ket qua tra ve

        boolean result = false;

        try {
            // 1. connect database
            con = DBUtils.getConnection();

            // 2. Model queries data from database
            // 2.1. Create sql string
            String sql = "SELECT userName "
                    + "FROM studentList "
                    + "WHERE userName = ? "
                    + "AND password = ?";

            // 2.2. Load sql string
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);

            // 2.3. Execute queries
            rs = stm.executeQuery();

            // 3. Model load data from db to model
            // 4. Model process and return result
            if (rs.next()) {
                result = true;
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

        return result;
    }

    List<RegistrationDTO> courses;

    public List<RegistrationDTO> getCourses() {
        return courses;
    }

    public void searchCourse(String searchValue)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null; // bien de dung cau lenh sql
        ResultSet rs = null; // bien chua ket qua tra ve

        try {
            // 1. connect database
            con = DBUtils.getConnection();

            if (con != null) {
                // 2. Model queries data from database
                // 2.1. Create sql string
                String sql = "SELECT courseID, courseName, credits, description "
                        + "FROM courseList "
                        + "WHERE courseID LIKE ? " + "OR courseName LIKE ?";

                // 2.2. Load sql string
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setString(2, "%" + searchValue + "%");

                // 2.3. Execute queries
                rs = stm.executeQuery();

                while (rs.next()) {
                    // 2.3.1. Model load data from db
                    String courseID = rs.getString("courseID");
                    String courseName = rs.getString("courseName");
                    int credits = rs.getInt("credits");
                    String description = rs.getString("description");
                    RegistrationDTO dto = new RegistrationDTO(courseID, courseName, credits, description);
                    
                    // 2.3.2. Model store data from db to itself
                    if (this.courses == null) courses = new ArrayList<>();
                    
                    this.courses.add(dto);
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
}
