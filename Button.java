import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A button class, used if mouse clicks need to be read from the user, 
 * 
 * @author James, Edmund
 * @version January 2020
 */
public class Button extends Actor
{
    int players;
    int type;
    
    //players is the number of players to be spawned in the next game
    //type is whether the button is on the title screen (1) or the end screen (2)
    public Button(int p, int t)
    {
        players = p;
        type = t;
        GreenfootImage image = new GreenfootImage("block.PNG");
        image.scale(180, 35);
        image.setTransparency(0);
        this.setImage(image);
    }

    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Create the game world when user clicks on the screen
        if(players == 0 && Greenfoot.mouseClicked(this))
        {
            EndScreen world = (EndScreen)getWorld();
            world.stopSound();
            Greenfoot.setWorld(new TitleScreen());
        }
        else if(Greenfoot.mouseClicked(this) && type == 2) {
            EndScreen world = (EndScreen)getWorld();
            world.stopSound();
            Greenfoot.setWorld(new MyWorld(players));
        }
        else if(Greenfoot.mouseClicked(this) && type ==1) {
            TitleScreen world = (TitleScreen)getWorld();
            world.stopSound();
            Greenfoot.setWorld(new MyWorld(players));
        }
    }   
}
