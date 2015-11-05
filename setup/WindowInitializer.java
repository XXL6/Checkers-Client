package setup;

import userInterface.LoginWindow;

import java.awt.EventQueue;

import loginHandler.LoginInfoRetriever;

public class WindowInitializer {
	
	private LoginWindow loginWindow;
	private LoginInfoRetriever loginParser;
	
	public WindowInitializer() {
		
	}//end WindowInitializer constructor
	
	public void startLogin() {
		loginWindow = new LoginWindow();
		loginParser = new LoginInfoRetriever(loginWindow);
		loginWindow.assignLoginButton(loginParser);
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				loginWindow.Display();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});		
		
	}//end startLogin
	
}//end WindowInitializer
