/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickebreaker;

/**
 *
 * @author skame
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.Timer; 
import javax.swing.JPanel;
import java.io.IOException;
import java.sql.*;
import javax.swing.JOptionPane;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
        
	private boolean play = false;
	private int score =0;
	private int totalbricks = 21;
	private Timer time;
	private int delay = 5;
	private int player1= 310;
	private int ballposx = 120;
	private int ballposy = 350;
	private int ballxdir = -1;
	private int ballydir = -2;
	
	private mapbrick map; 
	
	public Gameplay() {
            
                try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "kameel123");
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage());
        }
		map = new mapbrick (3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	public void paint(Graphics g) {
		//bg
		g.setColor(Color.black);
		g.fillRect(1,1, 692, 592);
		
		//draw
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0,0 , 692, 3);
		g.fillRect(691,0,3, 592);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("Verdana", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		//paddle
		g.setColor(Color.green);
		g.fillRect(player1, 550, 100,8);
		
		//ball 
		g.setColor(Color.yellow);
		g.fillOval(ballposx,ballposy, 20, 20);
		
		if(totalbricks <=0) {
			play = false;
			ballxdir = 0;
			ballydir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Verdana", Font.BOLD, 35));
			g.drawString("YOU WON!", 190, 300);
			
			g.setFont(new Font("Verdana", Font.BOLD, 30));
			g.drawString("Press ENTER to restart",230, 350);
		}
		
		if (ballposy > 570) {
			play = false;
			ballxdir = 0;
			ballydir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("Verdana", Font.BOLD, 35));
			g.drawString("GAME OVER!", 190, 300);
                        try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "kameel123");
//        PreparedStatement d = conn.prepareStatement("select session_id from scores ORDER BY id DESC LIMIT 1");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select session_id from scores ORDER BY session_id DESC LIMIT 1");

            // Get the first matching ID from the result set
            int id = -1;
            if (rs.next()) {
                id = rs.getInt("session_id");
            }
        PreparedStatement p = conn.prepareStatement("UPDATE scores SET high_score=? WHERE session_id=?;");
                        p.setInt(1, (score));
                        p.setInt(2,id);
                        int numrows=p.executeUpdate();
                        p.close();
                        conn.close();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage());
        }
                        
			
			g.setFont(new Font("Verdana", Font.BOLD, 30));
			g.drawString("Press ENTER to restart",230, 350);
                        
                        
			
		}
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if(play) {
			//detecting interstion of 2 objs
			if (new Rectangle(ballposx,ballposy, 20, 20).intersects(new Rectangle(player1, 550, 100, 8))) {
				ballydir = -ballydir; 
			}
			
			B: for (int i=0; i< map.map.length; i++) {
				for (int j=0; j<map.map[0].length; j++) {
					if (map.map[i][j]>0) {
						int brickx = j*map.brickwidth + 80;
						int bricky = i* map.brickheight + 50; 
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;
						
						Rectangle rect = new Rectangle(brickx, bricky, brickwidth, brickheight);
						Rectangle ballrect = new Rectangle(ballposx, ballposy, 20, 20);
						Rectangle brickrect = rect; 
						
						if(ballrect.intersects(brickrect)) {
							map.setBrickval(0,i,j);
							totalbricks--;
							score += 10;
							
							if(ballposx + 19 <= brickrect.x || ballposx + 1 >= brickrect.x + brickrect.width) {
								ballxdir = - ballxdir;
							} else {
								ballydir = -ballydir; 
							}
							
							break B;
						}
					}
				}
			}
			ballposx += ballxdir;
			ballposy += ballydir;
			if (ballposx < 0) { 
				ballxdir = - ballxdir;
			}
			if (ballposy < 0) { 
				ballydir = - ballydir;
			}
			if (ballposx > 670) { 
				ballxdir = - ballxdir;
			}
			
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(player1 >= 600) {
				player1 = 600;
			} else {
				moveright();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(player1 <10) {
				player1 = 10;
			} else {
				moveleft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
                            try{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "kameel123");
//      PreparedStatement d = conn.prepareStatement("select session_id from scores ORDER BY id DESC LIMIT 1");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select username from scores ORDER BY session_id DESC LIMIT 1");

            // Get the first matching ID from the result set
            String name ="";
            if (rs.next()) {
                name = rs.getString("username");
            }
        PreparedStatement p = conn.prepareStatement("INSERT INTO scores (username) VALUES (?);");
                    p.setString(1, name);
                    int numrows=p.executeUpdate();
                    p.close();
                    conn.close();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + ex.getMessage());
        }
				play = true;
				ballposx = 120;
				ballposy = 350;
				ballxdir = -1;
				ballydir = -2;
				player1 = 310;
				score = 0;
				totalbricks = 21;
				map = new mapbrick(3,7);
				
				repaint();
			};
			
		}
	}

	public void moveright() {
		play = true;
		player1+=20;
	}
	public void moveleft() {
		play = true;
		player1 -=20;
	}
	
}
