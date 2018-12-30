package mygame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class HelpState extends GameState
{
    private Background bg;
    private Font titleFont;
    private Font font;
    
    public HelpState(GameManager gm){
        this.gm = gm;
        
        try{
            bg = new Background("game2.jpg");
            
            new Color(128,0,0);
            titleFont = new Font("Broadway" , Font.PLAIN,20);
            
            font = new Font("Arial",Font.PLAIN,10);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void init(){
        lastKeyPress = System.currentTimeMillis();
    };
    
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
        g.drawString("My help information line 1", 100, 90);
        g.drawString("My help info line 2", 100, 105);
        g.drawString("Press enter to go back to the main menu.", 100, 120);
    }
    
    /**
     * We store in here, when the help option was started
     */
    private long lastKeyPress = 0;
    /**
     * The delay in between opening the help interface and going back.
     */
    private int keyPressDelay = 500;
    
    public void keyPressed(int k){
        if(System.currentTimeMillis() > lastKeyPress + keyPressDelay // if delay is fine
        && k == KeyEvent.VK_ENTER) { // and key is enter
            gm.setState(GameManager.MENU); // change state back to menu
            ((MenuState)gm.getState(GameManager.MENU)).setLastKeyPress(System.currentTimeMillis());
        }
    }
        
    public void keyReleased(int k){
    }
    
}
        
