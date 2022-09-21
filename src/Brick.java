import java.awt.*;

public class Brick extends Sprite {
	
	private boolean broken = false;
	int R = (int)(Math.random()*256);
	int G = (int)(Math.random()*256);
	int B= (int)(Math.random()*256);
	Color color;
	public Brick(int x, int y) {
		// Set x using the parameter
		this.x = x;
		// Set y using the parameter
		this.y = y;
		// Set the width and height of the brick
		this.height = Settings.BRICK_HEIGHT;
		this.width = Settings.BRICK_WIDTH;
	}

	public boolean isBroken() {
		return broken;	// Return variable
	}
	public void setBroken(boolean b) {
		// Set the broken variable using the parameter given
		broken = b;
	}
	
	public void paint(Graphics g) {
		if(!broken) {
			color = new Color(R, G, B); //random color, but can be bright or dull
			g.setColor(color);
			g.fillRect(x, y, Settings.BRICK_WIDTH, Settings.BRICK_HEIGHT);
		}
	}
}
