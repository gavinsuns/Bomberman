import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Superclass of Bombermen. Includes variables and abstract methods.
 * Bombermen drop bombs to defeat enemy players. They can pick up power ups
 * to enhance their abilities.
 * 
 * @author Edmund, James, Anson, Gavin
 * @version January 2020
 */
public abstract class Bomber extends Actor
{
    //Variables for Bombers to function
    protected int originalSpeed = 6;
    protected int xPos;
    protected int yPos;
    protected boolean dead = false;
    
    //For powerups
    protected int bombCount = 1;
    protected int speed = 6;
    protected int health = 1;
    protected int r = 1;
    protected boolean isSpike = false;
    SimpleTimer bombCD = new SimpleTimer();
    SimpleTimer skullT = new SimpleTimer();
    Powerup p;

    //sounds
    GreenfootSound oof = new GreenfootSound("oof.wav");
    /**
     * Act - do whatever the Bomber wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateLocation();
        control();
        if(skullT.millisElapsed()>5000)
        {
            speed = originalSpeed;
        }
    }    
    
    //Abstract methods that subclasses must have
    public abstract void control();

    public abstract void updateLocation();
    
    public abstract void Size();

    /**
     * Checks if there is a bomb at a given location
     * 
     * @param x The x position to check
     * @param y The y position to check
     */
    public boolean canMove(int x, int y)
    {
        ArrayList<Bomb> b = (ArrayList<Bomb>)getWorld().getObjectsAt(x, y, Bomb.class);
        if(b.size() != 0)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Removes bomber once health reaches zero
     */
    public void death()
    {
        health--;
        oof.setVolume(70);
        oof.play();
        if(health==0){
            dead = true;
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Checks if player is dead
     * 
     * @return boolean If player is dead returns true
     */
    public boolean checkDead(){
        return dead;
    }

    /**
     * Increase the radius of the bomb explosion
     */
    public void increaseFire()
    {
        r++;
    }
    
    /**
     * Increases the speed of the player
     */
    public void increaseSpeed()
    {
        speed++;
        originalSpeed++;
    }
    
    /**
     * Increase the number bombs that a player can hold
     */
    public void increaseBombs()
    {
        bombCount++;
    }
    
    /**
     * Increases the amount of times a player can be hit by a explosion
     */
    public void increaseHealth()
    {
        health++;
    }

    /**
     * Bombs become become spike bombs 
     * Allows player bombs to break through multiple blocks
     */
    public void spikeBomb()
    {
        isSpike = true;
    }

    /**
     * Decreases the players speed for a set time
     */
    public void skull()
    {
        originalSpeed = speed;
        speed = 1;
        skullT.mark();
    }
    
}



