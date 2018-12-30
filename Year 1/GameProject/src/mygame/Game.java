package mygame;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import javax.swing.JFrame;

public class Game 
{
    
    //MELOJAN IS KINGMELOJAN NAME MELO
   public static void main(String [] args){
       JFrame window = new JFrame("SUPER SQUARE"); // Make a Frame
       window.setContentPane(new GamePanel());
       // Closing the Window
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       //Not Resizing the Window
       window.setResizable(false);
       //We can see the game
       window.pack();
       window.setVisible(true);
    }
}