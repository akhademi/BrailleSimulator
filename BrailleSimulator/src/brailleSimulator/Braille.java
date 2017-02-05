package brailleSimulator;

public class Braille {
	
	private Button [] buttons;
	private Cell [] cells;
	private int numButtons;
	private int numCells;
	
	/**
	 * Creates a braille simulator without a GUI. It is initialized
	 * with numButtons of buttons and numCells of cells with each cell 
	 * having their eight pins set to false. If numButtons exceeds 10, 
	 * the braille simulator will have 10 buttons. If numButtons is less than  
	 * 0, it will have 0 buttons. If numCells is less than or equal to 0, 
	 * the braille simulator will have 1 cell.
	 * @param numButtons number of buttons activated
	 * @param numCells number of cells
	 */
	public Braille (int numButtons, int numCells){
		if (numButtons > 10){
			numButtons = 10;
		}
		if (numButtons < 0){
			numButtons = 0;
		}
		
		if (numCells < 1){
			numCells = 1;
		}
		buttons = new  Button [numButtons];
		cells = new Cell [numCells];
		for (int i=0; i <cells.length;i++){
			cells[i] = new Cell();
		}
		for (int i=0; i <buttons.length;i++){
			buttons [i] = new Button(i+1);
		}
		this.numButtons = numButtons;
		this.numCells = numCells;
	}
	
	/**
	 * Translates a String message based on English Braille alphabet into an array of
	 * Strings, each String being 8 characters long and consisting of 1s and 0s.
	 * Each String of 1s and 0s represents a braille character. Each of these Strings are
	 * then stored into a cell. The first String in the array of Strings corresponds to 
	 * the first cell, the second String with the second cell and so on. 
	 * Within each cell contains 8 pins, where Pin 1 is the top left pin in Braille 
	 * in real life, Pin 2 top right, Pin 3 below Pin 1, Pin 4 below Pin 2, 
	 * Pin 5 below Pin 3 and so on. Similar to how each String in the array of Strings
	 * maps into each cell, each character within a String maps to that cell's Pin
	 * number, with a 1 meaning that pin is true (raised) or 0 (not raised).
	 * Note that eight pins are available, but English Braille characters
	 * only require six. The message will not be translated and stored if the 
	 * length exceeds the number of available cells or does not consists 
	 * of only alphanumeric characters and space where alphanumeric is defined
	 * as numbers or the letters of the alphabet from a-z and A-Z.
	 * @param message is the string that is to be translated into English Braille
	 * and stored
	 * @throws InvalidInputException if message length exceeds numCells or if message
	 * does not consist of only alphanumeric characters and space
	 */
	public void translate(String message) throws InvalidInputException{
		
		if(message.length() > numCells){
			throw new InvalidInputException("Message length exceeds number of cells");
		}
		
		for (int i = 0; i<message.length();i++){
			if (!((message.substring(i,i+1).matches("[A-Z0-9a-z\\s]")))){
				throw new InvalidInputException("Message contains invalid symbols");
			}
		}
	
		setCellPins(message);
		
	}
	
	/**
	 * Return all the cells and their state in an array of eight characters Strings with the first cell 
	 * corresponding to the first element of the array, Cell 2 with second element and so 
	 * on. Inside each eight character string, the first pin corresponds 
	 * with the first character of the string, Pin 2 with the second and so on.
	 * A character of 1 represents that pin is raised and 0 if it is not. 
	 * See translate method for more information with how each pin maps to a pin in
	 * real life.
	 * @return configuration of pins for each of the cells
	 */
	public String [] getCellConfig (){
		String [] cellConfig = new String [numCells] ;
		for (int i = 0; i<numCells; i++){
			cellConfig[i] = cells[i].getPinConfig();
		}
		return cellConfig;
	}
	
	/**
	 * Receives an integer representing which button was pressed and returns a string. 
	 * The string details which button was pressed assuming the integer is higher than 
	 * 0 and less than or equal to the number of buttons the simulator has. 
	 * Returns an empty string otherwise.
	 * @param buttonNum represents which button was "pressed"
	 * @return a string message that tells which button was "pressed" or 
	 * an empty string if buttonNum is not higher than 0 and less than number of buttons
	 * the braille simulator has
	 */
	public String buttonEvent(int buttonNum){
		if (buttonNum>=1 && buttonNum <=numButtons){
			return String.format("Button " + buttons[buttonNum-1].getButtonNum() + " was pressed\n");
		}
		else
			return "";
			
	}
	/**
	 * Gets the number of cells activated in braille simulator.
	 * @return the number of cells activated in braille simulator 
	 */
	public int getNumCells(){
		return numCells;
	}
	
	/**
	 * Sets all pins in every cell to 0 (not raised).
	 */
	public void clearAllCells(){
		for (int i=0;i < numCells; i++){
			try {
				cells[i].setPinConfig("00000000");
			} catch (InvalidInputException e) {
			}
		}
	}
	
