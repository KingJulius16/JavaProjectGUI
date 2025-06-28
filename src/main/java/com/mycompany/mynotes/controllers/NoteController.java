/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mynotes.controllers;
import com.mycompany.mynotes.config.DbConnector;
import com.mycompany.mynotes.entities.NoteEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juliu
 */
public class NoteController {
    private final DbConnector dbConnector;

    public NoteController() {
        this.dbConnector = new DbConnector(); 
    }

    public void addNote(NoteEntity note) throws SQLException {
        String query = "INSERT INTO notes (user_id, title, content, created_at) VALUES (?, ?, ?, NOW())";

        try (Connection conn = dbConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, note.getUserId());
            stmt.setString(2, note.getTitle());
            stmt.setString(3, note.getContent());

            stmt.executeUpdate();
        }
    }

    
    
public List<NoteEntity> getNotesByUserId(int userId) throws SQLException {
    List<NoteEntity> notesList = new ArrayList<>();
    String sql = "SELECT note_id, title, content FROM notes WHERE user_id = ?";

    try (Connection conn = dbConnector.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            NoteEntity note = new NoteEntity();
            note.setNoteId(rs.getInt("note_id"));
            note.setTitle(rs.getString("title"));
            note.setContent(rs.getString("content"));
            notesList.add(note);
        }
    }

    return notesList;
}
    
    
    public void updateNote(int noteId, String newTitle, String newContent) throws SQLException {
    String sql = "UPDATE notes SET title = ?, content = ? WHERE note_id = ?";

    try (Connection conn = dbConnector.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newTitle);
        stmt.setString(2, newContent);
        stmt.setInt(3, noteId);
        stmt.executeUpdate();
    }
    }
    
    
    public void deleteNote(int noteId) throws SQLException {
    String sql = "DELETE FROM notes WHERE note_id = ?";

    try (Connection conn = dbConnector.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, noteId);
        stmt.executeUpdate();
    }
    }
}