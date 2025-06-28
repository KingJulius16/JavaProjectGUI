/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mynotes.utilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author juliu
 */
public class UserUtilities {
    public static boolean unameExists(Connection conn, String uname) throws SQLException {
        String checkEmailQuery = "SELECT COUNT(*) FROM users WHERE uname = ?";
        try (PreparedStatement stmt = conn.prepareStatement(checkEmailQuery)) {
            stmt.setString(1, uname);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }
}



