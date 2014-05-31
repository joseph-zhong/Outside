/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import InternalLogic.GameControl;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 *
 *  Joseph Zhong
 *  Arcade - Java (v3)
 *  This program is the Main GUI Object for my overall Arcade Project
 *  Arcade - Main GUI Object
 *  1 April 2014
 *
 */

public class mainForm extends JFrame
{
    // GUI Variables declaration
    /**
     * GUI. Menu Item which triggers the easy minesweeper construction option.
     * EasyButton JMenuItem binded to JMenuBar FileMenu
     */
    private JMenuItem EasyButton;

    /**
     * GUI. Menu Item which triggers the medium minesweeper construction option.
     * EasyButton JMenuItem binded to JMenuBar FileMenu
     */
    private JMenuItem HardButton;

    /**
     * GUI. Menu Item which triggers the hard minesweeper construction option.
     * EasyButton JMenuItem binded to JMenuBar FileMenu
     */
    private JMenuItem MediumButton;

    /**
     * GUI. Message Label which holds content for the win/loss dialog box to
     *  display to the user
     * MessageLabel is a JLabel with Strings later set
     */
    private JLabel MessageLabel;

    /**
     * String Builder using HTML to format with more flexibility.
     *
     * sb is later built with a String, and put into MessageLabel
     */
    private StringBuilder sb;

    /**
     * NewGameMenu is a submenu with options for constructing different
     *  minesweeper difficulties.
     */
    private JMenu NewGameMenu;

    /**
     * QuitButton is another close option button.
     */
    private JMenuItem QuitButton;

    /**
     * HelpMenu is the Help-Menu in the Toolbar.
     */
    private JMenu HelpMenu;

    /**
     * FileMenu is the File Sub-Menu in the Toolbar.
     */
    private JMenu FileMenu;

    /**
     * MainMenuBar contains all the buttons in the Toolbar.
     */
    private JMenuBar MainMenuBar;

    /**
     * MainPanel is the main JPanel object in this.
     * this is a Jframe. NetBeans likes to do it that way for some reason.
     */
    private JPanel MainPanel;
    // End of GUI variables declaration -- yes. Thanks NetBeans.

    /**
     * InformationFrame is the secondary Frame which contains the panels for
     *  timer and mines left information.
     */
    private InformationGUI InformationFrame;

    /**
     * Enumerated values appended with String names. Helped with avoiding magic
     *  numbers and with making the constructGrid method more robust, getting
     *  access to the string values.
     */
    private enum SizeSettings
    {
        EASY(500, 500), MEDIUM(680, 680), HARD(800, 680);

        private int x;
        private int y;

        private SizeSettings(int _x, int _y)
        {
            x = _x;
            y = _y;
        }
    };

    /**
     * Helper Dimension Object for setting the main Frame size.
     */
    private static Dimension FrameSize;

    /**
     * Helper Dimension Object for setting the main panel size.
     */
    private static Dimension PanelSize;

    /**
     * Helper Dimension Object for setting the main Frame size.
     * Not implemented yet.
     */
    private final static Dimension ScreenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();

    /**
     * Internal Game Controller Object.
     */
    private static GameControl MainManager;

    /**
     * External Button Controller array. Contains a grid of each external button.
     */
    private static MinesweeperButton[][] ButtonGrid;

    // simul clicky stuff
    /**
     * Part 1 of calculating simul-click boolean.
     * Saves typing from MouseEv... to just B1DM
     * Resembles Left Click
     */
    private static final int B1DM = MouseEvent.BUTTON1_DOWN_MASK;

    /**
     * Part 2 of calculating simul-click boolean.
     * Saves typing.
     * Resembles Right Click
     */
    private static final int B3DM = MouseEvent.BUTTON3_DOWN_MASK;

    /**
     * boolean that resembles the result of the comparison between B2DM and B3DM.
     *  Is true if both clicks are true.
     */
    private boolean bothWereDown;

    /**
     * Counter int for helping with simul-click selection of buttons.
     */
    private int flaggedNeighbors;

    // winning stuff
    /**
     * safeButtonsLeft is a counter to count if the user has won or not.
     */
    private int safeButtonsLeft;

    /**
     * minesLeft is a helper counter for the InformationGUI
     */
    private int minesLeft;


    private int buttonsSelectedCounter;

