/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickebreaker;

/**
 *
 * @author skame
 */
import java.sql.*;

public class SignupModel {

    private Connection connection;
    
    public SignupModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "kameel123");
        } catch (SQLException ex) {
            System.out.println("Database connection failed: " + ex.getMessage());
        }
    }
    
    public boolean signUp(String name, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, password);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Signup failed: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean login(String name, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login failed: " + ex.getMessage());
        }
        return false;
    }
    
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Failed to close database connection: " + ex.getMessage());
        }
    }
    
}