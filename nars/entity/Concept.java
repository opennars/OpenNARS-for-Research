/*
 * Concept.java
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
package nars.entity;

import java.util.*;
import java.io.*;

import nars.inference.*;
import nars.language.*;
import nars.io.*;
import nars.gui.*;
import nars.main.*;
import nars.operation.*;
import nars.storage.*;

/**
 * A concept contains information associated with a term, including directly 
 * and indirectly related tasks and beliefs.
 * <p>
 * To make sure the space will be released, the only allowed reference to a concept are
 * those in a ConceptBag. All other access go through the Term that names the concept.
 */
public final class Concept extends Item {
    /* Constant term as the unique ID of the concept */
    private Term term;
    /* Direct Task lists (they may never be used for a Term that is not used as a Statement) */
    private Question directQuestion;                // Question that can be directly answered
    private ArrayList<Goal> directGoals;            // Goals that can be directly achieved
    private ArrayList<Judgment> directBeliefs;      // Judgments with the same content
    private boolean revisible = true;               // truth value of judgments can be revised
    /* Link bags for indirect processing (for all concepts) */
    private TaskLinkBag taskLinks;
    private TermLinkBag termLinks;
    /* Link templates of TermLink, only in concepts with CompoundTerm */
    private ArrayList<TermLink> termLinkTemplates;
    private boolean event = false;      // default
    /* Display related fields */
    private boolean showing;            // whether this concept has an active window
    private ConceptWindow window;       // (direct) content display window

    /* ---------- constructor ---------- */
    /**
     * Constructor, called in Memory.getConcept only
     * @param tm A constant term corresponding to the concept
     */
    public Concept(Term tm) {
        super();
        term = tm;
        key = tm.toString();
        directBeliefs = new ArrayList<Judgment>();
        directGoals = new ArrayList<Goal>();
        directQuestion = null;
        taskLinks = new TaskLinkBag();
        termLinks = new TermLinkBag();
        if (tm instanceof CompoundTerm) {
            termLinkTemplates = ((CompoundTerm) tm).prepareComponentLinks();
            checkRevisibility();
            if (tm instanceof Temporal) {
                markEvent();
            }
        }
        showing = false;
        window = null;
    }

    /**
     * Judgments with dependent variable cannot be revised
     */
    private void checkRevisibility() {
        revisible = ((key.indexOf("()") < 0) && (key.indexOf("(#")) < 0);
    }

    /**
     * Terms in temporal compounds is event
     */
    private void markEvent() {
        for (TermLink tLink : termLinkTemplates) {
            Term target = tLink.getTarget();
            Concept concept = Memory.termToConcept(target);
            if (concept != null) {
                concept.setEvent();
            }
        }
    }

    /* ---------- direct processing of tasks ---------- */
    /**
     * Directly process a new task, called in Memory.immediateProcess only
     * Called exactly once on each task, using local information and finishing in a constant time
     * Provide feedback in the priority value of the task
     * @param task The task to be processed
     */
    public void directProcess(Task task) {
//        Memory.currentTask = task;
        Sentence sentence = task.getSentence();
        if (sentence instanceof Question) {
            processQuestion((Question) sentence, task);
        } else if (sentence instanceof Goal) {
            processGoal((Goal) sentence, task);
        } else {
            processJudgment((Judgment) sentence, task);
        }
        Memory.activateConcept(this, task.getBudget());
        if (showing) {
            window.post(displayContent());
        }
    }

    /**
     * To answer a question by existing beliefs
     * @param ques The question to be answered
     * @param task The task to be processed
     */
    private void processQuestion(Question ques, Task task) {
        if (directQuestion == null) { // keep the existing one for answer information
            directQuestion = ques;
        }
        for (Judgment judg : directBeliefs) {
            MatchingRules.trySolution(directQuestion, judg, task);    // look for better answer
        }
    }

    /**
     * Direct processing a new goal
     * @param goal The goal to be processed
     * @param task The task to be processed
     */
    private void processGoal(Goal goal, Task task) {
        if (revisible) {
            reviseTable(goal, task, directGoals);       // revise desire
        }
        for (Judgment judg : directBeliefs) {
            MatchingRules.trySolution(goal, judg, task);    // reality check
        }
        float desire = 2 * goal.getTruth().getExpectation() - 1;
        float qua = (desire < 0) ? 0 : desire;  // decision making
        task.setQuality(qua);
        if (task.aboveThreshold()) {
            Term content = goal.getContent();
            if (content instanceof Inheritance) {
                Term pred = (((Inheritance) content).getPredicate());
                if (pred instanceof Operator) {
                    ((Operator) pred).call(task);   // directly archiving
                    return;
                }
            }
        }
        if (task.getPriority() > 0) {
            addToTable(goal, directGoals, Parameters.MAXMUM_GOALS_LENGTH);   // indirectly archiving
        }
    }

