package appstate;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.circuit.Circuit;


/**
 * Store the state of the application
 * @author Greg
 *
 */
public class ApplicationState {
    protected final Circuit circuit;
    protected final Timer systemClock;
    protected int systemClockRate = 500;

    public ApplicationState(Circuit circuit) {
	this.circuit = circuit;
	
	systemClock = new Timer(systemClockRate, new SystemClockListener());
    }
    
    public void setSystemClockRate(int rate) {
	if (200 <= rate) {
	    systemClockRate = rate;
	    systemClock.setDelay(systemClockRate);
	}
	else
	    throw new IllegalArgumentException("The system clock delay must be at least 200ms!");
    }
    
    public int getSystemClockRate() {
	return systemClockRate;
    }
    
    private class SystemClockListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    circuit.systemClockTick();
	}
	
    }
}
