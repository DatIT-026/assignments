/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.io.Serializable;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class UniversityDto implements Serializable {
    //-----            your code here   --------------------------------
    private String id;
    private String name;
    private String shortName;
    private String description;
    private int foundedYear;
    private String address;
    private String city;
    private String region;
    private String type;
    private int totalStudents;
    private int totalFaculties;
    private boolean isDraft;

    public UniversityDto() {
    }

    public UniversityDto(String id, String name, String shortName, String description, int foundedYear, String address, String city, String region, String type, int totalStudents, int totalFaculties, boolean isDraft) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.foundedYear = foundedYear;
        this.address = address;
        this.city = city;
        this.region = region;
        this.type = type;
        this.totalStudents = totalStudents;
        this.totalFaculties = totalFaculties;
        this.isDraft = isDraft;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    /**
     * @return the foundedYear
     */
    public int getFoundedYear() {
        return foundedYear;
    }

    /**
     * @param foundedYear the foundedYear to set
     */
    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the totalStudents
     */
    public int getTotalStudents() {
        return totalStudents;
    }

    /**
     * @param totalStudents the totalStudents to set
     */
    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    /**
     * @return the totalFaculties
     */
    public int getTotalFaculties() {
        return totalFaculties;
    }

    /**
     * @param totalFaculties the totalFaculties to set
     */
    public void setTotalFaculties(int totalFaculties) {
        this.totalFaculties = totalFaculties;
    }

    /**
     * @return the isDraft
     */
    public boolean isIsDraft() {
        return isDraft;
    }

    /**
     * @param isDraft the isDraft to set
     */
    public void setIsDraft(boolean isDraft) {
        this.isDraft = isDraft;
    }

    
}
