package model.gates.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class SwitchTest {

    @Test
    public void test() {
	Switch switchGate = new Switch();
	
	assertFalse("Switches start off", switchGate.getOutput(0));
	switchGate.flip();
	assertTrue("One flip", switchGate.getOutput(0));
	switchGate.flip();
	assertFalse("Two flips", switchGate.getOutput(0));
	
	for (int i = 0; i < 50; i++)
	    switchGate.flip();
	assertFalse("50 more flips", switchGate.getOutput(0));
    }

}
