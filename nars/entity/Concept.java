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
 * along with Open-NARS.  If not, see <http://www.gnu.org/licenses/>.
 */
package nars.entity;

import java.util.ArrayList;

import nars.gui.ConceptWindow;
import nars.inference.*;
import nars.io.Record;
import nars.language.*;
import nars.main.*;
import nars.operation.Operator;
import nars.storage.*;

/**
 * A concept contains information associated with a term, including directly 
 * and indirectly related tasks and beliefs.
 * <p>
 * To make sure the space will be released, the only allowed reference to a concept are
 * those in a ConceptBag. All other access go through the Term that names the concept.
 */
public final class Concept extends Item {

    /** The term is the unique ID of the concept */
    private Term term;
    /** Question directly asked about the term */
    private Question directQuestion;
    /** Goals directly requested on the term */
    private ArrayList<Goal> directGoals;
    /** Judgments directly made about the term */
    private ArrayList<Judgment> directBeliefs;
    /** Whether truth value of judgments can be revised */
    private boolean revisible = true;               // 
    /** Task links for indirect processing */
    private TaskLinkBag taskLinks;
    /** Term links between the term and its components and compounds */
    private TermLinkBag termLinks;
    /** Link templates of TermLink, only in concepts with CompoundTerm */
    private ArrayList<TermLink> termLinkTemplates;
    /** Whether the term specifies an event [to be refined] */
    private boolean event = false;
    /** Whether the content of the concept is being displayed */
    private boolean showing = false;
    /** The display window */
    private ConceptWindow window = null;

