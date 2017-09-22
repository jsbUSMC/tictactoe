package game;

/**
 * Author: John S. Bissonette
 * NetID: jbisson2
 * @since 9/8/2017
 * Assignment: Project 1 - Tic Tac Toe
 */
@SuppressWarnings("WeakerAccess, UnusedDeclaration")
public class Board {
    public enum State {Open, X, O}
    private State[][] gameBoard;
    private State currentTurn;
    private int moves;
    private boolean gameEnded;

    /**
     * Empty constructor with default Tic Tac Toe game set to 3x3. No need for a parameterized constructor
     */
    public Board() {
        this.gameBoard = new State[3][3];
        this.currentTurn = State.X;
        this.gameEnded = false;
    }

    public boolean isGameEnded() {
        return this.gameEnded;
    }

    public State getWinningPlayer() {
        if (this.isGameEnded()) {
            return this.currentTurn;
        }
        return null;
    }

    public State getCurrentTurn() {
        return this.currentTurn;
    }

    /**
     * Advances the player whose turn is next by checking the current value of the
     * currentTurn instance variable
     */
    public void advanceTurn() {
        if (this.currentTurn == State.X) {
            this.currentTurn = State.O;
        } else {
            this.currentTurn = State.X;
        }
    }

    /**
     * Instance method that checks if a given location on the board has already been played yet. We don't want to let
     * players overwrite other's marks.
     * @param loc an integer representing a square on the 3x3 grid. This value is not normalized to the zero-indexed
     *            array used as the game's physical board.
     * @return boolean value true if a spot has been played, false if not.
     */
    public boolean checkValue(int loc) {
        if (loc > 9) {
            throw new IllegalArgumentException("The requested location does not exist!");
        }
        switch (loc) {
            case 1: return (this.gameBoard[0][0] == State.Open);
            case 2: return (this.gameBoard[0][1] == State.Open);
            case 3: return (this.gameBoard[0][2] == State.Open);
            case 4: return (this.gameBoard[1][0] == State.Open);
            case 5: return (this.gameBoard[1][1] == State.Open);
            case 6: return (this.gameBoard[1][2] == State.Open);
            case 7: return (this.gameBoard[2][0] == State.Open);
            case 8: return (this.gameBoard[2][1] == State.Open);
            case 9: return (this.gameBoard[2][2] == State.Open);
            default: return false;
        }
    }

    /**
     * Instance method that records a play for a given player on the board, and calls the #checkValue method to inspect
     * the chosen location before marking the play. If it has already been played, then an IllegalArgumentException is
     * thrown.
     * @param turn an integer representing the current turn number. If this value mod 2 == 1, then it is the first
     *             player's turn, if == 0, then it is second player's turn
     * @param loc an integer referencing a normalized index location on the game board. It is "normalized" in the sense
     *            that the integer represents a 3x3 grid starting at 1, like in the project prompt. The program considers
     *            this to be the canonical representation of the board to a player, and then decrements their chosen
     *            location by 1, rendering the true indexed location which begins at 0.
     */
    public void setMarker(int turn, int loc) {
        if (!checkValue(loc)) {
            throw new IllegalArgumentException("This spot has already been played!");
        }
        if ((turn % 2) == 1) {
            switch (loc) {
                case 1: this.gameBoard[0][0] = State.X;
                case 2: this.gameBoard[0][1] = State.X;
                case 3: this.gameBoard[0][2] = State.X;
                case 4: this.gameBoard[1][0] = State.X;
                case 5: this.gameBoard[1][1] = State.X;
                case 6: this.gameBoard[1][2] = State.X;
                case 7: this.gameBoard[2][0] = State.X;
                case 8: this.gameBoard[2][1] = State.X;
                case 9: this.gameBoard[2][2] = State.X;
            }
        }
        if ((turn % 2) == 0) {
            switch (loc) {
                case 1: this.gameBoard[0][0] = State.O;
                case 2: this.gameBoard[0][1] = State.O;
                case 3: this.gameBoard[0][2] = State.O;
                case 4: this.gameBoard[1][0] = State.O;
                case 5: this.gameBoard[1][1] = State.O;
                case 6: this.gameBoard[1][2] = State.O;
                case 7: this.gameBoard[2][0] = State.O;
                case 8: this.gameBoard[2][1] = State.O;
                case 9: this.gameBoard[2][2] = State.O;
            }
        }
    }

    public State[][] getGameBoard() {
        return gameBoard;
    }

    public void printBoard() {
        State[][] board = this.getGameBoard();

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
        int p1 = 1;
        int p2 = 2;

        b.setMarker(p1, 5);
        b.setMarker(p2, 1);
    }
}
