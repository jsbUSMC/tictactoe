import java.util.Arrays;

/**
 * Author: John S. Bissonette
 * NetID: jbisson2
 * @since 9/8/2017
 * Assignment: Project 1 - Tic Tac Toe
 */
@SuppressWarnings("WeakerAccess, UnusedDeclaration")
public class Board {
    private String[] gameBoard;

    /**
     * Empty constructor with default Tic Tac Toe game set to 3x3. No need for a parameterized constructor
     */
    public Board() {
        this.gameBoard = new String[9];
        Arrays.fill(this.gameBoard, "");
    }

    /**
     * Instance method that checks if a given location on the board has already been played yet. We don't want to let
     * players overwrite other's marks.
     * @param loc an integer representing a square on the 3x3 grid. This value is not normalized to the zero-indexed
     *            array used as the game's physical board.
     * @return boolean value true if a spot has been played, false if not.
     */
    public boolean checkValue(int loc) {
        if (loc >= this.gameBoard.length) {
            throw new IllegalArgumentException("The requested location does not exist!");
        }
        return !(this.gameBoard[loc].equals("") || this.gameBoard[loc] == null);
    }

    /**
     * Instance method that records a play for a given player on the board, and calls the #checkValue method to inspect
     * the chosen location before marking the play. If it has already been played, then an IllegalArgumentException is
     * thrown.
     * @param player a String referencing a player's Mark (Player 1: "X", Player 2: "O")
     * @param loc an integer referencing a normalized index location on the game board. It is "normalized" in the sense
     *            that the integer represents a 3x3 grid starting at 1, like in the project prompt. The program considers
     *            this to be the canonical representation of the board to a player, and then decrements their chosen
     *            location by 1, rendering the true indexed location which begins at 0.
     */
    public void setMarker(String player, int loc) {
        if (checkValue(loc - 1)) {
            throw new IllegalArgumentException("This spot has already been played!");
        }
        this.gameBoard[loc - 1] = player;
    }

    public String[] getGameBoard() {
        return gameBoard;
    }

    public void printBoard() {
        String[] board = this.getGameBoard();

        System.out.println();
        System.out.println("_____________");
        System.out.println(String.format("| %s | %s | %s |", board[0], board[1], board[2]));
        System.out.println("_____________");
        System.out.println(String.format("| %s | %s | %s |", board[3], board[4], board[5]));
        System.out.println("_____________");
        System.out.println(String.format("| %s | %s | %s |", board[6], board[7], board[8]));
        System.out.println("_____________");
        System.out.println();
    }

    public void printInitialBoardState() {
        System.out.println();
        System.out.println("_____________");
        System.out.println(String.format("| %d | %d | %d |", 1, 2, 3));
        System.out.println("_____________");
        System.out.println(String.format("| %d | %d | %d |", 4, 5, 6));
        System.out.println("_____________");
        System.out.println(String.format("| %d | %d | %d |", 7, 8, 9));
        System.out.println("_____________");
        System.out.println();
    }

    public static void main(String[] args) {
        Board b = new Board();
        String p1 = "X";
        String p2 = "O";

        b.setMarker(p1, 5);
        b.setMarker(p2, 1);
    }
}
