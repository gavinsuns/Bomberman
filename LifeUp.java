import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerup Object that increases the times a player be hit by a bomb when
 * picked up.
 * 
 * @author Anson 
 * @version January 2020 
 */
public class LifeUp extends Powerup
{
    private Bomber b;
    
    /**
     * Increases the bombers health.
     */
    public void pickMeUp()
    {
        b = (Bomber)getOneIntersectingObject(Bomber.class);
        b.increaseHealth(); //setter in bomber
    }    
}
