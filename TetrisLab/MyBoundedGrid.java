package TetrisLab;

import java.util.ArrayList;

/**
 * Provides implementation for the grid which contains the blocks
 * in a game of Tetris
 *
 * @author Montek Kalsi
 * @version 4/3/18
 *
 * @param <E> the type of MyBoundedGrid
 */
public class MyBoundedGrid<E>
{
    /**
     * stores the number of rows in the grid
     */
    private int rows;
    /**
     * stores the number of columns in the grid
     */
    private int cols;
    /**
     * stores the grid in a 2d array
     */
    private E[][] grid;

    /**
     * constructs a MyBoundedGrid object with the number of rows
     * and columns passed in as a parameter
     * @param rows the number of rows in the grid
     * @param cols the number of columns in the grid
     */
    public MyBoundedGrid(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
        grid = (E[][]) new Object[rows][cols];
    }

    /**
     * outputs the number of rows
     * @return the integer value of the number of rows
     */
    public int getNumRows()
    {
        return rows;
    }

    /**
     * outputs the number of columns
     * @return the integer value of the number of columns
     */
    public int getNumCols()
    {
        return cols;
    }

    /**
     * outputs true if the location is valid
     * @param loc the location being checked for validity
     * @return true if the location is valid
     */
    public boolean isValid(Location loc)
    {
        int r = loc.getRow();
        int c = loc.getCol();
        if(r > this.getNumRows() - 1 || c > this.getNumCols() - 1)
            return false;
        else if(r < 0 || c < 0)
            return false;
        return true;
    }

    /**
     * places obj in location loc
     * @param loc the location where obj is placedd
     * @param obj the block being placed
     * @return what was previously at loc
     */
    public E put(Location loc, Object obj)
    {
        int r = loc.getRow();
        int c = loc.getCol();
        Object temp = get(loc);
        if(isValid(loc))
            grid[r][c] = (E) obj;
        return (E) temp;
    }

    /**
     * removes whatever was at position loc and returns it
     * @param loc the position from which the block is removed
     * @return what was previously at position loc
     */
    public E remove(Location loc)
    {
        int r = loc.getRow();
        int c = loc.getCol();
        Object temp = grid[r][c];
        if(isValid(loc))
            grid[r][c] = null;
        return (E) temp;
    }

    /**
     * this method gets whatever is at loc
     * @param loc the location being checked
     * @return the object at loc
     */
    public E get(Location loc)
    {
        int r = loc.getRow();
        int c = loc.getCol();
        return grid[r][c];
    }

    /**
     * obtains and outputs all of the occupied locations
     * @return an ArrayList<Location> with all of the occcupied locations
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                Location loc = new Location(i, j);
                if(grid[i][j] != null)
                {
                    locs.add(loc);
                }
            }
        }
        return locs;
    }

}


