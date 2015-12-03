package tutorial;

import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;

import userInterface.TutorialWindow;

public class Tutorial {
	
	private TutorialWindow tutorialWindow;
	private int currentImagePointer = 0;
	private String[] tutorialMessages = {
			"1. Black moves diagonal left." ,
			"2. Red moves diagonal right.",
			"3. Black moves another checker left.",
			"4. Red moves another checker right.",
			"5. Black moves another checker left.",
			"6. Red moves another checker right.",
			"7. Black checker jumps and captures red checker left.",
			"8. Red checker jumps black checker left.",
			"9. Black moves another checker right.",
			"10. Red moves another checker right.",
			"11. Black moves another checker left.",
			"12. Red checker jumps black checker left.",
			"13. Red checker jumps black che3cker left and gets kinged.",
			"14. Black checker jumps red checker right and gets kinged.",
			"15. Black checker moved backwards right.",
			"16. Red checker moves left in front of black king.",
			"17. Black king jumps and captures red checker right.",
			"18. Red king moves backwards right.",
			"19. Normal black checker jumps and captures red king right.",
			"20. Next top last move of the game.",
			"21. Black checker jumps and captures red checker to win the game.",
			"22. Yeah you won the game.  You are the best checker player that ever lived."
	};
	private int currentMessagePointer = 0;
	private int maxTuts = 22;
	
	public Tutorial() {
		tutorialWindow = new TutorialWindow();
		TutorialButtonListener listener = new TutorialButtonListener(this);
		tutorialWindow.assignListeners(listener);
		setThings();
	}

	public void startTutorial() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tutorialWindow.display(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			});
	}
	
	public void exit() {
		tutorialWindow.display(false);
		
	}

	public void next() {
		if (currentMessagePointer < maxTuts) {
			currentMessagePointer++;
			currentImagePointer++;
			setThings();
		}
//		} else {
//			tutorialWindow.showNext(false);
//			tutorialWindow.showPrev(true);
//		}
				
	}

	public void prev() {
		if (currentMessagePointer >= 0) {
			currentMessagePointer--;
			currentImagePointer--;
			setThings();
		}
//		} else {
//			tutorialWindow.showPrev(false);
//			tutorialWindow.showNext(true);
//		}
			
	}
	
	public void setThings() {
		String filename = "Capture" + currentImagePointer + ".PNG";
		JLabel tutPic = new TutorialPicture(filename);
		String tempMessage = tutorialMessages[currentMessagePointer];
		tutorialWindow.updateText(tempMessage);
		tutorialWindow.updatePicture(tutPic);
	}
}
