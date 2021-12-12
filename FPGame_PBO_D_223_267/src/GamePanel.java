import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
   
   int x;
   int y;	
   
   Goal goal;
   Player player;
   Football football;
   InfoPanel infoPanel;

   SoundManager soundManager;
   
   
   public GamePanel (InfoPanel infoPanel) {
	setBackground(new Color(0,100,0));//Color RGB(0,100,0) dibuat background, setBackground berasal dari warisan JPanel
	this.infoPanel = infoPanel;
	this.soundManager = SoundManager.getInstance();
   }

//   6. membuat entitas dari kelas player dan juga goal (Ke kelas player)
   public void createGameEntities() {
       player = new Player (this, 20, 750);//this pada player mereference ke JPanel
       goal = new Goal(this,300,10);//this pada goal juga mereference ke JPanel
   }

   public void kickBall(){
      if(football!=null)
      {
	       if(football.gety()<0)
	       {
	         soundManager.playSound("kick", false);
	         
	         football = new Football(this,infoPanel,player,this.goal);
	         football.start();
	       }
      }
      else{
      soundManager.playSound("kick", false);
      football = new Football(this,infoPanel,player,this.goal);
      football.start();
      }
   }
   
   public void startGoal(){
      soundManager.playSound("game_intro", false);
      soundManager.playSound("whistle",false);
      goal = new Goal(this,300,10);
      goal.start();
      soundManager.playSound("background",true);
   }

   public void drawGameEntities() {

       if (player != null&&goal!= null){
         player.draw();
         goal.draw();
       }
       	
   }

  public void updateGameEntities(int direction) {

	if (player == null)
	  return;

	if (direction == 1) {
	  player.erase();
       	  player.moveLeft();
	}
	else
	if (direction == 3) {
	  player.erase();
       	  player.moveRight();
	}

 }

   public void setX (int xPos) {
	x = xPos;
   }

   public void setY (int yPos) {
	y = yPos;
   }

   public void paintComponent (Graphics g) {

      super.paintComponent(g);
   }
}
