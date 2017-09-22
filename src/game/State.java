//package game;
//
///**
// * Author: John S. Bissonette
// * NetID: jbisson2
// * @since 9/11/2017
// * Assignment: Project 1 - Tic Tac Toe
// */
//@SuppressWarnings("WeakerAccess, UnusedDeclaration, Duplicates")
//public class State {
//    private int turnCount;
//    private Board board;
//    private int movesRemaining;
//
//    public State(Board board) {
//        this.setBoard(board);
//        this.setTurnCount(0);
//        this.setMovesRemaining(9);
//    }
//
//    public State(Board board, int turnCount) {
//        this.setBoard(board);
//        this.setTurnCount(turnCount);
//        this.setMovesRemaining(9 - turnCount);
//    }
//
//    //<editor-fold desc="Mutators and Accessors for class fields">
//    public int getTurnCount() {
//        return turnCount;
//    }
//
//    public void setTurnCount(int turnCount) {
//        this.turnCount = turnCount;
//    }
//
//    public Board getBoard() {
//        return board;
//    }
//
//    public void setBoard(Board board) {
//        this.board = board;
//    }
//
//    public int getMovesRemaining() {
//        return movesRemaining;
//    }
//
//    public void setMovesRemaining(int movesRemaining) {
//        this.movesRemaining = movesRemaining;
//    }
//    //</editor-fold>
//
//    /**
//     * A simple method to advance the turn counter and decrement the number of moves remaining
//     */
//    public void advanceTurns() {
//        this.setTurnCount(this.getTurnCount() + 1);
//        this.setMovesRemaining(9 - this.getTurnCount());
//    }
//
//    // Todo: this method is unfinished
//    /**
//     * This method plays a move for a player, but checks for possible victory conditions
//     * @param player a String representing the player making the move
//     * @param loc an integer representing the index location to place the marker. See documentation for Board#move
//     */
//    public void playMove(String player, int loc) {
//        if  (this.movesRemaining >= 4) {
//            if (this.checkVictory() == 3) {
//                this.getBoard().move(player, loc);
//            }
//        } else {
//            this.getBoard().move(player, loc);
//        }
//    }
//
//    /**
//     * The code for scoring was based upon a snippet I stumbled across on GitHub. User honghsien5 created a repository
//     * of a version of this project, and released it publicly under the GNU GPLv3. The specific resource is found at:
//     * https://github.com/honghsien5/Tic-Tac-Toe-AI/blob/8c8e0e98218963abfc978621ca35134851628580/TicTacToeAI.java#L111
//     *
//     * @return an integer representing victory conditions: 0 for X's win, 1 for O's win, 2 for Tie, 3 for not ended
//     */
//    public int checkVictory() {
//        State[][] state = this.getBoard().getGameBoard();
//
//        // count is a "scorecard" for the game, and holds the score for each of the 8 ways to win a 3x3 game, for both
//        // players, which is why it's given a size of 16 at initialization.
//        int[] count = new int[16];
//
//        // horizontal check
//        for (int i = 0; i <= 6; i += 3) {
//            for (int j = 0; j < 3; j++) {
//                if (state[i + j].equals("X")) {
//                    count[i / 3]++;
//                }
//                if (state[i + j].equals("O")) {
//                    count[8 + (i / 3)]++;
//                }
//            }
//        }
//        //vertical check
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j <= 6; j += 3) {
//                if (state[i + j].equals("X")) {
//                    count[3 + i]++;
//                }
//                if (state[i + j].equals("O")) {
//                    count[11 + i]++;
//                }
//            }
//        }
//        //diagonal top left to bottom right
//        for (int i = 0; i <= 8; i += 4) {
//            if (state[i].equals("X")) {
//                count[6]++;
//            }
//            if (state[i].equals("O")) {
//                count[14]++;
//            }
//        }
//        //diagonal top left to bottom right
//        for (int i = 2; i <= 6; i += 2) {
//            if (state[i].equals("X")) {
//                count[7]++;
//            }
//            if (state[i].equals("O")) {
//                count[15]++;
//            }
//        }
//
//        //return the result if any count is equal to 3
//        for (int i = 0 ; i < 16 ;i++) {
//            if (count[i] == 3) {
//                if (i <=7) {
//                    return 0;
//                } else {
//                    return 1;
//                }
//            }
//        }
//
//        if (this.movesRemaining == 0) {
//            return 2;
//        } else return 3;
//    }
//}
