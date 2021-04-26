package GUI;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants.*;
import GUI.Components.GComponent;
import GUI.Components.InputField;
import GUI.Components.SearchResults;


public class Gui extends JFrame {
	private ArrayList<GComponent> components;
	
	public Gui () {
		this.components = new ArrayList<GComponent>();
		setSize(600,600);//frame size 300 width and 300 height  
		setLayout(null);//no layout manager  
		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//		JLabel searchButton = new JLabel("Search something...");
//	    searchButton.setBounds(10,0, 200,30);  
//	    searchButton.setFont(new Font ("TimesRoman", Font.BOLD | Font.ITALIC, 20) );
//	    Graphics g = getGraphics();
//	    this.paint(getGraphics());
//		this.add(searchButton);
		GComponent gComponenet = new InputField();
		GComponent scroll = new SearchResults();
		this.components.add(gComponenet);
		this.components.add(scroll);
		this.paint();
		this.revalidate();
		this.repaint();
	}
	
	public void paint() {
		for (Iterator<GComponent> iter = this.components.iterator(); iter.hasNext(); ) {
			GComponent c = iter.next();
			this.add(c.getComponent());
		}
		
		this.repaint();
		
	}
	
	public static void main(String args[]){  
		Gui f = new Gui();  
		
		
	}
}
