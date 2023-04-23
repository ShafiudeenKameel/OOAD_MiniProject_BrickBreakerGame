/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brickebreaker;

import java.sql.SQLException;

/**
 *
 * @author skame
 */

public class BrickeBreaker {

    public static void main(String[] args) {
//        LoginGUI login = new LoginGUI();
          try{
              LoginModel l1= new LoginModel();
              LoginView l2 = new LoginView();
              LoginController l3 = new LoginController(l1,l2);
          }
          catch(SQLException ex){
              
          }
   
          
          
    }
}
