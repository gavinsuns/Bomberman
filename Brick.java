
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Destroyable brick class.
 * 
 * @author Anson 
 * @version January 2020
 */
public class Brick extends Actor
{
    private int random = 0;
    private SimpleTimer t = new SimpleTimer();
    /**
     * Removes the brick at a specific location.
     * 
     * @param x Integer x location of brick in 2d array
     * @param y Integer y location of brick in 2d array
     */
    public void death(int x, int y)
    {
        getWorld().removeObject(this);
    }

}


