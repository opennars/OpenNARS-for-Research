
package nars.gui;

import java.awt.*;
import java.awt.event.*;

import nars.main.Memory;

/**
 * Input window, accepting user tasks
 */
public class InputWindow extends NarsFrame implements ActionListener {
    /* GUI components */
    Button      okButton, holdButton, clearButton, closeButton;
    TextArea    inputText;
    /** Whether the window is ready to accept new input */
    boolean     ready;
    
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
        c.insets = new Insets(5,5,5,5);
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
        if (b == okButton)
            ready = true;
        else if (b == holdButton)
            ready = false;
        else if (b == clearButton)
            inputText.setText("");
        else if (b == closeButton)
            setVisible(false);
    }
    
    /**
     * Get input lines, and send them to Memory
     * @return Nember of running steps, if any
     */
    public long getInput() {
        while (ready) {
            String input = inputText.getText();
            int EOL = input.indexOf('\n');
            if (EOL < 0)
                EOL = input.length();
            String line = input.substring(0, EOL).trim();
            if (input.length() > EOL)
                inputText.setText(input.substring(EOL+1));
            else
                inputText.setText("");
            if (line.length() == 0) // pause
                ready = false;
            else {
                try {
                    Long i = new Long(line);
                    return i.longValue();
                } catch (NumberFormatException e) {
                    Memory.inputTask(line);
                }
            }
        }
        return 0;
    }
}
