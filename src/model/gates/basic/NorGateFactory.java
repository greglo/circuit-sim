package model.gates.basic;

import model.gates.AbstractGate;
import model.gates.composite.CompositeGateFactory;

public class NorGateFactory implements GateFactory {
    public static final NorGateFactory INSTANCE = new NorGateFactory();
    
    @Override
    public AbstractGate newGate() {
	return newGate(2);
    }

    @Override
    public AbstractGate newGate(int numInputs) {
	CompositeGateFactory factory = new CompositeGateFactory(OrGateFactory.INSTANCE, NotGateFactory.INSTANCE);
	return factory.newGate(numInputs);
    }
}

