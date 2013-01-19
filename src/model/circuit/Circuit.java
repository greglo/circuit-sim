package model.circuit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.gates.AbstractGate;
import model.gates.InputJack;
import model.gates.OutputJack;
import model.gates.OutputJackListener;


public class Circuit implements OutputJackListener {
    private final Set<AbstractGate> gates = new HashSet<AbstractGate>();
    private final Map<OutputJack, InputJack> wires = new HashMap<OutputJack, InputJack>();
    
    private long systemTime = 0;
    private List<ClockSignalListener> clockSignalListeners = new ArrayList<ClockSignalListener>();
    
    
    public Circuit() {
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
    public void jackStatusChanged(OutputJack jack, boolean isOn) {
	// Propagate changes to the next wire
	// Assume no loops (for now)!
	InputJack nextJack = wires.get(jack);
	if (nextJack != null) {
	    nextJack.setOn(isOn);
	}
    }
}
