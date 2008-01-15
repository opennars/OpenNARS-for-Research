
package nars.inference;

import java.util.*;

import nars.entity.*;
import nars.language.*;
import nars.io.*;
import nars.main.*;

/**
 * Forward inference rules involving compound terms.
 * Input premises are one sentence and one BeliefLink.
 */
public final class StructuralRules {
    
    /* -------------------- transform between compounds and components -------------------- */
    
    /**
     * {<A --> B>} |- <(A&C) --> (B&C)>
     * @param compound The compound term
     * @param index The location of the indicated term in the compound
     * @param statement The premise
     * @param side The location of the indicated term in the premise
     */
    static void structuralCompose2(CompoundTerm compound, short index, Statement statement, short side) {
        Term sub = statement.getSubject();
        Term pred = statement.getPredicate();
        ArrayList<Term> components = compound.cloneComponents();
        if (((side == 0) && components.contains(pred)) || ((side == 1) && components.contains(sub)))
            return;     // both terms in compound
        if (side == 0) {
            sub = compound;
            components.set(index, pred);
            pred = CompoundTerm.make(compound, components);
        } else {
            components.set(index, sub);
            sub = CompoundTerm.make(compound, components);
            pred = compound;
        }
        if ((sub == null) || (pred == null))
            return;
        Term content;
        if (switchOrder(compound, index))
            content = Statement.make(statement, pred, sub);     // {A --> B, A @ (C-A)} |- (C-B) --> (C-A)
        else
            content = Statement.make(statement, sub, pred);     // the other cases
        if (content == null)
            return;
        Task task = Memory.currentTask;
        Sentence sentence = task.getSentence();
        TruthValue truth = sentence.getTruth();
        BudgetValue budget;
        if (sentence instanceof Question) {
            budget = BudgetFunctions.compoundBackwardWeak(content);
        } else {
            if (compound.size() > 1) {
                if (sentence.isJudgment())
                    truth = TruthFunctions.implying(truth);
                else // Goal
                    truth = TruthFunctions.implied(truth);
            }
            budget = BudgetFunctions.compoundForward(truth, content);
        }
        Memory.singlePremiseTask(budget, content, truth);
    }

    /**
     * {<(A&C) --> (B&C)>} |- <A --> B>
     * @param statement The premise
     */
    static void structuralDecompose2(Statement statement) {
        Term subj = statement.getSubject();
        Term pred = statement.getPredicate();
        if (subj.getClass() != pred.getClass())
            return;
        CompoundTerm sub = (CompoundTerm) subj;
        CompoundTerm pre = (CompoundTerm) pred;
        if (sub.size() != pre.size())
            return;
        int index = -1;
        Term t1, t2;
        for (int i = 0; i < sub.size(); i++) {
            t1 = sub.componentAt(i);
            t2 = pre.componentAt(i);
            if (!t1.equals(t2)) {
                if (index < 0)
                    index = i;
                else
                    return;
            }
        }
        t1 = sub.componentAt(index);
        t2 = pre.componentAt(index);
        Term content;
        if (switchOrder(sub, (short) index))
            content = Statement.make(statement, t2, t1);
        else
            content = Statement.make(statement, t1, t2);
        Task task = Memory.currentTask;
        Sentence sentence = task.getSentence();
        TruthValue truth = sentence.getTruth();
        BudgetValue budget;
        if (sentence instanceof Question) {
            budget = BudgetFunctions.compoundBackward(content);
        } else {
            if (sub.size() > 1) {
                if (sentence.isJudgment())
                    truth = TruthFunctions.implied(truth);
                else // Goal
                    truth = TruthFunctions.implying(truth);
            }
            budget = BudgetFunctions.compoundForward(truth, content);
        }
        Memory.singlePremiseTask(budget, content, truth);
    }
    
    private static boolean switchOrder(CompoundTerm compound, short index) {
        return ((((compound instanceof DifferenceExt) || (compound instanceof DifferenceInt)) && (index == 1)) ||
                ((compound instanceof ImageExt) && (index != ((ImageExt) compound).getRelationIndex())) ||
                ((compound instanceof ImageInt) && (index != ((ImageInt) compound).getRelationIndex())));
    }
    
