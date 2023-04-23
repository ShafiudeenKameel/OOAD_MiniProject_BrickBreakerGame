/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickebreaker;

/**
 *
 * @author skame
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignupGUI extends JFrame implements ActionListener {
    
    private JLabel nameLabel, emailLabel, passwordLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JButton signupButton, cancelButton;
    private JPanel mainPanel, buttonPanel;
    
    public SignupGUI() {
        // Initialize the components
        nameLabel = new JLabel("Name:");
//        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        signupButton = new JButton("Signup");
        cancelButton = new JButton("Cancel");
        
        // Add action listeners to the buttons
        signupButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        // Initialize the panels
        mainPanel = new JPanel(new GridLayout(3, 2));
        buttonPanel = new JPanel(new FlowLayout());
        
        // Add components to the main panel
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
//        mainPanel.add(emailLabel);
//        mainPanel.add(emailField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        
        // Add buttons to the button panel
        buttonPanel.add(signupButton);
        buttonPanel.add(cancelButton);
        
        // Add panels to the frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Set the frame properties
        setTitle("Signup");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String name = nameField.getText();
//            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            
            if (name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // Connect to the database
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "kameel123");
                    
                    // Prepare the SQL statement
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");
                    stmt.setString(1, name);
//                    stmt.setString(2, email);
                    stmt.setString(2, password);
                    
                    // Execute the SQL statement
                    stmt.executeUpdate();
                    
                    // Display a success message
                    JOptionPane.showMessageDialog(this, "Signup successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Close the database connection and dispose the frame
                    conn.close();
                    dispose();
                    LoginGUI login = new LoginGUI();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Signup failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }
}
