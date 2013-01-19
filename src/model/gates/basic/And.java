package model.gates.basic;

import model.gates.AbstractGate;

public class And extends AbstractGate {

    /**
     * Construct a 2-input AND gate
     */
    public And() {
	super(2, 1);
    }

    /**
     * Construct n-input AND gate
     * 
     * @param numInputs
     *            Number of inputs
     */
    public And(int numInputs) {
	super(numInputs, 1);
    }

    @Override
    protected void computeOutputs() {
	int i = 0;
	boolean allTrue = true;

	while (i < inputJacks.length && allTrue) {
	    allTrue = inputJacks[i].isOn();
	    i++;
	}

	setOutput(0, allTrue);
    }

    @Override
    public AbstractGate clone() {
	And newAnd = new And(inputJacks.length);
	newAnd.setInputs(getInputs());
	return newAnd;
    }
}
