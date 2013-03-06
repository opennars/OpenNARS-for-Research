package nars.main;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import nars.entity.Stamp;
import nars.entity.Task;
import nars.io.InputChannel;
import nars.io.OutputChannel;
import nars.io.StringParser;
import nars.io.Symbols;
import nars.storage.Memory;

public class ReasonerBatch {

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
	/** The number of steps to be carried out */
	private int walkingSteps;
	private boolean finishedInputs;

	public ReasonerBatch() {
		super();
	}

	/**
	 * Reset the system with an empty memory and reset clock. Called locally and from MainWindow.
	 */
	public void reset() {
	    running = false;
	    walkingSteps = 0;
	    clock = 0;
	    memory.init();
	    Stamp.init();
	}

	public Memory getMemory() {
	    return memory;
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

	/**
	 * Get the current time from the clock
	 * Called in nars.entity.Stamp
	 * @return The current time
	 */
	public long getTime() {
	    return clock;
	}

	/**
	 * Start the inference process
	 */
	public void run() {
	    running = true;
	}

	/**
	 * Carry the inference process for a certain number of steps
	 * @param n The number of inference steps to be carried
	 */
	public void walk(int n) {
	    walkingSteps = n;
	}

	/**
	 * Stop the inference process
	 */
	public void stop() {
	    running = false;
	}

	/**
	 * A clock tick. Run one working workCycle or read input. Called from NARS only.
	 */
	public void tick() {
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				if (walkingSteps == 0) {
					boolean reasonerShouldRun = false;
					for (InputChannel channelIn : inputChannels) {
						reasonerShouldRun = reasonerShouldRun ||
								channelIn.nextInput();
					}
					finishedInputs = ! reasonerShouldRun;
				}
				ArrayList<String> output = memory.getExportStrings();
				if (!output.isEmpty()) {
					for (OutputChannel channelOut : outputChannels) {
						channelOut.nextOutput(output);
					}
					output.clear();
				}
				if (running || walkingSteps > 0) {
					clock++;
					for (OutputChannel channelOut : outputChannels) {
						channelOut.tickTimer();
					}
					memory.workCycle(clock);
					if (walkingSteps > 0) {
						walkingSteps--;
					}
				}		
			} } );
	}

	public boolean isFinishedInputs() {
		return finishedInputs;
	}

	/**
	 * To process a line of input text
	 * @param text
	 */
	public void textInputLine(String text) {
	    if (text.isEmpty()) {
	        return;
	    }
	    char c = text.charAt(0);
	    if (c == Symbols.RESET_MARK) {
	        reset();
	        memory.getExportStrings().add(text);
	    } else if (c == Symbols.COMMENT_MARK) {
	        return;
	    } else {
	        // read NARS language or an integer : TODO duplicated code
	        try {
	            int i = Integer.parseInt(text);
	            walk(i);
	        } catch (NumberFormatException e) {
	            Task task = StringParser.parseExperience(new StringBuffer(text), memory, clock);
	            if (task != null) {
	                memory.inputTask(task);
	            }
	        }
	    }
	}

	@Override
	public String toString() {
		return memory.toString();
	}

}