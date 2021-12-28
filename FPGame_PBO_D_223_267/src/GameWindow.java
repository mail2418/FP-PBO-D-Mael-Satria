import javax.swing.*;			
import java.awt.*;			
import java.awt.event.*;

/* 2. Dalam kelas GameWindow mengimplementasikan KeyListener dan ActionListener yang merupakan interface dari awt.event,
 	  Serta merupakan kelas inheritance dari JFrame*/
public class GameWindow extends JFrame implements KeyListener,ActionListener{
	
	private JLabel gameL;
	private JButton createBallB;
	private JButton exitB;
    boolean started;

	InfoPanel infoPanel;
	GamePanel gamePanel;
	
	JPanel buttonPanel;
	JPanel mainPanel;

	private Container c;
	
	int x, y;
	
	public GameWindow() {
 
		setTitle ("Tendangan Si Messi"); //function dari JFrame
		setSize (800, 1150); //function dari JFrame
		
		gameL = new JLabel ("");//JLabel gameL
        started =  false;// boolean Started

		createBallB = new JButton ("Start game"); //JButton CreateBallB
		exitB = new JButton ("Exit");//JButton exitB

		createBallB.addActionListener(this); //Method dari interface ActionListener
		exitB.addActionListener(this);// Method dari interface ActionListener

//		3. mendeklarasi infopanel
		infoPanel = new InfoPanel(); 
//		4. mendeklarasi gamePanel dengan parameternya yaitu infopanel
		gamePanel = new GamePanel(infoPanel);
//		5. membuat entitas pada game (Ke kelas gamePanel)
//		gamePanel.createGameEntities();
//		6. membuat besar lapangan (ke kelas gamePanel)
        gamePanel.setPreferredSize(new Dimension(800, 800)); //Untuk menentukan besar lapangan 

		GridLayout gridLayout;
//		Menginstantiate JPanel dengan variabel buttonPnael
		buttonPanel = new JPanel();
		
		gridLayout = new GridLayout(1, 4); //membuat grid dengan tabel 1x4
//		set layout buttonpanel dengan grid yang sudah dibikin (1x4)
		buttonPanel.setLayout(gridLayout); 

//		Membuat tombol start
		buttonPanel.add (createBallB);
//		Membuat tombol exit
		buttonPanel.add (exitB);

		mainPanel = new JPanel();
//		Membuat tombol-tombol penting
		mainPanel.add(infoPanel);
		mainPanel.add(gameL);
		mainPanel.add(gamePanel);
		mainPanel.add(buttonPanel);

		c = getContentPane();//Container getContentPane()
		c.add(mainPanel);

		mainPanel.addKeyListener(this);
		mainPanel.requestFocus();
		mainPanel.setFocusable(true);
		
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
		if(started){
		int direction = 0;

		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_LEFT) {
			direction = 1;	
		}
		else if (keyCode == KeyEvent.VK_RIGHT) {
			direction = 3;	
		}
		
		if(keyCode==KeyEvent.VK_SPACE){
//		Menendang Bola dan digambar entitasnya (Ke kelas GamePanel)
		 gamePanel.kickBall();
//		 Menggambar entitas pemain dan bola
		 gamePanel.drawGameEntities();
		}
		
		gamePanel.updateGameEntities(direction);
		gamePanel.drawGameEntities();
	}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if (command.equals(createBallB.getText()) && !started) {
			gamePanel.drawGameEntities();
			gamePanel.startGoal();
			started = true;
		}
		else if (command.equals(exitB.getText())) {
			System.exit(0);
		}

		mainPanel.requestFocus();

	}
}
