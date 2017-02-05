package brailleSimulator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



 class SimController {
	private SimView view;
	private BrailleClient sim;
	
	//Creates the controller.
	public SimController(SimView view, BrailleClient sim){
		this.sim = sim;
	    this.view = view;
	    this.view.addTranslateListener(new TranslateListener());
	    this.view.addResetListener(new ResetListener());
	    this.view.addButtonListener(new ButtonListener());
	}
	
	
	private class TranslateListener implements ActionListener{
		
		/* Attempts to translate what is in the text box in the GUI
		 * if "Translate" is clicked on. The message is either translated
		 * and stored and the GUI is updated or an error message will be
		 * displayed in the text if message to be translated does not
		 * fulfill the criteria in the translate method of BrailleSimulator*/ 
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				sim.translate(view.getTextField());
				view.drawBraille(sim.getCellConfig());
			} catch (InvalidInputException e1) {
				view.messageError();
			}
		}
	}
	
	
	private class ResetListener implements ActionListener{
		
		/*Clears all cells when "Reset Cells" is pressed.*/ 
		
		@Override
		public void actionPerformed(ActionEvent e) {
			sim.clearAllCells();;
		}
	}
	
	private class ButtonListener implements KeyListener{
		
		/* Determines which keys were pressed and maps each key to a number.
		 * Here, F1 maps to 1, F2 to 2 and all the way to F10 which maps to 10. 
		 * Based on how many buttons are activated on the simulator and which button
		 * on the keyboard is being pressed, this method will result in a message 
		 * determining which button was pressed being output in the text 
		 * box of the GUI and in the console or nothing will happen.
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_F1){
				sim.buttonEvent(1);
			}
			else if (e.getKeyCode() == KeyEvent.VK_F2){
				sim.buttonEvent(2);
			}
			else if (e.getKeyCode() == KeyEvent.VK_F3){
				sim.buttonEvent(3);
			}
			else if (e.getKeyCode() == KeyEvent.VK_F4){
				sim.buttonEvent(4);
			}
			else if (e.getKeyCode() == KeyEvent.VK_F5){
				sim.buttonEvent(5);
			}
			else if (e.getKeyCode() == KeyEvent.VK_F6){
				sim.buttonEvent(6);
			}
			else if (e.getKeyCode() == KeyEvent.VK_F7){
				sim.buttonEvent(7);
			}
			else if (e.getKeyCode() == KeyEvent.VK_F8){
				sim.buttonEvent(8);
			}
			else if (e.getKeyCode() == KeyEvent.VK_F9){
				sim.buttonEvent(9);
			}
			else if (e.getKeyCode() == KeyEvent.VK_F10){
				sim.buttonEvent(10);
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}
	
}

