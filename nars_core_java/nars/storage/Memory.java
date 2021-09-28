/* 
 * The MIT License
 *
 * Copyright 2019 The OpenNARS authors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package nars.storage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import nars.Operator_Mental.Anticipate;
import nars.Operator_Mental.Deactive;
import nars.Operator_Mental.Escape;
import nars.Operator_Mental.Fire;
import nars.Operator_Mental.Left;
import nars.Operator_Mental.Right;
import nars.entity.BudgetValue;
import nars.entity.Concept;
import nars.entity.Item;
import nars.entity.Sentence;
import nars.entity.Stamp;
import nars.entity.Task;
import nars.entity.TaskLink;
import nars.entity.TermLink;
import nars.entity.TruthValue;
import nars.inference.BudgetFunctions;
import nars.io.IInferenceRecorder;
import nars.io.Symbols;
import nars.language.Equivalence;
import nars.language.Implication;
import nars.language.Operation;
import nars.language.Operator;
import nars.language.Tense;
import nars.language.Term;
import nars.main_nogui.Parameters;
import nars.main_nogui.NAR;
import nars.mental.Emotion;

/**
 * The memory of the system.
 */
public class Memory {

    /**
     * Backward pointer to the reasoner
     */
    private final NAR reasoner;

    /* ---------- Long-term storage for multiple cycles ---------- */
    /**
     * Concept bag. Containing all Concepts of the system
     */
    private final ConceptBag concepts;
    /**
     * New tasks with novel composed terms, for delayed and selective processing
     */
    //private final NovelTaskBag novelTasks;
    /**
     * Inference record text to be written into a log file
     */
    private IInferenceRecorder recorder;
    private final AtomicInteger beliefForgettingRate = new AtomicInteger(Parameters.TERM_LINK_FORGETTING_CYCLE);
    private final AtomicInteger taskForgettingRate = new AtomicInteger(Parameters.TASK_LINK_FORGETTING_CYCLE);
    private final AtomicInteger conceptForgettingRate = new AtomicInteger(Parameters.CONCEPT_FORGETTING_CYCLE);

    /* ---------- Short-term workspace for a single cycle ---------- */
    /**
     * List of new tasks accumulated in one cycle, to be processed in the next
     * cycle
     */
    //private final LinkedList<Task> newTasks;
    /**
     * List of Strings or Tasks to be sent to the output channels
     */
    private final ArrayList<String> exportStrings;
    /**
     * The selected Term
     */
    public Term currentTerm;
    /**
     * The selected Concept
     */
    public Concept currentConcept;
    /**
     * The selected TaskLink
     */
    public TaskLink currentTaskLink;
    /**
     * The selected Task
     */
    public Task currentTask;
    /**
     * The selected TermLink
     */
    public TermLink currentBeliefLink;
    /**
     * The selected belief
     */
    public Sentence currentBelief;
    /**
     * The new Stamp
     */
    public Stamp newStamp;
    
    private long originalTime = 0;
    /**
     * The substitution that unify the common term in the Task and the Belief
     * TODO unused
     */
    protected HashMap<Term, Term> substitute;

    public static Random randomNumber = new Random(1);
    
    private Map<String, Operator> operators;
    
    //private final long originalTime = 0;
    private Emotion emotion;
    
    /* ---------- Constructor ---------- */
    /**
     * Create a new memory <p> Called in Reasoner.reset only
     *
     * @param reasoner
     */
    public Memory(NAR reasoner) {
        this.reasoner = reasoner;
        recorder = new NullInferenceRecorder();
        concepts = new ConceptBag(this);
        exportStrings = new ArrayList<>();
        operators = new LinkedHashMap();
        initOperators();
        newStamp = null;
        emotion = new Emotion();
    }
    
    public void initOperators(){
        
        Operator anti = new Anticipate();
        Operator escape = new Escape();
        Operator left = new Left();
        Operator right = new Right();
        Operator deactive = new Deactive();
        Operator fire = new Fire();
        
        operators.put("^anticipate", anti);
        operators.put("^escape", escape);
        operators.put("^left", left);
        operators.put("^right", right);
        operators.put("^deactivate", deactive);
        operators.put("^fire", fire);
    }

    public void init() {
        concepts.init();
        exportStrings.clear();
        reasoner.initTimer();
        randomNumber = new Random(1);
        recorder.append("\n-----RESET-----\n");
    }

    /* ---------- access utilities ---------- */
    public ArrayList<String> getExportStrings() {
        return exportStrings;
    }

