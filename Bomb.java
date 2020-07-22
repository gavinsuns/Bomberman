import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bombs are objects that Bombers put down. They are able to break bricks and 
 * kill Bombers, by creating Explosion objects in all four directions. The 
 * amount of explosions created, and whether they can break more than one brick 
 * is based on the stats of the Bomber who placed the bomb. Bombs cannot go 
 * through Rocks, and Bombers are not able to walk over Bombs.
 * 
 * @author James
 * @version January 2020
 */
public class Bomb extends Actor
{
    //Variables for bomb to function
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer animate = new SimpleTimer();
    private int x;
    private int y;
    private int r;
    Bomber b;
    boolean isSpike = false;

    //For animation
    private int animSpeed = 200;
    private int index = 0;
    private GreenfootImage[] images = {new GreenfootImage("bomb0.png"),new GreenfootImage("bomb1.png"),new GreenfootImage("bomb2.png"),new GreenfootImage("bomb1.png")};

    //Sounds 
    protected GreenfootSound explosion = new GreenfootSound("explosion.mp3");
    protected GreenfootSound place = new GreenfootSound("place.wav");

    //Explosion images
    private GreenfootImage eStart = new GreenfootImage("ExplosionStart.png");
    private GreenfootImage eMidH = new GreenfootImage("ExplosionMidH.png");
    private GreenfootImage eMidV = new GreenfootImage("ExplosionMidV.png");
    private GreenfootImage eEndU = new GreenfootImage("ExplosionEndU.png");
    private GreenfootImage eEndR = new GreenfootImage("ExplosionEndR.png");
    private GreenfootImage eEndD = new GreenfootImage("ExplosionEndD.png");
    private GreenfootImage eEndL = new GreenfootImage("ExplosionEndL.png");
    private GreenfootImage eStartB = new GreenfootImage("ExplosionStartB.png");
    private GreenfootImage eMidHB = new GreenfootImage("ExplosionMidHB.png");
    private GreenfootImage eMidVB = new GreenfootImage("ExplosionMidVB.png");
    private GreenfootImage eEndUB = new GreenfootImage("ExplosionEndUB.png");
    private GreenfootImage eEndRB = new GreenfootImage("ExplosionEndRB.png");
    private GreenfootImage eEndDB = new GreenfootImage("ExplosionEndDB.png");
    private GreenfootImage eEndLB = new GreenfootImage("ExplosionEndLB.png");
    /**
     * Constructor for the Bomb. Sets up instance variables from the parameters
     * passed from the Bomber that placed the bomb.
     * 
     * @param xPos The x position of the Bomb on the game array
     * @param yPos The y position of the Bomb on the game array
     * @param radius The amount of explosions created in each direction
     * @param spike Whether or not the bomb is a spike bomb
     * @param bomber The bomber that placed the bomb down
     */
    public Bomb(int xPos, int yPos, int radius, boolean spike, Bomber bomber)
    {
        //plays sound for when the bomb is placed
        place.setVolume(90);
        place.play();
        r = radius;
        x = xPos;
        y = yPos;
        b = bomber;
        isSpike = spike;
        size();
        //begins the timer for the explosion countdown
        timer.mark();
    }

    /**
     * Sizes the bomb to the correct size
     */
    public void size()
    {
        GreenfootImage image = getImage();
        image.scale(40,40);
        setImage(image);
    }

