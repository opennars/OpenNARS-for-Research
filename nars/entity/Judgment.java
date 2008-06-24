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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package nars.entity;

import nars.language.*;
import nars.main.*;
import nars.io.Symbols;
import nars.entity.TruthValue;
import nars.inference.*;

/**
 * A Judgment is an piece of new knowledge to be absorbed.
 */
public class Judgment extends Sentence {
    
    public Judgment(Term term, char punc, TemporalRules.Relation s, TruthValue t, Base b) {
        content = term;
        punctuation = punc;
        tense = s;
        truth = t;
        base = b;
    }
    
    // operation executed
    public Judgment(Goal g) {
        content = g.cloneContent();
        punctuation = Symbols.JUDGMENT_MARK;
        tense = TemporalRules.Relation.BEFORE;
        truth = new TruthValue(1.0f, Parameters.DEFAULT_JUDGMENT_CONFIDENCE);
        base = new Base();
    }
    
    public TruthValue getTruth() {
        return truth;
    }

    public float getFrequency() {
        return truth.getFrequency();
    }

    public float getConfidence() {
        return truth.getConfidence();
    }

    public Base getBase() {
        return base;
    }

    boolean equivalentTo(Judgment judgment2) {
        return (truth.equals(judgment2.getTruth()) && base.equals(judgment2.getBase())); // may have different key
    }

    public float getExpectationDifference(Judgment that) {
        return getTruth().getExpDifAbs(that.getTruth());
    }
    
    public float solutionQuality(Sentence sentence) {
        Term problem = sentence.getContent(); 
        if (sentence instanceof Goal) 
            return truth.getExpectation();
        else if (problem.isConstant())          // "yes/no" question
            return truth.getConfidence();                                 // by confidence
        else                                                            // "what" question or goal
            return truth.getExpectation() / content.getComplexity();      // by likelihood/simplicity, to be refined
    }
    
    public boolean noOverlapping(Judgment judgment) {
        Base b = Base.make(base, judgment.getBase());
        if (b == null)
            return false;
        Memory.currentBase = b;
        return true;
    }
}

