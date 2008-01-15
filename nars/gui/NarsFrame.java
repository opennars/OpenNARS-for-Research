
package nars.gui;

import java.awt.*;

/**
 * Specify shared properties of NARS windows
 */
public abstract class NarsFrame extends Frame {
    /** Color for the background of the main window */
    static final Color MAIN_WINDOW_COLOR = new Color(120, 120, 255);
    /** Color for the background of the windows with unique instantiation */
    static final Color SINGLE_WINDOW_COLOR = new Color(180, 100, 230);
    /** Color for the background of the windows with multiple instantiations */
    static final Color MULTIPLE_WINDOW_COLOR = new Color(100, 220, 100);
    /** Color for the background of the text components that are read-only */
    static final Color DISPLAY_BACKGROUND_COLOR = new Color(200, 230, 220);
    /** Font for NARS GUI */
    static final Font NarsFont = new Font("Helvetica", Font.PLAIN, 11);
    /** Message for unimplemented functions */
    static final String UNAVAILABLE = "\n Not implemented in this demo applet.";
    
    /** Default constructor */
    NarsFrame() {
        super();
    }
    /**
     * Constructor with title and font setting
     * @param title The title displayed by the window
     */    
    NarsFrame(String title) {
        super(" " + title);
        setFont(NarsFont);
    }
}
