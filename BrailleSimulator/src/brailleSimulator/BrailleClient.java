package brailleSimulator;

public class BrailleClient extends Braille{
		public SimView view;
		private static int instances = 0;
	
		
	/**
	 * Create a braille simulator with a GUI. It is initialized
	 * with numButtons of buttons and numCells cells with each cell 
	 * having their eight pins set to false. If numButtons exceeds 10, 
	 * the braille simulation would have 10 buttons and 0 button if 
	 * it is less than zero. If numCells is less than 0, the braille 
	 * simulator would have 1 cell. The GUI has numCells of red 
	 * rectangles, each representing a cell. Raised pins inside each
	 * cell are represented by black rectangles, with the relative location
	 * of the black rectangles representing which pins are raised.
	 * @param numButtons number of buttons activated
	 * @param numCells number of cells
	 */
	public BrailleClient(int numButtons, int numCells){
		super(numButtons, numCells);
		view = new SimView(this);
		view.pack();
		view.setVisible(true);
		BrailleClient.instances = 1;
	}
	
	/**
	 * Translates a String based on Braille alphabet into an array of
	 * Strings, each String being 8 characters and consisting of 1 and 0s
	 * representing the Braille translation for that character. The message is 
	 * then stored with each cell representing a character. The first pin of a cell/
	 * first character representing the top left pin in Braille in real life, the second
	 * with the top left, third with the pin below the top left, fourth with the pin
	 * below the top right and so on. If translate is successful, 
	 * the GUI will also be updated to reflect changes to pin state.
	 * Note eight pins are available, but this translation 
	 * uses six pins English Braille. The message will not be translate and stored 
	 * (unsuccessful) if the length exceeds the number of available cells or 
	 * does not consist of alphanumeric characters or space.
	 * @param message is the string that is to be translated into braille
	 * and stored
	 * @return a boolean value true if boolean translation, 
	 * storage and display on GUI is successful and false if unsuccessful
	 */
	public boolean translate(String message){
		boolean update = super.translate(message);
		if (update){
			this.update(update);
		}
		else{
			view.messageError();
		}
		return update;
	}
	
	/**
	 * Resets all the cells' pins to 0 (not raised) and updates
	 * GUI to reflect change.
	 */
	public void clearAllCells(){
		super.clearAllCells();
		this.update(true);
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
	 * @return boolean true if pin is set successfully (and GUI is
	 * updated and false if not
	 */
	public boolean setCellPin(int cell, int pin, boolean state){
		boolean update = super.setCellPin(cell, pin, state);
		this.update(true);
		return update;
	}
	
	private void update(boolean update){
		if (update){
			view.drawBraille(super.getCellConfig());
		}
	}
	
	/**
	 * Return number of BrailleClient instances.
	 * @return integer representing number of BrailleClient instances
	 */
	public static int getInstances() {
		return instances;
	}
	
	/**
	 * Receives an integer representing which button was pressed and return a string 
	 * detailing which button was pressed assuming the integer is higher than 0 and less 
	 * than the number of buttons the simulator has. Return an empty string elsewise.
	 * Will also display the message in the GUI text box.
	 * @param buttonNum represent which button was "pressed"
	 * @return a string message that tells which button was "pressed" and 
	 * an empty string if buttonNum is higher than 0 and less than number of buttons
	 * the braille simulator has
	 */
	public String buttonEvent(int buttonNum){
		String buttonMessage = super.buttonEvent(buttonNum);
		if (!buttonMessage.equals("")){
			view.setButtonEvent(buttonMessage);
		}
		return buttonMessage;
	}
}
