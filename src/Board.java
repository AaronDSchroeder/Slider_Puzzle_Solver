import java.util.ArrayList;
import java.util.Random;

public class Board {

    private static final int SIZE = 3;
    private int[][] board;  // Values of board
    private int blankRow;   // Row location of blank
    private int blankCol;   // Column location of blank
    public StringBuilder history = new StringBuilder();
    public Board() {
        board = new int[SIZE][SIZE];
    }

    //Copy constructor
    Board(Board b) {
        //System.out.println( "Just Copied Board\n" + b.toString() +"\n");
        board = new int[SIZE][];
        for (int i = 0; i < SIZE; i++)
            this.board[i] = b.board[i].clone();
        this.blankRow = b.blankRow;
        this.blankCol = b.blankCol;
        this.history = new StringBuilder(b.history);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(board[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    ;


    // return true if board is identical to b
    boolean equals(Board b) {

        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] != b.board[i][j]) return false;
        return true;

    }

//Create a board by performing legal moves on a board
//jumbleCt indicates the number of moves to make
//if jumbleCt ==0, return the winning board
    void makeBoard(int jumbleCt) {
        int val = 1;
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = val++;
        blankRow = SIZE - 1;
        blankCol = SIZE - 1;
        board[blankRow][blankCol] = 0;
        jumble(jumbleCt);
    }

    //Create a board from a given set of values
    void makeBoard(int[] values) {
        int c = 0;
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                if (values[c] == 0) {
                    blankRow = i;
                    blankCol = j;
                }
                board[i][j] = values[c++];
            }
    }

    String makeID(){ // makes a unique String ID for the board.
        String id = "";
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
               id = id + board[i][j];
            }
            return id;
    }


    boolean slideUp()  // If possible, slides a tile up into the blank position.  Returns success of operation.
    {
        if (blankRow == SIZE - 1)return false;
        board[blankRow][blankCol] = board[blankRow + 1][blankCol];
        board[blankRow + 1][blankCol] = 0;
        blankRow += 1;
        return true;
    }

    boolean slideDown()  // If possible, slides a tile down into the blank position.  Returns success of operation.
    {
        if (blankRow == 0) return false;
        board[blankRow][blankCol] = board[blankRow - 1][blankCol];
        board[blankRow - 1][blankCol] = 0;
        blankRow -= 1;
        return true;
    }

    boolean slideLeft()  // If possible, slides a tile left into the blank position.  Returns success of operation.
    {
        if (blankCol == SIZE - 1) return false;
        board[blankRow][blankCol] = board[blankRow][blankCol + 1];
        board[blankRow][blankCol + 1] = 0;
        blankCol += 1;
        return true;
    }

    boolean slideRight()  // If possible, slides a tile right into the blank position.  Returns success of operation.
    {
        if (blankCol == 0) return false;
        board[blankRow][blankCol] = board[blankRow][blankCol - 1];
        board[blankRow][blankCol - 1] = 0;
        blankCol -= 1;
        return true;
    }


    // Randomly apply ct moves to the board, makingsure they are legal and don't undo the previous move
    void jumble(int ct) {
        Random rand = new Random();
        String moveStr = "UDLR";  // Moves representing Up, Down, Left, Right
        char lastMove = ' ';
        char thisMove = ' ';
        for (int i = 0; i < ct; i++) {
            thisMove = ' ';
            while (thisMove == ' ') {
                thisMove = moveStr.charAt(rand.nextInt(4));
                thisMove = makeMove(thisMove, lastMove);
            }
            lastMove = thisMove;
            //System.out.println("JUMBLE Moves" + thisMove + '\n');
        }
    }

    // Make the move indicated by m (L Left, R Right, U Up, D Down) if it is legal and if it doesn't undo the move specified by lastmove
// Return a blank if the move could not be made, otherwise return the move
    char makeMove(char m, char lastmove) {
        //System.out.println( "makeMove " + m + lastmove +"\n");
        boolean moved = false;
        switch (m) {
            case 'R':
                if (lastmove != 'L') {
                    moved = slideRight();
                }
                break;
            case 'L':
                if (lastmove != 'R') {
                    moved = slideLeft();
                }
                break;
            case 'D':
                if (lastmove != 'U') {
                    moved = slideDown();
                }
                break;
            case 'U':
                if (lastmove != 'D') {
                    moved = slideUp();
                }
                break;
        }
        if (!moved)
            return ' ';
        return m;
    }

    public static void main(String[] args) {
        Board b = new Board();
        int[] values = {1,2,3,4,5,6,8,7,0};
        Board c = new Board();
        c.makeBoard(values);
        b.makeBoard(2);
        System.out.println(b);
        System.out.println(c);

    }
}
