package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class SearchResults implements GComponent, ActionListener {
	private JList<String> list;
	private JScrollPane scroll;
	private JPanel panel;

	public SearchResults() {
		this.panel = new JPanel(new BorderLayout());
		
		List<String> myList = new ArrayList<>(10);
		for (int index = 0; index < 200; index++) {
			myList.add("List Item " + index);
		}

		this.list = new JList<String>(myList.toArray(new String[myList.size()]));
		this.scroll = new JScrollPane();
		this.scroll.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);
		this.panel.add(this.scroll);
		this.scroll.setBounds(5, 60, 550, 490);
		list.setBounds(5, 60, 550, 490);
		this.panel.setBounds(5, 60, 550, 490);
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
