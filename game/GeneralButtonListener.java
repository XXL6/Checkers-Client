package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralButtonListener implements ActionListener {

	Game game;
	
	public GeneralButtonListener(Game game) {
		this.game = game;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg) {
		System.out.println(arg.getSource());
		switch (arg.getActionCommand().toLowerCase()) {
			case "send": game.sendMessage();
				break;
			case "clear chat": game.clearChat();
				break;
			case "disconnect": game.disconnect();
				break;
			case "leave table": game.leaveTable();
				break;
			default: System.out.println("U wot m8");
		}
	}

}
