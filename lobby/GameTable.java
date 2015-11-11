package lobby;

public class GameTable {
	
	private int tableID;
	private String black;
	private String red;
	private boolean loading = true;;
	
	public GameTable(int tableID) {
		this.tableID = tableID;
		loading = true;
	}
	
	public void setTableInfo(int tableID, String black, String red) {
		this.tableID = tableID;
		if (black.equalsIgnoreCase("-1")) {
			this.black = "Open Seat";
		} else {
			this.black = black;
		}
		if (red.equalsIgnoreCase("-1")) {
			this.red = "Open Seat";
		} else {
			this.red = red;
		}
		loading = false;
	}
	
	public int getTableID() {
		return tableID;
	}
	
	public String getBlackSeat() {
		return black;
	}
	
	public String getRedSeat() {
		return red;
	}
	
	public String toString() {
		if (loading) {
			return "Table " + tableID + ": " + "Retrieving status......\n";
		} else {
			return "Table " + tableID + ": " + "Black: [" + black + "] Red: [" + red + "]\n";
		}
	}
}
