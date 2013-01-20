package view;

import appstate.ApplicationState;
import model.circuit.Circuit;

public class CircuitView {
    protected final Circuit circuit;
    protected final ApplicationState applicationState;
    
    public CircuitView(Circuit circuit, ApplicationState applicationState) {
	this.circuit = circuit;
	this.applicationState = applicationState;
    }
}