    public IInferenceRecorder getRecorder() {
        return recorder;
    }

    public void setRecorder(IInferenceRecorder recorder) {
        this.recorder = recorder;
    }

    public long getTime() {
        return reasoner.getTime();
    }
    
    public Emotion getEmotion(){
        
        return emotion;
        
    }

//    public MainWindow getMainWindow() {
//        return reasoner.getMainWindow();
//    }
    /**
     * Actually means that there are no new Tasks
     * @return 
     */
    public boolean noResult() {
        return reasoner.getGlobalBuffer().isEmpty();
    }

    /* ---------- conversion utilities ---------- */
    /**
     * Get an existing Concept for a given name <p> called from Term and
     * ConceptWindow.
     *
     * 能否在概念包中找到以参数名称为名字的概念，有返回真，无返回假
     * 
     * @param name the name of a concept
     * @return a Concept or null
     */
    public Concept nameToConcept(String name) {
        return concepts.get(name);
    }
    
    public Stamp getNewStamp(){        
        
        if(newStamp == null){  
            newStamp = new Stamp(getTime());
            resetOccurrenceTime();
        }
        
        return newStamp;
    }
    
    public Stamp setNewStamp(Stamp first, Stamp second, long time){
        
        newStamp = new Stamp(first, second, time);
        return newStamp;
        
    }
    
    public void resetOccurrenceTime(){     
        newStamp.setOccurrenceTime(Stamp.ETERNAL);     
    }
    
    public EventBuffer getOveralExperience(){
        
        return reasoner.getGlobalBuffer();
        
    }
    
    public EventBuffer getInternalExperience(){
        
        return reasoner.getInternalBuffer();
        
    }

    /**
     * Get a Term for a given name of a Concept or Operator <p> called in
     * StringParser and the make methods of compound terms.
     *
     * @param name the name of a concept or operator
     * @return a Term or null (if no Concept/Operator has this name)
     */
    public Term nameToListedTerm(String name) {
        Concept concept = concepts.get(name);
        if (concept != null) {
            return concept.getTerm();
        }
        return null;
    }

    /**
     * Get an existing Concept for a given Term.
     * 得到一个已存在的概念
     * @param term The Term naming a concept
     * @return a Concept or null
     */
    public Concept termToConcept(Term term) {
        return nameToConcept(term.getName());
    }

    /**
     * Get the Concept associated to a Term, or create it.
     *
     * @param term indicating the concept
     * @return an existing Concept, or a new one, or null ( TODO bad smell )
     */
    public Concept getConcept(Term term) {
        if (!term.isConstant()) {
            return null;
        }
        String n = term.getName();
        Concept concept = concepts.get(n);
        if (concept == null) {
            concept = new Concept(term, this); // the only place to make a new Concept
            boolean created = concepts.putIn(concept);
            if (!created) {
                return null;
            }
        }
        return concept;
    }

    /**
     * Get the current activation level of a concept.
     *
     * @param t The Term naming a concept
     * @return the priority value of the concept
     */
    public float getConceptActivation(Term t) {
        Concept c = termToConcept(t);
        return (c == null) ? 0f : c.getPriority();
    }

    /* ---------- adjustment functions ---------- */
    /**
     * Adjust the activation level of a Concept <p> called in
     * Concept.insertTaskLink only
     *
     * @param c the concept to be adjusted
     * @param b the new BudgetValue
     */
    public void activateConcept(Concept c, BudgetValue b) {
        concepts.pickOut(c.getKey());
        BudgetFunctions.activate(c, b);
        concepts.putBack(c);
    }

    /**
     * Activated task called in MatchingRules.trySolution and
     * Concept.processGoal
     *
     * @param budget The budget value of the new Task
     * @param sentence The content of the new Task
     * @param candidateBelief The belief to be used in future inference, for
     * forward/backward correspondence
     */
    public void activatedTask(BudgetValue budget, Sentence sentence, Sentence candidateBelief) {
        Task task = new Task(sentence, budget, currentTask, sentence, candidateBelief);
        recorder.append("!!! Activated: " + task.toString() + "\n");
        if (sentence.isQuestion()) {
            float s = task.getBudget().summary();
//            float minSilent = reasoner.getMainWindow().silentW.value() / 100.0f;
            float minSilent = reasoner.getSilenceValue().get() / 100.0f;
            if (s > minSilent) {  // only report significant derived Tasks
                //generalInfoReport("4");
                report(task.getSentence(), false, false);
            }
        }
        reasoner.getInternalBuffer().putInSequenceList(task, getTime());
    }

