import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Explosions are objects that spawn from Bombs, and they kill Bombers and break
 * Bricks when coming in contact with them. When a brick is broken by an 
 * Explosion, there is a chance that a Powerup will spawn. Explosions only 
 * stay on the screen for a short period of time.
 * 
 * @author James 
 * @version January 2020
 */
public class Explosion extends Actor
{
    //Variables for Explosion to function 
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer damagetimer = new SimpleTimer();
    Brick brick;
    int x;
    int y;
    private int random = 0;
    boolean broken = false;
    boolean lostHealth = false;
    Bomber b;
    /** 
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //checks if it has hit any Bombers
        checkHit();
        //checks to see if it is time to remove the Explosion
        if(timer.millisElapsed()>400)
        {
            //if it has broken a block, chance to spawn powerup
            if(broken){
                MyWorld world = (MyWorld) getWorld();
                world.setArray(x,y,0);
                random = Greenfoot.getRandomNumber(2);
                if(random == 0)
                {
                    Powerup p = world.nextPowerup();
                    world.addObject(p, this.getX(), this.getY());
                }
            } 
            getWorld().removeObject(this);
        }
    }    

    /**
     * Constructor for the Explosion, setting up the instance variables
     * 
     * @param xPos the x Position of the Explosion on the game array
     * @param yPos the y Position of the Explosion on the game array
     */
    public Explosion(int xPos, int yPos)
    {
        x = xPos;
        y = yPos;
    }

    /**
     * Code that is run after the Explosion has been added to the world
     */
    public void addedToWorld (World w){
        MyWorld world = (MyWorld) w;
        timer.mark();
        int[][] temp = world.getArray();
        //checks to see if the Explosion is touching a brick, and then breaks it.
        if(temp[x][y] == 1)
        {
            brick = (Brick)getOneObjectAtOffset(0,0,Brick.class);
            if (brick != null){
                brick.death(x,y); 
                broken = true;
            }
        }
    }

    /**
     * Checks to see if the Explosion has broken a Brick
     * 
     * @return boolean Whether or not the Explosion has broken a Brick
     */
    public boolean hasBroken()
    {
        return broken;
    }

    /**
     * Checks to see if the Explosion has hit a Bomber, and then removes a life from that bomber.
     */
    public void checkHit()
    {
        //Checks to see if the Explosion has hit a Bomber, and then removes a life from that bomber.
        if(isTouching(Bomber.class))
        {
            b = (Bomber)getOneIntersectingObject(Bomber.class);
            if(lostHealth == false){
                b.death();
                lostHealth = true;
                damagetimer.mark();
            }
        }    
        //prevents a Bomber from taking multiple hits from a singleexplosion. 
        if(damagetimer.millisElapsed()>550)
        {
            lostHealth = false;
        }
    }    
}


