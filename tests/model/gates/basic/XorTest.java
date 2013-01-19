package model.gates.basic;

import static org.junit.Assert.*;

import model.gates.AbstractGate;

import org.junit.Test;

public class XorTest {

    @Test
    public void test() {
	AbstractGate xor = new Xor(2);
	
	assertFalse("0 XOR 0 == 0", xor.getOutput(0));
	xor.setInput(0, true);
	assertTrue("1 XOR 0 == 1", xor.getOutput(0));
	xor.setInput(1, true);
	assertFalse("1 XOR 1 == 0", xor.getOutput(0));
	xor.setInput(1, false);
	assertTrue("0 XOR 1 == 1", xor.getOutput(0));
    }

}
