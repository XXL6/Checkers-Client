package game;

import javax.swing.JPanel;

public class CheckerBoard extends JPanel {
  
  /*
  Just a quick thing I made to populate a 2d array in the right checkers positions, though there was probably an easier
  to do it. It can easily be altered to make the array populated by checkers instances.
  */
  int board[][] = new int[8][8];
    public CheckersBoard(){
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
