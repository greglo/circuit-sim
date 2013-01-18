package model.gates.io;

import model.gates.AbstractGate;

public class Switch extends AbstractInput {
    private boolean isOn = false;
    
    public Switch() {
	super(1);
    }
    
    public void setOn(boolean isOn) {
	if (this.isOn != isOn) {
	    this.isOn = isOn;
	    computeOutput();
	    fireGateIOChanged();
	}
    }
    
    public void flip() {
	setOn(!isOn);
    }

    @Override
    public void computeOutput() {
	outputs[0] = isOn;
    }

    @Override
    public AbstractGate clone() {
	Switch newSwitch = new Switch();
	newSwitch.setOn(isOn);
	return newSwitch;
    }

}
