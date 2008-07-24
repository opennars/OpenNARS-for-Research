/*
 * MainWindow.java
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

import nars.io.*;
import nars.main.*;

/**
 * Main window of NARS GUI
 */
public class MainWindow extends NarsFrame implements ActionListener {

    /** Experience display area */
    private TextArea ioText;
    /** Control buttons */
    private Button stopButton,  walkButton,  runButton,  exitButton;
    /** Clock display field */
    private TextField timerText;
    /** Label of the clock */
    private Label timerLabel;
    /** System clock */
    private long timer;
    /** Whether the experience is saving into a file */
    private boolean savingExp = false;
    /** Input experience window */
    public static InputWindow inputWindow;
    /** Window to accept a Term to be looked into */
    public static TermWindow conceptWin;
    /** Windows for run-time parameter adjustment */
    public static ParameterWindow forgetTW,  forgetBW,  forgetCW,  silentW;

    /**
     * Constructor
     */
    public MainWindow() {
        super("NARS");
        setBackground(MAIN_WINDOW_COLOR);
        MenuBar menuBar = new MenuBar();

        Menu m = new Menu("File");
        m.add(new MenuItem("Load Experience"));
        m.add(new MenuItem("Save Experience"));
        m.addSeparator();
        m.add(new MenuItem("Record Inference"));
        m.addActionListener(this);
        menuBar.add(m);

        m = new Menu("Memory");
        m.add(new MenuItem("Initialize"));
        m.addSeparator();
        m.add(new MenuItem("Load Memory"));
        m.add(new MenuItem("Save Memory"));
        m.addActionListener(this);
        menuBar.add(m);

        m = new Menu("View");
        m.add(new MenuItem("Concepts"));
        m.add(new MenuItem("Task Buffer"));
        m.add(new MenuItem("Inference Log"));
        m.add(new MenuItem("Concept Content"));
        m.add(new MenuItem("Input Window"));
        m.addActionListener(this);
        menuBar.add(m);

        m = new Menu("Parameter");
        m.add(new MenuItem("Concept Forgetting Rate"));
        m.add(new MenuItem("Task Forgetting Rate"));
        m.add(new MenuItem("Belief Forgetting Rate"));
        m.addSeparator();
        m.add(new MenuItem("Report Silence Level"));
        m.addActionListener(this);
        menuBar.add(m);

        m = new Menu("Help");
        m.add(new MenuItem("Related Information"));
        m.add(new MenuItem("About NARS"));
        m.addActionListener(this);
        menuBar.add(m);

        setMenuBar(menuBar);

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
        ioText = new TextArea("");
        ioText.setBackground(DISPLAY_BACKGROUND_COLOR);
        ioText.setEditable(false);
        gridbag.setConstraints(ioText, c);
        add(ioText);

        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        runButton = new Button(" Run ");
        gridbag.setConstraints(runButton, c);
        runButton.addActionListener(this);
        add(runButton);
        walkButton = new Button(" Walk ");
        gridbag.setConstraints(walkButton, c);
        walkButton.addActionListener(this);
        add(walkButton);
        stopButton = new Button(" Stop ");
        gridbag.setConstraints(stopButton, c);
        stopButton.addActionListener(this);
        add(stopButton);
        timerLabel = new Label("Clock:", Label.RIGHT);
        timerLabel.setBackground(MAIN_WINDOW_COLOR);
        gridbag.setConstraints(timerLabel, c);
        add(timerLabel);

        c.weightx = 1.0;
        timerText = new TextField("");
        timerText.setBackground(DISPLAY_BACKGROUND_COLOR);
        timerText.setEditable(false);
        gridbag.setConstraints(timerText, c);
        add(timerText);

        c.weightx = 0.0;
        exitButton = new Button(" Exit ");
        gridbag.setConstraints(exitButton, c);
        exitButton.addActionListener(this);
        add(exitButton);

        setBounds(0, 250, 400, 350);
        setVisible(true);

        inputWindow = new InputWindow();
        conceptWin = new TermWindow();
        forgetTW = new ParameterWindow("Task Forgetting Rate", Parameters.TASK_LINK_FORGETTING_CYCLE);
        forgetBW = new ParameterWindow("Belief Forgetting Rate", Parameters.TERM_LINK_FORGETTING_CYCLE);
        forgetCW = new ParameterWindow("Concept Forgetting Rate", Parameters.CONCEPT_FORGETTING_CYCLE);
        silentW = new ParameterWindow("Report Silence Level", Parameters.SILENT_LEVEL);

        initTimer();
    }

    /**
     * Initialize the system for a new run
     */
    public void init() {
        if (timer != 0) {
            Center.experienceIO.saveLine(timer + "");
            initTimer();
        }
        ioText.setText("");
    }

    /**
     * Reset timer and its display
     */
    private void initTimer() {
        timer = 0;
        timerText.setText(timer + "");
    }

    /**
     * Update timer and its display
     */
    public void tickTimer() {
        timer++;
        timerText.setText(timer + "");
    }

    /**
     * Add new line to the display, plus the time interval
     * @param line The text to be displayed
     */
    public void post(String line) {
        if (timer > 0) {
            String timeLine = new String(timer + "\n");
            ioText.append(timeLine);
            Center.experienceIO.saveLine(timeLine);
        }
        if (line != null) {
            ioText.append(line);
            Center.experienceIO.saveLine(line);
        }
        initTimer();
    }

    /**
     * Handling button click
     * @param e The ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj instanceof Button) {
            if (obj == runButton) {
                Center.setStoper(-1);
            } else if (obj == stopButton) {
                Center.setStoper(0);
            } else if (obj == walkButton) {
                Center.setStoper(1);
            } else if (obj == exitButton) {
                setVisible(false);
                System.exit(0);
            }
        } else if (obj instanceof MenuItem) {
            String label = e.getActionCommand();
            if (label.equals("Load Experience")) {
                Center.experienceIO.openLoadFile();
            } else if (label.equals("Save Experience")) {
                savingExp = !savingExp;
                if (savingExp) {
                    ioText.setBackground(SAVING_BACKGROUND_COLOR);
                    Center.experienceIO.openSaveFile();
                } else {
                    post(null);
                    ioText.setBackground(DISPLAY_BACKGROUND_COLOR);
                    Center.experienceIO.closeSaveFile();
                }
            } else if (label.equals("Record Inference")) {
                if (Record.isLogging()) {
                    Record.closeLogFile();
                } else {
                    Record.openLogFile();
                }
            } else if (label.equals("Initialize")) {
                Center.reset();
            } else if (label.equals("Concepts")) {
                Memory.conceptsStartPlay("Active Concepts");
            } else if (label.equals("Task Buffer")) {
                Memory.newTasksStartPlay("Task Buffer");
            } else if (label.equals("Inference Log")) {
                Record.show();
                Record.play();
            } else if (label.equals("Concept Content")) {
                conceptWin.setVisible(true);
            } else if (label.equals("Input Window")) {
                inputWindow.setVisible(true);
            } else if (label.equals("Task Forgetting Rate")) {
                forgetTW.setVisible(true);
            } else if (label.equals("Belief Forgetting Rate")) {
                forgetBW.setVisible(true);
            } else if (label.equals("Concept Forgetting Rate")) {
                forgetCW.setVisible(true);
            } else if (label.equals("Report Silence Level")) {
                silentW.setVisible(true);
            } else if (label.equals("Related Information")) {
                new MessageDialog(this, NARS.WEBSITE);
            } else if (label.equals("About NARS")) {
                new MessageDialog(this, NARS.INFO);
            } else {
                new MessageDialog(this, UNAVAILABLE);
            }
        }
    }
}
