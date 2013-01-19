package model.gates.basic;

import static org.junit.Assert.*;

import model.gates.AbstractGate;

import org.junit.Test;

public class NotTest {

    @Test
    public void testOneInput() {
	AbstractGate not = new Not(1);
	
	not.setInput(0, false);
	assertTrue("!false == true", not.getOutput(0));
	
	not.setInput(0, true);
	assertFalse("!true == false", not.getOutput(0));
    }

}
