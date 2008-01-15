
package nars.language;

import java.util.*;
import nars.inference.*;
import nars.io.*;
import nars.main.Memory;

/** 
 * A disjunction of Statements.
 */
public class Disjunction extends CompoundTerm {
    
    /**
     * constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     */
    private Disjunction(String n, ArrayList<Term> arg) {
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
    private Disjunction(String n, ArrayList<Term> cs, ArrayList<Variable> open, ArrayList<Variable> closed, short i) {
        super(n, cs, open, closed, i);
    }
    
    /**
     * override the cloning methed in Object
     * @return A new object, to be casted into a Disjunction
     */
    public Object clone() {
        return new Disjunction(name, (ArrayList<Term>) cloneList(components),
                (ArrayList<Variable>) cloneList(openVariables), (ArrayList<Variable>) cloneList(closedVariables), complexity);
    }

    /**
     * Try to make a new Disjunction from two components. Called by the inference rules.
     * @param term1 The first compoment
     * @param term2 The first compoment
     * @return A Disjunction generated or a Term it reduced to
     */
    public static Term make(Term term1, Term term2) {
        TreeSet set;
        if (term1 instanceof Disjunction) {
            set = new TreeSet(((CompoundTerm) term1).cloneComponents());
            if (term2 instanceof Disjunction)
                set.addAll(((CompoundTerm) term2).cloneComponents());   // (&,(&,P,Q),(&,R,S)) = (&,P,Q,R,S)
            else
                set.add((Term) term2.clone());                          // (&,(&,P,Q),R) = (&,P,Q,R)
        } else if (term2 instanceof Disjunction) {
            set = new TreeSet(((CompoundTerm) term2).cloneComponents()); 
            set.add((Term) term1.clone());                              // (&,R,(&,P,Q)) = (&,P,Q,R)
        } else {
            set = new TreeSet();
            set.add((Term) term1.clone());
            set.add((Term) term2.clone());
        }
        return make(set);
    }
    
    /**
     * Try to make a new IntersectionExt. Called by StringParser.
     * @param argList a list of Term as compoments
     * @return the Term generated from the arguments
     */
    public static Term make(ArrayList<Term> argList) {
        TreeSet<Term> set = new TreeSet<Term>(argList); // sort/merge arguments
        return make(set);
    }

    /**
     * Try to make a new Disjunction from a set of components. Called by the public make methods.
     * @param set a set of Term as compoments
     * @return the Term generated from the arguments
     */
    public static Term make(TreeSet<Term> set) {
        if (set.size() == 1)
            return set.first();                         // special case: single component
        ArrayList<Term> argument = new ArrayList<Term>(set);
        String name = makeCompoundName(Symbols.DISJUNCTION_OPERATOR, argument);
        Term t = Memory.nameToListedTerm(name);
        return (t != null) ? t : new Disjunction(name, argument);
    }
    
    /**
     * get the operator of the term.
     * @return the operator of the term
     */
    public String operator() {
        return Symbols.DISJUNCTION_OPERATOR;
    }

    /**
     * Conjunction is communitative.
     * @return true for communitative
     */
    public boolean isCommutative() {
        return true;
    }    
}
