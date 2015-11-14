package userInterface;

import javax.swing.JTextArea;

public class ChatArea extends JTextArea {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChatArea() {
		super();
	}
	
	public void insert(String text) {
		super.append("\n" + text);
		super.validate();
	}
	
	public void clear() {
		super.setText("");
		super.validate();
		
	}
}
