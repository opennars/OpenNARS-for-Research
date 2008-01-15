
package nars.main;

/**
 * Collected system parameters. To be modified before compiling.
 */
public class Parameters {
    
    /* ---------- initial values of run-time adjustable parameters ---------- */

    /** Concept decay rate, in [1, 99]. */
    public static final int CONCEPT_DEFAULT_FORGETTING_CYCLE = 3;
    /** TaskLink decay rate, in [1, 99]. */
    public static final int TASK_DEFAULT_FORGETTING_CYCLE = 10;
    /** CompositionLink decay rate, in [1, 99]. */
    public static final int BELIEF_DEFAULT_FORGETTING_CYCLE = 50;
       
    /** Silent threshold for task reporting, in [0, 100]. */
    public static final int SILENT_LEVEL = 100;

    /* ---------- parameters fixed after compiling --- logical ---------- */

    /** Horizon, the amount of evidence coming in the near future. */
    public static final int NEAR_FUTURE = 1;    // or 2, can be float
    
    /** Default confidence of input judgment. */
    public static final float DEFAULT_JUDGMENT_CONFIDENCE = (float) 0.9;  // ?

    /* Default priority and durability of input judgment */
    public static final float DEFAULT_JUDGMENT_PRIORITY = (float) 0.8;
    public static final float DEFAULT_JUDGMENT_DURABILITY = (float) 0.8;
    public static final float DEFAULT_GOAL_PRIORITY = (float) 0.9;
    public static final float DEFAULT_GOAL_DURABILITY = (float) 0.7;
    public static final float DEFAULT_QUESTION_PRIORITY = (float) 0.9;
    public static final float DEFAULT_QUESTION_DURABILITY = (float) 0.7;
    
//    public static final float TASK_FOR_NEW_CONCEPT_DISCOUNT = (float) 0.2;  // ?
    
    // belief component priority rate
    public static final float DEFAULT_COMPONENT_PRIORITY_RATE = (float) 0.7;
    
    /**
     * Level granularity in Bag, two digits
     */
    public static final int BAG_LEVEL = 100;
    /**
     * Level separation in Bag, one digit, both for display (run-time adjustable) and management (fixed)
     */
    public static final int BAG_THRESHOLD = 10;         

    /* ---------- parameters fixed after compiling --- time management ---------- */
    
    // time distribution
    public static final int COLD_TASK_DELAY_STEPS = 10;                 // ?

    // decay rate, within 100
    public static final int NEW_TASK_DEFAULT_FORGETTING_CYCLE = 1;        // ?

    // quality updating rate
    public static final float DEFAULT_QUALITY_UPDATE_RATE = (float) 0.01; // percent of updating
    
    // maximum bLinks tried for each tLink (to avoid repeated inference)
    public static final int MAX_TAKE_OUT_K_LINK = 10;
    
    // maximum belief tried for each task (to avoid overlapping evidence)
    public static final int MAX_TAKE_OUT_BELIEF = 5;
    
    /* ---------- parameters fixed after compiling --- space management ---------- */

    // bag size
    public static final int CONCEPT_BAG_SIZE = 1000;         // vocabulary?
    public static final int TASK_BUFFER_SIZE = 20;          // "7+-2"?
    public static final int TASK_BAG_SIZE = 20;              // ?
    public static final int BELIEF_BAG_SIZE = 100;        // ?
    
    // other storage size
    public static final int MAXMUM_LABEL_RECORD_LENGTH = 16;    // should be pow(2,n)
    public static final int TASK_INFERENCE_RECORD_LENGTH = 20;  // ?
    public static final int MAXMUM_BELIEF_LENGTH = 8;      // duplicate links
    public static final int MAXMUM_GOALS_LENGTH = 5;       // duplicate links
    
    public static final float LOAD_FACTOR = 0.5f;   // bag hashtable parameter
}
