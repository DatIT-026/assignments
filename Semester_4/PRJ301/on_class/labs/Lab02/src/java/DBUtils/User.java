/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBUtils;

/**
 *
 * @author datto
 */
public class User {
    private String userName;
    private String password;
    private String LastName;
    private boolean isAdmin;
    
    public User() {
        this.userName = null;
        this.password = null;
        this.LastName = null;
        this.isAdmin = false;
    }

    public User(String userName, String password, String LastName, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.LastName = LastName;
        this.isAdmin = isAdmin;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the lastname
     */
    public String getFullName() {
        return LastName;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setFullName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    @Override
    public String toString() {
        return String.format("UserName:%s, Password:%s, LastName:%s, isAdmin:%b", 
                userName, password, LastName, isAdmin);
    }
}
