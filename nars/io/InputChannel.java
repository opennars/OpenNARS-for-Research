/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nars.io;

/**
 * An interface to be implemented in all input channels 
 * To get the input for the next moment from an input channel
 * The return value indicating whether the reasoner should run
 */
public interface InputChannel {
    public boolean nextInput();
}
