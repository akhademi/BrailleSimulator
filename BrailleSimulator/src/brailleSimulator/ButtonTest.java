package brailleSimulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ButtonTest {

private Button a;
private Button b;
private Button c;
	
	@Before
	public void setUp() throws Exception {
		a = new Button(12);
		b = new Button(-12);
		c = new Button(0);
	}

	@Test
	public void testGetButtonNum() {
		assertEquals(12,a.getButtonNum());
		assertEquals(-12,b.getButtonNum());
		assertEquals(0,c.getButtonNum());
	}

}