    /* ---------- constructor and intialization ---------- */
    /**
     * Constructor, called in Memory.getConcept only
     * @param tm A term corresponding to the concept
     */
    public Concept(Term tm) {
        super();
        key = tm.toString();
        term = tm;
        directQuestion = null;
        directGoals = new ArrayList<Goal>();
        directBeliefs = new ArrayList<Judgment>();
        taskLinks = new TaskLinkBag();
        termLinks = new TermLinkBag();
        if (tm instanceof CompoundTerm) {
            termLinkTemplates = ((CompoundTerm) tm).prepareComponentLinks();
            checkRevisibility();
            if (tm instanceof Temporal) {
                markEventComponents();
            }
        }
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
    private void markEventComponents() {
        Term component;
        for (TermLink tLink : termLinkTemplates) {
            component = tLink.getTarget();
            Concept concept = Memory.termToConcept(component);
            if (concept != null) {
                concept.setEvent();
            }
        }
    }

    /* ---------- direct processing of tasks ---------- */
    /**
     * Directly process a new task. Called exactly once on each task.
     * Using local information and finishing in a constant time.
     * Provide feedback in the budget value of the task.
     * <p>
     * called in Memory.immediateProcess only
     * @param task The task to be processed
     */
    public void directProcess(Task task) {
        Sentence sentence = task.getSentence();
        if (sentence instanceof Question) {
            processQuestion((Question) sentence, task);
        } else if (sentence instanceof Goal) {
            processGoal((Goal) sentence, task);
        } else {
            processJudgment((Judgment) sentence, task);
        }
        if (showing) {
            window.post(displayContent());  // show changes
        }
    }

    /**
     * To answer a question by existing beliefs
     * @param ques The question to be answered
     * @param task The task to be processed
     */
    private void processQuestion(Question ques, Task task) {
        if (directQuestion == null) {           // keep the existing one for answer information
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
        boolean revised = false;
        if (revisible) {
            revised = reviseTable(goal, task, directGoals);     // revise desire
        }
        if (revised) {      // don't process this goal, but the revised version
            return;
        }
        for (Judgment judg : directBeliefs) {
            MatchingRules.trySolution(goal, judg, task);        // reality check
        }
        Term content = goal.getContent();
        if ((task.getPriority() >= Parameters.PRIORITY_THRESHOLD) && (content instanceof Inheritance)) {
            Term pred = ((Inheritance) content).getPredicate();
            if (pred instanceof Operator) {
                float netDesire = goal.getTruth().getExpectation();
                if (netDesire > Parameters.DECISION_THRESHOLD) {
                    ((Operator) pred).call(task);
                    task.setPriority(0.0f);        // each call is executed once
                    return;
                }
            }
        }
        if (task.aboveThreshold()) {
            addToTable(goal, directGoals, Parameters.MAXMUM_GOALS_LENGTH);   // indirectly archiving
        }
    }

    /**
     * To accept a new judgment as belief, and check for revisions and solutions
     * @param judg The judgment to be accepted
     * @param task The task to be processed
     */
    private void processJudgment(Judgment judg, Task task) {
        if (judg.getTense() != TemporalRules.Relation.NONE) {
            setEvent();
        }
        if (revisible) {
            boolean revised = reviseTable(judg, task, directBeliefs);
            if (!revised) {
                if (isEvent()) {
                    TemporalRules.eventProcessing(task);
                }
            }
        }
        if (task.aboveThreshold()) {
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
    private boolean reviseTable(Judgment newSentence, Task task, ArrayList table) {
        boolean revised = false;
        Judgment belief;
        for (int i = table.size() - 1; i >= 0; i--) {
            belief = (Judgment) table.get(i);
            if ((newSentence.getTense() == belief.getTense()) && belief.noOverlapping(newSentence)) {
                if (isUpdate(newSentence, belief)) {
                    MatchingRules.update(task, belief);
                    table.remove(i);
                } else {
                    revised |= MatchingRules.revision(task, belief, false);
                }
            }
        }
        return revised;
    }

    /**
     * Distinguish update from revision [to be refined]
     * @param newBelief The new belief
     * @param oldBelief The previous belief
     * @return Whether the operation is update
     */
    private boolean isUpdate(Judgment newBelief, Judgment oldBelief) {
        if (oldBelief.getTense() != TemporalRules.Relation.WHEN) {
            return false;   // only update belief with present tense
        }
        if (newBelief.getTruth().getExpDifAbs(oldBelief.getTruth()) < 0.5) {
            return false;   // update means major change in expectation
        }
        if ((newBelief.getStamp().latest() <= oldBelief.getStamp().latest())) {
            return false;   // update previous experience with recent experience
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
    @SuppressWarnings("unchecked")
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
     * Insert a TaskLink into the TaskLink bag
     * <p>
     * called only from Memory.continuedProcess
     * @param taskLink The termLink to be inserted
     */
    public void insertTaskLink(TaskLink taskLink) {
        BudgetValue budget = taskLink.getBudget();
        taskLinks.putIn(taskLink);
        Memory.activateConcept(this, budget);       // activate the concept
    }

    /**
     * Recursively build TermLinks between a compound and its components
     * <p>
     * called only from Memory.continuedProcess
     * @param budget The budget of the task
     */
    public void buildTermLinks(BudgetValue budget) {
        Term t;
        Concept concept;
        TermLink termLink1, termLink2;
        if (termLinkTemplates.size() > 0) {
            BudgetValue subBudget = BudgetFunctions.distributeAmongLinks(budget, termLinkTemplates.size());
            if (subBudget.aboveThreshold()) {
                for (TermLink template : termLinkTemplates) {
                    if (template.getType() != TermLink.TRANSFORM) {
                        t = template.getTarget();
                        concept = Memory.getConcept(t);
                        termLink1 = new TermLink(t, template, subBudget);
                        insertTermLink(termLink1);   // this termLink to that
                        termLink2 = new TermLink(term, template, subBudget);
                        concept.insertTermLink(termLink2);   // that termLink to this
                        if (t instanceof CompoundTerm) {
                            concept.buildTermLinks(subBudget);
                        }
                    }
                }
            }
        }
    }

    /**
     * Insert a TermLink into the TermLink bag
     * <p>
     * called from buildTermLinks only
     * @param termLink The termLink to be inserted
     */
    public void insertTermLink(TermLink termLink) {
        termLinks.putIn(termLink);
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
     * Recalculate the quality of the concept [to be refined]
     * @return The quality value
     */
    @Override
    public float getQuality() {
        float linkPriority = termLinks.averagePriority();
        float termComplexityFactor = 1.0f / term.getComplexity();
        return UtilityFunctions.or(linkPriority, termComplexityFactor);
    }

    /**
     * Return the templates for TermLinks, only called in Memory.continuedProcess
     * @return The template get
     */
    public ArrayList<TermLink> getTermLinkTemplates() {
        return termLinkTemplates;
    }

    /**
     * Select a belief to interact with the given task in inference
     * <p>
     * get the first qualified one
     * <p>
     * only called in RuleTables.reason
     * @param task The selected task
     * @return The selected belief
     */
    public Judgment getBelief(Task task) {
        Sentence taskSentence = task.getSentence();
        Judgment belief;
        for (int i = 0; i < directBeliefs.size(); i++) {
            belief = directBeliefs.get(i);
            if (belief.noOverlapping(taskSentence)) {
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
        TaskLink tLink = taskLinks.takeOut();
        if (tLink == null) {
            return;
        }
        Memory.currentTaskLink = tLink;
        Memory.currentBeliefLink = null;
        Record.append(" * Selected TaskLink: " + tLink + "\n");
        Task task = tLink.getTargetTask();
        Memory.currentTask = task;
        if (tLink.getType() == TermLink.TRANSFORM) {
            RuleTables.transformTask(task, tLink);  // to turn this into structural inference as below?
            return;
        }
        int termLinkCount = Parameters.MAX_REASONED_TERM_LINK;
        while (Memory.noResult() && (termLinkCount > 0)) {
            TermLink termLink = termLinks.takeOut(tLink);
            if (termLink != null) {
                Record.append(" * Selected TermLink: " + termLink + "\n");
                Memory.currentBeliefLink = termLink;
                RuleTables.reason(tLink, termLink);
                termLinks.putBack(termLink);
                termLinkCount--;
            } else {
                termLinkCount = 0;
            }
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

