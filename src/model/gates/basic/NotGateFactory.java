package model.gates.basic;

import model.gates.AbstractGate;

public class NotGateFactory implements GateFactory {
    public static final NotGateFactory INSTANCE = new NotGateFactory();
    
    @Override
    public AbstractGate newGate() {
	return newGate(1);
    }

    @Override
    public AbstractGate newGate(int numInputs) {
	return new Not(numInputs);
    }
    
    private class Not extends AbstractGate {
	public Not() {
	    super(1, 1);
	}
	
	public Not(int numInputs) {
	    super(numInputs, numInputs);
	}

	@Override
	protected void computeOutput() {
	    for (int i = 0; i < inputs.length; i++) {
		outputs[i] = !inputs[i];
	    }
	}

	@Override
	public AbstractGate clone() {
	    Not newNot = new Not();
	    newNot.setInputs(inputs.clone());
	    return newNot;
	}
    }
}
