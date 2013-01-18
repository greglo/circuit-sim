package model.gates.basic;

import model.gates.AbstractGate;

public interface GateFactory {
    public AbstractGate newGate();
    public AbstractGate newGate(int numInputs);
}
