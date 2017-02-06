package brailleSimulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BrailleTest {

	Braille a;
	Braille b;
	Braille z;
	Braille num;
	@Before
	public void setUp() throws Exception {
		a = new Braille (2,2);
		b = new Braille (30,0);
		z = new Braille (-19,26);
		num = new Braille (0,11);
	}

	@Test
	public void testTranslate() {
		String [] c = {"11000000","10000000"};
		try {
			a.translate("C1");
			assertArrayEquals(c,a.getCellConfig());
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		String [] d = {"01110100", "00000000"};
		try {
			a.translate("w");
			assertArrayEquals(d,a.getCellConfig());
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		try {
			a.translate(" %");
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			assertArrayEquals(d,a.getCellConfig());
			assertEquals("Message contains invalid symbols", e.getMessage());
		}
		
		try {
			a.translate(" 1%");
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			assertArrayEquals(d,a.getCellConfig());
			assertEquals("Message length exceeds number of cells", e.getMessage());
		}
		
		
		String [] f = {"10000000"};
		try {
			b.translate("a");
			assertArrayEquals(f,b.getCellConfig());
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
	
		try {
			b.translate("ab");
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e) {
			assertArrayEquals(f,b.getCellConfig());
			assertEquals("Message length exceeds number of cells", e.getMessage());
		}
		
		String [] g = {"10000000","10100000","11000000","11010000","10010000","11100000",
				"11110000","10110000","01100000","01110000","10001000","10101000",
				"11001000","11011000","10011000","11101000","11111000","10111000",
				"01101000","01111000","10001100","10101100","01110100","11001100","11011100",
				"10011100"};
		try {
			z.translate("abcdefghijklmnopqrstuvwxyz");
			assertArrayEquals(g,z.getCellConfig());
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		try {
			z.translate("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			assertArrayEquals(g,z.getCellConfig());
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		String [] h = {"00000000", "10000000","10100000","11000000","11010000","10010000","11100000",
				"11110000","10110000","01100000","01110000"};
		try {
			num.translate(" 1234567890");
			assertArrayEquals(h,num.getCellConfig());
		} catch (InvalidInputException e) {
			fail("InvalidInputException should not have been thrown.");
		}
		
	}

	@Test
	public void testGetCellConfig() {
		String [] c = {"11000000","10000000"};
		String [] d;
		try {
			a.translate("C1");
		} catch (InvalidInputException e1) {
		}
		
		d = a.getCellConfig();
		assertArrayEquals(c,d);
		
		try {
			a.translate("C13232");
		} catch (InvalidInputException e1) {
		}
		
		d = a.getCellConfig();
		assertArrayEquals(c,d);
		
		String [] e = {"00000000"};
		String [] f;
		f = b.getCellConfig();
		assertArrayEquals(e,f);
	}

	@Test
	public void testButtonEvent() {

		assertEquals("",b.buttonEvent(11));
		assertEquals("",b.buttonEvent(0));
		assertEquals("Button 10 was pressed\n",b.buttonEvent(10));
		assertEquals("Button 9 was pressed\n",b.buttonEvent(9));
		assertEquals("Button 8 was pressed\n",b.buttonEvent(8));
		assertEquals("Button 7 was pressed\n",b.buttonEvent(7));
		assertEquals("Button 6 was pressed\n",b.buttonEvent(6));
		assertEquals("Button 5 was pressed\n",b.buttonEvent(5));
		assertEquals("Button 4 was pressed\n",b.buttonEvent(4));
		assertEquals("Button 3 was pressed\n",b.buttonEvent(3));
		assertEquals("Button 2 was pressed\n",b.buttonEvent(2));
		assertEquals("Button 1 was pressed\n",b.buttonEvent(1));
		
		assertEquals("",a.buttonEvent(0));
		assertEquals("",a.buttonEvent(3));
		assertEquals("Button 2 was pressed\n",a.buttonEvent(2));
		assertEquals("Button 1 was pressed\n",a.buttonEvent(1));
		
		assertEquals("",z.buttonEvent(0));
		assertEquals("",z.buttonEvent(1));
	}

	@Test
	public void testGetNumCells() {
		int c = 2;
		int d = a.getNumCells();
		assertEquals(c,d);
		int e = 1;
		int f = b.getNumCells();
		assertEquals(e,f);
		
		int g = 26;
		int h = z.getNumCells();
		assertEquals(g,h);
	}

	
	@Test
	public void testClearAllCells() {
		String [] c = {"00000000","00000000"};
		String [] d = {"00000000"};
		a.clearAllCells();
		assertArrayEquals(c,a.getCellConfig());

		try {
			a.translate("te");
			a.clearAllCells();
			assertArrayEquals(c,a.getCellConfig());
		} catch (InvalidInputException e) {
			fail("Nothing was translated. Unclear whether reset worked.");
		}
		
		
		try {
			b.translate("1");
			b.clearAllCells();
			assertArrayEquals(d,b.getCellConfig());
		} catch (InvalidInputException e) {
			fail("Nothing was translated. Unclear whether reset worked.");
		}
		
		
	}

	@Test
	public void testSetCellPin() {
		String [] c = {"11010000","10100000"};
		String [] d = {"11010000","10000000"};
		String [] e = {"11010000","10000001"};
		String [] f = {"00001000"};
		try {
			a.translate("Db");
			a.setCellPin(1, 1, true);
			assertArrayEquals(c,a.getCellConfig());
		} catch (InvalidInputException e1) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		
		try {
			a.setCellPin(2, 3, false);
			assertArrayEquals(d,a.getCellConfig());
		} catch (InvalidInputException e1) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		try {
			a.setCellPin(2, 8, true);
			assertArrayEquals(e,a.getCellConfig());
		} catch (InvalidInputException e1) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		try {
			a.setCellPin(1, 10, true);
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e1) {
			assertArrayEquals(e,a.getCellConfig());
			assertEquals("Invalid pin number", e1.getMessage());
		}
		
		
		try {
			a.setCellPin(-1, 3, true);
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e1) {
			assertArrayEquals(e,a.getCellConfig());
			assertEquals("Invalid cell number", e1.getMessage());
		}

		try {
			b.setCellPin(1, 5, true);
			assertArrayEquals(f,b.getCellConfig());
		} catch (InvalidInputException e1) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		try {
			b.setCellPin(1, 5, true);
			assertArrayEquals(f,b.getCellConfig());
		} catch (InvalidInputException e1) {
			fail("InvalidInputException should not have been thrown.");
		}
		
		try {
			b.setCellPin(-1, 10, true);
			fail("InvalidInputException should have been thrown.");
		} catch (InvalidInputException e1) {
			assertArrayEquals(f,b.getCellConfig());
			assertEquals("Invalid cell number", e1.getMessage());
		}
		
	}

}
