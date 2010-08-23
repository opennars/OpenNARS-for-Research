/*
 * Question.java
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
import nars.io.Symbols;
import nars.main.*;

/**
 * A Question is a sentence without a truth value, and may conain query variables
 */
public class Question extends Sentence {
    private boolean needFeedback = false;

    public Question() {
    }

    /**
     * Constructor
     * @param term The content
     * @param punc The punctuation
     * @param s The stamp
     */
    public Question(Term term, char punc, Stamp s) {
        content = term;
        punctuation = punc;
        stamp = s;
    }
    
    /**
     * Construct a question to check whether a goal has been achieved
     * @param g The goal that trigger the question
     */
    public Question(Goal g) {
        content = g.cloneContent();
        punctuation = Symbols.QUESTION_MARK;
        stamp = new Stamp(0, true);
    }

    /**
     * Construct a question to check whether a prediction can be confirmed
     * @param j The judgment that provides a default answer
     */
    public Question(Judgment j) {
        content = j.cloneContent();
        punctuation = Symbols.QUESTION_MARK;
        stamp = Memory.newStamp;
        bestSolution = j;
        needFeedback = true;
//        needFeedback = (stamp.getEventTime() >= Center.getTime());
    }

    public void checkFeedback() {
        if (needFeedback) {
            if (stamp.getEventTime() <= Center.getTime()) {
                needFeedback = false; // only do once
                if ((bestSolution == null) ||
                        (bestSolution.getTruth().getExpectation() < Parameters.DEFAULT_CONFIRMATION_EXPECTATION)) {
                    Memory.lackConfirmation(content, stamp.getEventTime());
                }
            }
        }
    }
}
