package game;

import java.util.Scanner;

import algos.MinimaxAB;

/**
 * Author: John S. Bissonette
 * NetID: jbisson2
 *
 * @since 9/8/2017
 * Assignment: Project 1 - Tic Tac Toe
 *
 * The Game class contains all the logic necessary to handle events throughout the game, and to control the flow of
 * the gameplay. There are three instance variables, a Board object that is to be played upon by both players, a
 * Scanner object for reading input from standard input, and a State enum representing the human player.
 */
public class Game {
    private Board board;
    private Scanner stdIn = new Scanner(System.in);
    private State player;

    // Simple constructor that initializes the board to be played. All other instance variables will be initialized
    // after the play() method is called from the main entry point of the project.
    public Game() {
        this.board = new Board();
    }

    /**
     * Prints a welcome message to the player, followed by a prompt for the user to enter their choice of marker.
     * This choice determines who gets to play first, as we have hardcoded that the X marker gets to go first. Then
     * enters a while loop, which is only broken upon the game being won or a draw.
     */
    public void play() {

        System.err.println("Welcome to a game of Classic TicTacToe!");
        System.err.println();

        promptPlayerMarker();

        while (true) {
            printGameBoard();
            playMove();

            if (board.isGameOver()) {
                printVictor();

                if (!promptForPlayAnotherRound()) {
                    break;
                }
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
        board.setAiPlayer((player == State.X) ? State.O : State.X);
    }

    /**
     * Handles the move to be played, either by the player or the AI.
     */
    private void playMove() {
        if (board.getPlayersTurn() == player) {
            getPlayerMove();
        } else {
            MinimaxAB.computeMinimax(board, 18);
            System.out.print(MinimaxAB.indexPlayed);
            System.err.println();
        }
    }

    /**
     * Prints the game board
     */
    private void printGameBoard() {
        System.err.println("\n" + board + "\n");
    }

    /**
     * Handles the user's input when prompted to pick a choice for their move to play. If the user doesn't enter a
     * value between 1 and 9 inclusively, it enters a while loop that will continuously prompt them to enter valid
     * input until they do.
     *
     * The requested value is decremented by 1 when passed to the move() method, so that the Board class can properly
     * handle the indexed value of the board, which starts at 0. If this wasn't done, then it would break the math.
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
     * Simple print of the summary statistics obtained during the run of the Minimax algorithm.
     */
    private void printSummaryStatistics() {
        System.err.println("\nSummary Statistics from Minimax Algorithm\n=========================================");
        System.err.println(String.format("Total Nodes Pruned: %d\nTotal States Evaluated: %d",
                MinimaxAB.nodesPruned, MinimaxAB.statesEvaluated));
    }

    /**
     * Prints the victor to the console, along with some summary statistics for the game. These summary statistics
     * are pulled from the MinimaxAB class's static int variables `nodesPruned` and `statesEvaluated`, which keep
     * track of how many successor states were generated during the course of the algorithm and also how many times a
     * node was pruned from the tree by the alpha-beta cutoff. This is further explained in the MinimaxAB class.
     */
    private void printVictor() {
        State victor = board.getWinner();

        // Print the final state of the board upon victory or draw
        System.err.println("\n" + board + "\n");

        // Uses the Open value from the State enum as the victor, otherwise, print the Player that won
        if (victor == State.Open) {
            System.err.println("The TicTacToe is a Draw.");
        } else {
            System.err.println("Player " + victor.toString() + " wins!");
        }
        printSummaryStatistics();
    }

    /**
     * Resets all values for the summary statistics and for the algorithm's move. Although by running through the
     * algorithm, it will already reset to it's best move choice, it is best to also blank it out here just in case
     * there are problems when printing the move choice to the screen.
     */
    private void resetStatistics() {
        MinimaxAB.indexPlayed = 0;
        MinimaxAB.nodesPruned = 0;
        MinimaxAB.statesEvaluated = 0;
    }

    /**
     * Prompts the player to choose whether or not they want to play again. If they do choose to play again, it
     * resets the game board and will set all Minimax statistics back to nil.
     *
     * @return true if the player wants to play again
     */
    private boolean promptForPlayAnotherRound() {
        while (true) {
            System.err.print("Would you like to start a new game? (Y/N): ");
            String response = stdIn.next();
            if (response.equalsIgnoreCase("y")) {
                board.reset();
                resetStatistics();
                System.err.println("Started new game.");
                System.err.println("X's turn.");
                return true;
            } else if (response.equalsIgnoreCase("n")) {
                return false;
            }
            System.err.println("Invalid input.");
        }
    }
}
