/*
 * Memory.java
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
package nars.main;

import java.util.*;

import nars.entity.*;
import nars.inference.*;
import nars.io.*;
import nars.language.*;
import nars.operation.Operator;
import nars.storage.*;

/**
 * The memory of the system.
 */
public class Memory {

    /* ---------- all members are static, with limited space ---------- */
    /** Concept bag. Containing all Concepts of the system */
    private static ConceptBag concepts;
    /** Operators (built-in terms) table. Accessed by name */
    private static HashMap<String, Operator> operators;
    /* There is no global Term table, which may ask for unlimited space. */
    /** List of inference newTasks, to be processed in the next working cycle */
    private static ArrayList<Task> newTasks;
    /** New tasks with novel composed terms, for delayed and selective processing */
    private static TaskBuffer novelTasks;
    /** List of recent events, for temporal learning */
    private static TaskBuffer recentEvents;
    /* ---------- global variables used to record emotional values ---------- */
    /** average desire-value */
    private static float happy;
    /** average priority */
    private static float busy;
    /* ---------- global variables used to reduce method arguments ---------- */
    /** Shortcut to the selected Term */
    public static Term currentTerm;
    /** Shortcut to the selected TaskLink */
    public static TaskLink currentTaskLink;
    /** Shortcut to the selected Task */
    public static Task currentTask;
    /** Shortcut to the selected TermLink */
    public static TermLink currentBeliefLink;
    /** Shortcut to the selected belief */
    public static Judgment currentBelief;
    /** Shortcut to the derived Stamp */
    public static Stamp newStamp;
    /* ---------- global variables used to record emotional values ---------- */

    /* ---------- initialization ---------- */
    /**
     * Initialize a new memory by creating all members.
     * <p>
     * Called in Center.reset only
     */
    public static void init() {
        concepts = new ConceptBag();            // initially empty, with capacity limit
        operators = Operator.setOperators();    // with operators created, then remain constant
        newTasks = new ArrayList<Task>();       // initially empty, without capacity limit
        novelTasks = new TaskBuffer();        // initially empty, with capacity limit
        recentEvents = new TaskBuffer();      // with a different capacity?
        happy = 0.5f;
        busy = 0.5f;
    }

    /* ---------- access utilities ---------- */
    /**
     * Get a Term for a given name of a Concept or Operator
     * <p>
     * called in StringParser and the make methods of compound terms.
     * @param name the name of a concept or operator
     * @return a Term or null (if no Concept/Operator has this name)
     */
    public static Term nameToListedTerm(String name) {
        Concept concept = concepts.get(name);
        if (concept != null) {
            return concept.getTerm();
        }
        return operators.get(name);
    }

    /**
     * Check if a string is an operator name
     * <p>
     * called in StringParser only.
     * @param name the name of a possible operator
     * @return the corresponding operator or null
     */
    public static Operator nameToOperator(String name) {
        return operators.get(name);
    }

    /**
     * Get an existing Concept for a given name
     * <p>
     * called from Term and ConceptWindow.
     * @param name the name of a concept
     * @return a Concept or null
     */
    public static Concept nameToConcept(String name) {
        return concepts.get(name);
    }

    /**
     * Get an existing Concept for a given Term.
     * @param term The Term naming a concept
     * @return a Concept or null
     */
    public static Concept termToConcept(Term term) {
        return nameToConcept(term.getName());
    }

    /**
     * Get the Concept associated to a Term, or create it.
     * @param term indicating the concept
     * @return an existing Concept, or a new one
     */
    public static Concept getConcept(Term term) {
        String n = term.getName();
        Concept concept = concepts.get(n);
        if (concept == null) {
            concept = new Concept(term); // the only place to make a new Concept
            boolean created = concepts.putIn(concept);
            if (!created) {
                return null;
            }
        }
        return concept;
    }

    /**
     * Adjust the activation level of a Concept
     * <p>
     * called in Concept.insertTaskLink only
     * @param c the concept to be adusted
     * @param b the new BudgetValue
     */
    public static void activateConcept(Concept c, BudgetValue b) {
        concepts.pickOut(c.getKey());
        BudgetFunctions.activate(c, b);
        concepts.putBack(c);
    }

