import java.awt.Color;
import java.util.Random;

public class Dot {

	private int x, y;
	private Color dotColor;
	private int r, g, b;
	private int red, green, blue;
	private final int colorModifier = 51;

	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
		r = g = b = 0;
		red = green = blue = 0;
		dotColor = new Color(r, g, b);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void generateRandomPosition() {
		x = new Random().nextInt(Main.SIZE - 50);
		y = new Random().nextInt(Main.SIZE - 50);
	}

	public void moveX() {
		int directionSwitch = new Random().nextInt(2);
		if ((x - 5) <= 0) {
			x += new Random().nextInt(3) + 1;
		} else if (directionSwitch == 0) {
			x -= new Random().nextInt(3) + 1;
		} else if ((x + 5) >= Main.SIZE) {
			x -= new Random().nextInt(3) + 1;
		} else {
			x += new Random().nextInt(3) + 1;
		}
	}

	public void moveY() {
		int directionSwitch = new Random().nextInt(2);
		if ((y - 5) <= 0) {
			y += new Random().nextInt(3) + 1;
		} else if (directionSwitch == 0) {
			y -= new Random().nextInt(3) + 1;
		} else if ((y + 5) >= Main.SIZE) {
			y -= new Random().nextInt(3) + 1;
		} else {
			y += new Random().nextInt(3) + 1;
		}
	}

	public void changeDotColor(int colorMode, boolean modeChanged) {
		if (modeChanged) {
			r = g = b = red = green = blue = 0;
		} else if (colorMode == 0) {
			int redUp = 200 - r;
			int greenUp = 200 - g;
			int blueUp = 200 - b;

			if (redUp != 0) {
				r += new Random().nextInt(redUp + 1);
			} else {
				r = 0;
			}

			if (greenUp != 0) {
				g += new Random().nextInt(greenUp + 1);
			} else {
				g = 0;
			}

			if (blueUp != 0) {
				b += new Random().nextInt(blueUp + 1);
			} else {
				b = 0;
			}
		} else if (colorMode == 1) {
			if (blue == 255) {
				blue = 0;
				if (green == 255) {
					green = 0;
					if (red == 255) {
						red = 0;
					} else {
						red += colorModifier;
					}
				} else {
					green += colorModifier;
				}
			} else {
				blue += colorModifier;
			}
		}
	}

	public Color getDotColor(int colorMode) {
		if (colorMode == 0) {
			dotColor = new Color(r, g, b);
		} else {
			dotColor = new Color(red, green, blue);
		}
		
		return dotColor;
	}
}
