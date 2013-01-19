package model.gates.io;

import model.gates.AbstractGate;

public class Toggle extends AbstractInput {
    private boolean isOn = false;
    
    public Toggle() {
	super(1);
    }
    
    public void setOn(boolean isOn) {
	if (this.isOn != isOn) {
	    this.isOn = isOn;
	    computeOutputs();
	    fireGateOutputChanged();
	}
    }
    
    public void flip() {
	setOn(!isOn);
    }

    @Override
    public void computeOutputs() {
	setOutput(0, isOn);
    }

    @Override
    public AbstractGate clone() {
	Toggle newToggle = new Toggle();
	newToggle.setOn(isOn);
	return newToggle;
    }

}
