package game;

import java.util.*;

/**
 * The Board class contains all the logic and functionality to interact with a game of Tic Tac Toe.
 * Author: John S. Bissonette
 * NetID: jbisson2
 * @since 9/8/2017
 * Assignment: Project 1 - Tic Tac Toe
 */
@SuppressWarnings("WeakerAccess, UnusedDeclaration")
public class Board {
    public enum State {Open, X, O, Draw, Victory}
    private State[][] gameBoard;
    private State currentTurn;
    private int moves;
    private boolean gameEnded;
    // TODO: comment why this is a HashSet and not an ArrayList
    private Set<Integer> availableMoves;
    private State winningPlayer;

    /**
     * Empty constructor with default Tic Tac Toe game set to 3x3. No need for a parameterized constructor
     */
    public Board() {
        this.gameBoard = new State[3][3];
        this.currentTurn = State.X;
        this.gameEnded = false;
        initializeAvailableMoves();
        this.winningPlayer = null;
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

    public Set<Integer> getAvailableMoves() {
        return this.availableMoves;
    }

    public void initializeAvailableMoves() {
        this.availableMoves = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            availableMoves.add(i);
        }
    }

    public void setWinningPlayer(State player) {
        this.winningPlayer = player;
    }

    /**
     * Advances the player whose turn is next by checking the current value of the
     * currentTurn instance variable. Uses the ternary operator for brevity
     */
    public void advanceTurn() {
        this.currentTurn = (this.currentTurn == State.X) ? State.O : State.X;
    }

    /**
     * Instance method that checks if a given location on the board has already been played yet. We don't want to let
     * players overwrite other's marks.
     * @param loc an integer representing a square on the 3x3 grid. This value is not normalized to the zero-indexed
     *            array used as the game's physical board.
     * @return boolean value true if a spot has been played, false if not.
     */
    public boolean checkForOpenValue(int loc) {
        if (loc > 9) {
            throw new IllegalArgumentException("The requested location does not exist!");
        }
        return mapLocationToIndex(loc) == State.Open;
    }

    /**
     * Instance method that records a play for a given player on the board, and calls the #checkForOpenValue method to
     * inspect the chosen location before marking the play. If it has already been played, then an
     * IllegalArgumentException is thrown.
     * @param player a State object representing the current player. This can be either the AI or a human player, and
     *               the human can choose whether or not they want to be either X or O, so we don't specify which one
     *               discretely.
     * @param loc an integer referencing a normalized index location on the game board. It is "normalized" in the sense
     *            that the integer represents a 3x3 grid starting at 1, like in the project prompt. The program considers
     *            this to be the canonical representation of the board to a player, and then decrements their chosen
     *            location by 1, rendering the true indexed location which begins at 0.
     */
    public void move(State player, int loc) {
        if (!checkForOpenValue(loc)) {
            throw new IllegalArgumentException("This spot has already been played!");
        }
        setMappedIndexLocationToValue(player, loc);
        availableMoves.remove(loc - 1);
        this.moves++;
        advanceTurn();
        if (moves == 9) {
            this.setWinningPlayer(State.Draw);
        }
    }

    public void playNextMove(int loc) {
        // TODO: Figure out if this is even necessary
        checkForOpenValue(loc);
    }

    /**
     * Checks to see if there is a victory condition in a given row.
     * @param rowIndex the row's index within the gameBoard
     * @return State object representing the victorious player, null if no victory
     */
    public State checkRowVictory(int rowIndex) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[rowIndex][i] != gameBoard[rowIndex][i - 1]) {
                return null;
            }
            if (i == 2) {
                this.setWinningPlayer(this.currentTurn);
                this.gameEnded = true;
                return State.Victory;
            }
        }
        return null;
    }

    /**
     * @param columnIndex the column's index within the gameBoard
     * @return State object representing the victorious player, null if no victory
     */
    public State checkColumnVictory(int columnIndex) {
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][columnIndex] != gameBoard[i - 1][columnIndex]) {
                return null;
            }
            if (i == 2) {
                this.setWinningPlayer(this.currentTurn);
                this.gameEnded = true;
                return State.Victory;
            }
        }
        return null;
    }

    /**
     * Checks to see if the left diagonal contains a victory condition. The left diagonal is the main diagonal of the
     * game matrix: from upper left corner to bottom right.
     * @return The State object representing the victorious player if a left diagonal victory condition is met.
     */
    public State checkLeftDiagonalVictory() {
        if (gameBoard[0][0] == State.X && gameBoard[1][1] == State.X & gameBoard[2][2] == State.X) {
            this.winningPlayer = State.X;
            return State.X;
        } else if (gameBoard[0][0] == State.O && gameBoard[1][1] == State.O & gameBoard[2][2] == State.O) {
            this.winningPlayer = State.O;
            return State.O;
        }
        return null;
    }

    /**
     * Checks to see if the victory conditions for a right diagonal victory are met. A right diagonal victory is the
     * diagonal starting from upper right corner to bottom left.
     * @return The State object representing the winning player if a right diagonal victory condition is met.
     */
    public State checkRightDiagonalVictory() {
        if (gameBoard[2][0] == State.X && gameBoard[1][1] == State.X & gameBoard[0][2] == State.X) {
            this.winningPlayer = State.X;
            return State.X;
        } else if (gameBoard[2][0] == State.O && gameBoard[1][1] == State.O & gameBoard[0][2] == State.O) {
            this.winningPlayer = State.O;
            return State.O;
        }
        return null;
    }

    // TODO: add comments
    public State mapLocationToIndex(int loc) {
        return gameBoard[(loc - 1) / 3][(loc - 1) % 3];
    }

    // TODO: add comments
    public void setMappedIndexLocationToValue(State player, int loc) {
        gameBoard[(loc - 1) / 3][(loc - 1) % 3] = player;
    }

    // Todo: Comment this method
    public Board deepClone() {
        Board board = new Board();
        
        for (int i = 0; i < board.getGameBoard().length; i++) {
            board.gameBoard[i] = this.gameBoard[i].clone();
        }
        
        board.currentTurn = this.currentTurn;
        board.moves = this.moves;
        board.gameEnded = this.gameEnded;
        
        return board;
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

        b.availableMoves.remove(3);
        b.availableMoves.remove(0);
        b.availableMoves.remove(6);

        b.availableMoves.forEach(System.out::println);

        for (int i = 0; i < 9; i++) {
            int row = i / 3;
            int col = i % 3;
            int computedIndex = row * 3 + col;
            System.out.println(String.format("index %d: (%d, %d)", computedIndex, row, col));
        }
    }
}
