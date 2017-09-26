package algos;

import game.Board;
import game.State;

// TODO: Better comments

/**
 * This class contains all the logic for implementing the Minimax algorithm, with a heuristic evaluation function
 * and alpha-beta pruning.
 */
@SuppressWarnings("WeakerAccess, Duplicates")
public class MinimaxAB {
    private static double searchDepth;
    public static int nodesPruned;
    public static int bestPossibleMove =  -1000;
    public static int worstPossibleMove = 1000;
    public static int statesEvaluated;

    private MinimaxAB() {}

//    public static void run(Board board) {
//        run(board.getTurn(), board, Double.POSITIVE_INFINITY);
//    }

    public static void computeMinimax(Board board, double searchDepth) {
        MinimaxAB.searchDepth = searchDepth;
        minimax(board.getTurn(), board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
    }

    public static int minimax(State skynet, Board board, double alpha, double beta, int currentNode) {
        if (currentNode++ == searchDepth || board.isGameOver()) {
            // FIXME: Remove these test cases
//            if (skynet == State.Blank) {
//                throw new IllegalArgumentException("Player must be X or O.");
//            }

            State opponent = (skynet == State.X) ? State.O : State.X;

            if (board.isGameOver() && board.getWinner() == skynet) {
                bestPossibleMove = ((10 - currentNode) > bestPossibleMove) ? (bestPossibleMove = (10 - currentNode)) : bestPossibleMove;
                // TODO: explain this (wins faster)
                return 10 - currentNode;
            } else if (board.isGameOver() && board.getWinner() == opponent) {
                worstPossibleMove = ((-10 + currentNode) < worstPossibleMove) ? (worstPossibleMove = (-10 + currentNode)) : worstPossibleMove;
                // TODO: explain the minimizer part here, forces min to choose a lower value (more negative)
                return -10 + currentNode;
            } else {
                return 0;
            }
        }

        if (board.getTurn() == skynet) {
//            return getMax(skynet, board, alpha, beta, currentNode);
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
                // last printout of getMax will be the final decision for move by AI
//            System.out.println(String.format("\ngetMax optimal move: %d\nnodes pruned thus far: %d", optimalMove + 1, nodesPruned));
                board.move(optimalMove);
            }
            return (int) alpha;
        } else {
//            return getMin(skynet, board, alpha, beta, currentNode);
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
//            System.err.println(String.format("\ngetMin optimal move: %d\nnodes pruned thus far: %d", optimalMove, nodesPruned));
                board.move(optimalMove);
            }
            return (int) beta;
        }
    }
}
