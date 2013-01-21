package model.gates;

/**
 * Jacks are points on a gate where wires are attached, and are themselves
 * attached to a Gate at a port number
 * 
 * @author Greg
 * 
 */
public class Jack {
    private final AbstractGate gate; // Gate the Jack is attached to
    private final int port; // Port on the Gate the Jack is attached
    private boolean isOn; // Whether the Jack is on or not

    /**
     * Create a new Jack attached to a gate at a specific port
     * 
     * @param gate
     *            Gate to be attached to
     * @param port
     *            Port to be attached to
     */
    public Jack(AbstractGate gate, int port) {
	this.gate = gate;
	this.port = port;
	isOn = false;
    }

    /**
     * 
     * @return The attached Gate
     */
    public AbstractGate getGate() {
	return gate;
    }

    /**
     * 
     * @return The attached port number
     */
    public int getPort() {
	return port;
    }

    /**
     * 
     * @return Whether the Jack is on or not
     */
    protected boolean isOn() {
	return isOn;
    }

    /**
     * Set whether the point is on or not
     * 
     * @param isOn
     */
    protected void setOn(boolean isOn) {
	this.isOn = isOn;
    }
}
