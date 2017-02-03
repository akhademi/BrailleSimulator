package brailleSimulator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BrailleClientTest {
	BrailleClient a;
	BrailleClient b;
	BrailleClient z;
	@Before
	public void setUp() throws Exception {
		a = new BrailleClient (2,2);
		b = new BrailleClient (30,0);
		z = new BrailleClient (-19,1);
	}

	@Test
	public void testTranslate() {
		//a.translate("B2"); Use robot to take screenshot, 16 assert
		//a.translate("c"); Use robot to take screenshot, 16 assert
		//a.translate(" &");  use robot Nothing Will change so use same 16 assert as the last one
		//a.translate(" 1*");  use robot Nothing will change so use same 16 assert as the last one
		
		//b.translate("u"); Use robot to take screenshot, 8 assert
		//b.translate("st"); Use robot to take screenshot, 8 assert, no changes
		
		//MAKE SURE TO ASSERT ERROR MESSAGE IF THERE IS ERROR AND FORMAT THIS
		//LIKE BRAILLETEST
	}

	@Test
	public void testButtonEvent() {
		String hold;
		
		hold = b.getView().getTextField();
		assertEquals("",b.buttonEvent(11));
		assertEquals(hold,b.getView().getTextField());
		assertEquals("Button 10 was pressed\n",b.buttonEvent(10));
		assertEquals("Button 10 was pressed ",b.getView().getTextField());
		assertEquals("Button 9 was pressed\n",b.buttonEvent(9));
		assertEquals("Button 9 was pressed ",b.getView().getTextField());
		assertEquals("Button 8 was pressed\n",b.buttonEvent(8));
		assertEquals("Button 8 was pressed ",b.getView().getTextField());
		assertEquals("Button 7 was pressed\n",b.buttonEvent(7));
		assertEquals("Button 7 was pressed ",b.getView().getTextField());
		assertEquals("Button 6 was pressed\n",b.buttonEvent(6));
		assertEquals("Button 6 was pressed ",b.getView().getTextField());
		assertEquals("Button 5 was pressed\n",b.buttonEvent(5));
		assertEquals("Button 5 was pressed ",b.getView().getTextField());
		assertEquals("Button 4 was pressed\n",b.buttonEvent(4));
		assertEquals("Button 4 was pressed ",b.getView().getTextField());
		assertEquals("Button 3 was pressed\n",b.buttonEvent(3));
		assertEquals("Button 3 was pressed ",b.getView().getTextField());
		assertEquals("Button 2 was pressed\n",b.buttonEvent(2));
		assertEquals("Button 2 was pressed ",b.getView().getTextField());
		assertEquals("Button 1 was pressed\n",b.buttonEvent(1));
		assertEquals("Button 1 was pressed ",b.getView().getTextField());
		hold = b.getView().getTextField();
		assertEquals("",b.buttonEvent(0));
		assertEquals(hold,b.getView().getTextField());
		
		hold = a.getView().getTextField();
		assertEquals("",a.buttonEvent(0));
		assertEquals(hold,a.getView().getTextField());
		assertEquals("Button 2 was pressed\n",a.buttonEvent(2));
		assertEquals("Button 2 was pressed ",a.getView().getTextField());
		assertEquals("Button 1 was pressed\n",a.buttonEvent(1));
		assertEquals("Button 1 was pressed ",a.getView().getTextField());
		hold = a.getView().getTextField();
		assertEquals("",a.buttonEvent(3));
		assertEquals(hold,a.getView().getTextField());
		
		
		
		//USE THE ROBOT TO PRESS AND RELEASE F2 F1 F3 F4 and ETC
		// ASSERT THE TEXTBOX CONTAINS THE SENTENCE
		//assertEquals("Button 2 was pressed\n",a.buttonEvent(2)); IF IT IS VALID
		
		//FOR BUTTONS THAT ARRE NOT ACTIVATED have hold=getView().getTextField();
		//Use robot to press inactivated button
		//Then confirm message in textbox did not change 
		//assertEquals(hold,a.getView().getTextField());
	}

	@Test
	public void testClearAllCells() {
		//a.translate("1d");
		//a.clearAllCells();
		
		//b.translate("d");
		//b.clearAllCells();
		
		//LOOK AT BRAILLE TEST Instead of asserting all 0000000
		//Take pic with robot and assert all cell colour are red
	}

	@Test
	public void testSetCellPin() {
		//USE THE SAME TEST CASES FROM BRAILLETEST
		//INSTEAD OF ASSERT PINCONFIG, YOU NEED TO USE ROBOT
		//TAKE SCREEN SHOT AND CONFIRM THE PINCONFIG LIKE YOU DID FOR TRANSLATE
	}

}
