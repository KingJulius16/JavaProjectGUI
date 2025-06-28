/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mynotes.config;

    import java.sql.Connection;
    import java.sql.SQLException;
    import java.sql.Statement;

/**
 *
 * @author juliu
 */
public class DbInitializer {
    private static DbConnector dbConnector;

    public DbInitializer(DbConnector dbConnector) {
        DbInitializer.dbConnector = dbConnector;
    }
    
    public void createUsersTable() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS users (
                         user_id SERIAL PRIMARY KEY,
                         fname VARCHAR(50) NOT NULL,
                         mname VARCHAR(50) NOT NULL,
                         lname VARCHAR(50) NOT NULL,
                         gender VARCHAR(50) NOT NULL,
                         uname VARCHAR(50) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL
                     )""";
        try (Connection conn = dbConnector.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;
        }
    }
    
    
        public void createNotesTable() throws SQLException {
        String sql = """
                     CREATE TABLE IF NOT EXISTS notes (
                         note_id SERIAL PRIMARY KEY,
                         user_id INT REFERENCES users(user_id) ON DELETE CASCADE,
                         title VARCHAR(255) NOT NULL,
                         content TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                     )""";
        try (Connection conn = dbConnector.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;
        }
    }
    
}
