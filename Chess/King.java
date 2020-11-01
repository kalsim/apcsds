package Chess;

import java.awt.*;
import java.util.*;

/**
 * Class for the king piece in a chess game
 *
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class King extends Piece{

    public King(Color col, String filename)
    {
        super(col, filename, 1000);
    }

    /**
     * overrides destinations method
     * @return an arraylist of the locations
     */
    public ArrayList<Location> destinations()
    {
        Location loc = this.getLocation();
        ArrayList<Location> x = new ArrayList<Location>();
        if(this.isValidDestination(new Location(loc.getRow() + 1, loc.getCol())))
            x.add(new Location(loc.getRow() + 1, loc.getCol()));
        if(this.isValidDestination(new Location(loc.getRow() + 1, loc.getCol() + 1)))
            x.add(new Location(loc.getRow() + 1, loc.getCol() + 1));
        if(this.isValidDestination(new Location(loc.getRow(), loc.getCol() + 1)))
            x.add(new Location(loc.getRow(), loc.getCol() + 1));
        if(this.isValidDestination(new Location(loc.getRow() - 1, loc.getCol() + 1)))
            x.add(new Location(loc.getRow() - 1, loc.getCol() + 1));
        if(this.isValidDestination(new Location(loc.getRow() - 1, loc.getCol())))
            x.add(new Location(loc.getRow() - 1, loc.getCol()));
        if(this.isValidDestination(new Location(loc.getRow() - 1, loc.getCol() - 1)))
            x.add(new Location(loc.getRow() - 1, loc.getCol() - 1));
        if(this.isValidDestination(new Location(loc.getRow(), loc.getCol() - 1)))
            x.add(new Location(loc.getRow(), loc.getCol() - 1));
        if(this.isValidDestination(new Location(loc.getRow() + 1, loc.getCol() - 1)))
            x.add(new Location(loc.getRow() + 1, loc.getCol() - 1));
        return x;
    }
}
