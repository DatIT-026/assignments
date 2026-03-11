/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab02_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author datto
 */
public class ItemDAO {
    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SampleDB";
        
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        
        return con;
    }
    
    public void printItems() throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = getConnection();
            String sql = "SELECT ItemID, ItemName, Quantity "
                        + "FROM Items";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                System.out.format("%-10s %-15s %5d %n", 
                        rs.getString("ItemID"),
                        rs.getString("ItemName"),
                        rs.getInt("Quantity"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
    public void AddNewItems(String itemID, String itemName, int quantity) 
            throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            String sql = "INSERT Items(ItemID, ItemName, Quantity) "
                        + "VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, itemID);
            ps.setString(2, itemName);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            System.out.println("Item has been added.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
    public void RemoveItem(String itemID) 
            throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            String sql = "DELETE Items "
                        + "WHERE ItemID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, itemID);
            ps.executeUpdate();
            System.out.println("Item has been removed.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
    
    public void UpdateItem(String itemID, String itemName, int quantity) 
            throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            String sql = "UPDATE Items SET ItemName = ?, Quantity = ? "
                        + "WHERE ItemID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, itemName);
            ps.setInt(2, quantity);
            ps.setString(3, itemID);
            ps.executeUpdate();
            System.out.println("Item has been updated.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (ps != null) ps.close();
            if (con != null) con.close();
        }
    }
}
