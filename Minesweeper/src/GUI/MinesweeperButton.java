/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import javax.swing.AbstractButton;
import javax.swing.JToggleButton;

/**
 *
 *  Joseph Zhong
 *  Arcade - Java (v3)
 *  This program is the MinesweeperButton Object for my overall Arcade Project
 *  Arcade - Minesweeper Object
 *  1 April 2014
 *
 **/

public class MinesweeperButton extends JToggleButton
{
    /**
     * Boolean tracks if the button has been flagged or not.
     */
    private boolean isFlagged;

    /**
     * Default Constructor.
     * Builds a blank JToggleButton as not flagged.
     */
    public MinesweeperButton()
    {
        super();
        isFlagged = false;
    }

    /**
     * Constructor method.
     * Builds the JToggleButton with str and is not flagged.
     * @param str string to set the button with.
     */
    public MinesweeperButton(String str)
    {
        super(str);
        isFlagged = false;
    }

    /**
     * Getter method.
     * @return is the button flagged or not.
     */
    public boolean getIsFlagged()
    {
        return isFlagged;
    }

    /**
     * Setter method.
     * @param b boolean to set the button flagged or not.
     */
    public void setIsFlagged(boolean b)
    {
        isFlagged = b;
    }
}
