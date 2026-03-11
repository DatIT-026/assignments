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
public class CoursesDAO implements Serializable {
    List<CoursesDTO> courses;
    
    public List<CoursesDTO> getCourses() {
        return courses;
    }
    
    public void searchCourses(String searchValue) 
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.getConnection(); // connect db
            
            // model queries data from db
            if (con != null) {
                // create sql string
                String sql = "SELECT courseID, courseName, credits, description "
                            + "FROM courseList "
                            + "WHERE courseID LIKE ? " 
                            + "OR courseName LIKE ?";
                // load sql into the db
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue +  "%");
                stm.setString(2, "%" + searchValue +  "%");
                rs = stm.executeQuery(); // execute queries
                
                if (rs.next()) {
                    // model loads data from db to model
                    // model process and return result
                    String course_id = rs.getString("courseID");
                    String course_name = rs.getString("courseName");
                    String credits = rs.getString("credits");
                    String description = rs.getString("description");
                    CoursesDTO dto = new CoursesDTO(course_id, course_name, credits, description);
                    
                    if (this.courses == null) courses = new ArrayList<>();
                    this.courses.add(dto);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (con != null) con.close();
        }
    }
}
