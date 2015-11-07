package lobby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import serverCommunication.ServerInterface;

public class Disconnector implements ActionListener{
	
	ServerInterface serverInterface;
	
	public Disconnector(ServerInterface serverInterface) {
		this.serverInterface = serverInterface;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		serverInterface.disconnect(true);
		
	}
	
}
