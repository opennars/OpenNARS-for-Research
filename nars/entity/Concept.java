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
 * A concept contains information directly related to a term, including directly and indirectly related tasks and beliefs.
 * <p>
 * To make sure the space will be released, no other will refer to a concept, except in a concept bag.
 */
public final class Concept extends Item {
    /* Constant term as the unique ID of the concept */
    private Term term;
    /* Direct Task lists (they may never be used for a Term that is not used as a Statement) */
    private ArrayList<Judgment> directBeliefs;      // Judgments with the same content
    private ArrayList<Goal> directGoals;            // Goals that can be directly achieved
    private Question directQuestion;                // Question that can be directly answered
    private boolean revisible = true;               // truth value of judgments can be revised
    /* Link bags for indirect processing (always used) */
    private ArrayList<TermLink> linkTemplates;  // templates of TermLink, only in Concepts for CompoundTerms
    private TaskLinkBag taskLinks;
    private TermLinkBag termLinks;
    /* Display related fields */
    private boolean showing;            // whether this concept has an active window
    private ConceptWindow window;       // (direct) content display window
    
    /* ---------- constructor ---------- */
    
    /**
     * Constructor
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
            linkTemplates = ((CompoundTerm) tm).prepareComponentLinks();
            checkRevisibility();
        }
        showing = false;
        window = null;
    }
    
    private void checkRevisibility() {
        revisible = !(term instanceof Tense);                                   // no tense
        if (revisible)
            revisible = ((key.indexOf("()") < 0) && (key.indexOf("(#")) < 0);   // no dependent variable
    }
    
    /* ---------- add direct information as Tasks ---------- */
    
    /**
     * New task to be directly processed in a constant time, called from Memory only
     * @param task The task to be processed
     */
    public void directProcess(Task task) {
        Memory.currentTask = task;
        Sentence sentence = task.getSentence();
        if (sentence instanceof Question)
            processQuestion(task);
        else if (sentence instanceof Goal)
            processGoal(task);
        else
            processJudgment(task);
        Memory.activateConcept(this, task.getBudget());
        if (showing)
            window.post(displayContent());
    }
    
    /**
     * New question to be directly answered by existing beliefs
     * @param task The task to be processed
     */
    private void processQuestion(Task task) {
        if (directQuestion == null)
            directQuestion = (Question) task.getSentence();         // remember it
        for (int i = 0; i < directBeliefs.size(); i++) {
            Judgment judg = directBeliefs.get(i);
            MatchingRules.trySolution(directQuestion, judg, task);    // look for better answer
        }
    }
    
    /**
     * New judgment
     * @param task The task to be processed
     */
    private void processJudgment(Task task) {
        Judgment judg = (Judgment) task.getSentence();
        if (revisible)
            reviseTable(task, directBeliefs);
        else
            updateTable(task);
        if (task.getPriority() > 0) {               // if still valuable --- necessary???
            if (directQuestion != null)
                MatchingRules.trySolution(directQuestion, judg, task);
            for (int i = 0; i < directGoals.size(); i++) {
                Goal goal = directGoals.get(i);
                MatchingRules.trySolution(goal, judg, task);
            }
            addToTable(judg, directBeliefs, Parameters.MAXMUM_BELIEF_LENGTH);
        }
    }
    
    /**
     * New goal
     * @param task The task to be processed
     */
    private void processGoal(Task task) {
        Goal goal = (Goal) task.getSentence();
        if (revisible)
            reviseTable(task, directGoals);
        else
            updateTable(task);
        for (int i = 0; i < directBeliefs.size(); i++) {
            Judgment judg = directBeliefs.get(i);
            MatchingRules.trySolution(goal, judg, task);
        }
        if (task.getPriority() > 0) {              // if still valuable
            addToTable(goal, directGoals, Parameters.MAXMUM_GOALS_LENGTH);         // with the feedbacks
        }
        decisionMaking(task);
    }
    
    private void decisionMaking(Task task) {    // add plausibility
        Goal goal = (Goal) task.getSentence();
        float desire = 2 * goal.getTruth().getExpectation() - 1;
        float quality = (desire < 0) ? 0 : desire;
        task.setQuality(quality);
    }
    
    // revise previous beliefs or goals
    private void reviseTable(Task task, ArrayList table) {
        Judgment belief;
        for (int i = 0; i < table.size(); i++) {    // call select()
            belief = (Judgment) table.get(i);
            if (belief.noOverlapping((Judgment) task.getSentence()))
                MatchingRules.revision(task, belief, false);
        }
    }
    
    // to be rewritten
    private void updateTable(Task task) {
//        Judgment belief;
//        for (int i = 0; i < directBeliefs.size(); i++) {    // call select()
//            belief = directBeliefs.get(i);
//            if (((Judgment) task.getSentence()).getBase().latest() > belief.getBase().latest())
//                MatchingRules.update(task, belief);
//        }
    }
    
