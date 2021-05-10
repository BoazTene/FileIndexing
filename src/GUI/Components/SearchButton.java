package gui.Components;

import gui.Components.GComponent;
import gui.GuiSearch;
import gui.Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * the search button class - when you press the button the searching action starts
 */
public class SearchButton implements GComponent, ActionListener {
    private final JButton button;

    /*
     * constructor - initializing the SerachButton properties
     */
    public SearchButton()  {
        this.button = new JButton();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resource/searchIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert img != null;
        Image dimg = img.getScaledInstance(25, 30,
                Image.SCALE_SMOOTH);
        this.button.setBackground(Color.RED);
        this.button.setIcon(new ImageIcon(dimg));
        this.button.addActionListener(this);
        this.button.setBounds(555, 30, 25, 30);
    }

    @Override
    public Component getComponent() {
        return this.button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GuiSearch.search(gui.frames.SearchFrame.inputFiled.getText());
    }
}
