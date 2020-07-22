import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerup Object that increases the power of bombs that a player can drop 
 * when picked up.
 * 
 *@author Anson 
 * @version January 2020
 */
public class Fire extends Powerup
{
    private Bomber b;
    /**
     * Increases the bombers firepower.
     */
    public void pickMeUp()
    {
        b = (Bomber)getOneIntersectingObject(Bomber.class);
        b.increaseFire(); //setter in bomber
    }
}
