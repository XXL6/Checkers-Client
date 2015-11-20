import javax.swing.JFrame;

import game.CheckerBoard;

public class MainForTesting {
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		CheckerBoard board = new CheckerBoard();
		frame.add(board);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 800);
	}
}
