package loginHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import userInterface.LoginWindow;

public class LoginInfoRetriever implements ActionListener {

	LoginWindow window;
	
	public LoginInfoRetriever(LoginWindow window) {
		this.window = window;
	}


	@Override
	public void actionPerformed(ActionEvent buttonPressed) {
		System.out.println(window.getUsername());
		System.out.println(window.getAddress());
		LoginInfoParser parser = new LoginInfoParser(window.getAddress(), window.getUsername());
	}
	
}
