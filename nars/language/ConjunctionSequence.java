
package nars.language;

import java.util.*;
import nars.inference.*;
import nars.io.*;
import nars.main.Memory;

/**
 * A sequential conjunction of Statements.
 */
public class ConjunctionSequence extends Conjunction {
    
    /**
     * constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     */
    private ConjunctionSequence(String n, ArrayList<Term> arg) {
        super(n, arg);
    }

    /**
     * constructor with full values, called by clone
     * @param cs component list
     * @param open open variable list
     * @param closed closed variable list
     * @param i syntactic complexity of the compound
     * @param n The name of the term
     */
    private ConjunctionSequence(String n, ArrayList<Term> cs, ArrayList<Variable> open, ArrayList<Variable> closed, short i) {
        super(n, cs, open, closed, i);
    }
    
    /**
     * override the cloning methed in Object
     * @return A new object, to be casted into a Conjunction
     */
    public Object clone() {
        return new ConjunctionSequence(name, (ArrayList<Term>) cloneList(components),
                (ArrayList<Variable>) cloneList(openVariables), (ArrayList<Variable>) cloneList(closedVariables), complexity);
    }
    
    /**
     * Try to make a new compound from a list of components. Called by StringParser.
     * @param argument the list of arguments
     * @return the Term generated from the arguments
     */
    public static Term make(ArrayList<Term> argument) {
        if (argument.size() == 1)
            return argument.get(0);
        String name = makeCompoundName(Symbols.PRODUCT_OPERATOR, argument);
        Term t = Memory.nameToListedTerm(name);
        return (t != null) ? t : new ConjunctionSequence(name, argument);
    }
    
    /**
     * get the operator of the term.
     * @return the operator of the term
     */
    public String operator() {
        return Symbols.SEQUENCE_OPERATOR;
    }
    
    /**
     * Check if the compound is communitative.
     * @return true for communitative
     */
    public boolean isCommutative() {
        return false;
    }

    public CompoundTerm.TemporalOrder getTemporalOrder() {
        return CompoundTerm.TemporalOrder.AFTER;
    }
}