    /**
     * {<A --> B>} |- <A --> (B&C)>
     * @param compound The compound term
     * @param index The location of the indicated term in the compound
     * @param statement The premise
     */
    static void structuralCompose1(CompoundTerm compound, short index, Statement statement) {
        if (!Memory.currentTask.getSentence().isJudgment())
            return;
        Term component = compound.componentAt(index);
        Task task = Memory.currentTask;
        Sentence sentence = task.getSentence();
        TruthValue truth = sentence.getTruth();
        Term subj = statement.getSubject();
        Term pred = statement.getPredicate();
        if (component.equals(subj)) {
            if (compound instanceof IntersectionExt)                        // {A --> B, A @ (A&C)} |- (A&C) --> B
                structuralStatement(compound, pred, TruthFunctions.implying(truth));
            else if (compound instanceof IntersectionInt)                   // {A --> B, A @ (A|C)} |- (A|C) --> B
                structuralStatement(compound, pred, TruthFunctions.implied(truth));
            else if ((compound instanceof DifferenceExt) && (index == 0))   // {A --> B, A @ (A-C)} |- (A-C) --> B
                structuralStatement(compound, pred, TruthFunctions.implying(truth));
            else if (compound instanceof DifferenceInt)
                if (index == 0)                                             // {A --> B, A @ (A~C)} |- (A~C) --> B
                    structuralStatement(compound, pred, TruthFunctions.implied(truth));
                else                                                        // {A --> B, A @ (C~A)} |- (C~A) --> B
                    structuralStatement(compound, pred, TruthFunctions.negImply(truth));
        } else if (component.equals(pred)) {
            if (compound instanceof IntersectionExt)                        // {B --> A, A @ (A&C)} |- B --> (A&C)
                structuralStatement(subj, compound, TruthFunctions.implied(truth));
            else if (compound instanceof IntersectionInt)                   // {B --> A, A @ (A|C)} |- B --> (A|C)
                structuralStatement(subj, compound, TruthFunctions.implying(truth));
            else if (compound instanceof DifferenceExt)
                if (index == 0)                                             // {B --> A, A @ (A-C)} |- B --> (A-C)
                    structuralStatement(subj, compound, TruthFunctions.implied(truth));
                else                                                        // {B --> A, A @ (C-A)} |- B --> (C-A)
                    structuralStatement(subj, compound, TruthFunctions.negImply(truth));
            else if ((compound instanceof DifferenceInt) && (index == 0))   // {B --> A, A @ (A~C)} |- B --> (A~C)
                structuralStatement(subj, compound, TruthFunctions.implying(truth));
        }
    }
    
    /**
     * {<(A&C) --> B>} |- <A --> B>
     * @param compound The compound term
     * @param index The location of the indicated term in the compound
     * @param statement The premise
     * @param side The location of the indicated term in the premise
     */
    static void structuralDecompose1(CompoundTerm compound, short index, Statement statement, short side) {
        if (!Memory.currentTask.getSentence().isJudgment())
            return;
        Term component = compound.componentAt(index);
        Task task = Memory.currentTask;
        Sentence sentence = task.getSentence();
        TruthValue truth = sentence.getTruth();
        Term subj = statement.getSubject();
        Term pred = statement.getPredicate();
        if (compound.equals(subj)) {
            if (compound instanceof IntersectionExt)                        // {(A&C) --> B, A @ (A&C)} |- A --> B
                structuralStatement(component, pred, TruthFunctions.implied(truth));
            else if (compound instanceof IntersectionInt)                   // {(A|C) --> B, A @ (A|C)} |- A --> B
                structuralStatement(component, pred, TruthFunctions.implying(truth));
            else if ((compound instanceof DifferenceExt) && (index == 0))   // {(A-C) --> B, A @ (A-C)} |- A --> B
                structuralStatement(component, pred, TruthFunctions.implied(truth));
            else if (compound instanceof DifferenceInt)
                if (index == 0)                                             // {(A~C) --> B, A @ (A~C)} |- A --> B
                    structuralStatement(component, pred, TruthFunctions.implying(truth));
                else                                                        // {(C~A) --> B, A @ (C~A)} |- A --> B
                    structuralStatement(component, pred, TruthFunctions.negImply(truth));
        } else if (compound.equals(pred)) {
            if (compound instanceof IntersectionExt)                        // {B --> (A&C), A @ (A&C)} |- B --> A
                structuralStatement(subj, component, TruthFunctions.implying(truth));
            else if (compound instanceof IntersectionInt)                   // {B --> (A&C), A @ (A&C)} |- B --> A
                structuralStatement(subj, component, TruthFunctions.implied(truth));
            else if (compound instanceof DifferenceExt)
                if (index == 0)                                             // {B --> (A-C), A @ (A-C)} |- B --> A
                    structuralStatement(subj, component, TruthFunctions.implying(truth));
                else                                                        // {B --> (C-A), A @ (C-A)} |- B --> A
                    structuralStatement(subj, component, TruthFunctions.negImply(truth));
            else if ((compound instanceof DifferenceInt) && (index == 0))   // {B --> (A~C), A @ (A~C)} |- B --> A
                structuralStatement(subj, component, TruthFunctions.implied(truth));
        }
    }
    
