/*
 * RuleTables.java
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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package nars.inference;

import nars.entity.*;
import nars.language.*;
import nars.main.Memory;

/**
 * Table of inference rules, indexed by the CompositionLinks for the task and the belief.
 * Used for indirective processing of a task
 */
public final class RuleTables {

    /* ----- inferences with two composition links ----- */
    public static void reason(TaskLink tLink, TermLink bLink) {
        Task task = Memory.currentTask;
        Term taskTerm = (Term) task.getContent().clone();         // cloning for substitution
        Term beliefTerm = (Term) bLink.getTarget().clone();       // cloning for substitution
        Concept beliefConcept = Memory.termToConcept(beliefTerm);
        Judgment belief = null;
        if (beliefConcept != null) {
            belief = beliefConcept.getBelief(task);
        }
        Memory.currentBelief = belief;  // may be null
        if ((belief != null) && (Variable.findSubstitute(Variable.VarType.QUERY, taskTerm, beliefTerm) != null)) {
            MatchingRules.match(task, belief);
        }
        short tIndex = tLink.getIndex(0);
        short bIndex = bLink.getIndex(0);
        switch (tLink.getType()) {
            case TermLink.SELF:
                switch (bLink.getType()) {
                    case TermLink.COMPONENT:
                        if ((task.getSentence() instanceof Goal) && (taskTerm instanceof ConjunctionSequence) && (bIndex > 0)) { // more general solution?
                            return;
                        }
                        compoundAndSelf((CompoundTerm) taskTerm, beliefTerm, true);
                        break;
                    case TermLink.COMPOUND:
                        compoundAndSelf((CompoundTerm) beliefTerm, taskTerm, false);
                        break;
                    case TermLink.COMPONENT_STATEMENT:  // detachment
                        if (belief != null) {
                            SyllogisticRules.detachment(task.getSentence(), belief, bIndex);
                        }
                        break;
                    case TermLink.COMPOUND_STATEMENT:   // detachment
                        if (belief != null) {
                            SyllogisticRules.detachment(belief, task.getSentence(), bIndex);
                        } //task, beliefTerm);
                        break;
                    case TermLink.COMPONENT_CONDITION:
                        if (belief != null) {
                            SyllogisticRules.conditionalDedInd((Implication) taskTerm, bIndex, beliefTerm, tIndex);
                        }
                        break;
                    case TermLink.COMPOUND_CONDITION:
                        if (belief != null) {
                            SyllogisticRules.conditionalDedInd((Implication) beliefTerm, bIndex, taskTerm, tIndex);
                        }
                        break;
                    default:
                }
                break;
            case TermLink.COMPOUND:
                switch (bLink.getType()) {
                    case TermLink.COMPOUND:
                        compoundAndCompound((CompoundTerm) taskTerm, tIndex, (CompoundTerm) beliefTerm, bIndex);
                        break;
                    case TermLink.COMPOUND_STATEMENT:
                        compoundAndStatement((CompoundTerm) taskTerm, tIndex, (Statement) beliefTerm, bIndex, beliefTerm);
                        break;
                    case TermLink.COMPOUND_CONDITION:
                        if (belief != null) {
                            SyllogisticRules.conditionalDedInd((Implication) beliefTerm, bIndex, taskTerm, -1);
                        }
                        break;
                    default:
                }
                break;
            case TermLink.COMPOUND_STATEMENT:
                switch (bLink.getType()) {
                    case TermLink.COMPONENT:
                        componentAndStatement((CompoundTerm) Memory.currentTerm, bIndex, (Statement) taskTerm, tIndex);
                        break;
                    case TermLink.COMPOUND:
                        compoundAndStatement((CompoundTerm) beliefTerm, bIndex, (Statement) taskTerm, tIndex, beliefTerm);
                        break;
                    case TermLink.COMPOUND_STATEMENT:
                        if (belief != null) {
                            syllogisms(tLink, bLink, taskTerm, beliefTerm);
                        }
                        break;
                    case TermLink.COMPOUND_CONDITION:
                        if (belief != null) {
                            conditionalDedIndWithVar((Implication) beliefTerm, bIndex, (Statement) taskTerm, tIndex);
                        }
                        break;
                    default:
                }
                break;
            case TermLink.COMPOUND_CONDITION:
                switch (bLink.getType()) {
                    case TermLink.COMPOUND_STATEMENT:
                        if (belief != null) {
                            conditionalDedIndWithVar((Implication) taskTerm, tIndex, (Statement) beliefTerm, bIndex);
                        }
                        break;
                    default:
                }
                break;
            default:
            // to be revised to cover all types
        }
    }

