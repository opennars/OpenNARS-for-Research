/*
 * Center.java
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

import nars.io.*;
import nars.gui.MainWindow;
import nars.entity.Stamp;

/**
 * The control center of the system.
 * <p>
 * Create static main window and input channel, reset memory, and manage system clock.
 */
public class Center {
    /** The unique main window */
    public static MainWindow mainWindow;
    /** The unique input channel */
    public static ExperienceIO experienceIO;
    /** System clock, relatively defined to guaranttee the repeatability of behaviors */
    private static long clock;
    /** Timer for fixed distance walking */
    private static long stoper;
    /** Flag for running continously */
    private static boolean running;

    /**
     * Start the initial windows and memory. Called from NARS only.
     */
    public static void start() {
        mainWindow = new MainWindow();
        experienceIO = new ExperienceIO();
        reset();
    }

    /**
     * Reset the system with an empty memory and reset clock. Called locally and from MainWindow.
     */
    public static void reset() {
        stoper = 0;
        clock = 0;
        running = false;
        Stamp.init();
        Record.init();
        Memory.init();
        mainWindow.init();
    }

    /**
     * Walk a fixed number of steps or continously. Called from MainWindow only.
     * @param i The number of steps of inference, or infinite if negative
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
     * A clock tick. Run one working cycle or read input. Called from NARS only.
     */
    public static void tick() {
        if (stoper == 0) {
            stoper = experienceIO.loadLine();
        }
        if (running || (stoper > 0)) {
            clock++;
            Record.append(" --- " + clock + " ---\n");
            mainWindow.tickTimer();
            Memory.cycle();
            if (stoper > 0) {
                stoper--;
            }
        }
    }

    /**
     * Get the current time from the clock
     * Called in nars.entity.Stamp
     * @return The current time
     */
    public static long getTime() {
        return clock;
    }
}
