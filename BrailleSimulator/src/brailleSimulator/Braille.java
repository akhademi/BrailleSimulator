package brailleSimulator;

public class Braille {
	
	private Button [] buttons;
	private Cell [] cells;
	private int numButtons;
	private int numCells;
	
	/**
	 * Create a braille simulator without a GUI. It is initialized
	 *  with numButtons of buttons and numCells cells with each cell 
	 *  having their eight pins set to false. If numButtons exceeds 10, 
	 *  the braille simulation would have 10 buttons and 0 button if 
	 *  it is less than zero. If numCells is less than 0, the braille 
	 *  simulator would have 1 cell.
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
	 * Translates a String based on Braille alphabet into an array of
	 * Strings, each String being 8 characters and consisting of 1 and 0s
	 * representing the Braille translation for that character. The message is 
	 * then stored with each cell representing a character. The first pin of a cell/
	 * first character representing the top left pin in Braille in real life, the second
	 * with the top left, third with the pin below the top left, fourth with the pin
	 * below the top right and so on. Note eight pins are available, but this translation 
	 * uses six pins English Braille. The message will not be translate and stored 
	 * (unsuccessful) if the length exceeds the number of available cells or 
	 * does not consist of alphanumeric characters or space.
	 * @param message is the string that is to be translated into braille
	 * and stored
	 * @return a boolean value true if boolean translation 
	 * and storage is successful and false if unsuccessful
	 */
	public boolean translate(String message){
		boolean error = false;
		
		if(message.length() > numCells){
			error = true;
		}
		
		for (int i = 0; i<message.length();i++){
			if (!((message.substring(i,i+1).matches("[A-Z0-9a-z\\s]")))){
				error = true;
			}
		}
		if (error){
			return false;
		}
		else{
			setCellPins(message);
			return true;
		}
	}
	
	private void setCellPins(String message){
		this.clearAllCells();
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
	
	/**
	 * Return all the cells and their state in an array of eight characters String with the first cell 
	 * corresponding to the first element of the array. Inside each string, the first pin corresponds 
	 * with the first character of the string with 1 representing pin is raised and 0 if it 
	 * is not. See translate method for more information with how the pin number here maps to the
	 * braille in real life.
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
	 * Receives an integer representing which button was pressed and return a string 
	 * detailing which button was pressed assuming the integer is higher than 0 and less 
	 * than the number of buttons the simulator has. Return an empty string elsewise.
	 * @param buttonNum represent which button was "pressed"
	 * @return a string message that tells which button was "pressed" and 
	 * an empty string if buttonNum is higher than 0 and less than number of buttons
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
	 * Gets the number of cells activated in braille simulator
	 * @return the number of cells activated in braille simulator 
	 */
	public int getNumCells(){
		return numCells;
	}
	
	/**
	 * Resets all the cells' pins to 0 (not raised).
	 */
	public void clearAllCells(){
		for (int i=0;i < numCells; i++){
			cells[i].setPinConfig("00000000");
		}
	}
	
	/**
	 * Sets a specific pin of a specific cell to a specific state. Pin 
	 * will not be set successfully if the cell specified is greater than the 
	 * number of cells in the braille simulator or if cell number is less than 
	 * 1.
	 * @param cell determines which cell number
	 * @param pin determines which pin
	 * @param state determine what the pin should be set to with 
	 * 1 representing raised and 0 not raised
	 * @return boolean true if pin is set successfully and false if not
	 */
	public boolean setCellPin(int cell, int pin, boolean state){
		if(cell>numCells || cell <= 0){
			return false;
		}
		return cells[cell-1].setPin(pin, state);
	}
	
	
}
