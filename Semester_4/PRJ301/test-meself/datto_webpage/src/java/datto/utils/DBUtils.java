/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datto.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author datto
 */
public class DBUtils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        
        // 1. load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        // 2. Create connection string
        String url = "jdbc:sqlserver://localhost:1433;databaseName=LopHocVuiTuoiCuaNTT";
                
        // 3. Open connection
        final String adname = "sa";
        final String adpass = "12345";
        con = DriverManager.getConnection(url, adname, adpass);
        
        return con;
    }
}
