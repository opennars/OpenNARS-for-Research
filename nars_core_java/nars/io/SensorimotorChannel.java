/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nars.io;

import nars.main_nogui.ReasonerBatch;
import nars.storage.Buffer;
import nars.storage.Memory;

/**
 *
 * @author Xiang
 */
public class SensorimotorChannel implements InputChannel{

    private ReasonerBatch reasoner;
    private String name;
    public SensorimotorChannel(ReasonerBatch reasoner){
        this.reasoner = reasoner;
        name = "Sensorimotor Channel";
    }
    
    public void openSensorimotor(){
        System.out.println("Open the Sensorimotor Channel");  
        reasoner.addInputChannel(this);
    }
    
    @Override
    public boolean nextInput() {
        System.out.println("Next Sensori Input");
        return false;
    }

    
}
