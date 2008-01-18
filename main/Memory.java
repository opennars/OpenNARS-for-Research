
package nars.main;

import java.util.*;
import nars.entity.*;
import nars.language.*;
import nars.operation.Operator;
import nars.storage.*;
import nars.io.*;
import nars.inference.BudgetFunctions;

/**
 * The memory of the system.
 */
public class Memory {
    
    /* ---------- all members are static, with limited space ---------- */
    
    /**
     * Concept bag. Containing all Concepts of the system.
     */
    private static ConceptBag concepts;
    
    /**
     * Operators (built-in terms) table. Accessed by name.
     */
    private static HashMap<String, Operator> operators;
    
    // There is no global Term table, which may ask for unlimited space.
    
    /**
     * List of inference newTasks, to be processed in the next working cycle.
     */
    private static ArrayList<Task> newTasks;
    
    /**
     * New tasks to be processed in the near future.
     */
    private static TaskBag taskBuffer;
    
    /* ---------- global variables used to reduce method arguments ---------- */
    
    /**
     * Shortcut to the selected Term.
     */
    public static Term currentTerm;
    
    /**
     * Shortcut to the selected TaskLink.
     */
    public static TaskLink currentTaskLink;
    
    /**
     * Shortcut to the selected Task.
     */
    public static Task currentTask;
    
    /**
     * Shortcut to the selected TermLink.
     */
    public static TermLink currentBeliefLink;
    
    /**
     * Shortcut to the selected belief (Sentence).
     */
    public static Judgment currentBelief;
    
    public static Base currentBase;
    
    /* ---------- initialization ---------- */
    
    /**
     * Initialize a new memory by creating all members.
     * <p>
     * Called in Center.reset only
     */
    public static void init() {
        concepts = new ConceptBag();         // initially empty
        newTasks = new ArrayList<Task>();     // initially empty
        taskBuffer = new TaskBag();       // initially empty
        operators = Operator.setOperators(); // with operators created
    }
    
    /* ---------- access utilities ---------- */
    
    /**
     * Get a Term for a given name of a Concept or Operator, called in StringParser and the make methods of compound terms.
     * @param name the name of a concept or operator
     * @return a Term or null (if no Concept/Operator has this name)
     */
    public static Term nameToListedTerm(String name) {
        Concept concept = concepts.get(name);
        if (concept != null)
            return concept.getTerm();           // Concept associated Term
        return operators.get(name);
    }
    
    /**
     * Check if a string is an operator name, called in StringParser only.
     * @param name the name of a possible operator
     * @return the corresponding operator or null
     */
    public static Operator nameToOperator(String name) {
        return operators.get(name);
    }
    
    /**
     * Get an existing Concept for a given name, called from Term and ConceptWindow.
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
     * Get the Concept associated to a Term, or creat it.
     * @param term indicating the concept
     * @return an existing Concept, or a new one
     */
    public static Concept getConcept(Term term) {
        String n = term.getName();
        Concept concept = concepts.get(n);
        if (concept == null)
            concept = new Concept(term);        // the only place to make a new Concept
        return concept;
    }
    
    /**
     * Adjust the activation level of a Concept or Operator, called in Concept only.
     * @param c the concept to be adusted
     * @param b the new BudgetValue
     */
    public static void activateConcept(Concept c, BudgetValue b) {
        BudgetValue budget;
        if (concepts.contains(c)) {     // listed Concept
            concepts.pickOut(c.getKey());
            BudgetFunctions.activate(c, b);
            concepts.putBack(c);
        } else {                        // new Concept
            BudgetFunctions.activate(c, b);
            concepts.putIn(c);
        }
    }
    
    /* ---------- new task entries ---------- */
    
    // There are three types of new tasks: (1) input, (2) derived, (3) activated
    // They are all added into the newTasks list, to be processed in the next cycle.
    // Some of them are reported and/or logged.
    
