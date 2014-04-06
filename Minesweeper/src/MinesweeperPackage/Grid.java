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
        
        if(choice.equals("easy") || choice.equals("medium") || choice.equals("hard"))
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
        int _x;
        int _y;
        for (int i = 0; i < maxBombs; i++) // for Easy_M -> 10 bombs; etc
        {
            _x = gen.nextInt(maxX);
            _y = gen.nextInt(maxY);
            bombs[_x][_y] = 9;
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
     * @param _x
     * @param _y 
     */
    private void selectBox(int _x, int _y)
    {
        // first check for special cases 0 and 9
        if(bombs[_x][_y] == 0)
        {
            // display blank
            // call recursive selection process
        }
        else if(bombs[_x][_y] == 9)
        {
            // display bomb
            // lose game
        }
        else
        {
            // display bombs
        }
    }
    
    private void adjacentBoxes(int _x, int _y)
    {
        // if the integer is not 0, return
        if(bombs[_x][_y] != 0)
        {
            display[_x][_y] = Integer.toString(bombs[_x][_y]);
        }
        else // integer must be zero, find adjacent of that
        {
            // create neighbor sub-grid
            // ** REPEATED CODE HERE
            // if any of these are zero, recursive
            int [] subX = new int [8];
            int [] subY = new int [8];

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
            
            subX[0] = _x + 1; 
            subY[0] = _y; 
            subX[1] = _x + 1; 
            subY[1] = _y + 1; 
            subX[2] = _x; 
            subY[2] = _y + 1; 
            subX[3] = _x - 1; 
            subY[3] = _y + 1; 
            subX[4] = _x - 1; 
            subY[4] = _y; 
            subX[5] = _x - 1; 
            subY[5] = _y - 1;
            subX[6] = _x; 
            subY[6] = _y - 1;
            subX[7] = _x + 1; 
            subY[7] = _y - 1;
            
            for(int r = 0; r < 8; r++) // iterate all sides
            {
                if (subX[r] >= 0 && subX[r] < bombs.length
                    && subY[r] >= 0 && subY[r] < bombs[r].length) 
                    // check whether within boundaries of grid
                {
                    if (bombs[subX[r]][subY[r]] == 0) 
                    {
                        // display then call recursive
                        display [subX[r]] [subY[r]] = "";
                        adjacentBoxes(subX[r], subY[r]);
                    }
                }
            }   
        }
    }
    
    public String getBombs()
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
    
    public String getDisplay()
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
    
}// end class
