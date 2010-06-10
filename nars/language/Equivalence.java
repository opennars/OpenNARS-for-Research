/*
 * Equivalence.java
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
 * A Statement about an Equivalence relation.
 */
public class Equivalence extends Statement {

    /**
     * Whether there is a temporal order between the components
     */
    private boolean isTemporal = false;
    /**
     * Temporal distance between the components
     */
    private int temporalDistance = 0;

    /**
     * Constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     */
    protected Equivalence(String n, ArrayList<Term> arg) {
        super(n, arg);
    }

    /**
     * Constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     * @param order The temporal order of the components
     */
    protected Equivalence(String n, ArrayList<Term> arg, int order) {
        super(n, arg);
        isTemporal = true;
        temporalDistance = order;
        name = makeName();          // repeat to get the temporal operator
    }

    /**
     * Constructor with full values, called by clone
     * @param n The name of the term
     * @param cs Component list
     * @param open Open variable list
     * @param i Syntactic complexity of the compound
     */
    protected Equivalence(String n, ArrayList<Term> cs, ArrayList<Variable> open, short i) {
        super(n, cs, open, i);
    }

    /**
     * Constructor with full values, called by clone
     * @param n The name of the term
     * @param cs Component list
     * @param open Open variable list
     * @param i Syntactic complexity of the compound
     * @param order The temporal order of the components
     */
    protected Equivalence(String n, ArrayList<Term> cs, ArrayList<Variable> open, short i, int order) {
        super(n, cs, open, i);
        isTemporal = true;
        temporalDistance = order;
        name = makeName();          // repeat to get the temporal operator
    }

    /**
     * Clone an object
     * @return A new object
     */
    @SuppressWarnings("unchecked")
    public Object clone() {
        if (isTemporal) {
            return new Equivalence(name, (ArrayList<Term>) cloneList(components),
                    (ArrayList<Variable>) cloneList(openVariables), complexity, temporalDistance);
        } else {
            return new Equivalence(name, (ArrayList<Term>) cloneList(components),
                    (ArrayList<Variable>) cloneList(openVariables), complexity);
        }
    }

    /**
     * Try to make a new compound from two components. Called by the inference rules.
     * @param subject The first compoment
     * @param predicate The second compoment
     * @param temporal Whether the components are temporally related
     * @param order The temporal order of the components
     * @return A compound generated or null
     */
    public static Equivalence make(Term subject, Term predicate, boolean temporal, int order) {  // to be extended to check if subject is Conjunction
        if ((subject instanceof Implication) || (subject instanceof Equivalence)) {
            return null;
        }
        if ((predicate instanceof Implication) || (predicate instanceof Equivalence)) {
            return null;
        }
        if (invalidStatement(subject, predicate)) {
            return null;
        }
        Term interm;
        if ((subject.compareTo(predicate) > 0) && (order == 0)) {
            interm = subject;
            subject = predicate;
            predicate = interm;
        } else if (order < 0) {
            interm = subject;
            subject = predicate;
            predicate = interm;
            order = 0 - order;
        }
        String sym = getEquivalenceSymbol(temporal, order);
        String name = makeStatementName(subject, sym, predicate);
        Term t = Memory.nameToListedTerm(name);
        if (t != null) {
            return (Equivalence) t;
        }
        ArrayList<Term> argument = argumentsToList(subject, predicate);
        if (temporal) {
            return new Equivalence(name, argument, order);
        } else {
            return new Equivalence(name, argument);
        }
    }

    /**
     * Get the operator of the term.
     * @return the operator of the term
     */
    public String operator() {
        return getEquivalenceSymbol(isTemporal, temporalDistance);
    }

    /**
     * Check if the compound is communitative.
     * @return true for communitative
     */
    @Override
    public boolean isCommutative() {
        return (temporalDistance == 0);
    }

    /**
     * Get the temporal order.
     * @return the temporal order of the components
     */
    @Override
    public int getOrder() {
        return temporalDistance;
    }

    /**
     * Get the symbole of the relation by tense
     * @param temporal Whether the components are temprally related
     * @param distance The temporal distance between the components
     * @return The String representation of the relation
     */
    public static String getEquivalenceSymbol(boolean temporal, int distance) {
        if (!temporal) {
            return Symbols.EQUIVALENCE_RELATION;
        }
        if (distance > 0) {
            return Symbols.EQUIVALENCE_AFTER_RELATION;
        }
        if (distance < 0) {
            return "ERROR: UNKNOWN EQUIVALENCE";
        }
        return Symbols.EQUIVALENCE_WHEN_RELATION;
    }

    @Override
    public boolean isTemporal() {
        return isTemporal;
    }

    /**
     * Given operations special treatment, used in display only.
     * @return The name of the term as a String
     */
    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer(Symbols.STATEMENT_OPENER + "");
        buf.append(getSubject() + operator());
        buf.append(getPredicate().toString() + Symbols.STATEMENT_CLOSER);
        return buf.toString();
    }
}
