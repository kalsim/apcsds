package Chess;

import java.awt.*;
import java.util.ArrayList;

/**
 * Provides implementation for a smart player in chess which
 * considers multiple moves ahead to pick the best options
 *
 * @author Montek Kalsi
 * @version 28/5/18
 */
public class SmartPlayer extends Player {

    /**
     * Constructs a SmarterPlayer with board b, name n, and color c
     * @param b the board of the game
     * @param n the name of the SmarterPlayer
     * @param c the color of the SmarterPlayer
     */
    public SmartPlayer(Board b, String n, Color c)
    {
        super(b, n, c);
    }

    /**
     * The outputs the score of the moves
     * @return an integer representation of the score
     */
    public int score()
    {
        int s = 0;
        Board b = this.getBoard();
        ArrayList<Location> x = b.getOccupiedLocations();
        for(Location loc : x)
        {
            if(b.get(loc).getColor() == this.getColor()) s += b.get(loc).getValue();
            else s -= b.get(loc).getValue();
        }
        return s;
    }

    /**
     * Outputs the next move for an actually smart player
     * @return the next move
     */
    public Move nextMove()
    {
        ArrayList<Move> allMoves = this.getBoard().allMoves(this.getColor());
        Move move = allMoves.get(0);
        int hiScore = 0;
        for(Move m : allMoves)
        {
            getBoard().executeMove(m);
            int x = valueOfMeanestResponse(4);
            getBoard().undoMove(m);
            if(x > hiScore)
            {
                hiScore = x;
                move = m;
            }
        }
        return move;
    }

    /**
     * Outputs the meanest response part of the Minimax algorithm
     * @param num the score of the move
     * @return an integer value of the Meanestresponse
     */
    private int valueOfMeanestResponse(int num)
    {
        if(num == 0) return score();
        Board board = getBoard();
        ArrayList<Move> moves = new ArrayList<Move>();
        if(getColor() == Color.WHITE)
        {
            moves = board.allMoves(Color.BLACK);
        }
        else
        {
            moves = board.allMoves(Color.WHITE);
        }
        int min = Integer.MAX_VALUE;
        int size = moves.size();
        for(int i = 0; i < size; i++)
        {
            Move current = moves.get(i);
            board.executeMove(current);
            int s = valueOfBestMove(num - 1);
            board.undoMove(current);
            if(s < min) min = s;
        }
        return min;
    }

    /**
     * Outputs the value of the best move
     * @param num the move being checked
     * @return int the value of the best move
     */
    private int valueOfBestMove(int num)
    {
        if(num == 0) return score();
        Board board = getBoard();
        ArrayList<Move> moves = board.allMoves(getColor());
        int max = Integer.MIN_VALUE;
        int size = moves.size();
        for(int i = 0; i < size; i++)
        {
            Move current = moves.get(i);
            board.executeMove(current);
            int s = valueOfMeanestResponse(num - 1);
            board.undoMove(current);
            if(s > max) max = s;
        }
        return max;
    }

}
