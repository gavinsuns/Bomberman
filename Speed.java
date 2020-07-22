import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerup Object that increases the movement speed of the player
 * when picked up.
 * 
 * @author Anson 
 * @version January 2020
 */
public class Speed extends Powerup
{
    private Bomber b;
    /**
     * Increases the bombers speed.
     */
    public void pickMeUp()
    {
        b = (Bomber)getOneIntersectingObject(Bomber.class);
        b.increaseSpeed(); //setter in bomber
    }
}
