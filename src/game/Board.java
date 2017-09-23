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
@SuppressWarnings("WeakerAccess, UnusedDeclaration")
public class Board {
    public enum State {Blank, X, O}

    private State[][] board;
    private State playersTurn;
    private State winner;
    // TODO: comment why this is a HashSet and not an ArrayList
    private HashSet<Integer> movesAvailable;

    private int moveCount;
    private boolean gameOver;

    /**
     * Construct the Tic Tac Toe board.
     */
    Board() {
        board = new State[3][3];
        movesAvailable = new HashSet<>();
        reset();
    }


    /**
     * Set the cells to be blank and load the available moves (all the moves are
     * available at the start of the game).
     */
    private void initialize() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = State.Blank;
            }
        }

        movesAvailable.clear();

        for (int i = 0; i < 9; i++) {
            movesAvailable.add(i);
        }
    }

    /**
     * Restart the game with a new blank board.
     */
    void reset() {
        moveCount = 0;
        gameOver = false;
        playersTurn = State.X;
        winner = State.Blank;
        initialize();
    }

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

        if (gameOver) {
            throw new IllegalStateException("TicTacToe is over. No moves can be played.");
        }

        if (board[y][x] == State.Blank) {
            board[y][x] = playersTurn;
        } else {
            return false;
        }

        moveCount++;
        movesAvailable.remove((y * 3) + x);

        // The game is a draw.
        if (moveCount == 9) {
            winner = State.Blank;
            gameOver = true;
        }

        // Check for a winner.
        checkRow(y);
        checkColumn(x);
        checkDiagonalFromTopLeft(x, y);
        checkDiagonalFromTopRight(x, y);

        playersTurn = (playersTurn == State.X) ? State.O : State.X;
        return true;
    }

    /**
     * Check to see if the game is over (if there is a winner or a draw).
     *
     * @return true if the game is over
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Get a copy of the array that represents the board.
     *
     * @return the board array
     */
    State[][] toArray() {
        return board.clone();
    }

    /**
     * Check to see who's turn it is.
     *
     * @return the player who's turn it is
     */
    public State getTurn() {
        return playersTurn;
    }

    /**
     * Check to see who won.
     *
     * @return the player who won (or Blank if the game is a draw)
     */
    public State getWinner() {
        if (!gameOver) {
            throw new IllegalStateException("TicTacToe is not over yet.");
        }
        return winner;
    }

    /**
     * Get the indexes of all the positions on the board that are empty.
     *
     * @return the empty cells
     */
    public HashSet<Integer> getAvailableMoves() {
        return movesAvailable;
    }

    /**
     * Checks the specified row to see if there is a winner.
     *
     * @param row the row to check
     */
    private void checkRow(int row) {
        for (int i = 1; i < 3; i++) {
            if (board[row][i] != board[row][i - 1]) {
                break;
            }
            if (i == 2) {
                winner = playersTurn;
                gameOver = true;
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
            if (board[i][column] != board[i - 1][column]) {
                break;
            }
            if (i == 2) {
                winner = playersTurn;
                gameOver = true;
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
                if (board[i][i] != board[i - 1][i - 1]) {
                    break;
                }
                if (i == 2) {
                    winner = playersTurn;
                    gameOver = true;
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
                if (board[2 - i][i] != board[3 - i][i - 1]) {
                    break;
                }
                if (i == 2) {
                    winner = playersTurn;
                    gameOver = true;
                }
            }
        }
    }

    /**
     * Get a deep copy of the Tic Tac Toe board.
     *
     * @return an identical copy of the board
     */
    public Board getDeepCopy() {
        Board board = new Board();

        for (int i = 0; i < board.board.length; i++) {
            board.board[i] = this.board[i].clone();
        }

        board.playersTurn = this.playersTurn;
        board.winner = this.winner;
        board.movesAvailable = new HashSet<>();
        board.movesAvailable.addAll(this.movesAvailable);
        board.moveCount = this.moveCount;
        board.gameOver = this.gameOver;
        return board;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {

                if (board[y][x] == State.Blank) {
                    sb.append("-");
                } else {
                    sb.append(board[y][x].name());
                }
                sb.append(" ");

            }
            if (y != 2) {
                sb.append("\n");
            }
        }

        return new String(sb);
    }
}
