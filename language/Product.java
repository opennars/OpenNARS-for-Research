
package nars.language;

import java.util.*;
import nars.io.Symbols;
import nars.entity.TermLink;
import nars.main.Memory;

/**
 * A Product is a sequence of terms.
 */
public class Product extends CompoundTerm {
    
    /**
     * constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     */
    private Product(String n, ArrayList<Term> arg) {
        super(n, arg);
    }
    
    /**
     * constructor with full values, called by clone
     * @param cs component list
     * @param open open variable list
     * @param closed closed variable list
     * @param complexity syntactic complexity of the compound
     * @param n The name of the term
     */
    private Product(String n, ArrayList<Term> cs, ArrayList<Variable> open, ArrayList<Variable> closed, short complexity) {
        super(n, cs, open, closed, complexity);
    }
    
    /**
     * override the cloning methed in Object
     * @return A new object, to be casted into an ImageExt
     */
    public Object clone() {
        return new Product(name, (ArrayList<Term>) cloneList(components),
                (ArrayList<Variable>) cloneList(openVariables), (ArrayList<Variable>) cloneList(closedVariables), complexity);
    }

     /**
     * Try to make a new compound. Called by StringParser.
     * @return the Term generated from the arguments
     * @param argument The list of components
     */
    public static Term make(ArrayList<Term> argument) {
        if (argument.size() < 2)
            return null;
        String name = makeCompoundName(Symbols.PRODUCT_OPERATOR, argument);
        Term t = Memory.nameToListedTerm(name);
        return (t != null) ? t : new Product(name, argument);
    }
        
    /**
     * Try to make a Product from an ImageExt/ImageInt and a component. Called by the inference rules.
     * @param image The existing Image
     * @param component The component to be added into the component list
     * @param index The index of the place-holder in the new Image -- optional parameter
     * @return A compound generated or a term it reduced to
     */
    // for both 
    public static Term make(CompoundTerm image, Term component, int index) {
        ArrayList<Term> argument = image.cloneComponents();
        argument.set(index, component);
        return make(argument);
    }
    
    /**
     * get the operator of the term.
     * @return the operator of the term
     */
    public String operator() {
        return Symbols.PRODUCT_OPERATOR;
    }
}
