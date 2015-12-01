package game;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Checker extends JLabel {
	
	final int RED = 1;
	final int BLACK = 2;
	private String checkerState = null;
	private boolean canMakeAMove(int row1, int row2, int col1, int col2, int player) {
	  // Need a getLegalMove methode and call this method i think.
	  // Use array for row and column to store moves made.
	         
	      if (player == RED) {
	         if (board[row1][col1] == RED && row2 > row1)
	             return false;  // Red piece move down one direction.
	          return true;  // Legal move.
	      }
	      else {
	         if (board[row1][col1] == BLACK && row2 < row1)
	             return false;  // Black piece can move up one direction.
	          return true;  // Legal move.
	      }
	      
	      if (col2 < 0 || col2 >= 8  || row2 < 0 || row2 >= 8)
	         return false;  // Cant make move no locations.
	    
	      if (board[row2][col2] != EMPTY)
	         return false;  // Square ocupied.
		}  // end canMakeAMove()

	public Checker(String color) {
		//super(new BorderLayout());
		super();
		Image checkerImage = null;
		if (color.equalsIgnoreCase("black")) {
	    	try {
				checkerImage = ImageIO.read(new File("./resources/BlackChecker.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	checkerState = "black";
		} else {
	    	try {
				checkerImage = ImageIO.read(new File("./resources/RedChecker.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	checkerState = "red";
		}
		//checkerImage = checkerImage.getS
		//JLabel tempChecker = new JLabel("", checkerImage, JLabel.CENTER);
		//this.add(tempChecker, BorderLayout.CENTER);
		Image sizedChecker = checkerImage.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(sizedChecker));
	}
	
	public String getColor() {
		return checkerState;
	}
}
