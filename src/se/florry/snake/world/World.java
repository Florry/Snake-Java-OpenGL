package se.florry.snake.world;

import se.florry.engine.model.Engine;
import se.florry.snake.constants.GameConstants;
import se.florry.snake.entity.Apple;
import se.florry.snake.model.Game;
import se.florry.snake.model.GameObject;

public class World
{
	final private GameObject[][] grid;
	final private Game game;
	final private Engine engine;

	public World(final Game game, final Engine engine)
	{
		this.grid = new GameObject[GameConstants.World.GRID_WIDTH][GameConstants.World.GRID_HEIGHT];
		this.game = game;
		this.engine = engine;
	}

	public int getGridLength()
	{
		return this.grid.length;
	}

	public int getGridLength(final int x)
	{
		return this.grid[x].length;
	}

	public GameObject get(final int x, final int y)
	{
		return this.grid[x][y];
	}

	public GameObject[][] add(final int x, final int y, final GameObject quad)
	{
		if (this.grid[x][y] != null)
		{
			this.grid[x][y].onDestroy(this.game);
		}
		this.grid[x][y] = quad;
		return this.grid;
	}

	public GameObject[][] remove(final int x, final int y)
	{
		if (this.grid[x][y] != null)
		{
			this.grid[x][y].onDestroy(this.game);
		}
		this.grid[x][y] = null;
		return this.grid;
	}

	public void spawnNewApple()
	{
		spawnNewApple(false);
	}

	private void spawnNewApple(boolean firstTime)
	{
		if (!firstTime && checkForGameComplete())
		{
			this.game.gameComplete();
			return;
		}

		Apple apple = new Apple(this, this.engine);
		int x;
		int y;

		do
		{
			x = (int) Math.round(Math.random() * (GameConstants.World.GRID_WIDTH - 1));
			y = (int) Math.round(Math.random() * (GameConstants.World.GRID_HEIGHT - 1));
		} while (this.get(x, y) != null);

		this.add(x, y, apple);
	}

	private boolean checkForGameComplete()
	{
		boolean isFull = true;

		for (int x = 0; x < this.grid.length; x++)
		{
			for (int y = 0; y < this.grid[x].length; y++)
			{
				if (this.grid[x][y] != null)
				{
					isFull = false;
				}
			}
		}

		return isFull;
	}

	public void reset()
	{
		for (int x = 0; x < this.grid.length; x++)
		{
			for (int y = 0; y < this.grid[x].length; y++)
			{
				this.grid[x][y] = null;
			}
		}

		this.spawnNewApple(true);
	}

}
