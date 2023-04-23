/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickebreaker;


/**
 *
 * @author skame
 */
import javax.swing.JFrame; 
public class Main{

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay game = new Gameplay();
		obj.setBounds(10,10,700,600);
		obj.setTitle("BRICK BREAKER");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);
		
		
	}

}