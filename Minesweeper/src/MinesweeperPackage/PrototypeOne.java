/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MinesweeperPackage;

import java.util.Random;
import java.util.Scanner;

/**
 *
 *  Joseph Zhong
 *  Outside Project - Minesweeper
 *  Minesweeper project inspired by Microsoft's Minesweeper game
 *  Text-Based Minesweeper (v1)
 *  2012(?)
 *
 * All content designed and written by Joseph Zhong.
 * 
 **/

public class PrototypeOne 
{
    // fields
    private static int[][] grid;
    private static String[][] placeholder;
    
    private static boolean play = true;
    
    private static int x = -1;
    private static int y = -1;
    
    private static int[][] M_G = 
    { 
        { 10, 10 }, { 40, 16 }, { 99, 25 } 
    };

    private static String userChoice;

    public static void main(String[] args)
    {
        start();
        testAdjacent();
        initPlaceholder();
        //printPlaceHolder();
        printIntArray(grid);
        while(play)
        {
            select();
            updatePlaceholder();
            printPlaceHolder();
        }
    }
    
    private static void printIntArray(int[][] o)
    {
        System.out.println();
        for (int r = 0; r < o.length; r++)
        {
            for (int c = 0; c < o[1].length; c++)
            {
                System.out.print(o[r][c]);
            }
            System.out.println();
        }
    }

