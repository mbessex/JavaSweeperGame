/**
*SweeperGame.java
*TCSS 143 - Winter 2018
*Programming Assignment 2
*Create a class SweeperGame that uses a 2D array to simulate a game similar to battleship
*The user constructs the game by entering a height and width of the gameboad
*The driver file is SimpleSweeper.java
*@author: Miranda Bessex
*@version: 1/29/18
 */

import java.util.*;
import java.io.*;

public class SweeperGame {
   
   //initial state
   private char[][] gameBoard;
   private int treasureX;
   private int treasureY;
   private int totalMoves;
   private boolean found;
   
   
   /**
   *@constructor: Builds a 2d array with height and width being passed to it
   *@param int height, int width
   *Builds a 2d array with height and width being passed to it
   *Assigns a random cordinate to be the treasure and set the array at that location to a 'T'
   *Initialize totalMoves to 0 and found to false
   */
   public SweeperGame(int height, int width){
      gameBoard = new char[height][width];
      for(int i = 0; i < height; i++){
         for(int j = 0; j < width; j++){
            gameBoard[i][j] = ' ';
         }
      }
      Random r = new Random(); 
      
      treasureX = r.nextInt(width);
      treasureY = r.nextInt(height);
      
      gameBoard[treasureY][treasureX] = 'T';
      
      totalMoves = 0;
      
      found = false;
      
      //System.out.println(treasureX); //was used to check logic by displaying where treasure was
      //System.out.println(treasureY);
            
   }
   
   
   
   
   
   /**
   *Method beenSwept: Checks to see if the cordinate being swept has already been checked
   *@param int x, int y
   *@return boolean
   */
   
   public boolean beenSwept(int x, int y){
      if(Character.isDigit(gameBoard[y][x])){
         return true;
      }
      else
         return false;
   }
   
   /**
   *Method treasureFound: Checks to see if the cordinate being swept contains the treasure
   *@param int x, int y
   *@return boolean
   */
   
   public boolean treasureFound(int x, int y){
      if(gameBoard[y][x] == 'T'){
         return true;
      }
      else
         return false;
   
   }
   
   /**
   *Method checkOutOfBounds: Checks to see if the cordinate being swept is within the bounds of the array
   *@param int x, int y
   *@return boolean
   */
   
   public boolean checkOutOfBounds(int x, int y){
      int height = gameBoard.length-1;
      int width = gameBoard[0].length-1;
      if( x <= width && x >= 0){
         if( y <= height && y >= 0){
            return true;
         }
         else
            return false;
      }
      else
         return false;
   }
   
   /**
   *Method getBoardHeight: Returns the height of the gameboard array
   *@param null
   *@return int
   */
   
   public int getBoardHeight(){
      return gameBoard.length;
   }
   
   /**
   *Method getBoardWidth: Returns the Width of the gameboard array
   *@param null
   *@return int
   */
   
   public int getBoardWidth(){
      return gameBoard[0].length;
   }
   
   /**
   *Method getTotalMoves: Returns the total moves played up to the winning move
   *@param null
   *@return int
   */
   
   public int getTotalMoves(){
      return totalMoves;
   }
   
   /**
   *Method digSand: checks if the treasure is found, if not it fills in the array with distance from the treasure 
   *@param int x, int y
   *@return boolean
   */
   
   public boolean digSand(int x, int y){
      
      if(treasureFound(x, y)){
         found = true;
         return true;
      }
      else{
         int manNum = (Math.abs(treasureX - x) + Math.abs(treasureY - y));
         //compute manhattan number for distance from treasure
         String manhattanNum = Integer.toString(manNum);
         //cast int as a char so it can be added to the array
         char c = manhattanNum.charAt(0);
         
         
         gameBoard[y][x] = c;
         
         
         totalMoves++;
         
         return false;
         
      }
   }
   
   /**
   *Method toString: Returns the Width of the gameboard array
   *@override of two string method from the object class 
   *@param null
   *@return String
   */
         
   public String toString()  { 
      String str = "";
      String sep = "";
      str += ",";
      sep += "`";
      for(int k = 0; k < gameBoard[0].length; k++){
         str += "~,";
         sep += "~`";
      }
      str += "\n";
      sep += "\n";
      for(int i = gameBoard.length-1; i >= 0; i--){
         
         for(int j = 0; j < gameBoard[0].length; j++){
         
            if(j % 2 == 0){
               str += ";";
            }
            else
               str += ":";
               
            if(gameBoard[i][j]== 'T' && found == false){
               str += ".";
            }
            
            else if(gameBoard[i][j]== 'T' && found == true){
               str += "T";
            } 
            else if(gameBoard[i][j]== ' '){
               str += ".";
            
            }
            else
               str += gameBoard[i][j];
            
            
         }
         if(gameBoard[0].length % 2 == 0){
               str += ";";
            }
            else
               str += ":";
         str += "\n";
         str += sep;
         
      }
   
      return str;
   }  
}      
   
   
   


//main method used to test logic
/*
class Main  {
   public static void main (String[] args)   {
      SweeperGame s = new SweeperGame(5, 4);
      
      //System.out.println(s.treasureFound(1, 3));
      System.out.println(s.digSand(1, 3));
      System.out.println(s.toString());
   }  

} 

*/