package model.gates.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClockTest {

    @Test
    public void test() {
	Clock clock = new Clock(5);
	
	assertFalse("Clocks start off", clock.getOutput(0));
	clock.updateTime(clock.getPeriod());
	assertTrue("They are on after clock.getPeriod() ticks", clock.getOutput(0));
    }

}
