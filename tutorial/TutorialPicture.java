package tutorial;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TutorialPicture extends JLabel {

	BufferedImage image;
	
	public TutorialPicture(String filename) {
    	try {
    		image = ImageIO.read(getClass().getClassLoader().getResource("resources/tutorial/" + filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int iBoardWidth = image.getWidth(this) / 2;
    	int iBoardHeight = image.getHeight(this) / 2;
    	//image = resizeBoard(image, 500, 500);
    	Image sizedImage = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
    	//image = resizeBoard(image, 500, 500);
    	ImageIcon icon = new ImageIcon(sizedImage);
    	this.setIcon(icon);

	}
	
//    public void paintComponent(Graphics g) {
//    	super.paintComponent(g);
//    	if (image != null) {
////    		int x = this.getParent().getWidth()/2 - boardWidth;
////    		int y = this.getParent().getHeight()/2 - boardHeight;
////    		g.drawImage(checkerBoardImage, x, y, this);
//    		g.drawImage(image, 0, 0, null);
//    	}
//    }
    
    public BufferedImage resizeBoard(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }  
}