    /**
     * Input task comes from the InputWindow.
     * @param str the input line
     */
    public static void inputTask(String str) {
        Task task = StringParser.parseTask(new StringBuffer(str));       // the only place to call StringParser
        if (task != null) {
            if (NARS.isStandAlone())
                Record.append("!!! Input: " + task + "\n");   // append to inference record
            if (task.aboveThreshold()) {                       // set a threshold?
                report(task.getSentence(), true);             // report input
                newTasks.add(task);       // wait to be processed in the next cycle
                taskBuffer.refresh();           // refresh display
            } else
                Record.append("!!! Ignored: " + task + "\n");   // append to inference record
        }
    }
    
    /**
     * Derived task comes from the inference rules.
     * @param task the derived task
     */
    private static void derivedTask(Task task) {
        Record.append("!!! Derived: " + task.toString() + "\n");
        if (task.aboveThreshold()) {
            float budget = task.getBudget().singleValue();
            float minSilent = Center.mainWindow.silentW.value() / 100.0f;
            if (budget > minSilent)
                report(task.getSentence(), false);        // report significient derived Tasks
            newTasks.add(task);
            taskBuffer.refresh();
        } else
            Record.append("!!! Ignored: " + task + "\n");   // append to inference record
    }
    
    /**
     * Reporting executed task, called from Concept.directOperation.
     * @param task the executed task
     */
    public static void executedTask(Task task) {   // called by the inference rules
        Record.append("!!! Executed: " + task.toString() + "\n");
        float budget = task.getBudget().singleValue();
        float minSilent = Center.mainWindow.silentW.value() / 100.0f;
        if (budget > minSilent)
            report(task.getSentence(), false);
    }
    
    /**
     * Activated task comes from MatchingRules.
     * @param budget The budget value of the new Task
     * @param sentence The content of the new Task
     * @param isInput Whether the question is input
     */
    public static void activatedTask(BudgetValue budget, Sentence sentence, boolean isInput) {
        Task task = new Task(sentence, budget);
        if (NARS.isStandAlone())
            Record.append("!!! Activated: " + task.toString() + "\n");
        newTasks.add(task);
        taskBuffer.refresh();
    }
    
    /* --------------- new task building --------------- */
    
    /**
     * Shared final operations by all double-premise rules, called from the rules except StructuralRules
     * @param budget The budget value of the new task
     * @param content The content of the new task
     * @param truth The truth value of the new task
     */
    public static void doublePremiseTask(BudgetValue budget, Term content, TruthValue truth) {
        Sentence newSentence = Sentence.make(currentTask.getSentence(), content, truth, Memory.currentBase);
        Task newTask = new Task(newSentence, budget);
        derivedTask(newTask);
    }
    
    /**
     * Shared final operations by all single-premise rules, called in StructuralRules
     * @param budget The budget value of the new task
     * @param content The content of the new task
     * @param truth The truth value of the new task
     */
    public static void singlePremiseTask(BudgetValue budget, Term content, TruthValue truth) {
        Sentence sentence = currentTask.getSentence();
        Sentence newSentence = Sentence.make(sentence, content, truth, sentence.getBase());
        Task newTask = new Task(newSentence, budget);
        newTask.setStructual();
        derivedTask(newTask);
    }

    /**
     * Shared final operations by all single-premise rules, called in MatchingRules
     * @param budget The budget value of the new task
     * @param truth The truth value of the new task
     */
    public static void singlePremiseTask(TruthValue truth, BudgetValue budget) {
        Term content = Memory.currentTask.getContent();
        Base base = Memory.currentBelief.getBase();
        Sentence newJudgment = Sentence.make(content, Symbols.JUDGMENT_MARK, truth, base);
        Task newTask = new Task(newJudgment, budget);
        newTask.setStructual();
        derivedTask(newTask);
    }
    
    /* ---------- system working cycle ---------- */
    
    /**
     * An atomic working cycle of the system. Called from Center only.
     */
    public static void cycle() {
        processTask();      // tune relative frequency?
        processConcept();   // use this order to check the new result
    }
    
