/*
 * Remind.java
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

/**
 * To activate a group of Terms
 */
public class Remind extends Operator {

    public Remind(String name) {
        super(name);
    }

    public ArrayList<Task> execute(Task task) {
        Inheritance content = (Inheritance) task.getContent();
        ArrayList<Term> list = content.parseOperation("^remind");
        Term t = list.get(1);
        Concept c = Memory.getConcept(t);
        BudgetValue v = new BudgetValue(Parameters.DEFAULT_QUESTION_PRIORITY, Parameters.DEFAULT_QUESTION_DURABILITY, 1);
        Memory.activateConcept(c, v);
        Memory.executedTask(task);
        return null;
    }
}

