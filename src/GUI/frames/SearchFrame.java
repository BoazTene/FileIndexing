package gui.frames;

import java.util.ArrayList;

import gui.GFrame;
import gui.Components.GComponent;
import gui.Components.InputField;
import gui.Components.SearchButton;
import gui.Components.SearchResults;

/*
 * this class is the main frame of the gui which you search from
 */
public class SearchFrame implements GFrame{
	public static final InputField inputFiled = new InputField();
	public static final SearchResults scroll = new SearchResults();
	public static final SearchButton searchButton = new SearchButton();
	
	/*
	 * constructor - initializing the search frame properties
	 */
	public SearchFrame() {
		gui.Gui.components.clear();
		gui.Gui.components.add((GComponent) inputFiled);
		gui.Gui.components.add((GComponent) scroll);
		gui.Gui.components.add(searchButton);
		
	}
	
	/* this function paints the frame
	 * (non-Javadoc)
	 * @see gui.GFrame#paint(gui.Gui)
	 */
	public void paint(gui.Gui graphics) {
		graphics.paint();
		graphics.repaint();
		graphics.revalidate();
	}
}
