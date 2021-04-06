import java.util.*;
public class Game {

    public void playGiven( String label, Board b){
        System.out.println(label + "\n"+  b);
        solve(b);
    }

    public void playRandom( String label, int jumbleCount){
        Board b = new Board();
        b.makeBoard(jumbleCount);
        System.out.println(label + "\n" + b);
        solve(b);
    }

    public void solve(Board b){
        Queue<Board> boardQueue = new Queue<>();
        boardQueue.add(b);
        Board solved = new Board();
        solved.makeBoard(0);
        Board nextBoard = new Board(b);
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert(b.makeID()); // creates a unique String for the board and inserts it on a binary search tree for later reference.
        boolean done = false;

        while((!done) && boardQueue.getHead() != null) {
            nextBoard = boardQueue.pop();
            String moveStr = "UDLR";
            char lastMove = ' ';
            char thisMove = ' ';

            for (int i = 0; i < 4; i++) {
                Board move = new Board(nextBoard);
                thisMove = moveStr.charAt(i);
                thisMove = move.makeMove(thisMove, lastMove);

                if(move.history.length() != 0) {
                    lastMove = move.history.charAt(move.history.length() - 1);
                }
                // if the move and an insert to the BST was successful, the board's history will be updated and it will be added the the queue.
                if((thisMove != ' ') && tree.insert(move.makeID())) {
                    move.history.append(thisMove);

                    if(move.equals(solved)){
                        done = true;
                        this.showMe(b, move.history.toString());
                        break;
                    }
                    boardQueue.add(move);
                }
            }
        }
        if(boardQueue.getHead() == null){
            System.out.println("The Board is not solvable.");
        }
        boardQueue.showQueueStats();
    }

    public void showMe(Board b, String moves){
    System.out.println("Original Board  Moves " + moves);
    System.out.println(b.toString());
    for(int i = 0; i < (moves.length()); i++){
        char thisMove = moves.charAt(i);
        char move = b.makeMove(thisMove, ' ' );
        System.out.println(thisMove + "==>");
        System.out.println(b.toString());
    }
    System.out.println("=>Show Me Moves Required: " + moves.length());
    }

    public static void main(String[] args) {
       Game g = new Game();
        Scanner in = new Scanner(System.in);

        int [] game0 = { 8, 7, 4, 1, 5, 6, 2, 3, 0 };
        Board b = new Board();
        b.makeBoard(game0);
        g.playGiven("game 0", b);
        System.out.println("Click any key to continue\n");
        String resp;
        resp= in.nextLine();

        int []game1 = { 1, 3, 2, 4, 5, 6, 8, 7, 0 };
        b.makeBoard(game1);
        g.playGiven("game 1", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game2 = { 1, 3, 8, 6, 2, 0, 5, 4, 7 };
        b.makeBoard(game2);
        g.playGiven("game 2", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game3 = { 4, 0, 1, 3, 5, 2, 6, 8, 7 };
        b.makeBoard(game3);
        g.playGiven("game 3", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game4 = { 7, 6, 4, 0, 8, 1, 2, 3, 5 };  // Warning slow to solve
        b.makeBoard(game4);
        g.playGiven("game 4", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

        int []game5 = { 1, 2, 3, 4, 5, 6, 8, 7, 0 };   // Warning unsolvable
        b.makeBoard(game5);
        g.playGiven("game 5", b);
        System.out.println("Click any key to continue\n");
        resp= in.nextLine();

       boolean playAgain = true;

	   int JUMBLECT = 4;  // how much jumbling to to in random board
        while (playAgain)
        {

            g.playRandom("Random Board", JUMBLECT);

            System.out.println("Play Again?  Answer Y for yes\n");
            resp= in.nextLine().toUpperCase();
            playAgain = (resp.charAt(0) == 'Y');
        }


    }


}
