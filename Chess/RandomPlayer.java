package Chess;

import Chess.Player;

import java.awt.*;
import java.util.ArrayList;
/**
 * Provides implementation of a RandomPlayer in a game of chess
 *
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class RandomPlayer extends Player
{
    /**
     * Constructs a RandomPlayer with board b, name n, and color c
     * @param b the board of the game
     * @param n the name of the RandomPlayer
     * @param c the color of the RandomPlayer
     */
    public RandomPlayer(Board b, String n, Color c)
    {
        super(b, n, c);
    }

    /**
     * outputs the next move for a player who only selects random moves
     * @return the next move
     */
    public Move nextMove()
    {
        ArrayList<Move> nucc = this.getBoard().allMoves(this.getColor());
        int succ = (int) (Math.random() * nucc.size());
        return nucc.get(succ);
    }
}
