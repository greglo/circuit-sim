package model.gates;

public interface Jack extends AbstractGateListener {
    public AbstractGate getGate();
    public int getPort();
}
