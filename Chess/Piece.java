package Chess;

import java.awt.*;
import java.util.*;

/**
 * Abstract class declaration for a generic Piece object
 * @author Montek Kalsi
 * @version 5/29/18
 */
public abstract class Piece
{
	/**
	 * the board the piece is on
	 */
	private Board board;

	/**
	 * the location of the piece
	 */
	private Location location;

	/**
	 * the color of the piece
	 */
	private Color color;

	/**
	 * the file displaying the piece
	 */
	private String imageFileName;

	/**
	 * the value of this piece in a game of chess
	 */
	private int value;

	/**
	 * constructs a new Piece with the color col and value val.
	 */
	public Piece(Color col, String fileName, int val)
	{
		color = col;
		imageFileName = fileName;
		value = val;
	}

	/**
	 * outputs the board
	 * @return the board
	 */
	public Board getBoard()
	{
		return board;
	}

	/**
	 * outputs the location of the piece on the board
	 * @return the location
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * outputs the color of the piece
	 * @return the color
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * outputs the name of the file used to display this piece
	 * @return a string of the file name of the image
	 */
	public String getImageFileName()
	{
		return imageFileName;
	}

	/**
	 * outputs a number representing the relative value of the piece
	 * @return an integer value of the piece
	 */
	public int getValue()
	{
		return value;
	}

    /**
     * Puts this piece into a board. If there is another piece at the given
     * location, it is removed.
     * Precondition: (1) This piece is not contained in a grid (2)
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                    "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
    }

    /**
     * Removes this piece from its board.
     * Precondition: This piece is contained in a board
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                    "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece at the
     * given location, it is removed.
     * Precondition: (1) This piece is contained in a grid (2)
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException("This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        board.put(location, this);
    }

	/**
	 * returns whether or not dest is valid
	 * @param dest
	 * @return boolean
	 */
	public boolean isValidDestination(Location dest)
	{
		return board.isValid(dest) && (board.get(dest)==null || board.get(dest).getColor()!=color);

	}

	/**
	 * abstract destinations method
	 * @return nothing yet
	 */
	public abstract ArrayList<Location> destinations();

	/**
	 * gets all locations in one direction until a piece is in the way
	 * @param dests the possible destinations
	 * @param direction the direction it's checking
	 */
    public void sweep(ArrayList<Location> dests, int direction) {
		Location current = location.getAdjacentLocation(direction);
		while(isValidDestination(current))
		{
			dests.add(current);
			if(board.get(current) != null && board.get(current).getColor() != color)
			{
				break;
			}
			current = current.getAdjacentLocation(direction);
		}
	}

}