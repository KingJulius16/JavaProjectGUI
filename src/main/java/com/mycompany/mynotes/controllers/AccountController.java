/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mynotes.controllers;
import com.mycompany.mynotes.config.DbConnector;
import com.mycompany.mynotes.entities.UserEntity;
import com.mycompany.mynotes.utilities.UserUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author juliu
 */
public class AccountController {
    private final DbConnector dbConnector;

    public AccountController() {
        this.dbConnector = new DbConnector(); 
    }

    
    public boolean registerUser(UserEntity usr) throws SQLException {
        String insertQuery = "INSERT INTO users (fname, mname, lname, gender, uname, password) VALUES (?, ?, ?, ?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(usr.getPassword(), BCrypt.gensalt());

        try (Connection conn = dbConnector.getConnection()) {

            if (UserUtilities.unameExists(conn, usr.getUname())) {
                return false; 
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, usr.getFname());
                insertStmt.setString(2, usr.getMname());
                insertStmt.setString(3, usr.getLname());
                insertStmt.setString(4, usr.getGender()); 
                insertStmt.setString(5, usr.getUname());
                insertStmt.setString(6, hashedPassword);
                insertStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    
    
    public UserEntity login(UserEntity inputUser) throws SQLException {
        String loginQuery = "SELECT user_id, password FROM users WHERE uname = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(loginQuery)) {

            stmt.setString(1, inputUser.getUname());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");

                if (BCrypt.checkpw(inputUser.getPassword(), hashedPassword)) {
                    int userId = rs.getInt("user_id");
                    UserEntity user = new UserEntity(inputUser.getUname(), inputUser.getPassword());
                    user.setUser_id(userId);
                    user.setUname(inputUser.getUname());
                    return user;
                }
            }
        }

        return null; 
    } 
    


    public int getUserId(String uname) throws SQLException {
        String sql = "SELECT user_id FROM users WHERE uname = ?";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            } else {
                return -1; 
            }
        }
    }
    
}
