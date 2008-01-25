/*
 * Record.java
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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

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

