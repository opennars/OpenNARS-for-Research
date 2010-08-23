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
 * but WITHOUT ANY WARRANTY; without even the abduction warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Open-NARS.  If not, see <http://www.gnu.org/licenses/>.
 */
package nars.inference;

import nars.entity.*;
import nars.language.*;
import nars.main.*;

/**
 * Directly process a task by a oldBelief, with only two Terms in both. 
 * In matching, the new task is compared with all existing direct Tasks in that Concept, to carry out:
 * <p>
 * revision: between judgments on non-overlapping evidence;
 * revision: between judgments;
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
            revision((Judgment) sentence, belief, true);
        } else {
            trySolution(sentence, belief, task);
        }
    }

    /**
     * Belief revision
     * <p>
     * called from Concept.reviseTable and match
     * @param newBelief The new belief in task
     * @param oldBelief The previous belief with the same content
     * @param feedbackToLinks Whether to send feedback to the links
     */
    public static void revision(Judgment newBelief, Judgment oldBelief, boolean feedbackToLinks) {
        long time1 = newBelief.getEventTime();
        long time2 = oldBelief.getEventTime();
        TruthValue newTruth = newBelief.getTruth();
        TruthValue oldTruth = oldBelief.getTruth();
        if (time1 != time2) {
            oldTruth = TruthFunctions.temporalCasting(oldTruth, time2, time1, Memory.currentTask.getSentence().getCreationTime());
            Term term1 = newBelief.getContent();
            Term term2 = oldBelief.getContent();
            if (term2.isTemporal() && (!term1.isTemporal() || (term2.getOrder() != term1.getOrder()))) {
                oldTruth = TruthFunctions.temporalCasting(oldTruth, term2.getOrder(), term1.getOrder(), 0); // to be refined
            }
        }
        TruthValue truth = TruthFunctions.revision(newTruth, oldTruth);
        BudgetValue budget = BudgetFunctions.revise(newTruth, oldTruth, truth, feedbackToLinks);
        Term content = newBelief.getContent();
        Memory.revisionTask(budget, content, truth, oldBelief, newBelief);
    }

    /**
     * Check if a Judgment provide a better answer to a Question or Goal
     * @param problem The Goal or Question to be answered
     * @param belief The proposed answer
     * @param task The task to be processed
     */
    public static void trySolution(Sentence problem, Judgment belief, Task task) {
        if (problem instanceof Question) {
            long taskTime = problem.getEventTime();
            long beliefTime = belief.getEventTime();
            if (taskTime != beliefTime) {
                belief = (Judgment) belief.clone();
                TruthValue castedTruth = TruthFunctions.temporalCasting(belief.getTruth(), beliefTime, taskTime, problem.getCreationTime());
                belief.setTruth(castedTruth);
                belief.getStamp().setEventTime(taskTime);
            }
        }
        Judgment oldBest = problem.getBestSolution();
        float newQ = solutionQuality(problem, belief);
        if (oldBest != null) {
            float oldQ = solutionQuality(problem, oldBest);
            if (oldQ >= newQ) {
                if (problem instanceof Goal) {
                    Memory.adjustHappy(oldQ, task.getPriority());
                }
                return;
            }
        }
        problem.setBestSolution(belief);
        if (problem instanceof Goal) {
            Memory.adjustHappy(newQ, task.getPriority());
        } else if (problem.isQuestion()) {
            ((Question) problem).checkFeedback();
        }
        BudgetValue budget = BudgetFunctions.solutionEval(problem, belief, task);
        if ((budget != null) && budget.aboveThreshold()) {
            Memory.activatedTask(budget, belief, problem.isInput());
        }
    }

    /**
     * Evaluate the quality of the judgment as a solution to a problem
     * @param problem A goal or question
     * @param solution The solution to be evaluated
     * @return The quality of the judgment as the solution
     */
    public static float solutionQuality(Sentence problem, Judgment solution) {
        if (problem == null) {
            return solution.getTruth().getExpectation();
        }
        long taskTime = problem.getEventTime();
        long beliefTime = solution.getEventTime();
        TruthValue truth = solution.getTruth();
        if ((taskTime != beliefTime) && (beliefTime != Stamp.ALWAYS)) {
            truth = TruthFunctions.temporalCasting(truth, beliefTime, taskTime, problem.getCreationTime());
        }
        if ((problem instanceof Goal) || (problem instanceof Quest)) {
            return truth.getExpectation();
        } else if (problem.getContent().isConstant()) {   // "yes/no" question
            return truth.getConfidence();
        } else {                                    // "what" question or goal
            return truth.getExpectation() / solution.getContent().getComplexity();
        }
    }

    /* -------------------- same terms, difference relations -------------------- */
    /**
     * The task and belief match reversely
     */
    public static void matchReverse() {
        Task task = Memory.currentTask;
        Judgment belief = Memory.currentBelief;
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
        int order1 = asym.getContent().getOrder();
        int order2 = sym.getContent().getOrder();
        int order = SyllogisticRules.temporalSyllogism(order1, order2, figure);
        if ((order1 != order2) && (order == 0)) {
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
        Term t1 = s1.getSubject();
        Term t2 = s1.getPredicate();
        Term content;
        if (s1 instanceof Inheritance) {
            content = Similarity.make(t1, t2);
        } else {
            int order = s1.getOrder();
            if (order < 0) {
                content = Equivalence.make(t1, t2, true, -order);
            } else {
                content = Equivalence.make(t1, t2, judgment1.isEvent(), order);
            }
        }
        TruthValue value1 = judgment1.getTruth();
        TruthValue value2 = judgment2.getTruth();
        TruthValue truth = TruthFunctions.intersection(value1, value2);
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.doublePremiseTask(budget, content, truth, judgment1, judgment2);
    }

    /**
     * {<S <-> P>, <P --> S>} |- <S --> P>
     * Produce an Inheritance/Implication from a Similarity/Equivalence and a reversed Inheritance/Implication
     * @param asym The asymmetric premise
     * @param sym The symmetric premise
     */
    private static void inferToAsym(Judgment asym, Judgment sym, int order) {
        Statement statement = (Statement) asym.getContent();
        Term sub = statement.getPredicate();
        Term pre = statement.getSubject();
        Statement content = Statement.make(statement, sub, pre, sym.isEvent(), order);
        TruthValue truth = TruthFunctions.reduceConjunction(sym.getTruth(), asym.getTruth());
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.doublePremiseTask(budget, content, truth, sym, asym);
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
            truth = TruthFunctions.abduction(truth, 1.0f);
        } else {
            truth = TruthFunctions.deduction(truth, 1.0f);
        }
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.convertedJudgment(truth, budget);
    }
}
