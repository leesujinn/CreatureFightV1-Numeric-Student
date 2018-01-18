/**
 * Write a description of class SwithchCommands here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SwitchCommands implements MenuCommands
{
    public void execute( int idx, Creature c )
    {
        c.switchCreature(idx);
    }
}
