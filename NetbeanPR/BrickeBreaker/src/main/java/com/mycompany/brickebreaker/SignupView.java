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

public class SignupView extends JFrame {

    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton signupButton, cancelButton;
    
    public SignupView() {
        // Initialize the components
        nameLabel = new JLabel("Name:");
        passwordLabel = new JLabel("Password:");
        nameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        signupButton = new JButton("Signup");
        cancelButton = new JButton("Cancel");
        
        // Initialize the panels
        JPanel mainPanel = new JPanel(new GridLayout(2, 2));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        // Add components to the main panel
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
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
    
    public String getName() {
        return nameField.getText();
    }
    
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    
    public void addSignupListener(ActionListener listener) {
        signupButton.addActionListener(listener);
    }
    
    public void addCancelListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
    
    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
    
    public void close() {
        dispose();
    }
    
}
