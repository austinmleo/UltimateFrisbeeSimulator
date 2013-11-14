package Game;

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
}