    /**
     * Derived task comes from the inference rules.
     *
     * @param task the derived task
     */
    public void derivedTask(Task task) {
        
        if (task.getBudget().aboveThreshold()) {
            recorder.append("!!! Derived: " + task + "\n");
            float budget = task.getBudget().summary();
            float minSilent = reasoner.getSilenceValue().get() / 100.0f;
            
            //System.out.println(task.getSentence().getContent().getName());
            if (budget > minSilent) {  // only report significant derived Tasks
                //generalInfoReport("5");
                report(task.getSentence(), false, false);
            }
            
            //generalInfoReport(task.getSentence().toString());
            
            reasoner.getInternalBuffer().putInSequenceList(task, getTime());
            
        } else {
            recorder.append("!!! Ignored: " + task + "\n");
        }
    }

    /* --------------- new task building --------------- */
    /**
     * Shared final operations by all double-premise rules, called from the
     * rules except StructuralRules
     *
     * @param newContent The content of the sentence in task
     * @param newTruth The truth value of the sentence in task
     * @param newBudget The budget value in task
     * @return 
     */
    public Task doublePremiseTask(Term newContent, TruthValue newTruth, BudgetValue newBudget) {
        Stamp deriveStamp = newStamp.clone();
        resetOccurrenceTime();
        
        Task newTask = null;
        
        if (newContent != null) {
            Sentence newSentence = new Sentence(newContent, currentTask.getSentence().getPunctuation(), newTruth, deriveStamp);
            
            //System.out.println("Sentence: " + newSentence.getContent().getName());
            newTask = new Task(newSentence, newBudget, currentTask, currentBelief);
            derivedTask(newTask);
        }
        
        return newTask;
    }

    /**
     * Shared final operations by all double-premise rules, called from the
     * rules except StructuralRules
     *
     * @param newContent The content of the sentence in task
     * @param newTruth The truth value of the sentence in task
     * @param newBudget The budget value in task
     * @param revisible Whether the sentence is revisible
     */
    public void doublePremiseTask(Term newContent, TruthValue newTruth, BudgetValue newBudget, boolean revisible) {
        
        Stamp deriveStamp = newStamp.clone();
        resetOccurrenceTime();
        
        if (newContent != null) {
            Sentence taskSentence = currentTask.getSentence();
            Sentence newSentence = new Sentence(newContent, taskSentence.getPunctuation(), newTruth, deriveStamp, revisible);
            Task newTask = new Task(newSentence, newBudget, currentTask, currentBelief);
            //newTask.setElemOfSequenceBuffer(false);
            //System.out.println("777");
            derivedTask(newTask);
        }
    }
    
    /**
     * Shared final operations by all single-premise rules, called in
     * StructuralRules
     *
     * @param newContent The content of the sentence in task
     * @param newTruth The truth value of the sentence in task
     * @param newBudget The budget value in task
     */
    public void singlePremiseTask(Term newContent, TruthValue newTruth, BudgetValue newBudget) {
        singlePremiseTask(newContent, currentTask.getSentence().getPunctuation(), newTruth, newBudget);
    }

    /**
     * Shared final operations by all single-premise rules, called in
     * StructuralRules
     *
     * @param newContent The content of the sentence in task
     * @param punctuation The punctuation of the sentence in task
     * @param newTruth The truth value of the sentence in task
     * @param newBudget The budget value in task
     */
    public void singlePremiseTask(Term newContent, char punctuation, TruthValue newTruth, BudgetValue newBudget) {
        Task parentTask = currentTask.getParentTask();
        if (parentTask != null && newContent.equals(parentTask.getContent())) { // circular structural inference
            return;
        }
        
        //System.out.println("Single: " + newContent.getName());
        
        Sentence taskSentence = currentTask.getSentence();
        if (taskSentence.isJudgment() || currentBelief == null) {
            newStamp = new Stamp(taskSentence.getStamp(), getTime());
        } else {    // to answer a question with negation in NAL-5 --- move to activated task?
            newStamp = new Stamp(currentBelief.getStamp(), getTime());
        }
        
        Stamp deriveStamp = newStamp.clone();
        resetOccurrenceTime();
        
        Sentence newSentence = new Sentence(newContent, punctuation, newTruth, deriveStamp, taskSentence.getRevisible());
        Task newTask = new Task(newSentence, newBudget, currentTask, null);
        //System.out.println("888");
        derivedTask(newTask);
    }

