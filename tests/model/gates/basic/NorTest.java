package model.gates.basic;

import static org.junit.Assert.*;

import model.gates.AbstractGate;

import org.junit.Test;

public class NorTest {

    @Test
    public void test() {
	AbstractGate Nor = NorGateFactory.INSTANCE.newGate(2);

	assertTrue("0 NOR 0 == 1", Nor.getOutput(0));
	Nor.setInput(0, true);
	assertFalse("1 NOR 0 == 0", Nor.getOutput(0));
	Nor.setInput(1, true);
	assertFalse("1 NOR 1 == 0", Nor.getOutput(0));
	Nor.setInput(1, false);
	assertFalse("0 NOR 1 == 0", Nor.getOutput(0));
    }

}
