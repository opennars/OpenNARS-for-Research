/*
 * BagWindow.java
 *
 * Copyright (C) 2008  Pei Wang
 *
 * This file is part of Open-NARS.
 *
 * Open-NARS is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Open-NARS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Open-NARS.  If not, see <http://www.gnu.org/licenses/>.
 */
package nars.gui;

import java.awt.*;
import java.awt.event.*;

import nars.main.Parameters;
import nars.storage.Bag;

/**
 * Window display the priority distribution of items within a given bag
 */
public class BagWindow extends NarsFrame implements ActionListener, AdjustmentListener {

    /** The bag to be displayed */
    private Bag bag;
    /** The lowest level displayed */
    private int showLevel;
    /** Control buttons */
    private Button playButton, stopButton, closeButton;
    /** Display area */
    private TextArea text;
    /** Display label */
    private Label valueLabel;
    /** Adjustable display level */
    private Scrollbar valueBar;
    /** The location of the display area, shifted according to the number of windows openned */
    private static int counter;

    /**
     * Constructor
     * @param b The bag to be displayed
     * @param title The title of the window
     */
    public BagWindow(Bag b, String title) {
        super(title);
        bag = b;
        showLevel = Parameters.BAG_THRESHOLD;
        setBackground(MULTIPLE_WINDOW_COLOR);
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);

        c.ipadx = 3;
        c.ipady = 3;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        text = new TextArea("");
        text.setBackground(DISPLAY_BACKGROUND_COLOR);
        text.setEditable(false);
        gridbag.setConstraints(text, c);
        add(text);

        c.weighty = 0.0;
        c.gridwidth = 1;
        valueLabel = new Label(String.valueOf(showLevel), Label.RIGHT);
        gridbag.setConstraints(valueLabel, c);
        add(valueLabel);

        valueBar = new Scrollbar(Scrollbar.HORIZONTAL, showLevel, 0, 1, Parameters.BAG_LEVEL);
        valueBar.addAdjustmentListener(this);
        gridbag.setConstraints(valueBar, c);
        add(valueBar);

        playButton = new Button("Play");
        gridbag.setConstraints(playButton, c);
        playButton.addActionListener(this);
        add(playButton);

        stopButton = new Button("Stop");
        gridbag.setConstraints(stopButton, c);
        stopButton.addActionListener(this);
        add(stopButton);

        closeButton = new Button("Close");
        gridbag.setConstraints(closeButton, c);
        closeButton.addActionListener(this);
        add(closeButton);

        setBounds(400, 60 + counter * 20, 400, 270);
        counter++;
        setVisible(true);
    }

    /**
     * Post the bag content
     * @param str The text
     */
    public void post(String str) {
        text.setText(str);
    }

    /**
     * The lowest display level
     * @return The level
     */
    public int showLevel() {
        return showLevel;
    }

    /**
     * Handling button click
     * @param e The ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == playButton) {
            bag.play();
        } else if (source == stopButton) {
            bag.stop();
        } else if (source == closeButton) {
            close();
        }
    }

    /**
     * Close the window
     */
    private void close() {
        bag.stop();
        dispose();
        counter--;
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        close();
    }

    /**
     * Handling scrollbar movement
     * @param e The AdjustmentEvent
     */
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getSource() == valueBar) {
            int v = valueBar.getValue();
            valueLabel.setText(String.valueOf(v));
            valueBar.setValue(v);
            showLevel = v;
            bag.play();
        }
    }
}
