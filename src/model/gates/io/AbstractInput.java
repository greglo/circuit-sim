package model.gates.io;

import model.gates.AbstractGate;

public abstract class AbstractInput extends AbstractGate {

    public AbstractInput() {
	super(0, 1);
    }
    
    public AbstractInput(int numOutputs) {
	super(0, numOutputs);
    }
}
