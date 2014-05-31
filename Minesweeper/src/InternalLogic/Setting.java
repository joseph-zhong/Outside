/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalLogic;

/**
 *
 *  Joseph Zhong
 *  Minesweeper - Java (v2.5)
 *  This program is the Setting Object for my overall Minesweeper Project
 *  Minesweeper - Setting Object
 *  1 April 2014
 *
 **/

public class Setting
{
    /**
     * Integer value of Mines to set
     */
    private int mines;

    /**
     * Integer value of maximum horizontal range for grid.
     */
    private int xMax;

    /**
     * Integer value of maximum vertical range for grid.
     */
    private int yMax;

    /**
     * Constructor method.
     * @param _mines mines to set
     * @param _xMax xMaximum value
     * @param _yMax yMaximum value
     */
    public Setting(int _mines, int _xMax, int _yMax)
    {
        mines = _mines;
        xMax = _xMax;
        yMax = _yMax;
    }

    /**
     * Getter method.
     * @return mines for that setting
     */
    public int getMines()
    {
        return mines;
    }

    /**
     * Getter method.
     * @return x length of the grid
     */
    public int getX()
    {
        return xMax;
    }

    /**
     * Getter method.
     * @return y length of the grid
     */
    public int getY()
    {
        return yMax;
    }
}
