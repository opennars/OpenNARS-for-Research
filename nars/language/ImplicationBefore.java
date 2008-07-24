/*
 * ImplicationBefore.java
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

import nars.inference.TemporalRules;
import nars.io.Symbols;
import nars.main.Memory;

/**
 * Temporal Implication relation, predicate before subject.
 */
public class ImplicationBefore extends Implication implements Temporal {

    /**
     * Constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     */
    private ImplicationBefore(String n, ArrayList<Term> arg) {
        super(n, arg);
    }

    /**
     * Constructor with full values, called by clone
     * @param n The name of the term
     * @param cs Component list
     * @param open Open variable list
     * @param i Syntactic complexity of the compound
     */
    private ImplicationBefore(String n, ArrayList<Term> cs, ArrayList<Variable> open, short i) {
        super(n, cs, open, i);
    }

    /**
     * Clone an object
     * @return A new object
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object clone() {
        return new ImplicationBefore(name, (ArrayList<Term>) cloneList(components), (ArrayList<Variable>) cloneList(openVariables), complexity);
    }

    /**
     * Try to make a new compound from two components. Called by the inference rules.
     * @param subject The first compoment
     * @param predicate The second compoment
     * @return A compound generated or a term it reduced to
     */
    public static ImplicationBefore make(Term subject, Term predicate) {  // to be extended to check if subject is Conjunction
        if (invalidStatement(subject, predicate)) {
            return null;
        }
        String name = makeStatementName(subject, Symbols.IMPLICATION_BEFORE_RELATION, predicate);
        Term t = Memory.nameToListedTerm(name);
        if (t != null) {
            return (ImplicationBefore) t;
        }
        if (predicate instanceof ImplicationBefore) {
            Term oldCondition = ((ImplicationBefore) predicate).getSubject();
            Term newCondition = ConjunctionSequence.make(oldCondition, subject);
            return make(newCondition, ((ImplicationBefore) predicate).getPredicate());
        } else if (!(predicate instanceof Implication)) {
            ArrayList<Term> argument = argumentsToList(subject, predicate);
            return new ImplicationBefore(name, argument);
        } else {
            return null;
        }
    }

    /**
     * Get the operator of the term.
     * @return the operator of the term
     */
    @Override
    public String operator() {
        return Symbols.IMPLICATION_BEFORE_RELATION;
    }

    /**
     * The relation is retrospective
     * @return BEFORE means the condition is post-condition
     */
    @Override
    public TemporalRules.Relation getTemporalOrder() {
        return TemporalRules.Relation.BEFORE;
    }
}
