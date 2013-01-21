package model.circuit;

import static org.junit.Assert.*;

import java.util.Iterator;

import model.gates.AbstractGate;
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
    public void test2Inputs() {
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

    @Test
    public void testRemoveWire() {
	Circuit circuit = new Circuit();
	Toggle toggle = new Toggle();
	Not not = new Not(1);

	circuit.addGate(toggle);
	circuit.addGate(not);
	circuit.addWire(toggle.getOutputJack(0), not.getInputJack(0));

	// Remove by output jack (if it doesn't work, line after will throw
	// exception)
	circuit.removeWire(toggle.getOutputJack(0));
	circuit.addWire(toggle.getOutputJack(0), not.getInputJack(0));

	// Remove by input jack (if it doesn't work, line after will throw
	// exception)
	circuit.removeWire(not.getInputJack(0));
	circuit.addWire(toggle.getOutputJack(0), not.getInputJack(0));

	assertTrue(not.getOutput(0));
    }
    
    @Test
    public void testRemoveWireNull() {
	Circuit circuit = new Circuit();
	circuit.removeWire(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveWireException() {
	Circuit circuit = new Circuit();
	Toggle toggle = new Toggle();
	And and = new And(2);

	circuit.addGate(toggle);
	circuit.addGate(and);
	circuit.addWire(toggle.getOutputJack(0), and.getInputJack(0));
	// Should throw an exception on next line
	circuit.addWire(toggle.getOutputJack(0), and.getInputJack(1));
    }

    @Test
    public void testRemoveGate() {
	Circuit circuit = new Circuit();
	Toggle toggle = new Toggle();
	Not not1 = new Not(1);
	Not not2 = new Not(1);

	circuit.addGate(toggle);
	circuit.addGate(not1);
	circuit.addGate(not2);
	circuit.addWire(toggle.getOutputJack(0), not1.getInputJack(0));
	assertTrue(not1.getOutput(0));

	// Removing `not1` should also remove the wires, so we can add a wire
	// toggle => not2
	circuit.removeGate(not1);
	circuit.addWire(toggle.getOutputJack(0), not2.getInputJack(0));
	toggle.flip();
	assertFalse(not2.getOutput(0));
    }
    
    @Test
    public void testRemoveGateNull() {
	Circuit circuit = new Circuit();
	circuit.removeGate(null);
    }
    
    @Test 
    public void testNumGates() {
	Circuit circuit = new Circuit();
	assertEquals(circuit.numGates(), 0);
	circuit.addGate(new Not());
	assertEquals(circuit.numGates(), 1);
	circuit.addGate(new And());
	assertEquals(circuit.numGates(), 2);
    }
    
    @Test public void testGetGates() {
	Circuit circuit = new Circuit();
	Iterator<AbstractGate> gateIter = circuit.getGates();
	
	assertFalse("Circuit initialised with no gates", gateIter.hasNext());
	
	circuit.addGate(new Not());
	gateIter = circuit.getGates();
	assertTrue("Gates returned in iterator", gateIter.hasNext());
	assertEquals("Correct gates returned", gateIter.next().getClass(), Not.class);
    }

}
