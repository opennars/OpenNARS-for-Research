
package nars.language;

import java.util.*;

/**
 * Term with temporal relation with "now"
 */
public abstract class Tense extends CompoundTerm {
    
    protected Tense(String n, ArrayList<Term> arg) {
        super(n, arg);
    }

    protected Tense(String n, ArrayList<Term> cs, ArrayList<Variable> open, ArrayList<Variable> closed, short i) {
        super(n, cs, open, closed, i);
    }

    public static Term make(Term content, CompoundTerm.TemporalOrder order) {
        switch (order) {
            case AFTER:
                return TenseFuture.make(content);
            case WHEN:
                return TensePresent.make(content);
            case BEFORE:
                return TensePast.make(content);
            default:
                return content;
        }
    }
}
