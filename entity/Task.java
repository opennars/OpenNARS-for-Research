
package nars.entity;

import nars.main.NARS;
import nars.operation.Operator;
import nars.language.*;

/**
 * A task to be processed.
 */
public class Task extends Item {
    private Sentence sentence;
    protected boolean structual = false;        // whether it is based on a structual rule
           
    public Task(Sentence s, BudgetValue b) {
        super(b);
        sentence = s;
        key = sentence.toString();
    }
    
    public Sentence getSentence() {
        return sentence;
    }

    public Term getContent() {
        return sentence.getContent();
    }

    public boolean isStructual() {
        return structual;
    }
    
    public void setStructual() {
        structual = true;
    }

    public void merge(Item that) {
        ((BudgetValue) this).merge(that.getBudget());
        structual = (structual || ((Task) that).isStructual());
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        if (NARS.isStandAlone())
            s.append(super.toString());
        s.append(sentence);
        return s.toString();
    }

    public String toString2() {
        StringBuffer s = new StringBuffer();
        if (NARS.isStandAlone())
            s.append(super.toString2());
        if (sentence instanceof Question)
            s.append(sentence);
        else
            s.append(((Judgment) sentence).toString2());
        return s.toString();
    }
}

