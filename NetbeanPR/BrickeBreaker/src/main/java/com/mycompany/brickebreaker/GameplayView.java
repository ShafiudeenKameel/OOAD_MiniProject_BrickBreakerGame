/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickebreaker;

/**
 *
 * @author skame
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameplayView extends JFrame implements ActionListener {
    private GameplayModel model;
    private JPanel gamePanel;
    private JButton restartButton;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JLabel gameOverLabel;
    public int map[][];
    //public mapbrick map;
    public int brickwidth;
    public int brickheight; 
    
    public GameplayView(GameplayModel model) {
        this.model = model;
        
        // Set up the game panel
        gamePanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the game objects on the panel
                drawBall(g);
                drawPaddle(g);
                //map = new mapbrick(3, 7);
                drawBricks((Graphics2D)g,3,7);
            }
        };
        gamePanel.setPreferredSize(new Dimension(700, 600));
        
        // Set up the restart button
        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        
        // Set up the score and lives labels
        scoreLabel = new JLabel("Score: " + model.getScore());
        //livesLabel = new JLabel("Lives: " + model.getLives());
        
        // Set up the game over label (initially invisible)
        gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setVisible(false);
        
        // Set up the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Brick Breaker");
        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        add(gamePanel);
        add(restartButton);
        add(scoreLabel);
//        add(livesLabel);
        add(gameOverLabel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Draw the ball on the game panel
    private void drawBall(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(model.getBallPosX(), model.getBallPosY(), 20, 30);
    }
    
    // Draw the paddle on the game panel
    private void drawPaddle(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(model.getPlayerPos(), 550, 100, 8);
    }
    
    // Draw the bricks on the game panel
    private void drawBricks(Graphics2D g,int row,int col) {
//        int[][] bricks = model.getTotalBricks();
//        int brickWidth = model.getBrickWidth();
//        int brickHeight = model.getBrickHeight();
//        for (int i = 0; i < bricks.length; i++) {
//            for (int j = 0; j < bricks[0].length; j++) {
//                if (bricks[i][j] > 0) {
//                    g.setColor(Color.GREEN);
//                    g.fillRect(j * brickWidth, i * brickHeight, brickWidth, brickHeight);
//                    g.setColor(Color.BLACK);
//                    g.drawRect(j * brickWidth, i * brickHeight, brickWidth, brickHeight);
//                }
//            }
//        } 
            
            brickwidth = 540/col;
            brickheight = 150/row;
            map = new int [row][col];
            for (int i=0; i<  map.length; i++) {
			for (int j=0; j< map[0].length; j++) {
				if (map[i][j]> 0) {
					g.setColor(Color.white);
					g.fillRect(j*brickwidth + 80,  i*brickheight + 50,  brickwidth,  brickheight);
					
					//map border of bricks
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*brickwidth +80, i*brickheight + 50, brickwidth, brickheight);
					
				}
			}
		}
    }
    
    // Update the score label
    public void updateScoreLabel() {
        scoreLabel.setText("Score: " + model.getScore());
    }
    
    // Update the lives label
    
    
    // Show the game over label
    public void showGameOverLabel() {
        gameOverLabel.setVisible(true);
    }
    
    // Handle restart button click
    @Override
    public void actionPerformed(ActionEvent e) {
        model.resetGame();
    }
//    @Override
//    public void addKeyListener(new KeyAdapter()) {
//        @Override
//        model.resetGame();
//    }
}
