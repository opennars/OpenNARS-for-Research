
package nars.language;

import java.util.*;
import nars.io.*;

/**
 * A statement is a compound term, consisting of a subject, a predicate,
 * and a relation symbol in between. It can be of either first-order or higher-order.
 */
public abstract class Statement extends CompoundTerm {
    
    /**
     * default constructor
     */
    protected Statement() {}
    
    /**
     * constructor with partial values, called by make
     * @param n The name of the term
     * @param arg The component list of the term
     */
    protected Statement(String n, ArrayList<Term> arg) {
        super(n, arg);
    }
    
    /**
     * constructor with full values, called by clone
     * @param cs component list
     * @param open open variable list
     * @param closed closed variable list
     * @param i syntactic complexity of the compound
     * @param n The name of the term
     */
    protected Statement(String n, ArrayList<Term> cs, ArrayList<Variable> open, ArrayList<Variable> closed, short i) {
        super(n, cs, open, closed, i);
    }
    
    /**
     * Make a Statement from String, called by StringParser
     * @param relation The relation String
     * @param subject The first component
     * @param predicate The second component
     * @return The Statement built
     */
    public static Statement make(String relation, Term subject, Term predicate) {
        if (invalidStatement(subject, predicate))
            return null;
        if (relation.equals(Symbols.INHERITANCE_RELATION))
            return Inheritance.make(subject, predicate);
        if (relation.equals(Symbols.SIMILARITY_RELATION))
            return Similarity.make(subject, predicate);
        if (relation.equals(Symbols.INSTANCE_RELATION))
            return Instance.make(subject, predicate);
        if (relation.equals(Symbols.PROPERTY_RELATION))
            return Property.make(subject, predicate);
        if (relation.equals(Symbols.INSTANCE_PROPERTY_RELATION))
            return InstanceProperty.make(subject, predicate);
        if (relation.equals(Symbols.IMPLICATION_RELATION))
            return Implication.make(subject, predicate);
        if (relation.equals(Symbols.EQUIVALENCE_RELATION))
            return Equivalence.make(subject, predicate);
        if (relation.equals(Symbols.IMPLICATION_AFTER_RELATION))
            return ImplicationAfter.make(subject, predicate);
        if (relation.equals(Symbols.IMPLICATION_WHEN_RELATION))
            return ImplicationWhen.make(subject, predicate);
        if (relation.equals(Symbols.IMPLICATION_BEFORE_RELATION))
            return ImplicationBefore.make(subject, predicate);
        if (relation.equals(Symbols.EQUIVALENCE_AFTER_RELATION))
            return EquivalenceAfter.make(subject, predicate);
        if (relation.equals(Symbols.EQUIVALENCE_WHEN_RELATION))
            return EquivalenceWhen.make(subject, predicate);
        return null;
    }
    
    /**
     * Make a Statement from given components, called by the rules
     * @return The Statement built
     * @param sub The first component
     * @param pred The second component
     * @param statement A sample statement providing the class type
     */
    public static Statement make(Statement statement, Term sub, Term pred) {
        if (statement instanceof Inheritance)
            return Inheritance.make(sub, pred);
        if (statement instanceof Similarity)
            return Similarity.make(sub, pred);
        if (statement instanceof ImplicationBefore)
            return ImplicationBefore.make(sub, pred);
        if (statement instanceof ImplicationWhen)
            return ImplicationWhen.make(sub, pred);
        if (statement instanceof ImplicationAfter)
            return ImplicationAfter.make(sub, pred);
        if (statement instanceof Implication)
            return Implication.make(sub, pred);
        if (statement instanceof EquivalenceWhen)
            return EquivalenceWhen.make(sub, pred);
        if (statement instanceof EquivalenceAfter)
            return EquivalenceAfter.make(sub, pred);
        if (statement instanceof Equivalence)
            return Equivalence.make(sub, pred);
        return null;
    }
    
    /**
     * Make a Statement from given components and temporal information, called by the rules
     * @param statement A sample statement providing the class type
     * @param sub The first component
     * @param pred The second component
     * @param order The temporal order of the statement
     * @return The Statement built
     */
    public static Statement make(Statement statement, Term sub, Term pred, CompoundTerm.TemporalOrder order) {
        if (order == CompoundTerm.TemporalOrder.UNSURE)
            return null;
        if (order == CompoundTerm.TemporalOrder.NONE)
            return make(statement, sub, pred);
        if (order == CompoundTerm.TemporalOrder.AFTER) {
            if (statement instanceof Implication)
                return ImplicationAfter.make(sub, pred);
            if (statement instanceof Equivalence)
                return EquivalenceAfter.make(sub, pred);
            return null;
        }
        if (order == CompoundTerm.TemporalOrder.WHEN) {
            if (statement instanceof Implication)
                return ImplicationWhen.make(sub, pred);
            if (statement instanceof Equivalence)
                return EquivalenceWhen.make(sub, pred);
            return null;
        }
        if (order == CompoundTerm.TemporalOrder.BEFORE) {
            if (statement instanceof Implication)
                return ImplicationBefore.make(sub, pred);
            if (statement instanceof Equivalence)
                return EquivalenceAfter.make(pred, sub);
            return null;
        }
        return null;
    }
    
