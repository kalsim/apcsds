package Chess;

import java.awt.*;
import java.util.ArrayList;

/**
 * class representation of a queen piece in a game of chess
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class Queen extends Piece{
    /**
     * constructs a queen object on column col
     * @param col the column the queen is on
     * @param filename the file of the queen piece
     */
    public Queen(Color col, String filename)
    {
        super(col, filename, 500);
    }

    /**
     * overriden destinations() method which outputs the locations
     * @return an ArrayList of the locations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> x = new ArrayList<Location>();
        sweep(x, Location.NORTH);
        sweep(x, Location.SOUTH);
        sweep(x, Location.EAST);
        sweep(x, Location.WEST);
        sweep(x, Location.NORTHEAST);
        sweep(x, Location.NORTHWEST);
        sweep(x, Location.SOUTHEAST);
        sweep(x, Location.SOUTHWEST);
        return x;
    }
}
