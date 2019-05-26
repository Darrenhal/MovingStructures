import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

	private Dot[] dots;
	public static final int SIZE = 800;
	private int red = 0;
	private int green = 0;
	private int blue = 0;
	
	public Main() {
		super("My Frame");

		dots = new Dot[25];
		for (int i = 0; i < dots.length; i++) {
			int x = new Random().nextInt(SIZE - 50);
			int y = new Random().nextInt(SIZE - 50);
			dots[i] = new Dot(x, y);
		}

		// You can set the content pane of the frame to your custom class.
		DrawPane contentPane = new DrawPane();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(SIZE, SIZE);
		setVisible(true);
		
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			move();
			contentPane.repaint();
		}
	}
	
	private void move() {
		for (int i = 0; i < dots.length; i++) {
			dots[i].moveX();
			dots[i].moveY();
		}
	}

	// Create a component that you can actually draw on.
	class DrawPane extends JPanel {

		private int colorChange = 0;
		
		public DrawPane() {
			setSize(SIZE, SIZE);
		}
		
		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, SIZE, SIZE);
			for (int i = 0; i < dots.length; i++) {
				g.setColor(dots[i].getDotColor());
				for (int j = 0; j < dots.length; j++) {
					if (colorChange == 20) {
						dots[i].changeDotColor();
						colorChange = 0;
					}
					g.drawLine(dots[i].getX(), dots[i].getY(), dots[j].getX(), dots[j].getY());
				}
			}
			colorChange ++;
		}
	}

	public static void main(String args[]) {
		new Main();
	}

}
