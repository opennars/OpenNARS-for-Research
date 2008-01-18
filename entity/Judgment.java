
package nars.entity;

import nars.language.Term;
import nars.main.*;

/**
 * A Judgment is an piece of new knowledge to be absorbed.
 */
public class Judgment extends Sentence {
    
    public Judgment(Term term, char punc, TruthValue t, Base b) {
        content = term;
        punctuation = punc;
        truth = t;
        base = b;
    }
  
    public TruthValue getTruth() {
        return truth;
    }

    public float getFrequency() {
        return truth.getFrequency();
    }

    public float getConfidence() {
        return truth.getConfidence();
    }

    public Base getBase() {
        return base;
    }

    boolean equivalentTo(Judgment judgment2) {
        return (truth.equals(judgment2.getTruth()) && base.equals(judgment2.getBase())); // may have different key
    }

    public float getExpectationDifference(Judgment that) {
        return getTruth().getExpDifAbs(that.getTruth());
    }
    
    public float solutionQuality(Sentence sentence) {
        Term problem = sentence.getContent(); 
        if (sentence instanceof Goal) 
            return truth.getExpectation();
        else if (problem.isConstant())          // "yes/no" question
            return truth.getConfidence();                                 // by confidence
        else                                                            // "what" question or goal
            return truth.getExpectation() / content.getComplexity();      // by likelihood/simplicity, to be refined
    }
    
    public boolean noOverlapping(Judgment judgment) {
        Base b = Base.make(base, judgment.getBase());
        if (b == null)
            return false;
        Memory.currentBase = b;
        return true;
    }
}

