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

/**
 * A Quest is a question on the desire-value of a statement
 */
public class Quest extends Question {

    public Quest(Term term, char punc, Stamp s) {
        super(term, punc, s);
    }

    public Quest(Goal g) {
        content = g.cloneContent();
        punctuation = Symbols.QUEST_MARK;
        stamp = new Stamp(0, true);
    }
}
