package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Game extends JFrame{
	
	JFrame topFrame = new JFrame();
	JButton passButton;
	JButton newButton;
	String resultString;
	Field field = new Field();
	
	
	
	
	private int SuccessfulPasses;
	private int InterceptedPasses;	
	public static final int SIZE = 25;
	private JTextArea result;
	
	private HumanPlayer selectedPlayer;

	public ArrayList<HumanPlayer> HumanPlayers;
	public ArrayList<ComputerPlayer> ComputerPlayers;

	
	public Game() {
		HumanPlayers = new ArrayList<HumanPlayer>();
		ComputerPlayers = new ArrayList<ComputerPlayer>();
		
		SuccessfulPasses = 0;
		InterceptedPasses = 0;
		
		initiatePlayers();
		
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topFrame.setTitle("Ultimate Frisbee Simulator v2.67   2nd Edition");
		topFrame.setSize(1230, 625);
		topFrame.setResizable(true);
		topFrame.setVisible(true);
		setSelectedPlayer(null);
		
		topFrame.addMouseListener(new PlayerClick());
		
		topFrame.setLayout(new BorderLayout());
		//topFrame.add(field, BorderLayout.CENTER);
		//Field field = new Field();
		topFrame.add(field, BorderLayout.CENTER);
		//field.paintComponent(super.getGraphics());
	
		JPanel buttonPanel = new JPanel();
		passButton = new JButton("Finalize Pass");
		passButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedPlayer != null){
					throwFrisbee(selectedPlayer);
				}
			}
		});
		newButton = new JButton("New Play");
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(selectedPlayer != null){
					topFrame.setVisible(false);
					Game game = new Game();
				}
			}
		});
		buttonPanel.add(passButton);
		buttonPanel.add(newButton);
		topFrame.add(buttonPanel, BorderLayout.SOUTH);
		
		JPanel resultPanel = new JPanel();
		JLabel resultLabel = new JLabel("Pass Result");
		result = new JTextArea(1,10);
		result.setBackground(Color.lightGray);
		result.setEditable(false);
		result.setLineWrap(true);
		resultString = "Not Thrown";
		resultPanel.add(resultLabel);
		resultPanel.add(result);
		topFrame.add(resultPanel, BorderLayout.EAST);
		
		updateDisplay();
	
	}
	
	public void updateDisplay(){
		result.setText(resultString);
		repaint();
	}
	
	
	public class Field extends JPanel{
		
		// Cyndi said to try placing the field imagine into a panel
		// and the placing that panel into the frame layout.center
		
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
			
			
			for(HumanPlayer p : HumanPlayers) {
				if (p == selectedPlayer){
					g.setColor(Color.GREEN);
					g.fillOval(p.getX(), p.getY(), SIZE, SIZE);
				}else{
					g.setColor(Color.BLUE);
					g.fillOval(p.getX(), p.getY(), SIZE, SIZE);
					if (p == HumanPlayers.get(0)){
						g.setColor(Color.YELLOW);
						g.drawString("X", p.getX()+10, p.getY()+17);
						//g.fillOval(p.getX()-5, p.getY()-5, SIZE-10, SIZE-10);
					}
				}
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
				
			HumanPlayer newHuman = new HumanPlayer(x, y);
			HumanPlayers.add(newHuman);
			
			System.out.println(newHuman.getX() + " " + newHuman.getY());
			
			if(i == 0){
				HumanPlayers.get(0).hasFrisbee = true;
			}
			
			Boolean computerPlayerAcceptable = false;

			while(!computerPlayerAcceptable) {
				
				int computerX = random.nextInt() % 100;
				int computerY = random.nextInt() % 100;
				
				if(x + computerX < 1001 && x + computerX > 0) {
					x = x + computerX;
				} else
					x = x - computerX;
				
				if(y + computerY < 487 && x + computerY > 0) {
					y = y + computerY;
				} else
					y = y - computerY;
				
				if (x > 0 && x < 1001 && y > 0 && y < 487) {
					computerPlayerAcceptable = true;
				}
			}
			
			ComputerPlayer newComputer = new ComputerPlayer(x, y);
			ComputerPlayers.add(newComputer);
		}	
	}
	
	
    private class PlayerClick extends JPanel implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) { 
			Point click = arg0.getPoint();
			//System.out.println("Getting location");
			for (HumanPlayer p : HumanPlayers){
				if(calcDistance(p, click) < 50){
					if(p != HumanPlayers.get(0)){
						selectedPlayer = p;
						updateScreen(topFrame);
					}
				}
			}
			//System.out.println("Selected player is" + getSelectedPlayer());
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
    	Boolean interception = false;
    	double distance = calcDistance(HumanPlayers.get(0), selectedPlayer);
    	HumanPlayers.get(0).hasFrisbee = false;
    	
    	if (distance > 800)
    		enemyMoveAmount = 100;
    	else if (600 < distance && distance < 800) 
    		enemyMoveAmount = 90;
       	else if (400 < distance && distance < 600) 
    		enemyMoveAmount = 65;
       	else if (100 < distance && distance < 400) 
    		enemyMoveAmount = 40;
       	else if (distance < 100) 
    		enemyMoveAmount = 15;
    	
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
    				} else {
    					moveAmountTemp = moveAmountTemp - Math.abs(xDist);
    					ComputerPlayers.get(i).setX(HumanPlayers.get(i).getX());
    					if (yDist > 0) {
    						if(moveAmountTemp != 0 && yDist > moveAmountTemp) {
    							ComputerPlayers.get(i).setY(ComputerPlayers.get(i).getY() + moveAmountTemp);
    						} else {
    							ComputerPlayers.get(i).setY(HumanPlayers.get(i).getY());
    						}
    					} else {
    						if(moveAmountTemp != 0 && Math.abs(yDist) > moveAmountTemp) {
    							ComputerPlayers.get(i).setY(ComputerPlayers.get(i).getY() - moveAmountTemp);
    						} else {
    							ComputerPlayers.get(i).setY(HumanPlayers.get(i).getY());
    						}
    					}
    				}
    			}

    		} else { // y picked first.
    			if (yDist > 0) { 
    				if (yDist > moveAmountTemp) {
    					ComputerPlayers.get(i).setY(ComputerPlayers.get(i).getY() + moveAmountTemp);
    				} else {
    					moveAmountTemp = moveAmountTemp - yDist;
    					ComputerPlayers.get(i).setY(HumanPlayers.get(i).getY());
    					if (xDist > 0) {
    						if(moveAmountTemp != 0 && xDist > moveAmountTemp) {
    							ComputerPlayers.get(i).setX(ComputerPlayers.get(i).getX() + moveAmountTemp);
    						} else {
    							ComputerPlayers.get(i).setX(HumanPlayers.get(i).getX());
    						}
    					} else {
    						if(moveAmountTemp != 0 && Math.abs(xDist) > moveAmountTemp)
    							ComputerPlayers.get(i).setX(ComputerPlayers.get(i).getX() - moveAmountTemp);
    						else {
    							ComputerPlayers.get(i).setX(HumanPlayers.get(i).getX());
    						}
    					}
    				}
    			} else {
    				if(Math.abs(yDist) > moveAmountTemp) {
    					ComputerPlayers.get(i).setY(ComputerPlayers.get(i).getY() - moveAmountTemp);
    				} else {
    					moveAmountTemp = moveAmountTemp - Math.abs(yDist);
    					ComputerPlayers.get(i).setY(HumanPlayers.get(i).getY());
    					if (xDist > 0) {
    						if(moveAmountTemp != 0 && xDist > moveAmountTemp) {
    							ComputerPlayers.get(i).setX(ComputerPlayers.get(i).getX() + moveAmountTemp);
    						} else {
    							ComputerPlayers.get(i).setX(HumanPlayers.get(i).getX());
    						}
    					} else {
    						if(moveAmountTemp != 0 && Math.abs(xDist) > moveAmountTemp) {
    							ComputerPlayers.get(i).setX(ComputerPlayers.get(i).getX() - moveAmountTemp);
    						} else {
    							ComputerPlayers.get(i).setX(HumanPlayers.get(i).getX());
    						}
    					}
    				}
    			}
    		}
    	}
    	
    	for (int i = 0; i < 5; i++) {
    		double dist = calcDistance(selectedPlayer, ComputerPlayers.get(i));
    		if (dist < 10){
    			interception = true;
    			System.out.println("Intercepted");
    			resultString = "Intercepted";
    		}
    	}
    
    	if (!interception) {
    		selectedPlayer.hasFrisbee = true;
    		System.out.println("Not Intercepted");
    		resultString = "Not Intercepted";
    	}
    	System.out.println("Frisbee thrown");
    	updateDisplay();
    	updateScreen(topFrame);
    	
    }
    
    public double calcDistance(HumanPlayer thrower, HumanPlayer catcher) {
    	return Math.sqrt(Math.pow((thrower.getX() - catcher.getX()), 2) + Math.pow((thrower.getY() - catcher.getY()), 2));
    }
    
    public double calcDistance(HumanPlayer thrower, ComputerPlayer catcher) {
    	return Math.sqrt(Math.pow((thrower.getX() - catcher.getX()), 2) + Math.pow((thrower.getY() - catcher.getY()), 2));
    }
    
    public double calcDistance(HumanPlayer thrower, Point p) {
    	return Math.sqrt(Math.pow((thrower.getX() - p.getX()), 2) + Math.pow((thrower.getY() - p.getY()), 2));
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
	
	public void updateScreen(JFrame frame) {
		frame.repaint();
	}
}
