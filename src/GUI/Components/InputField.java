package gui.Components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import gui.GuiSearch;

/**
 * This class is the Input Field Component.
 */
public class InputField implements gui.Components.GComponent, ActionListener{
	private final static int x = 5;
	private final static int y = 30;
	private final static int width = 550;
	private final static int height = 30;
	
	private final JTextField textField;

	/**
	 * This is the constructor, it initializing the Input Field properties.
	 */
	public InputField() {
		this.textField = new JTextField(20);
	    this.textField.setBounds(x, y, width, height);  
	    this.textField.addActionListener((ActionListener) this);
	    this.textField.setText("Search...");
	}

	/**
	 * @return returns the current text in the Input field.
	 */
	public String getText() {
		return this.textField.getText();
	}

	@Override
	public Component getComponent() {
		return this.textField;
	}

	/**
	 * This method is the event listener for the input field.
	 * it will be called each time the user presses enter,
	 * then it will search searching
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// When pressed enter this is called
		System.out.println(e.getActionCommand());
		String path = e.getActionCommand();
		GuiSearch.search(path);
	}
}
