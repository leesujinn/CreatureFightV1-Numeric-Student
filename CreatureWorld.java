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
 * Lab#1, Program #1
 * Date Last Modified: 09/29/2017
 * 
 */
public class CreatureWorld extends World
{
    private Creature playerOneCreature;
    private Creature playerTwoCreature;
    
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
        
    }
        
    private void prepareCreatures()
    {
        addObject(playerOneCreature, playerOneCreature.getImage().getWidth()/2, getHeight() - playerOneCreature.getImage().getHeight()/2);
        addObject( new Button(Color.RED, 50), 265, 685 );    
     
        addObject(playerTwoCreature, getWidth() - playerTwoCreature.getImage().getWidth()/2, playerTwoCreature.getImage().getHeight()/2);
        addObject( new Button(Color.RED, 50), 540, 85 );
    }
    
    public Creature getPlayerOne()
    {
        return playerOneCreature;
    }
    
    public Creature getPlayerTwo()
    {
        return playerTwoCreature;
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
        
        if( playerTwoCreature.getHealthBar().getCurrent() <= 0 )
        {
            showText("player one has won!", getWidth()/2, getHeight()/2 + 26);
            Greenfoot.stop();
           
            removeObjects(allObjects);
        }
        
        if( playerOneCreature.getHealthBar().getCurrent() <= 0 )
        {
            showText("player two has won!", getWidth()/2, getHeight()/2 + 26);
            Greenfoot.stop();
           
            removeObjects(allObjects);
        }        
    }
}

