/*
 * TaskBuffer.java
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

package nars.storage;

import nars.entity.Task;
import nars.main.*;

/**
 * New tasks that contain new Term.
 */
public class TaskBuffer extends Bag<Task> {
    
    /**
     * Get the (constant) capacity of TaskBuffer
     * @return The capacity of TaskBuffer
     */
    protected int capacity() {
        return Parameters.TASK_BUFFER_SIZE;
    }
    
    /**
     * Get the (constant) forget rate in TaskBuffer
     * @return The forget rate in TaskBuffer
     */
    protected int forgetRate() {
        return Parameters.NEW_TASK_FORGETTING_CYCLE;
    }

    /**
     * Sepecial treatment: the display also include Tasks in the NewTask list
     * @return New Tasks in the buffer and list for display
     */
    @Override
    public String toString() {
        return Memory.newTasksToString() + super.toString();
    }
}

