/* 
 * The MIT License
 *
 * Copyright 2019 The OpenNARS authors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package nars.main;

import javax.swing.SwingUtilities;

import nars.gui.InputWindow;
import nars.gui.MainWindow;
import nars.main_nogui.ReasonerBatch;

/**
 * A NARS Reasoner has its memory, I/O channels, and internal clock.
 * <p>
 * Create static main window and input channel, reset memory, and manage system
 * clock.
 */
public class Reasoner extends ReasonerBatch {

    /**
     * The unique main window
     */
    MainWindow mainWindow;
    /**
     * Input experience from a window
     */
    private InputWindow inputWindow;

    /**
     * Start the initial windows and memory. Called from NARS only.
     *
     * @param name The name of the reasoner
     */
    Reasoner(String name) {
        super();
        this.name = name;
        inputWindow = new InputWindow(this, name);
        mainWindow = new MainWindow(this, name);
        inputChannels.add(inputWindow);
        outputChannels.add(mainWindow);
        mainWindow.setVisible(true);
    }

    @Override
    public void tick() {
        final ReasonerBatch reasoner = this;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                reasoner.doTick();
            }
        });
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public InputWindow getInputWindow() {
        return inputWindow;
    }

    @Override
    public long updateTimer() {
        return mainWindow.updateTimer();
    }

    @Override
    public void initTimer() {
        mainWindow.initTimer();
    }

    @Override
    public void tickTimer() {
        mainWindow.tickTimer();
    }

    @Override
    public long getTimer() {
        return mainWindow.getTimer();
    }
}
