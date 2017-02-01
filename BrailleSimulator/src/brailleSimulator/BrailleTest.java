package brailleSimulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BrailleTest {

	Braille a;
	Braille b;
	@Before
	public void setUp() throws Exception {
		a = new Braille (2,2);
		b = new Braille (30,0);
	}

	@Test
	public void testTranslate() {
		String [] c = {"11000000","10000000"};
		assertTrue(a.translate("C1"));
		assertArrayEquals(c,a.getCellConfig());
		
		String [] d = {"00000000","01110100"};
		assertTrue(a.translate(" w"));
		assertArrayEquals(d,a.getCellConfig());
		
		assertFalse(a.translate(" %"));
		assertArrayEquals(d,a.getCellConfig());
		
		assertFalse(a.translate(" 1 "));
		assertArrayEquals(d,a.getCellConfig());
	}

	@Test
	public void testGetCellConfig() {
		String [] c = {"11000000","10000000"};
		String [] d;
		a.translate("C1");
		d = a.getCellConfig();
		assertArrayEquals(c,d);
		
		String [] e = {"00000000"};
		String [] f;
		f = b.getCellConfig();
		assertArrayEquals(e,f);
	}

	@Test
	public void testButtonEvent() {
		String c;
		String d = "";
		c = b.buttonEvent(11);
		assertEquals(d,c);
		
		d = "Button 10 was pressed\n";
		c = b.buttonEvent(10);
		assertEquals(d,c);
	}

	@Test
	public void testGetNumCells() {
		int c = 2;
		int d = a.getNumCells();
		assertEquals(c,d);
		int e = 1;
		int f = b.getNumCells();
		assertEquals(e,f);
	}

	@Test
	public void testClearAllCells() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCellPin() {
		fail("Not yet implemented");
	}

}
