import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;//50px for the text panel on top

    JFrame frame = new JFrame("Tic-Tac-Toe");// frame
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;// if 9 placed cannot play game

    TicTacToe(){// constructor
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//click x to close app
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);// font color
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);// CENTER LEEFT SIDE
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);// create frame upside

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);

        for(int r = 0; r<3; r++){// r-rows 3
            for(int c = 0; c<3; c++){// c- column
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setBackground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
                // tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText()==""){// if empty string put current x
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;// tenary expression
                                textLabel.setText(currentPlayer + "'s turn.");// whose turn is 
                            }
                            

                        }
                        
                    }
                });
                    
            }

        }
    }
    void checkWinner(){
         //horizontal
        for(int r= 0 ;r<3; r++){
            if(board[r][0].getText() =="")continue;// 3 x's
            if(board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
                for(int i = 0; i<3; i++){
                    setWinner(board[r][i]);// row first
                }
                gameOver = true;
                return;

            }
        }
        //vertical
        for(int c = 0; c < 3; c++){
            if(board[0][c].getText() =="") continue;// if tile in firat column it is empty string not be a 3 in 1 column
            if(board[0][c].getText() == board[1][c].getText() &&
                board[1][c].getText() == board[2][c].getText()){
                    for(int i =0;i<3;i++){// setwinner for each tile
                        setWinner(board[i][c]);// column 2nd 
                    }
                    gameOver = true;
                    return;
                }
        }
        //diagonally
        if(board[0][0].getText() == board[1][1].getText()&&// top left
            board[1][1].getText() == board[2][2].getText()&&// bottom right
            board[0][0].getText() != "" ){//first value is not empty
                for(int i = 0;i<3;i++){
                    setWinner(board[i][i]);
                }
                gameOver = true;
                return;
        }
        // anti-diagonally
        if(board[0][2].getText() == board[1][1].getText() &&// 0,2 top right
            board[1][1].getText() == board[2][0].getText() &&//1,1 middle 2,0 botom left
            board[0][2].getText()!=""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;

        }
        if(turns ==9){
            for(int r = 0;r<3;r++){
                for(int c=0;c<3;c++){
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
        }
}
    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer+"is the winner!");
    }

    void setTie(JButton tile){
        tile.setForeground(Color.red);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}

