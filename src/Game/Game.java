package Game;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private int SuccessfulPasses;
	private int InterceptedPasses;

	public ArrayList<HumanPlayer> HumanPlayer;
	public ArrayList<ComputerPlayer> ComputerPlayer;
	
	
	
	public void initiatePlayers() {
		Random random = new Random();
		
		HumanPlayer = new ArrayList<HumanPlayer>();
		ComputerPlayer = new ArrayList<ComputerPlayer>();

		
		for(int i = 0; i < 5; i++) {
			int x = random.nextInt() % 1000;
			int y = random.nextInt() % 800;
			
		//	HumanPlayer newHuman = new HumanPlayer();
		}
		
		
	}
	
	public static void main(String[] args) {
		
	}

}