    /* ----- syllogistic inferences ----- */
    /**
     * Meta-table of syllogistic rules, indexed by the content classes of the taskSentence and the belief
     */
    private static void syllogisms(TaskLink tLink, TermLink bLink, Term taskTerm, Term beliefTerm) {
        Sentence taskSentence = Memory.currentTask.getSentence();
        Judgment belief = Memory.currentBelief;
        int figure;
        if (taskTerm instanceof Inheritance) {
            if (beliefTerm instanceof Inheritance) {
                figure = indexToFigure(tLink, bLink);
                asymmetricAsymmetric(taskSentence, belief, figure);
            } else if (beliefTerm instanceof Similarity) {
                figure = indexToFigure(tLink, bLink);
                asymmetricSymmetric(taskSentence, belief, figure);
            } else {
                detachmentWithVar(belief, taskSentence, bLink.getIndex(0));
            }
        } else if (taskTerm instanceof Similarity) {
            if (beliefTerm instanceof Inheritance) {
                figure = indexToFigure(bLink, tLink);
                asymmetricSymmetric(belief, taskSentence, figure);
            } else if (beliefTerm instanceof Similarity) {
                figure = indexToFigure(bLink, tLink);
                symmetricSymmetric(belief, taskSentence, figure);
            }
        } else if (taskTerm instanceof Implication) {
            if (beliefTerm instanceof Implication) {
                figure = indexToFigure(tLink, bLink);
                asymmetricAsymmetric(taskSentence, belief, figure);
            } else if (beliefTerm instanceof Equivalence) {
                figure = indexToFigure(tLink, bLink);
                asymmetricSymmetric(taskSentence, belief, figure);
            } else if (beliefTerm instanceof Inheritance) {
                detachmentWithVar(taskSentence, belief, tLink.getIndex(0));
            }
        // or intro 2nd ind var
        } else if (taskTerm instanceof Equivalence) {
            if (beliefTerm instanceof Implication) {
                figure = indexToFigure(bLink, tLink);
                asymmetricSymmetric(belief, taskSentence, figure);
            } else if (beliefTerm instanceof Equivalence) {
                figure = indexToFigure(bLink, tLink);
                symmetricSymmetric(belief, taskSentence, figure);
            } else if (beliefTerm instanceof Inheritance) {
                detachmentWithVar(taskSentence, belief, tLink.getIndex(0));
            }
        }
    }

    private static int indexToFigure(TermLink link1, TermLink link2) {
        return (link1.getIndex(0) + 1) * 10 + (link2.getIndex(0) + 1);    // valid value: 11, 12, 21, 22
    }

