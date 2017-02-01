package brailleSimulator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class SimPanel extends JPanel {
	private BrailleClient sim;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final int WIDTH = (int) (screenSize.getWidth()/1.1);
	public final int HEIGHT = (int) (screenSize.getHeight()/2);
	private int widthPerCell;
	private int [] x;
	private String [] braille;
	private boolean drawCell = false;
	private boolean drawBraille = false;
	public final int HEIGHTPERCELL = (int) (screenSize.getHeight()/4);
	
	//Creates a panel.
	public SimPanel(BrailleClient sim){
			super();
			this.sim = sim;
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
			setBackground(Color.WHITE);
	}
	
	/* Overrides paintComponent to paint red rectangles (cells), size and number
	 * based on number of cells activated and monitor size and
	 * black rectangles inside cells (raised pins), based on stored pin configuration
	 * of each cell as required.
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		int xCoord = 0;
		int yCoord = (int)(screenSize.getHeight()/9);
		if (drawCell){
			x = new int [sim.getNumCells()];
			xCoord = widthPerCell/sim.getNumCells()/2;
			for (int i = 1; i <= sim.getNumCells(); i++ ){
				x[i-1] = xCoord;
				g.fillRect(xCoord, yCoord, widthPerCell, HEIGHTPERCELL);
				xCoord = xCoord + widthPerCell + widthPerCell/sim.getNumCells();
			}
			drawCell = false;
		}
		if (drawBraille){
			g.setColor(Color.BLACK);
			for (int i = 0; i < braille.length; i++ ){
				if (braille[i].charAt(0)== '1'){
					g.fillRect(x[i], yCoord, widthPerCell/2, HEIGHTPERCELL/4);
				}
				if (braille[i].charAt(1)== '1'){
					g.fillRect(x[i] + widthPerCell/2, yCoord, widthPerCell/2, HEIGHTPERCELL/4);
				}
				if (braille[i].charAt(2)== '1'){
					g.fillRect(x[i], yCoord+HEIGHTPERCELL/4, widthPerCell/2, HEIGHTPERCELL/4);
				}
				if (braille[i].charAt(3)== '1'){
					g.fillRect(x[i] + widthPerCell/2, yCoord+HEIGHTPERCELL/4, widthPerCell/2, HEIGHTPERCELL/4);
				}
				if (braille[i].charAt(4)== '1'){
					g.fillRect(x[i], yCoord+2*HEIGHTPERCELL/4, widthPerCell/2,HEIGHTPERCELL/4);
				}
				if (braille[i].charAt(5)== '1'){
					g.fillRect(x[i] + widthPerCell/2, yCoord+2*HEIGHTPERCELL/4, widthPerCell/2, HEIGHTPERCELL/4);
				}
				if (braille[i].charAt(6)== '1'){
					g.fillRect(x[i], yCoord+3*HEIGHTPERCELL/4, widthPerCell/2,HEIGHTPERCELL/4);
				}
				if (braille[i].charAt(7)== '1'){
					g.fillRect(x[i] + widthPerCell/2, yCoord+3*HEIGHTPERCELL/4, widthPerCell/2, HEIGHTPERCELL/4);
				}
				
			}
			
			
			drawBraille = false;
		}
	}
	
	/*Calculates width of each cell (approximately) based on monitor
	 * size and makes the paintComponent to redraw the cell when is is called.
	 */
	public void calcCellDimension(){
		widthPerCell = WIDTH /(sim.getNumCells()+1);
		drawCell = true;
	}
	
	/*Updates the braille configuration in this class and makes the
	 * paintComponent, when called, to redraw the pin configuration 
	 * based on update.
	 */

	public void drawBraille(String [] braille){
		this.braille = braille;
		drawBraille = true;
	}
		
		
}
