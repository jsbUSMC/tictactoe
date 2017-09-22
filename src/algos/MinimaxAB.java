package algos;

import game.Board;
import game.Board.State;

/**
 * This class contains all the logic for implementing the Minimax algorithm, with a heuristic evaluation function
 * and alpha-beta pruning.
 */
@SuppressWarnings("WeakerAccess")
public class MinimaxAB {
    private Double alpha = Double.NEGATIVE_INFINITY;
    private Double beta = Double.POSITIVE_INFINITY;
    private static double searchDepth;

    private MinimaxAB() {}

    /**
     * Execute the algorithm.
     * @param player        the player that the AI will identify as
     * @param board         the Tic Tac Toe board to play on
     * @param searchDepth        the maximum depth
     */
    static void run (State player, Board board, double searchDepth) {

        if (searchDepth < 1) {
            throw new IllegalArgumentException("Maximum depth must be greater than 0.");
        }

        MinimaxAB.searchDepth = searchDepth;
        alphaBetaPruning(player, board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
    }

    /**
     * The meat of the algorithm.
     * @param player        the player that the AI will identify as
     * @param board         the Tic Tac Toe board to play on
     * @param alpha         the alpha value
     * @param beta          the beta value
     * @param currentNode    the current depth
     * @return              the score of the board
     */
    private static int alphaBetaPruning (State player, Board board, double alpha, double beta, int currentNode) {
        if (currentNode++ == searchDepth || board.isGameEnded()) {
            return score(player, board, currentNode);
        }

        if (board.getCurrentTurn() == player) {
            return getMax(player, board, alpha, beta, currentNode);
        } else {
            return getMin(player, board, alpha, beta, currentNode);
        }
    }

    /**
     * Play the move with the highest score.
     * @param player        the player that the AI will identify as
     * @param board         the Tic Tac Toe board to play on
     * @param alpha         the alpha value
     * @param beta          the beta value
     * @param currentNode    the current depth
     * @return              the score of the board
     */
    private static int getMax (State player, Board board, double alpha, double beta, int currentNode) {
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.deepClone();
            modifiedBoard.move(player, theMove);
            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentNode);

            if (score > alpha) {
                alpha = score;
                indexOfBestMove = theMove;
            }

            if (alpha >= beta) {
                break;
            }
        }

        if (indexOfBestMove != -1) {
            board.move(player, indexOfBestMove);
        }
        return (int)alpha;
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
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.deepClone();
            modifiedBoard.move(player, theMove);

            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentNode);

            if (score < beta) {
                beta = score;
                indexOfBestMove = theMove;
            }

            if (alpha >= beta) {
                break;
            }
        }

        if (indexOfBestMove != -1) {
            board.move(player, indexOfBestMove);
        }
        return (int)beta;
    }

    /**
     * Get the score of the board. Takes depth into account.
     * @param player        the play that the AI will identify as
     * @param board         the Tic Tac Toe board to play on
     * @param currentNode    the current depth
     * @return              the score of the board
     */
    private static int score (State player, Board board, int currentNode) {

        if (player == State.Open) {
            throw new IllegalArgumentException("Player must be X or O.");
        }

        State opponent = (player == State.X) ? State.O : State.X;

        if (board.isGameEnded() && board.getWinningPlayer() == player) {
            return 10 - currentNode;
        } else if (board.isGameEnded() && board.getWinningPlayer() == opponent) {
            return -10 + currentNode;
        } else {
            return 0;
        }
    }
}
