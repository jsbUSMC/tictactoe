package game;

import java.util.HashSet;

/**
 * The NineBoard class contains all the logic and functionality to interact with a game of 9-Board Tic Tac Toe.
 * Author: John S. Bissonette
 * NetID: jbisson2
 *
 * @since 9/24/2017
 * Assignment: Project 1 - Tic Tac Toe
 */
@SuppressWarnings("WeakerAccess, UnusedDeclaration, Duplicates")
public class NineBoard {
    private Board[][] gameGrid;
    private State playersTurn;
    private State winner;
    private HashSet<Integer> movesAvailable;
    private int moveCount;
    private boolean gameOver;

    public NineBoard() {
        this.setGameGrid(new Board[3][3]);
        this.setMovesAvailable(new HashSet<>());
        reset();
    }

    private void initialize() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                this.getGameGrid()[row][col] = new Board();
            }
        }

        this.getMovesAvailable().clear();

        for (int i = 0; i < 9; i++) {
            getMovesAvailable().add(i);
        }
    }

    /**
     * Restart the game with a new blank board.
     */
    void reset() {
        setMoveCount(0);
        setGameOver(false);
        setPlayersTurn(State.X);
        setWinner(State.Open);
        initialize();
    }

    /*public boolean move(int index) {
        return move(index % 3, index %3);
    }*/

    /*private boolean move(int x, int y) {
        if (gameOver) {
            throw new IllegalStateException("TicTacToe is over. No moves can be played.");
        }

        if (board[y][x] == State.Open) {
            board[y][x] = playersTurn;
        } else {
            return false;
        }

        moveCount++;
        movesAvailable.remove((y * 3) + x);

        // The game is a draw.
        if (moveCount == 9) {
            winner = State.Open;
            gameOver = true;
        }

        // Check for a winner.
        checkRow(y);
        checkColumn(x);
        checkDiagonalFromTopLeft(x, y);
        checkDiagonalFromTopRight(x, y);

        playersTurn = (playersTurn == State.X) ? State.O : State.X;
        return true;
    }*/

    /**
     * Get a copy of the array that represents the board.
     *
     * @return the board array
     */
/*
    State[][] toArray() {
        return board.clone();
    }
*/

    /**
     * Check to see who's turn it is.
     *
     * @return the player who's turn it is
     */
    public State getTurn() {
        return playersTurn;
    }

    /**
     * Get the indexes of all the positions on the board that are empty.
     *
     * @return the empty cells
     */
    public HashSet<Integer> getAvailableMoves() {
        return movesAvailable;
    }

    public void move(int board, int index) {
        // select the board at board index and the loc on board with index
    }

    /**
     * Checks the specified row to see if there is a winner.
     *
     * @param row the row to check
     */
    /*private void checkRow(int row) {
        for (int i = 1; i < 3; i++) {
            if (board[row][i] != board[row][i - 1]) {
                break;
            }
            if (i == 2) {
                winner = playersTurn;
                gameOver = true;
            }
        }
    }*/

    /**
     * Checks the specified column to see if there is a winner.
     *
     * @param column the column to check
     */
    /*private void checkColumn(int column) {
        for (int i = 1; i < 3; i++) {
            if (board[i][column] != board[i - 1][column]) {
                break;
            }
            if (i == 2) {
                winner = playersTurn;
                gameOver = true;
            }
        }
    }*/

    /**
     * Check the left diagonal to see if there is a winner.
     *
     * @param x the x coordinate of the most recently played move
     * @param y the y coordinate of the most recently played move
     */
    /*private void checkDiagonalFromTopLeft(int x, int y) {
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
    }*/

    /**
     * Check the right diagonal to see if there is a winner.
     *
     * @param x the x coordinate of the most recently played move
     * @param y the y coordinate of the most recently played move
     *//*
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
    }*/

    public NineBoard getDeepCopy() {
        NineBoard grid = new NineBoard();

        for (int i = 0; i < grid.getGameGrid().length; i++) {
            // do something
        }
        return grid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // First row of games
        // First board row
        sb.append(this.gameGrid[0][0].getBoard()[0][0].name());
        sb.append(" ");
        sb.append(this.gameGrid[0][0].getBoard()[0][1].name());
        sb.append(" ");
        sb.append(this.gameGrid[0][0].getBoard()[0][2].name());
        sb.append(" ");
        // Dividers
        sb.append("  ||  ");
        // Second board row
        sb.append(this.gameGrid[0][1].getBoard()[0][0].name());
        sb.append(" ");
        sb.append(this.gameGrid[0][1].getBoard()[0][1].name());
        sb.append(" ");
        sb.append(this.gameGrid[0][1].getBoard()[0][2].name());
        sb.append(" ");
        // Dividers
        sb.append("  ||  ");
        // Third board row
        sb.append(this.gameGrid[0][2].getBoard()[0][0].name());
        sb.append(" ");
        sb.append(this.gameGrid[0][2].getBoard()[0][1].name());
        sb.append(" ");
        sb.append(this.gameGrid[0][2].getBoard()[0][2].name());

        return new String(sb);
    }

    //region Mutators and Accessors
    public Board[][] getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(Board[][] gameGrid) {
        this.gameGrid = gameGrid;
    }

    public State getPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(State playersTurn) {
        this.playersTurn = playersTurn;
    }

    public State getWinner() {
        return winner;
    }

    public void setWinner(State winner) {
        this.winner = winner;
    }

    public HashSet<Integer> getMovesAvailable() {
        return movesAvailable;
    }

    public void setMovesAvailable(HashSet<Integer> movesAvailable) {
        this.movesAvailable = movesAvailable;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    //endregion

//    public void betterPrint() {
//        for (int i = 0; i < 3; i++) {
//            System.out.print(gameGrid[i][0].getBoard()[0]);
//            System.out.print("  ");
//        }
//    }

    public static void main(String[] args) {
        NineBoard game = new NineBoard();
        System.out.print(game);
    }
}
