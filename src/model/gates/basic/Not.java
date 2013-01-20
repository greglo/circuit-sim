package model.gates.basic;

import model.gates.AbstractGate;

/**
 * Concrete implementation of an NOT gate
 * @author Greg
 *
 */
public class Not extends AbstractGate {

    /**
     * Construct a 1-input NOT gate
     */
    public Not() {
	super(1, 1);
    }

    /**
     * Construct n-input NOT gate
     * 
     * @param numInputs
     *            Number of inputs
     */
    public Not(int numInputs) {
	super(numInputs, numInputs);
    }

    @Override
    protected void computeOutputs() {
	assert(inputJacks.length == outputJacks.length);
	
	int i = 0;
	while (i < inputJacks.length) {
	    setOutput(i, !getInput(i));
	    i++;
	}
	}

    @Override
    public AbstractGate clone() {
	Not newNot = new Not(inputJacks.length);
	newNot.setInputs(getInputs());
	return newNot;
    }
}