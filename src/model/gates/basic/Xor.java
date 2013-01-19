package model.gates.basic;

import model.gates.AbstractGate;

public class Xor extends AbstractGate {

    /**
     * Construct a 2-input XOR gate
     */
    public Xor() {
	super(2, 1);
    }

    /**
     * Construct n-input XOR gate
     * 
     * @param numInputs
     *            Number of inputs
     */
    public Xor(int numInputs) {
	super(numInputs, 1);
    }

    @Override
    protected void computeOutputs() {
	// We use the generally implemented definition of n-input XOR: a modulo 2 adder.
	// We count the number of on-signals, and return its parity
	boolean oddNumOns = false;
	
	for (int i = 0; i < inputJacks.length; i++)
	    if (getInput(i))
		oddNumOns = !oddNumOns;
	
	setOutput(0, oddNumOns);
    }

    @Override
    public AbstractGate clone() {
	Xor newXor = new Xor(inputJacks.length);
	newXor.setInputs(getInputs());
	return newXor;
    }
}
