/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MinesweeperPackage;

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
    
    
    public static void main(String[] args)
    {
        Grid g = new Grid("easy");
        System.out.println(g.getBombs());
        System.out.println(g.getDisplay());
    }
}