    /**
     * Check if there is already any derived Task in the current cycle
     * <p>
     * called in Concept.fire only
     * @return Whether in the current cycle there is already derived Task
     */
    public static boolean noResult() {
        return (newTasks.size() == 0);
    }

    public static Term mostActive() {
        return concepts.mostActive();
    }

    /* ---------- new task entries ---------- */
    /* There are several types of new tasks, all added into the 
    newTasks list, to be processed in the next cycle.
    Some of them are reported and/or logged. */
    /**
     * Input task processing. Invoked by the outside or inside enviornment.
     * Outside: StringParser (input); Inside: Operator (feedback).
     * Input tasks with low priority are ignored, and the others are put into task buffer.
     * @param task The input task
     */
    public static void inputTask(Task task) {
        if (task.aboveThreshold()) {
            Record.append("!!! Perceived: " + task + "\n");
            report(task.getSentence(), true);    // report input
            newTasks.add(task);       // wait to be processed in the next cycle
        } else {
            Record.append("!!! Neglected: " + task + "\n");
        }
    }

    /**
     * Reporting executed task, and rememberAction the event
     * <p>
     * called from Operator.call only
     * @param task the executed task
     */
    public static void executedTask(Task task) {
        Record.append("!!! Executed: " + task.getSentence() + "\n");
//        Goal g = (Goal) task.getSentence();
//        Judgment j = new Judgment(g.cloneContent());   // perceived execution
//        Task newTask = new Task(j, task.getBudget());
//        report(newTask.getSentence(), false);
//        newTasks.add(newTask);
    }

    /**
     * To rememberAction an internal action as an operation
     * <p>
     * called from Concept
     * @param task The task processed
     */
    public static void rememberAction(Task task) {
        Term content = task.getContent();
        if (content instanceof Inheritance) {
            if (((Inheritance) content).parseOperation(null) != null)
                return;     // to present infinite recursions
        }
        Sentence sentence = task.getSentence();
        TruthValue truth = new TruthValue(1.0f, Parameters.DEFAULT_JUDGMENT_CONFIDENCE);
        Stamp stamp = (Stamp) task.getSentence().getStamp().clone();
        stamp.setEventTime(Center.getTime());
        Judgment j = new Judgment(sentence.toTerm(), truth, stamp);
        Task newTask = new Task(j, task.getBudget());
        Record.append("Remembered: " + j.toString2());
        newTasks.add(newTask);
    }

    /**
     * Activated task called in MatchingRules.trySolution and Concept.processGoal
     * @param budget The budget value of the new Task
     * @param sentence The content of the new Task
     * @param isInput Whether the question is input
     */
    public static void activatedTask(BudgetValue budget, Sentence sentence, boolean isInput) {
        Task task = new Task(sentence, budget);
        Record.append("!!! Activated: " + task.toString() + "\n");
        if (sentence instanceof Question) {
            float s = task.getBudget().summary();
            @SuppressWarnings("static-access")
            float minSilent = Center.mainWindow.silentW.value() / 100.0f;
            if (s > minSilent) {  // only report significient derived Tasks
                report(task.getSentence(), false);
            }
        }
        newTasks.add(task);
    }

    /**
     * Derived task comes from the inference rules.
     * @param task the derived task
     */
    private static void derivedTask(Task task) {
        if (task.aboveThreshold()) {
            Record.append("!!! Derived: " + task + "\n");
            float budget = task.getBudget().summary();
            @SuppressWarnings("static-access")
            float minSilent = Center.mainWindow.silentW.value() / 100.0f;
            if (budget > minSilent) {  // only report significient derived Tasks
                report(task.getSentence(), false);
            }
            newTasks.add(task);
            rememberInference(task);
        } else {
            Record.append("!!! Ignored: " + task + "\n");
        }
    }

