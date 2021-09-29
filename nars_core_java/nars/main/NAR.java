package nars.main;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import nars.entity.Stamp;
import nars.entity.Task;
import nars.io.InputChannel;
import nars.io.OutputChannel;
import nars.io.StringParser;
import nars.io.Symbols;
import nars.storage.InternalExperience;
import nars.storage.Memory;
import nars.storage.OveralExperience;

public class NAR {

    /** global DEBUG print switch */
    public static final boolean DEBUG = false;
    /** The name of the reasoner */
    protected String name;
    /** The memory of the reasoner */
    protected Memory memory;
    /** The input channels of the reasoner */
    protected ArrayList<InputChannel> inputChannels;
    /** The output channels of the reasoner */
    protected ArrayList<OutputChannel> outputChannels;
    /** System clock, relatively defined to guarantee the repeatability of behaviors */
    private long clock;
    /** Flag for running continuously */
    private boolean running;
    /** The remaining number of steps to be carried out (walk mode) */
    private int walkingSteps;
    /** Determines the end of {@link NARSBatch} program (set but not accessed in this class) */
    private boolean finishedInputs;
    /** System clock - number of cycles since last output */
    private long timer;
    /** Budget Threshold - show output if its budget average above threshold */
    private final AtomicInteger silenceValue = new AtomicInteger(Parameters.SILENT_LEVEL);
    /** Internal Experience Buffer for derivations */
    private final InternalExperience internalBuffer;
    /** Overall Experience Buffer for input and tasks from internalBuffer */
    private final OveralExperience globalBuffer;
    //private final Experience_From_Narsese narsese_Channel;
    
    private final int internal_Duration = Parameters.MAX_BUFFER_DURATION_FACTOR * Parameters.DURATION_FOR_INTERNAL_BUFFER;
    private final int global_Duration = Parameters.MAX_BUFFER_DURATION_FACTOR * Parameters.DURATION_FOR_GLOBAL_BUFFER;

    public NAR() {
        memory = new Memory(this);
        //System.out.println(memory.newStamp.getOccurrenceTime());
        inputChannels = new ArrayList();
        outputChannels = new ArrayList();
        internalBuffer = new InternalExperience(memory, internal_Duration, "Internal");
        globalBuffer = new OveralExperience(memory, global_Duration, "Global");
       // narsese_Channel = new Experience_From_Narsese(memory, global_Duration);
    }
    
    public void addInputChannel(InputChannel channel) {
        inputChannels.add(channel);
    }

    public void removeInputChannel(InputChannel channel) {
        inputChannels.remove(channel);
    }

    public void addOutputChannel(OutputChannel channel) {
        outputChannels.add(channel);
    }

    public void removeOutputChannel(OutputChannel channel) {
        outputChannels.remove(channel);
    }

    /** Reset the system with an empty memory and reset clock. */
    public void reset() {
        //CompositionalRules.rand = new Random(1);
        running = false;
        walkingSteps = 0;
        clock = 0;
        memory.init();
        Stamp.init();
        //timer = 0;
    }

    /** Start the inference process */
    public void run() {
        running = true;
    }

    /**
     * Will carry the inference process for a certain number of steps
     *
     * @param n The number of inference steps to be carried
     */
    public void walk(int n) {
        walkingSteps = n;
    }

    /**
     * Will stop the inference process
     */
    public void stop() {
        running = false;
    }

    /**
     * A system cycle. Run one working workCycle or read input. 
     * Called from Shell.java and NARS.java
     * only.
     */
    public void cycle() {
        if (DEBUG) {
            if (running || walkingSteps > 0 || !finishedInputs) {
                System.out.println("// In Cycle: "
                        + "walkingSteps " + walkingSteps
                        + ", clock " + clock
                        + ", getTimer " + getTimer()
                        + "\n//    memory.getExportStrings() " + memory.getExportStrings());
                System.out.flush();
            }
        }
        if (walkingSteps == 0) {
            boolean reasonerShouldRun = false;
            for (InputChannel channelIn : inputChannels) {
                reasonerShouldRun = reasonerShouldRun || channelIn.nextInput();
            }
            finishedInputs = !reasonerShouldRun;
        }
        // forward to output Channels
        ArrayList<String> output = memory.getExportStrings();
        if (!output.isEmpty()) {
            for (OutputChannel channelOut : outputChannels) {
                channelOut.nextOutput(output);
            }
            output.clear();	// this will trigger display the current value of timer in Memory.report()
        }
        if (running || walkingSteps > 0) {
            clock++;
            tickTimer();
            
            memory.workCycle(clock);

            if (walkingSteps > 0) {
                walkingSteps--;
            }
        }
    }
    
    /**
     * To process a line of input text
     *
     * @param text
     */
    public void textInputLine(String text) {
        if (text.isEmpty()) {return;}
        char c = text.charAt(0);
        if (c == Symbols.RESET_MARK) {
            reset();
            memory.getExportStrings().add(text);
        } else if (c != Symbols.COMMENT_MARK) {
            // read NARS language or an integer : TODO duplicated code
            try {
                int i = Integer.parseInt(text);
                walk(i);
            } catch (NumberFormatException e) {
                Task task = StringParser.parseExperience(new StringBuffer(text), memory, clock);              
                if (task != null) {
                    inputNarseseTask(task);
                }
            }
        }
    }

    /** Adds task to main memory 
     *  @param task
     */
    private void inputNarseseTask(Task task){
        if(task.getBudget().aboveThreshold()){
            memory.getRecorder().append("!!! Perceived: " + task + "\n");
            memory.report(task.getSentence(), true, false);
            task.getBudget().incPriority((float)0.1);
            globalBuffer.preProcessing(task, true);
            //globalBuffer.putInSequenceList(task, memory.getTime());
        }else{
            memory.getRecorder().append("!!! Neglected: " + task + "\n");
        }
    }
    
    public Memory getMemory() {
        return memory;
    }
    
    /**
     * Get the current time from the clock Called in {@link nars.entity.Stamp}
     *
     * @return The current time
     */
    public long getTime() {
        return clock;
    }
    
    public int getWalkingSteps(){
        return walkingSteps;
    }
    
    public InternalExperience getInternalBuffer(){
        return internalBuffer;        
    }
    
    public OveralExperience getGlobalBuffer(){
        return globalBuffer;
    }
    
    /** Report Silence Level */
    public AtomicInteger getSilenceValue() {
        return silenceValue;
    }

    /**
     * determines the end of {@link NARSBatch} program
     * @return 
     */
    public boolean isFinishedInputs() {
        return finishedInputs;
    }

    /**
     * To get the timer value and then to
     * reset it by {@link #initTimer()};
     *
     * @return The previous timer value
     */
    public long updateTimer() {
        long i = getTimer();
        initTimer();
        return i;
    }

    /** Reset timer */
    public void initTimer() {
        setTimer(0);
    }

    /** Update timer */
    public void tickTimer() {
        setTimer(getTimer() + 1);
    }

    /** @return System clock : number of cycles since last output */
    public long getTimer() {
        return timer;
    }

    /** set System clock : number of cycles since last output */
    private void setTimer(long timer) {
        this.timer = timer;
    }
    
    @Override
    public String toString() {
        return memory.toString();
    }
}