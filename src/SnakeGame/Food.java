package SnakeGame;
import java.awt.*;

public class Food {
	
	private int x, y, width, height;
	
	public Food(int x, int y, int tileSize)
	{
		
		this.x = x;
		this.y = y;
		this.width = tileSize;
		this.height = tileSize;
		
	}
	
	public void tick()
	{
		
	}
	
	public void draw(Graphics g)
	{
		
		g.setColor(Color.RED);
		g.fillRect(x * width, y * height, width, height);
		
	}

	public int getx() {
		return x;
	}

	public void setx(int x) {
		this.x = x;
	}

	public int gety() {
		return y;
	}

	public void sety(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
