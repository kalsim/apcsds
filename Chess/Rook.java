package Chess;

import java.awt.*;
import java.util.ArrayList;

/**
 * class declaration for Rook
 * @author Karthik Nukala
 * @version 28/5/18
 */
public class Rook extends Piece {
    private Color color;
    public Rook(Color col, String filename) { super(col, filename, 5); }

    /**
     * overriden destinations() method
     * @return ArrayList<Location>
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> x = new ArrayList<Location>();
        sweep(x, Location.NORTH);
        sweep(x, Location.SOUTH);
        sweep(x, Location.EAST);
        sweep(x, Location.WEST);
        return x;
    }
}
