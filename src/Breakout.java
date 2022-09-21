import java.awt.*;

import javax.swing.JFrame;

public class Breakout extends JFrame{
	
	static final long serialVersionUID = 1L;
	
	private BreakoutPanel panel;
	
	public Breakout() {

		this.setPreferredSize(new Dimension(Settings.WINDOW_WIDTH,Settings.WINDOW_HEIGHT ));
		this.setTitle(Settings.WINDOW_NAME);
		this.setBackground(Color.LIGHT_GRAY);
		this.pack();
		this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new BreakoutPanel(this);
        this.add(panel);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        	 new Breakout();	
	         }
		});

	}
}
