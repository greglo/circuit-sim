package application;

import javax.swing.JFrame;

import view.CircuitView;

import appstate.ApplicationState;

import model.circuit.Circuit;

/**
 * The program entry point. Create the program components, and display
 * @author Greg
 *
 */
public class ApplicationFrame extends JFrame {
    private static final long serialVersionUID = 1894475960276282851L;

    protected final Circuit circuit;
    protected final ApplicationState applicationState;
    protected final CircuitView circuitView;
    
    
    public ApplicationFrame() {
	circuit = new Circuit();
	applicationState = new ApplicationState(circuit);
	circuitView = new CircuitView(circuit, applicationState);
    }
    
    
    /**
     * Program entry point. Create a new ApplicationFrame and display it
     * @param args
     */
    public static void main(String[] args) {
	ApplicationFrame applicationFrame = new ApplicationFrame();
	applicationFrame.setVisible(true);
    }

}
