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
		assertEquals(players,11);
	}

	@Test
	public void FiveOnEachTeam() {
		assertEquals(game.HumanPlayers.size(), 6);
		assertEquals(game.ComputerPlayers.size(), 4);
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
			assertTrue(test.get(i).getX() == test.get(i + 1).getX());
			assertTrue(test.get(i).getY() == test.get(i + 1).getY());
		}
	}
	
	@Test
	public void playerNoLongerHasFrisbeeAfterThrowingIt() {
		assertTrue(game.HumanPlayers.get(0).hasFrisbee());
		game.throwFrisbee();
		assertFalse(game.HumanPlayers.get(0).hasFrisbee());
	}
	
	@Test public void opposingTeamPlayersMoveToHuamnTeamPlayersOnThrow() {
		game.HumanPlayers.remove(2);
		game.HumanPlayers.remove(2);
		game.HumanPlayers.remove(2);
		game.ComputerPlayers.remove(1);
		game.ComputerPlayers.remove(1);
		game.ComputerPlayers.remove(1);
		game.ComputerPlayers.remove(1);
		
		game.HumanPlayers.get(0).setX(0);
		game.HumanPlayers.get(0).setY(0);
		
		game.HumanPlayers.get(1).setX(100);
		game.HumanPlayers.get(1).setY(100);
		
		game.ComputerPlayers.get(0).setX(200);
		game.ComputerPlayers.get(0).setY(200);
		
		double originalDistance = Math.sqrt(Math.pow((game.HumanPlayers.get(1).getX() - game.ComputerPlayers.get(0).getX()), 2) + Math.pow((game.HumanPlayers.get(1).getY() - game.ComputerPlayers.get(0).getY()), 2));
		
		game.throwFrisbee();

		assertTrue(Math.sqrt(Math.pow((game.HumanPlayers.get(1).getX() - game.ComputerPlayers.get(0).getX()), 2) + Math.pow((game.HumanPlayers.get(1).getY() - game.ComputerPlayers.get(0).getY()), 2)) < originalDistance);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
