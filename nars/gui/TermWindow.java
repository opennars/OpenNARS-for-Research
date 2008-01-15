package nars.gui;

import java.awt.*;
import java.awt.event.*;

import nars.entity.Concept;
import nars.main.Memory;

/**
 * Window accept a Term, then display its Concept
 */
public class TermWindow extends NarsFrame implements ActionListener {
    /* GUI components */    
    private Label       termLabel;
    private TextField   termField;
    private Button      playButton, hideButton;
    private TextArea	text;
    
    /**
     * Constructor
     */
    TermWindow() {
        super("Term Window");
        setBackground(SINGLE_WINDOW_COLOR);
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);
        
        c.ipadx = 3;
        c.ipady = 3;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        termLabel = new Label("Term:", Label.RIGHT);
        termLabel.setBackground(SINGLE_WINDOW_COLOR);
        gridbag.setConstraints(termLabel, c);
        add(termLabel);
        
        c.weightx = 1.0;
        termField = new TextField("");
        gridbag.setConstraints(termField, c);
        add(termField);
        
        c.weightx = 0.0;
        playButton = new Button("Show");
        playButton.addActionListener(this);
        gridbag.setConstraints(playButton, c);
        add(playButton);
        
        hideButton = new Button("Hide");
        hideButton.addActionListener(this);
        gridbag.setConstraints(hideButton, c);
        add(hideButton);
        
        setBounds(400, 0, 400, 100);
    }
    
    /**
     * Handling button click
     * @param e The ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        Button b = (Button) e.getSource();
        if (b == playButton) {
            String name = termField.getText().trim();
            Concept concept = Memory.nameToConcept(name);
            if (concept != null)
                concept.startPlay();
        } else if (b == hideButton)
            setVisible(false);
    }
}
