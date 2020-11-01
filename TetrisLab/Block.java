package TetrisLab;

import TetrisLab.Location;
import TetrisLab.MyBoundedGrid;

import java.awt.Color;
/**
 * class Block encapsulates a Block abstraction which can be placed into a Gridworld style grid
 *
 * @author Montek Kalsi
 * @version 4/3/18
 */
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;
    /**
     * constructs a blue block with the grid and location set to null
     */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }
    /**
     * outputs the color of the block
     * @return the color Color
     */
    public Color getColor()
    {
        return color;
    }
    /**
     * sets the color to a new color
     * @param newColor the new color of the block
     */
    public void setColor(Color newColor)
    {
        color = newColor;
    }

    /**
     * outputs the grid the block is in
     * @return MyBoundedGrid<Block>
     */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }

    /**
     * Obtains and outputs the location of the block
     * @return the location Location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * Removes the block from the grid
     */
    public void removeSelfFromGrid()
    {
        grid.remove(location);
        grid = null;
        location = null;
    }

    /**
     * Places the block into grid gr at position loc
     * @param gr the grid in which the block is added
     * @param loc the location of the block in the grid after it's added
     */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
        Block temp = gr.get(loc);
        if(temp != null)
        {
            temp.grid = null;
            temp.location = null;
        }
        grid = gr;
        location = loc;
        gr.put(loc, this);
    }

    /**
     * Moves the block to position newLocation in the grid
     * @param newLocation the new location of the block
     */
    public void moveTo(Location newLocation)
    {
        Location old = location;
        putSelfInGrid(grid, newLocation);
        grid.remove(old);
        grid.put(newLocation, this);
    }

    /**
     * Returns a string with the location and color of this block
     * @return the location and color of this block as a string
     */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}