package lobby;

import java.util.ArrayList;
import java.util.LinkedList;

import serverCommunication.ServerInterface;
import userInterface.LobbyWindow;
import java.util.Queue;

public class TableManager extends Thread{

	TableList list;
	ServerInterface serverInterface;
	TableRefresher tableRefresher;
	GameTable table;
	Queue<GameTable> tableBuffer = new LinkedList<GameTable>();
//	ArrayList<Integer> localTableList = new ArrayList<Integer>();
//	ArrayList<Thread> refresherList = new ArrayList<Thread>();
	
	public TableManager(TableList list, ServerInterface serverInterface) {
		this.list = list;
		this.serverInterface = serverInterface;
	}
	
	public void refreshTables(int[] tableID) {
		tableRefresher = new TableRefresher(tableID, list, serverInterface, this);
		tableRefresher.start();
		//tableRefresher.run();
	}
	
	public void updateNextTable(int tableID, String black, String red) {
		if (tableRefresher.isAlive()) {
			synchronized(tableRefresher) {
				table = new GameTable(tableID);
				table.setTableInfo(tableID, black, red);
				tableBuffer.add(table);
				tableRefresher.notify();
			}
		} else {
			table = new GameTable(tableID);
			table.setTableInfo(tableID, black, red);
			insertTable(table);
		}
	}
	
	public GameTable getTable() {
		return tableBuffer.poll();
	}
	
	public void removeTable(int tableID) {
		list.remove(tableID);
	}
	
	public void insertTable(GameTable table) {
		list.insert(table);
	}
	
	public boolean areAnyTablesLoading() {
		return list.tablesLoading();
	}
}
