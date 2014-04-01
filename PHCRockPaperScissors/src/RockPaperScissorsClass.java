/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * EXTREMELY SIMPLE DEMO FOR MY JAVA CLASS 
 *  IN OLD REDMOND FIRE HOUSE 
 * @author Joseph
 * January session? 
 */
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissorsClass 
{
    public static String UserChoice;
    public static int ComputerChoice;
    
    public static void main (String[] args)
    {
        instructions(); //Prompt the user
        /*  */
        input();
        generate();
        compare();
    }
    
    public static void instructions()
    {
        System.out.println("Hello user, input your choice");
    }
    
    public static void input()
    {
       Scanner scan = new Scanner(System.in);
       System.out.println("Type Rock, Paper, or Scissors");
       UserChoice = scan.next();
    }
    
    public static void generate()
    {
        Random generator = new Random(/*seed*/);
        ComputerChoice = generator.nextInt(2);
    }
    
    public static void compare()
    {
        if(ComputerChoice == 0)
        {
            //win
            System.out.println("Computer Wins");
            if(UserChoice.equals("Rock"))
            {
                System.out.print("Computer won with paper");
            }
        }
        else if (ComputerChoice == 1)
        {
            System.out.print("Computer Loses");
        }
        else if (ComputerChoice == 0)
        {
            System.out.print("Computer Ties");
        }
    
    }
}






