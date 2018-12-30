package mygame;
/**
 * Write a description of class Background here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
public class Background
{
    private BufferedImage image;
    
    //Check this my g.
    //Points on the image
    private double x;
    private double y;
  
    public Background(String s){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(s)); // Able to load images from the file
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void update() {}
	
    public void draw(Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null); // Position for the image
		
		}
}
	