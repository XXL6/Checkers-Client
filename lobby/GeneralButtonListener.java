package lobby;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralButtonListener implements ActionListener {

	LobbyInterface lobby;
	
	public GeneralButtonListener(LobbyInterface lobby) {
		this.lobby = lobby;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg) {
		System.out.println(arg.getSource());
		switch (arg.getActionCommand().toLowerCase()) {
			case "send": lobby.sendMessage();
				break;
			case "clear chat": lobby.clearChat();
				break;
			case "disconnect": lobby.disconnect();
				break;
			case "create table": lobby.createTable();
				break;
			case "send a pm": lobby.requestPrivateMessage();
				break;
			case "join table": lobby.joinTable();
				break;
			case "spectate": lobby.spectate();
				break;
			case "refresh": lobby.refreshLocalTables();
				break;
			default: System.out.println("U wot m8");
		}
	}

}
