import java.awt.Graphics;

public class Ball extends Sprite {

	private int xVelocity = 1, yVelocity = -1;
	
	// Constructor
	public Ball() {
		// Set width to Settings.BALL_WIDTH
		width = Settings.BALL_WIDTH;
		// Set width to Settings.BALL_HEIGHT
		height = Settings.BALL_HEIGHT;
		// Call resetPosition
		resetPosition();
	}
	
	/**
	 * Resets the ball to the initial position
	 * Uses Settings.INITIAL_BALL_X/Y to set the position of the ball
	 */
	public void resetPosition() {
		// Set the balls y by using the INITIAL_BALL_Y
		setX(Settings.INITIAL_BALL_X);
		setY(Settings.INITIAL_BALL_Y);
	}
	
	public void update() {
		x += xVelocity;
		// TODO: Increase the y variable by yVelocity (see above)
		y += yVelocity;
		
		// Bounce off left side of screen
		if(x <= 0) {
			// TODO: Set x to 0 so it does not leave the screen
			x = 0;
			// TODO: Change the x velocity to make the ball go right
			xVelocity *= -1;
		}
		
		// Bounce off right side of screen
		if(x >= Settings.WINDOW_WIDTH - Settings.BALL_WIDTH) {
			// TODO: Set x to the right edge of the screen (see the above if condition)
//			if(x >= Settings.WINDOW_WIDTH - Settings.BALL_WIDTH) {
//
//			}
			// TODO: Change the x velocity to make the ball go left
			xVelocity *= -1;

		}
		
		// Bounce off top of screen
		if(y <= 0) {
			// TODO: Set y to 0 so it does not leave the screen
			y = 0;
			// TODO: Change the y velocity to make the ball go downward
			yVelocity *= -1;
		}
		
	}
	
	public void setXVelocity(int x) {
		// TODO: Set the x velocity
		xVelocity = 1;
	}
	public void setYVelocity(int y) {
		// TODO: Set the y velocity
		yVelocity = -1;
	}
	
	public int getXVelocity() {
		return xVelocity;	// TODO: Return the x velocity
	}
	public int getYVelocity() {
		return yVelocity;	// TODO: Return the y velocity
	}
	
	public void paint(Graphics g) {
		g.fillOval(x, y, Settings.BALL_WIDTH, Settings.BALL_HEIGHT);
	}
}
