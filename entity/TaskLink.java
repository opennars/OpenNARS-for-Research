
package nars.entity;

import java.util.*;
import nars.language.Term;
import nars.main.Parameters;

/**
 * Reference to a Task.
 * <p>
 * The reason to separate a Task and a TaskLink is that a Task can be linked from multiple Concepts, with different BudgetValue.
 */
public class TaskLink extends TermLink {
    private static final int RECORD_LENGTH = Parameters.TASK_INFERENCE_RECORD_LENGTH;
    private Task targetTask;        // now "target" means a term !!!
    private ArrayList<String> record; // remember the CompositionLinks that has been used recently
    
    public TaskLink(Task t, TermLink template, BudgetValue v) {
        super(v);
        if (template == null) {
            type = TermLink.SELF;
            index = null;
        } else {
            type = template.getType();
            index = template.getIndices();            
        }
        targetTask = t;
        record = new ArrayList<String>(Parameters.TASK_INFERENCE_RECORD_LENGTH);
        setKey();
        key += t.getKey();
    }
    
    public Task getTargetTask() {
        return targetTask;
    }
    
    public ArrayList<String> getRecord() {
        return record;
    }
    
    public void merge(Item that) {
        ((BudgetValue) this).merge(that.getBudget());
        ArrayList<String> v = ((TaskLink) that).getRecord();
        for (int i = 0; i < v.size(); i++)
            if (record.size() <= RECORD_LENGTH)
                record.add(v.get(i));
    }

    // To check whether a TaskLink can use a TermLink
    // return false if they intereacted recently
    // called in CompositionBag only
    // move into Task ?
    public boolean novel(TermLink bLink) {
        Term bTerm = bLink.getTarget();
        if (bTerm.equals(targetTask.getSentence().getContent()))
            return false;
        String key = bLink.getKey();
        for (int i = 0; i < record.size(); i++)
            if (key.equals((String) record.get(i)))
                return false;
        record.add(key);       // add knowledge reference to record
//        if (record.size() > RECORD_LENGTH)         // keep a constant length --- allow repeatation
//            record.remove(0);
        return true;
    }
}

