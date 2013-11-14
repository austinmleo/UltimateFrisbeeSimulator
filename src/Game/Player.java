package Game;

public class Player {
	private int x;
	private int y;
	private boolean hasFrisbee;
	
	
	public Player() {
		x = 0;
		y = 0;
		hasFrisbee = true;
	}
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean hasFrisbee() {
		return hasFrisbee;
	}

	public void setFrisbee(boolean hasFrisbee) {
		this.hasFrisbee = hasFrisbee;
	}


	
}
