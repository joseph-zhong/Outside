/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joseph Zhong
 * 
 */
import java.util.*;

public class RockPaperScissors 
{
    private static char userSelected;
    private static String tempInput;
    private static int compSelected;
    private static int computerScore;
    private static int userScore;
    private static int round;
    
    private static Random gen = new Random();
    private static Scanner scn = new Scanner(System.in);
    
    private static void init()
    {
        userSelected = 'α';
        round = 1;
    }
    
    private static void play()
    {
        while (userSelected != 'n')
        {
            if(round == 1)
            {
                userSelectedYes();
                StartProcess();
                testPrintChoice();
                System.out.println("ROUNNDDDDDD " + round);
                compPlay();

            }
            else
            {
                userSelectedYes();
                testPrintChoice();
                System.out.println("ROUNNDDDDDD " + round);
                compPlay();
            }
            
        }
        
    }
    
    public static void main(String[] args)
    {
        init();
        
        instructions();
        while(userSelected != 'n')
        {
            takeInput(500);
            if(userSelected == 'y')
            {
                System.out.println("You want to play?? \n"
                        + "\t:D :D :D :D :)\n"
                        + " YAY!!! This will be so fun!!\n\t"
                        + "There will be an infinite number of rounds, and score will be recorded, yours being the first number, and the computer's being the second.\n"
                        + "Remember to enter \"No\" whenever you want to quit and get back to work.");
                play();
            }
            else if(userSelected == 'n')
            {
                break;
            }
            else 
            {
                processing(500);
                warning();
            }
        }
        goodBye();
    }
    
    private static void goodBye()
    {
        System.out.println("Good-bye and continue giving us high scores. ");
    }
    
    private static void warning()
    {
        System.out.println("You have somehow entered something I don't understand. :( "
                + "\n"
                + "I only understand the terms that I instruct you, understand? (Now try again, please.) ");
        //takeInput();
    }
    
    private static void instructions()
    {
        System.out.println("Hello Counselor!!\n"
                + "Wanna play Rock Paper Scissors with the computer?!?\n"
                + "I programed this game myself (Joseph Zhong) in two days!\n"
                + "\t:D\n"
                + "(Type and enter \"Yes\" or \"No\"), please.");
        System.out.println("Type \"No\" to quit at any time, but if you want to play again, \n"
                + "please press \"Shift + F6\" to restart at any time.");
    }
    
    private static void StartProcess()
    {
        System.out.println(" in...");
        waitNSec(2000); System.out.print("3");
            waitNSec(500); System.out.print("."); waitNSec(500); System.out.print("."); waitNSec(500); System.out.println(".");
        waitNSec(1000); System.out.print("2");
            waitNSec(500); System.out.print("."); waitNSec(500); System.out.println(".");
        waitNSec(1000); System.out.print("1"); 
            waitNSec(1000); System.out.println(".");
        waitNSec(1000); System.out.print("Go!! --->>> ");
        
    }
    
    private static void userSelectedYes()
    {
        System.out.println("Type \"Rock\", \"Paper\", or \"Scissors\"");
                
    }
    
    private static void testPrintChoice()
    {
        takeInput(1000);
        if(userSelected == 'r')
        {
            printChoice(true, false, false);
        }
        else if(userSelected == 'p')
        {
            printChoice(false, true, false);
        }
        else if(userSelected == 's')
        {
            printChoice(false, false, true);
        }
    }
    
