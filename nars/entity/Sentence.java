/*
 * Sentence.java
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

import nars.inference.TemporalRules;
import nars.io.Symbols;
import nars.language.*;
import nars.main.*;

/**
 * A Sentence is an abstract class, mainly containing a Term, a TruthValue, and a Stamp.
 *<p>
 * It is used as the premises and conclusions of all inference rules.
 */
public abstract class Sentence implements Cloneable {

    /** The content of a Sentence is a Term */
    protected Term content;
    /** The punctuation also indicates the type of the Sentence: Judgment, Question, or Goal */
    protected char punctuation;
    /** The tense of a Sentence can be BEFORE(past), WHEN(present), AFTER(future), or NONE */
    protected TemporalRules.Relation tense = TemporalRules.Relation.NONE;
    /** The truth value of Judgment or desire value of Goal */
    protected TruthValue truth = null;
    /** Partial record of the derivation path */
    protected Stamp stamp = null;
    /** Whether it is an input sentence */
    protected boolean input = false;
    /** For Question and Goal: best solution found so far */
    protected Judgment bestSolution = null;

    /**
     * Make a Sentence from an input String. Called by StringParser.
     * @param term The content of the sentence
     * @param punc The puncuation (and therefore, type) of the sentence
     * @param tense The tense of the sntense
     * @param truth The truth value of the sentence, if it is a Judgment (or Goal)
     * @param stamp The stamp of the truth value (for Judgment or Goal)
     * @return the Sentence generated from the arguments
     */
    public static Sentence make(Term term, char punc, TemporalRules.Relation tense, TruthValue truth, Stamp stamp) {
        if (term instanceof CompoundTerm) {
            ((CompoundTerm) term).renameVariables();
        }
        switch (punc) {
            case Symbols.JUDGMENT_MARK:
                return new Judgment(term, punc, tense, truth, stamp);
            case Symbols.GOAL_MARK:
                return new Goal(term, punc, truth, stamp);
            case Symbols.QUESTION_MARK:
                return new Question(term, punc, tense, stamp);
            default:
                return null;
        }
    }

    /**
     * Make a derived Sentence from a template and some initial values. Called by Memory.
     * @param term The content of the sentence
     * @param oldS A sample sentence providing the type of the new sentence
     * @param tense The tense of the sentence
     * @param truth The truth value of the sentence, if it is a Judgment (or Goal)
     * @param stamp The stamp of the truth value (for Judgment or Goal)
     * @return the Sentence generated from the arguments
     */
    public static Sentence make(Sentence oldS, Term term, TemporalRules.Relation tense, TruthValue truth, Stamp stamp) {
        if (term instanceof CompoundTerm) {
            ((CompoundTerm) term).renameVariables();
        }
        if (oldS instanceof Question) {
            return new Question(term, Symbols.QUESTION_MARK, tense, stamp);
        }
        if (oldS instanceof Goal) {
            return new Goal(term, Symbols.GOAL_MARK, truth, stamp);
        }
        return new Judgment(term, Symbols.JUDGMENT_MARK, tense, truth, stamp);
    }

    /**
     * Clone the Sentence
     * @return The clone
     */
    @Override
    public Object clone() {
        return make(content, punctuation, tense, truth, stamp);
    }

    /**
     * Get the content of the sentence
     * @return The content Term
     */
    public Term getContent() {
        return content;
    }

    /**
     * Clone the content of the sentence
     * @return A clone of the content Term
     */
    public Term cloneContent() {
        return (Term) content.clone();
    }

    /**
     * Set the content Term of the Sentence
     * @param t The new content
     */
    public void setContent(Term t) {
        content = t;
    }

    /**
     * Get the tense of the Sentence
     * @return The tense of the Sentence
     */
    public TemporalRules.Relation getTense() {
        return tense;
    }

    /**
     * Set the tense of the Sentence
     * @param t The new tense of the Sentence
     */
    public void setTense(TemporalRules.Relation t) {
        tense = t;
    }

    /**
     * Get the truth value of the sentence
     * @return Truth value, null for question
     */
    public TruthValue getTruth() {
        return truth;
    }

    /**
     * Get the stamp of the sentence
     * @return The stamp
     */
    public Stamp getStamp() {
        return stamp;
    }

    /**
     * Distinguish Judgment from Goal ("instanceof Judgment" doesn't work)
     * @return Whether the object is a Judgment
     */
    public boolean isJudgment() {
        return (punctuation == Symbols.JUDGMENT_MARK);
    }

    /**
     * Check input sentence
     * @return Whether the
     */
    public boolean isInput() {
        return input;
    }

    /**
     * The only place to change the default, called in StringParser
     */
    public void setInput() {
        input = true;
    }

    /**
     * Get the best-so-far solution for a Question or Goal
     * @return The stored Judgment or null
     */
    public Judgment getBestSolution() {
        return bestSolution;
    }

    /**
     * Set the best-so-far solution for a Question or Goal
     * @param judg The solution to be remembered
     */
    public void setBestSolution(Judgment judg) {
        bestSolution = judg;
        if (input) {
            Memory.report(judg, false);     // report answer to input question
        }
    }

    /**
     * Check whether one sentence has stamp overlapping with another one, and change the system cash
     * @param that The sentence to be checked against
     * @return Whether the two have overlapping stamps
     */
    public boolean noOverlapping(Sentence that) {
        Memory.currentStamp = Stamp.make(stamp, that.getStamp());
        return (Memory.currentStamp != null);
    }

    /**
     * Get a String representation of the sentence
     * @return The String
     */
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(content.getName());
        s.append(punctuation + " ");
        s.append(tenseToString());
        if (truth != null) {
            s.append(truth.toString());
        }
        if (NARS.isStandAlone()) {
            s.append(stamp.toString());
            if (bestSolution != null) {
                s.append("BestSolution: " + bestSolution);
            }
        }
        return s.toString();
    }

    /**
     * Get a String representation of the sentence, with 2-digit accuracy
     * @return The String
     */
    public String toString2() {
        StringBuffer s = new StringBuffer();
        s.append(content.getName());
        s.append(punctuation + " ");
        s.append(tenseToString());
        if (truth != null) {
            s.append(truth.toString2());
        }
        if (NARS.isStandAlone()) {
            s.append(stamp.toString());
            if (bestSolution != null) {
                s.append("BestSolution: " + bestSolution);
            }
        }
        return s.toString();
    }

    /**
     * Get a String representation of the tense of the sentence
     * @return The String
     */
    private String tenseToString() {
        if (tense == TemporalRules.Relation.BEFORE) {
            return Symbols.TENSE_PAST + " ";
        }
        if (tense == TemporalRules.Relation.WHEN) {
            return Symbols.TENSE_PRESENT + " ";
        }
        if (tense == TemporalRules.Relation.AFTER) {
            return Symbols.TENSE_FUTURE + " ";
        }
        return "";
    }
}
