package setup;

import lobby.Lobby;
import lobby.LobbyInterface;
import serverCommunication.ServerInterface;

public class GameInitializer extends Thread {

	private ServerInterface serverInterface;
	private int tableID;
	private Lobby lobby;
	
	public GameInitializer(ServerInterface serverInterface, int tableID, LobbyInterface lobbyInterface) {
		
	}
}
