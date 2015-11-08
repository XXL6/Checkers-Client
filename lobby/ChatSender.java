package lobby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import serverCommunication.ServerInterface;
import userInterface.LobbyWindow;

public class ChatSender implements ActionListener {
	
	private ServerInterface serverInterface;
	private LobbyWindow lobbyWindow;
	//String messageToSend;
	
	public ChatSender(ServerInterface serverInterface, LobbyWindow lobbyWindow) {
		this.serverInterface = serverInterface;
		this.lobbyWindow = lobbyWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String messageToSend = lobbyWindow.retrieveInputText();
		serverInterface.sendMsg_All(messageToSend);		
	}

}
