import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Powerup Object that increases the number of bombs a player can drop at a
 * time when picked up.
 * 
 * @author Anson 
 * @version January 2020
 */
public class BombUp extends Powerup
{
    private Bomber b;
    /**
     * Increases the amount of bombers a bomber can drop.
     */
    public void pickMeUp()
    {
        b = (Bomber)getOneIntersectingObject(Bomber.class);
        b.increaseBombs(); //setter in bomber
    }
}
