import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerup Object allows the bomber to pierce through multiple blocks.
 * 
 * @author Anson 
 * @version January 2020
 */
public class Spike extends Powerup
{
    private Bomber b;
    /**
     * Enables bomber to pierce through blocks.
     */
    public void pickMeUp()
    {
        b = (Bomber)getOneIntersectingObject(Bomber.class);
        b.spikeBomb(); //setter in bomber
    }
}
