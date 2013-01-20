package model.gates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractGate {
    protected Jack inputJacks[];
    protected Jack outputJacks[];
    
    protected final List<GateStatusListener> listeners;
    
    /**
     * Class constructor
     * @param numInputs		The number of input jacks the gate will have
     * @param numOutputs	The number of output jacks the gate will have
     */
    public AbstractGate(int numInputs, int numOutputs) {
	assert(numInputs >= 0 && numOutputs >= 0);
	
	listeners = new ArrayList<GateStatusListener>();
	
	// Create input jacks
	inputJacks = new Jack[numInputs];
	for (int i = 0; i < numInputs; i++)
	    inputJacks[i] = new Jack(this, i);
	
	// Create output jacks
	outputJacks = new Jack[numOutputs];	
	for (int i = 0; i < numOutputs; i++)
	    outputJacks[i] = new Jack(this, i);
	
	computeOutputs();
    }
    
    /**
     * Returns the number of input jacks the gate has
     * @return
     */
    public int getNumInputs() {
	return inputJacks.length;
    }
    
    /**
     * Returns the number of output jacks the gate has
     * @return
     */
    public int getNumOutputs() {
	return outputJacks.length;
    }
    
    /**
     * Get the Jack object attached to a specific port of the gate
     * @param port	The port whose jack you want
     * @return		The jack
     */
    public Jack getInputJack(int port) {
	if (0 <= port && port < inputJacks.length)
	    return inputJacks[port];
	else
	    throw new IllegalArgumentException("AbstractGate.getInputJack(port) port out of bounds!");
    }
    
    /**
     * Get the Jack object attached to a specific port of the gate
     * @param port	The port whose jack you want
     * @return		The jack
     */
    public Jack getOutputJack(int port) {
	if (0 <= port && port < outputJacks.length)
	    return outputJacks[port];
	else
	    throw new IllegalArgumentException("AbstractGate.getOutputJack(port) port out of bounds!");
    }
    
    protected boolean getInput(int port) {
	return getInputJack(port).isOn();
    }
    
    protected boolean[] getInputs() {
	boolean inputs[] = new boolean[inputJacks.length];
	for (int i = 0; i < inputJacks.length; i++)
	    inputs[i] = inputJacks[i].isOn();
	
	return inputs;
    }
    
    /**
     * Sets the value coming into the gate at an input port
     * @param port	The port to set
     * @param isOn	The value of the input
     */
    public void setInput(int port, boolean isOn) {
	if (0 <= port && port < inputJacks.length) {
	    // Store old outputs
	    boolean oldOutputs[] = getOutputs();
	    
	    // Compute new outputs
	    inputJacks[port].setOn(isOn);
	    computeOutputs();
	    
	    // Only fire if outputs changed
	    if (!Arrays.equals(getOutputs(), oldOutputs))
		fireGateOutputChanged();
	}
	else
	    throw new IllegalArgumentException("AbstractGate.setInput(i) i out of bounds!");
    }
    
    /**
     * Sets the values coming into all input ports
     * @param inputs	Array of input values
     */
    public void setInputs(boolean inputs[]) {
	if (this.inputJacks.length == inputs.length) {
	    // Store old outputs
	    boolean oldOutputs[] = getOutputs();
	    
	    // Compute new outputs
	    for (int i = 0; i < inputJacks.length; i++)
		inputJacks[i].setOn(inputs[i]);
	    computeOutputs();
	   
	    // Only fire if outputs changed
	    if (!Arrays.equals(getOutputs(), oldOutputs))
		fireGateOutputChanged();
	}
	else
	    throw new IllegalArgumentException("AbstractGate.setInputs() incompatible input array!");
    }
    
    /**
     * Get whether a specific output port is on or off
     * @param port	The port to test
     * @return		Whether the port is on or off
     */
    public boolean getOutput(int port) {
	return getOutputJack(port).isOn();
    }
    
    /**
     * Get a boolean array of containing all outputs, indexed by port number
     * @return
     */
    public boolean[] getOutputs() {
	boolean outputs[] = new boolean[outputJacks.length];
	for (int i = 0; i < outputJacks.length; i++)
	    outputs[i] = outputJacks[i].isOn();
	
	return outputs;
    }
    
    protected void setOutput(int port, boolean isOn) {
	if (0 <= port && port < outputJacks.length)
	    outputJacks[port].setOn(isOn);
	else
	    throw new IllegalArgumentException("AbstractGate.setOutput(i) i out of bounds!");
    }
    
    /**
     * Evaluate the input jacks, and update the output jacks according to the logic rules of the gate
     */
    protected abstract void computeOutputs();
    
    
    public void addListener(GateStatusListener l) {
	listeners.add(l);
    }
    
    public void removeListener(GateStatusListener l) {
	listeners.remove(l);
    }
    
    protected void fireGateOutputChanged() {
	for (GateStatusListener l : listeners)
	    l.gateOutputChanged(this);
    }
    
    public abstract AbstractGate clone();
}
