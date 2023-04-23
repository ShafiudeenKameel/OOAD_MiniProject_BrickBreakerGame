/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickebreaker;

/**
 *
 * @author skame
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;

public class LoginController implements ActionListener {
    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;

        // register this controller as the listener for the view's buttons
        this.view.addLoginListener(this);
        this.view.addSignupListener(this);
        this.view.addLeaderboardListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getLoginButton()) {
            String username = view.getUsername();
            String password = view.getPassword();

            try {
                boolean loggedIn = model.login(username, password);

                if (loggedIn) {
                    view.showMessage("Login successful!");
                    model.addScore(username); // add score to the database
                    view.dispose(); // close the login window
                    JFrame obj = new JFrame();
                    Gameplay game = new Gameplay();
                    obj.setBounds(10,10,700,600);
                    obj.setTitle("BRICK BREAKER");
                    obj.setResizable(false);
                    obj.setVisible(true);
                    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    obj.add(game);
                    
                    // launch the game view
//                    GameplayModel gameModel = new GameplayModel();
//                    GameplayView gameView = new GameplayView(gameModel);
//                    GameplayController gameController = new GameplayController(gameModel,gameView);
                } else {
                    view.showMessage("Invalid username or password.");
                }
            } catch (SQLException ex) {
                view.showLoginFailedMessage();
            }
        } else if (e.getSource() == view.getSignupButton()) {
            SignupView signupView = new SignupView();
            SignupModel signupModel = new SignupModel();
            SignupController signupController = new SignupController(signupModel, signupView);
            view.dispose(); // close the login window
        } 
        else if (e.getSource() == view.getLeaderBoardButton()) {
            LeaderBoard lb= new LeaderBoard();
            lb.setVisible(true);
            view.dispose(); // close the login window
        }
    }

}

