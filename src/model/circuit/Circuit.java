package model.circuit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.gates.AbstractGate;
import model.gates.InputJack;
import model.gates.Jack;
import model.gates.OutputJack;
import model.gates.OutputJackListener;
import model.gates.basic.AndGateFactory;
import model.gates.basic.NotGateFactory;
import model.gates.io.Switch;

public class Circuit implements OutputJackListener {
    private final Set<AbstractGate> gates = new HashSet<AbstractGate>();
    private final Map<OutputJack, InputJack> wires = new HashMap<OutputJack, InputJack>();
    
    private long systemTime = 0;
    private List<ClockSignalListener> clockSignalListeners = new ArrayList<ClockSignalListener>();
    
    
    public Circuit() {
	runNotTest();
	runAndTest();
    }
    
    private void runNotTest() {
	// Test git
	
	System.out.println("NOT Test:");
	AbstractGate not = NotGateFactory.INSTANCE.newGate(1);
	gates.add(not);
	
	Switch toggle = new Switch();
	gates.add(toggle);
	
	OutputJack toggleOut = new OutputJack(toggle, 0);
	InputJack notIn = new InputJack(not, 0);
	wires.put(toggleOut, notIn);
	OutputJack notOut = new OutputJack(not, 0);
	
	toggleOut.addListener(this);
	notOut.addListener(this);
	toggle.addListener(toggleOut);
	not.addListener(notOut);
	
	System.out.println(notOut.isOn());
	toggle.flip();
	System.out.println(notOut.isOn());
	toggle.flip();
	System.out.println(notOut.isOn());
    }
    
    private void runAndTest() {
	System.out.println("AND Test:");
	
	Switch toggle1 = new Switch();
	Switch toggle2 = new Switch();
	OutputJack toggle1Out = new OutputJack(toggle1, 0);
	OutputJack toggle2Out = new OutputJack(toggle2, 0);
	
	AbstractGate and = AndGateFactory.INSTANCE.newGate(2);
	InputJack andIn1 = new InputJack(and, 0);
	InputJack andIn2 = new InputJack(and, 1);
	OutputJack andOut = new OutputJack(and, 0);
	
	wires.put(toggle1Out, andIn1);
	wires.put(toggle2Out, andIn2);
	
	toggle1.addListener(toggle1Out);
	toggle2.addListener(toggle2Out);
	toggle1Out.addListener(this);
	toggle2Out.addListener(this);
	
	System.out.println("0 AND 0 == " + andOut.isOn());
	toggle1.flip();
	System.out.println("1 AND 0 == " + andOut.isOn());
	toggle2.flip();
	System.out.println("1 AND 1 == " + andOut.isOn());

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
