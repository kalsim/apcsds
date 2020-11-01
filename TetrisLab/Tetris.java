package TetrisLab;

import TetrisLab.ArrowListener;

/**
 * Tetris provides implementation for a game of tetris with methods
 * to start a game, clear rows, detect input, and more.
 *
 * @author Montek Kalsi
 *
 * @version 1/10/18
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    BlockDisplay display;
    private Tetrad current;
    private static int score;
    private static int level;
    private static int rowsCleared;

    /**
     * starts a game of Tetris
     * @param args
     */
    public static void main(String[] args)
    {
        Tetris t = new Tetris();
    }

    /**
     * constructs a game of tetris by setting the level to 1,
     * score to 0, and setting up a grid
     */
    public Tetris()
    {
        level = 1;
        rowsCleared = 0;
        grid = new MyBoundedGrid<>(20, 10);
        score = 0;
        display = new BlockDisplay(grid);
        display.setTitle("Tetris");
        display.setArrowListener(this);
        display.showBlocks();
        current = new Tetrad(grid);
        while (true) play();
    }

    /**
     * Outputs the score of the game of Tetris
     * @return the score
     */
    public static int getScore()
    {
        return score;
    }

    /**
     * Outputs the current level of the game of Tetris
     * @return the level
     */
    public static int getLevel()
    {
        return level;
    }

    /**
     * when the up arrow is pressed, the block is rotated
     */
    public void upPressed() {
        System.out.println("upPressed()");
        current.rotate();
        display.showBlocks();
    }

    /**
     * when the space bar is pressed, the block is translated
     * as far down as permitted
     */
    public void spacePressed()
    {
        System.out.println("spacePressed()");
        while (current.translate(1, 0));
        display.showBlocks();
    }

    /**
     * when the down arrow is pressed, the block is brought
     * one row down
     */
    public void downPressed() {
        System.out.println("downPressed()");
        current.translate(1, 0);
        display.showBlocks();
    }

    /**
     * when the left arrow is pressed, the block is translated
     * one column to the left
     */
    public void leftPressed() {
        System.out.println("leftPressed()");
        current.translate(0, -1);
        display.showBlocks();
    }

    /**
     * when the right arrow is pressed, the block is translated
     * one column to the right
     */
    public void rightPressed() {
        System.out.println("rightPressed()");
        current.translate(0, 1);
        display.showBlocks();
    }


    /**
     * starts a game of Tetris and changes the speed of the blocks
     * according to the level
     */
    public void play()
    {
        try
        {
            Thread.sleep(1000/level);
            boolean x = current.translate(1, 0);
            if(!x)
            {
                clearCompletedRows();
                current = new Tetrad(grid);

            }
            display.showBlocks();

        }
        catch(InterruptedException e)
        {

        }

    }

    /**
     * determines whether a row is completed
     * @param row the row being checked
     * @return true if row is full and completed
     */
    private boolean isCompletedRow(int row)
    {
        for(int col = 0; col < grid.getNumCols(); col++)
        {
            if(grid.get(new Location(row, col)) == null)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * clears the row positioned at the specified row
     * by deleting all of the blocks from that row
     * @param row the position of the row being removed
     */
    private void clearRow(int row)
    {
        for(int col = 0; col < grid.getNumCols(); col++)
        {
            grid.get(new Location(row, col)).removeSelfFromGrid();
        }
    }

    /**
     * clears all of the rows that have been completed,
     * and increments the score and level accordingly
     */
    private void clearCompletedRows()
    {
        int count = 0;
        for(int i = 0; i < grid.getNumRows(); i++)
        {
            if(isCompletedRow(i))
            {
                clearRow(i);
                for(int r = i - 1; r >= 0; r--)
                {
                    for(int c = 0; c < grid.getNumCols(); c++)
                    {
                        if(grid.get(new Location(r, c)) != null)
                        {
                            grid.get(new Location(r, c)).moveTo(new Location(r + 1, c));
                        }
                    }
                }
                count++;
            }
        }
        if (count == 1)
            score+=40*level;
        else if (count==2)
            score+=100*level;
        else if (count==3)
            score+=300*level;
        else if (count==4)
            score+=1200*level;
        rowsCleared+=count;
        level = rowsCleared/10 + 1;
    }
}
