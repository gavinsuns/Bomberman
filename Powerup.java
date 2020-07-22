import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Superclass of powerup which defines powerups as needing a method 
 * for when a player picks up a powerup. This method will call a mutator in the
 * bomber class.
 * 
 * @author Anson 
 * @version January 2020
 */
public abstract class Powerup extends Actor
{
    //Abstract method needed in subclasses
    public abstract void pickMeUp();
}
