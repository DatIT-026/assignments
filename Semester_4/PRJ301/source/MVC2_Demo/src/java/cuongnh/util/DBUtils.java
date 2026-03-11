/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cuongnh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBUtils {
    public static Connection getConnection() 
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        
        //1. Load driver
        //1.1. add driver
        //1.2. write code to add driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        //2. Create Connection string to connect container and db
        //Syntax: protocol:DBMS_NAME://ip:port;databaseName=DB_NAME
        String url = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1922";
        
        //3.Open connection using Driver Manager
        final String name = "sa";
        final String pass = "12345";
        con = DriverManager.getConnection(url, name, pass);
        
        return con;
    }
}
