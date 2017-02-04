package brailleSimulator;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import org.junit.Before;
import org.junit.Test;
import java.awt.Point;


public class BrailleClientTest {
	BrailleClient a;
	BrailleClient b;
	BrailleClient z;
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final int WIDTH = (int) (screenSize.getWidth()/1.1);
	public final int HEIGHT = (int) (screenSize.getHeight()/2);
	public final int HEIGHTPERCELL = (int) (screenSize.getHeight()/4);

	
	
	@Before
	public void setUp() throws Exception {
		a = new BrailleClient (2,2);
		b = new BrailleClient (30,0);
		z = new BrailleClient (-19,1);
	}

	@Test
	public void testTranslate() {
		a = new BrailleClient(2,2);
		
		Point p = new Point(0, 0);
	    SwingUtilities.convertPointToScreen(p, a.view.panel);

	    //Get the region with wiht and heighht of panel and 
	    // starting coordinates of p.x and p.y
	    Rectangle region = a.view.panel.getBounds();
	    region.x = p.x;
	    region.y = p.y;

	    //Get screen capture over the area of region
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	    BufferedImage bi = null;
	 
	  //a.translate("B2"); Use robot to take screenshot, 16 assert
    	try {
			a.translate("B2");
		} catch (InvalidInputException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	    	
	    	bi = null;
	        bi = new Robot().createScreenCapture( region );
	        File outputfile = new File("saved.png");
		    try {
				ImageIO.write(bi, "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    } catch (AWTException ex) {
	    }
	    
	    int offset = 10;
	    Color cellColor[][] = new Color [a.getNumCells()][8];
	    // expected color for B2 
	    Color expectedCell[][]= new Color [][]  {
	    		{Color.black, Color.RED, Color.black , Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED },
	            { Color.black, Color.RED, Color.black,Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED}
	    };
	    for (int j=0;j<a.getNumCells();j++) {
		    for (int i = 0; i<8; i++) {
		    	int x = a.getView().panel.xCoordinate[j][i]+offset;
		    	int y = a.getView().panel.yCoordinate[j][i]+offset;
		    	cellColor[j][i] = new Color(bi.getRGB(x, y));
		    	//System.out.println("j=" + j + " i=" + i + " x=" + x + " y=" + y + " color = "+ cellColor[j][i]);
		    }
	    }
	    assertArrayEquals(cellColor, expectedCell);

	    /*
	    // we know a is translation of B2
	    for (int i = 0 ; i< a.getNumCells(); i++) {
	    	for (int j = 0; j<8 ; j++) {
	    		assertEquals(cellColor[i][j],expectedCell[i][j]);
	    	}
	    }
	    */
	   
	
	  //a.translate("c"); Use robot to take screenshot, 16 assert
	try {
		a.translate("c");
	} catch (InvalidInputException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    try {
    	
    	bi = null;
        bi = new Robot().createScreenCapture( region );
        File outputfile = new File("saved.png");
	    try {
			ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
    } catch (AWTException ex) {}
    
	int offset1 = 10;
    Color cellColor1[][] = new Color [a.getNumCells()][8];
    // expected color for C
    Color expectedCell1[][]= new Color [][]  {
    		{Color.black, Color.black, Color.RED , Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED },
            { Color.RED, Color.RED, Color.RED,Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED}
    };
    for (int j=0;j<a.getNumCells();j++) {
	    for (int i = 0; i<8; i++) {
	    	int x = a.getView().panel.xCoordinate[j][i]+offset1;
	    	int y = a.getView().panel.yCoordinate[j][i]+offset1;
	    	cellColor1[j][i] = new Color(bi.getRGB(x, y));
	    	//System.out.println("j=" + j + " i=" + i + " x=" + x + " y=" + y + " color = "+ cellColor[j][i]);
	    }
    
	    
	    }
    assertArrayEquals(cellColor1, expectedCell1);
    

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
		
	    Robot r;
		try {
			 r = new Robot();
			 r.keyPress(KeyEvent.VK_F1);
			 assertEquals("Button 1 was pressed\n",a.buttonEvent(1));
			 r.keyRelease(KeyEvent.VK_F1);  
			 r.keyPress(KeyEvent.VK_F2);
			 assertEquals("Button 2 was pressed\n",a.buttonEvent(2));
			 r.keyRelease(KeyEvent.VK_F2); 
			 
			//FOR BUTTONS THAT ARRE NOT ACTIVATED have hold=getView().getTextField();
			//Use robot to press inactivated button
			//Then confirm message in textbox did not change 
			//assertEquals(hold,a.getView().getTextField());
			 
			 hold = a.getView().getTextField();
			 r.keyPress(KeyEvent.VK_F3);
			 assertEquals("",a.buttonEvent(3));
			 assertEquals(hold,a.getView().getTextField());
			 r.keyRelease(KeyEvent.VK_F3);  
			 hold = a.getView().getTextField();
			 r.keyPress(KeyEvent.VK_F4);
			 assertEquals("",a.buttonEvent(4));
			 assertEquals(hold,a.getView().getTextField());
			 r.keyRelease(KeyEvent.VK_F4); 
			 hold = a.getView().getTextField();
			 r.keyPress(KeyEvent.VK_F5);
			 assertEquals("",a.buttonEvent(5));
			 assertEquals(hold,a.getView().getTextField());
			 r.keyRelease(KeyEvent.VK_F5);  
			 hold = a.getView().getTextField();
			 r.keyPress(KeyEvent.VK_F6);
			 assertEquals("",a.buttonEvent(6));
			 assertEquals(hold,a.getView().getTextField());
			 r.keyRelease(KeyEvent.VK_F6);  
			 hold = a.getView().getTextField();
			 r.keyPress(KeyEvent.VK_F7);
			 assertEquals("",a.buttonEvent(7));
			 assertEquals(hold,a.getView().getTextField());
			 r.keyRelease(KeyEvent.VK_F7); 
			 hold = a.getView().getTextField();
			 r.keyPress(KeyEvent.VK_F8);
			 assertEquals("",a.buttonEvent(8));
			 assertEquals(hold,a.getView().getTextField());
			 r.keyRelease(KeyEvent.VK_F8);
			 hold = a.getView().getTextField();
			 r.keyPress(KeyEvent.VK_F9);
			 assertEquals("",a.buttonEvent(9));
			 assertEquals(hold,a.getView().getTextField());
			 r.keyRelease(KeyEvent.VK_F9);
			 hold = a.getView().getTextField();
			 r.keyPress(KeyEvent.VK_F10);
			 assertEquals("",a.buttonEvent(10));
			 assertEquals(hold,a.getView().getTextField());
			 r.keyRelease(KeyEvent.VK_F10);  
		} catch (AWTException e) {
			fail("Was not the expected button");
		}
	   
	
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

