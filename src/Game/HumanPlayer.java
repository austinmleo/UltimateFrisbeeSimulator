package Game;

import java.awt.Color;
import java.awt.Graphics;

public class HumanPlayer extends Player {
	
	public HumanPlayer() {
		super();
	}
	
	public HumanPlayer(int x, int y) {
		super(x, y);
	}
	
	public void pass(Player player) {
		if (hasFrisbee()) {
			player.setFrisbee(true);
			this.setFrisbee(false);
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(this.x, this.y, 25, 25);
	}
}