    /**
     * To accept a new judgment as belief
     * This method must be put as the last of the three orverloading methods
     * @param s The judgment to be accepted
     * @param task The task to be processed
     */
    private void processJudgment(Judgment judg, Task task) {
        if (revisible) {
            reviseTable(judg, task, directBeliefs);
        }
        if (task.getPriority() > 0) {               // if still valuable --- necessary???
            if (directQuestion != null) {
                MatchingRules.trySolution(directQuestion, judg, task);
            }
            for (Goal goal : directGoals) {
                MatchingRules.trySolution(goal, judg, task);
            }
            addToTable(judg, directBeliefs, Parameters.MAXMUM_BELIEF_LENGTH);
        }
    }

    /**
     * Revise existing beliefs or goals
     * @param task The task to be processed
     * @param table The table to be revised
     */
    private void reviseTable(Judgment newSentence, Task task, ArrayList table) {
        Judgment belief;
        for (int i = table.size() - 1; i >= 0; i--) {
            belief = (Judgment) table.get(i);
            if ((newSentence.getTense() == belief.getTense()) && belief.noOverlapping(newSentence)) {
                if (isUpdate(newSentence, belief)) {
                    MatchingRules.update(task, belief);
                    table.remove(i);
                } else {
                    MatchingRules.revision(task, belief, false);
                }
            }
        }
    }

    private boolean isUpdate(Judgment newBelief, Judgment oldBelief) {
        if (oldBelief.getTense() != TemporalRules.Relation.WHEN) {
            return false;
        }
        if (newBelief.getTruth().getExpDifAbs(oldBelief.getTruth()) < 0.5) {
            return false;
        }
        if ((newBelief.getBase().latest() <= oldBelief.getBase().latest())) {
            return false;
        }
        return true;
    }

    /**
     * Add a new belief or goal into the table
     * Sort the beliefs/goals by rank, and remove redundant or low rank one
     * @param newJudgment The judgment to be processed
     * @param table The table to be revised
     * @param capacity The capacity of the table
     */
    private void addToTable(Judgment newJudgment, ArrayList table, int capacity) {
        float rank1 = BudgetFunctions.rankBelief(newJudgment);    // for the new belief
        Judgment judgment2;
        float rank2;
        int i;
        for (i = 0; i < table.size(); i++) {        // go through everyone
            judgment2 = (Judgment) table.get(i);
            rank2 = BudgetFunctions.rankBelief(judgment2); // previous belief
            if (rank1 >= rank2) {
                if (newJudgment.equivalentTo(judgment2)) {
                    return;
                }
                table.add(i, newJudgment);
                break;
            }
        }
        if (table.size() == capacity) {
            table.remove(capacity - 1);
        } else if (i == table.size()) {
            table.add(newJudgment);
        }
    }

    /* ---------- insert Links for indirect processing ---------- */
    /**
     * Insert a TaskLink into the TaskLink bag, and build/activate the TermLinks
     * called only from Memory.continuedProcess
     * @param taskLink The termLink to be inserted
     */
    public void insertTaskLink(TaskLink taskLink) {
        BudgetValue budget = taskLink.getBudget();
        taskLinks.putIn(taskLink);
        Memory.activateConcept(this, budget);       // activate the concept
        if (term instanceof CompoundTerm) {
            buildTermLinks(budget);             // always recognize structure
        }
    }

    /**
     * Recursively build TermLinks between a compound and its components
     * @param budget The budget of the task
     */
    private void buildTermLinks(BudgetValue budget) {
        Term t;
        Concept c;
        TermLink cLink1, cLink2;
        BudgetValue subBudget = BudgetFunctions.distributeAmongLinks(budget, termLinkTemplates.size());
        if (subBudget.aboveThreshold()) {
            for (TermLink link : termLinkTemplates) {
                t = link.getTarget();
                c = Memory.getConcept(t);
                cLink1 = new TermLink(t, link, subBudget);
                insertTermLink(cLink1);   // this termLink to that
                cLink2 = new TermLink(term, link, subBudget);
                c.insertTermLink(cLink2);   // that termLink to this
                if (t instanceof CompoundTerm) {
                    c.buildTermLinks(subBudget);
                }
            }
        }
    }

