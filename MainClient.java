import Interfaces.CheckersClient;
import serverCommunication.ServerInterface;
import serverCommunication.ServerCommunicator;
import setup.LoginInitializer;

public class MainClient implements CheckersClient {
	
	ServerInterface serverInterface;
	
	public MainClient() {
		start();
	}
	
	public void start() {
		LoginInitializer windowInitializer = new LoginInitializer();
		serverInterface = new ServerCommunicator(this);
		windowInitializer.startLogin(serverInterface);
	}
	@Override
	public void connectionOK() {
		System.out.println("Connection Successfull");
		
	}

	@Override
	public void youInLobby() {
		System.out.println("You are in the lobby");
		
	}

	@Override
	public void youLeftLobby() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newMsg(String user, String msg, boolean pm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void usersInLobby(String[] users) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nowJoinedLobby(String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nowLeftLobby(String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newTable(int tid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinedTable(int tid) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableList(int[] tids) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
