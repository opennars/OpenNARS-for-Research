/*
 * InputWindow.java
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

import nars.io.StringParser;

/**
 * Input window, accepting user tasks
 */
public class InputWindow extends NarsFrame implements ActionListener {

    /** Control buttons */
    private Button okButton,  holdButton,  clearButton,  closeButton;
    /** Input area */
    private TextArea inputText;
    /** Whether the window is ready to accept new input */
    private boolean ready;

    /**
     * Constructor
     */
    public InputWindow() {
        super("Input Window");
        setBackground(SINGLE_WINDOW_COLOR);
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
        inputText = new TextArea("");
        gridbag.setConstraints(inputText, c);
        add(inputText);
        c.weighty = 0.0;
        c.gridwidth = 1;
        okButton = new Button("OK");
        okButton.addActionListener(this);
        gridbag.setConstraints(okButton, c);
        add(okButton);
        holdButton = new Button("Hold");
        holdButton.addActionListener(this);
        gridbag.setConstraints(holdButton, c);
        add(holdButton);
        clearButton = new Button("Clear");
        clearButton.addActionListener(this);
        gridbag.setConstraints(clearButton, c);
        add(clearButton);
        closeButton = new Button("Hide");
        closeButton.addActionListener(this);
        gridbag.setConstraints(closeButton, c);
        add(closeButton);
        setBounds(0, 40, 400, 210);
        setVisible(true);
    }

    /**
     * Initialize the window
     */
    public void init() {
        ready = false;
        inputText.setText("");
    }

    /**
     * Handling button click
     * @param e The ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        Button b = (Button) e.getSource();
        if (b == okButton) {
            ready = true;
        } else if (b == holdButton) {
            ready = false;
        } else if (b == clearButton) {
            inputText.setText("");
        } else if (b == closeButton) {
            setVisible(false);
        }
    }

    /**
     * Get one input lines
     * @return The top line in the input window
     */
    public String getLine() {
        if (ready) {
            String input = inputText.getText().trim();
            int cutPoint;
            while (input.length() > 0) {
                cutPoint = input.indexOf('\n');
                if (cutPoint < 0) {
                    inputText.setText("");
                    return input;
                } else if (cutPoint > 0) {
                    inputText.setText(input.substring(cutPoint + 1));
                    return input.substring(0, cutPoint).trim();
                } else { // (cutPoint == 0)
                    input = input.substring(1, cutPoint).trim();
                }
            }
            ready = false;
            inputText.setText("");
        }
        return null;
    }

    /**
     * Get input lines, and send them to Memory
     * @return Nember of running steps, if any
     */
    public long getInput() {
        while (ready) {
            String input = inputText.getText();
            int EOL = input.indexOf('\n');
            if (EOL < 0) {
                EOL = input.length();
            }
            String line = input.substring(0, EOL).trim();
            if (input.length() > EOL) {
                inputText.setText(input.substring(EOL + 1));
            } else {
                inputText.setText("");
            }
            if (line.length() == 0) {
                ready = false;
            } else {
                try {
                    Long i = new Long(line);
                    return i.longValue();
                } catch (NumberFormatException e) {
                    StringParser.parseTask(line);
                }
            }
        }
        return 0;
    }
}
