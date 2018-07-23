package com.BDConsoleApp.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSingleton {
    private static Connection connection = null;
    private static final ConnectionSingleton INSTANCE = new ConnectionSingleton();
    private ConnectionSingleton(){
        try {
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream("resourses/db.properties");
            properties.load(inputStream);

            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String hostname = properties.getProperty("hostname");
            String dbname = properties.getProperty("dbname");
            String dburl = "jdbc:mysql://" + hostname + "/" + dbname;

            connection = DriverManager.getConnection(dburl, username, password);
            System.out.println("Database Connected!");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }
}
