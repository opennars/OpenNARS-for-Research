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
package nars.gui;

import nars.entity.Concept;
import nars.entity.Task;
import nars.storage.BagObserver;
import nars.storage.ConceptBag;

/**
 *
 * @author Peter
 * 
 * This class is a collection of methods moved from Concept.java, Memory.java and
 * Bag.java. These methods are needed for proper GUI functionality. 
 * Idea is to completely separate GUI from reasoner and remove any dependability 
 * from one another
 * 
 * Note: taskBuffersStartPlay method was commented out previously !!!
 * 
 */
final class StartPlay {
    
    private StartPlay(){}
    
    /**
     * Start display active concepts on given bagObserver, called from MainWindow.
     *
     * we don't want to expose fields concepts and novelTasks, AND we want to
     * separate GUI and inference, so this method takes as argument a 
     * {@link BagObserver} and calls {@link ConceptBag#addBagObserver(BagObserver, String)} ;
     * 
     * see design for {@link Bag} and {@link nars.gui.BagWindow}
     * in {@link Bag#addBagObserver(BagObserver, String)}
     *
     * @param bagObserver bag Observer that will receive notifications
     * @param title the window title
     */
    public static void conceptsStartPlay(BagObserver<Concept> bagObserver, ConceptBag concepts, String title ){
        bagObserver.setBag(concepts);
        concepts.addBagObserver(bagObserver, title);
    }

    /**
     * Display new tasks, called from MainWindow. see
     * {@link #conceptsStartPlay(BagObserver, String)}
     *
     * @param bagObserver
     * @param s the window title
     */
    public static void taskBuffersStartPlay(BagObserver<Task> bagObserver, ConceptBag concepts, String s ){
        //bagObserver.setBag(concepts);
        //reasoner.getInternalBuffer().addBagObserver(bagObserver, s);
    }
}
