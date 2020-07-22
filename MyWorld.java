import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Stack;
import java.util.NoSuchElementException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
/**
 * This is bomberman complete with four possible players on one keyboard.
 * Controls are WASD and E to drop bomb for player 1
 * TFGH for movement and Y to drop bomb for player 2
 * IJKL for movement O to drop bomb for player 3
 * Arrow keys for movement and 0 to drop bomb for player 4
 * Kill all the other players to win.
 * TextFile will save past scores between the four players.
 * Bombs can only destroy brown blocks.
 * 
 * @author Anson & Gavin & James
 * @version January 2020
 */
public class MyWorld extends greenfoot.World
{
    private int[][] gameArray = new int[13][9];
    public Scanner s;
    String file = "Score.txt";
    public String player;
    public int x = 0;
    public int p1 = 0;
    public int p2 = 0;
    public int p3 = 0;
    public int p4 = 0;
    private int numplayers;
    PrintWriter outputStream = null;
    public boolean lines = true;    
    private Stack<Powerup> powerups = new Stack<Powerup>();
    Brick brick;
    Rock rock;
    Bomber bomber1;
    Bomber bomber2;
    Bomber bomber3;
    Bomber bomber4;
    boolean player1Win = false;
    boolean player2Win = false;
    boolean player3Win = false;
    boolean player4Win = false;
    boolean gameOver = false;
    GreenfootSound backgroundMusic = new GreenfootSound("Fountain of Dreams.mp3");
    /**
     * World creates a randomized  field of blocks backed by a 2d array
     * Adds the bomberman objects to world and starts the game music.
     * @param players The number of players playing the game.
     */
    public MyWorld(int players)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1040, 720, 1);
        numplayers=players;
        bomber1 = new PlayerOne();
        bomber2 = new PlayerTwo();
        bomber3 = new PlayerThree();
        bomber4 = new PlayerFour();
        addObject(bomber1, 40, 40);
        addObject(bomber2, 1000, 40);
        addObject(bomber3, 40, 680);
        addObject(bomber4, 1000, 680);
        createGameWorld();
        backgroundMusic.setVolume(20);
        backgroundMusic.playLoop();
        makePowerups();
        if(players == 2)
        {
            bomber2.death();
            bomber3.death();
        }
        if(players == 3)
        {
            bomber2.death();
        }
    }
    /**
     * Checks which players are still alive every act cycle.
     */
    public void act(){
        if(gameOver == false){
            checkWinner();
        }
    }
    /**
     * Returns the 2D array backing the entire game.
     * 
     * @return int[][] returns the 2D array backing the entire game.
     */
    public int[][] getArray()
    {
        return gameArray;
    }
    /**
     * Setter method for the 2D array backing the entire game.
     */
    public void setArray(int x, int y, int z)
    {
        gameArray[x][y] = z;
    }    
    /**
     * Creates the randomzied world in the form of a 2D array with integer 
     * values of 0,1,2. 0 is empty space, 2 are unbreakable blocks (fixed in
     * place) and 1 are breakable blocks. 
     */
    public void createGameWorld(){
        for(int i =0; i<13;i+=1){
            for(int j =0; j<9;j+=1){
                gameArray[i][j]= 0;
            }
        }
        for(int i =1; i<13;i+=2){
            for(int j =1; j<9;j+=2){
                gameArray[i][j]= 2;
            }
        }
        for(int i =0; i<13;i+=1){
            for(int j =0; j<9;j+=1){
                if(gameArray[i][j] != 2)
                {
                    int r = Greenfoot.getRandomNumber(4);
                    if(r!=1)
                    {
                        gameArray[i][j] = 1;

                    }
                }
            }
        }
        gameArray[0][0]=0;
        gameArray[0][1]=0;
        gameArray[1][0]=0;
        gameArray[11][0]=0;
        gameArray[12][0]=0;
        gameArray[12][1]=0;
        gameArray[0][7]=0;
        gameArray[0][8]=0;
        gameArray[1][8]=0;
        gameArray[11][8]=0;
        gameArray[12][7]=0;
        gameArray[12][8]=0;
        for(int i =0; i<13;i+=1){
            for(int j =0; j<9;j+=1){
                if(gameArray[i][j] == 1)
                {
                    brick = new Brick();
                    addObject(brick, 80*i+40, 80*j+40);
                }
                else if(gameArray[i][j] == 2)
                {
                    rock = new Rock();
                    addObject(rock, 80*i+40, 80*j+40);
                }
            }
        }
    }
    /**
     * Randomized powerups created using random greenfoot numbers.
     * 25% chance for Fire
     * 20% chance for Speed
     * 5% chance for Extra life
     * 10% chance for Skull debuff
     * 15% chance for Piercing(Spike) bomb
     * 25% chance for Increase to total bomb placed at once
     * 
     * Pushes these powerups into a stack to be used whenever a block is destroyed.
     * When a block is destroyed there is a 50% chance of spawning a powerup
     * and when a powerup is spawned the stack will lose an object.
     * 
     */
    public void makePowerups()
    {
        for(int i = 0; i<100; i++)
        {
            int rand = Greenfoot.getRandomNumber(100);
            if(rand < 25)
            {
                Fire fire = new Fire();
                powerups.push(fire);
            }
            if(rand>24 && rand<45)
            {
                Speed speed = new Speed();
                powerups.push(speed);
            }
            if(rand>44 && rand<50)
            {
                LifeUp life = new LifeUp();
                powerups.push(life);
            }
            if(rand>49 && rand<65)
            {
                Spike spike = new Spike();
                powerups.push(spike);
            }
            if(rand>64 && rand<75)
            {
                Skull skull = new Skull();
                powerups.push(skull);
            }
            if(rand>75)
            {
                BombUp bombUp = new BombUp();
                powerups.push(bombUp);
            }
        }
    }
    /** 
     * Returns the next powerup in the stack full of randomized powerups.
     * @return Powerup Returns the next powerup in the stack.
     */
    public Powerup nextPowerup()
    {
        return (powerups.pop());
    }
    /** 
     * Checks for the winner of the game and writes the winner's win into a textfile
     * to save their score. Also transitions into the endgame screen when a player
     * wins.
     */
    public void checkWinner(){
        if(bomber2.checkDead()&&bomber3.checkDead()&&bomber4.checkDead()&&bomber1.checkDead()){
            player = "0";
            try{
                outputStream = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                outputStream.println(player);
                outputStream.close();
                s = new Scanner(new File(file));
                while(lines){
                    try{
                        x = Integer.parseInt(s.nextLine());
                        if (x == 1){
                            p1++;
                        }
                        if (x == 2){
                            p2++;
                        }
                        if (x == 3){
                            p3++;
                        }
                        if (x == 4){
                            p4++;
                        }
                    }
                    catch(NumberFormatException e){
                        lines = false;
                    }
                    catch(NullPointerException e){
                        lines = false;
                    }
                    catch(NoSuchElementException e){
                        lines = false;
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace(); 
            }
            backgroundMusic.stop();
            Greenfoot.setWorld(new EndScreen(0,p1,p2,p3,p4,numplayers));
        }
        if(bomber2.checkDead()&&bomber3.checkDead()&&bomber1.checkDead()&&bomber4.checkDead()!=true){
            boolean player4Win = true;
            player = "4";
            try{
                outputStream = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                outputStream.println(player);
                outputStream.close();
                s = new Scanner(new File(file));
                while(lines){
                    try{
                        x = Integer.parseInt(s.nextLine());
                        if (x == 1){
                            p1++;
                        }
                        if (x == 2){
                            p2++;
                        }
                        if (x == 3){
                            p3++;
                        }
                        if (x == 4){
                            p4++;
                        }
                    }
                    catch(NumberFormatException e){
                        lines = false;
                    }
                    catch(NullPointerException e){
                        lines = false;
                    }
                    catch(NoSuchElementException e){
                        lines = false;
                    }                    
                }
            }
            catch(IOException e){
                e.printStackTrace(); 
            }
            backgroundMusic.stop();
            Greenfoot.setWorld(new EndScreen(4,p1,p2,p3,p4,numplayers));
        }
        if(bomber2.checkDead()&&bomber4.checkDead()&&bomber1.checkDead()&&bomber3.checkDead()!=true){
            boolean player3Win = true;
            player = "3";
            try{
                outputStream = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                outputStream.println(player);
                outputStream.close();
                s = new Scanner(new File(file));
                while(lines){
                    try{
                        x = Integer.parseInt(s.nextLine());
                        if (x == 1){
                            p1++;
                        }
                        if (x == 2){
                            p2++;
                        }
                        if (x == 3){
                            p3++;
                        }
                        if (x == 4){
                            p4++;
                        }
                    }
                    catch(NumberFormatException e){
                        lines = false;
                    }
                    catch(NullPointerException e){
                        lines = false;
                    }                    
                    catch(NoSuchElementException e){
                        lines = false;
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace(); 
            }
            backgroundMusic.stop();
            Greenfoot.setWorld(new EndScreen(3,p1,p2,p3,p4,numplayers));
        }
        if(bomber3.checkDead()&&bomber1.checkDead()&&bomber4.checkDead()&&bomber2.checkDead()!=true){
            boolean player2Win = true;
            player = "2";
            try{
                outputStream = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                outputStream.println(player);
                outputStream.close();
                s = new Scanner(new File(file));
                while(lines){
                    try{
                        x = Integer.parseInt(s.nextLine());
                        if (x == 1){
                            p1++;
                        }
                        if (x == 2){
                            p2++;
                        }
                        if (x == 3){
                            p3++;
                        }
                        if (x == 4){
                            p4++;
                        }
                    }
                    catch(NumberFormatException e){
                        lines = false;
                    }
                    catch(NullPointerException e){
                        lines = false;
                    }
                    catch(NoSuchElementException e){
                        lines = false;
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace(); 
            }
            backgroundMusic.stop();
            Greenfoot.setWorld(new EndScreen(2,p1,p2,p3,p4,numplayers));
        }
        if(bomber2.checkDead()&&bomber3.checkDead()&&bomber4.checkDead()&&bomber1.checkDead()!=true){
            boolean player1Win = true;
            player = "1";
            try{
                outputStream = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                outputStream.println(player);
                outputStream.close();
                s = new Scanner(new File(file));
                while(lines){
                    try{
                        x = Integer.parseInt(s.nextLine());
                        if (x == 1){
                            p1++;
                        }
                        if (x == 2){
                            p2++;
                        }
                        if (x == 3){
                            p3++;
                        }
                        if (x == 4){
                            p4++;
                        }
                    }
                    catch(NumberFormatException e){
                        lines = false;
                    }
                    catch(NullPointerException e){
                        lines = false;
                    }
                    catch(NoSuchElementException e){
                        lines = false;
                    }
                }
            }
            catch(IOException e){
                e.printStackTrace(); 
            }
            backgroundMusic.stop();
            Greenfoot.setWorld(new EndScreen(1,p1,p2,p3,p4,numplayers));
        }       
    }
    
}