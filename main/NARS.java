package nars.main;

import java.applet.*;

/**
 * The main class of the poject.
 * <p>
 * Define an application with full funcationality and a demonstration applet with limited functionality.
 * <p>
 * Manage the internal working thread. Communicate with Center only.
 */
public class NARS extends Applet implements Runnable {
    /**
     * The information about the author, version, and copyright of the project.
     */
    public static final String INFO =
            "     Open-NARS     Version 1.0.0     January 2008  \n";
    /**
     * The project website.
     */
    public static final String WEBSITE =
      " Open-NARS website:  http://code.google.com/p/open-nars/ \n" +
      "      NARS website:  http://nars.wang.googlepages.com/ ";
    /**
     * Flag to distinguish the two running modes of the project.
     */
    private static boolean standAlone = false;  // application or applet
    /**
     * The internal working thread of the system.
     */
    Thread narsThread = null;
    
    /**
     * The entry point of the standalone application.
     * <p>
     * Create an instance of the class, then run the init and start methods,
     * shared by the application and the applet.
     * @param args no arguments are used
     */
    public static void main(String args[]) {
        standAlone = true;
        NARS nars = new NARS();
        nars.init();
        nars.start();
    }
    
    /**
     * Initialize the system at the control center.
     */
    public void init() {
        Center.start();
    }
       
    /**
     * start the thread if necessary, called when the page containing the applet first appears on the screen.
     */
    public void start() {
        if (narsThread == null) {
            narsThread = new Thread(this);
            narsThread.start();
        }
    }
    
    /**
     * The stop() method is called when the page containing the applet is no longer on the screen.
     */
    public void stop() {
        narsThread = null;
    }
    
    /**
     * Repeatedly execute NARS working cycle. This method is called when the Runnable's thread is started.
     */
    public void run() {
        Thread thisThread = Thread.currentThread();
        while (narsThread == thisThread) {
            try {
                thisThread.sleep(10);
            } catch (InterruptedException e){
            }
            Center.tick();
//            repaint();      // necessary for the applet?
        }
    }
    
    /**
     * Whether the project running as an application.
     * @return true for application; false for applet.
     */
    public static boolean isStandAlone() {
        return standAlone;
    }
    
    /**
     * Provide system information for the applet.
     * @return The string containing the information about the applet.
     */
    public String getAppletInfo() {
        return INFO;
    }
}