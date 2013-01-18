package model.gates;

public interface OutputJackListener {
    public void jackStatusChanged(OutputJack jack, boolean isOn);
}
