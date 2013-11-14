package Game;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	private int SuccessfulPasses;
	private int InterceptedPasses;

	public ArrayList<HumanPlayer> HumanPlayers;
	public ArrayList<ComputerPlayer> ComputerPlayers;
	
	
	public Game() {
		HumanPlayers = new ArrayList<HumanPlayer>();
		ComputerPlayers = new ArrayList<ComputerPlayer>();
		
		SuccessfulPasses = 0;
		InterceptedPasses = 0;
	}
	
	
	public void initiatePlayers() {
		Random random = new Random();

		for(int i = 0; i < 5; i++) {
			int x = random.nextInt() % 800;
			int y = random.nextInt() % 600;
			
			if(x < 0)
				x = -x;
			if(y < 1)
				y = -y;
				
			HumanPlayer newHuman = new HumanPlayer(x + 100, y + 100);
			HumanPlayers.add(newHuman);
			
			
			int computerX = random.nextInt() % 100;
			int computerY = random.nextInt() % 100;
			
			ComputerPlayer newComputer = new ComputerPlayer(x + computerX, y + computerY);
			ComputerPlayers.add(newComputer);
		}
		
	}
	
	public static void main(String[] args) {
		
		
	}

}
