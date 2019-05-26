import java.util.Random;

public class Dot {

	private int x, y;
	
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void moveX() {
		int directionSwitch = new Random().nextInt(2);
		if ((x - 5) <= 0) {
			x += new Random().nextInt(2) + 1;
		} else if (directionSwitch == 0) {
			x -= new Random().nextInt(2) + 1;
		} else if ((x + 5) >= Main.SIZE) {
			x -= new Random().nextInt(2) + 1;
		} else {
			x += new Random().nextInt(2) + 1;
		}
	}
	
	public void moveY() {
		int directionSwitch = new Random().nextInt(2);
		if ((y - 5) <= 0) {
			y += new Random().nextInt(2) + 1;
		} else if (directionSwitch == 0) {
			y -= new Random().nextInt(2) + 1;
		} else if ((y + 5) >= Main.SIZE) {
			y -= new Random().nextInt(2) + 1;
		} else {
			y += new Random().nextInt(2) + 1;
		}
	}
}
