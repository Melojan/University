package mygame;
/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.awt.Graphics2D;
public class GameManager
{
    private ArrayList<GameState> gs = new ArrayList<GameState>();
    private int choice;
    
    public static final int MENU = 0;
    public static final int PLAY = 1; // in game state
    public static final int HELP = 2; // in game state
    
    private Player player;
    
    public GameManager(){
        choice = MENU;
        gs.add(new MenuState(this));
        gs.add(new PlayState(this));
        gs.add(new HelpState(this));
    }
    
    public void setState (int state){
        choice = state;
        gs.get(choice).init();
    }
    
    public void update() {
        gs.get(choice).update();
    }
    
    public void draw(Graphics2D g){
        gs.get(choice).draw(g);
    }
    
    public void keyPressed(int k){
        gs.get(choice).keyPressed(k);
    }
    
    public void keyReleased(int k){
        gs.get(choice).keyReleased(k);
    }
    
    public void setPlayer(Player p) {
        player = p;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public GameState getState(int id) {
        return gs.get(id);
    }
}
