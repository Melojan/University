package mygame;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player
{
    /**
     * Amount to move horizontally by, moveSpeed is applied to it in the
     * moveLeft and moveRight functions.
     */
    private static final int BASE_MOVE_HORIZONTAL_UNIT = 10;
    
    //Player`s Status
    private int health;
    private int maxHealth;
    private boolean dead;
    
    //Animations Actions
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    
    private int width;
    private int height;
    private int cwidth;
    private int cheight;
    
    private double moveSpeed;
    private double maxSpeed;
    private double stopSpeed;
    private double jumpStart;
    private double stopJumpSpeed;
    
    private boolean facingRight;
    
    private double ap;
    private double dp;
    
    // position
    private int x;
    private int y;
    
    private BufferedImage playerImage;
    
    public Player(String name, double ap, double dp, int health){
        width = 30;
        height = 30;
      
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;
        
        facingRight = true;
        
        //health = maxHealth = 5;
        this.health = health;
        this.maxHealth = health;
        this.ap = ap;
        this.dp = dp;
        
        try{
            playerImage = ImageIO.read(getClass().getResourceAsStream("player.png"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    
    public void draw(Graphics g) { // draws the player image
        g.drawImage(playerImage, x, y, null);
    }
    
    public void moveLeft() {
        this.x = x - (int)(BASE_MOVE_HORIZONTAL_UNIT * moveSpeed);
    }
    
    public void moveRight() {
        this.x = x + (int)(BASE_MOVE_HORIZONTAL_UNIT * moveSpeed);
    }
}