    /**
     * Syllogistic rules whose both premises are on the same asymmetric relation
     */
    private static void asymmetricAsymmetric(Sentence sentence, Judgment belief, int figure) {
        Statement s1 = (Statement) sentence.cloneContent();
        Statement s2 = (Statement) belief.cloneContent();
        Term t1, t2;
        switch (figure) {
            case 11:    // induction
                if (Variable.unify(Variable.VarType.INDEPENDENT, s1.getSubject(), s2.getSubject(), s1, s2)) {
                    t1 = s2.getPredicate();
                    t2 = s1.getPredicate();
                    SyllogisticRules.abdIndCom(t1, t2, sentence, belief, figure);
                    CompositionalRules.composeCompound(sentence, belief, 0);
                }
                break;
            case 12:    // deduction
                if (Variable.unify(Variable.VarType.INDEPENDENT, s1.getSubject(), s2.getPredicate(), s1, s2)) {
                    t1 = s2.getSubject();
                    t2 = s1.getPredicate();
                    if (Variable.unify(Variable.VarType.QUERY, t1, t2, s1, s2)) {
                        MatchingRules.matchReverse();
                    } else {
                        SyllogisticRules.dedExe(t1, t2, sentence, belief);
                    }
                }
                break;
            case 21:    // exemplification
                if (Variable.unify(Variable.VarType.INDEPENDENT, s1.getPredicate(), s2.getSubject(), s1, s2)) {
                    t1 = s1.getSubject();
                    t2 = s2.getPredicate();
                    if (Variable.unify(Variable.VarType.QUERY, t1, t2, s1, s2)) {
                        MatchingRules.matchReverse();
                    } else {
                        SyllogisticRules.dedExe(t1, t2, sentence, belief);
                    }
                }
                break;
            case 22:    // abduction
                if (Variable.unify(Variable.VarType.INDEPENDENT, s1.getPredicate(), s2.getPredicate(), s1, s2)) {
                    t1 = s1.getSubject();
                    t2 = s2.getSubject();
                    if (!SyllogisticRules.conditionalAbd(t1, t2, s1, s2)) {         // if conditional abduction, skip the following
                        SyllogisticRules.abdIndCom(t1, t2, sentence, belief, figure);
                        CompositionalRules.composeCompound(sentence, belief, 1);
                    }
                }
                break;
            default:
        }
    }

    /**
     * Syllogistic rules whose first premise is on an asymmetric relation, and the second on a symmetric relation
     */
    private static void asymmetricSymmetric(Sentence asym, Sentence sym, int figure) {
        if (asym.getTense() == sym.getTense()) {
            Memory.currentTemporalRelation = asym.getTense();
        } else {
            return;
        }
        Statement asymSt = (Statement) asym.cloneContent();
        Statement symSt = (Statement) sym.cloneContent();
        Term t1, t2;
        switch (figure) {
            case 11:
                if (Variable.unify(Variable.VarType.INDEPENDENT, asymSt.getSubject(), symSt.getSubject(), asymSt, symSt)) {
                    t1 = asymSt.getPredicate();
                    t2 = symSt.getPredicate();
                    if (Variable.unify(Variable.VarType.QUERY, t1, t2, asymSt, symSt)) {
                        MatchingRules.matchAsymSym(asym, sym, figure);
                    } //task, belief, order1 - order2, false);
                    else {
                        SyllogisticRules.analogy(t2, t1, asym, sym, figure);
                    }
                }
                break;
            case 12:
                if (Variable.unify(Variable.VarType.INDEPENDENT, asymSt.getSubject(), symSt.getPredicate(), asymSt, symSt)) {
                    t1 = asymSt.getPredicate();
                    t2 = symSt.getSubject();
                    if (Variable.unify(Variable.VarType.QUERY, t1, t2, asymSt, symSt)) {
                        MatchingRules.matchAsymSym(asym, sym, figure);
                    } //task, belief, order1 - order2, false);
                    else {
                        SyllogisticRules.analogy(t2, t1, asym, sym, figure);
                    }
                }
                break;
            case 21:
                if (Variable.unify(Variable.VarType.INDEPENDENT, asymSt.getPredicate(), symSt.getSubject(), asymSt, symSt)) {
                    t1 = asymSt.getSubject();
                    t2 = symSt.getPredicate();
                    if (Variable.unify(Variable.VarType.QUERY, t1, t2, asymSt, symSt)) {
                        MatchingRules.matchAsymSym(asym, sym, figure);
                    } //task, belief, order1 - order2, false);
                    else {
                        SyllogisticRules.analogy(t1, t2, asym, sym, figure);
                    }
                }
                break;
            case 22:
                if (Variable.unify(Variable.VarType.INDEPENDENT, asymSt.getPredicate(), symSt.getPredicate(), asymSt, symSt)) {
                    t1 = asymSt.getSubject();
                    t2 = symSt.getSubject();
                    if (Variable.unify(Variable.VarType.QUERY, t1, t2, asymSt, symSt)) {
                        MatchingRules.matchAsymSym(asym, sym, figure);
                    } //task, belief, order1 - order2, false);
                    else {
                        SyllogisticRules.analogy(t1, t2, asym, sym, figure);
                    }
                }
                break;
        }
    }

