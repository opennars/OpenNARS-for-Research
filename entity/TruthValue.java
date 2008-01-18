
package nars.entity;

import nars.language.Term;
import nars.io.*;

/**
 * Frequency and confidence.
 */
public class TruthValue { // implements Cloneable {
    public static final char DELIMITER = Symbols.TRUTH_VALUE_MARK;       // default
    public static final char SEPARATOR = Symbols.VALUE_SEPARATOR;    // default
    protected ShortFloat frequency;
    protected ShortFloat confidence;
        
    public TruthValue(float f, float c) {
        frequency = new ShortFloat(f);
        confidence = new ShortFloat(c);
    }
    
    public TruthValue(TruthValue v) {
        frequency = new ShortFloat(v.getFrequency());
        confidence = new ShortFloat(v.getConfidence());
    }
        
    public float getFrequency() {
        return frequency.getValue();
    }
        
    public float getConfidence() {
        return confidence.getValue();
    }
        
    public float getExpectation() {
        return (float) (confidence.getValue() * (frequency.getValue() - 0.5) + 0.5);
    }
    
    public float getExpDifAbs(float e) {
        return Math.abs(e - getExpectation());
    }
    
    public float getExpDifAbs(TruthValue t) {
        return getExpDifAbs(t.getExpectation());
    }
    
    public boolean equals(Object that) {
        return ((that instanceof TruthValue) 
                && (getFrequency() == ((TruthValue) that).getFrequency())
                && (getConfidence() == ((TruthValue) that).getConfidence()));
    }
    
    // full output
    public String toString() {
        return DELIMITER + frequency.toString() + SEPARATOR + confidence.toString() + DELIMITER;
    }
    
    // short output
    public String toString2() {
        return DELIMITER + frequency.toString2() + SEPARATOR + confidence.toString2() + DELIMITER;
    }
}
