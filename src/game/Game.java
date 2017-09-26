package game;

import java.util.Scanner;

import algos.MinimaxAB;

/**
 * Author: John S. Bissonette
 * NetID: jbisson2
 *
 * @since 9/8/2017
 * Assignment: Project 1 - Tic Tac Toe
 */
public class Game {
    private Board board;
    private Scanner stdIn = new Scanner(System.in);
    private State player;

    private Game() {
        this.board = new Board();
    }

    public static void main(String[] args) {
        Game ticTacToe = new Game();
        ticTacToe.play();
    }

    /**
     * Begin the game.
     */
    private void play() {

        System.err.println("Welcome to a game of Classic TicTacToe!");
        System.err.println();

        promptPlayerMarker();

        while (true) {
            printGameStatus();
            playMove();

            if (board.isGameOver()) {
                printWinner();

                if (!tryAgain()) {
                    break;
                }

                promptPlayerMarker();
            }
        }
    }

    /**
     * Assigns the player's chosen marker to either an X or O
     */
    private void promptPlayerMarker() {
        System.err.print("Would you like to play as 'X' or 'O'? (X goes first): ");
        String marker = stdIn.nextLine().toLowerCase();

        while (!marker.toUpperCase().equals("X") && !marker.toUpperCase().equals("O")) {
            System.err.print("Please select either 'X' or 'O' (case insensitive): ");
        }

        player = (marker.toUpperCase().equals("X")) ? State.X : State.O;
    }

    /**
     * Handle the move to be played, either by the player or the AI.
     */
    private void playMove() {
        if (board.getTurn() == player) {
            getPlayerMove();
        } else {
            MinimaxAB.computeMinimax(board, 18);
        }
    }

    /**
     * Print out the board and the player who's turn it is.
     */
    private void printGameStatus() {
        System.err.println("\n" + board + "\n");
//        System.err.println(board.getTurn().name() + "'s turn.");
    }

    /**
     * For reading in and interpreting the move that the user types into the console.
     */
    private void getPlayerMove() {
        System.err.print("It's your turn. Select the location you wish to play: ");

        int move = stdIn.nextInt();

        while (move < 1 || move > 9) {
            System.err.println("\nInvalid move. The index of the move must be between 1 and 9, inclusive.\n");
            System.err.print("Select the index of board to play: ");

            move = stdIn.nextInt();
        }
        board.move(move - 1);
    }

    /**
     * Print out the winner of the game.
     */
    private void printWinner() {
        State winner = board.getWinner();

        System.err.println("\n" + board + "\n");

        if (winner == State.Open) {
            System.err.println("The TicTacToe is a Draw.");
        } else {
            System.err.println("Player " + winner.toString() + " wins!");
        }

        System.err.println(String.format("\nTotal Nodes Pruned: %d\nTotal States Evaluated: %d", MinimaxAB.nodesPruned, MinimaxAB.statesEvaluated));
    }

    /**
     * Reset the game if the player wants to play again.
     *
     * @return true if the player wants to play again
     */
    private boolean tryAgain() {
        if (promptTryAgain()) {
            board.reset();
            System.err.println("Started new game.");
            System.err.println("X's turn.");
            return true;
        }

        return false;
    }

    /**
     * Ask the player if they want to play again.
     *
     * @return true if the player wants to play again
     */
    private boolean promptTryAgain() {
        while (true) {
            System.err.print("Would you like to start a new game? (Y/N): ");
            String response = stdIn.next();
            if (response.equalsIgnoreCase("y")) {
                return true;
            } else if (response.equalsIgnoreCase("n")) {
                return false;
            }
            System.err.println("Invalid input.");
        }
    }
}
