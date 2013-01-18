package model.gates.basic;

import static org.junit.Assert.*;

import model.gates.AbstractGate;

import org.junit.Test;

public class AndTest {

    @Test
    public void test() {
	AbstractGate and = AndGateFactory.INSTANCE.newGate(2);
	
	assertFalse("0 AND 0 == 0", and.getOutput(0));
	and.setInput(0, true);
	assertFalse("1 AND 0 == 0", and.getOutput(0));
	and.setInput(1, true);
	assertTrue("1 AND 1 == 1", and.getOutput(0));
	and.setInput(1, false);
	assertFalse("0 AND 1 == 0", and.getOutput(0));
    }

}
