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
	
	private String checkerState = null;

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
		} else if (color.equalsIgnoreCase("red")){
	    	try {
				checkerImage = ImageIO.read(new File("./resources/RedChecker.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	checkerState = "red";
		} else if (color.equalsIgnoreCase("blackking")) {
	    	try {
				checkerImage = ImageIO.read(new File("./resources/BlackKing.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	checkerState = "blackking";
		} else if (color.equalsIgnoreCase("redking")) {
	    	try {
				checkerImage = ImageIO.read(new File("./resources/RedKing.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	checkerState = "redking";
			
		}
		Image sizedChecker = checkerImage.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(sizedChecker));
	}
	
	public String getColor() {
		return checkerState;
	}
}
