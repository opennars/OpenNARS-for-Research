
package nars.gui;

import java.awt.*;
import java.awt.event.*;

/**
 * Window displaying a system parameter that can be adjusted in run time
 */
public class ParameterWindow extends NarsFrame implements ActionListener, AdjustmentListener {
    /* GUI components */
    private Label       valueLabel;
    private Button      hideButton, undoButton, defaultButton;
    private Scrollbar   valueBar;
    /* parameter values */
    private int defaultValue;
    private int previousValue;
    private int currentValue;
    
    /**
     * Constructor
     * @param title Parameter name
     * @param dft The default value of the parameter
     */
    ParameterWindow(String title, int dft) {
        super(title);
        defaultValue = dft;
        previousValue = dft;
        currentValue = dft;
        setLayout(new GridLayout(3,3,8,4));
        setBackground(SINGLE_WINDOW_COLOR);
        Label sp1 = new Label("");
        sp1.setBackground(SINGLE_WINDOW_COLOR);
        add(sp1);
        valueLabel = new Label(String.valueOf(dft), Label.CENTER);
        valueLabel.setBackground(SINGLE_WINDOW_COLOR);
        add(valueLabel);
        Label sp2 = new Label("");
        sp2.setBackground(SINGLE_WINDOW_COLOR);
        add(sp2);
        add(new Label("0", Label.RIGHT));
        valueBar = new Scrollbar(Scrollbar.HORIZONTAL,dft,0,0,101);
        valueBar.addAdjustmentListener(this);
        add(valueBar);
        add(new Label("100", Label.LEFT));
        undoButton = new Button("Undo");
        undoButton.addActionListener(this);
        add(undoButton);
        defaultButton = new Button("Default");
        defaultButton.addActionListener(this);
        add(defaultButton);
        hideButton = new Button("Hide");
        hideButton.addActionListener(this);
        add(hideButton);
        this.setBounds(300, 300, 250, 120);
    }
    
    /**
     * Get the value of the parameter
     * @return The current value
     */
    public int value() {
        return currentValue;
    }
    
    /**
     * Handling button click
     * @param e The ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == defaultButton) {
            currentValue = defaultValue;
            valueBar.setValue(currentValue);
            valueLabel.setText(String.valueOf(currentValue));
        } else if (s == undoButton) {
            currentValue = previousValue;
            valueBar.setValue(currentValue);
            valueLabel.setText(String.valueOf(currentValue));
        } else if (s == hideButton) {
            previousValue = currentValue;
            setVisible(false);
        }
    }
    
    /**
     * Handling scrollbar movement
     * @param e The AdjustmentEvent
     */
    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getSource() == valueBar) {
            int v = ((Scrollbar) valueBar).getValue();
            valueLabel.setText(String.valueOf(v));
            valueBar.setValue(v);
            currentValue = v;
        }
    }
}
