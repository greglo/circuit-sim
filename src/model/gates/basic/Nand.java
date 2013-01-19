package model.gates.basic;

import model.gates.AbstractGate;

public class Nand extends AbstractGate {
    
    /**
     * Construct a 2-input NAND gate
     */
    public Nand() {
	super(2, 1);
    }

    /**
     * Construct n-input NAND gate
     * 
     * @param numInputs
     *            Number of inputs
     */
    public Nand(int numInputs) {
	super(numInputs, 1);
    }

    @Override
    protected void computeOutputs() {
	// A NAND gate is a negated AND gate, so simulate that here
	And and = new And(inputJacks.length);
	and.setInputs(getInputs());
	setOutput(0, !and.getOutput(0));
    }

    @Override
    public AbstractGate clone() {
	Nand newNand = new Nand(inputJacks.length);
	newNand.setInputs(getInputs());
	return newNand;
    }
}
