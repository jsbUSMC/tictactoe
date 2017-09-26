package game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Launches a game of Ultimate Tic Tac Toe
 */
@SuppressWarnings("WeakerAccess, UnusedDeclaration")
public class UltimateGame {
    private NineBoard grid;
    private Scanner stdIn = new Scanner(System.in);

    private UltimateGame() {
        this.grid = new NineBoard();
    }

    public static void main(String[] args) {
        UltimateGame game = new UltimateGame();
        game.play();
    }

    private void play() {
        System.err.println("Welcome to a game of Ultimate TicTacToe");

        while (true) {
            // TODO: implement game methods as in the Game class
        }
    }

    private void playMove() {
        // getPlayerMove or computer
    }

    /**
     * Prompts the player for a "legal" move. A legal move is defined by a regular expression that enforces a pattern
     * of 2 digits, each having value between 1 and 9, inclusive. There's a while loop in the case the player doesn't
     * enter a legal move that satisfies the regex pattern, which continuously prompts for legal input. If/when it does
     * match the pattern, the input value is split into two substrings, the first digit represents the grid location
     * of the board to play, and the second digit represents the location index within that game to place their mark.
     * These substrings are converted to ints using Integer.parseInt() and passed to the grid's move() method.
     */
    private void promptPlayerForMove() {
        Pattern p = Pattern.compile("[1-9]{2}");
        System.err.print("Please choose a board on the grid, and a location on that board to play\n" +
                "(Enter a two-digit number, both digits between 1 and 9, inclusive): ");
        String playChoice = stdIn.nextLine();
        Matcher m = p.matcher(playChoice);
        boolean legalValue = m.matches();

        while (!legalValue) {
            System.err.print("Please choose a board on the grid, and a location on that board to play\n" +
                    "(Enter a two-digit number, both digits between 1 and 9, inclusive): ");
            playChoice = stdIn.nextLine();
            m = p.matcher(playChoice);
            legalValue = m.matches();
        }

        String board = playChoice.substring(0,1);
        String loc = playChoice.substring(1);
        int boardVal = Integer.parseInt(board);
        int locVal = Integer.parseInt(loc);

        grid.move(boardVal, locVal);
    }
}
