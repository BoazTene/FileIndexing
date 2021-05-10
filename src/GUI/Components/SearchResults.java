package gui.Components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

/**
 * This class represents the SearchResults component.
 */
public class SearchResults implements gui.Components.GComponent, ActionListener {
	private final JPanel panel;
	private static DefaultListModel<String> defaultListModel;

	/**
	 * This is the constructor, it initializing the Search Result properties.
	 */
	public SearchResults() {
		this.panel = new JPanel(new BorderLayout());
		
		defaultListModel = new DefaultListModel<String>();

		JList<String> results = new JList<String>(defaultListModel);

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(results);
		results.setLayoutOrientation(JList.VERTICAL);
		this.panel.add(scroll);
		scroll.setBounds(5, 60, 550, 490);
		results.setBounds(5, 60, 550, 490);
		this.panel.setBounds(5, 60, 550, 490);

		results.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList)evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = list.locationToIndex(evt.getPoint());
					String path = defaultListModel.elementAt(index);
					try {
						if (!path.equals("Search ended!"))
						Runtime.getRuntime().exec("explorer.exe /select," + path);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (evt.getClickCount() == 3) {

					// Triple-click detected
					int index = list.locationToIndex(evt.getPoint());
				}
			}
		});
	}

	/**
	 * This method add an result to window.
	 * @param result The added result.
	 */
	public static void addResult(String result) {
		defaultListModel.addElement(result);
	}

	/**
	 * This method clear all the results.
	 */
	public static void resetResults() {
		defaultListModel.clear();
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return this.panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
