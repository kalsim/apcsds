package Chess;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Represesents a rectangular game board, containing Piece objects.
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class Board extends BoundedGrid<Piece>
{
	/**
	 * Constructs a new Board with the given dimensions
	 */
	public Board()
	{
		super(8, 8);
	}

	/**
	 * undoes the move
	 * @param move the move being undone
	 * Precondition:  move has already been made on the board
	 * Postcondition: piece has moved back to its source,
	 *               and any captured piece is returned to its location
	 */
	public void undoMove(Move move)
	{
		Piece piece = move.getPiece();
		Location source = move.getSource();
		Location dest = move.getDestination();
		Piece victim = move.getVictim();
		piece.moveTo(source);
		if (victim != null)
		{
			victim.putSelfInGrid(piece.getBoard(), dest);
		}
	}

	/**
	 * outputs an ArrayList of all of hte moves
	 * @param color the color being checked
	 * @return all the moves
	 */
	public ArrayList<Move> allMoves(Color color)
	{
		ArrayList<Move> al = new ArrayList<Move>();
		ArrayList<Location> pieces = this.getOccupiedLocations();
		for(Location loc : pieces)
		{
			Piece piece = get(loc);
			if(piece.getColor() == color)
			{
				ArrayList<Location> destinations = piece.destinations();
				for(Location l : destinations)
				{
					al.add(new Move(piece, l));
				}
			}
		}
		return al;
	}

	/**
	 * executes moves m
	 * @param m the move being executed
	 */
	public void executeMove(Move m)
	{
		Piece n = m.getPiece();
		n.removeSelfFromGrid();
		n.putSelfInGrid(this, m.getDestination());
	}
}