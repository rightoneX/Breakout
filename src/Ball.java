import java.awt.*;

public class Ball extends Sprite {

	private int xVelocity = 1, yVelocity = -1;
	
	// Constructor
	public Ball() {
		// Set width
		width = Settings.BALL_WIDTH;
		// Set height
		height = Settings.BALL_HEIGHT;
		// Call resetPosition
		resetPosition();
	}

	public void resetPosition() {
		// Set the balls y by using the INITIAL_BALL_Y
		setX(Settings.INITIAL_BALL_X);
		setY(Settings.INITIAL_BALL_Y);
	}
	
	public void update() {
		x += xVelocity;
		y += yVelocity;
		// Bounce off left side of screen
		if(x <= 0) {
			// Set x to 0 so it does not leave the screen
			x = 0;
			// Change the x velocity to make the ball go right
			xVelocity *= -1;
		}
		
		// Bounce off right side of screen
		if(x >= Settings.WINDOW_WIDTH - Settings.BALL_WIDTH) {
			// Change the x velocity to make the ball go left
			xVelocity *= -1;
		}
		
		// Bounce off top of screen
		if(y <= 0) {
			// Set y to 0 so it does not leave the screen
			y = 0;
			// Change the y velocity to make the ball go downward
			yVelocity *= -1;
		}
		
	}
	
	public void setXVelocity(int x) {
		// Set the x velocity
		xVelocity = 1;
	}
	public void setYVelocity(int y) {
		// Set the y velocity
		yVelocity = -1;
	}
	
	public int getXVelocity() {
		return xVelocity;	// Return the x velocity
	}
	public int getYVelocity() {
		return yVelocity;	// Return the y velocity
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(x, y, Settings.BALL_WIDTH, Settings.BALL_HEIGHT);
	}
}
