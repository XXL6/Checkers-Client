import javax.swing.JOptionPane;

import Interfaces.CheckersClient;
import chatHandler.ChatManager;
import game.Game;
import lobby.Lobby;
import lobby.LobbyInterface;
import serverCommunication.ServerInterface;
import serverCommunication.ServerCommunicator;
import setup.LoginInitializer;
import userInterface.ErrorPopups;
import userInterface.LobbyWindow;
import userInterface.LobbyWindowInterface;

public class MainClient extends Thread implements CheckersClient {
	
	ServerInterface serverInterface;
	LobbyInterface lobbyInterface;
	Thread loginInitializer;
	ErrorPopups errorPopup;
	String clientUsername;
	ChatManager chatManager;
	Game game;
	
	public MainClient() {
		serverInterface = new ServerCommunicator(this);
		errorPopup = new ErrorPopups();
		chatManager = new ChatManager(serverInterface);
		//lobbyInterface = new Lobby();
	}
	
	public MainClient(String user, String ip) {
		
		serverInterface = new ServerCommunicator(this);
		errorPopup = new ErrorPopups();
		chatManager = new ChatManager(serverInterface);
		serverInterface.connectToServer(ip, user);
	}
	
	@Override
	public void run() {
		login();
//		game(420);
//		game.enableBoard(true);
//		game.setColor("red");
//		game.startGame();
//		for (int i = 0; i < 100; i++) {
//			serverInterface.connectToServer("127.0.0.1", Integer.toString(i));
//			try {
//				sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//serverInterface.makeTable(Integer.toString(i));
//			//System.out.println(i);
//		}
	}
	
	public void login() {
		loginInitializer = new LoginInitializer(serverInterface);
		loginInitializer.run();		
	}
	
	public void lobby() {
		if (lobbyInterface == null) {
			lobbyInterface = new Lobby(serverInterface, chatManager);
			lobbyInterface.startLobby();
			lobbyInterface.setUsername(clientUsername);
		} else {
			lobbyInterface.toggleWindow();
		}
	}
	
	public void game(int tableID) {
		//lobbyInterface.toggleWindow();
		game = new Game(serverInterface, chatManager, clientUsername, tableID);
		game.setupGame();
		//System.out.println("Is the game a null: " + game == null);
	}
	
	public void observe(int tableID) {
		
	}
	
	public void setUsername(String username) {
		clientUsername = username;
	}
	@Override
	public void connectionOK() {
		System.out.println("Connection Successfull");
		
	}

	@Override
	public void youInLobby() {
		synchronized(loginInitializer) {
			loginInitializer.notify();
		}
		//nullifies the loginInitializer so it can be picked up by the garbage collector
		//loginInitializer = null;
		lobby();
	}

	@Override
	public void youLeftLobby() {
		lobbyInterface.toggleWindow();
		//lobbyInterface = null;
		
	}

	@Override
	public void newMsg(String user, String msg, boolean pm) {
		lobbyInterface.displayMessage(user, msg, pm);
		game.displayMessage(user, msg, pm);
		
	}

	@Override
	public void usersInLobby(String[] users) {
		lobbyInterface.refreshUsers(users, clientUsername);
		
	}

	@Override
	public void nowJoinedLobby(String user) {
		lobbyInterface.addUser(user);
		lobbyInterface.displayGeneralMessage(user + " has joined the lobby.");
		
	}

	@Override
	public void nowLeftLobby(String user) {
		lobbyInterface.removeUser(user);
		lobbyInterface.displayGeneralMessage(user + " has left the lobby.");		
	}

	@Override
	public void newTable(int tid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinedTable(int tid) {
		game(tid);
		
	}

	@Override
	public void alertLeftTable() {
		//synchronized (gameInitializer) {
		//	gameInitializer.notify();
		//}
		game.stopGame();
		game = null;
	}

	@Override
	public void gameStart() {
		game.startGame();
		
	}

	@Override
	public void colorBlack() {
		game.setColor("black");
		
	}

	@Override
	public void colorRed() {
		game.setColor("red");
		
	}

	@Override
	public void oppMove(int fr, int fc, int tr, int tc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void curBoardState(int tid, byte[][] boardState) {
		if (game.getID() == tid)
			game.refreshBoardState(boardState);
		
	}

	@Override
	public void youWin() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Winner, You are the best that ever was!", "Winner", JOptionPane.INFORMATION_MESSAGE);
		game.enableBoard(false);
	}

	@Override
	public void youLose() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Learn how to play loser...", "Loser", JOptionPane.INFORMATION_MESSAGE);
		game.enableBoard(false);
	}

	@Override
	public void onTable(int tid, String blackSeat, String redSeat) {
		lobbyInterface.incomingTableInfo(tid, blackSeat, redSeat);
		if (game != null && game.getID() == tid) {
			if (blackSeat.equals("-1") || redSeat.equals("-1")) {
				game.removeOpponent();
			} else {
				if (!blackSeat.equals(clientUsername))
					game.addOpponent(blackSeat);
				else {
					game.addOpponent(redSeat);
				}
			}
		}
		
	}

	@Override
	public void tableList(int[] tids) {
		lobbyInterface.refreshTables(tids);
	}

	@Override
	public void yourTurn() {
		game.enableBoard(true);
		
	}

	@Override
	public void nowObserving(int tid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stoppedObserving(int tid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void networkException(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nameInUseError() {
		
		
		
		errorPopup.usernameTakenError();
		
	}

	@Override
	public void nameIllegal() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Name is Illegal please pick another", "User name error", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void illegalMove() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "That is an illegal move.", "Illegal Move Error", JOptionPane.INFORMATION_MESSAGE);
		game.enableBoard(true);
		game.undoMove();
	}

	@Override
	public void tableFull() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "That table is full.", "Full Table Error", JOptionPane.INFORMATION_MESSAGE);
	
	}

	@Override
	public void tblNotExists() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "That table dos not exist.", "void table Error", JOptionPane.INFORMATION_MESSAGE);
	}
	

	@Override
	public void gameNotCreatedYet() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Game has not been Created", "No Game Error", JOptionPane.INFORMATION_MESSAGE);
	}
	

	@Override
	public void notYourTurn() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "It is not your turn", "Turn error", JOptionPane.INFORMATION_MESSAGE);
	}
	

	@Override
	public void notObserving() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "You are not Observing", "Observing error", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void oppNotReady() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Opponent is not ready", "ready-up error", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void errorInLobby() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "A lobby error has occured", "Lobby error", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void badMessage() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Error Bad Message", "Message error", JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void oppLeftTable() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Opponent has left the table", "Opponent left", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void notInLobby() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "You are not in the lobby", "Lobby error", JOptionPane.INFORMATION_MESSAGE);
	}

}
