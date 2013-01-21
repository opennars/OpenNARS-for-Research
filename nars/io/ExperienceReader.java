/*
 * ExperienceReader.java
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
package nars.io;

import java.awt.FileDialog;
import java.io.*;

import nars.main.*;

/**
 * To read and write experience as Task streams
 */
public class ExperienceReader implements InputChannel {
    /** Reference to the reasoner */
    private Reasoner reasoner;
    /** Input experience from a file */
    private BufferedReader inExp;
    /** Remaining working cycles before reading the next line */
    private int timer;

    /**
     * Default constructor
     * @param reasoner Backward link to the reasoner
     */
    public ExperienceReader(Reasoner reasoner) {
        this.reasoner = reasoner;
        inExp = null;
    }

    /**
     * Open an input experience file
     */
    public void openLoadFile() {
        FileDialog dialog = new FileDialog((FileDialog) null, "Load experience", FileDialog.LOAD);
        dialog.setVisible(true);
        String directoryName = dialog.getDirectory();
        String fileName = dialog.getFile();
        try {
            inExp = new BufferedReader(new FileReader(directoryName + fileName));
        } catch (IOException ex) {
            System.out.println("i/o error: " + ex.getMessage());
        }
        reasoner.addInputChannel(this);
    }

    /**
     * Close an input experience file
     */
    public void closeLoadFile() {
        try {
            inExp.close();
        } catch (IOException ex) {
            System.out.println("i/o error: " + ex.getMessage());
        }
        reasoner.removeInputChannel(this);
    }

    /**
     * Process the next chunk of input data
     * @return Whether the input channel should be chacked again
     */
    public boolean nextInput() {
        if (timer > 0) {
            timer--;
            return true;
        }
        if (inExp == null) {
            return false;
        }
        String line = null;
        while (timer == 0) {
            try {
                line = inExp.readLine();
                if (line == null) {
                    inExp.close();
                    inExp = null;
                    return false;
                }
            } catch (IOException ex) {
                System.out.println("i/o error: " + ex.getMessage());
            }
            line = line.trim();
            if (line.length() > 0) {
                try {
                    timer = Integer.parseInt(line);
                    reasoner.walk(timer);
                } catch (NumberFormatException e) {
                    reasoner.textInputLine(line);
                }
            }
        }
        return true;
    }
}
