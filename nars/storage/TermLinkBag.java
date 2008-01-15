
package nars.storage;

import nars.entity.*;
import nars.gui.MainWindow;
import nars.main.Parameters;

/**
 * Contains CompositionLinks to relevant Terms.
 */
public class TermLinkBag extends Bag<TermLink> {

    private static final int maxTakeOut = Parameters.MAX_TAKE_OUT_K_LINK;
    
    protected int capacity() {
        return Parameters.BELIEF_BAG_SIZE;
    }
    
    protected int forgetRate() {
        return MainWindow.forgetBW.value();
    }
    
    // replace defualt to prevent repeated inference
    public TermLink takeOut(TaskLink tLink) {
        for (int i = 0; i < maxTakeOut; i++) {
            TermLink bLink = takeOut();
            if (bLink == null)
                return null;
            if (tLink.novel(bLink)) {
                return bLink;
            }
            putBack(bLink);
        }
        return null;
    }
}

