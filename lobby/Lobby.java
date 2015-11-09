package lobby;
import userInterface.LobbyWindow;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import serverCommunication.ServerInterface;

public class Lobby implements LobbyInterface{
	
	LobbyWindow lobbyWindow;
	ServerInterface serverInterface;
	GeneralButtonListener buttonListener;
	MouseListener popupListener;
	
	public Lobby(ServerInterface serverInterface) {
		lobbyWindow = new LobbyWindow();
		this.serverInterface = serverInterface;
		buttonListener = new GeneralButtonListener(this);
		lobbyWindow.setChatListener(buttonListener);
		lobbyWindow.setChatClearListener(buttonListener);
		lobbyWindow.setDisconnectListener(buttonListener);
		popupListener = new PopupListener(this);
		lobbyWindow.addPopupMenu(buttonListener, popupListener);
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
	public void clearChat() {
		lobbyWindow.clearText();
	}

	@Override
	public void refreshUsers(String[] usernames, String clientUsername) {
		lobbyWindow.clearUsers();
		for (String s: usernames) {
			if (s.equalsIgnoreCase(clientUsername)) {
				lobbyWindow.insertUser("[You]  " + s, true);
			}
			else if (!(lobbyWindow.containsUser(s))){
				lobbyWindow.insertUser(s, false);
			}
		}
	}
	

	
	public void addUser(String username) {
		if (!(lobbyWindow.containsUser(username))) {
			lobbyWindow.insertUser(username, false);
		}
	}

	@Override
	public void removeUser(String username) {
		lobbyWindow.removeUser(username);
		
	}
	public void refreshTables(int[] tables) {
		String stringToAdd;
		lobbyWindow.clearTables();
		for (int i: tables) {
			stringToAdd = "Table " + i + ": " + "\n";
				lobbyWindow.insertTable(stringToAdd);
		}
	}
	@Override
	public void sendMessage() {
		String toSend;
		toSend = lobbyWindow.retrieveInputText();
		if (!(toSend.equals(""))) {
			serverInterface.sendMsg_All(toSend);
		}
	}

	@Override
	public void sendPrivateMessage(String username) {
		// TODO Auto-generated method stub
		
	}

	public void requestPrivateMessage() {
		System.out.println("GUD JEB LEL");
	}
	
	@Override
	public void disconnect() {
		serverInterface.disconnect(true);
	}

	public void showPopup(MouseEvent mouseEvent) {
		lobbyWindow.displayPopupMenu(mouseEvent);
	}
}
