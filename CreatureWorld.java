import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Write a description of class MyWorld here.
 * 
 * Name: SuJin Lee
 * Course: CS20S
 * Teacher: Mr.Hardman
 * Lab#5&6, Program #1
 * Date Last Modified: 01/18/2018
 * 
 */
public class CreatureWorld extends World
{
    private String playerOneCreature;
    private String playerTwoCreature;
    private boolean playerOneTurn;
    private String playerOneName;
    private String playerTwoName;
    private Menu oneFightMenu;
    private Menu oneSwitchMenu;
    private Menu twoFightMenu;
    private Menu twoSwitchMenu;
    private boolean start;
    private boolean playerOneMenusAdded = false;
    private boolean playerTwoMenusAdded = false;
    private Creature[] playerOneCreatures;
    private Creature[] playerTwoCreatures;
    
    /**
     * Default constructor for objects of class MyWorld.
     * CreatureWorld will be base the CreatureFight
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public CreatureWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 400, 1); 
        //playerOneCreature = new Charmander(this);
        //playerTwoCreature = new Pikachu(this);
        
        playerOneCreatures = new Creature[]{ new Charmander(this), new Golem(this), new Ivysaur(this)};
        playerTwoCreatures = new Creature[]{ new Pikachu(this), new Lapras(this), new Pidgeot(this)};
       
        prepareCreatures();
        Greenfoot.start();

        start = true;
        playerOneCreature = "Charmander";
        playerTwoCreature = "Pikachu";
    }
    
    /**
     * prepareCreature prepares image of the CreatureWorld
     *
     * @param There are no parameters
     * @return Nothing is returned
     */
     private void prepareCreatures()
    {
        
        for( int i = 0 ; i < playerOneCreatures.length ; i++ )
        {
            if( i == 0)
            {
                //Add a specific object at an x location and a y location
                addObject( playerOneCreatures[i], playerOneCreatures[i].getImage().getWidth()/2, getHeight() - playerOneCreatures[i].getImage().getHeight()/2);
            }
            else
            {
                playerOneCreatures[i].getImage().setTransparency(0);
                addObject( playerOneCreatures[i], 0, getHeight() - playerOneCreatures[i].getImage().getHeight()/2 );
            }
        }
        
        for( int j = 0 ; j < playerTwoCreatures.length ; j++ )
        {
            if( j == 0)
            {
                //Add a specific object at an x location and a y location
                addObject( playerTwoCreatures[j], getWidth() - playerTwoCreatures[j].getImage().getWidth()/2, playerTwoCreatures[j].getImage().getHeight()/2);
            }
            else
            {
                playerTwoCreatures[j].getImage().setTransparency(0);
                addObject( playerTwoCreatures[j], getWidth(), playerTwoCreatures[j].getImage().getHeight()/2);
            }
        }
        
    }
    
    /**
     * getPlayerOne tell characters on palyer one creature
     * 
     * @param There are no parameters
     * @return currentPlayerOne
     */
    public Creature getPlayerOne()
    {
        Creature currentPlayerOne;
        
        if( playerOneCreature.equalsIgnoreCase("Charmander" ) )
        {
            currentPlayerOne = playerOneCreatures[0];
        }
        else if( playerOneCreature.equalsIgnoreCase ("Golem") )
        {
            currentPlayerOne = playerOneCreatures[1];
        }
        else 
        {
            currentPlayerOne = playerOneCreatures[2];
        }
        
        return currentPlayerOne;
    }
    
    /**
     * getPlayerTwo tell characters on palyer two creature
     * 
     * @param There are no parameters
     * @return currentPlayerTwo
     */
    public Creature getPlayerTwo()
    {
        Creature currentPlayerTwo;
        
        if( playerTwoCreature.equalsIgnoreCase("Pikachu" ) )
        {
            currentPlayerTwo = playerTwoCreatures[0];
        }
        else if( playerTwoCreature.equalsIgnoreCase ("Lapras") )
        {
            currentPlayerTwo = playerTwoCreatures[1];
        }
        else 
        {
            currentPlayerTwo = playerTwoCreatures[2];
        }
        
        return currentPlayerTwo;
    }
    
    /**
     * changePlayerOne is chaging something about the PlayerOne. (Character, attack menu etc)
     * 
     * @param String creature will arrange the menu items
     * @return Nothing is returned
     */
    public void changePlayerOne( String creature )
    {
        playerOneCreature = creature;
        
        removeObject(oneFightMenu);
        removeObject(oneSwitchMenu);
        
        playerOneMenusAdded = false;
    }
    
    /**
     * changePlayerTwo is chaging something about the PlayerTwo. (Character, attack menu etc)
     * 
     * @param String creature will arrange the menu items
     * @return Nothing is returned
     */
    public void changePlayerTwo( String creature )
    {
        playerTwoCreature = creature;
        
        removeObject(twoFightMenu);
        removeObject(twoSwitchMenu);
        
        playerTwoMenusAdded = false;
    }
    
