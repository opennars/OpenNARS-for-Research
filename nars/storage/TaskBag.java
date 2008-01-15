
package nars.storage;

import nars.entity.Task;
import nars.gui.MainWindow;
import nars.main.*;

/**
 * New tasks that contain new Term.
 */
public class TaskBag extends Bag<Task> {
    public static final int defaultForgetRate = Parameters.NEW_TASK_DEFAULT_FORGETTING_CYCLE;
    
    protected int capacity() {
        return Parameters.TASK_BUFFER_SIZE;
    }
    
    protected int forgetRate() {
        return MainWindow.forgetTW.value();
    }
    
    // to include immediate tasks
    public String toString() {
        return Memory.resultsToString() + super.toString();
    }
}

