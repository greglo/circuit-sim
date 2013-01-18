package model.gates.composite;

import model.gates.AbstractGate;
import model.gates.basic.GateFactory;

public class CompositeGateFactory implements GateFactory {
    private final GateFactory firstFactory;
    private final GateFactory secondFactory;    
    
    public CompositeGateFactory(GateFactory firstFactory, GateFactory secondFactory) {
	this.firstFactory = firstFactory;
	this.secondFactory = secondFactory;
	
    }
    
    @Override
    public AbstractGate newGate() {
	return newGate(2);
    }

    @Override
    public AbstractGate newGate(int numInputs) {
	AbstractGate firstGate = firstFactory.newGate(numInputs);
	AbstractGate secondGate = secondFactory.newGate(firstGate.getNumOutputs());
	return new CompositeGate(firstGate, secondGate);
    }

    private class CompositeGate extends AbstractGate {
	private final AbstractGate firstGate;
	private final AbstractGate secondGate;
	
	public CompositeGate(AbstractGate firstGate, AbstractGate secondGate) {
	    super(firstGate.getNumInputs(), secondGate.getNumOutputs());
	    this.firstGate = firstGate;
	    this.secondGate = secondGate;
	    computeOutput();
	}

	@Override
	public void computeOutput() {
	    if (firstGate != null && secondGate != null) {
		firstGate.setInputs(inputs);
		secondGate.setInputs(firstGate.getOutputs());
		outputs = secondGate.getOutputs();
	    }
	}

	@Override
	public AbstractGate clone() {
	    return new CompositeGate(firstGate.clone(), secondGate.clone());
	}
    }
}