    /**
     * Syllogistic rules whose both premises are on the same symmetric relation
     */
    private static void symmetricSymmetric(Judgment belief, Sentence sentence, int figure) {
        if (sentence.getTense() == belief.getTense()) {
            Memory.currentTemporalRelation = sentence.getTense();
        } else {
            return;
        }
        Statement s1 = (Statement) belief.cloneContent();
        Statement s2 = (Statement) sentence.cloneContent();
        switch (figure) {
            case 11:
                if (Variable.unify(Variable.VarType.INDEPENDENT, s1.getSubject(), s2.getSubject(), s1, s2)) {
                    SyllogisticRules.resemblance(s1.getPredicate(), s2.getPredicate(), belief, sentence, figure);
                }
                break;
            case 12:
                if (Variable.unify(Variable.VarType.INDEPENDENT, s1.getSubject(), s2.getPredicate(), s1, s2)) {
                    SyllogisticRules.resemblance(s1.getPredicate(), s2.getSubject(), belief, sentence, figure);
                }
                break;
            case 21:
                if (Variable.unify(Variable.VarType.INDEPENDENT, s1.getPredicate(), s2.getSubject(), s1, s2)) {
                    SyllogisticRules.resemblance(s1.getSubject(), s2.getPredicate(), belief, sentence, figure);
                }
                break;
            case 22:
                if (Variable.unify(Variable.VarType.INDEPENDENT, s1.getPredicate(), s2.getPredicate(), s1, s2)) {
                    SyllogisticRules.resemblance(s1.getSubject(), s2.getSubject(), belief, sentence, figure);
                }
                break;
        }
    }

    /* ----- conditional inferences ----- */
    private static void detachmentWithVar(Sentence mainSentence, Sentence subSentence, int index) {
        Statement statement = (Statement) mainSentence.getContent();
        Term component = statement.componentAt(index);
        Term compound = subSentence.getContent();
        if ((component instanceof Inheritance) && (Memory.currentBelief != null)) {
            if (component.isConstant()) {
                SyllogisticRules.detachment(mainSentence, subSentence, index);
            } else if (Variable.unify(Variable.VarType.INDEPENDENT, component, compound, statement, compound)) {
                SyllogisticRules.detachment(mainSentence, subSentence, index);
            } else if ((statement instanceof Implication) && (Memory.currentTask.getSentence().isJudgment())) {
                SyllogisticRules.introVarIndInner((Statement) compound, (Statement) statement.getPredicate(), statement);    // tense???
                CompositionalRules.introVarDepInner(statement, statement.getPredicate(), compound);  // tense???
            }
        }
    }

    private static void conditionalDedIndWithVar(Implication conditional, short index, Statement statement, short side) {
        CompoundTerm condition = (CompoundTerm) conditional.getSubject();
        Term component = condition.componentAt(index);
        Term component2 = null;
        if (statement instanceof Inheritance) {
            component2 = statement;
        } else if (statement instanceof Implication) {
            component2 = statement.componentAt(side);
        }
        if ((component2 != null) && Variable.unify(Variable.VarType.INDEPENDENT, component, component2, conditional, statement)) {
            SyllogisticRules.conditionalDedInd(conditional, index, statement, -1);
        }
    }

    /* ----- structural inferences ----- */
    private static void compoundAndSelf(CompoundTerm compound, Term component, boolean compoundTask) {
        if ((compound instanceof Conjunction) || (compound instanceof Disjunction)) {
            if (Memory.currentBelief != null) {
                CompositionalRules.decomposeStatement(compound, component, compoundTask);
            } else if (compound.containComponent(component)) {
                StructuralRules.structuralCompound(compound, component, compoundTask);
            }
        } else if ((compound instanceof Negation) && !Memory.currentTask.isStructual()) {
            if (compoundTask) {
                StructuralRules.transformNegation(((Negation) compound).componentAt(0));
            } else {
                StructuralRules.transformNegation(compound);
            }
        }
    }

