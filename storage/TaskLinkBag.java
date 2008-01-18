
package nars.storage;

import nars.entity.TaskLink;
import nars.gui.*;
import nars.main.Parameters;

/**
 * TaskLinkBag contains links to tasks.
 */
public class TaskLinkBag extends Bag<TaskLink> {

    protected int capacity() {
        return Parameters.TASK_BAG_SIZE;
    }
    
    protected int forgetRate() {
        return MainWindow.forgetTW.value();
    }
}

