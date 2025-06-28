/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mynotes;

import com.mycompany.mynotes.config.DbConnector;
import com.mycompany.mynotes.config.DbInitializer;
import com.mycompany.mynotes.forms.Login;
import java.sql.SQLException;

/**
 *
 * @author juliu
 */
public class MyNotes {

    public static void main(String[] args) throws SQLException {
        DbConnector dbConnector = new DbConnector();
        dbConnector.createDatabaseIfNotExists();
        
        DbInitializer dbInitializer = new DbInitializer(dbConnector);
        dbInitializer.createUsersTable();
        dbInitializer.createNotesTable();
        
        Login loginForm = new Login();
        loginForm.setVisible(true);
    }
    
    

}
