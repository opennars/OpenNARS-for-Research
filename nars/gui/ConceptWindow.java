
package nars.gui;

import java.awt.*;
import java.awt.event.*;

import nars.entity.Concept;

/**
 * Window displaying a Concept
 */
public class ConceptWindow extends NarsFrame implements ActionListener {
    /* GUI components */
    private Button      playButton, stopButton, closeButton;
    private TextArea	text;
    /** The concept to be displayed */
    private Concept     concept;
    
    /**
     * Constructor
     * @param concept The concept to be displayed
     */
    public ConceptWindow(Concept concept) {
        super(concept.getKey());
        this.concept = concept;
        setBackground(MULTIPLE_WINDOW_COLOR);
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
        
        closeButton = new Button("Close");
        gridbag.setConstraints(closeButton, c);
        closeButton.addActionListener(this);
        add(closeButton);
        
        setBounds(400, 60, 400, 270);
        setVisible(true);
    }
    
    /**
     * Display the content of the concept
     * @param str The text to be displayed
     */
    public void post(String str) {
        text.setText(str);
    }
    
    /**
     * Handling button click
     * @param e The ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == playButton) {
            concept.play();
        } else if (s == stopButton) {
            concept.stop();
        } else if (s == closeButton) {
            concept.stop();
            dispose();
        }
    }
}
