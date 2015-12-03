package lobby;
import userInterface.LobbyWindow;
import userInterface.RequestPopups;

import chatHandler.ChatManager;
import chatHandler.Message;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import serverCommunication.ServerInterface;
import tutorial.Tutorial;

public class Lobby implements LobbyInterface {
	
	LobbyWindow lobbyWindow;
	ServerInterface serverInterface;
	GeneralButtonListener buttonListener;
	MouseListener popupListener;
	TableManager tableManager;
	ChatManager chatManager;
	String clientName;
	
	public Lobby(ServerInterface serverInterface, ChatManager chatManager) {
		lobbyWindow = new LobbyWindow();
		this.serverInterface = serverInterface;
		buttonListener = new GeneralButtonListener(this);
		lobbyWindow.setButtonListeners(buttonListener);
		popupListener = new PopupListener(this);
		lobbyWindow.addPopupMenu(buttonListener, popupListener);
		tableManager = new TableManager(lobbyWindow.getTableList(), serverInterface);
		this.chatManager = chatManager;
	}

	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#startLobby()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#displayMessage(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void displayMessage(String username, String message, boolean isPrivate) {
		if (isPrivate) {
			lobbyWindow.insertText("PM FROM " + username + ": " + message);
		} else if (!username.equals(clientName)){
			lobbyWindow.insertText(username + ": " + message);
		}
	}
	
	public void displayClientMessage(Message message) {
		
		lobbyWindow.insertText(clientName + ": " + message.getMessage());
	}
	
	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#displayGeneralMessage(java.lang.String)
	 */
	@Override
	public void displayGeneralMessage(String message) {
		lobbyWindow.insertText(message);
	}
	
	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#clearChat()
	 */
	@Override
	public void clearChat() {
		lobbyWindow.clearText();
	}

	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#refreshUsers(java.lang.String[], java.lang.String)
	 */
	@Override
	public void refreshUsers(String[] usernames, String clientUsername) {
		lobbyWindow.clearUsers();
		for (String s: usernames) {
			if (s.equals(clientUsername)) {
				lobbyWindow.insertUser("[You]  " + s, true);
			}
			else if (!(lobbyWindow.containsUser(s))){
				lobbyWindow.insertUser(s, false);
			}
		}
	}
	

	
	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#addUser(java.lang.String)
	 */
	@Override
	public void addUser(String username) {
		if (!(lobbyWindow.containsUser(username))) {
			lobbyWindow.insertUser(username, false);
		}
	}

	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#removeUser(java.lang.String)
	 */
	@Override
	public void removeUser(String username) {
		lobbyWindow.removeUser(username);
		
	}
	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#refreshTables(int[])
	 */
	@Override
	public void refreshTables(int[] tables) {
		tableManager.refreshTables(tables);
	}
	
	public void refreshLocalTables() {
		tableManager.refreshLocalTables();
	}
	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#incomingTableInfo(int, java.lang.String, java.lang.String)
	 */
	@Override
	public void incomingTableInfo(int tableID, String black, String red) {
		tableManager.updateNextTable(tableID, black, red);
	}
	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#sendMessage()
	 */
	@Override
	public void sendMessage() {
		String toSend;
		toSend = lobbyWindow.retrieveInputText();
		if (!(toSend.equals(""))) {
			Message message = new Message(toSend);
			chatManager.send(message);
			displayClientMessage(message);
		}
	}

	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#sendPrivateMessage(java.lang.String)
	 */
	@Override
	public void sendPrivateMessage(String username) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#requestPrivateMessage()
	 */
	@Override
	public void requestPrivateMessage() {
		String recipient, message;
		recipient = lobbyWindow.getSelectedUser();
		RequestPopups requestPopup = new RequestPopups();
		message = requestPopup.privateMessageRequest();
		if (message.length() > 0) {
			Message toSend = new Message(message, recipient);
			chatManager.send(toSend);
			if (!recipient.contains("[You] ")) {
				lobbyWindow.insertText("PM TO " + recipient + ": " + message);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#disconnect()
	 */
	@Override
	public void disconnect() {
		serverInterface.disconnect(true);
	}

	/* (non-Javadoc)
	 * @see lobby.LobbyInterface#showPopup(java.awt.event.MouseEvent)
	 */
	@Override
	public void showPopup(MouseEvent mouseEvent) {
		lobbyWindow.displayPopupMenu(mouseEvent);
	}
	
	public void createTable() {
		serverInterface.makeTable(clientName);
	}

	public void joinTable() {
		int tableID = lobbyWindow.getSelectedTable();
		//System.out.println(lobbyWindow.getSelectedTable());
		serverInterface.joinTable(clientName, tableID);
	}
	@Override
	public void setUsername(String username) {
		clientName = username;
		tableManager.setUsrName(username);
	}
	
	public void displayWindow(boolean display) {
		lobbyWindow.display(display);
	}
	
	public void toggleWindow() {
		lobbyWindow.toggleWindow(lobbyWindow.isEnabled());
	}
	
	public void spectate() {
		int tableID = lobbyWindow.getSelectedTable();
		serverInterface.observeTable(clientName, tableID);
	}

	@Override
	public void tutorial() {
		Tutorial tutorial = new Tutorial();
		tutorial.startTutorial();
	}
	
	
}
