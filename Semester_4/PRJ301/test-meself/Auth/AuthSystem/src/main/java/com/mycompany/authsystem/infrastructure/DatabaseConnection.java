/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.authsystem.infrastructure;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author datto
 */
public class DatabaseConnection {
    public static Connection getConnection() 
            throws NamingException, SQLException {
        Connection con = null;
        
        Context ctx = new InitialContext();
        Context envctx = (Context) ctx.lookup("java:comp/env");
        
        DataSource ds = (DataSource) envctx.lookup("AuthSystem_DBCon");
        con = ds.getConnection();
        
        return con;
    }
    
    public static void closeConnection(Connection con) {
        if (con != null) {
            try {
                if (!con.isClosed()) con.close();
            } catch (SQLException ex) {
                System.err.println("Error closing connection: " + ex.getMessage());
            }
        }
    }
    
}