    /**
     * Make a symmetric Statement from given components and temporal information, called by the rules
     * @param statement A sample asymmetric statement providing the class type
     * @param sub The first component
     * @param pred The second component
     * @param order The temporal order of the statement
     * @return The Statement built
     */
    public static Statement makeSym(Statement statement, Term sub, Term pred, CompoundTerm.TemporalOrder order) {
        if (order == CompoundTerm.TemporalOrder.UNSURE)
            return null;
        if (order == CompoundTerm.TemporalOrder.NONE) {
            if (statement instanceof Inheritance)
                return Similarity.make(sub, pred);
            if (statement instanceof Implication)
                return Equivalence.make(sub, pred);
            return null;
        }
        if (order == CompoundTerm.TemporalOrder.AFTER) {
            if (statement instanceof Implication)
                return EquivalenceAfter.make(sub, pred);
            return null;
        }
        if (order == CompoundTerm.TemporalOrder.WHEN) {
            if (statement instanceof Implication)
                return EquivalenceWhen.make(sub, pred);
            return null;
        }
        if (order == CompoundTerm.TemporalOrder.BEFORE) {
            if (statement instanceof Implication)
                return EquivalenceAfter.make(pred, sub);
            return null;
        }
        return null;
    }
    
    /**
     * check Statement relation symbol
     * @return if the given String is a relation symbol
     * @param s0 The String to be checked
     */
    public static boolean isRelation(String s0) {
        String s = s0.trim();
        if (s.length() != 3)
            return false;
        return (s.equals(Symbols.INHERITANCE_RELATION) ||
                s.equals(Symbols.SIMILARITY_RELATION) ||
                s.equals(Symbols.INSTANCE_RELATION) ||
                s.equals(Symbols.PROPERTY_RELATION) ||
                s.equals(Symbols.INSTANCE_PROPERTY_RELATION) ||
                s.equals(Symbols.IMPLICATION_RELATION) ||
                s.equals(Symbols.EQUIVALENCE_RELATION) ||
                s.equals(Symbols.IMPLICATION_AFTER_RELATION) ||
                s.equals(Symbols.IMPLICATION_WHEN_RELATION) ||
                s.equals(Symbols.IMPLICATION_BEFORE_RELATION) ||
                s.equals(Symbols.EQUIVALENCE_WHEN_RELATION) ||
                s.equals(Symbols.EQUIVALENCE_AFTER_RELATION) );
    }
    
    /**
     * override the default in making the name of the current term from existing fields
     * @return the name of the term
     */
    protected String makeName() {
        return makeStatementName(getSubject(), operator(), getPredicate());
    }
    
    /**
     * default method to make the name of an image term from given fields
     * @param subject the first component
     * @param predicate the second component
     * @param relation the relation operator
     * @return the name of the term
     */
    protected static String makeStatementName(Term subject, String relation, Term predicate) {
        StringBuffer name = new StringBuffer();
        name.append(Symbols.STATEMENT_OPENER);
        name.append(subject.getName());
        name.append(' ' + relation + ' ');
        name.append(predicate.getName());
        name.append(Symbols.STATEMENT_CLOSER);
        return name.toString();
    }
    
    /**
     * check the validity of a potential Statement.
     * <p>
     * Minimum requirement: the two terms cannot be the same. To be strengthened.
     * @param subject the first component
     * @param predicate the second component
     * @return Whether the Statement is invalid
     */
    public static boolean invalidStatement(Term subject, Term predicate) {
        if (subject.equals(predicate))
            return true;
        if ((subject instanceof CompoundTerm) && ((CompoundTerm) subject).containComponent(predicate))
            return true;
        if ((predicate instanceof CompoundTerm) && ((CompoundTerm) predicate).containComponent(subject))
            return true;
        return false;
    }
    
    /**
     * Return the first component of the statement
     * @return The first component
     */
    public Term getSubject() {
        return components.get(0);
    }
        
    /**
     * Return the second component of the statement
     * @return The second component
     */
    public Term getPredicate() {
        return components.get(1);
    }
}
