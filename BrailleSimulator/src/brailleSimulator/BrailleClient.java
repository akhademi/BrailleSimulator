package brailleSimulator;

public class BrailleClient extends Braille{
		private SimView view;

		
	/**
	 * Creates a braille simulator with a GUI. It is initialized
	 * with numButtons of buttons and numCells cells with each cell 
	 * having their eight pins set to false. If numButtons exceeds 10, 
	 * the braille simulator will have 10 buttons. If numButtons is less than  
	 * 0, it will have 0 buttons. If numCells is less than or equal to 0, 
	 * the braille simulator will have 1 cell. Based on the number of cells, the
	 * GUI will have the same number of red rectangles, each representing 
	 * a cell. Raised pins inside each cell are represented by black rectangles, 
	 * with the relative location of the black rectangles representing which pins 
	 * are raised.
	 * @param numButtons number of buttons activated
	 * @param numCells number of cells
	 */
	public BrailleClient(int numButtons, int numCells){
		super(numButtons, numCells);
		view = new SimView(this);
		view.pack();
		view.setVisible(true);
	}
	
	/**
	 * Translates a String message based on English Braille alphabet into an array of
	 * Strings, each String being 8 characters long and consisting of 1s and 0s and
	 * updates the GUI according with red and black rectangles.
	 * Each String of 1s and 0s represents a braille character. Each of these Strings are
	 * then stored into a cell. The first String in the array of Strings corresponds to 
	 * the first cell, the second String with the second cell and so on. 
	 * Within each cell contains 8 pins, where Pin 1 is the top left pin in Braille 
	 * in real life, Pin 2 top right, Pin 3 below Pin 1, Pin 4 below Pin 2, 
	 * Pin 5 below Pin 3 and so on. Similar to how each String in the array of Strings
	 * maps into each cell, each character within a String maps to that cell's Pin
	 * number, with a 1 meaning that pin is true (raised) or 0 (not raised).
	 * Note that eight pins are available, but English Braille characters
	 * only require six. The message will not be translated and stored and the
	 * GUI will not be updated if the length exceeds the number of available 
	 * cells or does not consists of only alphanumeric characters and space 
	 * where alphanumeric is defined as numbers or the letters of the alphabet 
	 * from a-z and A-Z.
	 * @param message is the string that is to be translated into braille
	 * and stored
	 * @throws InvalidInputException if message length exceeds numCells or if message
	 * does not only consist of only alphanumeric characters and space
	 */
	public void translate(String message) throws InvalidInputException{
		super.translate(message);
		this.update();
	}
	
	/**
	 * Sets all pins in every cell to 0 (not raised) and updates the
	 * GUI to reflect changes to pin configuration.
	 */
	public void clearAllCells(){
		super.clearAllCells();
		this.update();
	}
	
	
	/**
	 * Sets a specific pin of a specific cell to a specific state. Pin 
	 * will not be set successfully if the cell specified is greater than the 
	 * number of cells in the braille simulator or if cell number is less than 
	 * 1. GUI will be updated if pin is set successfully.
	 * @param cell determines which cell number
	 * @param pin determines which pin
	 * @param state determine what the pin should be set to with 
	 * 1 representing raised and 0 not raised
	 * @throws InvalidInputException if cell (number) is greater than numCells or
	 * is less than or equal to 0 or if pin (number) exceeds 8 or is equal or less
	 * than 0
	 */
	public void setCellPin(int cell, int pin, boolean state) throws InvalidInputException{
		super.setCellPin(cell, pin, state);
		this.update();
	}
	
	private void update(){
		view.drawBraille(super.getCellConfig());
	}
	
	
	/**
	 * Receives an integer representing which button was pressed and returns a string. 
	 * The string details which button was pressed assuming the integer is higher than 
	 * 0 and less than or equal to the number of buttons the simulator has. Return an empty string 
	 * otherwise. As long as the returned string is not an empty, it will also
	 * be displayed in the GUI text box and output to the console.
	 * @param buttonNum represents which button was "pressed"
	 * @return a string message that tells which button was "pressed" and 
	 * an empty string if buttonNum is not higher than 0 and less than number of buttons
	 * the braille simulator has
	 */
	public String buttonEvent(int buttonNum){
		String buttonMessage = super.buttonEvent(buttonNum);
		if (!buttonMessage.equals("")){
			view.setButtonEvent(buttonMessage);
		}
		return buttonMessage;
	}
	
	/**
	 * NOTE: THIS IS NOT PART OF THE API/JAVADOC.
	 * This method was put here for the JUnit Testing to work
	 * @return the view
	 */
	public SimView getView(){
		return view;
	}
}
