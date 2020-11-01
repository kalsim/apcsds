package TetrisLab;


import TetrisLab.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Provides implementation for the tetrads used
 * in a game of Tetris
 *
 * @author Montek Kalsi
 * @version 4/3/18
 */
public class Tetrad
{
    private Block[] blocks;
    private Semaphore lock;
    private int shape;

    /**
     * constructs a tetrad object contained within grid
     * @param grid the grid in which the tetrad is in
     */
    public Tetrad(MyBoundedGrid<Block> grid)
    {
        blocks = new Block[4];
        for (int i = 0; i < blocks.length; i++)
            blocks[i] = new Block();
        getNextShape(grid);
        lock = new Semaphore(1, true);
    }

    /**
     * getter to output the blocks
     * @return blocks
     */
    public Block[] getBlocks()
    {
        return blocks;
    }

    /**
     * determines the next shape of the block
     * @param grid the grid used to determine the next block
     */
    private void getNextShape(MyBoundedGrid<Block> grid)
    {
        Color color = null;
        Location[] locs = new Location[4];

        shape = 0;
        boolean next = false;
        Random r = new Random();
        shape = r.nextInt(7);
        if (shape == 0)
        {
            color = Color.cyan;
            locs[0] = new Location(0, 5);
            locs[1] = new Location(0, 6);
            locs[2] = new Location(0, 3);
            locs[3] = new Location(0, 4);
        }
        else if (shape == 1)
        {
            color = Color.MAGENTA;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 3);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(0, 5);
        }
        else if (shape == 2)
        {
            color = Color.yellow;
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(1, 5);
        }
        else if (shape == 3)
        {
            color = Color.blue;
            locs[0] = new Location(2, 4);
            locs[1] = new Location(1, 4);
            locs[2] = new Location(0, 4);
            locs[3] = new Location(2, 5);
        }
        else if (shape == 4)
        {
            color = Color.ORANGE;
            locs[0] = new Location(2, 5);
            locs[1] = new Location(1, 5);
            locs[2] = new Location(0, 5);
            locs[3] = new Location(2, 4);
        }
        else if (shape == 5)
        {
            color = Color.RED;
            locs[0] = new Location(1, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 3);
            locs[3] = new Location(0, 4);
        }
        else if (shape == 6)
        {
            color = Color.green;
            locs[0] = new Location(1, 4);
            locs[1] = new Location(0, 4);
            locs[2] = new Location(0, 3);
            locs[3] = new Location(1, 5);
        }
        ArrayList<Location> loc = grid.getOccupiedLocations();
        for (Location l : loc)
        {
            if (l.equals(locs[0])) System.exit(1000);
            else if (l.equals(locs[1])) System.exit(1000);
            else if (l.equals(locs[2])) System.exit(1000);
            else if (l.equals(locs[3])) System.exit(1000);
        }

        for (Block b : blocks) b.setColor(color);
        addToLocations(grid, locs);
    }

    /**
     * puts the blocks in grid into the locations in locs
     * @precondition:  blocks are not in any grid; locs.length = 4.
     * @postcondition: the locations of blocks match locs, and blocks
     *                 have been put in the grid.
     * @param grid the grid which contains the blocks
     * @param locs the locations where the blocks are added
     */

    private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for (int i = 0; i < locs.length; i++)
        {
            blocks[i].putSelfInGrid(grid, locs[i]);
        }
    }

    /**
     * removes the blocks from the grid
     * @precondition:  Blocks are in the grid.
     * @postcondition: Returns old locations of blocks;
     *                 blocks have been removed from grid.
     * @return an array of the locations from which the blocks
     *         were removed
     */
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[blocks.length];
        for (int i = 0; i < blocks.length; i++)
        {
            locs[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();

        }
        return locs;
    }

    /**
     * determines whether the locations in locs are empty and
     * valid within grid
     * @postcondition: Returns true if each of locs is valid
     *                 AND empty in grid; false otherwise.
     * @param grid the grid with the locations
     * @param locs the locations being checked
     * @return true if eacch of the locs are valid and empty in the grid
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
    {
        ArrayList<Location> location = grid.getOccupiedLocations();
        boolean empty = true;
        for (int i = 0; i < locs.length; i++)
        {
            for (Location loc : location)
            {
                if (loc.equals(locs[i])) empty = false;
            }
            if (!grid.isValid(locs[i]) || !empty) return false;
        }
        return true;
    }

    /**
     * @postcondition: Attempts to move this tetrad deltaRow
     *                 rows down and deltaCol columns to the right,
     *                 if those positions are valid
     *                 and empty; returns true if successful and false otherwise.
     * @param deltaRow the rows being shifted
     * @param deltaCol the columns being shifted
     * @return true if the shift was successful
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        try
        {
            lock.acquire();
            MyBoundedGrid<Block> grid;
            grid = blocks[0].getGrid();
            Location[] oldLocs = removeBlocks();
            Location[] newLocs = new Location[blocks.length];
            for (int i = 0; i < newLocs.length; i++)
            {
                newLocs[i] =
                        new Location(oldLocs[i].getRow() + deltaRow, oldLocs[i].getCol() + deltaCol);
            }
            if (areEmpty(grid, newLocs))
            {
                addToLocations(grid, newLocs);
                return true;
            }
            else
            {
                addToLocations(grid, oldLocs);
                return false;
            }
        }
        catch (InterruptedException e)
        {
            return false;
        }
        finally
        {
            lock.release();
        }
    }

    /**
     * Rotates the tetrad 90 degrees about its center
     * @postcondition: Attempts to rotate this tetrad
     *                 clockwise by 90 degrees about its
     *                 center, if the necessary positions
     *                 are empty; returns true if successful
     *                 and false otherwise.
     * @return true if it rotates successfully
     */
    public boolean rotate()
    {
        if (shape == 2)
            return true;
        try
        {
            lock.acquire();
            MyBoundedGrid<Block> grid;
            grid = blocks[0].getGrid();
            Location[] oldLocs = removeBlocks();
            Location[] newLocs = new Location[blocks.length];
            int row = oldLocs[0].getRow();
            int col = oldLocs[0].getCol();
            for (int i = 0; i < newLocs.length; i++)
            {
                newLocs[i] =
                        new Location(row - col + oldLocs[i].getCol(), row + col - oldLocs[i].getRow());
            }
            if (areEmpty(grid, newLocs))
            {
                addToLocations(grid, newLocs);
                return true;
            }
            else
            {
                addToLocations(grid, oldLocs);
                return false;
            }
        }
        catch (InterruptedException e)
        {
            return false;
        }
        finally
        {
            lock.release();
        }
    }
}