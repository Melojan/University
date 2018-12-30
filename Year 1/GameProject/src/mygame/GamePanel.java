package mygame;
/**
 * Write a description of class GamePanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;
//Runnable is a type of class that can be put into a thread , requires the class to implement the method run()
public class GamePanel extends JPanel implements Runnable, KeyListener
{
    //Dimensions
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;
    
    
    //Game Thread // implementing an interface and extending a class
    private Thread thread;
    private boolean running;
    private int FPS = 60; // set it to 60 , because the game is 2D
    private long targetTime = 1000/FPS;
    
    //Image
    private BufferedImage image;
    private Graphics2D g;
    
    private GameManager gm;
    
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH * SCALE ,HEIGHT * SCALE));
        setFocusable(true); // This method is to gain power of getting component focused
        requestFocus();
    }
    
    public void addNotify(){
        super.addNotify();
        //To start the Thread
        if(thread == null){
            thread = new Thread(this); 
            addKeyListener(this);
            thread.start();
        }
    }
    
    private void init(){
        // Do initialization
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;
        gm = new GameManager();
    }
    
    public void run(){
        init();
        
        //game timers
        long start;
        long elapsed;
        long wait;
        
        //game loop
        while(running){
            start = System.nanoTime();
            
            update();
            draw();
            drawToScreen();
            
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed/1000000;
            
            if(wait < 0){
                wait = 5;
            }
            
            //Nothing will happen to the game
            try{
                Thread.sleep(wait);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    private void update() {
        gm.update();
    }
    private void draw() {
        gm.draw(g);
    }
    private void drawToScreen() {
        Graphics g1 = getGraphics();
        g1.drawImage(image, 0, 0, WIDTH * SCALE , HEIGHT * SCALE ,null);
        g1.dispose();
    }
    public void keyTyped(KeyEvent key){}
    public void keyPressed(KeyEvent key){
        gm.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key){
        gm.keyPressed(key.getKeyCode());
    }
	

}
        

