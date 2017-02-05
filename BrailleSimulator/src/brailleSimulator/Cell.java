package brailleSimulator;
public class Cell {
	
	private Pin [] pins;
	
	/**
	 * Creates a cell with an array of eight pins, each pin initialized
	 * with a state of false.
	 */
	public Cell(){
		pins = new Pin [8];
		for (int i=0; i <pins.length;i++){
			pins[i] = new Pin();
		}
	}
	/**
	 * Sets the state of each pin corresponding to the pinConfig argument given
	 * with the first pin corresponding to first character of pinConfig and so on.
	 * A character of 1 means that pin should be set to true (raised) and 0 means to 
	 * false (not raised). Method will not set the state of any pin if pinConfig does 
	 * not have exactly eight characters, with each character being a 1 or a 0.
	 * @param pinConfig an eight character string with each character
	 * representing the state of each pin (1 is true, 0 is false)
	 * @throws InvalidInputException if pinConfig is not a String of length 8
	 * or pinConfig does not consist of only 1 and 0
	 */
	public void setPinConfig(String pinConfig) throws InvalidInputException {
		
		if (pinConfig.length()!=8){
			throw new InvalidInputException("Pin configuration length does not equal to 8");
		}
	
		for (int i = 0; i<8;i++){
			if (!(pinConfig.substring(i,i+1).matches("[0-1]"))){
				throw new InvalidInputException("Pin configuration length does not consist of only 1 and 0");
			}
		}
			
		for (int i = 0; i <pins.length; i++){
			if (pinConfig.charAt(i)== '1'){
					pins[i].setState(true);
			}
			else if (pinConfig.charAt(i)== '0'){
					pins[i].setState(false);
			}
		}
	
	}
	
	/**
	 * Returns an eight character long String consisting of 1 and 0 to
	 * represent the state of each pin. The first character of the String 
	 * corresponds with the first pin, the second character with Pin 2 and so on.
	 * A 1 means the pin is true (raised) while a 0 means false (not raised).
	 * @return an eight character String representing the state of each 
	 * pin in the cell with 1 corresponding to true (raised) and 0 corresponding to 
	 * false (not raised)
	 */
	public String getPinConfig() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pins.length; i++){
			if (pins[i].getState()){
				sb.append("1");
			}
			else{
				sb.append("0");
			}
				
		}
		return sb.toString();
	}
	
	/**
	 * Sets a specific pin to a specified state corresponding to the num and state given.
	 * Pin 1 would map to a num of 1, Pin 2 with 2 and so on. Changes will be 
	 * unsuccessful if num exceeds 8 (as there are only 8 pins) or is less than 
	 * or equal to 0.
	 * @param num an integer representing which pin's state is to be set
	 * @param state represent what state the pin should be set to 
	 * @throws InvalidInputException if (pin) num exceeds 8 or is equal to or less
	 * than 0
	 */
	public void setPin(int num, boolean state) throws InvalidInputException{
		if (num>pins.length || num <= 0){
			throw new InvalidInputException("Invalid pin number");
		}
		pins[num-1].setState(state);
	}
}
