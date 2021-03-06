import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JFrame {

	private static final long serialVersionUID = -609013040811944425L;
	private Dot[] dots;
	public static final int SIZE = 800;
	private final int colorReset = 20;
	private String[] drawModes = { "Static", "Mouse-Controlled", "Slight Random Movement", "Complete Madness" };
	private String[] colorModes = { "Random", "Spectrum" };
	private String[] options = { "Respawn Dots", "Set Dot Amount" };
	private int drawMode = 2; // 0 = static; 1 = mouse-controlled; 2 = slight
								// random movement; 3 = complete
								// randomness
	private int colorMode = 0;
	private int option = 0;
	private int dotAmount = 15;
	private boolean colorModeChanged = false;
	private boolean focusOnFrame = false;
	private boolean pauseDrawing = false;

	public static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	public Main() {
		super("Moving Structures - Press h for more options");

		dots = new Dot[dotAmount];
		for (int i = 0; i < dots.length; i++) {
			int x = new Random().nextInt(screen.width - 50);
			int y = new Random().nextInt(screen.height - 50);
			dots[i] = new Dot(x, y);
		}

		// You can set the content pane of the frame to your custom class.
		DrawPane contentPane = new DrawPane();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(screen.width, screen.height);
		setResizable(false);
		setVisible(true);

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				int userChoice = 0;
				if (e.getKeyChar() == 'h') {
					userChoice = JOptionPane.showOptionDialog(null, "Choose Structure Mode", "Structure Menu", 1, 1,
							null, drawModes, null);
				}

				if (e.getKeyChar() == 'c') {
					userChoice = JOptionPane.showOptionDialog(null, "Choose Color Mode", "Color Menu", 1, 1, null,
							colorModes, null);
				}

				if (e.getKeyChar() == 'o') {
					pauseDrawing = true;
					userChoice = JOptionPane.showOptionDialog(null, "Choose an option", "Options", 1, 1, null, options,
							null);
				}

				if (e.getKeyChar() == 'h' && userChoice != -1) {
					drawMode = userChoice;
				}

				if (e.getKeyChar() == 'c' && userChoice != -1) {
					colorMode = userChoice;
					colorModeChanged = true;
				}

				if (e.getKeyChar() == 'o' && userChoice != -1) {
					if (userChoice == 0) {
						dots = new Dot[dotAmount];
						for (int i = 0; i < dots.length; i++) {
							int x = new Random().nextInt(screen.width - 50);
							int y = new Random().nextInt(screen.height - 50);
							dots[i] = new Dot(x, y);
						}
					}
					if (userChoice == 1) {
						boolean validInput = false;
						String input;
						while (!validInput) {
							input = JOptionPane.showInputDialog(null,
									"Choose the amount of dots you'd like to have on screen", "Set Dot Amount", 1);
							if (input.matches("[0-9]*")) {
								dotAmount = Integer.parseInt(input);
								dots = new Dot[dotAmount];
								validInput = true;
								for (int i = 0; i < dots.length; i++) {
									int x = new Random().nextInt(screen.width - 50);
									int y = new Random().nextInt(screen.height - 50);
									dots[i] = new Dot(x, y);
								}
							}
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				if (drawMode == 1) {
					double screenDiagonal = 0;
					double distanceFromMouse = 0;
					double movementWeight = 0;
					Point mousePosition = getMousePosition();
					for (int i = 0; i < dots.length; i++) {
						screenDiagonal = Math.sqrt(Math.pow(screen.getHeight(), 2) + Math.pow(screen.getWidth(), 2));
						distanceFromMouse = Math.sqrt(Math.pow(Math.abs(mousePosition.getX() - dots[i].getX()), 2)
								+ Math.pow(Math.abs(mousePosition.getY() - dots[i].getY()), 2));
						movementWeight = Math.pow((1 - (distanceFromMouse / screenDiagonal)), 4) * 0.02;
						if (mousePosition.getX() > dots[i].getX()) {
							dots[i].setX(dots[i].getX() + (int) (distanceFromMouse * movementWeight));
						} else {
							dots[i].setX(dots[i].getX() - (int) (distanceFromMouse * movementWeight));
						}
						if (mousePosition.getY() > dots[i].getY()) {
							dots[i].setY(dots[i].getY() + (int) (distanceFromMouse * movementWeight));
						} else {
							dots[i].setY(dots[i].getY() - (int) (distanceFromMouse * movementWeight));
						}
					}
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		while (true) {
			if (drawMode == 3) {
				sleep(200);
			} else {
				sleep(50);
			}
			if (drawMode != 0 && drawMode != 1) {
				move();
			}
			contentPane.repaint();
		}
	}

	private void move() {
		for (int i = 0; i < dots.length; i++) {
			dots[i].moveX();
			dots[i].moveY();
		}
	}

	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Create a component that you can actually draw on.
	class DrawPane extends JPanel {

		private static final long serialVersionUID = 6061095142990749109L;
		private int colorChange = 0;

		public DrawPane() {
			setSize(SIZE, SIZE);
		}

		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, screen.width, screen.height);

			if (drawMode == 0) {
				drawDots(g);
			} else if (drawMode == 1) {
				drawDots(g);
			} else if (drawMode == 2) {
				drawDots(g);
			} else if (drawMode == 3) {
				for (int i = 0; i < dots.length; i++) {
					dots[i].generateRandomPosition();
					drawDots(g);
				}
			}
			if (colorChange == colorReset) {
				colorChange = 0;
			}
			colorChange++;
		}

		private void drawDots(Graphics g) {
			for (int i = 0; i < dots.length; i++) {
				g.setColor((Color) dots[i].getDotColor(colorMode));
				if (colorChange == colorReset) {
					dots[i].changeDotColor(colorMode, colorModeChanged);
					colorModeChanged = false;
				}
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
