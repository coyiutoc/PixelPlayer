/**
* PixelPlayer.Java
*
* Performs the back-end work of the GUI, and provides methods
* that link the front-end to the back-end. 
* 
* @author  Shannon Brown, Caitlin Coyiuto, Adri Tan
* @since   2016-05-08 
*/

import javafoundations.*;
import java.io.*;
import java.util.*;

public class PixelPlayer{
  
  //instance variables
  final int GRIDDIM = 9; //grid dimension (rows)
  Pixel[][] pixelArray; //array that the pixels will be stored in
  
/**
* Constructor creates a pixelArray, and fills it with a default
* pixel.
*/
  public PixelPlayer(){
    
    // Need to instantiate each pixel in the matrix or else 
    // is just a null object.
    pixelArray = new Pixel[GRIDDIM][8];
    
    for (int i = 0; i<GRIDDIM; i++){
      for (int j = 0; j<8; j++){
        pixelArray[i][j] = new Pixel();
      }
    }
  }
  
/**
* For a given row and column in the matrix, set the color 
* to the next color in the color list. 
*/
  public void setColor(int row, int col){
    
    pixelArray[row][col].nextColor();
    
  }
  
/**
* Will produce a linked list of frequencies, and NOT include any 
* double of the same frequency/color. (For example, if have two
* blue blocks in the same column, the frequency associated with
* blue will only be stored once into the linked list.)
* 
* @return LinkedList<Double> linked list of the frequencies.
*/
  public LinkedList<Double> getColumnFrequencies(int inputCol){

    LinkedList<Double> columnFrequencies = new LinkedList<Double>();
    boolean emptycol = true;

    for (int i=0; i<9; i++){
      Pixel current = pixelArray[i][inputCol];
      
      // TESTING ------------------------------------------------
       System.out.println("Current Index: " + i + " " + GRIDDIM);
       System.out.println("Current pixel color: " + current.getPixelColor());
      // --------------------------------------------------------
      
      // If pixel has a color and is currently not in the columnFrequencies list, add the pixel:
      if ((current.getPixelColor() != "Black") && (columnFrequencies.indexOf(current.getFrequency()) == -1)) {
          //System.out.println("Current freq: " + current.getFrequency());
          columnFrequencies.add(current.getFrequency());
          emptycol = false;
      }

    }

    if (emptycol == true){
        for (int i = 0; i<3; i++){
            columnFrequencies.add(0.0);
        }
    }

    return columnFrequencies;
  }
  
/**
* Helper method that checks to see if the column is empty. 
* 
* @return boolean
*/
     public boolean emptyColumn(int col){
      
       boolean check = true; 
       
       for (int i = 0; i<9; i++){
         Pixel current = pixelArray[i][col];
         // If there is any pixel that is NOT black, break out of the loop
         // and return false. 
         if (current.getPixelColor() != "Black"){
           check = false;
           break;
         }
       }
      
      return check;
    }
    
/**
* Helper method (primarily for debugging) that prints the colors
* in the matrix in a matrix format.
* 
* @return String prints the pixel colors in the matrix
*/
  public String toString(){
    
    String s = "";
    for (int i = 0; i < pixelArray.length; i++) {
      for (int j = 0; j < pixelArray[0].length; j++) {
        s += pixelArray[i][j].getPixelColor() + " ";
      }
      s += "\n";
    } 
    return s;
  }
  
/**
* Resets all of the pixels in the matrix to be the default (black).
* 
*/
  public void resetPixelArray(int i, int j){
    pixelArray[i][j] = new Pixel();
  }
  
  
/**
* Helper method (primarily used for debugging) that retrieves the
* color of index of the inputted pixel location.
* 
* @return int colorIndex of the pixel. 
*/
  public int getPixelIndex(int i,int j){
    return pixelArray[i][j].getIndex();
  }
  
  
  //------------------------------------------------------------------
  // Main method
  //------------------------------------------------------------------
  public static void main(String[] args){
    
    PixelPlayer test = new PixelPlayer();
    
    Pixel a = new Pixel();
    a.setPixelColor("Blue");
    Pixel b = new Pixel();
    b.setPixelColor("Yellow");
    Pixel c = new Pixel();
    c.setPixelColor("Green");
    Pixel d = new Pixel();
    d.setPixelColor("Pink");
    Pixel e = new Pixel();
    e.setPixelColor("Pink");
    
    test.pixelArray[5][0] = a;
    test.pixelArray[2][0] = b;
    test.pixelArray[3][0] = c;
    test.pixelArray[1][0] = d;
    test.pixelArray[4][0] = e;
    test.setColor(0,0);
    
    System.out.println("Test set color: " + test.pixelArray[0][0].getPixelColor());
      test.setColor(0,0);
    
    LinkedList<Double> colFreq = test.getColumnFrequencies(0);
    System.out.println("colFreq: " + colFreq.toString());
    
    Tone.playChord(colFreq);
    
    //System.out.println(test.toString());

  }
}