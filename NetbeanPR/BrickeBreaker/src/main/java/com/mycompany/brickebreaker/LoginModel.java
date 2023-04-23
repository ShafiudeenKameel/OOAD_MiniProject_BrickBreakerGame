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
import java.io.IOException;

public class LoginModel {
    private Connection conn;

    public LoginModel() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "kameel123");
    }

    public boolean login(String username, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        boolean result = rs.next();

        ps.close();
        rs.close();

        return result;
    }

    public void addScore(String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO scores (username) VALUES (?);");
        ps.setString(1, username);
        int numrows = ps.executeUpdate();
        ps.close();
    }

    public ResultSet getLeaderboard() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT username, COUNT(*) AS score FROM scores GROUP BY username ORDER BY score DESC LIMIT 10");
        ResultSet rs = ps.executeQuery();

        return rs;
    }

    public void close() throws SQLException {
        conn.close();
    }
}
