
package nars.operation;

import java.io.*;
import nars.entity.Task;

/**
 * A class used in testing only.
 */
public class GoTo extends Operator {
    public GoTo(String name) {
        super(name);
    }
    
    public Object execute(Task task) {
        System.out.println("EXECUTE in " + name + " " + task);
        return null;
    }
}

