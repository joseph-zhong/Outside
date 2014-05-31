/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalLogic;

import java.util.Scanner;

/**
 *
 *  Joseph Zhong
 *  Minesweeper - Java (v2.5)
 *  This program is the Game Control for my overall Minesweeper Project
 *  Minesweeper - Game Control (Main)
 *  1 April 2014
 *
 **/

public class GameControl
{
    /**
     * MainGrid is the main Minesweeper Grid.
     */
    private Grid MainGrid;
    //private String settingKey;

    /**
     * Constructor Method.
     * @param settingKey the difficulty level to build the minesweeper grid.
     */
    public GameControl(String settingKey)
    {
        MainGrid = new Grid(settingKey);
    }

    /**
     * Default Constructor Method.
     * Does nothing and leaves MainGrid to be constructed later.
     */
    public GameControl()
    {

    }

    /**
     * Main Method.
     * Can run to play in text.
     * @param args cmd prompt stuff
     */
    public static void main(String[] args)
    {
        //Testing Methods in console
        Scanner scn = new Scanner(System.in);

        Grid g = new Grid("easy");
        System.out.println(g.printMines());
        System.out.println(g.toString());

        int y = scn.nextInt();
        int x = scn.nextInt();

        g.selectBox(x, y);

        System.out.println(g.toString());
    }

    /**
     * Constructor Method.
     * Builds a grid.
     * @param _settingKey
     */
    public void constructGrid(String _settingKey)
    {
        MainGrid = new Grid(_settingKey);
    }

    /**
     * Getter method.
     * @return the main minesweeper grid.
     */
    public Grid getMainGrid()
    {
        return MainGrid;
    }
}
