/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickebreaker;

/**
 *
 * @author skame
 */
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.*;

public class SignupController {
    
    private SignupView view;
    private SignupModel model;
    
    public SignupController(SignupModel model,SignupView view) {
        this.view = view;
        this.model = model;
        
        // Add action listeners to the view
        view.addSignupListener(new SignupListener());
        view.addCancelListener(new CancelListener());
    }
    
    class SignupListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = view.getName();
            String password = view.getPassword();
            
            if (name.isEmpty() || password.isEmpty()) {
                view.showMessage("Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Insert the user into the database
                    model.signUp(name, password);
                    
                    // Display a success message
                    view.showMessage("Signup successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Close the view
                    view.close();
                    
                    // Open the login view
                    LoginView loginView = new LoginView();
                    LoginModel lm=new LoginModel();
//                    SignupModel userModel = new SignupModel();
                    LoginController loginController = new LoginController(lm,loginView);
                } catch (SQLException ex) {
                    view.showMessage("Signup failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.close();
        }
    }
    
}
