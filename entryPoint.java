
import Interfaces.CheckersClient;
import serverCommunication.ServerInterface;
import serverCommunication.ServerCommunicator;
import setup.LoginInitializer;

public class entryPoint {

	public static void main(String[] args) {
		Thread client = new MainClient();
		client.start();
		//hello
	}//end main

}//end class
