/*
 * ConceptBag.java
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

import nars.entity.Concept;
import nars.main.Parameters;
import nars.gui.MainWindow;
import nars.language.Term;

/**
 * Contains Concepts.
 */
public class ConceptBag extends Bag<Concept> {
    
    /**
     * Get the (constant) capacity of ConceptBag
     * @return The capacity of ConceptBag
     */
    protected int capacity() {
        return Parameters.CONCEPT_BAG_SIZE;
    }
    
    /**
     * Get the (adjustable) forget rate of ConceptBag
     * @return The forget rate of ConceptBag
     */
    protected int forgetRate() {
        return MainWindow.forgetCW.value();
    }

    public Term mostActive() {
        int i = TOTAL_LEVEL - 1;
        while (emptyLevel(i)) {
            i--;
        }
        Concept c = itemTable[i].get(0);
        return c.getTerm();
    }
}