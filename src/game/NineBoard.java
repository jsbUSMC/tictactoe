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
@SuppressWarnings("WeakerAccess, Duplicates")
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

    public boolean move(int board, int index) {
        int boardX = board / 3;
        int boardY = board % 3;
        return move(boardX, boardY, index);
    }

    private boolean move(int boardX, int boardY, int loc) {
        if (gameOver) {
            throw new IllegalStateException("TicTacToe is over. No moves can be played.");
        }

        gameGrid[boardX][boardY].move(loc);

        moveCount++;
        movesAvailable.remove((boardX * 3) + boardY);

        // The game is a win
        if (gameGrid[boardX][boardY].getWinner() != State.Open) {
            winner = gameGrid[boardX][boardY].getWinner();
            gameOver = true;
        }

        playersTurn = (playersTurn == State.X) ? State.O : State.X;
        return true;
    }


    /**
     * Check to see whose turn it is to play
     *
     * @return the player who's turn it is
     */
    public State getTurn() {
        return playersTurn;
    }

    /**
     * Get the indices of all the positions on the board that are empty, for each of the boards
     * This method is incomplete
     *
     * @return the empty cells
     */
    public HashSet<Integer> getAvailableMoves() {
        return movesAvailable;
    }

    public NineBoard generateSuccessors() {
        NineBoard grid = new NineBoard();

        for (int i = 0; i < grid.getGameGrid().length; i++) {
            grid.getGameGrid()[i] = this.getGameGrid()[i].clone();
            // do something
        }

        // Set all current game conditions on the generated successor state
        grid.setPlayersTurn(this.getPlayersTurn());
        grid.setWinner(this.getWinner());
        grid.setMovesAvailable(new HashSet<>());
        grid.getAvailableMoves().addAll(this.getAvailableMoves());
        grid.setMoveCount(this.getMoveCount());
        grid.setGameOver(this.isGameOver());

        return grid;
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

    /**
     * @return String to print the board. Uses many, many, many calls to sb.append() as it uses a StringBuilder to
     * create the properly formatted output of the 9-Board TicTacToe game
     */
    public String betterPrint() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            //region first row
            // first game
            if ((gameGrid[i][0].getBoard()[0][0].name().equalsIgnoreCase("open"))) {
                sb.append(" - ");
            } else {
                sb.append(" ").append(gameGrid[i][0].getBoard()[0][0].name());
            }

            if (gameGrid[i][0].getBoard()[0][1].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][0].getBoard()[0][1].name());
            }

            if (gameGrid[i][0].getBoard()[0][2].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][0].getBoard()[0][2].name());
            }

            sb.append("  ||  ");

            // second game
            if ((gameGrid[i][1].getBoard()[0][0].name().equalsIgnoreCase("open"))) {
                sb.append(" - ");
            } else {
                sb.append(" ").append(gameGrid[i][1].getBoard()[0][0].name());
            }

            if (gameGrid[i][1].getBoard()[0][1].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][1].getBoard()[0][1].name());
            }

            if (gameGrid[i][1].getBoard()[0][2].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][1].getBoard()[0][2].name());
            }

            sb.append("  ||  ");

            // third game
            if ((gameGrid[i][2].getBoard()[0][0].name().equalsIgnoreCase("open"))) {
                sb.append(" - ");
            } else {
                sb.append(" ").append(gameGrid[i][2].getBoard()[0][0].name());
            }

            if (gameGrid[i][2].getBoard()[0][1].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][2].getBoard()[0][1].name());
            }

            if (gameGrid[i][2].getBoard()[0][2].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][2].getBoard()[0][2].name());
            }

            sb.append("\n");
            //endregion

            //region second row
            // first game
            if ((gameGrid[i][0].getBoard()[1][0].name().equalsIgnoreCase("open"))) {
                sb.append(" - ");
            } else {
                sb.append(" ").append(gameGrid[i][0].getBoard()[1][0].name());
            }

            if (gameGrid[i][0].getBoard()[1][1].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][0].getBoard()[1][1].name());
            }

            if (gameGrid[i][0].getBoard()[1][2].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][0].getBoard()[1][2].name());
            }

            sb.append("  ||  ");

            // second game
            if ((gameGrid[i][1].getBoard()[1][0].name().equalsIgnoreCase("open"))) {
                sb.append(" - ");
            } else {
                sb.append(" ").append(gameGrid[i][1].getBoard()[1][0].name());
            }

            if (gameGrid[i][1].getBoard()[1][1].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][1].getBoard()[1][1].name());
            }

            if (gameGrid[i][1].getBoard()[1][2].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][1].getBoard()[1][2].name());
            }

            sb.append("  ||  ");

            // third game
            if ((gameGrid[i][2].getBoard()[1][0].name().equalsIgnoreCase("open"))) {
                sb.append(" - ");
            } else {
                sb.append(" ").append(gameGrid[i][2].getBoard()[1][0].name());
            }

            if (gameGrid[i][2].getBoard()[1][1].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][2].getBoard()[1][1].name());
            }

            if (gameGrid[i][2].getBoard()[1][2].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][2].getBoard()[1][2].name());
            }

            sb.append("\n");
            //endregion

            //region third row
            // first game
            if ((gameGrid[i][0].getBoard()[2][0].name().equalsIgnoreCase("open"))) {
                sb.append(" - ");
            } else {
                sb.append(" ").append(gameGrid[i][0].getBoard()[2][0].name());
            }

            if (gameGrid[i][0].getBoard()[2][1].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][0].getBoard()[2][1].name());
            }

            if (gameGrid[i][0].getBoard()[2][2].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][0].getBoard()[2][2].name());
            }

            sb.append("  ||  ");

            // second game
            if ((gameGrid[i][1].getBoard()[2][0].name().equalsIgnoreCase("open"))) {
                sb.append(" - ");
            } else {
                sb.append(" ").append(gameGrid[i][1].getBoard()[2][0].name());
            }

            if (gameGrid[i][1].getBoard()[2][1].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][1].getBoard()[2][1].name());
            }

            if (gameGrid[i][1].getBoard()[2][2].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][1].getBoard()[2][2].name());
            }

            sb.append("  ||  ");

            // third game
            if ((gameGrid[i][2].getBoard()[2][0].name().equalsIgnoreCase("open"))) {
                sb.append(" - ");
            } else {
                sb.append(" ").append(gameGrid[i][2].getBoard()[2][0].name());
            }

            if (gameGrid[i][2].getBoard()[2][1].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][2].getBoard()[2][1].name());
            }

            if (gameGrid[i][2].getBoard()[2][2].name().equalsIgnoreCase("open")) {
                sb.append(" - ");
            } else  {
                sb.append(" ").append(gameGrid[i][2].getBoard()[2][2].name());
            }

            sb.append("\n");
            //endregion

            if (i != 2) {
                sb.append("\n====================================== \n\n");
            }
        }
        return new String(sb);
    }

    // Simple tester to make sure the board prints nicely
    public static void main(String[] args) {
        NineBoard game = new NineBoard();
        System.out.print(game.betterPrint());
    }
}
