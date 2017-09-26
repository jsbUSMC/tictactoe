package algos;

import game.Board;
import game.State;

/**
 * This class contains all the logic for implementing the Minimax algorithm, with a heuristic evaluation function
 * and alpha-beta pruning.
 */
@SuppressWarnings("WeakerAccess, Duplicates")
public class MinimaxAB {
    private static double searchDepth;
    public static int nodesPruned;
    public static int statesEvaluated;

    /**
     * @param board the game board in play
     * @param searchDepth the maximum depth to recurse through the game tree
     */
    public static void computeMinimax(Board board, double searchDepth) {
        MinimaxAB.searchDepth = searchDepth;
        minimax(board.getPlayersTurn(), board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
    }

    private static int minimax(State skynet, Board board, double alpha, double beta, int currentNode) {
        // utility function
        if (currentNode++ == searchDepth || board.isGameOver()) {
            State opponent = (skynet == State.X) ? State.O : State.X;
            if (board.isGameOver() && board.getWinner() == skynet) {
                return 10 - currentNode;
            } else if (board.isGameOver() && board.getWinner() == opponent) {
                return -10 + currentNode;
            } else {
                return 0;
            }
        }

        if (board.getPlayersTurn() == skynet) {
            int optimalMove = -1000;
            for (Integer possibleMove : board.getAvailableMoves()) {
                statesEvaluated++;
                Board successorState = board.generateSuccessor();
                successorState.move(possibleMove);
                int score = minimax(skynet, successorState, alpha, beta, currentNode);
                if (score > alpha) {
                    alpha = score;
                    optimalMove = possibleMove;
                }
                if (alpha >= beta) {
                    nodesPruned++;
                    break;
                }
            }
            // This condition should never not be met...meaning optimalMove should NEVER be == -1000
            if (optimalMove != -1000) {
                board.move(optimalMove);
            }
            return (int) alpha;
        } else {
            int optimalMove = -1000;
            for (Integer possibleMove : board.getAvailableMoves()) {
                statesEvaluated++;
                Board successorState = board.generateSuccessor();
                successorState.move(possibleMove);
                int score = minimax(skynet, successorState, alpha, beta, currentNode);
                if (score < beta) {
                    beta = score;
                    optimalMove = possibleMove;
                }
                if (alpha >= beta) {
                    nodesPruned++;
                    break;
                }
            }
            // This condition should never not be met...meaning optimalMove should NEVER be == -1000
            if (optimalMove != -1000) {
                board.move(optimalMove);
            }
            return (int) beta;
        }
    }
}
