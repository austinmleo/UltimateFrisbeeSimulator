package Game;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Player player = new Player();
		Player player2 = new Player(1, 2);
		System.out.println(player.hasFrisbee());
		System.out.println(player2.hasFrisbee());
	}

}
