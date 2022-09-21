import java.awt.*;
public class Paddle extends Sprite {

	private int xVelocity;
	private Image pic;
	
	public Paddle() {
		// Set width
		width = Settings.PADDLE_WIDTH;
		// Set height
		height = Settings.PADDLE_HEIGHT;
		// Call resetPosition
		resetPosition();
	}
	
	public void resetPosition() {
		// Set initial position x and y
		setX(Settings.INITIAL_PADDLE_X);
		setY(Settings.INITIAL_PADDLE_Y);
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, Settings.PADDLE_WIDTH, Settings.PADDLE_HEIGHT);
	}

}
