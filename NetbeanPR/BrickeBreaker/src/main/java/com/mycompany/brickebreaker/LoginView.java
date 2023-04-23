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

public class LoginView extends JFrame {
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JButton leaderboardButton;

    public LoginView() {
        super("Login");

        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        signupButton = new JButton("Sign Up");
        leaderboardButton = new JButton("Leaderboard");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(titleLabel);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);
        panel.add(leaderboardButton);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addSignupListener(ActionListener listener) {
        signupButton.addActionListener(listener);
    }

    public void addLeaderboardListener(ActionListener listener) {
        leaderboardButton.addActionListener(listener);
    }

    public void showLoginFailedMessage() {
        JOptionPane.showMessageDialog(this, "Invalid username or password.");
    }

    public void showLoginSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Login successful!");
    }
    public void showMessage(String Message){
        JOptionPane.showMessageDialog(this,Message);
    }
    public JButton getLoginButton(){
        return loginButton;
    }
    public JButton getSignupButton(){
        return signupButton;
    }
    public JButton getLeaderBoardButton(){
        return leaderboardButton;
    }
    public void close() {
        dispose();
    }
}