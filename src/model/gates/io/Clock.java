package model.gates.io;

import model.circuit.ClockSignalListener;
import model.gates.AbstractGate;

public class Clock extends AbstractInput implements ClockSignalListener {
    private final Switch internalSwitch;
    private final long period;
    
    public Clock(long period) {
	super(1);
	this.internalSwitch = new Switch();
	this.period = period;
	computeOutput();
    }
    
    public long getPeriod() {
	return period;
    }

    @Override
    public void updateTime(long time) {
	if (time % period == 0) {
	    internalSwitch.flip();
	    computeOutput();
	}
    }

    @Override
    protected void computeOutput() {
	if (internalSwitch != null)
	    outputs[0] = internalSwitch.getOutput(0);
    }

    @Override
    public AbstractGate clone() {
	return new Clock(period);
    }
}
