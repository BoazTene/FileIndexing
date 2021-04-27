package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

public class SearchResults implements GComponent, ActionListener {
	private final JPanel panel;
	private static DefaultListModel<String> defaultListModel;

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

	public static void addResult(String result) {
		defaultListModel.addElement(result);
	}

	public static void resetResults() {
		defaultListModel.clear();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub


	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return this.panel;
	}

}
