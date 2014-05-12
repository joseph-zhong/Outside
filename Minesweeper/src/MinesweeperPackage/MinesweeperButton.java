/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MinesweeperPackage;

import javax.swing.AbstractButton;
import javax.swing.JToggleButton;

/**
 *
 *  Joseph Zhong
 *  ASSIGNMENT_NUMBER
 *  PROGRAM_DESCRIPTION
 *  PROGRAM_TITLE
 *  DATE
 *
 **/

public class MinesweeperButton extends JToggleButton
{
    private boolean isFlagged;

    public MinesweeperButton()
    {
        super();
        isFlagged = false;
    }

    public MinesweeperButton(String str)
    {
        super(str);
    }

    public boolean getIsFlagged()
    {
        return isFlagged;
    }

    public void setIsFlagged(boolean b)
    {
        isFlagged = b;
    }
}
