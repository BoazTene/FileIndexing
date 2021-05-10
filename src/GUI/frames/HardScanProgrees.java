package gui.frames;

// Java Program to create a
// simple progress bar
import java.awt.*;
import javax.swing.*;

import DataSorter.SortByFilter;
import gui.GFrame;
import gui.Gui;
import gui.Components.GComponent;

import java.awt.event.*;
public class HardScanProgrees implements GFrame {
	public static int filesNum = SortByFilter.listLength ;
	public static int currentIndex = SortByFilter.index;
	public static JProgressBar bar = new JProgressBar();
	public static JLabel label = new JLabel("Please wait for the hard scan to finish...");
 
    
    public static void HardScanProgrees()
    {
		gui.Gui.components.clear();
		gui.Gui.components.add((GComponent) label);
		gui.Gui.components.add((GComponent) bar);
   
    }
 
    // function to increase progress
    public static void fill()
    {
    	int num1 =100000;
    	int num2=0;
        try {
        	//filesNum = SortByFilter.listLength;
        	//currentIndex = SortByFilter.index;
            while (num2 <= num1) {
                // fill the menu bar
                bar.setValue((num2/num1 )*100);
                Thread.sleep(100);
                num2++;
                
            }
            
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
    


	@Override
	public void paint(gui.Gui graphics) {
		graphics.paint();
		graphics.repaint();
		graphics.revalidate();
	}
}