package brailleSimulator;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;

public class BrailleClientTest {
	
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final int WIDTH = (int) (screenSize.getWidth()/1.1);
	public final int HEIGHT = (int) (screenSize.getHeight()/2);
	public final int HEIGHTPERCELL = (int) (screenSize.getHeight()/4);
	BrailleClient test;
	
	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void testTranslate() throws InterruptedException {
		test = new BrailleClient(2,2);
		int widthPerCell = WIDTH /(2+1);
		int xCoord = 0;
		int yCoord = (int)(screenSize.getHeight()/9);
		int [] x = new int [2];
		xCoord = widthPerCell/2/2;
		for (int i = 1; i <= 2; i++ ){
			x[i-1] = xCoord;
			xCoord = xCoord + widthPerCell + widthPerCell/2;
		}
		
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		test.view.background.paint(g2);
		g2.dispose();
		try {
	
		    File outputfile = new File("saved.png");
		    ImageIO.write(image, "png", outputfile); //SAVES PNG so you can check what bufferedimage is getting
		} catch (IOException e) {}
		Color mycolor = new Color(image.getRGB(x[0], yCoord));
		System.out.println(mycolor);
		mycolor = new Color(image.getRGB(x[1], yCoord));
		System.out.println(mycolor);
		
		test.translate("a "); // If you put it above bufffered image, rectangles will not show

	}



}
