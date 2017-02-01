package brailleSimulator;

public class Pin {
	private boolean state;
	
	/**
	 * Creates a pin with a state of false.
	 */
	public Pin(){
		state = false;
	}
	
	/**
	 * Sets the pin to a certain state.
	 * @param state a boolean value that represent what the pin state 
	 * should be set to
	 */
	public void setState(boolean state){
		this.state = state;
	}
	/**
	 * Gets the state of the pin.
	 * @return the state of the pin
	 */
	public boolean getState(){
		return state;
	}
}