    private static void initPlaceholder()
    {
        placeholder = new String[grid[0].length][grid[1].length];
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++)
            {
                placeholder[r][c] = "X";
            }
        }
    }

    private static void printPlaceHolder()
    {
        for (int r = -1; r < grid.length; r++)
        {
            if (r > -1) 
            {
                //System.out.print(r);
            }
            else 
            {
                System.out.print(" ");
            }
            for (int c = 0; c < grid[1].length; c++)
            {
                if (r == -1) 
                {
                    //System.out.print(c);
                }
                else 
                {
                    System.out.print(placeholder[r][c]);
                }
            }
            System.out.println();
        }
    }

    private static void select()
    {
        System.out.println("Please enter the y-coordinate of the square which you wish to uncover: ");
        check();
        System.out.println("Please enter the x-coordinate of the square which you wish to uncover: ");
        check();
    }

    private static void checkSpecial()
    {
        if (grid[x][y] == 9)
        {
            System.out.println("You have clicked a mine. You lose");
        }
        else if(grid[x][y] == 0)
        {
            
        }
    }    

    private static void uncoverAdjacent()
    {
        
    }
    
    private static void start()
    {
        System.out.print("Hello, welcome to Minesweeper by Joseph Zhong.\nPlease enter your desired difficulty level"
            + "(\"easy\", \"medium\", or \"hard\"): ");
        //Console.Write(userChoice);
        check();
    }

    private static void check()
    {
        Scanner scn;
        scn = new Scanner(System.in);
        userChoice = scn.next();
        userChoice = userChoice.toLowerCase().trim();

        if(userChoice.equals("easy"))
        {
            grid = new int[M_G[0][1]] [M_G[0][1]];
            generator(M_G[0][0], M_G[0][1]);
            return;
        }
        else if(userChoice.equals("medium"))
        {
            grid = new int[M_G[1][1]][M_G[1][1]];
            generator(M_G[1][0], M_G[1][1]);
            return;
        }
        else if (userChoice.equals("hard"))
        {
            grid = new int[M_G[2][1]][M_G[2][1]];
            generator(M_G[2][0], M_G[2][1]);
            return;
        }

        if(x == -1) 
        {
            x = Integer.parseInt(userChoice);
        }
        else if(y == -1)
        {
            y = Integer.parseInt(userChoice);
        }
    }

    private static void updatePlaceholder()
    {
        placeholder[x][y] = "" + grid[x][y];
        checkSpecial();
        x = -1;
        y = -1;
    }

    private static void generator(int m, int g)
    {
        Random gen;
        gen = new Random();
        int _x;
        int _y;
        for (int i = 0; i < m; i++) // for Easy_M -> 10 bombs; etc
        {
            _x = gen.nextInt(g);
            _y = gen.nextInt(g);
            grid[_x][_y] = 9;
        }   
    }

    private static void testAdjacent()
    {
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[1].length; c++)
            {
                addAdjacent(r, c);
            }
        }
    }

    //if mine, adjacent needs to ++
    private static void addAdjacent( int row,  int col)
    {
        if (grid[row][col] == 9)
        {
            return;
        }
        else
        {
            grid[row][col] = 0;

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
                if (row_neighbor[r] >= 0 && row_neighbor[r] < grid.length
                    && col_neighbor[r] >= 0 && col_neighbor[r] < grid[r].length)
                {
                    if (grid[row_neighbor[r]][col_neighbor[r]] == 9)
                    {
                        grid[row][col]++;
                    }
                }
            }
        }
    }
}

    
    // Code from C#... Written by Joseph Zhong in 2013 long time ago...
    
    /*
     * 
     * 
     * 
     * 
     * using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _2013
{
    
    class Minesweeper
    {
        private static int[ , ] grid;
        private static String[,] placeholder;

        private static bool play;

        private static int x = -1;
        private static int y= -1;

        private static int[,] M_G = new int[3,2] 
        { { 10, 10 }, { 40, 16 }, { 99, 25 } };
        
        private static String userChoice;

        private static void printIntArray(int[ , ] o)
        {
            Console.WriteLine();
            for (int r = 0; r < o.GetLength(0); r++)
            {
                for (int c = 0; c < o.GetLength(1); c++)
                {
                    Console.Write(o[r, c]);
                }
                Console.WriteLine();
            }
        }

        private static void initPlaceholder()
        {
            placeholder = new String[grid.GetLength(0), grid.GetLength(1)];
            for (int r = 0; r < grid.GetLength(0); r++)
            {
                for (int c = 0; c < grid.GetLength(1); c++)
                {
                    placeholder[r, c] = "X";
                }
            }
        }

        private static void printPlaceHolder()
        {
            for (int r = -1; r < grid.GetLength(0); r++)
            {
                if (r > -1)
                    Console.Write(r);
                else Console.Write(" ");
                for (int c = 0; c < grid.GetLength(1); c++)
                {
                    if (r == -1)
                        Console.Write(c);
                    else
                        Console.Write(placeholder[r, c]);
                }
                Console.WriteLine();
            }
        }

        private static void select()
        {
            Console.Write("Please enter the x-coordinate of the square which you wish to uncover: ");
            check();
            Console.Write("Please enter the y-coordinate of the square which you wish to uncover: ");
            check();
        }

        private static void checkLose()
        {
            if (grid[x, y] == 9)
            {
                Console.Write("You have clicked a mine. You lose");
            }
        }

        private static void Main(string[] args)
        {
            start();
            testAdjacent();
            initPlaceholder();
            printPlaceHolder();
            select();
            updatePlaceholder();
            printPlaceHolder();
        }

        private static void start()
        {
            Console.Write("Hello, welcome to Minesweeper by Joseph Zhong. Please enter your desired difficulty level"
                + "(\"easy\", \"medium\", or \"hard\"): ");
            //Console.Write(userChoice);
            check();
        }

        private static void check()
        {
            userChoice = Console.ReadLine();
            userChoice.ToLower().Trim();

            if (userChoice.Equals("easy"))
            {
                grid = new int[M_G[0,1], M_G[0,1]];
                generator(M_G[0,0], M_G[0,1]);
                return;
            }
            else if(userChoice.Equals("medium"))
            {
                grid = new int[M_G[1,1], M_G[1,1]];
                generator(M_G[1, 0], M_G[1, 1]);
                return;
            }
            else if (userChoice.Equals("hard"))
            {
                grid = new int[M_G[2,1] , M_G[2,1]];
                generator(M_G[2, 0], M_G[2, 1]);
                return;
            }

            if(x == -1)
                x = int.Parse(userChoice);
            else
                y = int.Parse(userChoice);
        }

        private static void updatePlaceholder()
        {
            placeholder[x, y] = grid[x, y].ToString();
            checkLose();
            x = -1;
            y = -1;
        }

        private static void generator(int m, int g)
        {
            Random gen = new Random();
            int x;
            int y;
            for (int i = 0; i < m; i++) // for Easy_M -> 10 bombs; etc
            {
                x = gen.Next(g);
                y = gen.Next(g);
                grid[x, y] = 9;
            }   
        }

        private static void testAdjacent()
        {
            for (int r = 0; r < grid.GetLength(0); r++)
            {
                for (int c = 0; c < grid.GetLength(1); c++)
                {
                    addAdjacent(r, c);
                }
            }
        }

        //if mine, adjacent needs to ++
        private static void addAdjacent( int row,  int col)
        {
            if (grid[row, col] == 9)
            {
                return;
            }
            else
            {
                grid[row, col] = 0;
                
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
                        if (row_neighbor[r] >= 0 && row_neighbor[r] < grid.GetLength(0) 
                            && col_neighbor[r] >= 0 && col_neighbor[r] < grid.GetLength(1))
                        {
                            if (grid[row_neighbor[r], col_neighbor[r]] == 9)
                            {
                                grid[row, col]++;
                            }
                        }
                    
                    
                }
            }
            
        }
    }
}

     * 
     */
    

