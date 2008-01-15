
package nars.io;

import nars.gui.*;

/**
 * Inference log, different for (demo) applet and (working) application
 */
public class Record {    
    /** the display window */
    private static InferenceWindow window = new InferenceWindow();
    /** whether to display */
    private static boolean isReporting = false;
    
    /** initialize the window */
    public static void init() {
        window.clear();
    }
    
    /** show the window */
    public static void show() {
        window.setVisible(true);
    }
    
    /** begin the display */
    public static void play() {
        isReporting = true;
    }
    
    /** stop the display */
    public static void stop() {
        isReporting = false;
    }

    /** add new text to display */
    public static void append(String s) {
        if (isReporting)
            window.append(s);
    }
}

