package brailleSimulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PinTest {

	private Pin a;
	
	@Before
	public void setUp() throws Exception {
		a = new Pin();
	}
	
	@Test
	public void testSetState() {
		a.setState(true);
		assertTrue(a.getState());
		a.setState(false);
		assertFalse(a.getState());
	}

	@Test
	public void testGetState() {
		boolean b;
		b = a.getState();
		assertFalse(b);
		a.setState(true);
		b = a.getState();
		assertTrue(b);
	}

}
