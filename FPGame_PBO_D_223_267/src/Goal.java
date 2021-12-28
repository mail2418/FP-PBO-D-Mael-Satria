import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Goal extends Thread{

	private static final int XSIZE = 100;
	private static final int YSIZE = 10;

   private JPanel panel;
   private int x;
   private int y;
   private int dx;
   boolean mvright;
   private Random rand;
   Graphics2D g2;
   private Color backgroundColor;
   private Dimension dimension;
   private int randomDirectionY;
   private InfoPanel infoPanel;
   Image background = new ImageIcon("./Asset/Background2.png").getImage();
   public Goal (JPanel p, int xPos, int yPos, InfoPanel infoPanel) {
      this.panel = p;
      this.backgroundColor = panel.getBackground();
      this.x = xPos;
      this.y = yPos;
      this.mvright = false;
      this.infoPanel = infoPanel;
      
      rand = new Random();
      randomDirectionY = 100 + rand.nextInt(infoPanel.getLevel()) < 400? 50 + rand.nextInt(infoPanel.getLevel()): 400;
      this.dx = 10 + rand.nextInt(infoPanel.getLevel()) < 50? 10 + rand.nextInt(infoPanel.getLevel()): 50;
     
   }

   public void draw () {
    Graphics g = panel.getGraphics ();
    g2 = (Graphics2D) g;
    Rectangle goal = new Rectangle(x, y, XSIZE, YSIZE);
    g.setColor(Color.DARK_GRAY);
    g2.fill(goal);
   }

   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double (x, y, XSIZE, YSIZE);
   }

   public void erase () {
      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;
      g2.setColor (backgroundColor);
      g2.fill (new Rectangle(x, y, XSIZE, YSIZE));
   }

   
   public void move(){
    
    if (!panel.isVisible ()) return;

    dimension = panel.getSize();

    if (((x + XSIZE) < dimension.width) && !mvright)
    {
    	x += dx;
    	if(y < 350 && y > 0) {
    		y +=  rand.nextInt(15);
    		y -= rand.nextInt(15);    		
    	}
    	else
    		y = rand.nextInt(100);
    }
    else
       mvright = true;   
    
    if((x  > 0) && mvright == true) {
    	x -= dx;    
    	if(y < 350 && y > 0) {
    		y += randomDirectionY = rand.nextInt(5);
    		y -= randomDirectionY = rand.nextInt(9);    		
    	}
    	else
    		y = randomDirectionY = rand.nextInt(100);
    }
    else
    	mvright = false;
    
 }

  public void run () {
	  try {
	      draw ();
	      while (true) {			
	          erase();
	          move ();
	          draw();
	          sleep (55);			
	      }
	  }
	  catch(InterruptedException e) {
	      System.out.println(e +" Terdapat error");
	
	  }
  }

}