package se.florry.snake.entity;

import org.lwjgl.util.vector.Vector2f;

import se.florry.engine.material.Material;
import se.florry.snake.constants.GameConstants;
import se.florry.snake.model.GameObject;

/*
 * Parts of the snake body being controlled by the Player class.
 */
public class SnakePart extends GameObject
{

	protected Material currentMaterial;
	public final Vector2f worldPosition;
	private boolean initialized;

	public SnakePart()
	{
		super(GameConstants.World.GRID_PIXEL_SIZE);
		this.currentMaterial = new Material("pattern01");
		this.worldPosition = new Vector2f();

		this.color.r = 162;
		this.color.g = 195;
		this.color.b = 222;
	}

	@Override
	public void render()
	{
		this.currentMaterial.setMaterial();
		super.render();
		this.currentMaterial.disable();
	}

	public void initialize()
	{
		this.initialized = true;
	}

	public boolean isInitialized()
	{
		return this.initialized;
	}

}
