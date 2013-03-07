/*
 * Reasoner.java
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

import java.util.ArrayList;


import nars.storage.*;
import nars.io.*;
import nars.main_nogui.ReasonerBatch;
import nars.gui.*;

/**
 * A NARS Reasoner has its memory, I/O channels, and internal clock.
 * <p>
 * Create static main window and input channel, reset memory, and manage system clock.
 */
public class Reasoner extends ReasonerBatch {
    /** The unique main window */
    MainWindow mainWindow;
    /** Input experience from a window */
    private InputWindow inputWindow;
    /**
     * Start the initial windows and memory. Called from NARS only.
     * @param name The name of the reasoner
     */
    public Reasoner(String name) {
    	super();
        this.name = name;
        memory = new Memory(this);
        mainWindow = new MainWindow(this, name);
        inputWindow = new InputWindow(this, name);
        inputChannels.add(inputWindow);
        outputChannels.add(mainWindow);
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public InputWindow getInputWindow() {
        return inputWindow;
    }
}