    /**
     * Common final operations of the above two methods
     * @param subject The subject of the new task
     * @param predicate The predicate of the new task
     * @param truth The truth value of the new task
     */
    private static void structuralStatement(Term subject, Term predicate, TruthValue truth) { // Inheritance only?
        Task task = Memory.currentTask;
        Sentence sentence = task.getSentence();
        Term content = Statement.make((Statement) task.getContent(), subject, predicate);
        if (content == null)
            return;
        BudgetValue budget = BudgetFunctions.compoundForward(truth, content);
//        if (sentence instanceof Question)
//            budget = BudgetFunctions.compoundBackward(content);
//        else
//            budget = BudgetFunctions.compoundForward(truth, content);
        Memory.singlePremiseTask(budget, content, truth);
    }
    
    /* -------------------- set transform -------------------- */
    
    /**
     * {<S --> {P}>} |- <S <-> {P}>
     * @param compound The set compound
     * @param statement The premise
     * @param side The location of the indicated term in the premise
     */
    static void transformSetRelation(CompoundTerm compound, Statement statement, short side) {
        if (compound.size() > 1)
            return;
        if (statement instanceof Inheritance)
            if (((compound instanceof SetExt) && (side == 0)) || ((compound instanceof SetInt) && (side == 1)))
                return;
        Term sub = statement.getSubject();
        Term pre = statement.getPredicate();
        Term content;
        if (statement instanceof Inheritance) {
            content = Similarity.make(sub, pre);
        } else {
            if (((compound instanceof SetExt) && (side == 0)) || ((compound instanceof SetInt) && (side == 1)))
                content = Inheritance.make(pre, sub);
            else
                content = Inheritance.make(sub, pre);
        }
        Task task = Memory.currentTask;
        Sentence sentence = task.getSentence();
        TruthValue truth = sentence.getTruth();
        BudgetValue budget;
        if (sentence instanceof Question)
            budget = BudgetFunctions.compoundBackward(content);
        else
            budget = BudgetFunctions.compoundForward(truth, content);
        Memory.singlePremiseTask(budget, content, truth);
    }
    
    /* -------------------- products and images transform -------------------- */
    
