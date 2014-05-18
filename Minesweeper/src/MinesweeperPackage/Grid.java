/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MinesweeperPackage;

import java.util.HashMap;
import java.util.Random;

/**
 *
 *  Joseph Zhong
 *  Minesweeper - Java (v2.5)
 *  This program is the Grid Object for my overall Minesweeper Project
 *  Minesweeper - Grid Object
 *  1 April 2014
 *
 **/

public class Grid
{
    /**
     * @bombs is a two dimensional integer array which acts as the actual grid which
     *  holds values of integers resembling the field
     * 0 is an empty slot, while 9 resembles the bomb
     */
    private int[][] bombs;

    /**
     * @display is a two dimensional char array which acts as the field which the
     *  user sees - a bunch of x that hide the actual values of the grid
     *      Character that is initially all empty - displays the true integer
     *      value after selection
     */
    private String[][] display;

    /**
     * @setting is a map of grid specs, accessed by a string key - resembling a
     *  game-mode difficulty level.
     * easy - 10 mines, 9 * 9 grid
     * medium - 40 m, 16*16 grid
     * hard - 99 m, 16*30 grid
     */
    private HashMap<String, Setting> settings;

    /**
     * Constructor.
     * @param choice is a user input that chooses which grid size to use
     */
    public Grid(String choice)
    {
        settings = new HashMap<String, Setting>();
        settings.put("easy", new Setting(10, 9, 9));
        settings.put("medium", new Setting(40, 16, 16));
        settings.put("hard", new Setting(99, 16, 30));
        settings.put("test", new Setting(1, 3, 3));

        if(choice.equals("easy") || choice.equals("medium") || choice.equals("hard") || choice.equals("test"))
        {
            bombs = new int[settings.get(choice).getX()][settings.get(choice).getY()];
            generateBombs(settings.get((choice)).getMines(), settings.get(choice).getX(),
                    settings.get(choice).getY());
        }

        addBombInformation();

        generateDisplay();
    }

    /**
     * Constructor Helper-Method (v2: Modified from v1.2).
     *  This method produces the bombs for the integer array.
     * PRECONDITION: @bombs is null
     * @param maxBombs is an integer value - resembles the maximum number of bombs
     *  for the given grid
     * @param maxX is an integer value - resembles the maximum horizontal size
     * @param maxY is an integer value - resembles the maximum vertical size
     */
    private void generateBombs(int maxBombs, int maxX, int maxY)
    {
        Random gen;
        gen = new Random();
        int _y;
        int _x;
        for (int i = 0; i < maxBombs; i++) // for Easy_M -> 10 bombs; etc
        {
            _y = gen.nextInt(maxX);
            _x = gen.nextInt(maxY);
            bombs[_y][_x] = 9;
        }
    }

    /**
     * Constructor Helper-Method (Taken from v1.2).
     *  This method traverses through the grid and adds information to each box
     *  based on surrounding information.
     */
    private void addBombInformation()
    {
        for (int r = 0; r < bombs.length; r++)
        {
            for (int c = 0; c < bombs[1].length; c++)
            {
                addAdjacent(r, c);
            }
        }
    }

    /**
     * Constructor Helper-Method (Taken from v1.2).
     *  This method takes the coordinates of the grid, creates a sub-grid of
     *  adjacent "neighbors". For each "bomb" in the sub-grid, the core value
     *  increases.
     * PRECONDITION: bombs are set
     * @param row integer value resembling the x coordinate in the grid
     * @param col integer value resembling the y coordinate in the grid
     */
    private void addAdjacent(int row,  int col)
    {
        //if mine, adjacent needs to ++
        if (bombs[row][col] == 9)
        {
            return;
        }
        else
        {
            bombs[row][col] = 0;

            int [] row_neighbor = new int [8];
            int [] col_neighbor = new int [8];

            row_neighbor[0] = row+1;
            col_neighbor[0] = col;
            row_neighbor[1] = row+1;
            col_neighbor[1] = col+1;
            row_neighbor[2] = row;
            col_neighbor[2] = col+1;
            row_neighbor[3] = row-1;
            col_neighbor[3] = col+1;
            row_neighbor[4] = row-1;
            col_neighbor[4] = col;
            row_neighbor[5] = row-1;
            col_neighbor[5] = col-1;
            row_neighbor[6] = row;
            col_neighbor[6] = col-1;
            row_neighbor[7] = row+1;
            col_neighbor[7] = col-1;

            for(int r = 0; r < 8; r++)
            {
                if (row_neighbor[r] >= 0 && row_neighbor[r] < bombs.length
                    && col_neighbor[r] >= 0 && col_neighbor[r] < bombs[r].length)
                {
                    if (bombs[row_neighbor[r]][col_neighbor[r]] == 9)
                    {
                        bombs[row][col]++;
                    }
                }
            }
        }
    }

