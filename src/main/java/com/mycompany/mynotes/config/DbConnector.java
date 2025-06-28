/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mynotes.config;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    import java.sql.Statement;
/**
 *
 * @author juliu
 */
public class DbConnector {
    
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/";
    private static final String JDBC_USER = "postgres"; 
    private static final String JDBC_PASSWORD = "123456789"; 
    private static final String DEFAULT_DB = "postgres"; // 
    private static final String NEW_DATABASE_NAME = "db_mynotes"; 
    private static final String NEW_DATABASE_URL = JDBC_URL + NEW_DATABASE_NAME;
    
    public DbConnector() {
        try {
            createDatabaseIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void createDatabaseIfNotExists() throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL + DEFAULT_DB, JDBC_USER, JDBC_PASSWORD);
            Statement statement = connection.createStatement()) {
            String createDatabaseSQL = "CREATE DATABASE " + NEW_DATABASE_NAME;
            statement.executeUpdate(createDatabaseSQL);
            System.out.println("Database " + NEW_DATABASE_NAME + " created successfully.");
        } catch (SQLException e) {
            
            if ("42P04".equals(e.getSQLState())) { 
                System.out.println("Database " + NEW_DATABASE_NAME + " already exists.");
            } else {
                throw e;
            }
        }
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(NEW_DATABASE_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
