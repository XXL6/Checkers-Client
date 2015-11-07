package lobby;
import userInterface.LobbyWindow;

import java.awt.EventQueue;

import serverCommunication.ServerInterface;

public class Lobby implements LobbyInterface{
	
	LobbyWindow lobbyWindow;
	ServerInterface serverInterface;
	ChatSender sender;
	
	public Lobby(ServerInterface serverInterface) {
		lobbyWindow = new LobbyWindow();
		this.serverInterface = serverInterface;
		sender = new ChatSender(serverInterface, lobbyWindow);
		lobbyWindow.setChatListener(sender);
	}

	public void startLobby() {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				lobbyWindow.display(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
	}
	@Override
	public void displayMessage(String username, String message, boolean isPrivate) {
		if (isPrivate) {
			lobbyWindow.insertText("PM FROM " + username + ": " + message);
		} else {
			lobbyWindow.insertText(username + ": " + message);
		}
	}
	
	public void displayGeneralMessage(String message) {
		lobbyWindow.insertText(message);
	}

	@Override
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshUsers(String[] usernames) {
		lobbyWindow.clearUsers();
		for (String s: usernames) {
			lobbyWindow.insertUser(s);
		}
	}
	public void addUser(String username) {
		lobbyWindow.insertUser(username);
	}

	@Override
	public void removeUser(String username) {
		lobbyWindow.removeUser(username);
		
	}

	@Override
	public void sendPrivateMessage(String username, String message) {
		// TODO Auto-generated method stub
		
	}
}
