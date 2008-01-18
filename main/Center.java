
package nars.main;

import nars.entity.Base;
import nars.io.*;
import nars.gui.*;

/**
 * The control center of the system.
 * <p>
 * Create static main and inpit windows, reset memory, and manage system clock.
 */
public class Center {   // One Center per NARS, all members are static
    /**
     * The unique main window of the system.
     */
    public static MainWindow mainWindow;
    /**
     * The unique input window of the system.
     */
    public static InputWindow inputWindow;
    /**
     * System clock, relatively defined to guaranttee the repeatability of behaviors.
     */
    private static long clock;
    /**
     * Timer for fixed distance walking.
     */
    private static long stoper;
    /**
     * Running continously.
     */
    private static boolean running;
    
    /**
     * Start the initial windows and memory. Called from NARS only.
     */
    public static void start() {
        mainWindow = new MainWindow();
        inputWindow = new InputWindow();
        reset();
    }
    
    /**
     * Reset the system with an empty memory and reset clock. Called locally and from MainWindow.
     */
    public static void reset() {
        stoper = 0;
        clock = 0;
        Base.init();
        Memory.init();
        running = false;
    }
    
    /**
     * Walk a fixed number of steps. Called from MainWindow only.
     * @param i the number of steps of inference
     */
    public static void setStoper(long i) {
        if (i < 0) {
            running = true;
            stoper = 0;
        } else {
            running = false;
            stoper = i;
        }
    }
    
    /**
     * A clock tick. Called from NARS only.
     */
    public static void tick() {
        if (stoper == 0)
            stoper = inputWindow.getInput();
        if (running || (stoper > 0)) {
            clock++;
            Record.append(" --- " + clock + " ---\n");
            mainWindow.tickTimer();
            Memory.cycle();
            if (stoper > 0)
                stoper--;
        }
    }
    
    /**
     * Redisplay the input window. Called from MainWindow only.
     */
    public static void showInputWindow() {
        inputWindow.setVisible(true);
    }
}
