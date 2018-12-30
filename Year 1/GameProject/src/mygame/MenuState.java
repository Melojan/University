package mygame;
/**
 * Write a description of class MenuState here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
public class MenuState extends GameState
{
    private Background bg;
    
    private int choice = 0;
    private String [] options = {"START","HELP","QUIT"};
    
    private Font titleFont;
    private Font font;
    
    //private Stage stage;
    
    public MenuState(GameManager gm){
        this.gm = gm;
        
        try{
            bg = new Background("game2.jpg");
            
            
            new Color(128,0,0);
            titleFont = new Font("Broadway" , Font.PLAIN,20);
            
            font = new Font("Arial",Font.PLAIN,12);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void init(){};
    public void update(){
        bg.update();
    }
    public void draw(Graphics2D g){
        //Draw Background
        bg.draw(g);
        
        //Draw Title
        Color titleColor = null;
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("SUPER SQUARE", 80, 70);
        
        //draw Menu Options
        g.setFont(font);
        for(int i = 0 ; i < options.length ; i++){
            if(i == choice){
                g.setColor(Color.RED);
            }
            else
            {
                g.setColor(Color.BLACK);
            }
            g.drawString(options[i] , 150, 140 + i * 10);
        }
    }
    private void select() {
        if(choice == 0){
            // get player object parameters
            String name = JOptionPane.showInputDialog(null,"Name your Character?");
           
            int health = (int)Math.round(Math.random()*100);
            double ap = Math.round(Math.random()*100);
            double dp = Math.round(Math.random()*100);
            
            // create player object
            Player player = new Player(name, ap, dp, health);
            
            // print details
            String info = "Name: " + name + "\n" + "Health: " + health + "%" + "\n" + "Attack: " + ap + "\n" + "Defense: " + dp;
            JOptionPane.showMessageDialog(null,info);
            
            // now update state
            gm.setPlayer(player);
            gm.setState(GameManager.PLAY);
        }
        
        if(choice == 1){
            gm.setState(GameManager.HELP);
        }
        
        if(choice == 2){
            System.exit(0);
        }
    }
    
    /**
     * We store in here, when the last menu action was processed.
     */
    private long lastKeyPress = 0;
    /**
     * The delay in between key presses we want, in milli seconds.
     */
    private int scrollKeyPressDelay = 200;
    /**
     * The delay in between key presses we want, in milli seconds.
     */
    private int enterKeyPressDelay = 350;
    
    public void setLastKeyPress(long d) {
        lastKeyPress = d;
    }
    
    public void keyPressed(int k){
        if (System.currentTimeMillis() > lastKeyPress + enterKeyPressDelay) {
            // confirm option
            if(k == KeyEvent.VK_ENTER){
                select();
                lastKeyPress = System.currentTimeMillis(); // update last key press
            }
        }
        
        if (System.currentTimeMillis() > lastKeyPress + scrollKeyPressDelay) { // if current_time > lastkeypress + delay
            // scroll through options
            if(k == KeyEvent.VK_UP){
                choice--;
                
                if(choice == -1){
                    choice = options.length - 1;
                }
                lastKeyPress = System.currentTimeMillis(); // update last key press
            }
            
            if(k == KeyEvent.VK_DOWN){
                choice++;
                
                if(choice == options.length){
                    choice = 0;
                }
                lastKeyPress = System.currentTimeMillis(); // update last key press
            }
        }
    }
        
    public void keyReleased(int k){
    }
    
}
        

