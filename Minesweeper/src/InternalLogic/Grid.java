/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalLogic;

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
     * Mines is a two dimensional integer array which acts as the actual grid which
     *  holds values of integers resembling the field
     * 0 is an empty slot, while 9 resembles the bomb
     */
    private int[][] Mines;

    /**
     * display is a two dimensional char array which acts as the field which the
     *  user sees - a bunch of x that hide the actual values of the grid
     *      Character that is initially all empty - displays the true integer
     *      value after selection
     */
    private String[][] display;

    /**
     * setting is a map of grid specs, accessed by a string key - resembling a
     *  game-mode difficulty level.
     * easy - 10 mines, 9 * 9 grid
     * medium - 40 m, 16*16 grid
     * hard - 99 m, 16*30 grid
     */
    private HashMap<String, Setting> settings;

    /**
     * String of User's difficulty choice.
     */
    private String UserChoice;


    /**
     * integer tracking safesqures left to click.
     * Used in winning
     */
    private int safeSquares;

    /**
     * Constructor.
     * @param choice is a user input that chooses which grid size to use
     */
    public Grid(String choice)
    {
        UserChoice = choice;

        settings = new HashMap<String, Setting>();
        settings.put("easy", new Setting(10, 9, 9));
        settings.put("medium", new Setting(25, 14, 14));
        settings.put("hard", new Setting(40, 17, 17));
        settings.put("test", new Setting(1, 3, 3));

        if(choice.equals("easy") || choice.equals("medium") || choice.equals("hard") || choice.equals("test"))
        {
            Mines = new int[settings.get(choice).getX()][settings.get(choice).getY()];
            generateMines(settings.get((choice)).getMines(), settings.get(choice).getX(),
                    settings.get(choice).getY());
            safeSquares = settings.get(choice).getX() * settings.get(choice).getY() - settings.get(choice).getMines() + 1;
        }

        addBombInformation();

        generateDisplay();
    }

    /**
     * Constructor Helper-Method (v2: Modified from v1.2).
     *  This method produces the Mines for the integer array.
     * PRECONDITION: @int Mines is null
     * @param maxMines is an integer value - resembles the maximum number of Mines
     *  for the given grid
     * @param maxX is an integer value - resembles the maximum horizontal size
     * @param maxY is an integer value - resembles the maximum vertical size
     */
    private void generateMines(int maxMines, int maxX, int maxY)
    {
        Random gen;
        gen = new Random();
        int _y = gen.nextInt(maxX);
        int _x = gen.nextInt(maxY);
        for (int i = 0; i < maxMines; i++) // for Easy_M -> 10 Mines; etc
        {
            while(Mines[_y][_x] == 9)
            {
                _y = gen.nextInt(maxX);
                _x = gen.nextInt(maxY);
            }
            Mines[_y][_x] = 9;
        }
    }

    /**
     * Constructor Helper-Method (Taken from v1.2).
     *  This method traverses through the grid and adds information to each box
     *  based on surrounding information.
     */
    private void addBombInformation()
    {
        for (int r = 0; r < Mines.length; r++)
        {
            for (int c = 0; c < Mines[1].length; c++)
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
     * PRECONDITION: Mines are set
     * @param row integer value resembling the x coordinate in the grid
     * @param col integer value resembling the y coordinate in the grid
     */
    private void addAdjacent(int row,  int col)
    {
        //if mine, adjacent needs to ++
        if (Mines[row][col] == 9)
        {
            return;
        }
        else
        {
            Mines[row][col] = 0;

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
                if (row_neighbor[r] >= 0 && row_neighbor[r] < Mines.length
                    && col_neighbor[r] >= 0 && col_neighbor[r] < Mines[r].length)
                {
                    if (Mines[row_neighbor[r]][col_neighbor[r]] == 9)
                    {
                        Mines[row][col]++;
                    }
                }
            }
        }
    }

    /**
     * Modifier helper method.
     * Method initializes each display as a "_" string
     */
    private void generateDisplay()
    {
        display = new String[Mines.length][Mines[1].length];
        for(int r = 0; r < Mines.length; r++)
        {
            for(int c = 0; c < Mines[r].length; c++)
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
     * PRECONDITION: @Mines array has been completed and the selected box must
     *  be valid
     * @param _y
     * @param _x
     */
    public void selectBox(int _y, int _x)
    {
        if(display[_y][_x].equals("_"))
        {
            // first check for special cases 0 and 9
            if(Mines[_y][_x] == 0)
            {
                // display blank (inside recursive process)
                // call recursive selection process
                adjacentBoxes(_y, _x);
            }
            else if(Mines[_y][_x] == 9)
            {
                // display bomb
                // lose game
                display[_y][_x] = "9";
                System.out.println("MESSAGE FROM INTERNAL LOGIC: You lose."); // for now
            }
            else
            {
                // display Mines
                display[_y][_x] = Integer.toString(Mines[_y][_x]);
                safeSquares--;
                if(safeSquares == 0)
                {
                    System.out.println("MESSAGE FROM INTERNAL LOGIC: You Win.");
                }
            }
        }
    }

    /**
     * Modifier method.
     * "flags" a square marked by the user.
     * @param _y the row
     * @param _x the column
     */
    public void flagBox(int _y, int _x)
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
     * Helper method.
     * Recursive function to call zero-clicks
     * @param _y row
     * @param _x column
     */
    private void adjacentBoxes(int _y, int _x)
    {
        // if the integer is not 0, return
            // display -> subs -> recursive
            display[_y][_x] = " ";
            safeSquares--;
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
            if (neighborY[r] >= 0 && neighborY[r] < Mines.length       // within y
                && neighborX[r] >= 0 && neighborX[r] < Mines[r].length // within x
                //&& Mines[subX[r]][subY[r]] == 0)           // is zero?
                && "_".equals(display[neighborY[r]][neighborX[r]]))  // is operable?
            {
                if(Mines[neighborY[r]][neighborX[r]] != 0
                        && display[neighborY[r]][neighborX[r]].equals("_"))
                {
                    display[neighborY[r]][neighborX[r]] = Integer.toString(Mines[neighborY[r]][neighborX[r]]);
                    safeSquares--;
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
    /**
     * Getter method.
     * @param dimension is a one or zero value, meant to represent either dimension
     *  of the grid to return the length of that dimension
     * @return the length of the dimension of the grid
     */
    public int getLength(boolean dimension)
    {
        if(dimension)
        {
            return Mines.length;
        }
        return Mines[1].length;
    }

    /**
     * Getter method.
     * @return integer safe squares left
     */
    public int getSafeSquares()
    {
        return safeSquares;
    }

    // debugging methods...

    /**
     * Getter Method.
     * @return String of the entire array of integer values.
     */
    public String printMines()
    {
        String str = "";
        for(int r = 0; r < Mines.length; r++)
        {
            for(int c = 0; c < Mines[r].length; c++)
            {
                str += Integer.toString(Mines[r][c]) + " ";
            }
            str += "\n";
        }
        return str;
    }

    /**
     * Getter method.
     * @param r row
     * @param c column
     * @return the integer value at the given row and column
     */
    public int getMines(int r, int c)
    {
        return Mines[r][c];
    }

    /**
     * Getter method.
     * @return Current setting previously choosen.
     */
    public Setting getCurrentSetting()
    {
        return settings.get(UserChoice);
    }

    /**
     * Getter Method.
     * @return difficulty previously choosen.
     */
    public String getDifficulty()
    {
        return UserChoice;
    }

    @Override
    /**
     * ToString method.
     * Returns the current display array.
     */
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

    /**
     * Getter method.
     * @param r row
     * @param c column
     * @return the display string at the row and col
     */
    public String getDisplay(int r, int c)
    {
        return display[r][c];
    }


}// end class
