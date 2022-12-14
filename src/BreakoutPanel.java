import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BreakoutPanel extends JPanel implements ActionListener, KeyListener {
	
	static final long serialVersionUID = 2L;

	private boolean gameRunning = false;
	private int livesLeft = Settings.TOTAL_LIVES;
	private String screenMessage = "";
	private Ball ball;
	private Paddle paddle;
	private Brick bricks[];

	private Sprite lives[];
	
	public BreakoutPanel(Breakout game) {
		
		addKeyListener(this);
		setFocusable(true);
		
		Timer timer = new Timer(5, this);
		timer.start();

		// Create a lives
		lives = new Sprite[Settings.TOTAL_LIVES];
		// Call the createLives() method
		createLives();
	    // Create a new ball object and assign it to the appropriate variable
		ball = new Ball();
		// Create a new paddle object and assign it to the appropriate variable
		paddle = new Paddle();
		// Create a new bricks array
		bricks = new Brick[Settings.TOTAL_BRICKS];
		// Call the createBricks() method
		createBricks();

	}
	
	private void createBricks() {
		int counter = 0;
		int x_space = 0;
		int y_space = 0;
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 5; y++) {
				bricks[counter] = new Brick((x * Settings.BRICK_WIDTH) + Settings.BRICK_HORI_PADDING + x_space, (y * Settings.BRICK_HEIGHT) + Settings.BRICK_VERT_PADDING + y_space);
				counter++;
				y_space++;
			}
			x_space++;
			y_space = 0;
		}
	}


	private void createLives() {
		int x_space = 10;
		for(int counter  = 0; counter  < livesLeft; counter ++) {
				lives[counter] = new Sprite();
				lives[counter].setX(x_space);
				lives[counter].setY(Settings.WINDOW_HEIGHT - 70);
				x_space += Settings.LIVES_WIDTH + 5;
		}
	}

	private void paintBricks(Graphics g) {
		// Loop through the bricks
		for(int i = 0; i < Settings.TOTAL_BRICKS; i++){
			if(!bricks[i].isBroken()){
				bricks[i].paint(g);
			}
		}
		// Loop through the lives left
		for(int i = 0; i < livesLeft; i++){
				lives[i].paint(g);
		}
	}
	
	private void update() {
		if(gameRunning) {
			ball.update();
			collisions();
			repaint();
		}
	}
	
	private void gameOver() {
		// Set screen message
		screenMessage = "Game Over";
		stopGame();
	}
	
	private void gameWon() {
		// Set screen message
		screenMessage = "You Won";
		stopGame();
	}
	
	private void stopGame() {
		gameRunning = false;
	}
	
	private void collisions() {
		// Check for loss
		if(ball.y > 450) {
			// Game over
			livesLeft--;
			System.out.printf(String.valueOf(livesLeft));
			if(livesLeft <= 0) {
				gameOver();
				return;
			} else {
				ball.resetPosition();
				ball.setYVelocity(-1);
			}
		}
		
		// Check for win
		boolean bricksLeft = false;
		for(int i = 0; i < bricks.length; i++) {
			// Check if there are any bricks left
			if(!bricks[i].isBroken()) {
				// Brick was found, close loop
				bricksLeft = true;
				break;
			}
		}

		if(!bricksLeft) {
			gameWon();
			return;
		}
		
		// Check collisions
		if(ball.getRectangle().intersects(paddle.getRectangle())) {
			// Simplified touching of paddle
			// Proper game would change angle of ball depending on where it hit the paddle
			ball.setYVelocity(-1);
		}
		
		for(int i = 0; i < bricks.length; i++) {
			if (ball.getRectangle().intersects(bricks[i].getRectangle())) {
				int ballLeft = (int) ball.getRectangle().getMinX();
	            int ballHeight = (int) ball.getRectangle().getHeight();
	            int ballWidth = (int) ball.getRectangle().getWidth();
	            int ballTop = (int) ball.getRectangle().getMinY();

	            Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
	            Point pointLeft = new Point(ballLeft - 1, ballTop);
	            Point pointTop = new Point(ballLeft, ballTop - 1);
	            Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

	            if (!bricks[i].isBroken()) {
	                if (bricks[i].getRectangle().contains(pointRight)) {
	                    ball.setXVelocity(-1);
	                } else if (bricks[i].getRectangle().contains(pointLeft)) {
	                    ball.setXVelocity(1);
	                }

	                if (bricks[i].getRectangle().contains(pointTop)) {
	                    ball.setYVelocity(1);
	                } else if (bricks[i].getRectangle().contains(pointBottom)) {
	                    ball.setYVelocity(-1);
	                }
	                bricks[i].setBroken(true);
	            }
			}
		}
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.paint(g);
        paddle.paint(g);
        paintBricks(g);
        
        // Draw lives left
        // TODO: Draw lives left in the top left hand corner

        
        // Draw screen message
        if(screenMessage != null) {
        	g.setFont(new Font("Arial", Font.BOLD, 18));
        	int messageWidth = g.getFontMetrics().stringWidth(screenMessage);
        	g.drawString(screenMessage, (Settings.WINDOW_WIDTH / 2) - (messageWidth / 2), Settings.MESSAGE_POSITION);
        }
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: Set the velocity of the paddle depending on whether the player is pressing left or right
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)){
			paddle.x +=15;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0){
			paddle.x -=15;
		}
		//start the game
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(!gameRunning){
				ball.resetPosition();
				paddle.resetPosition();
				livesLeft = Settings.TOTAL_LIVES;
				screenMessage = "";
				createBricks();
				createLives();
				gameRunning = true;
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO: Set the velocity of the paddle after the player has released the keys

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
	}

}
