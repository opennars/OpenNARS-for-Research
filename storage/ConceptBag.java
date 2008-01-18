
package nars.storage;

import nars.entity.Concept;
import nars.main.Parameters;
import nars.gui.MainWindow;

/**
 * Contains Concepts.
 */
public class ConceptBag extends Bag<Concept> {
    
    protected int capacity() {
        return Parameters.CONCEPT_BAG_SIZE;
    }
    
    // this is for active concept only
    protected int forgetRate() {
        return MainWindow.forgetCW.value();
    }
}