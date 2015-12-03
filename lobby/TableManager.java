package lobby;

import java.util.ArrayList;
import java.util.LinkedList;

import serverCommunication.ServerInterface;
import userInterface.LobbyWindow;
import userInterface.TableList;

import java.util.Queue;

public class TableManager extends Thread{

	TableList list;
	String user = "test";
	ServerInterface serverInterface;
	TableUpdater tableUpdater;
	GameTable table;
	Queue<GameTable> tableBuffer = new LinkedList<GameTable>();
//	ArrayList<Integer> localTableList = new ArrayList<Integer>();
//	ArrayList<Thread> refresherList = new ArrayList<Thread>();
	
	public TableManager(TableList list, ServerInterface serverInterface) {
		this.list = list;
		this.serverInterface = serverInterface;
	}
	
	public void setUsrName(String user) {
		this.user = user;
	}
	public void refreshTables(int[] tableID) {
		//tableUpdater = new TableUpdater(tableID, list, serverInterface, this);
		//tableUpdater.start();
		//tableRefresher.run();
		
		//This will work well assuming there aren't too many tables that need to be updated
		for (int i : tableID) {
			table = new GameTable(i);
			insertTable(table);
			serverInterface.getTblStatus(user, i);
		}
	}
	
	public void refreshLocalTables() {
		refreshTables(list.getLocalTableIDs());
	}
	public void updateNextTable(int tableID, String black, String red) {
//		if (tableRefresher.isAlive()) {
//			synchronized(tableRefresher) {
//				table = new GameTable(tableID);
//				table.setTableInfo(tableID, black, red);
//				tableBuffer.add(table);
//				tableRefresher.notify();
//			}
//		} else {
			table = new GameTable(tableID);
			table.setTableInfo(tableID, black, red);
			//tableBuffer.add(table);
			insertTable(table);
//		}
	}
	
	public GameTable getIncomingTable() {
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
	
	public boolean hasIncomingTables() {
		return !tableBuffer.isEmpty();
	}
}
