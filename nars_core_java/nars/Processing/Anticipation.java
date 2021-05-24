/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nars.Processing;

import nars.entity.Task;
import nars.language.Term;

/**
 *
 * @author Xiang
 */
public class Anticipation implements Comparable<Anticipation>{
    
    private final Task task;
    private final Term relation;
    private final long expectationOccurrenceTime;
    private final long preconditionOccurrenceTime;
    
    public Anticipation(Task task, Term relation, long expectationOccurrenceTime, long preconditionOccurrenceTime){
        
        this.task = task;
        this.expectationOccurrenceTime = expectationOccurrenceTime;
        this.preconditionOccurrenceTime = preconditionOccurrenceTime;
        this.relation = relation;
        
    }
    
    public Task getTask(){
        return task;
    }
    
    public Term getRelation(){
        return relation;
    }
    
    public long getExpectationOccurrenceTime(){
        return expectationOccurrenceTime;
    }
    
    public long getPreconditionOccurrenceTime(){
        return preconditionOccurrenceTime;
    }

    @Override
    public int compareTo(Anticipation o) {
       
        if(task.getName().equals(o.getTask().getName()) && relation.equals(o.getRelation()) &&
                expectationOccurrenceTime == o.getExpectationOccurrenceTime() &&
                preconditionOccurrenceTime == o.getPreconditionOccurrenceTime())
            return 0;
        else if(task.getPriority() > o.getTask().getPriority())
            return 1;
        else
            return -1;
        
    }
    
}
