package model.gates.basic;

import static org.junit.Assert.*;

import model.gates.AbstractGate;

import org.junit.Test;

public class NandTest {

    @Test
    public void test() {
	AbstractGate nand = new Nand(2);

	assertTrue("0 NAND 0 == 1", nand.getOutput(0));
	nand.setInput(0, true);
	assertTrue("1 NAND 0 == 1", nand.getOutput(0));
	nand.setInput(1, true);
	assertFalse("1 NAND 1 == 0", nand.getOutput(0));
	nand.setInput(1, false);
	assertTrue("0 NAND 1 == 1", nand.getOutput(0));
    }

}