    private static void rememberInference(Task derived) {
        Sentence sentence = derived.getSentence();
        TruthValue t1 = sentence.getTruth();
        if ((t1 != null) && t1.toWord().equals("UNSURE")) {
            return;
        }
        Term pred = sentence.getContent();
        if (!((pred instanceof Inheritance) && ((Inheritance) pred).parseOperation(null) != null)) {
             pred = sentence.toTerm();
        }
        Term subj = currentTask.getSentence().getContent();
        if (!((subj instanceof Inheritance) && ((Inheritance) subj).parseOperation(null) != null)) {
             subj = currentTask.getSentence().toTerm();
        }
        if (currentBelief != null) {
            Term b = currentBelief.toTerm();
            Term c = Conjunction.make(subj, b, -1);
            subj = c;
        } else {
            return;     // temporary solution
        }
        Term imp = Implication.make(subj, pred, false, 0);
        if (imp != null) {
            TruthValue truth = new TruthValue(1.0f, Parameters.DEFAULT_JUDGMENT_CONFIDENCE);
            Judgment j = new Judgment(imp, truth, sentence.getStamp());
            Task newTask = new Task(j, derived.getBudget());
            Record.append("Remembered: " + j.toString2());
            newTasks.add(newTask);
        }
    }

    /* --------------- new task building --------------- */
    /**
     * Shared final operations by all double-premise rules, called from the rules except StructuralRules
     * @param budget The budget value of the new task
     * @param content The content of the new task
     * @param truth The truth value of the new task
     * @param premise1 The first premise to record in the new Judgment
     * @param premise2 The second premise to record in the new Judgment
     */
    public static void doublePremiseTask(BudgetValue budget, Term content, TruthValue truth,
            Sentence premise1, Sentence premise2) {
        if (content != null) {
            Sentence newSentence = Sentence.make(currentTask.getSentence(), content, truth, (Stamp) newStamp.clone(), premise1, premise2);
            Task newTask = new Task(newSentence, budget);
            derivedTask(newTask);
        }
    }

    public static void predictionWithConfirmation(BudgetValue budget, Term content, TruthValue truth,
            Sentence premise1, Sentence premise2) {
        if (content != null) {
            Sentence newSentence = Sentence.make(currentTask.getSentence(), content, truth, newStamp, premise1, premise2);
            Task newTask = new Task(newSentence, budget);
            derivedTask(newTask);
            Question ques = new Question((Judgment) newSentence);
            newTask = new Task(ques, budget);
            derivedTask(newTask);
        }
    }

    public static void lackConfirmation(Term content, long t) {
        TruthValue tv = new TruthValue(0f, 2*Parameters.DEFAULT_CONFIRMATION_EXPECTATION - 1);
        BudgetValue bv = new BudgetValue(Parameters.DEFAULT_JUDGMENT_PRIORITY, Parameters.DEFAULT_JUDGMENT_DURABILITY, 1);
        Stamp st = new Stamp();
        st.setEventTime(t);
        Judgment j = new Judgment(content, '.', tv, st, null, null);
        Task newTask = new Task(j, bv);
        derivedTask(newTask);
    }

    /**
     * The final operations of the revision rules, called from MatchingRules
     * @param budget The budget value of the new task
     * @param content The content of the new task
     * @param truth The truth value of the new task
     * @param premise1 The first premise to record in the new Judgment. May be null.
     * @param premise2 The second premise to record in the new Judgment. May be null.
     */
    public static void revisionTask(BudgetValue budget, Term content, TruthValue truth,
            Sentence premise1, Sentence premise2) {
        if (content != null) {
            Sentence newSentence = Sentence.make(currentTask.getSentence(), content, truth, newStamp, premise1, premise2);
            Task newTask = new Task(newSentence, budget);
            if (currentTask.isStructural()) {
                newTask.setStructural();
            }
            derivedTask(newTask);
        }
    }

    /**
     * Shared final operations by all single-premise rules, called in StructuralRules
     * @param budget The budget value of the new task
     * @param content The content of the new task
     * @param truth The truth value of the new task
     * @param premise The premise to record in the new Judgment
     */
    public static void singlePremiseTask(BudgetValue budget, Term content, TruthValue truth, Sentence premise) {
        Sentence sentence = currentTask.getSentence();
        Sentence newSentence = Sentence.make(sentence, content, truth, (Stamp) sentence.getStamp().clone(), premise, null);
        Task newTask = new Task(newSentence, budget);
        newTask.setStructural();
        derivedTask(newTask);
    }

