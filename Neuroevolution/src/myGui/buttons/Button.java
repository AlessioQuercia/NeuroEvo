package myGui.buttons;

import java.awt.Rectangle;

import myGui.render.Texture;

public abstract class Button
{
	private Rectangle rectangle;
	private Texture texture;
	
	public Button(int x, int y, int width, int height, Texture texture)
	{
		this.rectangle = new Rectangle(x, y, width, height);
		this.texture = texture;
	}
	public Rectangle getRectangle()
	{
		return rectangle;
	}
	public boolean intersects(float x, float y)
	{
		return rectangle.contains(x, y);
	}
//	public void render(//DA VEDERE)
//	{
//		//DA VEDERE
//	}
	public void dispose()
	{
		texture.dispose();
	}
}
