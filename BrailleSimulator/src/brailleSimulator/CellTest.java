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
		
		
		try {
			a.setPinConfig("30000000w");
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			
			assertEquals("00000000",a.getPinConfig());
			assertEquals("Pin configuration length does not equal to 8", e.getMessage());
		}
		
		try {
			a.setPinConfig("1111111");
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			assertEquals("00000000",a.getPinConfig());
			assertEquals("Pin configuration length does not equal to 8", e.getMessage());
		}
		
		try {
			a.setPinConfig("10010001");
			assertEquals("10010001",a.getPinConfig());
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		
		try {
			a.setPinConfig("10000003");
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			assertEquals("10010001",a.getPinConfig());
			assertEquals("Pin configuration length does not consist of only 1 and 0", e.getMessage());
		}
	
		
		try {
			a.setPinConfig("11101111");
			assertEquals("11101111",a.getPinConfig());
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
	}

	@Test
	public void testGetPinConfig() {
		String b = a.getPinConfig();
		assertEquals("00000000",b);
		try {
			a.setPinConfig("10101111");
			b = a.getPinConfig();
			assertEquals("10101111",b);
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		try {
			a.setPinConfig("11111111110");
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			b = a.getPinConfig();
			assertEquals("10101111",b);
		}
		
		try {
			a.setPinConfig("1111111q");
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			b = a.getPinConfig();
			assertEquals("10101111",b);
		}
	}

	@Test
	public void testSetPin() {
		
		
		try {
			a.setPin(1, true);
			assertEquals("10000000",a.getPinConfig());
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
		try {
			a.setPinConfig("11110000");
			a.setPin(4, false);
			assertEquals("11100000",a.getPinConfig());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			fail("InvalidInputException should not have been thrown.");
		}
		
		try {
			a.setPin(4, false);
			assertEquals("11100000",a.getPinConfig());
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			fail("InvalidInputException should not have been thrown.");
		}
		
		try {
			a.setPin(9, true);
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			assertEquals("Invalid pin number", e.getMessage());
			assertEquals("11100000",a.getPinConfig());
		}

		try {
			a.setPin(-1, true);
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			assertEquals("Invalid pin number", e.getMessage());
			assertEquals("11100000",a.getPinConfig());
		}
	
	}

}
