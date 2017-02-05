package brailleSimulator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

 class SimPanel extends JPanel {
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
	private int xCoordinate[][];
	private int yCoordinate[][];
	
	//Creates a panel with a white background.
	public SimPanel(BrailleClient sim){
		super();
		this.sim = sim;
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.WHITE);
		xCoordinate = new int[sim.getNumCells()][8];
		yCoordinate = new int[sim.getNumCells()][8];
	}
	
	/* Overrides paintComponent to paint red rectangles (cells) and black rectangles (raised pins). 
	 * The size and number of red rectangles are based on the number of cells 
	 * activated and monitor size. Based on stored pin configuration, black rectangles
	 * (raised pins) are painted inside of each cell as required over the red rectangles.
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
				getXCoordinate()[i][0] = x[i];
				getYCoordinate()[i][0] = yCoord;
				if (braille[i].charAt(0)== '1'){
					g.fillRect(getXCoordinate()[i][0], getYCoordinate()[i][0], widthPerCell/2, HEIGHTPERCELL/4);
				}
				getXCoordinate()[i][1] = x[i] + widthPerCell/2;
				getYCoordinate()[i][1] = yCoord;
				if (braille[i].charAt(1)== '1'){
					g.fillRect(getXCoordinate()[i][1], getYCoordinate()[i][1], widthPerCell/2, HEIGHTPERCELL/4);
					
				}
				getXCoordinate()[i][2] = x[i];
				getYCoordinate()[i][2] = yCoord + HEIGHTPERCELL/4;
				if (braille[i].charAt(2)== '1'){
					g.fillRect(getXCoordinate()[i][2], getYCoordinate()[i][2], widthPerCell/2, HEIGHTPERCELL/4);
				}
				getXCoordinate()[i][3] = x[i] + widthPerCell/2;
				getYCoordinate()[i][3] = yCoord + HEIGHTPERCELL/4;
				if (braille[i].charAt(3)== '1'){
					g.fillRect(getXCoordinate()[i][3], getYCoordinate()[i][3], widthPerCell/2, HEIGHTPERCELL/4);
				}
				getXCoordinate()[i][4] = x[i];
				getYCoordinate()[i][4] = yCoord + 2*HEIGHTPERCELL/4;
				if (braille[i].charAt(4)== '1'){
					g.fillRect(getXCoordinate()[i][4], getYCoordinate()[i][4], widthPerCell/2,HEIGHTPERCELL/4);
				}
				getXCoordinate()[i][5] = x[i] + widthPerCell/2;
				getYCoordinate()[i][5] = yCoord +2*HEIGHTPERCELL/4;
				if (braille[i].charAt(5)== '1'){
					g.fillRect(getXCoordinate()[i][5], getYCoordinate()[i][5], widthPerCell/2, HEIGHTPERCELL/4);
				}
				getXCoordinate()[i][6] = x[i];
				getYCoordinate()[i][6] = yCoord + 3*HEIGHTPERCELL/4;
				if (braille[i].charAt(6)== '1'){
					g.fillRect(getXCoordinate()[i][6], getYCoordinate()[i][6], widthPerCell/2,HEIGHTPERCELL/4);
				}
				getXCoordinate()[i][7] = x[i] + widthPerCell/2;
				getYCoordinate()[i][7] = yCoord +3*HEIGHTPERCELL/4;
				if (braille[i].charAt(7)== '1'){
					g.fillRect(getXCoordinate()[i][7], getYCoordinate()[i][7], widthPerCell/2, HEIGHTPERCELL/4);
				}
				
			}
			drawBraille = false;
		}
	}
	
	/* Calculates what the width of each cell should based on monitor
	 * size and prompts the paintComponent to redraw the cells when
	 * the paintComponent is called.
	 */
	public void calcCellDimension(){
		widthPerCell = WIDTH /(sim.getNumCells()+1);
		drawCell = true;
	}
	
	/* Updates the pin configuration of each cell in this class and makes the
	 * paintComponent, when called, to redraw the pin configuration 
	 * based on update.
	 */

	public void drawBraille(String [] braille){
		this.braille = braille;
		drawBraille = true;
	}

	
	//BELOW METHODS ARE FOR TESTING PURPOSES
	public int[][] getXCoordinate() {
		return xCoordinate;
	}

	public int[][] getYCoordinate() {
		return yCoordinate;
	}	
		
}
