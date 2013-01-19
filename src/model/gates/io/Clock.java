package model.gates.io;

import model.circuit.ClockSignalListener;
import model.gates.AbstractGate;

public class Clock extends AbstractInput implements ClockSignalListener {
    private final Toggle internalToggle;
    private final long period;
    
    public Clock(long period) {
	super(1);
	this.internalToggle = new Toggle();
	this.period = period;
	computeOutputs();
    }
    
    public long getPeriod() {
	return period;
    }

    @Override
    public void updateTime(long time) {
	if (time % period == 0) {
	    internalToggle.flip();
	    computeOutputs();
	}
    }

    @Override
    protected void computeOutputs() {
	if (internalToggle != null)
	    setOutput(0, internalToggle.getOutput(0));
    }

    @Override
    public AbstractGate clone() {
	return new Clock(period);
    }
}
