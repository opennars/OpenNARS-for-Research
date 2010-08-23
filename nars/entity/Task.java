/*
 * Task.java
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
package nars.entity;

import nars.language.Term;
import nars.main.NARS;

/**
 * A task to be processed, consists of a Sentence and a BudgetValue
 */
public class Task extends Item implements Cloneable {
    /** The sentence of the Task */
    private Sentence sentence;
    /** Whether it is derived by a structural rule */
    protected boolean structural = false;        // 

    /**
     * Constructor
     * @param s The sentence
     * @param b The budget
     */
    public Task(Sentence s, BudgetValue b) {
        super(b);
        sentence = s;
        key = sentence.forTaskKey();
    }

    public Object clone() {
        return new Task(sentence, (BudgetValue) this);
    }

    /**
     * Get the sentence
     * @return The sentence
     */
    public Sentence getSentence() {
        return sentence;
    }

    /**
     * Directly get the content of the sentence
     * @return The content of the sentence
     */
    public Term getContent() {
        return sentence.getContent();
    }

    /**
     * Check if a Task is derived by a StructuralRule
     * @return Whether the Task is derived by a StructuralRule
     */
    public boolean isStructural() {
        return structural;
    }

    /**
     * Record if a Task is derived by a StructuralRule
     */
    public void setStructural() {
        structural = true;
    }

    /**
     * Merge one Task into another
     * @param that The other Task
     */
    public void merge(Item that) {
        super.merge(that);
        structural = (structural || ((Task) that).isStructural());
    }

    /**
     * Get a String representation of the Task
     * @return The Task as a String
     */
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        if (NARS.isStandAlone()) {
            s.append(super.toString() + " ");
        }
        s.append(sentence);
        return s.toString();
    }

    /**
     * Get a String representation of the Task, with reduced accuracy
     * @return The Task as a String, with 2-digit accuracy for the values
     */
    @Override
    public String toString2() {
        StringBuffer s = new StringBuffer();
        if (NARS.isStandAlone()) {
            s.append(super.toString2() + " ");
        }
        if (sentence instanceof Question) {
            s.append(sentence);
        } else {
            s.append(((Judgment) sentence).toString2());
        }
        return s.toString();
    }
}