    private void generateDisplay()
    {
        display = new String[bombs.length][bombs[1].length];
        for(int r = 0; r < bombs.length; r++)
        {
            for(int c = 0; c < bombs[r].length; c++)
            {
                display[r][c] = "_"; // for now
            }
        }
    }

    /**
     * Modifier method.
     *  This method takes input from the user
     * The grid that is selected changes the display char to display the integer
     *  value in the integer grid
     * PRECONDITION: @bombs array has been completed and the selected box must
     *  be valid
     * @param _y
     * @param _x
     */
    public void selectBox(int _y, int _x)
    {
        // first check for special cases 0 and 9
        if(bombs[_y][_x] == 0)
        {
            // display blank (inside recursive process)
            // call recursive selection process
            adjacentBoxes(_y, _x);
        }
        else if(bombs[_y][_x] == 9)
        {
            // display bomb
            // lose game
            display[_y][_x] = "9";
            System.out.println("You lose"); // for now
        }
        else
        {
            // display bombs
            display[_y][_x] = Integer.toString(bombs[_y][_x]);
        }
    }

    /**
     *
     * @param _y
     * @param _x
     */
    public void markBox(int _y, int _x)
    {
        if(display[_y][_x].equals("!"))
        {
           display[_y][_x] = "_";
        }
        else
        {
            display[_y][_x] = "!";
        }
    }

    /**
     *
     * @param _y
     * @param _x
     */
    private void adjacentBoxes(int _y, int _x)
    {
        // if the integer is not 0, return
            // display -> subs -> recursive
            display[_y][_x] = " ";
            //System.out.println(getDisplay()); // debug
            // create neighbor sub-grid
            // ** REPEATED CODE HERE
            // if any of these are zero, recursive
            int [] neighborY =  new int [8];
            int [] neighborX = new int [8];

            /**
             * [x-1, y-1] [x, y-1] [x+1, y-1]
             * [x-1, y]   [x,y]    [x+1, y]
             * [x-1, y+1] [x, y+1] [x+1, y+1]
             */

            /**
             * [5] [6] [7]
             * [4] [r] [0]
             * [3] [2] [1]
             */

        neighborY[0] = _y + 1;
        neighborX[0] = _x;

        neighborY[1] = _y + 1;
        neighborX[1] = _x + 1;

        neighborY[2] = _y;
        neighborX[2] = _x + 1;

        neighborY[3] = _y - 1;
        neighborX[3] = _x + 1;

        neighborY[4] = _y - 1;
        neighborX[4] = _x;

        neighborY[5] = _y - 1;
        neighborX[5] = _x - 1;

        neighborY[6] = _y;
        neighborX[6] = _x - 1;

        neighborY[7] = _y + 1;
        neighborX[7] = _x - 1;

        for(int r = 0; r < 8; r++) // iterate all sides
        {
            if (neighborY[r] >= 0 && neighborY[r] < bombs.length       // within y
                && neighborX[r] >= 0 && neighborX[r] < bombs[r].length // within x
                //&& bombs[subX[r]][subY[r]] == 0)           // is zero?
                && "_".equals(display[neighborY[r]][neighborX[r]]))  // is operable?
            {
                if(bombs[neighborY[r]][neighborX[r]] != 0)
                {
                    display[neighborY[r]][neighborX[r]] = Integer.toString(bombs[neighborY[r]][neighborX[r]]);
                    //System.out.println(getDisplay()); // debug
                }
                else
                {
                    // display then call recursive
                    //display [subX[r]] [subY[r]] = "";
                    adjacentBoxes(neighborY[r], neighborX[r]);
                }
            }
        }
    }

    // get methods
    public int getLength(boolean dimension)
    {
        if(dimension)
        {
            return bombs.length;
        }
        return bombs[1].length;
    }


    // debugging methods...

    public String printBombs()
    {
        String str = "";
        for(int r = 0; r < bombs.length; r++)
        {
            for(int c = 0; c < bombs[r].length; c++)
            {
                str += Integer.toString(bombs[r][c]) + " ";
            }
            str += "\n";
        }
        return str;
    }

    public int getBombs(int r, int c)
    {
        return bombs[r][c];
    }


    @Override
    public String toString()
    {
        String str = "";
        for(int r = 0; r < display.length; r++)
        {
            for(int c = 0; c < display[r].length; c++)
            {
                str += display[r][c] + " ";
            }
            str += "\n";
        }
        return str;
    }


    public String getDisplay(int r, int c)
    {
        return display[r][c];
    }

}// end class
