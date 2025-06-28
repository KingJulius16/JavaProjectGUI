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
public class NoteUtilities {
    public static boolean titleExists(Connection conn, String title) throws SQLException {
        String checktitleQuery = "SELECT COUNT(*) FROM notes WHERE title = ?";
        try (PreparedStatement stmt = conn.prepareStatement(checktitleQuery)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }
}

