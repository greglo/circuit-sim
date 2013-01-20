package model.circuit;

/**
 * Interface for any AbstractGate which requires the system time to determine
 * its behaviour
 * 
 * @author Greg
 * 
 */
public interface ClockSignalListener {

    /**
     * Notifies a ClockSignalListener of the current system time
     * 
     * @param time
     *            The current system time
     */
    public void updateTime(long time);
}
