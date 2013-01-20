package model.circuit;

import static org.junit.Assert.*;
import model.gates.basic.And;
import model.gates.basic.Not;
import model.gates.io.Toggle;

import org.junit.Test;

public class CircuitTest {

    @Test
    public void testSimple() {
	Circuit circuit = new Circuit();
	Toggle toggle = new Toggle();
	Not not = new Not(1);
	
	circuit.addGate(toggle);
	circuit.addGate(not);
	circuit.addWire(toggle.getOutputJack(0), not.getInputJack(0));
	
	assertTrue("TOGGLE => 0 => NOT => 1", not.getOutput(0));
	toggle.flip();
	assertFalse("TOGGLE => 1 => NOT => 0", not.getOutput(0));
    }
    
    @Test
    public void test2Input() {
	Circuit circuit = new Circuit();
	Toggle toggle1 = new Toggle();
	Toggle toggle2 = new Toggle();
	And and = new And(2);
	
	circuit.addGate(toggle1);
	circuit.addGate(toggle2);
	circuit.addGate(and);
	circuit.addWire(toggle1.getOutputJack(0), and.getInputJack(0));
	circuit.addWire(toggle2.getOutputJack(0), and.getInputJack(1));
	
	assertFalse("0 AND 0 == 0", and.getOutput(0));
	toggle1.flip();
	assertFalse("1 AND 0 == 0", and.getOutput(0));
	toggle2.flip();
	assertTrue("1 AND 1 == 0", and.getOutput(0));
    }

}
