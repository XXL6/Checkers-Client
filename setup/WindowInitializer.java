package setup;

import userInterface.LoginWindow;

public class WindowInitializer {
	
	private LoginWindow loginWindow;
	
	public WindowInitializer() {
		
	}//end WindowInitializer constructor
	
	public void startLogin() {
		LoginWindow loginWindow = new LoginWindow();
		loginWindow.Display();
	}//end startLogin
	
}//end WindowInitializer
