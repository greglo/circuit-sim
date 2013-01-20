package model.gates.io;

import model.gates.AbstractGate;

/**
 * Super type for input gates (gates with no input jacks, and generate their
 * outputs from other part of the state). E.g. Switches (user input)
 * 
 * @author Greg
 * 
 */
public abstract class AbstractInput extends AbstractGate {

    public AbstractInput() {
	super(0, 1);
    }

    public AbstractInput(int numOutputs) {
	super(0, numOutputs);
    }
}
