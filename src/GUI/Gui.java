package gui;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import gui.Components.GComponent;
import gui.Components.InputField;
import gui.Components.SearchButton;
import gui.Components.SearchResults;
import gui.frames.SearchFrame;



/**
 * This class is the main gui class.
 * THe class paints the window, add new components.
 */
public class Gui extends JFrame {
	public static final ArrayList<GComponent> components = new ArrayList<GComponent>();
	public static Graphics g = null;

	/**
	 * This is the constructor, initializing the window and the components.
	 */
	public Gui () {
		g = getGraphics();
		initializeWindow();
		GFrame search = new SearchFrame();
		setFrame(search);
	
		
	}
	
	public void setFrame(GFrame frame) {
		frame.paint(this);
	}

	/**
	 * This method initializing the window properties.
	 */
	private void initializeWindow() {
		setSize(600,600);//frame size 300 width and 300 height
		setLayout(null);//no layout manager
		setVisible(true);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
	}

	/**
	 * This method repaint the window.
	 */
	public void paint() {
		for (GComponent c : components) {
			this.add(c.getComponent());
		}
	}

}
