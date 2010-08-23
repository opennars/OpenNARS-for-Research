/*
 * Implication.java
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
 * A Statement about an Inheritance relation.
 */
public class Implication extends Statement {

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
    protected Implication(String n, ArrayList<Term> arg) {
        super(n, arg);
    }

    /**
     * Constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     * @param order The temporal order of the components
     */
    protected Implication(String n, ArrayList<Term> arg, int order) {
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
    protected Implication(String n, ArrayList<Term> cs, ArrayList<Variable> open, short i) {
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
    protected Implication(String n, ArrayList<Term> cs, ArrayList<Variable> open, short i, int order) {
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
            return new Implication(name, (ArrayList<Term>) cloneList(components),
                    (ArrayList<Variable>) cloneList(openVariables), complexity, temporalDistance);
        } else {
            return new Implication(name, (ArrayList<Term>) cloneList(components),
                    (ArrayList<Variable>) cloneList(openVariables), complexity);
        }
    }

    /**
     * Try to make a new compound from two components. Called by the inference rules.
     * @param subject The first compoment
     * @param predicate The second compoment
     * @param temporal Whether there is a temporal relation
     * @param order The temporal order of the components
     * @return A compound generated or a term it reduced to
     */
    public static Implication make(Term subject, Term predicate, boolean temporal, int order) {
        if ((subject instanceof Implication) || (subject instanceof Equivalence) || (predicate instanceof Equivalence)) {
            return null;
        }
        if (invalidStatement(subject, predicate)) {
            return null;
        }
        String sym = temporal ? getSymbol(order) : Symbols.IMPLICATION_RELATION;
        String name = makeStatementName(subject, sym, predicate);
        Term t = Memory.nameToListedTerm(name);
        if (t != null) {
            return (Implication) t;
        }
        if (predicate instanceof Implication) {
            Term oldCondition = ((Implication) predicate).getSubject();
            if ((oldCondition instanceof Conjunction) && ((Conjunction) oldCondition).containComponent(subject)) {
                return null;
            }
            Term newCondition;
            if (temporal) {
                newCondition = Conjunction.make(subject, oldCondition, order);
                return make(newCondition, ((Implication) predicate).getPredicate(), true, order);
            } else {
                newCondition = Conjunction.make(subject, oldCondition, -1);
                return make(newCondition, ((Implication) predicate).getPredicate(), false, 0);
            }
        } else {
            ArrayList<Term> argument = argumentsToList(subject, predicate);
            if (temporal) {
                return new Implication(name, argument, order);
            } else {
                return new Implication(name, argument);
            }
        }
    }

    /**
     * Get the operator of the term.
     * @return the operator of the term
     */
    public String operator() {
        if (isTemporal) {
            return getSymbol(temporalDistance);
        }
        return Symbols.IMPLICATION_RELATION;
    }

    /**
     * Get the order of the components.
     * @return the order within the term
     */
    @Override
    public int getOrder() {
        return temporalDistance;
    }

    /**
     * Convert a TemporalValue into its String representation
     * @param t The temporal value to be represented
     * @return The the String representation
     */
    public static String getSymbol(int t) {
        if (t > 0) {
            return Symbols.IMPLICATION_AFTER_RELATION;
        }
        if (t < 0) {
            return Symbols.IMPLICATION_BEFORE_RELATION;
        }
        return Symbols.IMPLICATION_WHEN_RELATION;
    }

    @Override
    public boolean isTemporal() {
        return isTemporal;
    }
}
