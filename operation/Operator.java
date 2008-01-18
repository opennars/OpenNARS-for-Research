
package nars.operation;

import java.util.*;
import java.io.*;
import nars.language.Term;
import nars.entity.Task;

/**
 * An individual operator that can be execute by the system.
 * The only file to modify when adding a new operator into NARS
 */
public abstract class Operator extends Term {
    public Operator(String name) {
        super(name);
    }
    
    // required method for every operation
    public abstract Object execute(Task task);

    // register the operators in the memory
    // the only method to modify when adding a new operator into NARS
    // an operator should contain at least two characters after "^""
    public static HashMap<String, Operator> setOperators() {
        HashMap<String, Operator> table = new HashMap<String, Operator>();
        table.put("^go-to", new GoTo("^go-to"));
        table.put("^pick", new Pick("^pick"));
        table.put("^open", new Open("^open"));
        return table;
    }
}

