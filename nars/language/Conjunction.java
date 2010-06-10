/*
 * Conjunction.java
 *
 * Copyright (C) 2008  Pei Wang
 *
 * This file is part of Open-NARS.
 *
 * Open-NARS is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Open-NARS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Open-NARS.  If not, see <http://www.gnu.org/licenses/>.
 */
package nars.language;

import java.util.*;

import nars.io.Symbols;
import nars.main.Memory;

/**
 * Conjunction of statements
 */
public class Conjunction extends CompoundTerm {

    /**
     * Temporal order between the components: 1 for sequential, 0 for parallel, -1 for N/A
     */
    private int temporalOrder = -1;

    /**
     * Constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     * @param order The temporal order of the components
     */
    protected Conjunction(String n, ArrayList<Term> arg, int order) {
        super(n, arg);
        temporalOrder = order;
        name = makeName();          // repeat to get the temporal operator
    }

    /**
     * Constructor with full values, called by clone
     * @param n The name of the term
     * @param cs Component list
     * @param open Open variable list
     * @param i Syntactic complexity of the compound
     * @param order The temporal order of the components
     */
    protected Conjunction(String n, ArrayList<Term> cs, ArrayList<Variable> open, short i, int order) {
        super(n, cs, open, i);
        temporalOrder = order;
        name = makeName();          // repeat to get the temporal operator
    }

    /**
     * Clone an object
     * @return A new object
     */
    @SuppressWarnings("unchecked")
    public Object clone() {
        return new Conjunction(name, (ArrayList<Term>) cloneList(components), (ArrayList<Variable>) cloneList(openVariables), complexity, temporalOrder);
    }

    /**
     * Get the operator of the term.
     * @return the operator of the term
     */
    public String operator() {
        return getConjunctionSymbol(temporalOrder);
    }

    /**
     * COnvert a temporal into its String representation
     * @param t The given temporal value
     * @return String representation of the order
     */
    public static String getConjunctionSymbol(int t) {
        if (t == -1) {
            return Symbols.CONJUNCTION_OPERATOR;
        }
        if (t == 1) {
            return Symbols.SEQUENCE_OPERATOR;
        }
        return Symbols.PARALLEL_OPERATOR;
    }

    /**
     * Check if the compound is communitative.
     * @return true for communitative
     */
    @Override
    public boolean isCommutative() {
        return (temporalOrder < 1);
    }

    /**
     * Return the temporal order.
     * @return Temporal order of the components
     */
    @Override
    public int getOrder() {
        return temporalOrder;
    }

    /**
     * Whether the Term is a parallel conjunction
     * @param t A given term
     * @return If the term is a paralel conjunction
     */
    public static boolean isParallel(Term t) {
        return ((t instanceof Conjunction) && (((Conjunction) t).getOrder() == 0));
    }

    /**
     * Whether the Term is a sequential conjunction
     * @param t A given term
     * @return If the term is a sequential conjunction
     */
    public static boolean isSequence(Term t) {
        return ((t instanceof Conjunction) && (((Conjunction) t).getOrder() == 1));
    }

    @Override
    public boolean isTemporal() {
        return (temporalOrder >= 0);
    }

    /**
     * Try to make a new compound from a list of components. Called by StringParser.
     * @return the Term generated from the arguments
     * @param argList the list of arguments
     * @param order The temporal order of the components
     */
    public static Term make(ArrayList<Term> argList, int order) {
        if (order == 1) {
            String name;
            if (argList.size() == 1) {
                return argList.get(0);
            }
            name = makeCompoundName(Symbols.SEQUENCE_OPERATOR, argList);
            Term t = Memory.nameToListedTerm(name);
            return (t != null) ? t : new Conjunction(name, argList, order);
        } else {
            TreeSet<Term> set = new TreeSet<Term>(argList); // sort/merge arguments
            return make(set, order);
        }
    }

    /**
     * Try to make a new compound from a set of components. Called by the public make methods.
     * @param set a set of Term as compoments
     * @param order The temporal order of the components
     * @return the Term generated from the arguments
     */
    public static Term make(TreeSet<Term> set, int order) {
        if (set.isEmpty()) {
            return null;
        }                         // special case: no component
        if (set.size() == 1) {
            return set.first();
        }                         // special case: single component
        ArrayList<Term> argument = new ArrayList<Term>(set);
        String sym = getConjunctionSymbol(order);
        String name = makeCompoundName(sym, argument);
        Term t = Memory.nameToListedTerm(name);
        return (t != null) ? t : new Conjunction(name, argument, order);
    }

    // overload this method by term type?
    /**
     * Try to make a new compound from two components. Called by the inference rules.
     * @param term1 The first compoment
     * @param term2 The second compoment
     * @param order The temporal order of the components
     * @return A compound generated or a term it reduced to
     */
    @SuppressWarnings("unchecked")
    public static Term make(Term term1, Term term2, int order) {
        if (order == 1) {
            ArrayList<Term> argument;
            if (isSequence(term2)) { // to be refined to check other cases
                argument = ((CompoundTerm) term2).cloneComponents();
                argument.add(0, term1);
            } else {
                argument = new ArrayList<Term>();
                argument.add(term1);
                argument.add(term2);
            }
            return make(argument, order);
        } else { // to be refined to check other cases
            TreeSet set;
            if (order == 0) {
                if (isParallel(term1)) {
                    set = new TreeSet(((CompoundTerm) term1).cloneComponents());
                    if (isParallel(term2)) {
                        set.addAll(((CompoundTerm) term2).cloneComponents());
                    } else {
                        set.add((Term) term2.clone());
                    }                          // (&,(&,P,Q),R) = (&,P,Q,R)
                } else if (isParallel(term2)) {
                    set = new TreeSet(((CompoundTerm) term2).cloneComponents());
                    set.add((Term) term1.clone());   // (&,R,(&,P,Q)) = (&,P,Q,R)
                } else {
                    set = new TreeSet();
                    set.add(term1);
                    set.add(term2);
                    return make(set, order);
                }
            } else { // if (order == null)
                if (term1 instanceof Conjunction) {
                    set = new TreeSet(((CompoundTerm) term1).cloneComponents());
                    if (term2 instanceof Conjunction) {
                        set.addAll(((CompoundTerm) term2).cloneComponents());
                    } // (&,(&,P,Q),(&,R,S)) = (&,P,Q,R,S)
                    else {
                        set.add((Term) term2.clone());
                    }                          // (&,(&,P,Q),R) = (&,P,Q,R)
                } else if (term2 instanceof Conjunction) {
                    set = new TreeSet(((CompoundTerm) term2).cloneComponents());
                    set.add((Term) term1.clone());  // (&,R,(&,P,Q)) = (&,P,Q,R)
                } else {
                    set = new TreeSet();
                    set.add(term1);         // clone?
                    set.add(term2);         // clone?
                }
                return make(set, order);
            }
        }
        return null; // report error? how about compound sets?
    }

    /**
     * Given operations special treatment, used in display only.
     * @return The name of the term as a String
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer(Symbols.COMPOUND_TERM_OPENER + operator() + ",");
        for (Term t : components) {
            buf.append(t.toString() + Symbols.ARGUMENT_SEPARATOR);
        }
        buf.setCharAt(buf.length() - 1, Symbols.COMPOUND_TERM_CLOSER);
        return buf.toString();
    }
}
