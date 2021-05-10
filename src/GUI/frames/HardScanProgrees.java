package gui.frames;

// Java Program to create a
// simple progress bar
import java.awt.*;
import javax.swing.*;

import DataSorter.SortByFilter;
import gui.GFrame;
import gui.Gui;

import java.awt.event.*;
public class HardScanProgrees extends JFrame implements GFrame {
	public static int filesNum = SortByFilter.listLength ;
	public static int currentIndex = SortByFilter.index;
 
    // create a frame
    static JFrame f;
 
    static JProgressBar b;
    private static JLabel label;
 
    public static void main()
    {
    	label = new JLabel ("Please wait for the hard scan to finish...");
    	
   
        // create a frame
      
 
        // create a panel
        JPanel p = new JPanel();
 
        // create a progressbar
        b = new JProgressBar();
 
        // set initial value
        b.setValue(0);
 
        b.setStringPainted(true);
 
        // add progressbar
        p.add(b);
 
        // add panel
        
        p.add(label);
 
        // set the size of the frame
 
        fill();
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
                b.setValue((num2/num1 )*100);
                Thread.sleep(100);
                num2++;
                
               System.out.println(num2);
                
            }
            
        }
        catch (Exception e) {
        }
    }
    


	@Override
	public void paint(Gui graphics) {
		main();
	}
}