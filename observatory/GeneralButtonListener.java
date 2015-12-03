package observatory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralButtonListener implements ActionListener {

	Observer observer;
	
	public GeneralButtonListener(Observer observer) {
		this.observer = observer;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg) {
		System.out.println(arg.getSource());
		if (arg.getActionCommand().toLowerCase().contains("exit"))
			observer.stopObserving();
//		switch (arg.getActionCommand().toLowerCase()) {
//			case "send": observer.sendMessage();
//				break;
//			default: System.out.println("U wot m8");
//		}
	}

}
