/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datto.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author datto
 */
public class DBUtils implements Serializable{
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=LopHocVuiTuoiCuaNTT";
        
        final String adName = "sa";
        final String adPass = "12345";
        
        con = DriverManager.getConnection(url, adName, adPass);
        return con;
    }
}