	/**
	 * Sets a specific pin of a specific cell to a specified state. Pin 
	 * will not be set successfully if the cell specified is greater than the 
	 * number of cells in the braille simulator or less than 1. Changes will also be 
	 * unsuccessful if specified pin exceeds 8 (as there are only 8 pins) or 
	 * is less than or equal to 0.
	 * @param cell determines which cell number
	 * @param pin determines which pin number
	 * @param state determines what truth value the pin should be set to with 
	 * true representing raised and false not raised
	 * @throws InvalidInputException if cell (number) is greater than numCells or
	 * is less than or equal to 0 or if pin (number) exceeds 8 or is less than or 
	 * equal to 0
	 */
	public void setCellPin(int cell, int pin, boolean state) throws InvalidInputException{
		if(cell>numCells || cell <= 0){
			throw new InvalidInputException("Invalid cell number");
		}
		cells[cell-1].setPin(pin, state);	
	}
	
	//Clear all the cells, translate the message into English Braille and
	//stores it into the cells.
	private void setCellPins(String message){
		this.clearAllCells();
		try{
			for (int i = 0; i<message.length();i++){
				if (message.charAt(i)== 'a' || message.charAt(i)== 'A' || message.charAt(i)== '1'){
					cells[i].setPinConfig("10000000");
				}
				else if (message.charAt(i)== 'b' || message.charAt(i)== 'B'|| message.charAt(i)== '2' ){
					cells[i].setPinConfig("10100000");
				}
				else if (message.charAt(i)== 'c' || message.charAt(i)== 'C'|| message.charAt(i)== '3' ){
					cells[i].setPinConfig("11000000");
				}
				else if (message.charAt(i)== 'd' || message.charAt(i)== 'D'|| message.charAt(i)== '4' ){
					cells[i].setPinConfig("11010000");
				}
				else if (message.charAt(i)== 'e' || message.charAt(i)== 'E'|| message.charAt(i)== '5' ){
					cells[i].setPinConfig("10010000");
				}
				else if (message.charAt(i)== 'f' || message.charAt(i)== 'F'|| message.charAt(i)== '6' ){
					cells[i].setPinConfig("11100000");
				}
				else if (message.charAt(i)== 'g' || message.charAt(i)== 'G'|| message.charAt(i)== '7' ){
					cells[i].setPinConfig("11110000");
				}
				else if (message.charAt(i)== 'h' || message.charAt(i)== 'H'|| message.charAt(i)== '8' ){
					cells[i].setPinConfig("10110000");
				}
				else if (message.charAt(i)== 'i' || message.charAt(i)== 'I'|| message.charAt(i)== '9' ){
					cells[i].setPinConfig("01100000");
				}
				else if (message.charAt(i)== 'j' || message.charAt(i)== 'J'|| message.charAt(i)== '0' ){
					cells[i].setPinConfig("01110000");
				}
				else if (message.charAt(i)== 'k' || message.charAt(i)== 'K' ){
					cells[i].setPinConfig("10001000");
				}
				else if (message.charAt(i)== 'l' || message.charAt(i)== 'L' ){
					cells[i].setPinConfig("10101000");
				}
				else if (message.charAt(i)== 'm' || message.charAt(i)== 'M' ){
					cells[i].setPinConfig("11001000");
				}
				else if (message.charAt(i)== 'n' || message.charAt(i)== 'N' ){
					cells[i].setPinConfig("11011000");
				}
				else if (message.charAt(i)== 'o' || message.charAt(i)== 'O' ){
					cells[i].setPinConfig("10011000");
				}
				else if (message.charAt(i)== 'p' || message.charAt(i)== 'P' ){
					cells[i].setPinConfig("11101000");
				}
				else if (message.charAt(i)== 'q' || message.charAt(i)== 'Q' ){
					cells[i].setPinConfig("11111000");
				}
				else if (message.charAt(i)== 'r' || message.charAt(i)== 'R' ){
					cells[i].setPinConfig("10111000");
				}
				else if (message.charAt(i)== 's' || message.charAt(i)== 'S' ){
					cells[i].setPinConfig("01101000");
				}
				else if (message.charAt(i)== 't' || message.charAt(i)== 'T' ){
					cells[i].setPinConfig("01111000");
				}
				else if (message.charAt(i)== 'u' || message.charAt(i)== 'U' ){
					cells[i].setPinConfig("10001100");
				}
				else if (message.charAt(i)== 'v' || message.charAt(i)== 'V' ){
					cells[i].setPinConfig("10101100");
				}
				else if (message.charAt(i)== 'w' || message.charAt(i)== 'W' ){
					cells[i].setPinConfig("01110100");
				}
				else if (message.charAt(i)== 'x' || message.charAt(i)== 'X' ){
					cells[i].setPinConfig("11001100");
				}
				else if (message.charAt(i)== 'y' || message.charAt(i)== 'Y' ){
					cells[i].setPinConfig("11011100");
				}
				else if (message.charAt(i)== 'z' || message.charAt(i)== 'Z' ){
					cells[i].setPinConfig("10011100");
				}
				else{
					cells[i].setPinConfig("00000000");
				}
			}
		}
		catch (InvalidInputException e1) {
		}
		
	}
	
}
