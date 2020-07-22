import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Titlescreen. Includes buttons to select the number of players
 * 
 * @author Edmund, James, Anson
 * @version January 2020
 */
public class TitleScreen extends World
{
    GreenfootSound backgroundMusic = new GreenfootSound("start.mp3");
    Button button2P = new Button(2,1);
    Button button3P = new Button(3,1);
    Button button4P = new Button(4,1);
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1040, 720, 1); 
        backgroundMusic.setVolume(20);
        backgroundMusic.playLoop();
        button2P.setImage("2PButton.png");
        button3P.setImage("3PButton.png");
        button4P.setImage("4PButton.png");
        addObject(button2P, 225, 500); 
        addObject(button3P, 520, 500);
        addObject(button4P, 815, 500);
    }
    public void stopSound()
    {
        backgroundMusic.stop();
    }
}
