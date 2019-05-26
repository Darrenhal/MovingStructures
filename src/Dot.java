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
		if (directionSwitch == 0) {
			x -= new Random().nextInt(5) + 1;
		} else {
			x += new Random().nextInt(5) + 1;
		}
	}
	
	public void moveY() {
		int directionSwitch = new Random().nextInt(2);
		if (directionSwitch == 0) {
			y -= new Random().nextInt(5) + 1;
		} else {
			y += new Random().nextInt(5) + 1;
		}
	}
}
