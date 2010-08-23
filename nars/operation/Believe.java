/*
 * Believe.java
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

package nars.operation;

import java.util.ArrayList;
import nars.entity.*;
import nars.language.*;
import nars.main.*;
import nars.io.Symbols;

/**
 * To activate a group of Terms
 */
public class Believe extends Operator {
    public Believe(String name) {
        super(name);
    }
    
    public ArrayList<Task> execute(Task task) {
        Sentence sentence = task.getSentence();
        Inheritance operation = (Inheritance) sentence.getContent();
        ArrayList<Term> list = operation.parseOperation("^believe");
        Term content = list.get(1);
        String str = list.get(2).getName();
        TruthValue truth = TruthValue.make(str);
        if (truth == null) {
            return null;
        }
        Judgment j = new Judgment(content, truth, sentence.getStamp());
        Task newTask = new Task(j, task.getBudget());
        ArrayList<Task> feedback = new ArrayList<Task>();
        feedback.add(newTask);
        Memory.executedTask(task);
        return feedback;
    }
}

