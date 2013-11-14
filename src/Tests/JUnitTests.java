package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Game.Game;

public class JUnitTests {
	private static Game game;
	
	@BeforeClass
	public void setUp(){
		game = new Game();
	}

	@Test
	public void numberOfPlayers() {
		int players = game.HumanPlayer.size() + game.ComputerPlayer.size();
		assertEquals(players,10);
	}

	@Test
	public void FiveOnEachTeam() {
		assertEquals(game.HumanPlayer.size(), 5);
		assertEquals(game.ComputerPlayer.size(), 5);
	}
	
	@Test
	public void PlayerHasFrisbee() {
		
	}
	
	@Test
	public void AllPlayersInDifferentPlaces() {
		
	}
	
	
}
