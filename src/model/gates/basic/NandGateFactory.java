package model.gates.basic;

import model.gates.AbstractGate;
import model.gates.composite.CompositeGateFactory;

public class NandGateFactory implements GateFactory {
    public static final NandGateFactory INSTANCE = new NandGateFactory();
    
    @Override
    public AbstractGate newGate() {
	return newGate(2);
    }

    @Override
    public AbstractGate newGate(int numInputs) {
	CompositeGateFactory factory = new CompositeGateFactory(AndGateFactory.INSTANCE, NotGateFactory.INSTANCE);
	return factory.newGate(numInputs);
    }
}
