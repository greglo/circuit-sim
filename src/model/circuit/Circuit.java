package model.circuit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.gates.AbstractGate;
import model.gates.GateStatusListener;
import model.gates.Jack;

public class Circuit implements GateStatusListener {
    private final Set<AbstractGate> gates = new HashSet<AbstractGate>();
    private final Map<Jack, Jack> wires = new HashMap<Jack, Jack>();

    private long systemTime = 0;
    private List<ClockSignalListener> clockSignalListeners = new ArrayList<ClockSignalListener>();

    public Circuit() {
    }

    public void addGate(AbstractGate gate) {
	if (gates.add(gate))
	    gate.addListener(this);
    }

    public void removeGate(AbstractGate gate) {
	if (gates.remove(gate))
	    gate.removeListener(this);
    }

    public void addWire(Jack outputJack, Jack inputJack) {
	if (!gates.contains(outputJack.getGate()))
	    throw new IllegalArgumentException(
		    "Source gate does not not in the circuit!");
	if (!gates.contains(inputJack.getGate()))
	    throw new IllegalArgumentException(
		    "Target gate does not not in the circuit!");
	if (wires.containsKey(outputJack))
	    throw new IllegalArgumentException(
		    "A wire is already coming from the output terminal!");
	if (wires.containsValue(inputJack))
	    throw new IllegalArgumentException(
		    "A wire is already going to the input terminal!");

	wires.put(outputJack, inputJack);
    }

    public long getSystemTime() {
	return systemTime;
    }

    private void setSystemTime(long systemTime) {
	this.systemTime = systemTime;
	fireSystemTimeChanged();
    }

    public void systemClockTick() {
	setSystemTime(systemTime + 1);
    }

    private void fireSystemTimeChanged() {
	for (ClockSignalListener l : clockSignalListeners)
	    l.updateTime(systemTime);
    }

    @Override
    public void gateOutputChanged(AbstractGate gate) {
	// Propagate through the wires to the next gate
	// Assume no loops (for now)!
	// For a cyclic system, use propagation delays? Spawn new thread?
	// Also, only propagate for jacks which changed?
	System.out.println("Called on " + gate.toString());
	for (int port = 0; port < gate.getNumOutputs(); port++) {
	    Jack outputJack = gate.getOutputJack(port);
	    Jack nextJack = wires.get(outputJack);
	    if (nextJack != null)
		nextJack.getGate().setInput(nextJack.getPort(), gate.getOutput(port));
	    }
    }
}
