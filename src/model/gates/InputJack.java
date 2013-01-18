package model.gates;

public class InputJack implements Jack {
    private final AbstractGate gate;
    private final int port;
    private boolean isOn;
    
    public InputJack(AbstractGate gate, int port) {
	assert(gate != null);
	
	if (0 <= port && port < gate.getNumInputs()) {
	    this.gate = gate;
	    this.port = port;
	    this.isOn = false;
	}
	else
	    throw new IllegalArgumentException("You may only attach a Jack to an existing port!");
    }
    
    public void setOn(boolean isOn) {
	if (this.isOn != isOn) {
	    this.isOn = isOn;
	    gate.setInput(port, isOn);
	}
    }

    @Override
    public AbstractGate getGate() {
	return gate;
    }

    @Override
    public int getPort() {
	return port;
    }

    @Override
    public void gateOutputChanged() {
    }

}