    private static void printChoice(boolean zero, boolean one, boolean two)
    {
        
        if(zero)
        {
            System.out.println("ROCK SELECTED!!\n"
                    + "\n   _____\n" 
                    + " /\\| | | | \n" 
                    + "/ /|_|_|_|\n"
                    + "\\\\        |\n"
                    + " \\_______/ \n");
        }
        else if(one)
        {
            System.out.println("PAPER SELECTED!!\n\n"
                + "          _\n"
                + "     . -  ' '   '  ' .\n"
                + "  . '       _,._       ' .\n"
                + ",'        ;'    \";        ',\n"
                + "|'         ',_ ,-'        .|\n"
                + "| '.          '         .  |\n"
                + "|   ' ,             . '    |\n"
                + "|   |   ' - . _ . -|       |\n"
                + "|               '. |       |\n"
                + "|   |             '.       |\n"
                + "|                   '.     |\n"
                + "|   |                .,    |\n"                                               
                + "|                    . ,   |\n"   
                + "|   |               .  '   |\n"
                + "|                  .   '   |\n"
                + "|   |            .    '    |\n"                                               
                + "!.              '    '    .'\n"                
                + "  '.|           '   ,   .\n"
                + "    ' ,        '   ,. '\n"
                + "        ' - . ,  .-\n"
                + "             ,  .\n"                                                        
                + "            , .\n"
                + "           , .\n"
                + "           ,'\n");
        }
        else if(two)
        {
            
        System.out.println("SCISSORS SELECTED!!!\n\n" +  
            "   ____\n" +
            "  / __ \\ \n" +
            " ( (__) |___ ___\n" +
            "  \\________,'   \"\"\"\"\"----....____\n" +
            "   _______<  () dd       ____----'\n" +
            "  / __   __`.___-----\"\"\"\"\n" +
            " ( (__) |\n" +
            "  \\____/\n");
        }
    }
    
    private static void compPlay()
    {
        compSelected = gen.nextInt(98);
        if(compSelected <= 32 && compSelected >= 0)
        {
            System.out.print("The computer has selected ROCK!!! ");
            printChoice(true, false, false);
            if(userSelected == 'r')
            {
                System.out.print("It is a TIE!!\n");    
            }
            else if(userSelected == 'p')
            {
                userScore++;
                System.out.print("PAPER BEATS ROCK!! YOU WIN THIS ROUND!!\n");
            } 
            else if(userSelected == 's')
            {
                computerScore++;
                System.out.print("ROCKS BEAT SCISSORS!! YOU LOSE THIS ROUND!!\n");
            }
            
        }
        else if(compSelected <= 65 && compSelected >= 33)
        {
            System.out.print("The computer has selected PAPER!!! ");
            printChoice(false, true, false);
            
            if(userSelected == 'r')
            {
                computerScore++;
                System.out.print("PAPER BEATS ROCK!! YOU LOSE THIS ROUND!!\n");
            }
            else if(userSelected == 'p')
            {
                System.out.print("It is a TIE!!\n");
            } 
            else if(userSelected == 's')
            {
                userScore++;
                System.out.print("SCISSORS BEAT PAPER!! YOU WIN THIS ROUND!!\n");
            }
        }
        else if (compSelected <= 98 && compSelected >= 65)
        {
            System.out.print("The computer has selected SCISSORS!!! ");
            printChoice(false, false, true);
            if(userSelected == 'r')
            {
                userScore++;
                System.out.print("ROCK BEATS SCISSORS!! YOU WIN THIS ROUND!!\n");
            }
            else if(userSelected == 'p')
            {
                computerScore++;
                System.out.print("SCISSORS BEAT PAPER!! YOU WIN THIS ROUND!!\n");
                
            } 
            else if(userSelected == 's')
            {
                System.out.print("It is a TIE!!\n");
            }
        }
        System.out.print("Score: " + userScore + "-" + computerScore);
        System.out.print("\nPlay again? (\"Yes\", \"No\")? ");
                takeInput(1000);
        if(userSelected == 'y')
        {
            round++;
            play();
        }
        else if(userSelected == 'n')
        {
            //goodBye();
            
        }
                
       
    }
    
    private static void processing(int n)
    {
        System.out.print("Processing");
        waitNSec(n); System.out.print(".");
        waitNSec(n); System.out.print(".");
        waitNSec(n); System.out.println(".");
        if(userSelected != 'y' && userSelected != 'n' && userSelected != 'r' && userSelected != 'p' && userSelected != 's')
        {
            warning();
        }
    }
    
    private static void waitNSec(int n)
    {
        long t0, t1; 
        t0 = System.currentTimeMillis();
        do
        {
            t1 = System.currentTimeMillis();
        }
        while(t1-t0 < n);
        
    }
    
    private static void takeInput(int n)
    {
        tempInput = scn.next();
        userSelected = tempInput.trim().toLowerCase().charAt(0);
        processing(n);
    }
}
