package lobby;

public class SingleTableRefresher extends Thread {
	
	GameTable table;
	GameTable newTable;
	TableManager manager;
	boolean finished = false;
	
	public SingleTableRefresher(GameTable table, TableManager manager) {
		this.table = table;
		this.manager = manager;
	}
	
	public void run() {
		manager.insertTable(table);
		while (!finished) {
			synchronized(this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			newTable = manager.getTable();
			if (newTable.getTableID() == table.getTableID()) {
				manager.insertTable(newTable);
				System.out.println(newTable.getTableID());
				System.out.println(table.getTableID());
				finished = true;
			}
		}
		return;
	}
}
