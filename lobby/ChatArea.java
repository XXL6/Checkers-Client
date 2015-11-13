package lobby;

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
		super.append(text + "\n");
		super.validate();
	}
	
	public void clear() {
		super.setText("");
		super.validate();
		
	}
}
