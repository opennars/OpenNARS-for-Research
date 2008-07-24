/*
 * TemporalRules.java
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
package nars.inference;

import java.util.*;

import nars.entity.*;
import nars.main.*;

/**
 * Temporal inference rules
 * <p> 
 * These rules are independent of the semantic and symtactic inference rules.
 */
public class TemporalRules {

    /** Temporal relations: a default and three specific */
    public enum Relation {

        NONE, BEFORE, WHEN, AFTER
    }

    /**
     * Temporal relationships among three terms
     * @param r12 Temporal relationship from term1 to term2
     * @param r23 Temporal relationship from term2 to term3
     * @return Temporal relationship from term1 to term3
     */
    public static Relation temporalInference(Relation r12, Relation r23) {
        if (r12 == r23) {
            return r12;
        }
        if (r12 == Relation.NONE) {
            return r23;
        }
        if (r23 == Relation.NONE) {
            return r12;
        }
        if (r12 == Relation.WHEN) {
            return r23;
        }
        if (r23 == Relation.WHEN) {
            return r12;
        }
        assert (r12 == reverse(r23));
        return null;
    }

    public static Relation reverse(Relation t1) {
        if (t1 == Relation.BEFORE) {
            return Relation.AFTER;
        }
        if (t1 == Relation.AFTER) {
            return Relation.BEFORE;
        }
        return t1;
    }

    /**
     * Temporal inference in syllogism
     * @param r1 The first premise
     * @param r2 The second premise
     * @param figure The location of the shared term
     * @return The temporal order in the conclusion
     */
    public static Relation temporalSyllogism(Relation r1, Relation r2, int figure) {
        switch (figure) {
            case 11:
                return temporalInference(reverse(r1), r2);
            case 12:
                return temporalInference(reverse(r1), reverse(r2));
            case 21:
                return temporalInference(r1, r2);
            case 22:
                return temporalInference(r1, reverse(r2));
            default:
                return null;
        }
    }

    // called in variable introduction
    /**
     * The temporal inference on tenses
     * @param tense1 The tense of the first premise
     * @param tense2 The tense of the second premise
     * @return The tense of the conclusion
     */
    public static Relation tenseInference(Relation tense1, Relation tense2) {
        if (tense1 == tense2) {
            if (tense1 == Relation.NONE) {
                return Relation.NONE;
            }
            return Relation.WHEN;
        }
        if (((tense1 == Relation.BEFORE) && (tense2 == Relation.WHEN)) || ((tense1 == Relation.WHEN) && (tense2 == Relation.AFTER))) {
            return Relation.AFTER;
        }
        if (((tense1 == Relation.AFTER) && (tense2 == Relation.WHEN)) || ((tense1 == Relation.WHEN) && (tense2 == Relation.BEFORE))) {
            return Relation.BEFORE;
        }
        return null;
    }

    /**
     * Simple temporal regularity discovery [To be refined]
     * <p>
     * called in Memory.immediateProcess
     * @param event1 A new event
     */
    public static void eventProcessing(Task event1) {
        ArrayList<Task> recentEvents = Memory.recentEvents;
        Sentence sentence1 = event1.getSentence();
        TemporalRules.Relation tense1 = sentence1.getTense();
        if (!sentence1.isJudgment() || (tense1 == TemporalRules.Relation.AFTER)) {
            return;
        }
        recentEvents.add(0, event1);
        if (recentEvents.size() > Parameters.MAXMUM_EVENTS_LENGTH) {
            recentEvents.remove(Parameters.MAXMUM_EVENTS_LENGTH);
        }
        Task event2;
        Sentence sentence2;
//        TemporalRules.Relation tense2;
        TemporalRules.Relation order;
        for (int i = 1; i < recentEvents.size(); i++) {
            event2 = recentEvents.get(i);
            sentence2 = event2.getSentence();
//            tense2 = sentence2.getTense();
//            if (tense1 == tense2) {
            order = sentence1.getStamp().orderWith(sentence2.getStamp());
            if (order != null) {
                SyllogisticRules.temporalIndCom(event1, event2, order);
            }
//            }
        }
    }
}
