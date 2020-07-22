import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PlayerFour class includes variables specific to this bomberman, such as
 * character image.
 * 
 * @author Edmund, James, Anson, Gavin
 * @version January 2020
 */
public class PlayerFour extends Bomber
{
    //Variables for animation
    private int dir = 3;
    private int timer = 10;
    private int animSpeed = 40;
    SimpleTimer bombCD = new SimpleTimer();
    SimpleTimer animate = new SimpleTimer();
    private int index = 0;
    private GreenfootImage[] imgUp;
    private GreenfootImage[] imgDown;
    private GreenfootImage[] imgRight;
    private GreenfootImage[] imgLeft;  
    
    //For powerup detection
    Powerup p;
    
    /**
     * Constructor for PlayerFour. 
     * Prepares animation images
     */
    public PlayerFour (){
        imgUp = new GreenfootImage[4];
        for (int i = 0; i < 4; i++){
            imgUp[i] = new GreenfootImage ("trainerUp" + i + ".png");
        }
        imgDown = new GreenfootImage[4];
        for (int i = 0; i < 4; i++){
            imgDown[i] = new GreenfootImage ("trainerDown" + i + ".png");
        }
        imgRight = new GreenfootImage[4];
        for (int i = 0; i < 4; i++){
            imgRight[i] = new GreenfootImage ("trainerRight" + i + ".png");
        }
        imgLeft = new GreenfootImage[4];
        for (int i = 0; i < 4; i++){
            imgLeft[i] = new GreenfootImage ("trainerLeft" + i + ".png");
        }
    }    

    /**
     * Act - do whatever the PlayerOne wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    
    /**
     * Sizes chraracter to the acceptable size
     */
    public void Size()
    {
        GreenfootImage image = getImage();
        image.scale(45,45);
        setImage(image);
    }

    /**
     * Used to control player movement and bomb placement
     */
    public void control()
    {
        String key = Greenfoot.getKey();
        int x = getX(), y= getY();
        //Controls for movement
        if(Greenfoot.isKeyDown("i")&&animate.millisElapsed() > animSpeed)
        {
            dir = 1;
            animate.mark();
            if(canMove(getX(),getY()-50))
            {
                setImage(imgUp[index]);
                index++;
                if (index >= 4){
                    index = 0;
                }
                Size();
                setLocation(this.getX(), this.getY() - speed);
            }
        }
        if(Greenfoot.isKeyDown("j")&&animate.millisElapsed() > animSpeed)
        {   
            dir = 2;
            animate.mark();
            if(canMove(getX() - 50, getY()))
            {
                setImage(imgLeft[index]);
                index++;
                if (index >= 4){
                    index = 0;
                }
                Size();
                setLocation(this.getX() - speed, this.getY());
            }
        }
        if(Greenfoot.isKeyDown("k")&&animate.millisElapsed() > animSpeed)
        {      
            dir = 3;
            animate.mark();
            if(canMove(getX(), getY() + 50))
            {
                setImage(imgDown[index]);
                index++;
                if (index >= 4){
                    index = 0;
                }
                Size();
                setLocation(this.getX(), this.getY() + speed);           
            }
        }
        if(Greenfoot.isKeyDown("l")&&animate.millisElapsed() > animSpeed)
        {   
            dir = 4;
            animate.mark();
            if(canMove(getX() + 50, getY()))
            {
                setImage(imgRight[index]);
                index++;
                if (index >= 4){
                    index = 0;
                }
                Size();
                setLocation(this.getX() + speed, this.getY());             
            }
        }
        if(Greenfoot.isKeyDown("i") == false && Greenfoot.isKeyDown("j") == false && Greenfoot.isKeyDown("k") == false && Greenfoot.isKeyDown("l") == false){
            if(dir == 1){
                setImage(imgUp[0]); 
            }
            if(dir == 2){
                setImage(imgLeft[0]); 
            }
            if(dir == 3){
                setImage(imgDown[0]); 
            }
            if(dir == 4){
                setImage(imgRight[0]); 
            }              
            Size();     
        }
        //Places bomb at location of player
        if(Greenfoot.isKeyDown("o")&&bombCD.millisElapsed() > 500&&bombCount>0)
        {
            bombCD.mark();
            bombCount--;
            Bomb bomb = new Bomb(xPos,yPos,r,isSpike,this);
            getWorld().addObject(bomb,80*xPos+40, 80*yPos+40);
        }
        //Prevents player from moving out of the world border and going through blocks and rocks
        if (isTouching(Rock.class) || isTouching(Brick.class)|| getX() < 20 || getX() > 1020 || getY() < 20 || getY() > 700)
        {
            setLocation(x, y);
        }
        //Determines if player is touching a powerup. If true, it removes the power up.
        if(isTouching(Powerup.class))
        {
            p = (Powerup)getOneIntersectingObject(Powerup.class);
            p.pickMeUp();
            getWorld().removeObject(p);
        }
    }
    //Finds the location of the player in relation to the 2DArray size
    public void updateLocation()
    {
        xPos = (getX())/80;
        yPos = (getY())/80;
    }
}
