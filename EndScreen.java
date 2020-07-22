import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays different end screen depending on victor. Options to restart or 
 * go back to title screen. Credits to Super Smash Bros Ultimate and superkoopa21
 * for images.
 * 
 * @Author Edmund, Anson
 * @Version January 2020
 */
public class EndScreen extends World
{
    private Button restartbutton;    //declaring buttons
    private Button titlescreenbutton;
    private int winningPlayer;  //player who won last game
    private int numberPlayer;   //number of players last game
    GreenfootSound backgroundMusic = new GreenfootSound("end.mp3");
    /**
     * Constructor for the end screen and displays information regarding score
     * aswell as allowing for options to restart the game or keep playing.
     * @param winner Winner of the last match.
     * @param player1 Score of player one(Bomberman).
     * @param player2 Score of player two(Sans).  
     * @param player3 Score of player three(Link).
     * @param player4 Score of player four(Pokemon trainer).
     * @param numplayer Number of players last game.
     */
    public EndScreen(int winner,int player1, int player2,int player3,int player4,int numplayer)
    {    
        super(1040, 720, 1);
        backgroundMusic.setVolume(20);
        backgroundMusic.playLoop();
        winningPlayer = winner;
        numberPlayer = numplayer;
        if(winner ==1){
            setBackground("bombermanvictory.png");
            text(player1, player2, player3, player4);
        }
        if(winner ==2){
            setBackground("sansvictoryscreen.png");
            text(player1, player2, player3, player4);
        }
        if(winner ==3){
            setBackground("linkvictoryscreen.png");
            text(player1, player2, player3, player4);
        }
        if(winner ==4){
            setBackground("pkmtrainerwin.png");
            text(player1, player2, player3, player4);
        }
        restartbutton = new Button(numplayer,2);
        titlescreenbutton = new Button(0,2);
        addObject(restartbutton, 300, 620);
        addObject(titlescreenbutton, 300, 670);
    }
    /**
     * Act method detects button clicks for restart and title screen functions.
     */
    public void act(){
       
    }
    /**
     * Displays player's scores and button text
     */
    public void text(int player1, int player2,int player3,int player4)
    {
        showText("Player 1 score:"+player1,100,20);
        showText("Player 2 score:"+player2,100,70);
        showText("Player 3 score:"+player3,300,20);
        showText("Player 4 score:"+player4,300,70);
        showText("Restart?",300, 620);
        showText("Title Screen?",300, 670);
    }
    public void stopSound()
    {
        backgroundMusic.stop();
    }
}
