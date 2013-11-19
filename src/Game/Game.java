package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame{
	private int SuccessfulPasses;
	private int InterceptedPasses;

	public ArrayList<HumanPlayer> HumanPlayers;
	public ArrayList<ComputerPlayer> ComputerPlayers;
	
	
	public Game() {
		HumanPlayers = new ArrayList<HumanPlayer>();
		ComputerPlayers = new ArrayList<ComputerPlayer>();
		
		SuccessfulPasses = 0;
		InterceptedPasses = 0;
		
		initiatePlayers();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ultimate Frisbee Simulator v2.67   2nd Edition");
		setSize(1000, 600);
		setResizable(false);
		setVisible(true);
	}
	
	
	public void initiatePlayers() {
		
		Random random = new Random();

		for(int i = 0; i < 5; i++) {
			int x = random.nextInt() % 800;
			int y = random.nextInt() % 400;
			
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
	
	
    private class PlayerClick extends JPanel implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) { }

		@Override
		public void mouseEntered(MouseEvent arg0) { }

		@Override
		public void mouseExited(MouseEvent arg0) { }

		@Override
		public void mousePressed(MouseEvent arg0) {
			System.out.println(arg0.getX() + ", " + arg0.getY());
		}

		@Override
		public void mouseReleased(MouseEvent arg0) { }
    }
        
    public void throwFrisbee() {
    	// TODO Auto-generated method stub
    	
    }
    
    
    
	public static void main(String[] args) {
		Game game = new Game();
		
		
	}
}
