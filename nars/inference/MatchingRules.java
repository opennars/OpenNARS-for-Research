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
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package nars.inference;

import nars.entity.*;
import nars.language.*;
import nars.io.*;
import nars.main.Memory;

/**
 * Directly process a task by a belief, with only two Terms in both
 */
public final class MatchingRules {
    
    /* -------------------- same contents -------------------- */
    
    // the task and belief match each other
    // forward inference only
    // called only for Figure 0 of syllogism, in ThreeTermRules
    public static void match(Task task, Judgment belief) {
        Sentence sentence = task.getSentence();
        if (sentence.isJudgment())
            revision(task, belief, true);
        else
            trySolution(sentence, belief, null);
    }
    
    // to be rewritten
    public static void update(Task newBelief, Judgment oldBelief) {
//        Base label = Base.make(newBelief.getBase(), oldBelief.getBase());
//        if ((label == null) || (label.length() == 0))
//            return;
//        Task updatedNewBelief = (Task) newBelief.clone();
//        Task updatedOldBelief = (Task) oldBelief.clone();
//        Term content = oldBelief.getContent();
//        Term toPast = Past.make(content);
//        updatedOldBelief.setContent(toPast);
//        updatedNewBelief.setBase(label);
//        TruthValue truth = TruthFunctions.revision(newBelief.getTruth(), oldBelief.getTruth());
//        float confidence = truth.getConfidence();
//        updatedNewBelief.setConfidence(confidence);
//        BudgetValue usage = BudgetFunctions.update(newBelief, oldBelief);
//        updatedNewBelief.setBudget(usage);
//        Memory.derivedTask(updatedNewBelief);
//        Memory.derivedTask(updatedOldBelief);
    }
    
    // called from Concept (direct) and match (indirect)
    public static void revision(Task task, Judgment belief, boolean feedbackToLinks) {
        Judgment judgment = (Judgment) task.getSentence();
        TruthValue tTruth = judgment.getTruth();
        TruthValue bTruth = belief.getTruth();
        TruthValue truth = TruthFunctions.revision(tTruth, bTruth);
        BudgetValue budget = BudgetFunctions.revise(tTruth, bTruth, truth, task, feedbackToLinks);
        Term content = judgment.getContent();
        Memory.doublePremiseTask(budget, content, truth);
    }

    /**
     * Check if a Judgment provide a better answer to a Question
     * @param task The task to be processed
     */
    public static void trySolution(Sentence problem, Judgment belief, Task task) {
        Judgment oldBest = problem.getBestSolution();
        if (betterSolution(belief, oldBest, problem)) {
            problem.setBestSolution(belief);
            BudgetValue budget = BudgetFunctions.solutionEval(problem, belief, task);
            if (budget != null)
                Memory.activatedTask(budget, belief, problem.isInput());
        }
    }
    
    // s1 is a better answer to q than s2 is
    private static boolean betterSolution(Judgment newSol, Judgment oldSol, Sentence problem) {
        if (oldSol == null)
            return true;
        else
            return (newSol.solutionQuality(problem) > oldSol.solutionQuality(problem));
    }

    /* -------------------- same terms, difference relations -------------------- */
    
    // the task and belief match each other reversely
    // forward inference only
    // called only for Figure 5 of syllogism
    public static void matchReverse() {
        Task task = Memory.currentTask;
        Judgment belief = Memory.currentBelief;
        if (task.getContent().getTemporalOrder() != CompoundTerm.temporalReverse(belief.getContent().getTemporalOrder()))
            return;
        Sentence sentence = task.getSentence();
        if (sentence.isJudgment())
            inferToSym((Judgment) sentence, belief);
        else
            conversion();
    }
    
    // Inheritance matches Similarity
    // forward inference only
    // called from ThreeTermRules only
    public static void matchAsymSym(Sentence asym, Sentence sym, int figure) { // (Task task, Sentence belief, int order, boolean inhSim) {
        CompoundTerm.TemporalOrder order1 = asym.getContent().getTemporalOrder();
        CompoundTerm.TemporalOrder order2 = sym.getContent().getTemporalOrder();
        CompoundTerm.TemporalOrder order = CompoundTerm.temporalInferenceWithFigure(order1, order2, figure);
        if (order == CompoundTerm.TemporalOrder.UNSURE)
            return;
        if (Memory.currentTask.getSentence().isJudgment())
            inferToAsym((Judgment) asym, (Judgment) sym, order);
        else {
            convertRelation();
        }
    }

    /* -------------------- two-premise inference rules -------------------- */
    
    /**
     * Produce Similarity/Equivalence from a pire of reversed Inheritance/Implication
     * @param judgment1 The first premise
     * @param judgment2 The second premise
     */
    private static void inferToSym(Judgment judgment1, Judgment judgment2) {
        Statement s1 = (Statement) judgment1.getContent();
        Statement s2 = (Statement) judgment2.getContent();
        Term t1 = s1.getSubject();
        Term t2 = s1.getPredicate();
        Term content;
        if (s1 instanceof Inheritance)
            content = Similarity.make(t1, t2);
        else if (s1 instanceof ImplicationAfter)
            content = EquivalenceAfter.make(t1, t2);
        else if (s1 instanceof ImplicationBefore)
            content = EquivalenceAfter.make(t2, t1);
        else
            content = Equivalence.make(t1, t2);
        TruthValue value1 = judgment1.getTruth();
        TruthValue value2 = judgment2.getTruth();
        TruthValue truth = TruthFunctions.intersection(value1, value2);
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.doublePremiseTask(budget, content, truth);
    }
    
    /**
     * Produce an Inheritance/Implication from a Similarity/Equivalence and a reversed Inheritance/Implication
     * @param asym The asymmetric premise
     * @param sym The symmetric premise
     */
    private static void inferToAsym(Judgment asym, Judgment sym, CompoundTerm.TemporalOrder order) {
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
     * Produce an Inheritance/Implication from a reversed Inheritance/Implication
     */
    private static void conversion() {
        TruthValue truth = TruthFunctions.conversion(Memory.currentBelief.getTruth());
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.singlePremiseTask(truth, budget);
    }
    
    // switch between Inheritance/Implication and Similarity/Equivalence
    private static void convertRelation() {
        TruthValue truth = Memory.currentBelief.getTruth();
        if (((Statement) Memory.currentTask.getContent()).isCommutative())
            truth = TruthFunctions.implied(truth);
        else
            truth = TruthFunctions.implying(truth);
        BudgetValue budget = BudgetFunctions.forward(truth);
        Memory.singlePremiseTask(truth, budget);
    }
}