    /**
     * Convert jusgment into different relation
     * <p>
     * called in MatchingRules
     * @param budget The budget value of the new task
     * @param truth The truth value of the new task
     */
    public static void convertedJudgment(TruthValue truth, BudgetValue budget) {
        Statement content = (Statement) Memory.currentTask.getContent();
        Statement beliefContent = (Statement) Memory.currentBelief.getContent();
        Term subj = content.getSubject();
        Term pred = content.getPredicate();
        Term subjB = beliefContent.getSubject();
        Term predB = beliefContent.getPredicate();
        Term otherTerm;
        if (subj.getName().indexOf(Symbols.QUERY_VARIABLE_TAG) >= 0) {
//        if ((subj instanceof Variable) && (((Variable) subj).getType() == Variable.VarType.QUERY)) {
            otherTerm = (pred.equals(subjB)) ? predB : subjB;
            content = Statement.make(content, otherTerm, pred);
        }
        if (pred.getName().indexOf(Symbols.QUERY_VARIABLE_TAG) >= 0) {
//        if ((pred instanceof Variable) && (((Variable) pred).getType() == Variable.VarType.QUERY)) {
            otherTerm = (subj.equals(subjB)) ? predB : subjB;
            content = Statement.make(content, subj, otherTerm);
        }
        Stamp stamp = (Stamp) Memory.currentBelief.getStamp().clone();
        Sentence newJudgment = Sentence.make(content, Symbols.JUDGMENT_MARK, truth, stamp, Memory.currentBelief, null);
        Task newTask = new Task(newJudgment, budget);
        newTask.setStructural();
        derivedTask(newTask);
    }

    /* ---------- system working cycle ---------- */
    /**
     * An atomic working cycle of the system: process new Tasks, then fire a concept
     * <p>
     * Called from Center.tick only
     * <p>
     * In the future, it is possible to adjust the occuring ratio of the two actions
     */
    public static void cycle() {
        processNewTask();
        if (noResult()) {
            processNovelTask();
        }
        if (noResult()) {
            processConcept();
        }
        novelTasks.refresh();
    }

    /**
     * Process the newTasks accumulated in the previous cycle, accept input ones
     * and those that corresponding to existing concepts, plus one from the buffer.
     */
    private static void processNewTask() {
        Task task;
        int counter = newTasks.size();  // don't include new tasks produced in the current cycle
        while (counter-- > 0) {
            task = newTasks.remove(0);
            adjustBusy(task.summary(), task.getPriority());
            if (task.getSentence().isInput() || (termToConcept(task.getContent()) != null)) { // new input or existing concept
                immediateProcess(task);
            } else {
                novelTasks.putIn(task);    // delayed processing
            }
        }
    }

    private static void processNovelTask() {
        Task task = novelTasks.takeOut();       // select a task from novelTasks
        if (task != null) {
            immediateProcess(task);
        }
    }

    /**
     * Select a concept to fire.
     */
    private static void processConcept() {
        resetCurrent();
        Concept currentConcept = concepts.takeOut();
        if (currentConcept != null) {
            currentTerm = currentConcept.getTerm();
            Record.append(" * Selected Concept: " + currentTerm + "\n");
            concepts.putBack(currentConcept);   // current Concept remains in the bag all the time
            currentConcept.fire();              // a working cycle
        }
    }

    private static void resetCurrent() {
        currentTerm = null;
        currentTaskLink = null;
        currentTask = null;
        currentBeliefLink = null;
        currentBelief = null;
        newStamp = null;
    }

    /* ---------- task processing ---------- */
    /**
     * Immediate processing of a new task, in constant time
     * Local processing, in one concept only
     * @param task the task to be accepted
     */
    private static void immediateProcess(Task task) {
        resetCurrent();
        currentTask = task; // one of the two places where this variable is set
        Record.append("!!! Insert: " + task + "\n");
        Term content = task.getContent();
        Concept c = getConcept(content);
        if (c != null) {
            c.directProcess(task);
        }
    }