    // add the Task as a new direct Belief or Goal, remove redundant ones
    // table sorted by rank
    private void addToTable(Judgment newJudgment, ArrayList table, int capacity) {
        float rank1 = BudgetFunctions.rankBelief(newJudgment);    // for the new belief
        Base base1 = newJudgment.getBase();
        Judgment judgment2;
        float rank2;
        int i;
        for (i = 0; i < table.size(); i++) {        // go through everyone
            judgment2 = (Judgment) table.get(i);
            rank2 = BudgetFunctions.rankBelief(judgment2); // previous belief
            if (rank1 >= rank2) {
                if (newJudgment.equivalentTo(judgment2))
                    return;
                table.add(i, newJudgment);
                break;
            }
        }
        if (table.size() == capacity)
            table.remove(capacity - 1);
        else if (i == table.size())
            table.add(newJudgment);
    }

    // return a piece of Belief to be used with the task
    // get the first qualified one
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
    
    /* ---------- insert relational information as Links ---------- */
    
    public ArrayList<TermLink> getTermLinks() {
        return linkTemplates;
    }
    
    // insert TaskLink into the task base, called from Memory only
    public void insertTaskLink(TaskLink taskLink) {
        BudgetValue budget = taskLink.getBudget();
        taskLinks.putIn(taskLink);
        Memory.activateConcept(this, budget);       // activate the concept
        if (term instanceof CompoundTerm)
            buildTermLinks(budget);
    }
    
    private void buildTermLinks(BudgetValue budget) {
        Term t;
        Concept c;
        TermLink cLink1, cLink2;
        BudgetValue subBudget = BudgetFunctions.distributeAmongLinks(budget, linkTemplates.size());
        if (!subBudget.aboveThreshold())
            return;
        for(TermLink link : linkTemplates) {
            t = link.getTarget();
            c = Memory.getConcept(t);
            cLink1 = new TermLink(t, link, subBudget);
            insertTermLink(cLink1);   // this link to that
            cLink2 = new TermLink(term, link, subBudget);
            c.insertTermLink(cLink2);   // that link to this
            if (t instanceof CompoundTerm)
                c.buildTermLinks(subBudget);
        }
    }
    
    // insert TermLink into the Belief base, called from Memory only
    public void insertTermLink(TermLink cLink) {
        termLinks.putIn(cLink);
        Memory.activateConcept(this, cLink.getBudget());
    }
    
    /* ---------- main loop ---------- */
    
    // a single step of syllogism within a concept
    public void fire() {
        TaskLink tLink = (TaskLink) taskLinks.takeOut();
        if (tLink == null)
            return;
        Memory.currentTaskLink = tLink;
        Memory.currentBeliefLink = null;
        if (NARS.isStandAlone())
            Record.append(" * Selected TaskLink: " + tLink + "\n");
        Task task = tLink.getTargetTask();
        Record.append(" * Selected Task: " + task + "\n");
        Memory.currentTask = task;
        if ((tLink.getType() == TermLink.TRANSFORM) && !task.isStructual()) {
            RuleTables.transformTask(task, tLink);      // inference from a TaskLink and the Task --- for Product and Image
            return; // cannot be used otherwise
        }
        TermLink bLink = (TermLink) termLinks.takeOut(tLink);  // to avoid repeated syllogism
        if (bLink != null) {
            if (NARS.isStandAlone())
                Record.append(" * Selected BeliefLink: " + bLink + "\n");
            Memory.currentBeliefLink = bLink;
            RuleTables.reason(tLink, bLink);
            termLinks.putBack(bLink);
        }
        taskLinks.putBack(tLink);
    }
    
    /* ---------- utility ---------- */
    
    public Term getTerm() {     // called from Memory only
        return term;
    }
    
    public String toString() {  // called from concept bag
        if (NARS.isStandAlone())
            return (super.toString2() + " " + key);
        else
            return key;
    }
    
    public float getQuality() {         // re-calculate-and-set? consider syntactic complexity?
        return UtilityFunctions.and(taskLinks.averagePriority(), termLinks.averagePriority());
    }
    
    /* ---------- display ---------- */
    
    // to display contents and links, called from ConceptWindow only
    public void startPlay() {
        window = new ConceptWindow(this);
        showing = true;
        window.post(displayContent());
        taskLinks.startPlay("Task Links in " + term);
        termLinks.startPlay("Term Links in " + term);
    }
    
    // called from ConceptWindow only
    public void play() {
        showing = true;
        window.post(displayContent());
    }
    
    // called from ConceptWindow only
    public void stop() {
        showing = false;
    }
    
    // display direct belief, questions, and goals
    private String displayContent() {
        StringBuffer buffer = new StringBuffer();
        if (directBeliefs.size() > 0) {
            buffer.append("  Beliefs:\n");
            for (int i = 0; i < directBeliefs.size(); i++)
                buffer.append(directBeliefs.get(i) + "\n");
        }
        if (directGoals.size() > 0) {
            buffer.append("\n  Goals:\n");
            for (int i = 0; i < directGoals.size(); i++)
                buffer.append(directGoals.get(i) + "\n");
        }
        if (directQuestion != null)
            buffer.append("\n  Question:\n" + directQuestion + "\n");
        return buffer.toString();
    }
}

