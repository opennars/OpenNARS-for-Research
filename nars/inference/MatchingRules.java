/*
 * MatchingRules.java
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

import nars.entity.*;
import nars.language.*;
import nars.main.Memory;

/**
 * Directly process a task by a oldBelief, with only two Terms in both. 
 * In matching, the new task is compared with all existing direct Tasks in that Concept, to carry out:
 * <p>
 * revision: between judgments on non-overlapping evidence; 
 * update: between judgments; 
 * satisfy: between a Judgment and a Question/Goal; 
 * merge: between items of the same type and stamp;
 * conversion: between different inheritance relations.
 */
public final class MatchingRules {

    /* -------------------- same contents -------------------- */
    /**
     * The task and belief have the same content
     * <p>
     * called in RuleTables.reason
     * @param task The task
     * @param belief The belief
     */
    public static void match(Task task, Judgment belief) {
        Sentence sentence = task.getSentence();
        if (sentence.isJudgment()) {
            revision(task, belief, true);
        } else {
            trySolution(sentence, belief, null);
        }
    }

    /**
     * Belief update
     * <p>
     * called from oncept.reviseTable
     * @param task The task containing new belief
     * @param oldBelief The previous belief with the same content
     */
    public static void update(Task task, Judgment oldBelief) {
        BudgetValue budget = BudgetFunctions.update(task, oldBelief.getTruth());
        Memory.currentTense = TemporalRules.Relation.BEFORE;
        Memory.doublePremiseTask(budget, oldBelief.getContent(), oldBelief.getTruth());
    }
    // called from Concept (direct) and match (indirect)
    /**
     * Belief revision
     * <p>
     * called from Concept.reviseTable and match
     * @param task The task containing new belief
     * @param oldBelief The previous belief with the same content
     * @param feedbackToLinks Whether to send feedback to the links
     * @return Whether revision happened
     */
    public static boolean revision(Task task, Judgment oldBelief, boolean feedbackToLinks) {
        Judgment newBelief = (Judgment) task.getSentence();
        if (newBelief.getTense() == oldBelief.getTense()) {
            TruthValue tTruth = newBelief.getTruth();
            TruthValue bTruth = oldBelief.getTruth();
            TruthValue truth = TruthFunctions.revision(tTruth, bTruth);
            BudgetValue budget = BudgetFunctions.revise(tTruth, bTruth, truth, task, feedbackToLinks);
            Term content = newBelief.getContent();
            Memory.currentTense = newBelief.getTense();
            Memory.doublePremiseTask(budget, content, truth);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if a Judgment provide a better answer to a Question
     * @param problem The Goal or Question to be answered
     * @param belief The proposed answer
     * @param task The task to be processed
     */
    public static void trySolution(Sentence problem, Judgment belief, Task task) {
        if (problem instanceof Goal) {
            if (belief.getTense() == TemporalRules.Relation.AFTER) {
                return;
            }
        } else if ((problem.getTense() != belief.getTense()) && (belief.getTense() != TemporalRules.Relation.NONE)) {
            return;
        }
        Judgment oldBest = problem.getBestSolution();
        if (betterSolution(belief, oldBest, problem)) {
            problem.setBestSolution(belief);
            BudgetValue budget = BudgetFunctions.solutionEval(problem, belief, task);
            if ((budget != null) && budget.aboveThreshold()) {
                Memory.activatedTask(budget, belief, problem.isInput());
            }
        }
    }

    /**
     * Compare the quality of two solutions
     * @param newSol A new solution
     * @param oldSol A old solution
     * @param problem The problem
     * @return Whether the new one is better
     */
    private static boolean betterSolution(Judgment newSol, Judgment oldSol, Sentence problem) {
        if (oldSol == null) {
            return true;
        } else {
            return (newSol.solutionQuality(problem) > oldSol.solutionQuality(problem));
        }
    }

    /* -------------------- same terms, difference relations -------------------- */
    /**
     * The task and belief match reversely
     */
    public static void matchReverse() {
        Task task = Memory.currentTask;
        Judgment belief = Memory.currentBelief;
        if (task.getContent().getTemporalOrder() != TemporalRules.reverse(belief.getContent().getTemporalOrder())) {
            return;
        }
        Sentence sentence = task.getSentence();
        if (sentence.isJudgment()) {
            inferToSym((Judgment) sentence, belief);
        } else {
            conversion();
        }

    }

    /**
     * Inheritance/Implication matches Similarity/Equivalence
     * @param asym A Inheritance/Implication sentence
     * @param sym A Similarity/Equivalence sentence
     * @param figure location of the shared term
     */
    public static void matchAsymSym(Sentence asym, Sentence sym, int figure) {
        TemporalRules.Relation order1 = asym.getContent().getTemporalOrder();
        TemporalRules.Relation order2 = sym.getContent().getTemporalOrder();
        TemporalRules.Relation order = TemporalRules.temporalSyllogism(order1, order2, figure);
        if (order == null) {
            return;
        }
        if (Memory.currentTask.getSentence().isJudgment()) {
            inferToAsym((Judgment) asym, (Judgment) sym, order);
        } else {
            convertRelation();
        }
    }

    /* -------------------- two-premise inference rules -------------------- */
    /**
     * {<S --> P>, <P --> S} |- <S <-> p>
     * Produce Similarity/Equivalence from a pair of reversed Inheritance/Implication
     * @param judgment1 The first premise
     * @param judgment2 The second premise
     */
    private static void inferToSym(Judgment judgment1, Judgment judgment2) {
        Statement s1 = (Statement) judgment1.getContent();
        Statement s2 = (Statement) judgment2.getContent();
        Term t1 = s1.getSubject();
        Term t2 = s1.getPredicate();
        Term content;
        if (s1 instanceof Inheritance) {
            content = Similarity.make(t1, t2);
        } else if (s1 instanceof ImplicationAfter) {
            content = EquivalenceAfter.make(t1, t2);
        } else if (s1 instanceof ImplicationBefore) {
            content = EquivalenceAfter.make(t2, t1);
        } else {
            content = Equivalence.make(t1, t2);
        }
        TruthValue value1 = judgment1.getTruth();
        TruthValue value2 = judgment2.getTruth();
        TruthValue truth = TruthFunctions.intersection(value1, value2);
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.doublePremiseTask(budget, content, truth);
    }

    /**
     * {<S <-> P>, <P --> S>} |- <S --> P>
     * Produce an Inheritance/Implication from a Similarity/Equivalence and a reversed Inheritance/Implication
     * @param asym The asymmetric premise
     * @param sym The symmetric premise
     */
    private static void inferToAsym(Judgment asym, Judgment sym, TemporalRules.Relation order) {
        Statement statement = (Statement) asym.getContent();
        Term sub = statement.getPredicate();
        Term pre = statement.getSubject();
        Statement content = Statement.make(statement, sub, pre, order);
        TruthValue truth = TruthFunctions.reduceConjunction(sym.getTruth(), asym.getTruth());
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.doublePremiseTask(budget, content, truth);
    }

    /* -------------------- one-premise inference rules -------------------- */
    /**
     * {<P --> S>} |- <S --> P>
     * Produce an Inheritance/Implication from a reversed Inheritance/Implication
     */
    private static void conversion() {
        TruthValue truth = TruthFunctions.conversion(Memory.currentBelief.getTruth());
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.convertedJudgment(truth, budget);
    }

    /**
     * {<S --> P>} |- <S <-> P>
     * {<S <-> P>} |- <S --> P>
     * Switch between Inheritance/Implication and Similarity/Equivalence
     */
    private static void convertRelation() {
        TruthValue truth = Memory.currentBelief.getTruth();
        if (((Statement) Memory.currentTask.getContent()).isCommutative()) {
            truth = TruthFunctions.implied(truth);
        } else {
            truth = TruthFunctions.implying(truth);
        }
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.convertedJudgment(truth, budget);
    }
}
