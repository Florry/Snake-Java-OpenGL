package se.florry.snake.entity;

import org.lwjgl.util.vector.Vector2f;

import se.florry.engine.material.Material;

public class SnakeHead extends SnakePart
{

	private Material up;
	private Material down;
	private Material left;
	private Material right;

	public SnakeHead()
	{
		super();
		this.up = new Material("snake-head-up");
		this.down = new Material("snake-head-down");
		this.left = new Material("snake-head-left");
		this.right = new Material("snake-head-right");
		this.currentMaterial = left;
		this.setTransparency(true);
	}

	public void setDirection(Vector2f direction)
	{
		if (direction.y > 0)
		{
			this.currentMaterial = down;
		} else if (direction.y < 0)
		{
			this.currentMaterial = up;
		} else if (direction.x > 0)
		{
			this.currentMaterial = right;
		} else if (direction.x < 0)
		{
			this.currentMaterial = left;
		}
	}

}
