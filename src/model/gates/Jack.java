package model.gates;

public class Jack {
    private final AbstractGate gate;
    private final int port;
    private boolean isOn;
    
    public Jack(AbstractGate gate, int port) {
	this.gate = gate;
	this.port = port;
	isOn = false;
    }
    
    public AbstractGate getGate() {
	return gate;
    }
    
    public int getPort() {
	return port;
    }
    
    public boolean isOn() {
	return isOn;
    }
    
    public void setOn(boolean isOn) {
	this.isOn = isOn;
    }
}