    private boolean timerStart;

    /**
     * Modified "Generated Code".
     * Initialize the GUI Components of the program.
     *  THIS PART GETS EXTREMELY CONFUSING.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Bull Excrements Code">
    private void initComponents()
    {
        //MainFrame = new JFrame();

        sb = new StringBuilder(64);

        sb.append(instructions2());

        MessageLabel = new JLabel(sb.toString());

        MainPanel = new JPanel();
        MainMenuBar = new JMenuBar();
        FileMenu = new JMenu();
        NewGameMenu = new JMenu();
        EasyButton = new JMenuItem();
        MediumButton = new JMenuItem();
        HardButton = new JMenuItem();
        QuitButton = new JMenuItem();
        HelpMenu = new JMenu();

        // frame things -- useless!
        /*
        GroupLayout mainFrameLayout = new GroupLayout(MainFrame.getContentPane());
        MainFrame.getContentPane().setLayout(mainFrameLayout);
        mainFrameLayout.setHorizontalGroup(
            mainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainFrameLayout.setVerticalGroup(
            mainFrameLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                formMouseReleased(evt);
            }
        });
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                formKeyPressed(evt);
            }
        });

        GroupLayout mainPanelLayout = new GroupLayout(MainPanel);
        MainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(40, 60, 80)
                .addComponent(MessageLabel)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(MessageLabel)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        FileMenu.setText("File");
        FileMenu.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                FileMenuMouseClicked(evt);
            }
        });

        NewGameMenu.setText("New Game");

        EasyButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        EasyButton.setText("Easy");
        EasyButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                EasyButtonMouseReleased(evt);
            }
        });
        NewGameMenu.add(EasyButton);

        MediumButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        MediumButton.setText("Medium");
        MediumButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                MediumButtonMouseReleased(evt);
            }
        });
        NewGameMenu.add(MediumButton);

        HardButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        HardButton.setText("Hard");
        HardButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                HardButtonMouseReleased(evt);
            }
        });
        NewGameMenu.add(HardButton);

        FileMenu.add(NewGameMenu);

        QuitButton.setText("Quit");
        QuitButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                QuitButtonMouseReleased(evt);
            }
        });
        FileMenu.add(QuitButton);

        MainMenuBar.add(FileMenu);

        HelpMenu.setText("Help");
        MainMenuBar.add(HelpMenu);

        setJMenuBar(MainMenuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        //post everything
        this.setTitle("Arcade");

        JLabel label = new JLabel(sb.toString());
        add(label);

        setVisible(true);
        repaint();
        pack();
    }// </editor-fold>

    /**
     * Main Constructor.
     * Creates new form MinesweeperGUI
     */
    public mainForm()
    {
        timerStart = false;
        bothWereDown = false;
        initComponents();
    }

    /**
     * Instructions String. Replaced by a StringBuilder object using HTML appending.
     *  Intended to use for Displaying instructions to the user.
     * @return String for instructions.
     */
    /*
    private String instructions()
    {
        String instructions =
                "\t\tWelcome to MiArcade (v0.0.2)! "
                + "\nClick on File to get started, or Help for more information."
                + "\n\n\t Keyboard Shortcuts: F2 for New Game";
        return instructions;
    }
    * */

    /**
     * Instructions String.
     * Uses HTML to format the string.
     * @return String for instructions.
     */
    private String instructions2()
    {
        String instructions2 =
            "<html>"
            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
            + "Welcome to MiArcade (v0.0.2)! "
            + "<br>Click on File to get started, or Help for more information."
            + "<br><br>"
            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
            + "Keyboard Shortcuts: F2 for New Game</html>";
        return instructions2;
    }

    /**
     * Exit method.
     *  Exits the program as does Alt+F4 or Clicking the Red X in the upper right
     *  corner.
     * @param evt User Mouse Click release.
     */
    private void QuitButtonMouseReleased(MouseEvent evt)
    {
        // TODO add your handling code here:
        //this.dispose(); old way
        System.exit(0);
    }

    /**
     * Constructor Method.
     * Constructs the easy grid.
     * @param evt User Mouse Click release.
     */
    private void EasyButtonMouseReleased(MouseEvent evt)
    {
        MainPanel.removeAll();
        constructMinesweeper("easy");
    }

