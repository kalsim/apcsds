package Chess;

import java.awt.Color;
import java.util.*;

/**
 * Implementation for a pawn piece in chess.
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class Pawn extends Piece
{

  /**
   * constructs a pawn piece for a game of chess
   * @param col the column where the pawn is placed
   * @param fileName the file being read
   */
  public Pawn(Color col, String fileName)
  {
    super(col, fileName, 1);
  }

  /**
   * outputs an arraylist of the locations
   * @return an arraylist with the locations of destinations
   */
  public ArrayList<Location> destinations()
  {
    ArrayList<Location> destinations = new ArrayList<Location>();
    ArrayList<Location> tempWhite = new ArrayList<Location>();
    ArrayList<Location> tempBlack = new ArrayList<Location>();
    int row = getLocation().getRow();
    int col = getLocation().getCol();
    for (int i = 0; i < 8; i++)
    {
      tempBlack.add(new Location(1, i));
      tempWhite.add(new Location(6, i));
    }
    if (getColor().equals(Color.BLACK))
    {
      Location loc1 = new Location(row + 1, col);
      if (isValidDestination(loc1))
      {
        destinations.add(loc1);
        Location loc2 = new Location(row + 2, col);
        if (isValidDestination(loc2) && isIn(getLocation(), tempBlack))
        {
          destinations.add(loc2);
        }
      }
      Location loc3 = new Location(row + 1, col + 1);
      if (inBounds(loc3)) {
        Piece p = getBoard().get(loc3);
        if (p != null && p.getColor().equals(Color.WHITE)) {
          destinations.add(loc3);
        }
      }
      Location loc4 = new Location(row + 1, col - 1);
      if (inBounds(loc4)) {
        Piece p = getBoard().get(loc4);
        if (p != null && p.getColor().equals(Color.WHITE)) {
          destinations.add(loc4);
        }
      }
      return destinations;
    }
    else
    {
      Location loc1w = new Location(row - 1, col);
      if (isValidDestination(loc1w)) {
        destinations.add(loc1w);
          Location loc2 = new Location(row - 2, col);
        if (isValidDestination(loc2) && isIn(getLocation(), tempWhite)) {
          destinations.add(loc2);
        }
      }
      Location loc3 = new Location(row - 1, col - 1);
      if (inBounds(loc3)) {
        Piece p = getBoard().get(loc3);
        if (p != null && p.getColor().equals(Color.BLACK)) {
          destinations.add(loc3);
        }
      }
      Location loc4 = new Location(row - 1, col + 1);
      if (inBounds(loc4)) {
        Piece p = getBoard().get(loc4);
        if (p != null && p.getColor().equals(Color.BLACK)) {
          destinations.add(loc4);
        }
      }
      return destinations;
    }
  }


  /**
   * determines whether loc is within the ArrayList of locs
   * @param loc the location being checked
   * @param locs the ArrayList of possible locations
   * @return true if loc is contained within locs
   */
  private boolean isIn(Location loc, ArrayList<Location> locs)
  {
    for(int i = 0; i < locs.size(); i++)
    {
      if(loc.equals(locs.get(i)))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * determines whether the location loc is within the boundaries
   * @param loc the location being checked
   * @return true if loc is within the bounds of the board
   */
  private boolean inBounds(Location loc)
  {
    int row = loc.getRow();
    int col = loc.getCol();
    return (row < 8 && row >= 0 && col < 8 && col >= 0);
  }

  /**
   * determines whether dest is a true destination on the board
   * @param dest the position being checked
   * @return true if dest is valid
   */
  public boolean isValidDestination(Location dest)
  {
    if(getBoard().isValid(dest)) {
      Piece temp = getBoard().get(dest);
      if (temp == null) {
        return true;
      }
    }
    return false;
  }

}
