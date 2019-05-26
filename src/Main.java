import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {

	Dot[] dots;
	
	public Main() {
		super("My Frame");

		dots = new Dot[30];
		for (int i = 0; i < dots.length; i++) {
			int x = new Random().nextInt(350);
			int y = new Random().nextInt(350);
			dots[i] = new Dot(x, y);
		}

		// You can set the content pane of the frame to your custom class.
		DrawPane contentPane = new DrawPane();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setVisible(true);
		
		while (true) {
			try {
				Thread.sleep(200);
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

		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 400, 400);
			g.setColor(Color.BLUE);
			for (int i = 0; i < dots.length; i++) {
				for (int j = 0; j < dots.length; j++) {
					g.drawLine(dots[i].getX(), dots[i].getY(), dots[j].getX(), dots[j].getY());
				}
			}
		}
	}

	public static void main(String args[]) {
		new Main();
	}

}
