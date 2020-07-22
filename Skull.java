import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerup Object that debuffs a player to move at minimum speed
 * when picked up.
 * 
 * @author Anson 
 * @version January 2020
 */
public class Skull extends Powerup
{
    private Bomber b;
    /**
     * Decreases the bombers speed.
     */
    public void pickMeUp()
    {
        b = (Bomber)getOneIntersectingObject(Bomber.class);
        b.skull(); //setter in bomber
    }  
}
