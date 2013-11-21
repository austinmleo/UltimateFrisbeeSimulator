package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Game.ComputerPlayer;
import Game.Game;
import Game.HumanPlayer;
import Game.Player;

public class JUnitTests {
	private static Game game;
	
	@Before
	public void setUp(){
		game = new Game();
	}

	@Test
	public void numberOfPlayers() {
		int players = game.HumanPlayers.size() + game.ComputerPlayers.size();
		assertEquals(players,10);
	}

	@Test
	public void FiveOnEachTeam() {
		assertEquals(game.HumanPlayers.size(), 5);
		assertEquals(game.ComputerPlayers.size(), 5);
	}
	
	@Test
	public void PlayerZeroHasFrisbeeAtGameStart() {
		assertTrue(game.HumanPlayers.get(0).hasFrisbee());
	}
	
	@Test
	public void AllPlayersInDifferentPlaces() {
		ArrayList<Player> test = new ArrayList<Player>();
		
		for (HumanPlayer h :  game.HumanPlayers)
			test.add(h);
		for (ComputerPlayer c :  game.ComputerPlayers)
			test.add(c);
		
		assertTrue(test.size() > 1);
		
		for(int i = 0; i < test.size()-1; i++){
			assertTrue(test.get(i).getX() != test.get(i + 1).getX());
			assertTrue(test.get(i).getY() != test.get(i + 1).getY());
		}
	}
	
	@Test
	public void playerNoLongerHasFrisbeeAfterThrowingIt() {
		assertTrue(game.HumanPlayers.get(0).hasFrisbee());
		game.setSelectedPlayer(game.HumanPlayers.get(1));
		game.throwFrisbee(game.getSelectedPlayer());
		assertFalse(game.HumanPlayers.get(0).hasFrisbee());
	}
	
	@Test public void opposingTeamPlayersMoveToHumanTeamPlayersOnThrow() {
		game.setSelectedPlayer(game.HumanPlayers.get(1));
		
		double originalDistance = game.calcDistance(game.HumanPlayers.get(1), game.ComputerPlayers.get(1));
		
		game.throwFrisbee(game.getSelectedPlayer());

		assertTrue(game.calcDistance(game.HumanPlayers.get(1), game.ComputerPlayers.get(1)) < originalDistance);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