    /* ---------- system working workCycle ---------- */
    /**
     * An atomic working cycle of the system: process new Tasks, then fire a
     * concept <p> Called from Reasoner.cycle only
     *
     * @param clock The current time to be displayed
     */
    public void workCycle(long clock) {
        recorder.append(" --- " + clock + " ---\n");      
        processBuffer();
        processConcept();    
        
        //reasoner.getGlobalBuffer().refresh();
    }
    
    private void processBuffer(){  
        
        //reasoner.getGlobalBuffer().print();
        
        if(!reasoner.getInternalBuffer().isEmpty()){
            Task task = reasoner.getInternalBuffer().observe(true, false);
            //report(task.getSentence(), false, false);
            if(task != null)
                reasoner.getGlobalBuffer().preProcessing(task, true);
        }
        
        //reasoner.getGlobalBuffer().print();
        Task task = reasoner.getGlobalBuffer().observe(true, true);
        //generalInfoReport("Take Out: " + task);
        if(task != null){
            //System.out.println("Task take out: " + task.getName() + " " + task.getSentence().getTruth().toString());
            immediateProcess(task);
        }
    }
    
    public NAR getReasoner(){
        return reasoner;
    }
    
    /**
     * Select a concept to fire.
     */
    private void processConcept() {
        currentConcept = concepts.takeOut();  
        
        //System.out.println(" * Selected Concept: " + currentConcept.getTerm());
           
        if (currentConcept != null) {
            currentTerm = currentConcept.getTerm();
            recorder.append(" * Selected Concept: " + currentTerm + "\n");
            //System.out.println(" * Selected Concept: " + currentTerm + "\n");
            concepts.putBack(currentConcept);   // current Concept remains in the bag all the time
            //generalInfoReport("Concept selected: " + currentConcept.toStringBrief());
            currentConcept.fire();              // a working workCycle
        }
    }

    /* ---------- task processing ---------- */
    /**
     * Immediate processing of a new task, in constant time Local processing, in
     * one concept only
     *
     * @param task the task to be accepted
     */
    public void immediateProcess(Task task) {
        currentTask = task; // one of the two places where this variable is set
        recorder.append("!!! Insert: " + task + "\n");
        //System.out.println("!!! Insert: " + task + "\n");
        currentTerm = task.getContent();
        currentConcept = getConcept(currentTerm);
        if (currentConcept != null) {
            activateConcept(currentConcept, task.getBudget());
            currentConcept.directProcess(task);
        }
    }

    /* ---------- display ---------- */
    /**
     * Start display active concepts on given bagObserver, called from MainWindow.
     *
     * we don't want to expose fields concepts and novelTasks, AND we want to
     * separate GUI and inference, so this method takes as argument a 
     * {@link BagObserver} and calls {@link ConceptBag#addBagObserver(BagObserver, String)} ;
     * 
     * see design for {@link Bag} and {@link nars.gui.BagWindow}
     * in {@link Bag#addBagObserver(BagObserver, String)}
     *
     * @param bagObserver bag Observer that will receive notifications
     * @param title the window title
     */
	public void conceptsStartPlay( BagObserver<Concept> bagObserver, String title ) {
        bagObserver.setBag(concepts);
        concepts.addBagObserver(bagObserver, title);
    }

    /**
     * Display new tasks, called from MainWindow. see
     * {@link #conceptsStartPlay(BagObserver, String)}
     *
     * @param bagObserver
     * @param s the window title
     */
    public void taskBuffersStartPlay( BagObserver<Task> bagObserver, String s ) {
        //bagObserver.setBag(concepts);
        //reasoner.getInternalBuffer().addBagObserver(bagObserver, s);
    }
    
    public Concept getConceptFromTheList(Term t){
        return concepts.get(t.getName());
    }

