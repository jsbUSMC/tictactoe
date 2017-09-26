package game;

import java.util.*;

// TODO: flesh out all the comments. Make sure to add them to the writeup
/**
 * The Board class contains all the logic and functionality to interact with a game of Tic Tac Toe.
 * Author: John S. Bissonette
 * NetID: jbisson2
 *
 * @since 9/8/2017
 * Assignment: Project 1 - Tic Tac Toe
 */
@SuppressWarnings("WeakerAccess, Duplicates")
public class Board {
    //public enum State {Blank, X, O}

    private State[][] board;
    private State playersTurn;
    private State winner;
    // TODO: comment why this is a HashSet and not an ArrayList
    private HashSet<Integer> availableMoves;

    private int moveCount;
    private boolean gameOver;

    //region Constructor and Setup Methods
    /**
     * Construct the Tic Tac Toe board.
     */
    Board() {
        setBoard(new State[3][3]);
        setAvailableMoves(new HashSet<>());
        reset();
    }

    /**
     * Set the cells to be blank and load the available moves (all the moves are
     * available at the start of the game).
     */
    private void initialize() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                getBoard()[row][col] = State.Blank;
            }
        }

        getAvailableMoves().clear();

        for (int i = 0; i < 9; i++) {
            getAvailableMoves().add(i);
        }
    }

    /**
     * Restart the game with a new blank board.
     */
    void reset() {
        setMoveCount(0);
        setGameOver(false);
        setPlayersTurn(State.X);
        setWinner(State.Blank);
        initialize();
    }
    //endregion

    /**
     * Places an X or an O on the specified index depending on whose turn it is.
     *
     * @param index the position on the board (example: index 4 is location (0, 1))
     * @return true if the move has not already been played
     */
    public boolean move(int index) {
        return move(index % 3, index / 3);
    }

    /**
     * Places an X or an O on the specified location depending on who turn it is.
     *
     * @param x the x coordinate of the location
     * @param y the y coordinate of the location
     * @return true if the move has not already been played
     */
    private boolean move(int x, int y) {

        if (isGameOver()) {
            throw new IllegalStateException("TicTacToe is over. No moves can be played.");
        }

        if (getBoard()[y][x] == State.Blank) {
            getBoard()[y][x] = getPlayersTurn();
        } else {
            return false;
        }

        setMoveCount(getMoveCount() + 1);
        getAvailableMoves().remove((y * 3) + x);

        // The game is a draw.
        if (getMoveCount() == 9) {
            setWinner(State.Blank);
            setGameOver(true);
        }

        // Check for a winner.
        checkRow(y);
        checkColumn(x);
        checkDiagonalFromTopLeft(x, y);
        checkDiagonalFromTopRight(x, y);

        setPlayersTurn((getPlayersTurn() == State.X) ? State.O : State.X);
        return true;
    }

    //region Check Victory Conditions
    /**
     * Checks the specified row to see if there is a winner.
     *
     * @param row the row to check
     */
    private void checkRow(int row) {
        for (int i = 1; i < 3; i++) {
            if (getBoard()[row][i] != getBoard()[row][i - 1]) {
                break;
            }
            if (i == 2) {
                setWinner(getPlayersTurn());
                setGameOver(true);
            }
        }
    }

    /**
     * Checks the specified column to see if there is a winner.
     *
     * @param column the column to check
     */
    private void checkColumn(int column) {
        for (int i = 1; i < 3; i++) {
            if (getBoard()[i][column] != getBoard()[i - 1][column]) {
                break;
            }
            if (i == 2) {
                setWinner(getPlayersTurn());
                setGameOver(true);
            }
        }
    }

    /**
     * Check the left diagonal to see if there is a winner.
     *
     * @param x the x coordinate of the most recently played move
     * @param y the y coordinate of the most recently played move
     */
    private void checkDiagonalFromTopLeft(int x, int y) {
        if (x == y) {
            for (int i = 1; i < 3; i++) {
                if (getBoard()[i][i] != getBoard()[i - 1][i - 1]) {
                    break;
                }
                if (i == 2) {
                    setWinner(getPlayersTurn());
                    setGameOver(true);
                }
            }
        }
    }

    /**
     * Check the right diagonal to see if there is a winner.
     *
     * @param x the x coordinate of the most recently played move
     * @param y the y coordinate of the most recently played move
     */
    private void checkDiagonalFromTopRight(int x, int y) {
        if (2 - x == y) {
            for (int i = 1; i < 3; i++) {
                if (getBoard()[2 - i][i] != getBoard()[3 - i][i - 1]) {
                    break;
                }
                if (i == 2) {
                    setWinner(getPlayersTurn());
                    setGameOver(true);
                }
            }
        }
    }
    //endregion

    //region Utility Methods
    /**
     * Get a deep copy of the Tic Tac Toe board.
     *
     * @return an identical copy of the board
     */
    public Board generateSuccessor() {
        Board board = new Board();

        for (int i = 0; i < board.getBoard().length; i++) {
            board.getBoard()[i] = this.getBoard()[i].clone();
        }

        board.setPlayersTurn(this.getPlayersTurn());
        board.setWinner(this.getWinner());
        board.setAvailableMoves(new HashSet<>());
        board.getAvailableMoves().addAll(this.getAvailableMoves());
        board.setMoveCount(this.getMoveCount());
        board.setGameOver(this.isGameOver());
        return board;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {

                if (getBoard()[x][y] == State.Blank) {
                    sb.append("-");
                } else {
                    sb.append(getBoard()[x][y].name());
                }
                sb.append(" ");

            }
            if (x != 2) {
                sb.append("\n");
            }
        }

        return new String(sb);
    }
    //endregion

    //region Mutators and Accessors
    public State[][] getBoard() {
        return board;
    }

    public void setBoard(State[][] board) {
        this.board = board;
    }

    public State getPlayersTurn() {
        return playersTurn;
    }

    /**
     * Check to see who's turn it is.
     *
     * @return the player who's turn it is
     */
    public State getTurn() {
        return getPlayersTurn();
    }

    public void setPlayersTurn(State playersTurn) {
        this.playersTurn = playersTurn;
    }

    /**
     * Check to see who won.
     *
     * @return the player who won (or Blank if the game is a draw)
     */
    public State getWinner() {
//        if (!isGameOver()) {
//            throw new IllegalStateException("TicTacToe is not over yet.");
//        }
        return winner;
    }

    public void setWinner(State winner) {
        this.winner = winner;
    }

    /**
     * Get the indexes of all the positions on the board that are empty.
     *
     * @return the empty cells
     */
    public HashSet<Integer> getAvailableMoves() {
        return availableMoves;
    }

    public void setAvailableMoves(HashSet<Integer> movesAvailable) {
        this.availableMoves = movesAvailable;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    /**
     * Check to see if the game is over (if there is a winner or a draw).
     *
     * @return true if the game is over
     */
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    //endregion

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(9);
        Set<Integer> hs = new HashSet<>(9);
        for (int i = 0; i < 9; i++) {
            list.add(i);
            hs.add(i);
        }

        list.remove(3);
        hs.remove(3);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(String.format("%d, ", i));
        }

        System.out.println();

        for (Integer i : hs) {
            System.out.print(String.format("%d, ", i));
        }
    }
}
