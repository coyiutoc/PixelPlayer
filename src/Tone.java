/**
* Tone.Java
* 
* Responsible for calculating the double[] needed to produce the tone. 
* 
* Note that Tone constructor originally taken from:
* http://introcs.cs.princeton.edu/java/stdlib/javadoc/StdAudio.html
* 
* playChord method was specially written for use with our GUI.
* 
* @author  Shannon Brown, Caitlin Coyiuto, Adri Tan
* @since   2016-05-08 
*/

import java.util.LinkedList;
import javafoundations.*;

public class Tone<T> {
  
/**
* Takes in a frequency (hz) and duration (seconds).
* 
* @return double[] array of doubles that when passed into 
* StdAudio.play, will play the tone. 
*/
  public static double[] tone(double hz, double duration) { 
    int N = (int) (StdAudio.SAMPLE_RATE * duration);
    double[] a = new double[N+1];
    for (int i = 0; i <= N; i++) {
      a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
    }
    return a; 
  } 
  
  
/**
* When given a LinkedList of doubles (consisting of the frequencies
* within a specific column in the color matrix), will compute 
* the double[] that represents the chord, and play it. 
*/
  public static void playChord(LinkedList<Double> input){

    for (int i = 0; i<input.size(); i++){
       // System.out.println("Playchord: " + input.get(i));
    }
    double[] frequencies = {0.0, 0.0, 0.0, 0.0};
    
    int count = 0;
    
    // As long as the input is not empty, add the frequency
    // into the frequencies array. 
    while (!input.isEmpty()){  
     frequencies[count] = input.pop(); 
     count++;
    }
    
    double[] a = tone((Double)frequencies[0],1);
    double[] b = tone((Double)frequencies[1],1);
    double[] c = tone((Double)frequencies[2],1);
    double[] d = tone((Double)frequencies[3],1);
   
    // Calculates the double[] for a chord of maximum length
    // 4 (which is the maximum number of colors available
    // to be in a column). 
    for (int i = 0; i<a.length; ++i){
      
      a[i] = ((a[i] + b[i] + c[i] + d[i]) / count);
    
    }

    StdAudio.play(a);
  
  }
  
//------------------------------------------------------------------
// Main method
//------------------------------------------------------------------
  public static void main(String[] args) {
    
    LinkedList<Double> test = new LinkedList<Double>();
    test.add(261.6);
    test.add(329.6);
    //test.enqueue(391.9);
    //test.enqueue(466.1);
    
    playChord(test);
    
  
}
}
