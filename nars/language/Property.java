
package nars.language;

/**
 * A Statement about a Property relation, which is used only in Narsese for I/O, 
 * and translated into Inheritance for internal use.
 */
public abstract class Property extends Statement {
    
    /**
     * Try to make a new compound from two components. Called by the inference rules.
     * <p>
     *  A --] B becomes A --> [B]
     * @param subject The first compoment
     * @param predicate The second compoment
     * @return A compound generated or null
     */
    public static Statement make(Term subject, Term predicate) {
        return Inheritance.make(subject, SetInt.make(predicate));
    }
}
