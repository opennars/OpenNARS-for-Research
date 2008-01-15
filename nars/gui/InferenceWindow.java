
package nars.gui;

import java.awt.*;
import java.awt.event.*;
import nars.io.Record;

/**
 * Window displying inference log
 */
public class InferenceWindow extends NarsFrame implements ActionListener {
    /* GUI components */    
    private Button      playButton, stopButton, hideButton;
    private TextArea	text;
    
    /**
     * Constructor
     */
    public InferenceWindow() {
        super("Inference log");
        setBackground(SINGLE_WINDOW_COLOR);
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);
        
        c.ipadx = 3;
        c.ipady = 3;
        c.insets = new Insets(5,5,5,5);
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
        playButton = new Button("Play");
        gridbag.setConstraints(playButton, c);
        playButton.addActionListener(this);
        add(playButton);
        
        stopButton = new Button("Stop");
        gridbag.setConstraints(stopButton, c);
        stopButton.addActionListener(this);
        add(stopButton);
        
        hideButton = new Button("Hide");
        gridbag.setConstraints(hideButton, c);
        hideButton.addActionListener(this);
        add(hideButton);
        
        setBounds(400, 200, 400, 400);
    }
    
    /**
     * Clear display
     */
    public void clear() {
        text.setText("");
    }
        
    /**
     * Append a new line to display
     */
    public void append(String str) {
        text.append(str);
    }
    
    /**
     * Handling button click
     * @param e The ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == playButton) {
            Record.play();
        } else if (s == stopButton) {
            Record.stop();
        } else if (s == hideButton) {
            Record.stop();
            dispose();
        }
    }
}
