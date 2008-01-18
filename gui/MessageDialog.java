
package nars.gui;

import java.awt.*;
import java.awt.event.*;

/**
 * Pop-up message for the user
 */
public class MessageDialog extends Dialog implements ActionListener {
    protected Button button;
    protected TextArea text;
    
    /**
     * Constructor
     * @param parent The parent Frame
     * @param message The text to be displayed
     */    
    public MessageDialog(Frame parent, String message) {
        super(parent, "Message", false);
        setLayout(new BorderLayout(5, 5));
        setBackground(NarsFrame.SINGLE_WINDOW_COLOR);
        setFont(NarsFrame.NarsFont);
        text = new TextArea(message);
        text.setBackground(NarsFrame.DISPLAY_BACKGROUND_COLOR);
        this.add("Center", text);
        button = new Button(" OK ");
        button.addActionListener(this);
        Panel p = new Panel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        p.add(button);
        this.add("South", p);
        setModal(true);
        setBounds(200, 250, 400, 180);
        setVisible(true);
    }
    
    /**
     * Handling button click
     * @param e The ActionEvent
     */    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            this.setVisible(false);
            this.dispose();
        }
    }
}