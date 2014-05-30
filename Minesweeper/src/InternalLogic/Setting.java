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
    private int mines;

    private int xMax;

    private int yMax;

    public Setting(int _mines, int _xMax, int _yMax)
    {
        mines = _mines;
        xMax = _xMax;
        yMax = _yMax;
    }

    public int getMines()
    {
        return mines;
    }

    public int getX()
    {
        return xMax;
    }

    public int getY()
    {
        return yMax;
    }
}
