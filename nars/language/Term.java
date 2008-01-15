
package nars.language;

import java.util.*;
import nars.io.Symbols;
import nars.main.Memory;
import nars.inference.SyllogisticRules;

/**
 * Term is the basic component of Narsese, and the object of processing in NARS.
 * <p>
 * A Term may or may not have an associated Concept containing relations with other Terms. It
 * is not linked in the Term, because a Concept may be forgot, while the Term exists.
 */
public class Term implements Cloneable, Comparable<Term> {
    /**
     * A Term is identified uniquely by its name, a sequence of characters in a given alphabet.
     */
    protected String name;              // name of the term, an ASCII string (can be changed to Unicode)
    
    /**
     * Default constructor
     */
    protected Term() {}
    
    /**
     * Constructor with a given name
     * @param name A String as the name of the Term
     */
    public Term(String name) {
        this.name = name;
    }
    
    /**
     * The same as getName, used in display.
     * @return The name of the term as a String
     */
    public final String toString() {
        return name;
    }
    
    /**
     * Reporting the name of the current Term.
     * @return The name of the term as a String
     */
    public String getName() {
        return name;
    }
    
    /**
     * Default, to be overrided in variable Terms.
     * @return The name of the term as a String
     */
    public String getConstantName() {
        return name;
    }
    
    /**
     * Make a new Term with the same name.
     * @return The new Term
     */
    public Object clone() {
        return new Term(name);
    }
    
    /**
     * Equal terms have identical name, though not necessarily the same reference.
     * @return Whether the two Terms are equal
     * @param that The Term to be compared with the current Term
     */
    public boolean equals(Object that) {
        return (that instanceof Term) && getName().equals(((Term) that).getName());
    }
    
    /**
     * The default complexity, for constant automic Term, is 1.
     * @return The conplexity of the term, an integer
     */
    public int getComplexity() {
        return 1;
    }
        
    /**
     * Check the relative order of two Terms.
     * @param that The Term to be compared with the current Term
     * @return The same as compareTo as defined on Strings when the constant parts are compared
     */
    public final int compareTo(Term that) {
        int i = this.getConstantName().compareTo(that.getConstantName());       // based on the constant part first
        return (i != 0) ? i : this.getName().compareTo(that.getName());
    }
    
    /**
     * Check whether the current Term can name a Concept.
     * @return a Term is constant by default
     */
    public boolean isConstant() {
        return true;
    }
    
    public CompoundTerm.TemporalOrder getTemporalOrder() {
        return CompoundTerm.TemporalOrder.NONE;
    }
    
    public final boolean containQueryVariable() {                           // to be revised
        return (name.indexOf(Symbols.QUERY_VARIABLE_TAG) >= 0);
    }
}
