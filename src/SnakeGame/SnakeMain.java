package SnakeGame;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class SnakeMain
{
	public JFrame window = new JFrame();
	
	public static SnakePanel panel = new SnakePanel();
	
	public static int xLocation, yLocation;
	
	public static final int X_DIMENSION = 705, Y_DIMENSION = 600;
	
	public static SnakeMain snake;
	
	public SnakeMain()
	{
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		
		xLocation = screen.width / 2 - X_DIMENSION / 2;
		yLocation = screen.height / 2 - Y_DIMENSION / 2;
		
		
		window.setLocation(xLocation , yLocation);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(panel);
		window.setResizable(true);
		window.pack();
		window.setVisible(true);
		

	}
	public static void main(String[] args) 
	{

		snake = new SnakeMain();
		
		
	}

}
