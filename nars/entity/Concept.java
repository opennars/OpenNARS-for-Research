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
    /** Task links for indirect processing */
    private TaskLinkBag taskLinks;
    /** Term links between the term and its components and compounds */
    private TermLinkBag termLinks;
    /** Link templates of TermLink, only in concepts with CompoundTerm */
    private ArrayList<TermLink> termLinkTemplates;
    /** Question directly asked about the term */
    private ArrayList<Question> questions;
    /** Goals directly requested on the term */
    private ArrayList<Goal> goals;
    /** Judgments directly made about the term, with non-future tense */
    private ArrayList<Judgment> beliefs;
    /** Most recent and confidence judgment */
//    private Judgment perfectBelief = null;  // stored for demo and debugging purpose only
    /** Whether truth value of judgments can be revised */
    private boolean revisible = true;
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
        key = tm.getName();
        term = tm;
        questions = new ArrayList<Question>();
        goals = new ArrayList<Goal>();
        beliefs = new ArrayList<Judgment>();
        taskLinks = new TaskLinkBag();
        termLinks = new TermLinkBag();
        if (tm instanceof CompoundTerm) {
            termLinkTemplates = ((CompoundTerm) tm).prepareComponentLinks();
            checkRevisibility();
        }
    }

    /**
     * Judgments with dependent variable cannot be revised
     */
    private void checkRevisibility() {
        revisible = ((key.indexOf("()") < 0) && (key.indexOf("(#")) < 0);
    }

    /* ---------- direct processing of tasks ---------- */
    /**
     * Directly process a new task. Called exactly once on each task.
     * Using local information and finishing in a constant time.
     * Provide feedback in the budget value of the task.
     * <p>
     * called in Memory.immediateProcess only
     * @param task The task to be processed
     * @return Whether to continue the processing of the task
     */
    public boolean directProcess(Task task) {
        Sentence sentence = task.getSentence();
        boolean toContinue;
        if (sentence instanceof Question) {
            toContinue = processQuestion((Question) sentence, task);
        } else if (sentence instanceof Goal) {
            toContinue = processGoal((Goal) sentence, task);
        } else {
            toContinue = processJudgment((Judgment) sentence, task);
        }
        if (showing) {
            window.post(displayContent());
        }
        return toContinue;
    }

    /**
     * To answer a question by existing beliefs
     * @param ques The question to be answered
     * @param task The task to be processed
     * @return Whether to continue the processing of the task
     */
    private boolean processQuestion(Question ques, Task task) {
        boolean toContinue = true;
        for (Question q : questions) {
            if (q.getContent().equals(ques.getContent()) && (q.getEventTime() == ques.getEventTime())) {
                toContinue = false;
                break;
            }
        }
        if (toContinue) {
            questions.add(ques);
        }
        for (Judgment judg : beliefs) {
            MatchingRules.trySolution(ques, judg, task);
        }
        return toContinue;
    }

    /**
     * Direct processing a new goal
     * @param goal The goal to be processed
     * @param task The task to be processed
     * @return Whether to continue the processing of the task
     */
    private boolean processGoal(Goal goal, Task task) {
        boolean revised = false;
        if (revisible) {
            revised = reviseTable(goal, goals);
            if (revised) {
                return false;
            }
        }
        for (Judgment judg : beliefs) {
            MatchingRules.trySolution(goal, judg, task);
        }
        if (task.aboveThreshold()) {
            Term content = goal.getContent();
            if (content instanceof Inheritance) {
                Term pred = ((Inheritance) content).getPredicate();
                if (pred instanceof Operator) {
                    float netDesire = goal.getTruth().getExpectation();
                    if (netDesire > Parameters.DECISION_THRESHOLD) {
                        ((Operator) pred).call(task);
                        return false;
                    }
                }
            }
            addToTable(goal, goals, Parameters.MAXMUM_GOALS_LENGTH);
            Question ques = new Question(goal);
            Memory.activatedTask(task.getBudget(), ques, false);
            return true;
        } else {
            return false;
        }
    }

    /**
     * To accept a new judgment as belief, and check for revisions and solutions
     * @param judg The judgment to be accepted
     * @param task The task to be processed
     * @return Whether to continue the processing of the task
     */
    private boolean processJudgment(Judgment judg, Task task) {
        if (revisible) {
            reviseTable(judg, beliefs);
        }
        if (task.aboveThreshold()) {
            for (Question ques : questions) {
                MatchingRules.trySolution(ques, judg, task);
            }
            for (Goal goal : goals) {
                MatchingRules.trySolution(goal, judg, task);
            }
            addToTable(judg, beliefs, Parameters.MAXMUM_BELIEF_LENGTH);
//            generateNegation(task);   // may be recovered in the future
            if (judg.isEvent()) {
                Memory.eventProcessing(task);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Directly express a negative judgment as a negation
     * @param task The task to be processed
     */
//    private void generateNegation(Task task) {
//        if (task.getSentence() instanceof Judgment) {
//            Sentence s = task.getSentence();
//            if (s.getTruth().getFrequency() < 0.5) {
//                Term t = Negation.make(s.getContent());
//                if (t != null) {
//                    StructuralRules.transformNegation(t);
//                }
//            }
//        }
//    }
    /**
     * Revise existing beliefs or goals
     * @param judg The judgment (belief or goal) to be processed
     * @param table The table to be revised
     * @return Whether the new belief triggered a revision
     */
    private boolean reviseTable(Judgment judg, ArrayList table) {
        boolean revised = false;
        for (Object belief : table) {
            if (judg.noOverlapping((Judgment) belief)) {
                MatchingRules.revision(judg, (Judgment) belief, false);
                revised = true;
            }
        }
        return revised;
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
        for (i = 0; i < table.size(); i++) {
            judgment2 = (Judgment) table.get(i);
            rank2 = BudgetFunctions.rankBelief(judgment2);
            if (rank1 >= rank2) {
                if (newJudgment.equivalentTo(judgment2)) {
                    return;
                }
                table.add(i, newJudgment);
                break;
            }
        }
        if (table.size() >= capacity) {
            while (table.size() > capacity) {
                table.remove(table.size() - 1);
            }
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
        Memory.activateConcept(this, budget);
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
                        if (concept != null) {
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
     * Recalculate the quality of the concept [to be refined to show extension/intension balance]
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
        for (int i = 0; i < beliefs.size(); i++) {
            belief = beliefs.get(i);
            Record.append(" * Selected Belief: " + belief + "\n");
            if (taskSentence.noOverlapping(belief)) {
                long taskTime = taskSentence.getEventTime();
                long beliefTime = belief.getEventTime();
                if ((taskTime == beliefTime) || (beliefTime == Stamp.ALWAYS) || (taskSentence instanceof Goal)) {
                    return belief;
                }
                Judgment belief2 = (Judgment) belief.clone();   // will this mess up priority adjustment?
                TruthValue v = TruthFunctions.temporalCasting(belief.getTruth(), beliefTime, taskTime, taskSentence.getCreationTime());
                belief2.setTruth(v);
                return belief2;
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
        Memory.currentTask = task;  // one of the two places where this variable is set
        if (task.getSentence() instanceof Question) {
            ((Question) task.getSentence()).checkFeedback();
        }
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
     * Start displaying contents and links, called from ConceptWindow or Memory.processTask only
     * @param showLinks Whether to display the task links
     */
    public void startPlay(boolean showLinks) {
        if (window != null && window.isVisible()) {
            window.detachFromConcept();
        }
        window = new ConceptWindow(this);
        showing = true;
        window.post(displayContent());
        if (showLinks) {
            taskLinks.startPlay("Task Links in " + term);
            termLinks.startPlay("Term Links in " + term);
        }
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
    public String displayContent() {
        StringBuffer buffer = new StringBuffer();
//        if (perfectBelief != null && perfectBelief != presentBelief) {
//            buffer.append("  Perfect Belief:\n");
//            if (perfectBelief instanceof Judgment && window != null && window.getShowDerivation()) {
//                buffer.append(((Judgment) perfectBelief).toStringWithPremises(""));
//            } else {
//                buffer.append(perfectBelief + "\n");
//            }
//        }
        if (beliefs.size() > 0) {
            buffer.append("\n  Beliefs:\n");
            for (Sentence s : beliefs) {
                buffer.append(s + "\n");
            }
        }
        if (goals.size() > 0) {
            buffer.append("\n  Goals:\n");
            for (Sentence s : goals) {
                buffer.append(s + "\n");
            }
        }
        if (questions.size() > 0) {
            buffer.append("\n  Question:\n");
            for (Sentence s : questions) {
                buffer.append(s + "\n");
            }
        }
        return buffer.toString();
    }
}

