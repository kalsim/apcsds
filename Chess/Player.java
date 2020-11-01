package Chess;

import java.awt.*;

/**
 * Provides implementation of a player in chess
 *
 * @author Montek Kalsi
 * @version 5/29/18
 */
public abstract class Player {
    /**
     * board of the chess game
     */
    private Board board;
    /**
     * name of the player
     */
    private String name;
    /**
     * color of the player
     */
    private Color color;

    /**
     * constructs a player object with board b, name n, and color c
     * @param b the board of the game
     * @param n the name of the player
     * @param c the color of the pieces of the player
     */
    public Player(Board b, String n, Color c)
    {
        board = b;
        name = n;
        color = c;
    }

    /**
     * outputs board
     * @return the board
     */
    public Board getBoard(){ return board; }

    /**
     * outputs the name of the player
     * @return a string representation of the name
     */
    public String getName(){ return name; }

    /**
     * outputs color
     * @return the color of the pieces of this player
     */
    public Color getColor(){ return color; }

    /**
     * abstract declaration of a nextMove() method for a player
     * @return nothing yet
     */
    public abstract Move nextMove();

}