    /**
     * Insert a TermLink into the TermLink bag, called from buildTermLinks only
     * @param termLink The termLink to be inserted
     */
    public void insertTermLink(TermLink termLink) {
        termLinks.putIn(termLink);
        Memory.activateConcept(this, termLink.getBudget());
    }

    /* ---------- access local information ---------- */
    /**
     * Return the assocated term, called from Memory only
     * @return The assocated term
     */
    public Term getTerm() {
        return term;
    }

    /**
     * Distinguish events from non-events
     * @return Whether the concept is an event
     */
    public boolean isEvent() {
        return event;
    }

    /**
     * Mark the concept as an event
     */
    public void setEvent() {
        event = true;
    }

    /**
     * Return a string representation of the concept, called in ConceptBag only
     * @return The concept name, with budget in the full version
     */
    @Override
    public String toString() {  // called from concept bag
        if (NARS.isStandAlone()) {
            return (super.toString2() + " " + key);
        } else {
            return key;
        }
    }

    /**
     * Return the quality of the concept
     * @return The quality value
     */
    @Override
    public float getQuality() {
        // re-calculate-and-set? consider syntactic complexity?
        return UtilityFunctions.and(taskLinks.averagePriority(), termLinks.averagePriority());
    }

    /**
     * Return the templates for TermLinks, only called in Memory.continuedProcess
     * @return The template list
     */
    public ArrayList<TermLink> getTermLinkTemplates() {
        return termLinkTemplates;
    }

    /**
     * Select a belief to interact with the given task in inference
     * only called in RuleTables.reason
     * get the first qualified one
     * @param task The selected task
     * @return The selected belief
     */
    public Judgment getBelief(Task task) {
        Sentence sentence = task.getSentence();
        Judgment belief;
        for (int i = 0; i < directBeliefs.size(); i++) {
            belief = directBeliefs.get(i);
            if ((sentence instanceof Question) || belief.noOverlapping((Judgment) sentence)) {
                Record.append(" * Selected Belief: " + belief + "\n");
                return belief;
            }
        }
        return null;
    }

    /* ---------- main loop ---------- */
    /**
     * An atomic step in a concept, only called in Memory.processConcept
     */
    public void fire() {
        TaskLink tLink = (TaskLink) taskLinks.takeOut();
        if (tLink == null) {
            return;
        }
        Memory.currentTaskLink = tLink;
        Memory.currentBeliefLink = null;
//        if (NARS.isStandAlone())
        Record.append(" * Selected TaskLink: " + tLink + "\n");
        Task task = tLink.getTargetTask();
//        Record.append(" * Selected Task: " + task + "\n");
        Memory.currentTask = task;
        if ((tLink.getType() == TermLink.TRANSFORM) && !task.isStructual()) {
            RuleTables.transformTask(task, tLink);      // inference from a TaskLink and the Task --- for Product and Image
            return; // cannot be used otherwise
        }
        TermLink mLink = (TermLink) termLinks.takeOut(tLink);  // to avoid repeated syllogism
        if (mLink != null) {
            if (NARS.isStandAlone()) {
                Record.append(" * Selected TermLink: " + mLink + "\n");
            }
            Memory.currentBeliefLink = mLink;
            RuleTables.reason(tLink, mLink);
            termLinks.putBack(mLink);
        }
        taskLinks.putBack(tLink);
    }

    /* ---------- display ---------- */
    /**
     * Start displaying contents and links, called from ConceptWindow only
     */
    public void startPlay() {
        window = new ConceptWindow(this);
        showing = true;
        window.post(displayContent());
        taskLinks.startPlay("Task Links in " + term);
        termLinks.startPlay("Term Links in " + term);
    }

    /**
     * Resume display, called from ConceptWindow only
     */
    public void play() {
        showing = true;
        window.post(displayContent());
    }

    // 
    /**
     * Stop display, called from ConceptWindow only
     */
    public void stop() {
        showing = false;
    }

    /**
     * Collect direct belief, questions, and goals for display
     * @return String representation of direct content
     */
    private String displayContent() {
        StringBuffer buffer = new StringBuffer();
        if (directBeliefs.size() > 0) {
            buffer.append("  Beliefs:\n");
            for (int i = 0; i < directBeliefs.size(); i++) {
                buffer.append(directBeliefs.get(i) + "\n");
            }
        }
        if (directGoals.size() > 0) {
            buffer.append("\n  Goals:\n");
            for (int i = 0; i < directGoals.size(); i++) {
                buffer.append(directGoals.get(i) + "\n");
            }
        }
        if (directQuestion != null) {
            buffer.append("\n  Question:\n" + directQuestion + "\n");
        }
        return buffer.toString();
    }
}

