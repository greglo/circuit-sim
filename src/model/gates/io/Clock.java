package model.gates.io;

import model.circuit.ClockSignalListener;
import model.gates.AbstractGate;

/**
 * A clock is an single-output AbstractInput which generates its output based
 * off the system clock.
 * 
 * @author Greg
 * 
 */
public class Clock extends AbstractInput implements ClockSignalListener {
    private final Toggle internalToggle;
    private final long period;

    /**
     * Create a new Clock
     * @param period
     * 		The clock changes its state every `period` clock ticks
     */
    public Clock(long period) {
	super(1);
	this.internalToggle = new Toggle();
	this.period = period;
	computeOutputs();
    }

    /**
     * 
     * @return	The period of the system clock
     */
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
