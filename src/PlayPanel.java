/**
* Project: PIXEL PLAYER
* File: PlayPanel.java
* Written by: Shannon Brown
* Description: This panel contains the playing board. Using two panels,
* it has a grid of buttons for the game board and a panel that has
* the play and reset buttons. The user can click a button on the grid
* multiple times to get different color options. Once the user is 
* finished with their picture, they can hit the play button to hear
* the song that corresponds with the placement of their colors and
* the different colors they used. The PlayPanel class uses a series
* of button listeners to connect the front end and the back end code.
* 
* @author Caitlin Coyiuto, Adri Tan, Shannon Brown
*/

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PlayPanel extends JPanel{
  
  /**
   * Instance variables.
   */
  private PixelPlayer PPlayer;
  JButton playbutton;
  JButton resetbutton;
  private JButton[][] buttons;
  private Pixel[][] pixelMatrix; //matrix of pixel objects
  private ImageIcon blue, yellow, black, green, pink, playButtonIcon, resetButtonIcon;
  private int[][] indexMatrix;
  
  
  /**
   * PlayPanel which creates the panel that holds the grid of buttons
   * and the play and reset buttons.
   */
  PlayPanel(PixelPlayer input){
    PPlayer = input;
    indexMatrix = new int[9][8]; //creates a matrix of the different color indices on the grid
      setBackground (Color.decode("#212121")); //dark grey

    for (int i = 0; i<9; i++){
      for (int j = 0; j<8; j++){
        indexMatrix[i][j] = 0;
      }
    }
    
    Panel p1 = new Panel(); //panel that will contain the button grid
    Panel p2 = new Panel(); //panel that will contain the reset and play buttons
    p1.setLayout(new GridLayout(9, 8)); // rows, cols
    p2.setLayout(new FlowLayout()); 
    p1.setPreferredSize(new Dimension(550,600));
    p2.setPreferredSize(new Dimension(100,100));
    
    playbutton = new JButton();
    resetbutton = new JButton();
    
    //create the images for the play and reset buttons
    playButtonIcon = createImageIcon("playButton.png", "play button"); 
    resetButtonIcon = createImageIcon("resetButton.png", "reset button");
    playbutton.setIcon(playButtonIcon);
    resetbutton.setIcon(resetButtonIcon);
    
    resetbutton.addActionListener(new ButtonListener());     
    playbutton.addActionListener(new ButtonListener());
    
    //these image icons represent the different colors that the buttons
    //in the grid can be changed to
    blue = createImageIcon("blue.jpg", "blue");
    yellow = createImageIcon("yellow.jpg", "yellow");
    black = createImageIcon("black.jpg", "black");
    green = createImageIcon("green.jpg", "green");
    pink = createImageIcon("pink.jpg", "pink");
    
    //add the play and reset buttons to the panel
    p2.add(playbutton); 
    p2.add(resetbutton);
    
    //buttons is our matrix of button objects
    buttons = new JButton[9][8];
    
    //loop through all of the buttons in the matrix
    for (int i = 0; i<9; i++) {
      for (int j = 0; j<8; j++) {
        buttons[i][j] = new JButton();
        //add button listener to every button
        buttons[i][j].addActionListener(new ButtonListener());  
        //set the buttons to be black initially
        buttons[i][j].setIcon(black);
        buttons[i][j].setOpaque(true);
        p1.add(buttons[i][j]); //add the buttons to the grid
      }
    }
    
    //add both panels to the pane
    add(p1); 
    add(p2);
    setVisible(true);
  }
  
  /**
   * Returns an ImageIcon, or null if the path was invalid.
   * NOTE: Used from the Tic Tac Toe GUI in lab.
   * @return ImageIcon
   */

  protected ImageIcon createImageIcon(String path,
                                      String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
  
  /**
   * ButtonListener class that will change the colors of the buttons
   * when clicked, reset the grid when the reset button is clicked
   * and play the tones when the play button is clicked.
   */

  private class ButtonListener implements ActionListener{
    
    private ImageIcon[] colors = new ImageIcon[] {black, blue, green, pink, yellow};
    private int index;
    private LinkedList<Double> temp;
    
    public void actionPerformed(ActionEvent e)
    {
      //loop through all of the buttons in the matrix
      for (int i=0; i<9; i++) 
        for (int j=0; j<8; j++) {
        //if a button in the matrix was clicked
        if ((e.getSource() == buttons[i][j])) {
          
          //System.out.println("current: " + current); //used for testing
          //System.out.println("BUTTON PRESSED"); //used for testing
          
          //System.out.println("Pixel index: " + PPlayer.getPixelIndex(i,j));
          
          //set the color in the indexMatrix and PPlayer object
          //if(indexMatrix[i][j] < (colors.length - 1)){
            indexMatrix[i][j]++;
            PPlayer.setColor(i,j);
            System.out.println("pixel index at " + i + " , " + j + " : " + PPlayer.getPixelIndex(i, j));
            //System.out.println("inside if statement"); //used for testing
            //System.out.println("IndexMatrix: " + indexMatrix[i][j]);
          //}
          //else{
            //indexMatrix[i][j] = 0;
            //PPlayer.resetPixelArray(i,j); //call the resetPixelArray helper method in PPlayer
            //System.out.println("inside else statement"); //used for testing
          //}
          //System.out.println("current is now: " + indexMatrix[i][j]); //used for testing
          
          //Change the color of the button when clicked
          buttons[i][j].setIcon(colors[indexMatrix[i][j]]);
          //PPlayer.setColor(i,j,colorStr[index]); //used for testing
          //System.out.println(PPlayer.toString()); //used for testing
          buttons[i][j].setOpaque(true);
          buttons[i][j].setContentAreaFilled(true);
        }
      }
      
      //if the reset button is clicked, call the resetGUI helper method
      if (e.getSource() == resetbutton)  {
        System.out.println("RESET");
        resetGUI();
      }
      
      //if the play button is clicked, call the getColumnFrequencies and
      //play the chord. Connects to back end.
      if (e.getSource() == playbutton)  {
        System.out.println("PLAYS");
        for (int i=0; i<8; i++){

          temp = PPlayer.getColumnFrequencies(i);
          //System.out.println("Column frequencies: " + temp);
          Tone.playChord(temp);
        }
      }
    }
    
    
  /**
   * Helper method that resets the GUI grid board by looping through the
   * buttons, changing the index to be zero and changing the color back to 
   * black.
   */
    
    private void resetGUI(){
      //System.out.println("RESET"); //used for testing
      //reset buttons
      for (int i=0; i<9; i++) 
        for (int j=0; j<8; j++) {
        indexMatrix[i][j] = 0;
        PPlayer.resetPixelArray(i,j);
        buttons[i][j].setIcon(black);
        buttons[i][j].setOpaque(true);
        buttons[i][j].setContentAreaFilled(true);
      }
      
      System.out.println("RESET PRESSED");
    }
  }
  
  
  //USED FOR TESTING:
  public static void main(String[] args){
//   
//    for (int i = 0; i<9; i++){
//      for (int j = 0; j<9; j++){
//        System.out.println(PPlayer.pixelArray[i][j].getPixelColor());
//      }
//    }
  }
}





  
  
  
  
  
  
