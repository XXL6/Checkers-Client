package lobby;

import java.awt.Point;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class UserList extends JList<String> {
	
	/**
	 * I don't even know what this does
	 */
	private static final long serialVersionUID = 1L;
	
	static DefaultListModel<String> MODEL = new DefaultListModel<String>();
	
	public UserList() {
		super.setModel(MODEL);
	}
	
	public UserList(DefaultListModel<String> model) {
		super(model);
	}
	
	public void insertUser(String username, boolean client) {
		if (client) {
			MODEL.insertElementAt(username, 0);	
		} else {
			MODEL.addElement(username);
		}
	}
	
	public void removeUser(String username) {
		String removeString;
		for(int i = 0; i < MODEL.getSize(); i++){
		     removeString =  MODEL.getElementAt(i).toString(); 
		     if (removeString.equalsIgnoreCase(username)) {
		    	 MODEL.remove(i);
		     }
		}
	}
	
	public boolean containsUser(String username) {
		for(int i = 0; i < MODEL.getSize(); i++){
		    if (MODEL.getElementAt(i).toString().equalsIgnoreCase(username)) {
		    	return true;
		    }
		}
		return false;
	}
	
	public void clearUsers() {
		MODEL.clear();
	}
	
	@Override
     public int locationToIndex(Point location) {
         int index = super.locationToIndex(location);
         if (index != -1 && !getCellBounds(index, index).contains(location)) {
             return -1;
         }
         else {
             return index;
         }
     }
}
