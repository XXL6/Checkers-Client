
import Interfaces.CheckersClient;
import serverCommunication.ServerInterface;
import serverCommunication.ServerCommunicator;
import setup.LoginInitializer;

public class entryPoint {

	public static void main(String[] args) {
		//MainClient client = new MainClient("steve", "127.0.0.1");
		//client.start();
		//MainClient client2 = new MainClient("bob", "127.0.0.1");
		//client2.start();
		MainClient client = new MainClient();
		client.start();
		//hello
	}//end main

}//end class
