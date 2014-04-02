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
     * @g is a two dimensional integer array which acts as the actual grid which 
     *  holds values of integers resembling the field
     * 0 is an empty slot, while 9 resembles the bomb
     */
    private int[][] g;
    
    /**
     * @hiders is a two dimensional char array which acts as the field which the
     *  user sees - a bunch of x that hide the actual values of the grid
     * 
     */
    private char[][] hiders;
    
        /**
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
        settings.put("easy", new Setting(10, 9, 9));
        settings.put("medium", new Setting(40, 16, 16));
        settings.put("hard", new Setting(99, 16, 30));
        
        if(choice.equals("easy") || choice.equals("medium") || choice.equals("hard"))
        {
            g = new int[settings.get(choice).getX()][settings.get(choice).getY()];
            generator(settings.get((choice)).getMines(), settings.get(choice).getX(), settings.get(choice).getY());
        }
    }
    
    /**
     * 
     * @param maxBombs
     * @param maxX
     * @param maxY 
     */
    private void generator(int maxBombs, int maxX, int maxY)
    {
        Random gen;
        gen = new Random();
        int _x;
        int _y;
        for (int i = 0; i < maxBombs; i++) // for Easy_M -> 10 bombs; etc
        {
            _x = gen.nextInt(maxX);
            _y = gen.nextInt(maxY);
            g[_x][_y] = 9;
        }   
    }
    
}
