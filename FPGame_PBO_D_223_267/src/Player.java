import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player extends Thread {

   private static final int XSIZE = 25;
   private static final int YSIZE = 25;

   private JPanel panel;
   private int x;
   private int y;
   private int dx;
   private int dy;
   Graphics g;
   Graphics2D g2;
   private Color backgroundColor;
   private Dimension dimension;
   private boolean start = false;

//   menginstantiate kelas Player
   public Player (JPanel p, int xPos, int yPos) {
      panel = p;
      backgroundColor = panel.getBackground ();//getBackground berasal dari method JPanel
      x = xPos;
      y = yPos;
      dx = 8;
      dy = 0;
   }

   public void draw () {
      Graphics g = panel.getGraphics();
      g2 = (Graphics2D) g;
      
      Ellipse2D bat =new Ellipse2D.Double (x, y, XSIZE, YSIZE);
      g2.fill(bat);
      start = true;
   }

   public void erase () {
      Graphics g = panel.getGraphics();
      g2 = (Graphics2D) g;
      g2.setColor (backgroundColor);
//      g.drawImage(background, 0, 0, 800, 800, null);
      g2.fill (new Ellipse2D.Double (x, y, XSIZE, YSIZE));
   }

   public void moveLeft () {

      if (!panel.isVisible()) return;
      
      dimension = panel.getSize();
// kalau kita berjalan di sebelah kiri, kita harus memastikan posisinya lebih dari 0 atau tidak kelebihan sisi kiri panel
      if (x - dx > 0)x -= dx;      

   }

   public void moveRight () {

      if (!panel.isVisible ()) return;

      dimension = panel.getSize();

      if (x + dx + XSIZE < dimension.width)x += dx;      
   }

   public int getx(){
       return this.x;
   }

   public int gety(){
    return this.y;
   }   
}