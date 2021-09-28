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

import nars.io.ExperienceReader;
import nars.main_nogui.CommandLineParameters;
import nars.main_nogui.NARSBatch;
import nars.main_nogui.NAR;

/**
 * The main class of the open-nars project.
 * <p>
 * Manage the internal working thread. Communicate with Reasoner only.
 */
public class NARS implements Runnable {

    /**
     * The information about the version and date of the project.
     */
    public static final String INFO = "Open-NARS\tVersion 1.5.8\tFebruary 2019 \n";
    /**
     * The project web sites.
     */
    public static final String WEBSITE
            = " Open-NARS website:  http://code.google.com/p/open-nars/ \n"
            + "      NARS website:  http://sites.google.com/site/narswang/";
    /**
     * The internal working thread of the system.
     */
    Thread narsThread = null;
    /**
     * The reasoner
     */
    Reasoner reasoner;

    /**
     * The entry point of the standalone application.
     * <p>
     * Create an instance of the class
     *
     * @param args optional argument used : one input file, possibly followed by
     * --silence <integer>
     */
    public static void main(String args[]) {
        NARSBatch.setStandAlone(true);
        NARS nars = new NARS();
        nars.init(args);
        nars.start();
    }

    /**
     * TODO multiple files
     *
     * @param args Input file
     */
    public void init(String[] args) {
        reasoner = new Reasoner("NARS Reasoner");
        if ((args.length > 0) && CommandLineParameters.isReallyFile(args[0])) {
            ExperienceReader experienceReader = new ExperienceReader(reasoner);
            experienceReader.openLoadFile(args[0]);
        }
        CommandLineParameters.decode(args, reasoner);
    }

    /**
     * Start the thread if necessary, called when the page containing the applet
     * first appears on the screen.
     */
    public void start() {
        if (narsThread == null) {
            narsThread = new Thread(this, "Inference");
            narsThread.start();
        }
    }

    /* Implementing the Runnable Interface */
    /**
     * Repeatedly execute NARS working cycle. This method is called when the
     * Runnable's thread is started.
     */
    @Override
    public void run() {
        Thread thisThread = Thread.currentThread();
        while (narsThread == thisThread) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
            try {
                reasoner.tick();
            } catch (Exception e) {
            }
        }
    }
}
