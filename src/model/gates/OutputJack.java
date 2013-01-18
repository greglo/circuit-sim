package model.gates;

import java.util.ArrayList;
import java.util.List;

public class OutputJack implements Jack {
    private final AbstractGate gate;
    private final int port;
    private final List<OutputJackListener> listeners = new ArrayList<OutputJackListener>();
    private boolean wasOn;
    
    public OutputJack(AbstractGate gate, int port) {
	assert(gate != null);
	
	if (0 <= port && port < gate.getNumOutputs()) {
	    this.gate = gate;
	    this.port = port;
	    this.wasOn = isOn();
	}
	else
	    throw new IllegalArgumentException("You may only attach a Jack to an existing port!");
    }
    
    public boolean isOn() {
	return gate.getOutput(port);
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
	if (isOn() != wasOn) {
	    fireStatusChanged();
	    wasOn = isOn();
	}
    }
    
    public void addListener(OutputJackListener l) {
	listeners.add(l);
    }
    
    public void removeListener(OutputJackListener l) {
	listeners.remove(l);
    }
    
    private void fireStatusChanged() {
	boolean isOn = isOn();
	for (OutputJackListener l : listeners)
	    l.jackStatusChanged(this, isOn);
    }

}
