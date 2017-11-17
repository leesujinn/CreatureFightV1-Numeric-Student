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
 * Lab#3, Program #1
 * Date Last Modified: 11/17/2017
 * 
 */
public class CreatureWorld extends World
{
    private Creature playerOneCreature;
    private Creature playerTwoCreature;
    private boolean playerOneTurn;
    private String playerOneName;
    private String playerTwoName;
    private Menu oneFightMenu;
    private Menu oneSwitchMenu;
    private Menu twoFightMenu;
    private Menu twoSwitchMenu;
    private boolean start;
    private boolean playerOneMenusAdded;
    private boolean playerTwoMenusAdded;
    //private boolean playerTurn;
    
    /**
     * Default constructor for objects of class MyWorld.
     * 
     * @param There are no parameters
     * @return an object of class MyWorld
     */
    public CreatureWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        playerOneCreature = new Charmander(this);
        playerTwoCreature = new Pikachu(this);
        
        prepareCreatures();
        Greenfoot.start();
        

        start = true;
        //Greenfoot.start();
    }
    
     private void prepareCreatures()
    {
        addObject(playerOneCreature, playerOneCreature.getImage().getWidth()/2, getHeight() - playerOneCreature.getImage().getHeight()/2);
        //addObject( new Button(Color.RED, 50), 265, 685 );    
     
        addObject(playerTwoCreature, getWidth() - playerTwoCreature.getImage().getWidth()/2, playerTwoCreature.getImage().getHeight()/2);
        //addObject( new Button(Color.RED, 50), 540, 85 );
    }
    
    public Creature getPlayerOne()
    {
        return playerOneCreature;
    }
    
    public Creature getPlayerTwo()
    {
        return playerTwoCreature;
    }
    
    public boolean isPlayerOneTurn()
    {
        return playerOneTurn;
    }
    
    public void changeTurn( boolean isPlayerOne)
    {
        playerOneTurn = true;
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
        
        
        if( start == true )
        {
            playerOneName = JOptionPane.showInputDialog( "Player One, please enter your name:", null );
            playerTwoName = JOptionPane.showInputDialog( "Player Two, please enter your name:", null );
        
            //turnNumber = 1;
            start = true;
            start = false;
            playerOneTurn = true;
        }
        else
        {
            showText( playerOneName + " , Your turn", getWidth()/2, getHeight()/2);
            showText( "", getWidth()/2, getHeight()/2 + 26);
        
            showText( playerTwoName + " , Your turn", getWidth()/2, getHeight()/2);
            showText( "", getWidth()/2, getHeight()/2 + 26);
        }
       

        
        if( playerOneMenusAdded == false)
        {
            oneFightMenu = new Menu( "Fight", "Scratch \n Flamethrower", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands() );
            oneSwitchMenu = new Menu( "Switch", "Golem \n Ivysaur", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands() );
            addObject( oneFightMenu, 173, getHeight() - 100 );
            addObject( oneSwitchMenu, 241, getHeight() - 100 );
        }
        
        if( playerTwoMenusAdded == false )
        {
            twoFightMenu = new Menu( "Fight", "Tackle \n Thunderbolt ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands() );
            twoSwitchMenu = new Menu( "Switch", "Lapras \n Pidgeot", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands() );
            addObject( twoFightMenu, 431, 75 );
            addObject( twoSwitchMenu, 499, 75 );
        }
        
        
        
        if( playerTwoCreature.getHealthBar().getCurrent() <= 0 )
        {
            showText( "", getWidth()/2, getHeight()/2);
            showText("Player One has won!!", getWidth()/2, getHeight()/2 + 26);
            Greenfoot.stop();
           
            removeObjects(allObjects);
        }
        
        if( playerOneCreature.getHealthBar().getCurrent() <= 0 )
        {
            showText( "", getWidth()/2, getHeight()/2);
            showText("Player Two has won!!", getWidth()/2, getHeight()/2 + 26);
            Greenfoot.stop();
           
            removeObjects(allObjects);
        }      
    }
}

