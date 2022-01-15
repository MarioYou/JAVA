import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 75; // the higher the delay the slower the game. 
	
	// creating arrays
	final int x[] = new int[GAME_UNITS]; // holds the x coordinates
	final int y[] = new int[GAME_UNITS]; // holds the y coordinates
	int bodyParts = 6;
	int applesEaten = 0; 
	int appleX; // randomly appear
	int appleY; // randomly appear
	char direction = 'R'; // the snake will begin by going Right 
	boolean running = false; 
	Timer timer; 
	Random random; 
	
	
	
	GamePanel(){
		
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
	}
	public void startGame() {
		newApple(); // call the method to create a new apple
		running = true; 
		timer = new Timer(DELAY, this);
		timer.start(); // starting the timer. 
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
			// for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++) { //using this for loop to draw lines across the panel
			//	g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT); // drawing a vertical lines
			//	g.drawLine(0,i*UNIT_SIZE, SCREEN_WIDTH,i*UNIT_SIZE);
			//}
			g.setColor(Color.green);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(Color.gray); // head color
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255))); // creates random colors for the head
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					g.setColor(Color.gray); // body color
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			
			// Score 
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			
			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize()); // puts it in the center. 
		}

		else {
			gameOver(g);
		}
	}
		
	public void newApple() {
		// adding the -1 to the screen width and height makes the game a little easier because the apples will never touch the wall. 
		appleX = random.nextInt((int)((SCREEN_WIDTH-1)/UNIT_SIZE))*UNIT_SIZE; // makes it appear randomly on the x axis
		appleY = random.nextInt((int)((SCREEN_HEIGHT-1)/UNIT_SIZE))*UNIT_SIZE; // makes it appear randomly on the y axis
	}
	
	public void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i-1]; // shifting the coordinates by 1 spot
			y[i] = y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break; 
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}
	
	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	
	public void checkCollisions() {
		// checks if the head collides with the body
		for(int i = bodyParts; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		// checks if the head collides the left border
		if(x[0] < 0) {
			running = false; 
		}
		
		// checks if the head collides the right border
		if(x[0] > SCREEN_WIDTH) {
			running = false; 
		}
		
		// checks if the head collides the top border
		if(y[0] < 0) {
			running = false; 
		}
		
		// checks if the head collides the bottom border
		if(x[0] > SCREEN_HEIGHT) {
			running = false; 
		}
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		// Game over text 
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 30));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("GAME OVER! , you got "+applesEaten+" Points", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER! , you got "+applesEaten+"Points"))/2, SCREEN_HEIGHT/2); // puts it in the center. 
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
			
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override 
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break; 
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break; 
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break; 
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break; 
			}
		}
	}
}
