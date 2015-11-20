package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

import javax.swing.JPanel;

public class CheckerBoard extends JPanel {
  
	private static BufferedImage checkerBoardImage;
	private int boardWidth, boardHeight;
	private int board[][] = new int[8][8];
  /*
  Just a quick thing I made to populate a 2d array in the right checkers positions, though there was probably an easier
  to do it. It can easily be altered to make the array populated by checkers instances.
  */
  
  
    public CheckerBoard(){
    	
    	try {
			checkerBoardImage = ImageIO.read(new File("./resources/Board.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	boardWidth = checkerBoardImage.getWidth(this) / 2;
    	boardHeight = checkerBoardImage.getHeight(this) / 2;
    	resizeBoard(500, 500);
    	
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            if(((i<3 && (j%2)== 0 && i!=1) )|| ((i == 1 && (j%2) != 0))){
                board[i][j] = 1;
        }else if((i>4 && (j%2 !=0) && i !=6) || (i == 6 && (j%2) == 0)){
                board[i][j] = 2;
            }
        else{
            board[i][j] = 0;
        }
            }
    }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j]+ ", ");
            }
            System.out.println("");
        }
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	if (checkerBoardImage != null) {
//    		int x = this.getParent().getWidth()/2 - boardWidth;
//    		int y = this.getParent().getHeight()/2 - boardHeight;
//    		g.drawImage(checkerBoardImage, x, y, this);
    		g.drawImage(checkerBoardImage, 0, 0, null);
    	}
    }
    /*
     * http://stackoverflow.com/questions/9417356/bufferedimage-resize/9417836#9417836
     * added to size the board appropriately
     */
    public static BufferedImage resizeBoard(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }  
    
    public static void resizeBoard(int newWidth, int newHeight) {
    	checkerBoardImage = resizeBoard(checkerBoardImage, newWidth, newHeight);
    }
    
    public int getBoardHeight() {
    	return this.boardHeight;
    }
    
    public int getBoardWidth() {
    	return this.boardWidth;
    }
}
