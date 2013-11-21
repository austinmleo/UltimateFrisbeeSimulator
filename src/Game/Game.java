package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame{
	private int SuccessfulPasses;
	private int InterceptedPasses;
	private HumanPlayer selectedPlayer;

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
		
		addMouseListener(new PlayerClick());
		
		
		Field field = new Field();
		add(field);
		field.paintComponent(super.getGraphics());
	}
	
	public class Field extends JPanel{
		public Field() {
			setBackground(Color.GREEN);
		}
		

		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			g.setColor(Color.BLUE);
			for(HumanPlayer p : HumanPlayers) {
				g.fillOval(p.getX(), p.getY(), 25, 25);
			}
			
			g.setColor(Color.RED);
			for(ComputerPlayer p : ComputerPlayers) {
				g.fillOval(p.getX(), p.getY(), 25, 25);
			}
		}		
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
			if(i == 0){
				HumanPlayers.get(0).hasFrisbee = true;
			}
			
			
			int computerX = random.nextInt() % 100;
			int computerY = random.nextInt() % 100;
			
			ComputerPlayer newComputer = new ComputerPlayer(x + computerX, y + computerY);
			ComputerPlayers.add(newComputer);
		}
		
	}
	
	
    private class PlayerClick extends JPanel implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) { 
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) { }

		@Override
		public void mouseExited(MouseEvent arg0) { }

		@Override
		public void mousePressed(MouseEvent event) {
			Point click = event.getPoint();
			System.out.println(click);
			

			/*for(HumanPlayer player : HumanPlayers) {
				player.draw(super.getGraphics());
			}*/

		}

		@Override
		public void mouseReleased(MouseEvent arg0) { }
    }
        
    public void throwFrisbee(HumanPlayer selectedPlayer) {
    	double originalDistance = Math.sqrt(Math.pow((HumanPlayers.get(0).getX() - selectedPlayer.getX()), 2) + Math.pow((HumanPlayers.get(0).getY() - selectedPlayer.getY()), 2));
    	HumanPlayers.get(0).hasFrisbee = false;
    	selectedPlayer.hasFrisbee = true;
    }
    
    
    
	public static void main(String[] args) {
		Game game = new Game();

	}


	public HumanPlayer getSelectedPlayer() {
		return selectedPlayer;
	}


	public void setSelectedPlayer(HumanPlayer selectedPlayer) {
		this.selectedPlayer = selectedPlayer;
	}
}
