package GUI.Components;

import DataSorter.Filters.ExtensionFilter;
import DataSorter.Filters.Filter;
import DataSorter.Filters.NameFilter;
import Search.Search;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

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
		System.out.println(e.getActionCommand());
		SearchResults.resetResults();
		Filter[] filters = new Filter[2];
		try {
			filters[0] = new NameFilter();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		filters[1] = new ExtensionFilter();
		String path = e.getActionCommand();

		Search search = null;
		try {
			search = new Search(filters, path);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		assert search != null;
		FutureTask<String>
				task = new FutureTask<>(search,
				"");

		ExecutorService executor = Executors.newFixedThreadPool(1);

		// submit futureTask1 to ExecutorService
		executor.submit(task);

		while (!task.isDone()) {
			String result = search.getLastResult();
			if (result!=null && !result.equals("")) {
				SearchResults.addResult(result);
			}
		}
	}
}
