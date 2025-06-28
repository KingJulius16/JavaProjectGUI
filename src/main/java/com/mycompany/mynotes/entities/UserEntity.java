/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mynotes.entities;

/**
 *
 * @author juliu
 */
public class UserEntity {
    private int user_id;
    private String fname;
    private String mname;
    private String lname;
    private String gender;
    private String uname;
    private String password;
    
    public UserEntity(int user_id, String uname, String password){
        this.user_id = user_id;
        this.uname = uname;
        this.password = password;
    }
    
    public UserEntity(String uname, String password){
        this.uname = uname;
        this.password = password;
    }
    
    public UserEntity(String fname, String mname, String lname, String gender, String uname, String password) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.gender = gender;
        this.uname = uname;
        this.password = password;
    }
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public boolean hasBlankRegistrationFields() {
    return isBlank(fname) || isBlank(mname) || isBlank(lname) || isBlank(gender) || isBlank(uname) || isBlank(password);
    }

    public boolean hasBlankLoginFields() {
    return isBlank(uname) || isBlank(password);
    }

    private boolean isBlank(String s) {
    return s == null || s.isBlank();
    }
    
}

