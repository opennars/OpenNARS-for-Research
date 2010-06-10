/*
 * NARS.java
 *
 * Copyright (C) 2008  Pei Wang
 *
 * This file is part of Open-NARS.
 *
 * Open-NARS is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Open-NARS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Open-NARS.  If not, see <http://www.gnu.org/licenses/>.
 */
package nars.main;

import java.applet.*;

/**
 * The main class of the project.
 * <p>
 * Define an application with full funcationality and an applet with partial functionality.
 * <p>
 * Manage the internal working thread. Communicate with Center only.
 */
public class NARS extends Applet implements Runnable {

    /**
     * The information about the version and date of the project.
     */
    public static final String INFO =
            "     Open-NARS     Version 1.3.3     June 2010  \n";
    /**
     * The project websites.
     */
    public static final String WEBSITE =
            " Open-NARS website:  http://code.google.com/p/open-nars/ \n" +
            "      NARS website:  http://sites.google.com/site/narswang/ ";
    /**
     * Flag to distinguish the two running modes of the project.
     */
//    private static boolean standAlone = false;  // application or applet
    /**
     * The internal working thread of the system.
     */
    Thread narsThread = null;

    /**
     * The entry point of the standalone application.
     * <p>
     * Create an instance of the class, then run the init and start methods.
     * @param args no arguments are used
     */
    public static void main(String args[]) {
//        standAlone = true;
        NARS nars = new NARS();
        nars.init();
        nars.start();
    }

    /**
     * Initialize the system at the control center.
     */
    @Override
    public void init() {
        Center.start();
    }

    /**
     * Start the thread if necessary, called when the page containing the applet first appears on the screen.
     */
    @Override
    public void start() {
        if (narsThread == null) {
            narsThread = new Thread(this);
            narsThread.start();
        }
    }

    /**
     * Called when the page containing the applet is no longer on the screen.
     */
    @Override
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
                Thread.sleep(2);
            } catch (InterruptedException e) {
            }
            Center.tick();
        }
    }

    /**
     * Whether the project running as an application.
     * @return true for application; false for applet.
     */
    public static boolean isStandAlone() {
        return true;    // make the application and the applet identical, for now
//        return standAlone;
    }

    /**
     * Provide system information for the applet.
     * @return The string containing the information about the applet.
     */
    @Override
    public String getAppletInfo() {
        return INFO;
    }
}
