/**
* Pixel.Java
*
* Represents the pixel object for the color matrix. Contains 
* instance variables for color, frequency and the color index. 
* 
* @author  Shannon Brown, Caitlin Coyiuto, Adri Tan
* @since   2016-05-08 
*/

import javafoundations.*;
import java.io.*;
import java.util.*;

public class Pixel{
  
  //instance variables
  String color;
  int colorIndex;
  double frequency;
  Hashtable<String,Double> frequencies; //hashtable provides color keys to values
  String[] colorList;

  
/**
* Constructor creates a pixel with black, the default of -1 frequency,
* and the default value of the color index, 0. 
*/
  public Pixel(){
    color = "Black";
    frequencies = new Hashtable<String,Double>(5);
    frequencies.put("Black", -1.0);
    frequencies.put("Blue", 261.626);
    frequencies.put("Green", 329.628);
    frequencies.put("Pink", 391.995);
    frequencies.put("Yellow", 466.164);
    frequency = -1;
    colorIndex = 0;
    colorList = new String[]{"Black", "Blue", "Green", "Pink", "Yellow"};
}
  

/**
* Sets the color of the pixel, and also assigns the appropriate
* colorIndex and frequency.
*/
  public void setPixelColor(String c){
    color = c;
    colorIndex = getIndex();
    frequency = frequencies.get(color);

  }
  

/**
* Returns the color of the pixel.
* 
* @ return String color of the pixel.
*/
  public String getPixelColor(){
    return color;
  }
  
  
/**
* Returns the frequency of the pixel.
* 
* @ return double frequency of the pixel.
*/
  public double getFrequency(){
    return frequency;
  }
  
/**
* Returns the color index of the pixel.
* 
* @ return int colorIndex of the pixel.
*/
  public int getIndex(){
    for (int i=0; i<colorList.length; i++){
      if (color.equals(colorList[i])){
        colorIndex = i;
      }
    }
    return colorIndex;
  }
    
/**
* Sets the pixel to be the next color (of the order of colors
* that the list cycles through when a single square in the
* color matrix is pressed repeatedly). 
*/
    public void nextColor(){
      if (colorIndex < (colorList.length -1)){
        setPixelColor(colorList[colorIndex+1]);
        colorIndex ++;
      }
      else{
        setPixelColor(colorList[0]);
        colorIndex = 0;
      }
    }
    
   
  //------------------------------------------------------------------
  // Main Method
  //------------------------------------------------------------------
  public static void main(String[] args){
    Pixel test = new Pixel();
    System.out.println("Color of test is: " + test.getPixelColor());
    System.out.println("Frequency of test is: " + test.getFrequency());  
    System.out.println("Index of color from test is: " + test.getIndex());
    test.nextColor();
    System.out.println("Color of test is: " + test.getPixelColor());
    System.out.println("Frequency of test is: " + test.getFrequency());  
    System.out.println("Index of color from test is: " + test.getIndex());
    test.nextColor();
    System.out.println("Color of test is: " + test.getPixelColor());
    System.out.println("Frequency of test is: " + test.getFrequency());  
    System.out.println("Index of color from test is: " + test.getIndex());
    test.nextColor();
    System.out.println("Color of test is: " + test.getPixelColor());
    System.out.println("Frequency of test is: " + test.getFrequency());  
    System.out.println("Index of color from test is: " + test.getIndex());
    test.nextColor();
    System.out.println("Color of test is: " + test.getPixelColor());
    System.out.println("Frequency of test is: " + test.getFrequency());  
    System.out.println("Index of color from test is: " + test.getIndex());
    test.nextColor();
    System.out.println("Color of test is: " + test.getPixelColor());
    System.out.println("Frequency of test is: " + test.getFrequency());  
    System.out.println("Index of color from test is: " + test.getIndex());
  }
}
    
  