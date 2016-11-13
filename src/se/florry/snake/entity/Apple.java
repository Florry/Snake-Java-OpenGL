package se.florry.snake.entity;

import se.florry.engine.material.Material;
import se.florry.engine.model.Engine;
import se.florry.snake.constants.GameConstants;
import se.florry.snake.model.Game;
import se.florry.snake.model.GameObject;
import se.florry.snake.world.World;

/*
 * Apple to eat
 */
public class Apple extends GameObject
{

	private final World world;
	private final Engine engine;

	private final Material material;

	public Apple(final World world, final Engine engine)
	{
		super(GameConstants.World.GRID_PIXEL_SIZE);
		this.world = world;
		this.engine = engine;
		this.material = new Material("apple");
		this.setTransparency(true);
	}

	@Override
	public void render()
	{
		this.material.setMaterial();
		super.render();
		this.material.disable();
	}

	@Override
	public void onDestroy(final Game game)
	{
		this.engine.playSound("apple-pickup");
		this.world.spawnNewApple();
		game.appleEaten();
	}

}
