package GUI.Components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import javax.swing.JTextField;

public class InputField implements GComponent, ActionListener{
	private final static int x = 5;
	private final static int y = 30;
	private final static int width = 550;
	private final static int height = 30;
	
	private JTextField textField;
	
	public InputField() {
		this.textField = new JTextField(20);
	    this.textField.setBounds(x, y, width, height);  
	    this.textField.addActionListener((ActionListener) this);
	    this.textField.setText("Search...");
	}
	
	@Override
	public Component getComponent() {
		return this.textField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// When pressed enter this is called
		String path = e.getActionCommand();	
	}
}
