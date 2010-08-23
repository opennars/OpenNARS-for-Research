/*
 * Judgment.java
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

import nars.io.Symbols;
import nars.language.Term;
import nars.main.Parameters;

/**
 * A Judgment is an piece of new knowledge to be absorbed.
 */
public class Judgment extends Sentence {

    public Judgment _premise1 = null;
    public Judgment _premise2 = null;

    /**
     * Constructor
     * @param term The content
     * @param punc The punctuation
     * @param t The truth value
     * @param b The stamp
     * @param premise1 The first premise to record in the new Judgment. May be null.
     * This is only used for display.  It is not used in processing.
     * @param premise2 The second premise to record in the new Judgment. May be null.
     * This is only used for display.  It is not used in processing.
     */
    public Judgment(Term term, char punc, TruthValue t, Stamp b, Sentence premise1, Sentence premise2) {
        content = term;
        punctuation = punc;
        truth = t;
        stamp = b;
//        if (premise1 instanceof Judgment) {
//            _premise1 = (Judgment) premise1;
//        }
//        if (premise2 instanceof Judgment) {
//            _premise2 = (Judgment) premise2;
//        }
    }

    /**
     * Construct a Judgment from a term
     * @param t The content of the judgment
     */
    public Judgment(Term t) {
        content = t;
        punctuation = Symbols.JUDGMENT_MARK;
        truth = new TruthValue(1.0f, Parameters.DEFAULT_JUDGMENT_CONFIDENCE);
        stamp = new Stamp(0, true);
    }

    public Judgment(Term t, TruthValue v, Stamp s) {
        content = t;
        punctuation = Symbols.JUDGMENT_MARK;
        truth = v;
        stamp = s;
    }

    /**
     * Check whether the judgment is equivalent to another one
     * <p>
     * The two may have different keys
     * @param that The other judgment
     * @return Whether the two are equivalent
     */
    boolean equivalentTo(Judgment that) {
        assert content.equals(that.getContent());
        return (truth.equals(that.getTruth()) && stamp.equals(that.getStamp()));
    }

    public void setTruth(TruthValue v) {
        truth = v;
    }

    /**
     * To display the premises used to derive a judgment
     */
//    public String toStringWithPremises(String indentation) {
//        String result = indentation + toString() + "\n";
//        if (_premise1 != null) {
//            result = result + _premise1.toStringWithPremises(indentation + "  ");
//        }
//        if (_premise2 != null) {
//            result = result + _premise2.toStringWithPremises(indentation + "  ");
//        }
//        return result;
//    }
}
