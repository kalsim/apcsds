package Chess;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class which starts a two player chess game.
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class HumanPlayer extends Player {

    /**
     * creates a display for the board
     */
    BoardDisplay display;

    /**
     * Constructs a new HumanPlayer object with board b, name n,
     * color c, display d
     * @param b the new board
     * @param n the name
     * @param c the color of the humanplayer
     * @param d the display of the board
     */
    public HumanPlayer(Board b, String n, Color c, BoardDisplay d)
    {
        super(b, n, c);
        display = d;
    }

    /**
     * returns the next move of the humanplayer
     * @return the next move
     */
    public Move nextMove()
    {
        ArrayList<Move> s = this.getBoard().allMoves(this.getColor());
        Move n = display.selectMove();
        for(Move z : s)
        {
            if(z.toString().equals(n.toString()))
                return n;
        }
        return nextMove();
    }
}
