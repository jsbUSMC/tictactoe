package algos;

import game.Board;
import game.State;

// TODO: Better comments
/**
 * This class contains all the logic for implementing the Minimax algorithm, with a heuristic evaluation function
 * and alpha-beta pruning.
 */
@SuppressWarnings("WeakerAccess")
public class MinimaxAB {
    private static double searchDepth;
    public static int nodesPruned;

    private MinimaxAB() {}

    public static void run(Board board) {
        run(board.getTurn(), board, Double.POSITIVE_INFINITY);
    }

    /**
     * Execute the algorithm.
     * @param player        the player that the AI will identify as
     * @param board         the Tic Tac Toe board to play on
     * @param searchDepth        the maximum depth
     */
    private static void run (State player, Board board, double searchDepth) {

        if (searchDepth < 1) {
            throw new IllegalArgumentException("Maximum depth must be greater than 0.");
        }

        MinimaxAB.searchDepth = searchDepth;
        runMinimax(player, board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
    }

    /**
     * The meat of the algorithm.
     * @param player the player that the AI will identify as
     * @param board the Tic Tac Toe board to play on
     * @param alpha the alpha value
     * @param beta the beta value
     * @param currentNode the current depth
     * @return the score of the board
     */
    private static int runMinimax (State player, Board board, double alpha, double beta, int currentNode) {
        if (currentNode++ == searchDepth || board.isGameOver()) {
            return score(player, board, currentNode);
        }

        if (board.getTurn() == player) {
            return getMax(player, board, alpha, beta, currentNode);
        } else {
            return getMin(player, board, alpha, beta, currentNode);
        }
    }

    /**
     * Play the move with the highest score.
     * @param player the player that the AI will identify as
     * @param board  the Tic Tac Toe board to play on
     * @param alpha the alpha value
     * @param beta the beta value
     * @param currentNode the current depth
     * @return the score of the board
     */
    private static int getMax (State player, Board board, double alpha, double beta, int currentNode) {
        int optimalMove = -1;

        for (Integer possibleMove : board.getAvailableMoves()) {
            Board successorState = board.getDeepCopy();
            successorState.move(possibleMove);
            int score = runMinimax(player, successorState, alpha, beta, currentNode);

            if (score > alpha) {
                alpha = score;
                optimalMove = possibleMove;
            }

            if (alpha >= beta) {
                nodesPruned++;
                break;
            }
        }

        if (optimalMove != -1) {
            board.move(optimalMove);
        }
        return (int) alpha;
    }

    /**
     * Play the move with the lowest score.
     * @param player        the player that the AI will identify as
     * @param board         the Tic Tac Toe board to play on
     * @param alpha         the alpha value
     * @param beta          the beta value
     * @param currentNode    the current depth
     * @return              the score of the board
     */
    private static int getMin (State player, Board board, double alpha, double beta, int currentNode) {
        int optimalMove = -1;

        for (Integer possibleMove : board.getAvailableMoves()) {

            Board successorState = board.getDeepCopy();
            successorState.move(possibleMove);

            int score = runMinimax(player, successorState, alpha, beta, currentNode);

            if (score < beta) {
                beta = score;
                optimalMove = possibleMove;
            }

            if (alpha >= beta) {
                nodesPruned++;
                break;
            }
        }

        if (optimalMove != -1) {
            board.move(optimalMove);
        }
        return (int) beta;
    }

    /**
     * Get the score of the board. Takes depth into account.
     * @param player        the play that the AI will identify as
     * @param board         the Tic Tac Toe board to play on
     * @param currentNode    the current depth
     * @return              the score of the board
     */
    private static int score (State player, Board board, int currentNode) {
        if (player == State.Blank) {
            throw new IllegalArgumentException("Player must be X or O.");
        }

        State opponent = (player == State.X) ? State.O : State.X;

        if (board.isGameOver() && board.getWinner() == player) {
            return 10 - currentNode;
        } else if (board.isGameOver() && board.getWinner() == opponent) {
            return -10 + currentNode;
        } else {
            return 0;
        }
    }
}