    /**
     * Semi-Robust constructor helper method.
     * Initializes and constructs the grid based on the difficulty setting.
     * @param difficulty is the difficulty level
     */
    private void constructMinesweeper(String difficulty)
    {
        MainManager = new GameControl(difficulty);
        if(InformationFrame == null)
        {
            InformationFrame = new InformationGUI();
        }
        InformationFrame.resetClock();
        timerStart = false;

        minesLeft = MainManager.getMainGrid().getCurrentSetting().getMines();
        InformationFrame.setMines(minesLeft);

        ButtonGrid = new MinesweeperButton[MainManager.getMainGrid().getLength(false)][MainManager.getMainGrid().getLength(true)];
       // produce a GUI grid

        for(int y = 0; y < MainManager.getMainGrid().getLength(false); y++)
        {
            for(int x = 0; x < MainManager.getMainGrid().getLength(true); x++)
            {
                ButtonGrid[y][x] = new MinesweeperButton(" ");
                MouseListener MouseClick;
                MouseMotionListener MouseMove;

                MouseMove = new MouseMotionListener()
                {
                    @Override
                    public void mouseDragged(MouseEvent evt)
                    {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        //System.out.println("Button detected dragging: " + e.getX() + ", " + e.getY());
                        bothWereDown = false;
                    }

                    @Override
                    public void mouseMoved(MouseEvent evt)
                    {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        //System.out.println("Button detected movement: " + e.getX() + ", " + e.getY());
                    }
                };

                MouseClick = new MouseListener()
                {
                    @Override
                    public void mouseClicked(MouseEvent evt)
                    {
                        if(SwingUtilities.isLeftMouseButton(evt))
                        {
                            System.out.println("Left click Successful");
                        }
                        else if(SwingUtilities.isRightMouseButton(evt))
                        {
                            System.out.println("Right click Succesful");
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent evt)
                    {
                        int y = 0; int x = 0;
                        outerloop:
                        for(y = 0; y < ButtonGrid.length; y++)
                        {
                            for(x = 0; x < ButtonGrid[1].length; x++)
                            {
                                if(((MinesweeperButton) evt.getSource()).equals(ButtonGrid[y][x]))
                                {
                                    break outerloop;
                                    // coordinates saved
                                }
                            }
                        }

                        System.out.println("Press Successful");
                        int both = B1DM | B3DM;
                        bothWereDown = (evt.getModifiersEx() & both) == both;

                        if (bothWereDown)
                        {
                            // action if both buttons pressed
                            int r = getButtonCoordinates(evt)[0];
                            int c = getButtonCoordinates(evt)[1];

                            //makeNeighborCoordinates(r, c);
                            int [] row_neighbor = new int [8];
                            int [] col_neighbor = new int [8];

                            row_neighbor[0] = r + 1;
                            col_neighbor[0] = c;
                            row_neighbor[1] = r + 1;
                            col_neighbor[1] = c + 1;
                            row_neighbor[2] = r;
                            col_neighbor[2] = c + 1;
                            row_neighbor[3] = r - 1;
                            col_neighbor[3] = c + 1;
                            row_neighbor[4] = r - 1;
                            col_neighbor[4] = c;
                            row_neighbor[5] = r - 1;
                            col_neighbor[5] = c - 1;
                            row_neighbor[6] = r;
                            col_neighbor[6] = c - 1;
                            row_neighbor[7] = r + 1;
                            col_neighbor[7] = c - 1;

                            flaggedNeighbors = 0;

                            for(int s = 0; s < 8; s++) // iterate all sides
                            {
                                if (row_neighbor[s] >= 0 && row_neighbor[s] < ButtonGrid.length       // within y
                                    && col_neighbor[s] >= 0 && col_neighbor[s] < ButtonGrid[1].length // within x
                                    && !ButtonGrid[row_neighbor[s]][col_neighbor[s]].isSelected()
                                    && ButtonGrid[row_neighbor[s]][col_neighbor[s]].getIsFlagged())
                                {
                                    flaggedNeighbors++;
                                }
                                else if(row_neighbor[s] >= 0 && row_neighbor[s] < ButtonGrid.length       // within y
                                    && col_neighbor[s] >= 0 && col_neighbor[s] < ButtonGrid[1].length // within x
                                    && !ButtonGrid[row_neighbor[s]][col_neighbor[s]].isSelected())
                                {
                                    ButtonGrid[row_neighbor[s]][col_neighbor[s]].getModel().setArmed(true);
                                    ButtonGrid[row_neighbor[s]][col_neighbor[s]].getModel().setPressed(true);
                                }
                            }

                            System.out.println("Flagged Neighbors: " + flaggedNeighbors);
                            System.out.println("Simultaneous click successful");
                        }

                    }

                    @Override
                    public void mouseReleased(MouseEvent evt)
                    {
                        if(!timerStart)
                        {
                            timerStart = true;
                            InformationFrame.startClock();
                        }

                        AbstractButton abstractButton = (AbstractButton) evt.getSource();

                        int y = 0; int x = 0;
                        outerloop:
                        for(y = 0; y < ButtonGrid.length; y++)
                        {
                            for(x = 0; x < ButtonGrid[1].length; x++)
                            {
                                if(abstractButton.equals(ButtonGrid[y][x]))
                                {
                                    break outerloop;
                                    // coordinates saved
                                }
                            }
                        }
                        System.out.println(y + " " + x);

                        // prepare special icons
                        ImageIcon FlagIcon;
                        FlagIcon = new ImageIcon("C://Users/Joseph/Downloads/GitHub/Outside/2013/Minesweeper/src/Images/FlagImage.png");
                        ImageIcon MineIcon;
                        MineIcon = new ImageIcon("C://Users/Joseph/Downloads/GitHub/Outside/2013/Minesweeper/src/Images/MineImage.png");

                        // prepare resize
                        Image MineImage = MineIcon.getImage(); // transform it
                        Image FlagImage = FlagIcon.getImage();

                        int maxSize = Math.max(abstractButton.getHeight(), abstractButton.getWidth()) / 2;

                        Image rescaledImage;
                        ImageIcon imageIcon;

                        int bothMask = MouseEvent.BUTTON1_DOWN_MASK | MouseEvent.BUTTON3_DOWN_MASK;
                        if (bothWereDown && !MainManager.getMainGrid().getDisplay(y, x).equals("_"))
                        {
                            System.out.println("Both down");
                            int row = getButtonCoordinates(evt)[0];
                            int col = getButtonCoordinates(evt)[1];

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

                            MinesweeperButton saveTypingButton;
                            for(int s = 0; s < 8; s++)
                            {
                                if(row_neighbor[s] >= 0 && row_neighbor[s] < ButtonGrid.length
                                    && col_neighbor[s] >= 0 && col_neighbor[s] < ButtonGrid[1].length
                                    && !(saveTypingButton = ButtonGrid[row_neighbor[s]][col_neighbor[s]]).getIsFlagged()
                                    /*&& !saveTypingButton.isSelected()*/
                                    && flaggedNeighbors == Integer.parseInt(ButtonGrid[y][x].getText()))
                                {

                                    MainManager.getMainGrid().selectBox(row_neighbor[s], col_neighbor[s]);
                                    //safeButtonsLeft--;
                                    //System.out.println("Safe Buttons Left: " + safeButtonsLeft);
                                    String displayText = MainManager.getMainGrid().getDisplay(row_neighbor[s], col_neighbor[s]);
                                    if(displayText.equals("9"))
                                    {
                                        rescaledImage = MineImage.getScaledInstance(maxSize, maxSize, Image.SCALE_SMOOTH); // scale it the smooth way
                                        imageIcon = new ImageIcon(rescaledImage);  // transform it back

                                        JLabel test1 = new JLabel(imageIcon);

                                        saveTypingButton.setBackground(Color.red);
                                        saveTypingButton.add(test1);

                                        // initiate losing
                                        losing();
                                    }
                                    saveTypingButton.setText(displayText);

                                    resetFont(displayText, row_neighbor[s], col_neighbor[s]);
                                }
                            }
                        }

                        // right click flag
                        if(SwingUtilities.isRightMouseButton(evt)
                                && !SwingUtilities.isLeftMouseButton(evt)
                                && !abstractButton.isSelected())
                        {
                            if(!ButtonGrid[y][x].getIsFlagged())
                            {
                                rescaledImage = FlagImage.getScaledInstance(maxSize, maxSize, Image.SCALE_SMOOTH);
                                imageIcon = new ImageIcon(rescaledImage);
                                JLabel iconLabel = new JLabel(imageIcon);
                                abstractButton.add(iconLabel);
                                ButtonGrid[y][x].setIsFlagged(true);
                                MainManager.getMainGrid().flagBox(y, x);
                                //safeButtonsLeft--;
                                //System.out.println("Safe Buttons Left: " + safeButtonsLeft);
                                resetFont("", y, x);
                                minesLeft--;
                                InformationFrame.setMines(minesLeft);
                            }
                            else
                            {
                                ButtonGrid[y][x].setIsFlagged(false);
                                MainManager.getMainGrid().flagBox(y, x);
                                //safeButtonsLeft--;
                                //System.out.println("Safe Buttons Left: " + safeButtonsLeft);
                                ButtonGrid[y][x].removeAll();
                                resetFont("", y, x);
                                minesLeft++;
                                InformationFrame.setMines(minesLeft);
                            }
                        }
                        else if(SwingUtilities.isLeftMouseButton(evt)
                                && !SwingUtilities.isRightMouseButton(evt)
                                && !ButtonGrid[y][x].getIsFlagged())
                        {
                            // leftClick
                            MainManager.getMainGrid().selectBox(y, x);
                            //safeButtonsLeft--;
                            //System.out.println("Safe Buttons Left: " + safeButtonsLeft);
                            String displayText = MainManager.getMainGrid().getDisplay(y, x);
                            if(displayText.equals("9"))
                            {
                                rescaledImage = MineImage.getScaledInstance(maxSize, maxSize, Image.SCALE_SMOOTH); // scale it the smooth way
                                imageIcon = new ImageIcon(rescaledImage);  // transform it back

                                JLabel test1 = new JLabel(imageIcon);

                                abstractButton.setBackground(Color.red);
                                abstractButton.add(test1);

                                //intiate losing
                                losing();
                            }
                            abstractButton.setText(displayText);

                            resetFont(displayText, y, x);

                            //break; // apparently that made all the differnece lol
                        }
                        else
                        {
                            abstractButton.setSelected(false);
                        }

                        // update everything
                        safeButtonsLeft = MainManager.getMainGrid().getLength(true)
                            * MainManager.getMainGrid().getLength(false)
                            - MainManager.getMainGrid().getCurrentSetting().getMines();
                        int count = 0;

                        for(int r = 0; r < ButtonGrid.length; r++)
                        {
                            for(int c = 0; c < ButtonGrid[1].length; c++)
                            {
                                // check for zeros
                                String displayText = MainManager.getMainGrid().getDisplay(r, c);
                                if(!(displayText.equals("_"))
                                        && !displayText.equals("!"))
                                {
                                    ButtonGrid[r][c].setSelected(true);
                                    ButtonGrid[r][c].setText(displayText);
                                    /*
                                    if(!MainManager.getMainGrid().getDisplay(r, c).equals("9"))
                                    {
                                        safeButtonsLeft--;
                                        System.out.println("Safe Buttons Left: " + safeButtonsLeft);
                                    }
                                    * */
                                    //System.out.println("Safe Squares: " + MainManager.getMainGrid().getSafeSquares());
                                    //count++;
                                    //System.out.println("Count: " + count);
                                    resetFont(displayText, r, c);
                                }

                                //if(ButtonGrid[r][c].isSelected())
                                if(!MainManager.getMainGrid().getDisplay(r, c).equals("_") && !MainManager.getMainGrid().getDisplay(r, c).equals("!"))
                                {
                                    count++;
                                }
                                System.out.println("Count: " + count);
                                ButtonGrid[r][c].getModel().setArmed(false);
                                ButtonGrid[r][c].getModel().setPressed(false);
                            }
                        }

                        if(MainManager.getMainGrid().getLength(true)
                            * MainManager.getMainGrid().getLength(false)
                            - MainManager.getMainGrid().getCurrentSetting().getMines() == count)
                        {
                            winning();
                        }
                        requestFocusInWindow(); // works now
                    }

                    @Override
                    public void mouseEntered(MouseEvent evt)
                    {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        AbstractButton abstractButton = (AbstractButton) evt.getSource();
                        abstractButton.getModel().setArmed(true);
                    }

                    @Override
                    public void mouseExited(MouseEvent evt)
                    {
                        //throw new UnsupportedOperationException("Not supported yet.");
                        AbstractButton abstractButton = (AbstractButton) evt.getSource();
                        //abstractButton.getModel().setArmed(false);
                        //abstractButton.getModel().setPressed(false);
                    }
                };

                ButtonGrid[y][x].addMouseListener(MouseClick);
                ButtonGrid[y][x].addMouseMotionListener(MouseMove);
                MainPanel.add(ButtonGrid[y][x]);

                System.out.println(y + ", " + x); // debug
            }
        } // end for loop

        // sets panel and frame
        this.setResizable(false); // MUST PUT THIS LINE BEFORE BOUNDS AND SIZES ARE SET

        FrameSize = new Dimension(SizeSettings.valueOf(difficulty.toUpperCase()).x, SizeSettings.valueOf(difficulty.toUpperCase()).y);
        PanelSize = new Dimension(SizeSettings.valueOf(difficulty.toUpperCase()).x, SizeSettings.valueOf(difficulty.toUpperCase()).y);

        this.setSize(FrameSize);
        this.setLocation(300, 0);

        MainPanel.setPreferredSize(PanelSize);

        MainPanel.setLayout(new GridLayout(MainManager.getMainGrid().getLength(true), MainManager.getMainGrid().getLength(false)));
        this.setVisible(true);

        this.pack();
    }

    private void winning()
    {
        String[] options = {"Try again", "Go back to Start", "Quit"};
        InformationFrame.stopClock();
        int n = JOptionPane.showOptionDialog(rootPane, "You won!"
                + "\n╔══╗░░░░╔╦╗░░╔═════╗"
                + "\n║╚═╬════╬╣╠═╗║░▀░▀░║"
                + "\n╠═╗║╔╗╔╗║║║╩╣║╚═══╝║"
                + "\n╚══╩╝╚╝╚╩╩╩═╝╚═════╝", "Smileys  c:  ☺  ☻  ت ヅ  ツ  ッ  シ Ü  ϡ  ﭢ"
                + "\nWhat would you like to do now?",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[2]);

        if(n == 2)
        {
            //System.out.println("n = " + n);
            System.exit(0);
        }
        else if(n == 1)
        {
            InformationFrame.dispose();
            main(null);
            this.dispose();
        }
        else if(n == 0)
        {
            String difficulty = MainManager.getMainGrid().getDifficulty();
            MainPanel.removeAll();
            constructMinesweeper(difficulty.toLowerCase());
        }
    }

    /**
     * Losing Function.
     * This method contains everything required to end a game, and also give the
     *  user options afterwards.
     */
    private void losing()
    {
        InformationFrame.stopClock();
        timerStart = false;

        for(int r = 0; r < ButtonGrid.length; r++)
        {
            for(int c = 0; c < ButtonGrid[1].length; c++)
            {
                if(MainManager.getMainGrid().getMines(r, c) == 9)
                {
                    ButtonGrid[r][c].setSelected(true);
                     // prepare special icons

                    ImageIcon MineIcon;
                    MineIcon = new ImageIcon("C://Users/Joseph/Downloads/GitHub/"
                            + "Outside/2013/Minesweeper/src/Images/MineImage.png");

                    // prepare resize
                    Image MineImage = MineIcon.getImage(); // transform it

                    int maxSize = Math.max(ButtonGrid[r][c].getHeight(),
                            ButtonGrid[r][c].getWidth()) / 2;

                    Image rescaledImage;
                    ImageIcon imageIcon;

                    rescaledImage = MineImage.getScaledInstance(maxSize, maxSize,
                            Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(rescaledImage);  // transform it back

                    JLabel test1 = new JLabel(imageIcon);

                    ButtonGrid[r][c].add(test1);
                    //ButtonGrid[r][c].repaint();
                    resetFont("", r, c);
                }
            }
        }

        /*
        JOptionPane.showMessageDialog(rootPane, "You lose. Sorry."
                + "\n╔╦╦╦╦╦╦╦╦╦╦╦╦╗"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬█╬╬╬╬╬╬█╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬██████████╬╣"
                + "\n╠╬█╬╬╬╬╬╬╬╬█╬╣"
                + "\n╚╩╩╩╩╩╩╩╩╩╩╩╩╝", "☹", JOptionPane.YES_NO_CANCEL_OPTION);
                * */
        //Custom button text
        String[] options = {"Try again", "Go back to Start", "Quit"};
        int n = JOptionPane.showOptionDialog(rootPane, "You lose. Sorry."
                + "\n╔╦╦╦╦╦╦╦╦╦╦╦╦╗"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬█╬╬╬╬╬╬█╬╬╣"
                + "\n╠╬╬╬╬╬╬█╬╬╬╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬╬╬╬╬╬╬╬╬╬╬╬╣"
                + "\n╠╬██████████╬╣"
                + "\n╠╬█╬╬╬╬╬╬╬╬█╬╣"
                + "\n╚╩╩╩╩╩╩╩╩╩╩╩╩╝"
                + "\n What would you like to do?", "☹",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[2]);

        if(n == 2)
        {
            //System.out.println("n = " + n);
            System.exit(0);
        }
        else if(n == 1)
        {
            InformationFrame.dispose();
            main(null);
            this.dispose();
        }
        else if(n == 0)
        {
            String difficulty = MainManager.getMainGrid().getDifficulty();
            MainPanel.removeAll();
            constructMinesweeper(difficulty.toLowerCase());
        }

        for(int y = 0; y < ButtonGrid.length; y++)
        {
            for(int x = 0; x < ButtonGrid[1].length; x++)
            {
                ButtonGrid[y][x].setFocusable(false);
            }
        }

        System.out.println("You lose");
    }

    /**
     * Update Helper Method.
     * Creates the neighbor sub-grid.
     * Not yet fully implemented.
     * @param row center row int
     * @param col center col int
     */
    /*
    private void makeNeighborCoordinates(int row, int col)
    {
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
    }
    * */

    /**
     * Update helper method.
     * Gets the coordinates of the selected button.
     * @param evt User mouse event.
     * @return int[] of two integers, y and x, or row and col.
     */
    private int[] getButtonCoordinates(MouseEvent evt)
    {
        AbstractButton abstractButton = (AbstractButton) evt.getSource();

        int y; int x = 0;
        outerloop:
        for(y = 0; y < ButtonGrid.length; y++)
        {
            for(x = 0; x < ButtonGrid[1].length; x++)
            {
                if(abstractButton.equals(ButtonGrid[y][x]))
                {
                    break outerloop;
                    // coordinates saved
                }
            }
        }

        int[] coordinates = {y, x};
        return coordinates;
    }

    /**
     * Update helper method.
     * Resets the fonts and repaints the buttons after the updates.
     * @param displayString is a String resembling the internal contents of the
     *  button
     * @param _r row integer of the button
     * @param _c col integer of the button
     */
     private void resetFont(String displayString, int _r, int _c)
     {
         int height = ButtonGrid[_r][_c].getHeight() / 2;
         Font numberFont = new Font("sansserif", Font.BOLD, height);

         ButtonGrid[_r][_c].setFont(numberFont);

         if(displayString.equals("9"))
         {
             ButtonGrid[_r][_c].setForeground(new Color(255, 0, 0)); // red
         }
         else if(ButtonGrid[_r][_c].getIsFlagged() && MainManager.getMainGrid().getMines(_r, _c) == 9)
         {
             ButtonGrid[_r][_c].setForeground(new Color(0, 0, 0));
         }
         else
         {
             ButtonGrid[_r][_c].setForeground(new Color(0, 130, 200)); // 0,130,200 is a pretty and solid cyan blue
         }

         ButtonGrid[_r][_c].repaint();
     }

     /**
      * Manual Update method. Manually updates every button after changes.
      * Not implemented.
      */
     /*
     private void update()
     {
         buttonsSelectedCounter = 0;
         for(int r = 0; r < ButtonGrid.length; r++)
         {
             for(int c = 0; c < ButtonGrid[1].length; c++)
             {
                 if(ButtonGrid[r][c].isSelected() && MainManager.getMainGrid().getMines(r, c) != 9)
                 {
                     buttonsSelectedCounter++;
                 }
                 System.out.println("Safe Buttons: " + (safeButtonsLeft - buttonsSelectedCounter));
             }
         }

         if(buttonsSelectedCounter == safeButtonsLeft)
         {
             JOptionPane.showMessageDialog(rootPane, "You won!"
                     + "\n╔══╗░░░░╔╦╗░░╔═════╗"
                     + "\n║╚═╬════╬╣╠═╗║░▀░▀░║"
                     + "\n╠═╗║╔╗╔╗║║║╩╣║╚═══╝║"
                     + "\n╚══╩╝╚╝╚╩╩╩═╝╚═════╝", "Smileys  ☺  ☻  ت ヅ  ツ  ッ  シ Ü  ϡ  ﭢ", JOptionPane.YES_NO_CANCEL_OPTION);
         }
     }
     * */

    /**
     * Event handler method.
     * Runs constructor for Medium.
     * @param evt User Mouse release
     */
    private void MediumButtonMouseReleased(MouseEvent evt)
    {
        // TODO add your handling code here:
        MainPanel.removeAll();
        constructMinesweeper("medium");
    }

    /**
     * Event handler method.
     * Runs constructor for Hard.
     * @param evt User Mouse release
     */
    private void HardButtonMouseReleased(MouseEvent evt)
    {
        // TODO add your handling code here:
        MainPanel.removeAll();
        constructMinesweeper("hard");
    }

    /**
     * Event handler method.
     * Frame checks for user keyboard presses
     * @param evt User Keyboard Press
     */
    private void formKeyPressed(KeyEvent evt)
    {
        // TODO add your handling code here:
        checkF2Key(evt);
        //checkTestCMD(evt);
        System.out.println("Form detected a key press: " + evt.getKeyLocation() + ": " + evt.getKeyCode() + " - " + evt.getKeyChar());

    }

    /**
     * Event handler method.
     * Frame checks for user mouse release
     * Not very useful, just cool
     * @param evt User Mouse release
     */
    private void formMouseReleased(MouseEvent evt)
    {
        // TODO add your handling code here:
        //System.out.println("Mouse Click Coordinates: " + evt.getX() + ", " + evt.getY());
    }

    /**
     * Event handler method.
     * File Menu click checker. Debugger method.
     * @param evt User Mouse Click
     */
    private void FileMenuMouseClicked(MouseEvent evt)
    {
        // TODO add your handling code here:
        //System.out.println("FileMenu Clicked");
    }

    /**
     * Event handler helper-method.
     * Actionable F2-key check
     * @param evt User Keyboard Press
     */
    private void checkF2Key(KeyEvent evt)
    {
        if(evt.getKeyCode() == 113)
        {
            System.out.println(evt.getKeyCode());
            EasyButtonMouseReleased(null);
            //HardButtonMouseReleased(null);
            //FileMenuMouseClicked(null);
        }
    }

    /**
     * Event handler method.
     * Actionable F3-key check
     * Not implemented.
     * @param evt User Keyboard Press
     */
    /*
    private void checkTestCMD(KeyEvent evt)
    {
        if(evt.getKeyCode() == 114)
        {
            System.out.println(evt.getKeyCode());
            MainPanel.removeAll();
            constructMinesweeper("test");
        }
    }
    * */

    /**
     * Main method.
     * Begins the GUI, and the rest of the program.
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        //playSound();
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(mainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new mainForm().setVisible(true);
            }
        });
    }

    /**
     *  Sound playing thread.
     *  This method begins a thread which is supposed to play a nice tune from the URL.
     *  Commented as the URL finder doesn't work atm.
     */
    /*
    public static synchronized void playSound()
    {
        new Thread(new Runnable()
        {
            // The wrapper thread is unnecessary, unless it blocks on the
            // Clip finishing; see comments.
            @Override
            public void run()
            {
                try
                {
                    URL WavURL = this.getClass().getClassLoader().getResource("C:/Users/Joseph/Music/DJSmack/Made in NZ.wav");
                    //AudioInputStream inputStream = AudioSystem.getAudioInputStream("C:/Users/Joseph/Music/DJSmack/Made in NZ.wav");
                    AudioInputStream inputStream;

                    inputStream = AudioSystem.getAudioInputStream(WavURL);
                    Clip audioClip = AudioSystem.getClip();
                    audioClip.open(inputStream);
                    audioClip.start();
                }
                catch (LineUnavailableException | UnsupportedAudioFileException | IOException e)
                {
                    //System.err.println(e.getMessage());
                    System.out.println("Audio file not found.");
                }
            }
        }).start();
    }
    * */
}