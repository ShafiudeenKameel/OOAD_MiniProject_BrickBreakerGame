package com.mycompany.brickebreaker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author skame
 */
import java.sql.*;

public class test {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set up the database connection
            String url = "jdbc:mysql://localhost:3306/mydatabase?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String username = "root";
            String password = "kameel123";
            connection = DriverManager.getConnection(url, username, password);

            // Test the connection by creating a statement
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 1");

            // Display a success message if the query executed successfully
            if (resultSet.next()) {
                System.out.println("Database connectivity test successful.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}