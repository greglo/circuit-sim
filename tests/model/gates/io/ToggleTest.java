package model.gates.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class ToggleTest {

    @Test
    public void test() {
	Toggle toggle = new Toggle();
	
	assertFalse("Toggles start off", toggle.getOutput(0));
	toggle.flip();
	assertTrue("One flip", toggle.getOutput(0));
	toggle.flip();
	assertFalse("Two flips", toggle.getOutput(0));
	
	for (int i = 0; i < 50; i++)
	    toggle.flip();
	assertFalse("50 more flips", toggle.getOutput(0));
    }

}
