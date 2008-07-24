/*
 * ConjunctionSequence.java
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
import nars.inference.*;
import nars.io.*;
import nars.main.Memory;

/**
 * A sequential conjunction of Statements.
 */
public class ConjunctionSequence extends Conjunction implements Temporal {

    /**
     * constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     */
    private ConjunctionSequence(String n, ArrayList<Term> arg) {
        super(n, arg);
    }

    /**
     * Constructor with full values, called by clone
     * @param n The name of the term
     * @param cs Component list
     * @param open Open variable list
     * @param i Syntactic complexity of the compound
     */
    private ConjunctionSequence(String n, ArrayList<Term> cs, ArrayList<Variable> open, short i) {
        super(n, cs, open, i);
    }

    /**
     * Clone an object
     * @return A new object
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object clone() {
        return new ConjunctionSequence(name, (ArrayList<Term>) cloneList(components), (ArrayList<Variable>) cloneList(openVariables), complexity);
    }

    /**
     * Try to make a new compound from a list of components. Called by StringParser.
     * @param argument the list of arguments
     * @return the Term generated from the arguments
     */
    public static Term make(ArrayList<Term> argument) {
        if (argument.size() == 1) {
            return argument.get(0);
        }
        String name = makeCompoundName(Symbols.PRODUCT_OPERATOR, argument);
        Term t = Memory.nameToListedTerm(name);
        return (t != null) ? t : new ConjunctionSequence(name, argument);
    }

    public static Term make(Term term1, Term term2) {
        ArrayList<Term> argument = new ArrayList<Term>();
        argument.add(term1);
        argument.add(term2);
        return make(argument);
    }

    /**
     * Get the operator of the term.
     * @return the operator of the term
     */
    @Override
    public String operator() {
        return Symbols.SEQUENCE_OPERATOR;
    }

    /**
     * Check if the compound is communitative.
     * @return true for communitative
     */
    @Override
    public boolean isCommutative() {
        return false;
    }

    /**
     * The components are temporally sorted
     * @return AFTER means the components happen in that order
     */
    @Override
    public TemporalRules.Relation getTemporalOrder() {
        return TemporalRules.Relation.AFTER;
    }
}
