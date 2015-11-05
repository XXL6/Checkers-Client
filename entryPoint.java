
import Interfaces.CheckersClient;
import Interfaces.ServerInterface;
import serverCommunication.ServerCommunicator;
import setup.WindowInitializer;

public class entryPoint {
	
	public static void main(String[] args) {
		CheckersClient client = new MainClient();
		//client.start();
	}//end main

}//end class
