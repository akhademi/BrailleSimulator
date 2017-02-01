package brailleSimulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

	private Cell a;
	
	@Before
	public void setUp() throws Exception {
		a = new Cell ();
	}

	@Test
	public void testSetPinConfig() {
		a.setPinConfig("30000000");
		assertEquals("00000000",a.getPinConfig());
		a.setPinConfig("100000000");
		assertEquals("00000000",a.getPinConfig());
		a.setPinConfig("10010001");
		assertEquals("10010001",a.getPinConfig());
		a.setPinConfig("1001001");
		assertEquals("10010001",a.getPinConfig());
	}

	@Test
	public void testGetPinConfig() {
		String b = a.getPinConfig();
		assertEquals("00000000",b);
		a.setPinConfig("11111111");
		b = a.getPinConfig();
		assertEquals("11111111",b);
		a.setPinConfig("111111110");
		b = a.getPinConfig();
		assertEquals("11111111",b);
	}

	@Test
	public void testSetPin() {
		a.setPin(1, true);
		assertEquals("10000000",a.getPinConfig());
		a.setPinConfig("11110000");
		a.setPin(4, false);
		assertEquals("11100000",a.getPinConfig());
		a.setPin(9, true);
		assertEquals("11100000",a.getPinConfig());
		a.setPin(-1, true);
		assertEquals("11100000",a.getPinConfig());
	}

}
