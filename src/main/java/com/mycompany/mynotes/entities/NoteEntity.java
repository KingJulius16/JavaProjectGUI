/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mynotes.entities;

/**
 *
 * @author juliu
 */
public class NoteEntity {
    private int noteId;
    private int userId;
    private String title;
    private String content;
    
    public NoteEntity() {
    }
    
    public NoteEntity(int userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
    
    public NoteEntity(int noteId, int userId, String title, String content) {
        this.noteId = noteId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
    
    public int getNoteId(){
        return noteId;
    }
    
    public void setNoteId(int noteId){
        this.noteId = noteId;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }   
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
   
    public boolean hasBlankNotesFields() {
    return isBlank(title) || isBlank(content);
    }

    private boolean isBlank(String s) {
    return s == null || s.isBlank();
    }
    
}

