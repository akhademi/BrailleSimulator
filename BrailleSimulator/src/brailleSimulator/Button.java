package brailleSimulator;

public class Button {
	
	private int buttonNum;
	
	/**
	 * Creates a button with a button number same as the argument.
	 * @param buttonNum an integer that determine what number the button is
	 */
	public Button(int buttonNum){
		this.buttonNum = buttonNum;
		}
	
	/**
	 * Returns the button number.
	 * @return an integer representing what number the button is
	 */
	public int getButtonNum(){
		return buttonNum;
	}
}
