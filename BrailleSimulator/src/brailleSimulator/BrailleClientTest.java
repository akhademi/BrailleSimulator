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
		
		a = new BrailleClient (2,2);
		Point p = new Point(0, 0);
	    SwingUtilities.convertPointToScreen(p, a.getView().getPanel());
	    Rectangle region = a.getView().getPanel().getBounds();
	    region.x = p.x;
	    region.y = p.y;

	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
		}
	    BufferedImage bi = null;

    	try {
			a.translate("B2");
		} catch (InvalidInputException e2) {
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
	       
	    } catch (AWTException ex) {}
	   
	    Color cellColor[][] = new Color [a.getNumCells()][8];
	    Color expectedCell[][]= new Color [][]  {
	    		{Color.black, Color.RED, Color.black , Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED },
	            { Color.black, Color.RED, Color.black,Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED}
	    };
	    for (int j=0;j<a.getNumCells();j++) {
		    for (int i = 0; i<8; i++) {
		    	int x = a.getView().getPanel().getXCoordinate()[j][i];
		    	int y = a.getView().getPanel().getYCoordinate()[j][i];
		    	cellColor[j][i] = new Color(bi.getRGB(x, y));
		    	
		    }
		 
	    }
	    assertArrayEquals(cellColor, expectedCell);
	    try {
			a.translate("&");
		} catch (InvalidInputException e2) {
			assertEquals("Message contains invalid symbols", e2.getMessage());
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
	       
	    } catch (AWTException ex) {}
	    assertArrayEquals(cellColor, expectedCell);
	    
	    
  
 
	try {
		a.translate("c");
	} catch (InvalidInputException e2) {
		e2.printStackTrace();
	}
	
    try {
    	bi = null;
        bi = new Robot().createScreenCapture( region );
      
    } catch (AWTException ex) {}
    
	
    Color cellColor1[][] = new Color [a.getNumCells()][8];
    Color expectedCell1[][]= new Color [][]  {
    		{Color.black, Color.black, Color.RED , Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED },
            { Color.RED, Color.RED, Color.RED,Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED}
    };
    for (int j=0;j<a.getNumCells();j++) {
	    for (int i = 0; i<8; i++) {
	    	int x = a.getView().getPanel().getXCoordinate()[j][i];
	    	int y = a.getView().getPanel().getYCoordinate()[j][i];
	    	cellColor1[j][i] = new Color(bi.getRGB(x, y));
	    }
   
	}
    assertArrayEquals(cellColor1, expectedCell1);
  
    	b = new BrailleClient (30,0);
    	
    try {
		b.translate("u");
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
    } catch (AWTException ex) {}
    
	
    Color cellColor2[][] = new Color [b.getNumCells()][8];
    Color expectedCell2[][]= new Color [][]  {
    		{Color.black, Color.RED, Color.RED , Color.RED ,Color.black,Color.black,Color.RED ,Color.RED},
    };
    for (int j=0;j<b.getNumCells();j++) {
	    for (int i = 0; i<8; i++) {
	    	int x = b.getView().getPanel().getXCoordinate()[j][i];
	    	int y = b.getView().getPanel().getYCoordinate()[j][i];
	    	cellColor2[j][i] = new Color(bi.getRGB(x, y));
	    	
	    }
    }
    assertArrayEquals(cellColor2, expectedCell2);
    try {
		b.translate("1*");
	} catch (InvalidInputException e2) {
		
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
    } catch (AWTException ex) {}
    	assertArrayEquals(cellColor2, expectedCell2);
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
	
	    Robot r;
		try {
			 r = new Robot();
			 r.keyPress(KeyEvent.VK_F1);
			 assertEquals("Button 1 was pressed\n",a.buttonEvent(1));
			 r.keyRelease(KeyEvent.VK_F1);  
			 r.keyPress(KeyEvent.VK_F2);
			 assertEquals("Button 2 was pressed\n",a.buttonEvent(2));
			 r.keyRelease(KeyEvent.VK_F2); 

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
		a = new BrailleClient(2,2);
		Point p = new Point(0, 0);
	    SwingUtilities.convertPointToScreen(p, a.getView().getPanel());
	    Rectangle region = a.getView().getPanel().getBounds();
	    region.x = p.x;
	    region.y = p.y;

	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	    BufferedImage bi = null;
    	try {
			a.translate("1d");
			a.clearAllCells();
		} catch (InvalidInputException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    try {
	    	bi = null;
	        bi = new Robot().createScreenCapture( region );
	    } catch (AWTException ex) {}
	    
	    Color cellColor[][] = new Color [a.getNumCells()][8];
	    Color expectedCell[][]= new Color [][]  {
	    		{Color.RED, Color.RED, Color.RED , Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED },
	            { Color.RED, Color.RED, Color.RED,Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED}
	    };
	    for (int j=0;j<a.getNumCells();j++) {
		    for (int i = 0; i<8; i++) {
		    	int x = a.getView().getPanel().getXCoordinate()[j][i];
		    	int y = a.getView().getPanel().getYCoordinate()[j][i];
		    	cellColor[j][i] = new Color(bi.getRGB(x, y));
		    }
	    }
	    assertArrayEquals(cellColor, expectedCell);
	    
	    b = new BrailleClient (30,0);
	    try {
			b.translate("d");
			b.clearAllCells();
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
	         
	    } catch (AWTException ex) {}
	    
	    Color cellColor1[][] = new Color [b.getNumCells()][8];
	    Color expectedCell1[][]= new Color [][]  {
	    		{Color.RED, Color.RED, Color.RED , Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED },
	            
	    };
	    for (int j=0;j<b.getNumCells();j++) {
		    for (int i = 0; i<8; i++) {
		    	int x = b.getView().getPanel().getXCoordinate()[j][i];
		    	int y = b.getView().getPanel().getYCoordinate()[j][i];
		    	cellColor1[j][i] = new Color(bi.getRGB(x, y));
		    }
	    }
	    assertArrayEquals(cellColor1, expectedCell1);
	
	}

	@Test
	public void testSetCellPin() {
		
		a = new BrailleClient (2,2);
		Point p = new Point(0, 0);
	    SwingUtilities.convertPointToScreen(p, a.getView().getPanel());
	    Rectangle region = a.getView().getPanel().getBounds();
	    region.x = p.x;
	    region.y = p.y;

	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	    BufferedImage bi = null;
	    
	  
			try {
				a.translate("1d");
				a.setCellPin(1, 1, true);
				a.setCellPin(1, 2, false);
				a.setCellPin(1, 3, false);
				a.setCellPin(1, 4, false);
				a.setCellPin(1, 5, false);
				a.setCellPin(1, 6, false);
				a.setCellPin(1, 7, false);
				a.setCellPin(1, 8, false);
				a.setCellPin(2, 1, true);
				a.setCellPin(2, 2, true);
				a.setCellPin(2, 3, false);
				a.setCellPin(2, 4, true);
				a.setCellPin(2, 5, false);
				a.setCellPin(2, 6, false);
				a.setCellPin(2, 7, false);
				a.setCellPin(2, 8, false);
			} catch (InvalidInputException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    try {
		    	bi = null;
		        bi = new Robot().createScreenCapture( region );
		      
			  
		    } catch (AWTException ex) {}
		
		    
		  Color cellColor[][] = new Color [a.getNumCells()][8];
		    Color expectedCell[][]= new Color [][]  {
		    		{Color.black, Color.RED, Color.RED , Color.RED ,Color.RED ,Color.RED ,Color.RED ,Color.RED },
		            { Color.black, Color.black, Color.RED,Color.black,Color.RED ,Color.RED ,Color.RED ,Color.RED}
		    };
		    for (int j=0;j<a.getNumCells();j++) {
			    for (int i = 0; i<8; i++) {
			    	int x = a.getView().getPanel().getXCoordinate()[j][i];
			    	int y = a.getView().getPanel().getYCoordinate()[j][i];
			    	cellColor[j][i] = new Color(bi.getRGB(x, y));
			    }
		    }
		    assertArrayEquals(cellColor, expectedCell);
		    try {
		    	a.translate("a");
				a.setCellPin(-1, 3, true);
				a.setCellPin(1, 1, true);
				a.setCellPin(1, 2, false);
				a.setCellPin(1, 3, false);
				a.setCellPin(1, 4, false);
				a.setCellPin(1, 5, false);
				a.setCellPin(1, 6, false);
				a.setCellPin(1, 7, false);
				a.setCellPin(1, 8, false);
				a.setCellPin(-2, 1, true);
				a.setCellPin(2, 2, true);
				a.setCellPin(2, 3, false);
				a.setCellPin(2, 4, true);
				a.setCellPin(2, 5, false);
				a.setCellPin(2, 6, false);
				a.setCellPin(2, 7, false);
				a.setCellPin(-2, 8, false);
				
			} catch (InvalidInputException e2) {}
		    try {
		    	bi = null;
		        bi = new Robot().createScreenCapture( region );
		    } catch (AWTException ex) {}
		    assertArrayEquals(cellColor, expectedCell);
		    
		    
		    b = new BrailleClient (30,0);
		    try {
				b.translate("q");
				b.setCellPin(1, 1, true);
				b.setCellPin(1, 2, true);
				b.setCellPin(1, 3, true);
				b.setCellPin(1, 4, true);
				b.setCellPin(1, 5, true);
				b.setCellPin(1, 6, false);
				b.setCellPin(1, 7, false);
				b.setCellPin(1, 8, false);
			} catch (InvalidInputException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		    try {
		    	bi = null;
		        bi = new Robot().createScreenCapture( region );
		      
			  
		    } catch (AWTException ex) {}
		
		    
		  Color cellColor1[][] = new Color [b.getNumCells()][8];
		    Color expectedCell1[][]= new Color [][]  {
		    		{Color.black, Color.black, Color.black, Color.black ,Color.black ,Color.red ,Color.red ,Color.red},
		    };
		    for (int j=0;j<b.getNumCells();j++) {
			    for (int i = 0; i<8; i++) {
			    	int x = b.getView().getPanel().getXCoordinate()[j][i];
			    	int y = b.getView().getPanel().getYCoordinate()[j][i];
			    	cellColor1[j][i] = new Color(bi.getRGB(x, y));
			    }
		    }
		    	assertArrayEquals(cellColor1, expectedCell1);
		    try {
				b.translate("q");
				b.setCellPin(-1, 10, true);
				b.setCellPin(1, 2, true);
				b.setCellPin(1, 3, true);
				b.setCellPin(1, 4, true);
				b.setCellPin(1, 5, true);
				b.setCellPin(1, -6, false);
				b.setCellPin(1, 7, false);
				b.setCellPin(1, 8, false);
			} catch (InvalidInputException e2) {}
		    
		    try {
		    	bi = null;
		        bi = new Robot().createScreenCapture( region );
		    } catch (AWTException ex) {}
		    assertArrayEquals(cellColor1, expectedCell1);
	}
}