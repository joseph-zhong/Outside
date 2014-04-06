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
    
    
    public GameControl()
    {
        
    }
    
    public static void main(String[] args)
    {
        Scanner scn = new Scanner(System.in);
        
        Grid g = new Grid("easy");
        System.out.println(g.getBombs());
        System.out.println(g.getDisplay());
        
        int x = scn.nextInt();
        int y = scn.nextInt();
        
        g.selectBox(x, y);
        
        System.out.println(g.getDisplay());
    }
}
