/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firehelperedu.registration;

import java.io.Serializable;

/**
 *
 * @author datto
 */
public class CoursesDTO implements Serializable {
    private String course_id;
    private String course_name;
    private String credits;
    private String description;

    public CoursesDTO() {}

    public CoursesDTO(String course_id, String course_name, String credits, String description) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.credits = credits;
        this.description = description;
    }

    /**
     * @return the course_id
     */
    public String getCourse_id() {
        return course_id;
    }

    /**
     * @param course_id the course_id to set
     */
    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    /**
     * @return the course_name
     */
    public String getCourse_name() {
        return course_name;
    }

    /**
     * @param course_name the course_name to set
     */
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    /**
     * @return the credits
     */
    public String getCredits() {
        return credits;
    }

    /**
     * @param credits the credits to set
     */
    public void setCredits(String credits) {
        this.credits = credits;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
