import java.awt.*;

public class Sprite {
	
	protected int x,y,width,height;

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() { 
		return x;	// Return value
	}
	public int getY() { 
		return y;	// Return value
	}
	public int getWidth() { 
		return width;	// Return value
	}
	public int getHeight() { 
		return height;	// Return value
	}
	
	Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
	public void paint(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, Settings.LIVES_WIDTH,Settings.LIVES_HEIGHT);
	}
}


