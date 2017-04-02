package SnakeGame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import SnakeBody.SnakePart;

public class SnakePanel extends JPanel implements Runnable 
{
	private static final long serialVersionUID = 1L;

	public int xCoor = 20, yCoor = 20, snakeSize = 1, ticks = 0, tileSize = 15, xCoorF, yCoorF,
				scoreNum = 0;

	public static boolean up = false, down = false, left = false, right = false;
	
	private Thread thread1;
	
	private boolean running = false;
	
	@SuppressWarnings("unused")
	private static int myColor = 200;
	
	private SnakePart part;
	
	private ArrayList<SnakePart> snakeList;
	
	private ArrayList<Food> foodList;
	
	private SnakeKeyListen keyListen;
	
	private Food f;
	
	public Random random = new Random();
	
	public JLabel gameOver, score;
	
	public JButton restart;
	
	public SnakePanel()
	{
		setFocusable(true);
		keyListen = new SnakeKeyListen();
		gameOver = new JLabel("Game Over!");
		gameOver.setForeground(Color.YELLOW);
		gameOver.setFont((new Font("Times New Roman", Font.PLAIN, 18)));
		addKeyListener(keyListen);
		score = new JLabel("Score: " + scoreNum);
		score.setForeground(Color.YELLOW);
		score.setLocation(30, 550);
		score.setFont(new Font("Courier", Font.BOLD, 20));
		add(score);
		snakeList = new ArrayList<SnakePart>();
		foodList = new ArrayList<Food>();		
		setPreferredSize(new Dimension(SnakeMain.X_DIMENSION, SnakeMain.Y_DIMENSION));
		start();
	}
	protected void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, SnakeMain.X_DIMENSION, SnakeMain.Y_DIMENSION);
		super.paintComponent(g);
		g.setColor(new Color(0, 0, 255));
		g.fillRect(0, 0, SnakeMain.X_DIMENSION, SnakeMain.Y_DIMENSION);
		
		for(int i = 0; i < snakeList.size(); i++)
		{
			snakeList.get(i).draw(g);
		}
		for(int i = 0; i < foodList.size(); i++)
		{
			foodList.get(i).draw(g);
		}
		
	}
	
	public void tick()
	{
		
		if (xCoor > SnakeMain.X_DIMENSION / 15 - 1|| yCoor > SnakeMain.Y_DIMENSION / 15 - 1
			|| xCoor < 0 || yCoor < 0)
		{
			stop();
		}
		for (int i = 0; i < snakeList.size(); i++)
			if (xCoor == snakeList.get(i).getX() && yCoor == snakeList.get(i).getY())
			{
				if (i != snakeList.size() - 1)
				{
					stop();
				}	
			}
		
		if (snakeList.size() == 0)
		{
			part = new SnakePart(xCoor, yCoor, this.tileSize);
			snakeList.add(part);
		}
		
		if (foodList.size() == 0)
		{
			f = new Food(xCoorF, yCoorF, this.tileSize);
			f.setx(random.nextInt(SnakeMain.X_DIMENSION / this.tileSize));
			f.sety(random.nextInt(SnakeMain.Y_DIMENSION / this.tileSize));
			foodList.add(f);
		}
		for (int i = 0; i < foodList.size(); i++)
		{
			int increase = 3;
			//trouble here
			if (xCoor == foodList.get(i).getx() && yCoor== foodList.get(i).gety())
			{
				if (this.snakeSize == 0)
				{
					snakeSize+= increase + 1;
				}
				else
				{
					snakeSize+= increase;
				}
				foodList.remove(i);
				i--;
				
				this.scoreNum+=3;
				score.setText("Score: " + scoreNum);
			}
		}
		
		
		
		ticks++;
		
		if (ticks > 450000)
		{
			if(right) xCoor++;
			if(left) xCoor--;
			if(up) yCoor--;
			if(down) yCoor++;
			
			ticks = 0;
			
			part = new SnakePart(xCoor, yCoor, this.tileSize);
			snakeList.add(part);
			
			if (snakeList.size() > snakeSize)
			{
				snakeList.remove(0);
			}
			
		}
		
		
	}
	
	public void start()
	{
		
		running = true;
		thread1 = new Thread(this, "Main Loop");
		thread1.start();
				
	}
	
	@SuppressWarnings("deprecation")
	public void stop()
	{
		running = false;
		new BorderLayout();
		this.add(gameOver);
		this.revalidate();
		thread1.stop();
		
		
	}
	
	public void run()
	{
		while (running)
		{
			tick();
			repaint();
		}
	}
	
	private class SnakeKeyListen implements KeyListener{
	

		@SuppressWarnings("static-access")
		public void keyPressed(KeyEvent event) {
			int keyCode = event.getKeyCode();
			
			if (keyCode == event.VK_LEFT && !SnakePanel.right)
			{
				SnakePanel.left = true;
				SnakePanel.up = false;
				SnakePanel.down = false;
			}
			if (keyCode == event.VK_RIGHT && !SnakePanel.left)
			{
				SnakePanel.right = true;
				SnakePanel.up = false;
				SnakePanel.down = false;
			}
			if (keyCode == event.VK_UP && !SnakePanel.down)
			{
				SnakePanel.up = true;
				SnakePanel.right = false;
				SnakePanel.left = false;
			}
			if (keyCode == event.VK_DOWN && !SnakePanel.up)
			{
				SnakePanel.down = true;
				SnakePanel.right = false;
				SnakePanel.left = false;
			}
			if (keyCode == event.VK_ENTER && running == false)
			{
				
				
					SnakeMain.panel.snakeSize = 0;
					SnakeMain.panel.remove(gameOver);
					scoreNum = 0;
					score.setText("Score: " + scoreNum);
					snakeList.remove(snakeList.size() - 1);
					
					for (int i = 0; i <= snakeList.size() - 1; i++)
					{
						snakeList.remove(i);
						i--;
					}
					
					foodList.clear();
					SnakeMain.panel.revalidate();
					SnakeMain.panel.repaint();
					xCoor = 20;
					yCoor = 20;
					start();
				
			}
		}


		public void keyReleased(KeyEvent arg0) {
			// not used
			
		}


		public void keyTyped(KeyEvent arg0) {
			// not used
			
		}

	}

}

