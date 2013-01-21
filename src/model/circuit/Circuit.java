package model.circuit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import model.gates.AbstractGate;
import model.gates.GateStatusListener;
import model.gates.Jack;

/**
 * A circuit, containing the set of gates and a relation from output jacks to
 * input jacks (the wires). It also stores its own system time, for clocked
 * devices
 * 
 * @author Greg
 * 
 */
public class Circuit implements GateStatusListener {
    private final Set<AbstractGate> gates = new HashSet<AbstractGate>();
    private final BiMap<Jack, Jack> wires = HashBiMap.create();

    private long systemTime = 0;
    private List<ClockSignalListener> clockSignalListeners = new ArrayList<ClockSignalListener>();

    public Circuit() {
    }

    /**
     * Add a Gate to the circuit
     * 
     * @param gate
     *            The gate to be added
     */
    public void addGate(AbstractGate gate) {
	if (gates.add(gate))
	    gate.addListener(this);
    }

    /**
     * Remove a Gate from the circuit
     * 
     * @param gate
     *            The gate to be removed
     */
    public void removeGate(AbstractGate gate) {
	if (gates.remove(gate)) {
	    gate.removeListener(this);

	    // Remove all wires going into this gate
	    Iterator<Jack> inputJackIter = gate.getInputJacks();
	    while (inputJackIter.hasNext())
		removeWire(inputJackIter.next());

	    // Remove all wires coming out of this gate
	    Iterator<Jack> outputJackIter = gate.getOutputJacks();
	    while (outputJackIter.hasNext())
		removeWire(outputJackIter.next());
	}
    }

    /**
     * Return the number of gates in the circuit
     * 
     * @return
     */
    public int numGates() {
	return gates.size();
    }

    /**
     * Return an iterator across all the gates in the circuit
     * 
     * @return
     */
    public Iterator<AbstractGate> getGates() {
	return gates.iterator();
    }

    /**
     * Tests whether a jack is already bound to a wire in the circuit
     * 
     * @param jack
     * @return
     */
    public boolean isJackUsed(Jack jack) {
	boolean isOutputJack = wires.containsKey(jack);
	boolean isInputJack = wires.containsValue(jack);
	return (isOutputJack || isInputJack);
    }

    /**
     * Adds a wire to the circuit from an output jack to an input jack. The
     * corresponding gates must already be part of the circuit, and the Jacks
     * unbound
     * 
     * @param outputJack
     *            Output jack from a gate in the circuit
     * @param inputJack
     *            Input jack to a gate in the circuit
     */
    public void addWire(Jack outputJack, Jack inputJack) {
	if (!gates.contains(outputJack.getGate()))
	    throw new IllegalArgumentException(
		    "Source gate does not not in the circuit!");
	if (!gates.contains(inputJack.getGate()))
	    throw new IllegalArgumentException(
		    "Target gate does not not in the circuit!");
	if (isJackUsed(outputJack))
	    throw new IllegalArgumentException(
		    "The output jack is already in use!");
	if (isJackUsed(inputJack))
	    throw new IllegalArgumentException(
		    "The input jack is already in use!");

	wires.put(outputJack, inputJack);
    }

    /**
     * Removes (if it exists) the wire attached to a Jack. The Jack may be
     * either the input or the output jack
     * 
     * @param jack
     *            The jack at either end of the wire
     */
    public void removeWire(Jack jack) {
	// Try to remove the wire assuming we were given an output jack, if that
	// fails try assuming we were given an input jack
	if (wires.remove(jack) == null)
	    wires.inverse().remove(jack);
    }

    /**
     * 
     * @return
     */
    public long getSystemTime() {
	return systemTime;
    }

    /**
     * 
     * @param systemTime
     */
    private void setSystemTime(long systemTime) {
	this.systemTime = systemTime;
	fireSystemTimeChanged();
    }

    /**
     * 
     */
    public void systemClockTick() {
	setSystemTime(systemTime + 1);
    }

    /**
     * 
     */
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
	// Alternatively, add `gate` to a set of 'dirty' gates, and update
	// and propagate through one wire every clock tick?
	// On second thoughts, that is not a fantastic idea. Don't we want the
	// propagation delay for gates to be at least an order smaller than an
	// expected Timer period?
	for (int port = 0; port < gate.getNumOutputs(); port++) {
	    Jack outputJack = gate.getOutputJack(port);
	    Jack nextJack = wires.get(outputJack);

	    if (nextJack != null)
		nextJack.getGate().setInput(nextJack.getPort(),
			gate.getOutput(port));
	}
    }
}
