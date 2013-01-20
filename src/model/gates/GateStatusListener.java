package model.gates;

/**
 * Interface for classes which need notification when gates change their outputs
 * 
 * @author Greg
 * 
 */
public interface GateStatusListener {
    /**
     * A Gate has changed its output
     * @param gate	The gate
     */
    public void gateOutputChanged(AbstractGate gate);
}
