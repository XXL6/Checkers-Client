import Interfaces.CheckersClient;
import lobby.Lobby;
import lobby.LobbyInterface;
import serverCommunication.ServerInterface;
import serverCommunication.ServerCommunicator;
import setup.GameInitializer;
import setup.LoginInitializer;
import userInterface.ErrorPopups;
import userInterface.LobbyWindow;
import userInterface.LobbyWindowInterface;

public class MainClient extends Thread implements CheckersClient {
	
	ServerInterface serverInterface;
	LobbyInterface lobbyInterface;
	Thread loginInitializer;
	Thread gameInitializer;
	ErrorPopups errorPopup;
	String clientUsername;
	
	public MainClient() {
		serverInterface = new ServerCommunicator(this);
		errorPopup = new ErrorPopups();
		//lobbyInterface = new Lobby();
	}
	
	@Override
	public void run() {
		login();
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
		lobbyInterface = new Lobby(serverInterface);
		lobbyInterface.startLobby();
		lobbyInterface.setUsername(clientUsername);
	}
	
	public void game(int tableID) {
		gameInitializer = new GameInitializer(serverInterface, tableID, lobbyInterface);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newMsg(String user, String msg, boolean pm) {
		lobbyInterface.displayMessage(user, msg, pm);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void colorBlack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void colorRed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oppMove(int fr, int fc, int tr, int tc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void curBoardState(int tid, byte[][] boardState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youWin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youLose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTable(int tid, String blackSeat, String redSeat) {
		lobbyInterface.incomingTableInfo(tid, blackSeat, redSeat);
		
	}

	@Override
	public void tableList(int[] tids) {
		lobbyInterface.refreshTables(tids);
	}

	@Override
	public void yourTurn() {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void illegalMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableFull() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tblNotExists() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameNotCreatedYet() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notYourTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notObserving() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oppNotReady() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorInLobby() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void badMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void oppLeftTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notInLobby() {
		// TODO Auto-generated method stub
		
	}

}
