
package nars.operation;

import java.io.*;
import nars.entity.Task;

/**
 * A class used in testing only.
 */
public class Pick extends Operator {
    public Pick(String name) {
        super(name);
    }

    public Object execute(Task task) {
        System.out.println("EXECUTE in " + name + " " + task);
        return null;
    }
}
