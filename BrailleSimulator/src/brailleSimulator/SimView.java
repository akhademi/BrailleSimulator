package brailleSimulator;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class SimView extends JFrame{
	private BrailleClient sim;
	public SimPanel background;
	private JTextField message;
	private JButton translateButton;
	private JButton resetButton;
	private Font f= new Font("Arial", Font.BOLD, 30);
	
	//Creates the view and the initial state of GUI.
	public SimView(BrailleClient test) {
		super();
		if (BrailleClient.getInstances()==1){
			try {
				throw new TooManyInstanceException();
			} catch (TooManyInstanceException e) {
				// TODO Auto-generated catch block
				System.out.println("Too many instances.");
				System.exit(1);
			}
		}
		this.sim = test;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    background = new SimPanel(sim);
	    setResizable(false);
	    setContentPane(background);
	    this.message = new JTextField("Enter message to be translated to Braille", 60);
	    this.translateButton = new JButton("Translate");
	    this.resetButton = new JButton("Reset Cells");
	    message.setFont(f);
	    translateButton.setFont(f);
	    resetButton.setFont(f);
	    this.add(message);
	    this.add(translateButton);
	    this.add(resetButton);
	    SimController controller = new SimController(this, sim);
	    background.drawBraille(sim.getCellConfig());
	    background.calcCellDimension();
	    
	}
	
	//Add listener to "Translate" button.
	public void addTranslateListener(ActionListener e) {
	    this.translateButton.addActionListener(e);
		}
	
	//Add listener to "Reset Cells" button.
		public void addResetListener(ActionListener e) {
		    this.resetButton.addActionListener(e);
			}
	
	//Add key listener to whole GUI
	public void addButtonListener(KeyListener e) {
		background.addKeyListener(e);
		background.setFocusable(true);
		message.addKeyListener(e);
		message.setFocusable(true);
		translateButton.addKeyListener(e);
		translateButton.setFocusable(true);
		resetButton.addKeyListener(e);
		resetButton.setFocusable(true);
		}
	
	//Retrieves text in text box.
	public String getTextField(){
		return message.getText();
		}
  
	//Sets an error message in the text box.
	public void messageError(){
		message.setText("Error: Message length greater than cell number or invalid symbol");
		}
	
	  /*
	   * Forces Java to repaint with corresponding pin configuration
	   * based on received brailleBits configuration.
	   */
	  public void drawBraille(String[] brailleBits) {
		  background.calcCellDimension();
		  this.background.drawBraille(brailleBits);
		  validate();
		  repaint();
		     try
		     {
		          Thread.sleep(40); 
		     }
		     catch (Exception e)
		     {
		          e.printStackTrace();
		     }
	  }
	  
	  /* Outputs a message to text box telling which of the activated buttons
	   * was pressed and to the console.
	   */
	  public void setButtonEvent(String buttonEvent) {
		  System.out.print(buttonEvent);
		  message.setText(buttonEvent);
		
	}
	  
	 
	
}