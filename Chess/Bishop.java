package Chess;

import java.awt.*;
import java.util.ArrayList;

/**
 * Defines the bishop piece in a chess game.
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class Bishop extends Piece{

    public Bishop(Color col, String filename)
    {
        super(col, filename, 3);
    }

    /**
     * overrides the destinations method
     * @return an ArrayList of locations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> x = new ArrayList<Location>();
        sweep(x, Location.NORTHEAST);
        sweep(x, Location.NORTHWEST);
        sweep(x, Location.SOUTHEAST);
        sweep(x, Location.SOUTHWEST);
        return x;
    }

}