    static void transformProductImage(Inheritance inh, CompoundTerm oldContent, short[] indices, Task task) {
        Term subject = null;
        Term predicate = null;
        short index = indices[indices.length - 1];
        short side = indices[indices.length - 2];
        CompoundTerm comp = (CompoundTerm) inh.componentAt(side);
        if (comp instanceof Product) {
            if (side == 0) {           // Product as subject
                subject = comp.componentAt(index);
                predicate = ImageExt.make((Product) comp, inh.getPredicate(), index);
            } else {
                subject = ImageInt.make((Product) comp, inh.getSubject(), index);
                predicate = comp.componentAt(index);
            }
        } else if ((comp instanceof ImageExt) && (side == 1)) {   // ImageExt as predicate
            if (index == ((ImageExt) comp).getRelationIndex()) {
                subject = Product.make(comp, inh.getSubject(), index);
                predicate = comp.componentAt(index);
            } else {
                subject = comp.componentAt(index);
                predicate = ImageExt.make((ImageExt) comp, inh.getSubject(), index);
            }
        } else if ((comp instanceof ImageInt) && (side == 0)) {   // ImageInt as subject
            if (index == ((ImageInt) comp).getRelationIndex()) {
                subject = comp.componentAt(index);
                predicate = Product.make(comp, inh.getPredicate(), index);
            } else {
                subject = ImageInt.make((ImageInt) comp, inh.getPredicate(), index);
                predicate = comp.componentAt(index);
            }
        } else //        if ((subject == null) || (predicate == null))
            return;
        Inheritance newInh = Inheritance.make(subject, predicate);
        Term content = null;
        if (indices.length == 2)
            content = newInh;
        else {
            ArrayList<Term> componentList;
            if ((oldContent instanceof Implication) && (oldContent.componentAt(0) instanceof Conjunction)) {
                componentList = ((CompoundTerm) oldContent.componentAt(0)).cloneComponents();
                componentList.set(indices[0], newInh);
                Term newCond = Conjunction.make(componentList);
                content = Implication.make(newCond, ((Statement) oldContent).getPredicate());
            } else
                componentList = oldContent.cloneComponents();
            componentList.set(indices[0], newInh);
            if (oldContent instanceof Conjunction)
                content = Conjunction.make(componentList);
            else if ((oldContent instanceof Implication) || (oldContent instanceof Equivalence))
                content = Statement.make((Statement) oldContent, componentList.get(0), componentList.get(1));
        }
        if (content == null)
            return;
        Sentence sentence = task.getSentence();
        TruthValue truth = sentence.getTruth();
        BudgetValue budget;
        if (sentence instanceof Question)
            budget = BudgetFunctions.compoundBackward(content);
        else
            budget = BudgetFunctions.compoundForward(truth, content);
        Memory.singlePremiseTask(budget, content, truth);
    }
    
    /* --------------- Disjunction and Conjunction transform --------------- */
    
    /**
     * {(&&, A, B)} |- A
     * @param compound The premise
     * @param component The recognized component in the premise
     * @param compoundTask Whether the compound comes from the task
     */
    static void structuralCompound(CompoundTerm compound, Term component, boolean compoundTask) {
        Term content = (compoundTask ? component : compound);
        Task task = Memory.currentTask;
        Sentence sentence = task.getSentence();
        TruthValue truth = sentence.getTruth();
        BudgetValue budget;
        if (sentence instanceof Question) {
            budget = BudgetFunctions.compoundBackward(content);
        } else {
            if ((sentence.isJudgment()) == (compoundTask == (compound instanceof Conjunction)))
                truth = TruthFunctions.implying(truth);
            else
                truth = TruthFunctions.implied(truth);
            budget = BudgetFunctions.compoundForward(sentence.getTruth(), content);
        }
        Memory.singlePremiseTask(budget, content, truth);
    }
    
    /* --------------- Negation related rules --------------- */
    
    /**
     * {A} |- (--, A)
     * @param content The premise
     */
    static void transformNegation(Term content) {
        Task task = Memory.currentTask;
        Sentence sentence = task.getSentence();
        TruthValue truth = sentence.getTruth();
        if (sentence instanceof Judgment)
            truth = TruthFunctions.negation(truth);
        BudgetValue budget;
        if (sentence instanceof Question)
            budget = BudgetFunctions.compoundBackward(content);
        else
            budget = BudgetFunctions.compoundForward(truth, content);
        Memory.singlePremiseTask(budget, content, truth);
    }
    
    /**
     * {<A ==> B>} |- <(--B) ==> (--A)>
     * @param statement The premise
     */
    static void contraposition(Statement statement) {
        Term subj = statement.getSubject();
        Term pred = statement.getPredicate();
        Term content = Statement.make(statement, Negation.make(pred), Negation.make(subj), CompoundTerm.temporalReverse(statement.getTemporalOrder()));
        Task task = Memory.currentTask;
        Sentence sentence = task.getSentence();
        TruthValue truth = sentence.getTruth();
        BudgetValue budget;
        if (sentence instanceof Question) {
            if (content instanceof Implication)
                budget = BudgetFunctions.compoundBackwardWeak(content);
            else
                budget = BudgetFunctions.compoundBackward(content);
        } else {
            if (content instanceof Implication)
                truth = TruthFunctions.contraposition(truth);
            budget = BudgetFunctions.compoundForward(truth, content);
        }
        Memory.singlePremiseTask(budget, content, truth);
    }
}
