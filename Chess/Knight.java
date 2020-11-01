package Chess;

import java.awt.*;
import java.util.ArrayList;

/**
 * class declaration for the Knight piece in chess
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class Knight extends Piece {

    public Knight(Color col, String filename)
    {
        super(col, filename, 3);
    }

    /**
     * overrides destinations method
     * @return an arraylist of the locations
     */
    public ArrayList<Location> destinations() {
        Location locc = this.getLocation();
        ArrayList<Location> x = new ArrayList<Location>();
        if(this.isValidDestination(new Location(locc.getRow() - 1, locc.getCol() + 2)))
        {
            x.add(new Location(locc.getRow() - 1, locc.getCol() + 2));
        }
        if(this.isValidDestination(new Location(locc.getRow() - 2, locc.getCol() + 1)))
        {
            x.add(new Location(locc.getRow() - 2, locc.getCol() + 1));
        }
        if(this.isValidDestination(new Location(locc.getRow() - 1, locc.getCol() - 2)))
        {
            x.add(new Location(locc.getRow() - 1, locc.getCol() - 2));
        }
        if(this.isValidDestination(new Location(locc.getRow() - 2, locc.getCol() - 1)))
        {
            x.add(new Location(locc.getRow() - 2, locc.getCol() - 1));
        }
        if(this.isValidDestination(new Location(locc.getRow() + 1, locc.getCol() - 2)))
        {
            x.add(new Location(locc.getRow() + 1, locc.getCol() - 2));
        }
        if(this.isValidDestination(new Location(locc.getRow() + 2, locc.getCol() - 1)))
        {
            x.add(new Location(locc.getRow() + 2, locc.getCol() - 1));
        }
        if(this.isValidDestination(new Location(locc.getRow() + 2, locc.getCol() + 1)))
        {
            x.add(new Location(locc.getRow() + 2, locc.getCol() + 1));
        }
        if(this.isValidDestination(new Location(locc.getRow() + 1, locc.getCol() + 2)))
        {
            x.add(new Location(locc.getRow() + 1, locc.getCol() + 2));
        }
        return x;
    }
}
