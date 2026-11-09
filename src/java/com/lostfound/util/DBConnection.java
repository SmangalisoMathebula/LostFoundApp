package com.lostfound.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

   
    public static Connection getConnection() {
        Connection con = null;

        try {
           
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            String url = "jdbc:mysql://localhost:3306/lostfound_db?useSSL=false&allowPublicKeyRetrieval=true";
            String username = "root";                        
            String password = "your_mysql_password";           

            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return con;
    }
}