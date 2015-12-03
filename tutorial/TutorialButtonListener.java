package tutorial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialButtonListener implements ActionListener {

	Tutorial tutorial;
	
	public TutorialButtonListener(Tutorial tutorial) {
		this.tutorial = tutorial;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg) {
		System.out.println(arg.getSource());
		switch (arg.getActionCommand().toLowerCase()) {
			case "exit": tutorial.exit();
				break;
			case "next": tutorial.next();
				break;
			case "prev": tutorial.prev();
				break;
			default: System.out.println("U wot m8");
		}
	}

}
