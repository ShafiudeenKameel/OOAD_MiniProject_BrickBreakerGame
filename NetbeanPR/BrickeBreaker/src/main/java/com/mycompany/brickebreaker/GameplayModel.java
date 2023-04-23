/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickebreaker;

/**
 *
 * @author skame
 */

import java.awt.Rectangle;

public class GameplayModel {
    private int ballposx;
    private int ballposy;
    private int ballxdir;
    private int ballydir;
    private int player1;
    private int score;
    private int totalbricks;
    private mapbrick map;
    private boolean play;

    public GameplayModel() {
        ballposx = 120;
        ballposy = 350;
        ballxdir = -1;
        ballydir = -2;
        player1 = 310;
        score = 0;
        totalbricks = 21;
        map = new mapbrick(3, 7);
        play = false;
    }

    public void update() {
        if (play) {
            // detecting intersection of 2 objs
            if (new Rectangle(ballposx, ballposy, 20, 20).intersects(new Rectangle(player1, 550, 100, 8))) {
                ballydir = -ballydir;
            }

            B: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickx = j * map.brickwidth + 80;
                        int bricky = i * map.brickheight + 50;
                        int brickwidth = map.brickwidth;
                        int brickheight = map.brickheight;

                        Rectangle rect = new Rectangle(brickx, bricky, brickwidth, brickheight);
                        Rectangle ballrect = new Rectangle(ballposx, ballposy, 20, 20);
                        Rectangle brickrect = rect;

                        if (ballrect.intersects(brickrect)) {
                            map.setBrickval(0, i, j);
                            totalbricks--;
                            score += 10;

                            if (ballposx + 19 <= brickrect.x || ballposx + 1 >= brickrect.x + brickrect.width) {
                                ballxdir = -ballxdir;
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
                ballxdir = -ballxdir;
            }
            if (ballposy < 0) {
                ballydir = -ballydir;
            }
            if (ballposx > 670) {
                ballxdir = -ballxdir;
            }
        }
    }

    public void resetGame() {
        ballposx = 120;
        ballposy = 350;
        ballxdir = -1;
        ballydir = -2;
        player1 = 310;
        score = 0;
        totalbricks = 21;
        map = new mapbrick(3, 7);
        play = true;
    }

    public int getBallPosX() {
        return ballposx;
    }

    public int getBallPosY() {
        return ballposy;
    }

    public int getBallXDir() {
        return ballxdir;
    }

    public int getBallYDir() {
        return ballydir;
    }

    public int getPlayerPos() {
        return player1;
    }

    public int getScore() {
        return score;
    }
    
    public int getTotalBricks() {
    return totalbricks;
}
    
    public void movePaddleRight(){
        if(player1 >= 600) {
				player1 = 600;
			} else {
				moveright();
			}
        
   }
    
   public void movePaddleLeft(){
        if(player1 <10) {
				player1 = 10;
			} else {
				moveleft();
			}
   }
   public void moveright(){
       play = true;
        player1+=20;
   }
   public void moveleft() {
		play = true;
		player1 -=20;
	}
    
        

public mapbrick getMap() {
    return map;
}

public void detectIntersection() {
    if (new Rectangle(ballposx, ballposy, 20, 20).intersects(new Rectangle(player1, 550, 100, 8))) {
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
            //repaint();
	}
