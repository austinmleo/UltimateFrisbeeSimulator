package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame{
	private int SuccessfulPasses;
	private int InterceptedPasses;	
	public static final int SIZE = 25;
	
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
		
		private BufferedImage image;
		
		public Field() {
			try {
				image = ImageIO.read(new File("FieldLayout.png"));
			} catch (IOException ex) {
				ex.getLocalizedMessage();
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			
			g.drawImage(image, 0,  0, null);
			
			g.setColor(Color.BLUE);
			for(HumanPlayer p : HumanPlayers) {
				g.fillOval(p.getX(), p.getY(), SIZE, SIZE);
			}
			
			g.setColor(Color.RED);
			for(ComputerPlayer p : ComputerPlayers) {
				g.fillOval(p.getX(), p.getY(), SIZE, SIZE);
			}
		}		
	}

	
	public void initiatePlayers() {
		
		Random random = new Random();

		for(int i = 0; i < 5; i++) {
			int x = random.nextInt() % 1001;
			int y = random.nextInt() % 487;
			
			if(x < 0)
				x = -x;
			if(y < 1)
				y = -y;
				
			HumanPlayer newHuman = new HumanPlayer(x + 100, y + 100);
			HumanPlayers.add(newHuman);
			if(i == 0){
				HumanPlayers.get(0).hasFrisbee = true;
			}
			
			//Need code to be sure the computers don't spawn off the field.
			
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
		}

		@Override
		public void mouseReleased(MouseEvent arg0) { }
    }
        
    public void throwFrisbee(HumanPlayer selectedPlayer) {
    	Random generator = new Random();
    	int enemyMoveAmount = 0;
    	double distance = calcDistance(HumanPlayers.get(0), selectedPlayer);
    	HumanPlayers.get(0).hasFrisbee = false;
    	
    	if (distance > 800)
    		enemyMoveAmount = 90;
    	else if (600 < distance && distance < 800) 
    		enemyMoveAmount = 70;
       	else if (400 < distance && distance < 600) 
    		enemyMoveAmount = 50;
       	else if (100 < distance && distance < 400) 
    		enemyMoveAmount = 30;
       	else if (distance < 100) 
    		enemyMoveAmount = 10;
    	
    	for (int i = 0; i < 5; i++) {
    		int moveAmountTemp = enemyMoveAmount;
    		int xOryFirst = generator.nextInt(1); //0 for x first.
    		int yDist = HumanPlayers.get(i).getY() - ComputerPlayers.get(i).getY();
    		int xDist = HumanPlayers.get(i).getX() - ComputerPlayers.get(i).getX();

    		if (xOryFirst == 0) { //x picked first
    			if (xDist > 0) { 
    				if (xDist > moveAmountTemp) {
    					ComputerPlayers.get(i).setX(ComputerPlayers.get(i).getX() + moveAmountTemp);
    				} else {
    					moveAmountTemp = moveAmountTemp - xDist;
    					ComputerPlayers.get(i).setX(HumanPlayers.get(i).getX());
    					if (yDist > 0) {
    						if(moveAmountTemp != 0 && yDist > moveAmountTemp) {
    							ComputerPlayers.get(i).setY(ComputerPlayers.get(i).getY() + moveAmountTemp);
    						} else {
    							ComputerPlayers.get(i).setY(HumanPlayers.get(i).getY());
    						}
    					} else {
    						if(moveAmountTemp != 0 && Math.abs(yDist) > moveAmountTemp)
    							ComputerPlayers.get(i).setY(ComputerPlayers.get(i).getY() - moveAmountTemp);
    						else {
    							ComputerPlayers.get(i).setY(HumanPlayers.get(i).getY());
    						}
    					}
    				}
    			} else {
    				if(Math.abs(xDist) > moveAmountTemp) {
    					ComputerPlayers.get(i).setX(ComputerPlayers.get(i).getX() - moveAmountTemp);
    				}
    				else {
    					moveAmountTemp = moveAmountTemp - Math.abs(xDist);
    					ComputerPlayers.get(i).setX(HumanPlayers.get(i).getX());
    					if (yDist > 0) {
    						if(moveAmountTemp != 0 && yDist > moveAmountTemp) {
    							ComputerPlayers.get(i).setY(ComputerPlayers.get(i).getY() + moveAmountTemp);
    						} else {
    							ComputerPlayers.get(i).setY(HumanPlayers.get(i).getY());
    						}
    					} else {
    						if(moveAmountTemp != 0 && Math.abs(yDist) > moveAmountTemp)
    							ComputerPlayers.get(i).setY(ComputerPlayers.get(i).getY() - moveAmountTemp);
    						else {
    							ComputerPlayers.get(i).setY(HumanPlayers.get(i).getY());
    						}
    					}
    				}
    			}

    		} else { // y picked first.

    		}
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//selectedPlayer.hasFrisbee = true;
    }
    
    public double calcDistance(HumanPlayer thrower, HumanPlayer catcher) {
    	return Math.sqrt(Math.pow((thrower.getX() - catcher.getX()), 2) + Math.pow((thrower.getY() - catcher.getY()), 2));
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
