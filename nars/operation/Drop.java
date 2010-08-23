/*
 * Drop.java
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
import nars.entity.Task;
import nars.main.Memory;

/**
 * A class used in testing only.
 */
public class Drop extends Operator {
    public Drop(String name) {
        super(name);
    }

    public ArrayList<Task> execute(Task task) {
        Memory.executedTask(task);
        return null;
    }
}
