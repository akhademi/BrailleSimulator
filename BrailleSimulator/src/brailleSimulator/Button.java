package brailleSimulator;

public class Button {
	
	private int buttonNum;
	
	/**
	 * Creates a button with given buttonNum.
	 * @param buttonNum an integer that determine what number is the button.
	 */
	public Button(int buttonNum){
		this.buttonNum = buttonNum;
		}
	
	/**
	 * Return button number.
	 * @return an integer representing what number the button is
	 */
	public int getButtonNum(){
		return buttonNum;
	}
}