    /**
     * Display input/output sentence in the output channels.The only place to
 add Objects into exportStrings. Currently only Strings are added, though
 in the future there can be outgoing Tasks; also if exportStrings is empty
 display the current value of timer ( exportStrings is emptied in
 {@link NAR#doTick()} - TODO fragile mechanism)
     *
     * @param sentence the sentence to be displayed
     * @param input whether the task is input
     * @param answer
     */
    public void report(Sentence sentence, boolean input, boolean answer) {
        if (NAR.DEBUG) {
            System.out.println("// report( clock " + reasoner.getTime()
                    + ", input " + input
                    + ", timer " + reasoner.getTimer()
                    + ", Sentence " + sentence
                    + ", exportStrings " + exportStrings);
            System.out.flush();
        }
        if (exportStrings.isEmpty()) {
            long timer = reasoner.updateTimer();
            if (timer > 0) {
                exportStrings.add(String.valueOf(timer));
            }
        }
        
        long interval = 0;
        if(sentence.getContent() instanceof Implication)
            interval = ((Implication)(sentence.getContent())).getInterval();
        else if (sentence.getContent() instanceof Equivalence)
            interval = ((Equivalence)(sentence.getContent())).getInterval();
                     
        boolean execution = sentence.getContent() instanceof Operation;
        
        String s;
        if (input) {
            s = "  IN: ";
        }else if(execution){
        
            s = "EXE: ";
            
        }else {
            if(answer)
                s = " ANSWER";
            else
                s = " OUT: ";
        }
        
        //System.out.println(sentence.toStringBrief());
        //System.out.println("occurrence Time: " + sentence.getStamp().getOccurrenceTime());
        
        if(!sentence.getStamp().isEternal()){
            String tense = sentence.getStamp().getTense(getTime(), Parameters.DURATION);
            if(tense == null){
                s += sentence.toStringBrief(tense) + " Eternal";
            }
            else{ 
                
                if(execution && !input)
                    s += ((Operation)sentence.getContent()).getPredicate().getName();
                else
                    s += sentence.toStringBrief(tense);
                
                /*switch (tense) {
                    case Symbols.TENSE_FUTURE:
                        s += " Will happen at : " + sentence.getOccurrenceTime();
                        break;
                    case Symbols.TENSE_PAST:
                        s+= " Happened at: " + sentence.getOccurrenceTime();
                        break;
                    default:
                        s+= " Happening";
                        break;
                }*/
                
                
            }
        }else{
            s += sentence.toStringBrief();
        }

        if(interval > 0)
            s += " Interval is " + interval;
        
        //System.out.println("s: " + s);
        
        exportStrings.add(s);
    }
    
    public void generalInfoReport(String s){
        
        exportStrings.add(s);
        
    }
    
    public void executeTask(long time, Operation operation, TruthValue truth){
        
        Stamp stamp = new Stamp(Tense.Present, time);
        Sentence sentence = new Sentence(operation, Symbols.JUDGMENT_MARK, truth, stamp);
        
        BudgetValue budgetForNewTask = new BudgetValue(Parameters.DEFAULT_FEEDBACK_PRIORITY, Parameters.DEFAULT_FEEDBACK_DURABILITY,
        BudgetFunctions.truthToQuality(truth));
        
        Task newTask = new Task(sentence, budgetForNewTask);
        
        reasoner.getInternalBuffer().putInSequenceList(newTask, getTime());
    }
    
    public Operator getOperator(String op){
        return operators.get(op);
    }
    
    public Operator addOperator(Operator op){
        operators.put(op.getName(), op);
        return op;
    }
    
    public Operator removeOperator(Operator op){
        return operators.remove(op.getName());
    }
    
    @Override
    public String toString() {
        return toStringLongIfNotNull(concepts, "concepts")
                + toStringLongIfNotNull(currentTask, "currentTask")
                + toStringLongIfNotNull(currentBeliefLink, "currentBeliefLink")
                + toStringIfNotNull(currentBelief, "currentBelief");
    }

    private String toStringLongIfNotNull(Bag<?> item, String title) {
        return item == null ? "" : "\n " + title + ":\n"
                + item.toStringLong();
    }

    private String toStringLongIfNotNull(Item item, String title) {
        return item == null ? "" : "\n " + title + ":\n"
                + item.toStringLong();
    }

    private String toStringIfNotNull(Object item, String title) {
        return item == null ? "" : "\n " + title + ":\n"
                + item.toString();
    }

    public AtomicInteger getTaskForgettingRate() {
        return taskForgettingRate;
    }

    public AtomicInteger getBeliefForgettingRate() {
        return beliefForgettingRate;
    }

    public AtomicInteger getConceptForgettingRate() {
        return conceptForgettingRate;
    }

    class NullInferenceRecorder implements IInferenceRecorder {

        @Override
        public void init() {
        }

        @Override
        public void show() {
        }

        @Override
        public void play() {
        }

        @Override
        public void stop() {
        }

        @Override
        public void append(String s) {
        }

        @Override
        public void openLogFile() {
        }

        @Override
        public void closeLogFile() {
        }

        @Override
        public boolean isLogging() {
            return false;
        }
    }
    
    public ConceptBag getConcepts(){
        return concepts;
    }
}
