package SnakeBody;

import java.awt.Color;
import java.awt.Graphics;


public class SnakePart {

	int x, y, width, height;
	
	public SnakePart(int x, int y, int tileSize)
	{
		this.x = x;
		this.y = y;
		this.width = tileSize;
		this.height = tileSize;
		
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void tick()
	{
		
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(x * width, y * height, width, height);
		g.setColor(Color.GREEN);
		g.drawRect(x * width, y * height, width, height);
		
	}
}
