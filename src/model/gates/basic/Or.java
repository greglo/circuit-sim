package model.gates.basic;

import model.gates.AbstractGate;

public class Or extends AbstractGate {

    /**
     * Construct a 2-input AND gate
     */
    public Or() {
	super(2, 1);
    }

    /**
     * Construct n-input OR gate
     * 
     * @param numInputs
     *            Number of inputs
     */
    public Or(int numInputs) {
	super(numInputs, 1);
    }

    @Override
    protected void computeOutputs() {
	int i = 0;
	boolean anyTrue = false;

	while (i < inputJacks.length && !anyTrue) {
	    anyTrue = inputJacks[i].isOn();
	    i++;
	}

	setOutput(0, anyTrue);
	}

    @Override
    public AbstractGate clone() {
	Or newOr = new Or(inputJacks.length);
	newOr.setInputs(getInputs());
	return newOr;
    }
}
