package mygame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;

public class PlayState extends GameState
{
    private GameManager gm;
    private Background bg;
    
    public PlayState(GameManager gm)
    {
        this.gm = gm;
        bg = new Background("ingame_scene.png");
    }
    
    public void init() {
    }
    
    public void update() {
        
    }
    
    public void draw(Graphics2D g) {
        Player p = gm.getPlayer();
        
        // draw background
        bg.draw(g);
        
        // draw player
        p.draw(g);
    }
    
    public void keyPressed(int k) {
        Player p = gm.getPlayer();
        
        // TODO add more actions, put some delays?, add boundaries so you cant move off screen
        // load different maps or smth, check collisions
        
        // player movement
        if (k == KeyEvent.VK_LEFT) {
            p.moveLeft();
        } else if (k == KeyEvent.VK_RIGHT) {
            p.moveRight();
        }
    }
    
    public void keyReleased(int k) {
        
    }
}