    /**
     * Link to a new task from all relevant concepts for continued processing in
     * the near future for unspecified time.
     * <p>
     * The only method that calls the TaskLink constructor.
     * @param task The task to be linked
     * @param content The content of the task
     */
    public static void linkToTask(Task task, Term content, Concept contentConcept) {
        BudgetValue budget = task.getBudget();
        TaskLink taskLink = new TaskLink(task, null, budget);   // link type: SELF
        contentConcept.insertTaskLink(taskLink);
        if (content instanceof CompoundTerm) {
            ArrayList<TermLink> termLinks = (contentConcept != null)
                    ? contentConcept.getTermLinkTemplates()
                    : ((CompoundTerm) content).prepareComponentLinks();  // use saved if exist
            if (termLinks.size() > 0) {
                BudgetValue subBudget = BudgetFunctions.distributeAmongLinks(budget, termLinks.size());
                if (subBudget.aboveThreshold()) {
                    Term componentTerm;
                    Concept componentConcept;
                    for (TermLink termLink : termLinks) {
                        if (!(task.isStructural() && (termLink.getType() == TermLink.TRANSFORM))) { // avoid circular transform
                            taskLink = new TaskLink(task, termLink, subBudget);
                            componentTerm = termLink.getTarget();
                            componentConcept = getConcept(componentTerm);
                            if (componentConcept != null) {
                                componentConcept.insertTaskLink(taskLink);
                            }
                        }
                    }
                    contentConcept.buildTermLinks(budget);  // recursively insert TermLink
                }
            }
        }
    }

    /**
     * Simple temporal regularity discovery [To be refined]
     * <p>
     * called in Memory.immediateProcess
     * @param event1 A new event
     */
    public static void eventProcessing(Task event1) {
        Task event2 = recentEvents.takeOut();
        if (event2 != null) {
            if (event1.getSentence().noOverlapping(event2.getSentence())) {
                SyllogisticRules.temporalIndCom(event1, event2);
            }
            recentEvents.putBack(event2);
        }
        Term content = event1.getContent();
        if (!(content instanceof Implication) && !(content instanceof Equivalence)) {
            recentEvents.putIn(event1);
        }
    }

    /* ---------- status evaluation ---------- */

    public static float happyValue() {
        return happy;
    }

    public static float busyValue() {
        return busy;
    }

    public static void adjustHappy(float newValue, float weight) {
        float oldV = happy;
        happy += newValue * weight;
        happy /= 1.0f + weight;
        if (Math.abs(oldV - happy) > 0.1) {
            Record.append("HAPPY: " + (int) (oldV*10.0) + " to " + (int) (happy*10.0) + "\n");
        }
    }
    public static void adjustBusy(float newValue, float weight) {
        float oldV = busy;
        busy += newValue * weight;
        busy /= (1.0f + weight);
        if (Math.abs(oldV - busy) > 0.1) {
            Record.append("BUSY: " + (int) (oldV*10.0) + " to " + (int) (busy*10.0) + "\n");
        }
    }


    /* ---------- display ---------- */
    /**
     * Display active concepts, called from MainWindow.
     * @param s the window title
     */
    public static void conceptsStartPlay(String s) {
        concepts.startPlay(s);
    }

    /**
     * Display newd tasks, called from MainWindow.
     * @param s the window title
     */
    public static void newTasksStartPlay(String s) {
        novelTasks.startPlay(s);
        recentEvents.startPlay("Recent Events");
    }

    /**
     * Prepare buffered tasks for display, called from TaskBuffer.
     * @return the tasks as a String
     */
    public static String newTasksToString() {
        String s = " New Tasks: \n";
        for (int i = 0; i < newTasks.size(); i++) {
            s += newTasks.get(i) + "\n";
        }
        s += "\n Task Buffer: \n";
        return s;
    }

    /**
     * Display input/output sentence in the MainWindow.
     * @param sentence the sentence to be displayed
     * @param input whether the task is input
     */
    public static void report(Sentence sentence, boolean input) {
        String s;
        if (input) {
            s = "  IN: ";
        } else {
            s = " OUT: ";
        }
        s += sentence.toString2() + "\n";
        Center.mainWindow.post(s);
    }
}
