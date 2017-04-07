//********************************************************************
// Project: PIXEL PLAYER
// File: PixelPlayerGUI.java
// Written by: Shannon Brown
// Description: This sets up the tabbed pane that contains a Welcome
// tab and a play tab.
//
// Note: Used with help from the Grad Schools example.
//********************************************************************

import javax.swing.*;
import javax.swing.border.*;
public class PixelPlayerGUI
  
{
  
//-----------------------------------------------------------------
// Sets up a frame containing a tabbed pane. The panel on each
// tab demonstrates a different tab for Pixel Player.
//-----------------------------------------------------------------
  
  public static void main (String[] args)
    
  {
    
    PixelPlayer PPlayer = new PixelPlayer();
    
    JFrame frame = new JFrame ("Pixel Player");
    
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    JTabbedPane tp = new JTabbedPane();
    
    tp.addTab ("Welcome", new WelcomePanel()); //welcome tab
    
    tp.addTab ("Play", new PlayPanel(PPlayer)); //play tab
    
    frame.getContentPane().add(tp); //add tabbed pane to frame
    
    frame.pack();
    
    frame.setVisible(true);

    
  }
  
}