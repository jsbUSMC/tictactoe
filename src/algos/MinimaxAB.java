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
    public static int bestPossibleMove =  -1000;
    public static int worstPossibleMove = 1000;
    public static int statesEvaluated;

    private MinimaxAB() {}

    public static void run(Board board) {
        run(board.getTurn(), board, Double.POSITIVE_INFINITY);
    }

    /**
     * Execute the algorithm.
     *
     * @param skynet      the player that the AI will identify as
     * @param board       the Tic Tac Toe board to play on
     * @param searchDepth the maximum depth
     */
    private static void run(State skynet, Board board, double searchDepth) {

        if (searchDepth < 1) {
            throw new IllegalArgumentException("Maximum depth must be greater than 0.");
        }

        MinimaxAB.searchDepth = searchDepth;
//        int skynetsMove = runMinimax(skynet, board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
//        System.err.println(String.format("The AI played move: %d", skynetsMove));
        runMinimax(skynet, board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
    }

    /**
     * The meat of the algorithm.
     *
     * @param skynet      the player that the AI will identify as
     * @param board       the Tic Tac Toe board to play on
     * @param alpha       the alpha value
     * @param beta        the beta value
     * @param currentNode the current depth
     * @return the evaluated heuristic (score) of the board
     */
    private static int runMinimax(State skynet, Board board, double alpha, double beta, int currentNode) {
        if (currentNode++ == searchDepth || board.isGameOver()) {
//            int skynetsMove = evalHeuristic(skynet, board, currentNode);
//            System.out.println(String.format("The AI played move: %d", skynetsMove));
//            return skynetsMove;
            return evalHeuristic(skynet, board, currentNode);
        }

        if (board.getTurn() == skynet) {
            return getMax(skynet, board, alpha, beta, currentNode);
        } else {
            return getMin(skynet, board, alpha, beta, currentNode);
        }
    }

    /**
     * Play the move with the highest evalHeuristic.
     *
     * @param skynet      the player that the AI will identify as
     * @param board       the Tic Tac Toe board to play on
     * @param alpha       the alpha value
     * @param beta        the beta value
     * @param currentNode the current depth
     * @return the evaluated heuristic (score) of the board
     */
    private static int getMax(State skynet, Board board, double alpha, double beta, int currentNode) {
        int optimalMove = -1000;

        for (Integer possibleMove : board.getAvailableMoves()) {
            // TODO: Rename getDeepCopy() to generateSuccessor()
            statesEvaluated++;
            Board successorState = board.getDeepCopy();
            successorState.move(possibleMove);
            int score = runMinimax(skynet, successorState, alpha, beta, currentNode);

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
    }

    /**
     * Play the move with the lowest evalHeuristic.
     *
     * @param skynet      the player that the AI will identify as
     * @param board       the Tic Tac Toe board to play on
     * @param alpha       the alpha value
     * @param beta        the beta value
     * @param currentNode the current depth
     * @return the evaluated heuristic (score) of the board
     */
    private static int getMin(State skynet, Board board, double alpha, double beta, int currentNode) {
        int optimalMove = -1000;

        for (Integer possibleMove : board.getAvailableMoves()) {
            statesEvaluated++;
            Board successorState = board.getDeepCopy();
            successorState.move(possibleMove);

            int score = runMinimax(skynet, successorState, alpha, beta, currentNode);

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

    /**
     * Get the score of the board. Takes depth into account.
     *
     * @param skynet      the player that the AI will identify as
     * @param board       the Tic Tac Toe board to play on
     * @param currentNode the current depth
     * @return the evaluated heuristic (score) of the board
     */
    private static int evalHeuristic(State skynet, Board board, int currentNode) {
        if (skynet == State.Blank) {
            throw new IllegalArgumentException("Player must be X or O.");
        }

        State opponent = (skynet == State.X) ? State.O : State.X;

        if (board.isGameOver() && board.getWinner() == skynet) {
//            System.err.println(String.format("\nScore at terminal node: %d", (10 - currentNode)));
            bestPossibleMove = ((10 - currentNode) > bestPossibleMove) ? (bestPossibleMove = (10 - currentNode)) : bestPossibleMove;
//            System.err.println(String.format("AI win currentNode value: %d", currentNode));
            // TODO: explain this (wins faster)
            return 10 - currentNode;
        } else if (board.isGameOver() && board.getWinner() == opponent) {
//            System.err.println(String.format("\nScore at terminal node: %d", (-10 + currentNode)));
            worstPossibleMove = ((-10 + currentNode) < worstPossibleMove) ? (worstPossibleMove = (-10 + currentNode)) : worstPossibleMove;
//            System.err.println(String.format("Player win currentNode value: %d", currentNode));
            // TODO: explain the minimizer part here, forces min to choose a lower value (more negative)
            return -10 + currentNode;
        } else {
            return 0;
        }
    }
}