    /**
     * Process the newTasks accumulated in the previous cycle, accept input ones
     * and those that corresponding to existing concepts, plus one from the buffer.
     */
    private static void processTask() {
        Task task;
        int counter = newTasks.size();              // don't include new tasks produced in the current cycle
        while (counter-- > 0) {                     // process the newTasks of the previous cycle
            task = (Task) newTasks.remove(0);
            if (task.getSentence().isInput() || (termToConcept(task.getContent()) != null))  // new input or existing concept
                immediateProcess(task);                   // immediate process
            else
                taskBuffer.putIn(task);             // postponed process
        }
        task = (Task) taskBuffer.takeOut();         // select a task from taskBuffer
        if (task != null)
            immediateProcess(task);                       // immediate process
    }
    
    /**
     * Select a concept to fire.
     */
    private static void processConcept() {
        Concept currentConcept = (Concept) concepts.takeOut();
        if (currentConcept != null) {
            currentTerm = currentConcept.getTerm();
            Record.append(" * Selected Concept: " + currentTerm + "\n");
            concepts.putBack(currentConcept);   // current Concept remains in the bag all the time
            currentConcept.fire();              // a working cycle
            taskBuffer.refresh();               // show new result
        }
    }
    
    /* ---------- task / belief insertion ---------- */
    
    /**
     * Imediate processing of a new task
     * @param task the task to be accepted
     */
    private static void immediateProcess(Task task) {
        if (NARS.isStandAlone())
            Record.append("!!! Accept: " + task.toString() + "\n");
        Term content = task.getContent();
        if (content.isConstant()) {                        // does not creat concept for Query?
            Concept c = getConcept(content);
            c.directProcess(task);
        }
        if (task.aboveThreshold())
            continuedProcess(task, content);
    }

    /**
     * Link to a new task from all relevant concepts for distributed processing.
     * @param task The task to be linked
     * @param content The content of the task
     */
    private static void continuedProcess(Task task, Term content) {
        TaskLink tLink;
        Concept c1 = null;                      // local Concept
        BudgetValue budget = task.getBudget();
        if (content.isConstant()) {
            c1 = getConcept(content);
            tLink = new TaskLink(task, null, budget);   // link type SELF
            c1.insertTaskLink(tLink);
        }
        if (content instanceof CompoundTerm) {
            Term component;                     // component term
            Concept c2;                         // component concept
            TermLink cLink1, cLink2;     // a pair of compound/component links
            ArrayList<TermLink> cLinks;  // link list
            cLinks = (c1 != null) ? c1.getTermLinks() : ((CompoundTerm) content).prepareComponentLinks();  // use saved
            short[] indices;
            BudgetValue subBudget = BudgetFunctions.distributeAmongLinks(budget, cLinks.size());
            if (!subBudget.aboveThreshold())
                return;
            for (TermLink cLink0 : cLinks) {
                component = cLink0.getTarget();
                c2 = getConcept(component);
                if (!(task.isStructual() && (cLink0.getType() == TermLink.TRANSFORM))) {
                    tLink = new TaskLink(task, cLink0, subBudget);
                    c2.insertTaskLink(tLink);               // component link to task
                }
            }
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
     * Display buffered tasks, called from MainWindow.
     * @param s the window title
     */
    public static void taskBufferStartPlay(String s) {
        taskBuffer.startPlay(s);
    }
    
    /**
     * Prepare buffered tasks for display, called from TaskBag.
     * 
     * @return the tasks as a String
     */
    public static String resultsToString() {
        String s = " New Tasks: \n";
        for (int i = 0; i < newTasks.size(); i++)
            s += newTasks.get(i).toString() + "\n";
        s += "\n Task Buffer: \n";
        return s;
    }
    
    /**
     * Display selected task.
     * @param sentence the sentence to be displayed
     * @param input whether the task is input
     */
//    public static void report(Task task, boolean input) {
    public static void report(Sentence sentence, boolean input) {
        String s = " ";
        if (input)
            s += "IN: ";
        else
            s += "OUT: ";
        s += sentence.toString2() + "\n";
        Center.mainWindow.post(s);
    }
}
