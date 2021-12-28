import java.awt.Color;
import javax.swing.*;			
import java.awt.*;			

public class InfoPanel extends JPanel {

	private int hits;
	private int misses;
	private int points;
	private int level;
	private int tempResizeGoal;

	private JLabel hitsL;
	private JLabel missesL;
	private JLabel pointsL;
	private JLabel LevelL;


	private JTextField hitsTF;
	private JTextField missesTF;
	private JTextField pointsTF;
	private JTextField LevelTF;
	
   	public InfoPanel () {

		setBackground(Color.GRAY);

		hitsL = new JLabel ("# Goals");
		missesL = new JLabel ("# Misses   ");
		pointsL = new JLabel ("Points");
		LevelL = new JLabel("Level");

		hitsTF = new JTextField ();
		missesTF = new JTextField ();
		pointsTF = new JTextField ();
		LevelTF = new JTextField ();
		
		hitsTF.setEditable(false);
		missesTF.setEditable(false);
		pointsTF.setEditable(false);
		LevelTF.setEditable(false);

		GridLayout gridLayout;

		gridLayout = new GridLayout(2, 5);
		setLayout(gridLayout);

		add (hitsL);
		add (missesL);
		add (pointsL);
		add (LevelL);

		add (hitsTF);
		add (missesTF);
		add (pointsTF);
		add (LevelTF);

		resetInfo();
  	}
   	
	public void resetInfo () {
		hits = misses = points = 0;
		level = 1;
	}

	public void incrementHits () {
		hits++;
		level++;
	}

	public void incrementMisses () {
		misses++;
		if(level > 1)
			level--;
	}

	public void incrementPoints (int numPoints) {
		points+= numPoints;
	}

	public void decreasePoints(int points){
		if((this.points - points)>0)
			this.points-= points;
		else
			this.points = 0;
	}

	public void displayInfo () {
		hitsTF.setText (hits+"");
		missesTF.setText (misses+"");
		pointsTF.setText (points+"");
		LevelTF.setText(level+"");
	}
	
	public int getLevel() {
		return level;
	}

}