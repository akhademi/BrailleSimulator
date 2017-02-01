package brailleSimulator;
public class Cell {
	
	private Pin [] pins;
	
	/**
	 * Creates a cell of eight pins, each initialized
	 * with a state of false.
	 */
	public Cell(){
		pins = new Pin [8];
		for (int i=0; i <pins.length;i++){
			pins[i] = new Pin();
		}
	}
	/**
	 * Sets the state of each pin corresponding to pinConfig
	 * given with the first pin corresponding to first character of pinConfig and so on. 
	 * Method will not set the state if pinConfig does not have exactly
	 * eight characters, with each character being a 1 or a 0.
	 * @param pinConfig a eight character string with each character
	 * representing the state of each pin (1 is true, 0 is false)
	 * @return boolean value the returns true if pins are set
	 * based on pinConfig or false if no changes are made due to
	 * invalid input
	 */
	public boolean setPinConfig(String pinConfig) {
		boolean valid = true;
		if (pinConfig.length()!=8){
			valid = false;
		}
		else {
			for (int i = 0; i<8;i++){
				if (!(pinConfig.substring(i,i+1).matches("[0-1]"))){
					valid = false;
				}
			}
			
		}
		
		if (valid){
			for (int i = 0; i <pins.length; i++){
				if (pinConfig.charAt(i)== '1'){
					pins[i].setState(true);
				}
				else if (pinConfig.charAt(i)== '0'){
					pins[i].setState(false);
				}
					
			}
			return valid;
		}
		return valid;
	}
	
	/**
	 * Returns an eight character long String consisting of 1 and 0 to
	 * represent the state of each corresponding pin with the first character
	 * corresponding with and first pin and so on.
	 * @return an eight character long String representing the state of each 
	 * of the eight pins in the cell with 1 corresponding to true and
	 * 0 corresponding to false
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
	 * Sets a specific pin to a certain state corresponding to num and state given.
	 * The first pin corresponds with a num of 1 and so on. Change will be unsuccessful
	 * num exceeds the amount of pins or is less then or equal to 0.
	 * @param num represent which pin is to be changed
	 * @param state represent what state the pin should be set to
	 * @return boolean value that is true if change is successful or false if it is not
	 */
	public boolean setPin(int num, boolean state){
		if (num>pins.length || num <= 0){
			return false;
		}
		pins[num-1].setState(state);
		return true;
	}
}
