package model.gates.basic;

import static org.junit.Assert.*;

import model.gates.AbstractGate;

import org.junit.Test;

public class OrTest {

    @Test
    public void test() {
	AbstractGate or = new Or(2);
	
	assertFalse("0 OR 0 == 0", or.getOutput(0));
	or.setInput(0, true);
	assertTrue("1 OR 0 == 1", or.getOutput(0));
	or.setInput(1, true);
	assertTrue("1 OR 1 == 1", or.getOutput(0));
	or.setInput(1, false);
	assertTrue("0 OR 1 == 1", or.getOutput(0));
    }

}
