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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameplayController implements KeyListener {

    private GameplayModel model;
    private GameplayView view;

    public GameplayController(GameplayModel model, GameplayView view) {
        this.model = model;
        this.view = view;
        this.view.addKeyListener(this);
        //this.view.getPlayButton().addActionListener(this);
        //this.view.getQuitButton().addActionListener(this);
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == view.getPlayButton()) {
//            model.resetGame();
//            view.requestFocusInWindow();
//            view.repaint();
//        } 
//        else if (e.getSource() == view.getQuitButton()) {
//            System.exit(0);
//        }
//    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            model.movePaddleLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            model.movePaddleRight();
        } else if (keyCode == KeyEvent.VK_ENTER){
            
        }
        
         
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
