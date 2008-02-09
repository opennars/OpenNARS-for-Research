package com.googlecode.opennars.parser;

import com.googlecode.opennars.entity.Task;
import com.googlecode.opennars.main.Memory;

public abstract class Parser extends Symbols {

	/**
	 * The only public (static) method of the class, called from Memory.inputTask.
	 * @param buffer the single-line input String
	 * @param memory the memory object doing the parsing
	 * @return an input Task, or null if the input line cannot be parsed into a Task
	 * @throws InvalidInputException 
	 */
	public abstract Task parseTask(String input, Memory memory) throws InvalidInputException;
	
}