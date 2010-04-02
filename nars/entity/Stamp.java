/*
 * Stamp.java
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

import java.util.*;

import nars.io.Symbols;
import nars.main.*;

/**
 * Each Sentence has a time stamp, consisting the following components:
 * (1) The creation time of the sentence, 
 * (2) The time when the truth-value is supported, 
 * (3) A trail of serial numbers of sentence, from which the sentence is derived.
 * Each input sentence gets a unique serial number.
 * The derived sentences inherits serial numbers from its parents, cut at the length limit.
 */
public class Stamp implements Cloneable {

    /** serial number, for the whole system */
    private static long current = 0;
    /** special value, for time-independent truth-values */
    public static final long ALWAYS = Long.MIN_VALUE;
    /** serial numbers */
    private long[] trail;
    /** trail length */
    private int length;
    /** creation time of the stamp */
    private long creationTime;
    /** creation time of the stamp */
    private long eventTime;

    /**
     * Generate a new stamp, with a new serial number, for new sentence without tense
     */
    public Stamp() {
        current++;
        length = 1;
        trail = new long[length];
        trail[0] = current;
        creationTime = Center.getTime();
        eventTime = ALWAYS;
    }

    /**
     * Generate a new stamp, with a new serial number, for new sentence with numeric tense
     * @param time The time of the event
     * @param relative Whether the time is absolute or relative
     */
    public Stamp(long time, boolean relative) {
        current++;
        length = 1;
        trail = new long[length];
        trail[0] = current;
        creationTime = Center.getTime();
        if (relative) {
            eventTime = creationTime + time;
        } else {
            eventTime = time;
        }
    }

    /**
     * Generate a new stamp, with a new serial number, for input sentence with symbolic tense
     * @param tense The String representing input tense
     */
    public Stamp(String tense) {
        current++;
        length = 1;
        trail = new long[length];
        trail[0] = current;
        creationTime = Center.getTime();
        if (tense.equals(Symbols.TENSE_PAST)) {
            eventTime = creationTime - 1;
        } else if (tense.equals(Symbols.TENSE_FUTURE)) {
            eventTime = creationTime + 1;
        } else {
            eventTime = creationTime;
        }
    }

    /**
     * Generate a new stamp from an existing one, with the same trail but different creation time
     * <p>
     * For single-premise rules
     * @param old The stamp of the single premise
     */
    public Stamp(Stamp old) {
        length = old.length();
        trail = old.getList();
        creationTime = Center.getTime();
        eventTime = old.getEventTime();
    }

    /**
     * Generate a new stamp for derived sentence by merging the two from parents
     * the first one is no shorter than the second
     * @param first The first Stamp
     * @param second The second Stamp
     */
    private Stamp(Stamp first, Stamp second, long time) {
        int i1, i2, j;
        i1 = i2 = j = 0;
        length = Math.min(first.length() + second.length(), Parameters.MAXMUM_STAMP_LENGTH);
        trail = new long[length];
        while (i2 < second.length() && j < length) {
            trail[j] = first.get(i1);
            i1++;
            j++;
            trail[j] = second.get(i2);
            i2++;
            j++;
        }
        while (i1 < first.length() && j < length) {
            trail[j] = first.get(i1);
            i1++;
            j++;
        }
        creationTime = Center.getTime();
        if (Memory.currentTask.getSentence() instanceof Goal) {
            eventTime = ALWAYS;
        } else {
            eventTime = time;
        }
    }

    /**
     * Try to merge two Stamps, return null if have overlap
     * <p>
     * By default, the event time of the first stamp is used in the result
     * @param first The first Stamp
     * @param second The second Stamp
     * @return The merged Stamp, or null
     */
    public static Stamp make(Stamp first, Stamp second) {
        for (int i = 0; i < first.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (first.get(i) == second.get(j)) {
                    return null;
                }
            }
        }
        if (first.length() > second.length()) {
            return new Stamp(first, second, first.getEventTime());
        } else {
            return new Stamp(second, first, first.getEventTime());
        }
    }

    /**
     * Initialize the stamp machenism of the system, called in Center
     */
    public static void init() {
        current = 0;
    }

    /**
     * Return the length of the trail
     * @return Length of the Stamp
     */
    public int length() {
        return length;
    }

    /**
     * Get a number from the trail by index, called in this class only
     * @param i The index
     * @return The number at the index
     */
    long get(int i) {
        return trail[i];
    }

    /**
     * Get the trail, called in this class only
     * @return The trail of numbers
     */
    long[] getList() {
        return trail;
    }

    /**
     * Convert the trail into a set
     */
    TreeSet<Long> toSet() {
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < length; i++) {
            set.add(trail[i]);
        }
        return set;
    }

    /**
     * Check if two stamps contains the same content
     * @param that The Stamp to be compared
     * @return Whether the two have contain the same elements
     */
    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Stamp)) {
            return false;
        }
        if ((creationTime != ((Stamp) that).getCreationTime()) || eventTime != ((Stamp) that).getEventTime()) {
            return false;
        }
        TreeSet<Long> set1 = toSet();
        TreeSet<Long> set2 = ((Stamp) that).toSet();
        return (set1.containsAll(set2) && set2.containsAll(set1));
    }

    /**
     * The hash code of Stamp
     * @return The hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Get the creationTime of the truth-value
     * @return The creation time
     */
    public long getCreationTime() {
        return creationTime;
    }

    /**
     * Get the eventTime of the truth-value
     * @return The event time
     */
    public long getEventTime() {
        return eventTime;
    }

    /**
     * Adjust the eventTime of the truth-value
     * @param d The direction and extent of the change
     */
    public void adjustEventTime(int d) {
        if (eventTime != ALWAYS) {
            eventTime += d;
        }
    }

    /**
     * Get a String form of the Stamp for display
     * Format: {creationTime [: eventTime] : trail}
     * @return The Stamp as a String
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer(" " + Symbols.STAMP_OPENER + creationTime);
        if (eventTime != ALWAYS) {
            buffer.append(" " + Symbols.STAMP_STARTER + " " + eventTime);
        }
        buffer.append(" " + Symbols.STAMP_STARTER + " ");
        for (int i = 0; i < length; i++) {
            buffer.append(Long.toString(trail[i]));
            if (i < (length - 1)) {
                buffer.append(Symbols.STAMP_SEPARATOR);
            } else {
                buffer.append(Symbols.STAMP_CLOSER + " ");
            }
        }
        return buffer.toString();
    }
}
