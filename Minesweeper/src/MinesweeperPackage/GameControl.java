/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MinesweeperPackage;

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
    private Grid MainGrid;
    //private String settingKey;


    public GameControl(String settingKey)
    {
        MainGrid = new Grid(settingKey);
    }

    public GameControl()
    {

    }

    public static void main(String[] args)
    {
        //Testing Methods in console
        Scanner scn = new Scanner(System.in);

        Grid g = new Grid("easy");
        System.out.println(g.printBombs());
        System.out.println(g.printDisplay());

        int y = scn.nextInt();
        int x = scn.nextInt();

        g.selectBox(x, y);

        System.out.println(g.printDisplay());
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

    public Grid getMainGrid()
    {
        return MainGrid;
    }
}
