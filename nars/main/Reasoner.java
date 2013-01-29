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
import nars.gui.*;
import nars.entity.*;

/**
 * A NARS Reasoner has its memory, I/O channels, and internal clock.
 * <p>
 * Create static main window and input channel, reset memory, and manage system clock.
 */
public class Reasoner {
    /** The name of the reasoner */
    @SuppressWarnings("unused")
	private String name;
    /** The memory of the reasoner */
    private Memory memory;
    /** The input channels of the reasoner */
    private ArrayList<InputChannel> inputChannels;
    /** The output channels of the reasoner */
    private ArrayList<OutputChannel> outputChannels;
    /** The unique main window */
    private MainWindow mainWindow;
    /** Input experience from a window */
    private InputWindow inputWindow;
    /** System clock, relatively defined to guarantee the repeatability of behaviors */
    private long clock;
    /** Flag for running continuously */
    private boolean running;
    /** The number of steps to be carried out */
    private int walkingSteps;

    /**
     * Start the initial windows and memory. Called from NARS only.
     * @param name The name of the reasoner
     */
    public Reasoner(String name) {
        this.name = name;
        memory = new Memory(this);
        mainWindow = new MainWindow(this, name);
        inputWindow = new InputWindow(this, name);
        inputChannels = new ArrayList<InputChannel>();
        inputChannels.add(inputWindow);
        outputChannels = new ArrayList<OutputChannel>();
        outputChannels.add(mainWindow);
    }

    /**
     * Reset the system with an empty memory and reset clock. Called locally and from MainWindow.
     */
    public void reset() {
        running = false;
        walkingSteps = 0;
        clock = 0;
        memory.init();
        Stamp.init();
    }

    public Memory getMemory() {
        return memory;
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public InputWindow getInputWindow() {
        return inputWindow;
    }

    public void addInputChannel(InputChannel channel) {
        inputChannels.add(channel);
    }

    public void removeInputChannel(InputChannel channel) {
        inputChannels.remove(channel);
    }

    public void addOutputChannel(OutputChannel channel) {
        outputChannels.add(channel);
    }

    public void removeOutputChannel(OutputChannel channel) {
        outputChannels.remove(channel);
    }

    /**
     * Get the current time from the clock
     * Called in nars.entity.Stamp
     * @return The current time
     */
    public long getTime() {
        return clock;
    }

    /**
     * Start the inference process
     */
    public void run() {
        running = true;
    }

    /**
     * Carry the inference process for a certain number of steps
     * @param n The number of inference steps to be carried
     */
    public void walk(int n) {
        walkingSteps = n;
    }

    /**
     * Stop the inference process
     */
    public void stop() {
        running = false;
    }

    /**
     * A clock tick. Run one working workCycle or read input. Called from NARS only.
     */
    public void tick() {
        if (walkingSteps == 0) {
            for (InputChannel channelIn : inputChannels) {
                channelIn.nextInput();
            }
        }
        ArrayList<String> output = memory.getExportStrings();
        if (!output.isEmpty()) {
            for (OutputChannel channelOut : outputChannels) {
                channelOut.nextOutput(output);
            }
            output.clear();
        }
        if (running || walkingSteps > 0) {
            clock++;
            mainWindow.tickTimer();
            memory.workCycle(clock);
            if (walkingSteps > 0) {
                walkingSteps--;
            }
        }
    }

    /**
     * To process a line of input text
     * @param text
     */
    public void textInputLine(String text) {
        if (text.isEmpty()) {
            return;
        }
        char c = text.charAt(0);
        if (c == Symbols.RESET_MARK) {
            reset();
            memory.getExportStrings().add(text);
        } else if (c == Symbols.COMMENT_MARK) {
            return;
        } else {
            try {
                int i = Integer.parseInt(text);
                walk(i);
            } catch (NumberFormatException e) {
                Task task = StringParser.parseExperience(new StringBuffer(text), memory, clock);
                if (task != null) {
                    memory.inputTask(task);
                }
            }
        }
    }
}
