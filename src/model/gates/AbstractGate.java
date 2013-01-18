package model.gates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractGate {
    protected boolean inputs[];
    protected boolean outputs[];
    
    protected final List<AbstractGateListener> listeners;
    
    public AbstractGate(int numInputs, int numOutputs) {
	assert(numInputs >= 0 && numOutputs >= 0);
	inputs = new boolean[numInputs];
	outputs = new boolean[numOutputs];
	computeOutput();
	
	listeners = new ArrayList<AbstractGateListener>();
    }
    
    public int getNumInputs() {
	return inputs.length;
    }
    
    public int getNumOutputs() {
	return outputs.length;
    }
    
    public void setInput(int port, boolean value) {
	if (0 <= port && port < inputs.length) {
	    // Only update if necessary
	    if (value != inputs[port]) {
		inputs[port] = value;
		computeOutput();
		fireGateIOChanged();
	    }
	}
	else
	    throw new IllegalArgumentException("AbstractGate.setInput(i) i out of bounds!");
    }
    
    public void setInputs(boolean inputs[]) {
	if (this.inputs.length == inputs.length) {
	    this.inputs = inputs.clone();
	    
	    boolean oldOutputs[] = new boolean[outputs.length];
	    oldOutputs = outputs.clone();
	    computeOutput();
	   
	    if (!Arrays.equals(outputs, oldOutputs))
	    	fireGateIOChanged();
	}
	else
	    throw new IllegalArgumentException("AbstractGate.setInputs() incompatible input array!");
    }
    
    public boolean getOutput(int port) {
	if (0 <= port && port < outputs.length) {
	    return outputs[port];
	}
	else
	    throw new IllegalArgumentException("AbstractGate.getInput(i) i out of bounds!");
    }
    
    public boolean[] getOutputs() {
	return outputs.clone();
    }
    
    protected abstract void computeOutput();
    
    public void addListener(AbstractGateListener l) {
	listeners.add(l);
    }
    public void removeListener(AbstractGateListener l) {
	listeners.remove(l);
    }
    
    protected void fireGateIOChanged() {
	for (AbstractGateListener l : listeners)
	    l.gateOutputChanged();
    }
    
    public abstract AbstractGate clone();
}