    /**
     * Act method - code to be ran every act of the program
     */
    public void act() 
    {
        //Cycles through the images for animation
        if(animate.millisElapsed() > animSpeed)
        {
            animate.mark();
            setImage(images[index]);
            index++;
            if (index >= 4){
                index = 0;
            }
            size();
        }
        //Checks if the bomb is supposed to explode
        if(timer.millisElapsed()>3000)
        {
            //Plays explosion sound
            explosion.setVolume(50);
            explosion.play();
            MyWorld world = (MyWorld) getWorld();
            int[][] temp = world.getArray();
            //creates the middle explosion right where the bomb was
            Explosion middle = new Explosion(x,y); 
            middle.setImage(eStart);
            //checks if the blue image should be set instead.
            if(isSpike)
            {
                middle.setImage(eStartB);
            }
            world.addObject(middle, getX() , getY());
            //Loops below are for creating and adding the explosions in the
            //4 directions.

            //up direction, this one is commented, others are similar
            for(int i = 1; i < r+1; i++)
            {
                //to avoid array out of bounds
                if(y-i < 0)
                {
                    //doing i += r breaks it out of the main loop
                    i+=r;
                }
                //makes sure it is not about to hit a rock
                else if(temp[x][y-i] != 2)
                {
                    Explosion explosion;
                    //checks if this is the last explosion on this side
                    if(i==r)
                    {
                        explosion = new Explosion(x,y-i); 
                        explosion.setImage(eEndU);
                        if(isSpike)
                        {
                            explosion.setImage(eEndUB);
                        }
                        world.addObject(explosion, getX() , getY() - 80*i);
                    }
                    else
                    {
                        explosion = new Explosion(x,y-i); 
                        explosion.setImage(eMidV);
                        if(isSpike)
                        {
                            explosion.setImage(eMidVB);
                        }
                        world.addObject(explosion, getX() , getY() - 80*i);
                    }
                    //checks if the explosion has broken a block, or if it's
                    //not a spike bomb.
                    if(explosion.hasBroken() && !isSpike)
                    {
                        i+=r;
                    }
                }
                else
                {
                    i+=r;
                }
            }
            for(int i = 1; i < r+1; i++)
            {
                if(y+i > 8)
                {
                    i+=r;
                }

                else if(temp[x][y+i] != 2)
                {
                    Explosion explosion;
                    if(i==r)
                    {
                        explosion = new Explosion(x,y+i); 
                        explosion.setImage(eEndD);
                        if(isSpike)
                        {
                            explosion.setImage(eEndDB);
                        }
                        world.addObject(explosion, getX() , getY() + 80*i);
                    }
                    else
                    {
                        explosion = new Explosion(x,y+i); 
                        explosion.setImage(eMidV);
                        if(isSpike)
                        {
                            explosion.setImage(eMidVB);
                        }
                        world.addObject(explosion, getX() , getY() + 80*i);
                    }
                    if(explosion.hasBroken() && isSpike == false)
                    {
                        i+=r;
                    }
                }
                else
                {
                    i+=r;
                }
            }
            for(int i = 1; i < r+1; i++)
            {
                if(x-i < 0)
                {
                    i+=r;
                }
                else if(temp[x-i][y] != 2)
                {
                    Explosion explosion;
                    if(i==r)
                    {
                        explosion = new Explosion(x-i,y); 
                        explosion.setImage(eEndL);
                        if(isSpike)
                        {
                            explosion.setImage(eEndLB);
                        }
                        world.addObject(explosion, getX() - 80*i , getY());
                    }
                    else
                    {
                        explosion = new Explosion(x-i,y); 
                        explosion.setImage(eMidH);
                        if(isSpike)
                        {
                            explosion.setImage(eMidHB);
                        }
                        world.addObject(explosion, getX()  - 80*i, getY());
                    }
                    if(explosion.hasBroken() && isSpike == false)
                    {
                        i+=r;
                    }
                }
                else
                {
                    i+=r;
                }
            }
            for(int i = 1; i < r+1; i++)
            {
                if(x+i > 12)
                {
                    i+=r;
                }
                else if(temp[x+i][y] != 2)
                {
                    Explosion explosion;
                    if(i==r)
                    {
                        explosion = new Explosion(x+i,y);
                        explosion.setImage(eEndR);
                        if(isSpike)
                        {
                            explosion.setImage(eEndRB);
                        }
                        world.addObject(explosion, getX() + 80*i , getY());
                    }
                    else
                    {
                        explosion = new Explosion(x+i,y); 
                        explosion.setImage(eMidH);
                        if(isSpike)
                        {
                            explosion.setImage(eMidHB);
                        }
                        world.addObject(explosion, getX()  + 80*i, getY());
                    }
                    if(explosion.hasBroken() && isSpike == false)
                    {
                        i+=r;
                    }
                }
                else
                {
                    i+=r;
                }
            }
            //"returns" the bomb to the bomber
            b.increaseBombs();
            world.removeObject(this);
        }
    }    
}