    /**
     * getNewOneCreature get another playerOneCreature
     * 
     * @param index is variable
     * @return palayerOneCreature and index
     */
    public Creature getNewOneCreature( int index )
    {
        return playerOneCreatures[index];
    }
    
       
    /**
     * getNewTwoCreature get another playerTwoCreature
     * 
     * @param index is variable
     * @return palayerTwoCreature and index
     */
    public Creature getNewTwoCreature( int index )
    {
        return playerTwoCreatures[index];
    }
    
    /**
     * isPlayerOneTurn returns the PlayerOneTurn for use in other sections of code or for the user's information
     * 
     * @param There are no parameters
     * @return a boolean representing the playerOnrTurn
     */
    public boolean isPlayerOneTurn()
    {
        return playerOneTurn;
    }
    
    /**
     * changeTurn will change each turn
     * 
     * @param boolean will say it is true or false
     * @return Nothing is returned
     */
    public void changeTurn( boolean isPlayerOne)
    {
        playerOneTurn = isPlayerOne;
    }
    
    /**
     * act will complete actions that the CreatureWorld object should
     * accomplish while the scenario is running
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {
        List allObjects = getObjects(null);  
        boolean playerOneLose = true;
        boolean playerTwoLose = true;
        
        
        
        if( start == true )
        {
            playerOneName = JOptionPane.showInputDialog( "Player One, please enter your name:", null );
            playerTwoName = JOptionPane.showInputDialog( "Player Two, please enter your name:", null );
        
            //turnNumber = 1;
            start = false;
            playerOneTurn = true;
        }
        else
        {
            if( playerOneTurn == true )
            {
                showText( playerOneName + " , Your turn", getWidth()/2, getHeight()/2);
                showText( "", getWidth()/2, getHeight()/2 + 26);
            }
            else
            {
                showText( playerTwoName + " , Your turn", getWidth()/2, getHeight()/2);
                showText( "", getWidth()/2, getHeight()/2 + 26);
            }
        }
        
        if( playerOneMenusAdded == false)
        {
            
            if( playerOneCreature.equalsIgnoreCase( "Charmander" ))
            {
                oneFightMenu = new Menu( " Fight ", " Scratch \n Flamethrower ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                oneSwitchMenu = new Menu( " Switch ", " Golem \n Ivysaur ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else if( playerOneCreature.equalsIgnoreCase( "Golem" ))
            {
                oneFightMenu = new Menu( " Fight ", " Tackel \n Earthquake ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                oneSwitchMenu = new Menu( " Switch ", " Charmander \n Ivysaur ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else
            {
                oneFightMenu = new Menu( " Fight ", " Tackel \n Razor Leaf ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                oneSwitchMenu = new Menu( " Switch ", " Chatmander \n Golem ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            
            addObject(oneFightMenu, 177, getHeight() - 100 );
            addObject(oneSwitchMenu, 241, getHeight() - 100);
            playerOneMenusAdded = true;
        }
        
        if( playerTwoMenusAdded == false )
        {
            if( playerTwoCreature.equalsIgnoreCase( "Pikachu" ))
            {
                twoFightMenu = new Menu( " Fight ", " Tackle \n Thunderbolt ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                twoSwitchMenu = new Menu( " Switch ", " Lapras \n Pidgeot ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else if( playerTwoCreature.equalsIgnoreCase( "Lapras" ))
            {
                twoFightMenu = new Menu( " Fight ", " Tackle \n Hydro Pump ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                twoSwitchMenu = new Menu( " Switch ", " Pikachu \n Pidgeot ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else
            {
                twoFightMenu = new Menu( " Fight ", " Tackle \n Wing Attack ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                twoSwitchMenu = new Menu( " Switch ", " Pikachu \n Lapras ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            
            addObject(twoFightMenu, 135, 75);
            addObject(twoSwitchMenu, 199, 75);
            playerTwoMenusAdded = true;
        }
        
        for( int i = 0; playerOneLose == true && i < playerOneCreatures.length; i++ )
        {
            if(playerOneCreatures[i].getHealthBar().getCurrent() > 0)
            {
                playerOneLose = false;
            }
            
        }
        
        for( int j = 0; playerTwoLose == true && j < playerTwoCreatures.length; j++ )
        {
            if(playerTwoCreatures[j].getHealthBar().getCurrent() > 0)
            {
                playerTwoLose = false;
            }
        }
                  
        if( playerOneLose == true )
        {
            showText( "", getWidth()/2, getHeight()/2);
            showText("Player One has won!!", getWidth()/2, getHeight()/2 + 26);
            Greenfoot.stop();
           
            removeObjects(allObjects);
        }
        
        if( playerTwoLose == true )
        {
            showText( "", getWidth()/2, getHeight()/2);
            showText("Player Two has won!!", getWidth()/2, getHeight()/2 + 26);
            Greenfoot.stop();
           
            removeObjects(allObjects);
        }    
    }
}

