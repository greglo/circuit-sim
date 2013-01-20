package model.gates.basic;

import model.gates.AbstractGate;

/**
 * Concrete implementation of an NOR gate
 * @author Greg
 *
 */
public class Nor extends AbstractGate {
    
    /**
     * Construct a 2-input NOR gate
     */
    public Nor() {
	super(2, 1);
    }

    /**
     * Construct n-input NOR gate
     * 
     * @param numInputs
     *            Number of inputs
     */
    public Nor(int numInputs) {
	super(numInputs, 1);
    }

    @Override
    protected void computeOutputs() {
	// A NOR gate is a negated OR gate, so simulate that here
	Or or = new Or(inputJacks.length);
	or.setInputs(getInputs());
	setOutput(0, !or.getOutput(0));
    }

    @Override
    public AbstractGate clone() {
	Nor newNor = new Nor(inputJacks.length);
	newNor.setInputs(getInputs());
	return newNor;
    }
}