    private static void compoundAndCompound(CompoundTerm taskTerm, int tIndex, CompoundTerm beliefTerm, int bIndex) {
        if (taskTerm.getClass() == beliefTerm.getClass()) {
            if (taskTerm.size() > beliefTerm.size()) {
                compoundAndSelf(taskTerm, beliefTerm, true);
            } else if (taskTerm.size() < beliefTerm.size()) {
                compoundAndSelf(beliefTerm, taskTerm, false);
            }
        }
    }

    private static void compoundAndStatement(CompoundTerm compound, short index, Statement statement, short side, Term beliefTerm) {
        Term component = compound.componentAt(index);
        Task task = Memory.currentTask;
        if (component.getClass() == statement.getClass()) {
            if ((compound instanceof Conjunction) && (Memory.currentBelief != null)) {
                if (Variable.unify(Variable.VarType.DEPENDENT, component, statement, compound, statement)) {
                    CompositionalRules.abdVarDepOuter(compound, component, statement.equals(beliefTerm));
                } else if (task.getSentence().isJudgment()) {
                    SyllogisticRules.introVarIndInner(statement, (Statement) component, compound);
                    CompositionalRules.introVarDepInner(compound, component, statement);
                }
            }
        } else {
            if (!task.isStructual() && task.getSentence().isJudgment()) {
                if (statement instanceof Inheritance) {
                    StructuralRules.structuralCompose1(compound, index, statement);
                    if (!(compound instanceof SetExt) && !(compound instanceof SetInt)) {
                        StructuralRules.structuralCompose2(compound, index, statement, side);
                    }    // {A --> B, A @ (A&C)} |- (A&C) --> (B&C)
                } else if (statement instanceof Similarity) {
                    StructuralRules.structuralCompose2(compound, index, statement, side);
                }    // {A <-> B, A @ (A&C)} |- (A&C) <-> (B&C)
            }
        }
    }

    private static void componentAndStatement(CompoundTerm compound, short index, Statement statement, short side) {
        if (!Memory.currentTask.isStructual()) {
            if (statement instanceof Inheritance) {
                StructuralRules.structuralDecompose1(compound, index, statement, side);
                if (!(compound instanceof SetExt) && !(compound instanceof SetInt)) {
                    StructuralRules.structuralDecompose2(statement);
                } // {(C-B) --> (C-A), A @ (C-A)} |- A --> B
                else {
                    StructuralRules.transformSetRelation(compound, statement, side);
                }
            } else if (statement instanceof Similarity) {
                StructuralRules.structuralDecompose2(statement);   // {(C-B) --> (C-A), A @ (C-A)} |- A --> B
                if ((compound instanceof SetExt) || (compound instanceof SetInt)) {
                    StructuralRules.transformSetRelation(compound, statement, side);
                }
            } else if ((statement instanceof Implication) && (compound instanceof Negation)) {
                StructuralRules.contraposition(statement);
            }
        }
    }

    /* ----- inference with one composition link ----- */
    public static void transformTask(Task task, TaskLink tLink) {          // move to StructuralRules???
        CompoundTerm content = (CompoundTerm) task.getContent().clone();
        short[] indices = tLink.getIndices();
        Term inh = null;
        if (indices.length == 2) {
            inh = content;
        } else if (indices.length == 3) {
            if ((indices[0] == 0) && (content instanceof Implication) && (content.componentAt(0) instanceof Conjunction)) {
                inh = ((CompoundTerm) content.componentAt(0)).componentAt(indices[0]);
            } else {
                inh = content.componentAt(indices[0]);
            }
        }
        if (inh instanceof Inheritance) {
            StructuralRules.transformProductImage((Inheritance) inh, content, indices, task);
        }
    }
}
