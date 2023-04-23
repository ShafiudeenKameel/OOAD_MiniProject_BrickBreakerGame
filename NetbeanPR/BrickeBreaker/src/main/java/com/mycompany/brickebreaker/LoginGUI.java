package com.mycompany.brickebreaker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author skame
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import java.io.IOException;



public class LoginGUI extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;
    private JButton LeaderBoardButton;

    public LoginGUI() {
        super("Login");

        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        signupButton = new JButton("Sign Up");
        signupButton.addActionListener(this);
        
        LeaderBoardButton = new JButton("LeaderBoard");
        LeaderBoardButton.addActionListener(this);
        

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(titleLabel);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);
        panel.add(LeaderBoardButton);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                 
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "kameel123");
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    dispose(); // Close the login window
                    PreparedStatement p = conn.prepareStatement("INSERT INTO scores (username) VALUES (?);");
                    p.setString(1, username);
                    int numrows=p.executeUpdate();
                    p.close();
                    conn.close();
                    JFrame obj = new JFrame();
                    Gameplay game = new Gameplay();
                    obj.setBounds(10,10,700,600);
                    obj.setTitle("BRICK BREAKER");
                    obj.setResizable(false);
                    obj.setVisible(true);
                    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    obj.add(game);

//                    try {
//                        ProcessBuilder builder = new ProcessBuilder("java", "Main");
//                        builder.redirectErrorStream(true); // Redirect error stream to standard output
//                        Process process = builder.start();
//                        JFrame obj = new JFrame();
//		Gameplay game = new Gameplay();
//		obj.setBounds(10,10,700,600);
//		obj.setTitle("BRICK BREAKER");
//		obj.setResizable(false);
//		obj.setVisible(true);
//		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		obj.add(game);
//
//                    } catch (IOException ex) {
//                        JOptionPane.showMessageDialog(this, "Error opening Main program: " + ex.getMessage());
//                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }

                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage());
            }
        } else if (e.getSource() == signupButton) {
            SignupGUI signup = new SignupGUI();
            dispose(); // Close the login window
        }else if (e.getSource()==LeaderBoardButton){
            LeaderBoard lb= new LeaderBoard();
            lb.setVisible(true);
            dispose();
        }
        
    }
}

