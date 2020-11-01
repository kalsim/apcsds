package Chess;

import java.awt.*;
import java.util.*;

/**
 * Class declaration which represents a single move in a game of chess.
 *
 * @author Montek Kalsi
 * @version 5/29/18
 */
public class Move
{
	/**
	 * the piece being moved
	 */
	private Piece piece;
	/**
	 * the location the piece is moved from
	 */
	private Location source;
	/**
	 * the location being moved to
	 */
	private Location destination;
	/**
	 * the captured piece if any at the destination
	 */
	private Piece victim;

	/**
	 * Constructs a new move for moving the piece to the destination.
	 * @param piece the piece being moved
	 * @param destination the end result of the piece
	 */
	public Move(Piece piece, Location destination)
	{
		this.piece = piece;
		this.source = piece.getLocation();
		this.destination = destination;
		this.victim = piece.getBoard().get(destination);

		if (source.equals(destination))
			throw new IllegalArgumentException("Both source and dest are " + source);
	}

	/**
	 * outputs the current piece
	 * @return the piece
	 */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * outputs the location being moved from
	 * @return the source
	 */
	public Location getSource()
	{
		return source;
	}

	/**
	 * outputs the end result of the piece
	 * @return the destination
	 */
	public Location getDestination()
	{
		return destination;
	}

	/**
	 * outputs the piece being captured if any
	 * @return the victim
	 */
	public Piece getVictim()
	{
		return victim;
	}

	/**
	 * outputs a description of the move in string format
	 * @return a string description of the move
	 */
	public String toString()
	{
		return piece + " from " + source + " to " + destination + " containing " + victim;
	}

	/**
	 * determines whether one move is the same as another
 	 * @param x the other move being compared to
	 * @return true if the moves are the same
	 */
	public boolean equals(Object x)
	{
		Move other = (Move)x;
		return piece == other.getPiece() && source.equals(other.getSource()) &&
			destination.equals(other.getDestination()) && victim == other.getVictim();
	}


	/**
	 * outputs a hash code for this move ensuring that
	 * equivalent moves have the same code
	 * @return an integer hash code corresponding to the move
	 */
	public int hashCode()
	{
		return piece.hashCode() + source.hashCode() + destination.hashCode();
	}
}