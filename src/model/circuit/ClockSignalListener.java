package model.circuit;

/**
 * Interface for any AbstractGate which listens to the clock signal
 * @author Greg
 *
 */
public interface ClockSignalListener {
    public void updateTime(long time);
}
