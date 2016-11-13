package se.florry.snake.entity;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector2f;

import se.florry.engine.input.Input;
import se.florry.snake.constants.GameConstants;
import se.florry.snake.model.Game;
import se.florry.snake.model.GameObject;
import se.florry.snake.world.World;

public class Player
{

	final private Input input;
	final private World world;
	final private Game game;

	final private Vector2f position;
	final private Vector2f travelingDirection;
	final private List<SnakePart> body;

	boolean locked = false;

	public Player(final Input input, final World world, final Game game)
	{
		this.input = input;
		this.world = world;
		this.game = game;

		this.body = new ArrayList<>();
		this.position = new Vector2f();
		this.travelingDirection = new Vector2f();

		this.init();
		this.reset();
	}

	/*
	 * Sets the input handlers for the player
	 */
	private void init()
	{
		this.input.press(GLFW.GLFW_KEY_LEFT, pressed ->
		{
			this.setTravelingDirection(new Vector2f(-1, 0));
		});

		this.input.press(GLFW.GLFW_KEY_RIGHT, pressed ->
		{
			this.setTravelingDirection(new Vector2f(1, 0));
		});

		this.input.press(GLFW.GLFW_KEY_UP, pressed ->
		{
			this.setTravelingDirection(new Vector2f(0, -1));
		});

		this.input.press(GLFW.GLFW_KEY_DOWN, pressed ->
		{
			this.setTravelingDirection(new Vector2f(0, 1));
		});
	}

	/*
	 * Sets the direction in which the snake will travel. Makes sure the snake
	 * can't turn back on itself. e.g. traveling left you can't turn to the
	 * right without making a turn.
	 */
	private void setTravelingDirection(final Vector2f direction)
	{
		if (this.body.size() > 1)
		{
			if (direction.x != 0 && direction.x == -this.travelingDirection.x)
			{
				return;
			}
		}
		if (direction.y != 0 && direction.y == -this.travelingDirection.y)
		{
			return;
		}

		this.travelingDirection.x = direction.x;
		this.travelingDirection.y = direction.y;
	}

	/*
	 * Carries out the actual movement of the snake. Takes in the direction it
	 * is traveling, checks if you are about to leave the screen, adjusts the
	 * postion accordingly and sets the position. Then the Game.processWorld
	 * method takes care of the rest. If we collide with a snake part it calls
	 * game over.
	 */
	public Player move(final Vector2f direction)
	{
		final Vector2f moveToPosition = new Vector2f();

		if (this.body.get(0).worldPosition.x + direction.x > GameConstants.World.GRID_WIDTH - 1)
		{
			moveToPosition.x = 0;
		} else if (this.body.get(0).worldPosition.x + direction.x < 0)
		{
			moveToPosition.x = GameConstants.World.GRID_WIDTH - 1;
		} else
		{
			moveToPosition.x = this.body.get(0).worldPosition.x + direction.x;
		}

		if (this.body.get(0).worldPosition.y + direction.y > GameConstants.World.GRID_HEIGHT - 1)
		{
			moveToPosition.y = 0;
		} else if (this.body.get(0).worldPosition.y + direction.y < 0)
		{
			moveToPosition.y = GameConstants.World.GRID_HEIGHT - 1;
		} else
		{
			moveToPosition.y = this.body.get(0).worldPosition.y + direction.y;
		}

		if (this.isValidMove(moveToPosition))
		{
			this.position.x = moveToPosition.x;
			this.position.y = moveToPosition.y;
		} else
		{
			this.game.gameOver();
		}

		return this;
	}

	/*
	 * Makes sure where the snake is headed is not occupied by anything else,
	 * with the exception of the apple. Since we check if it is a valid move
	 * before we move the rest of the body we have to make sure we don't collide
	 * with the last part of the snake before it has been moved.
	 */
	private boolean isValidMove(final Vector2f moveTo)
	{
		final GameObject occupant = this.world.get((int) moveTo.x, (int) moveTo.y);

		if (occupant != null && !(occupant instanceof Apple) && !occupant.equals(this.body.get(this.body.size() - 1)))
		{
			return false;
		} else
		{
			return true;
		}
	}

	/*
	 * Adds another SnakePart to the body array, extending the body of the
	 * snake.
	 */
	public Player extendBody()
	{
		final SnakePart part = new SnakePart();
		part.worldPosition.x = 0;
		part.worldPosition.y = 0;

		this.body.add(part);

		return this;
	}

	public void stop()
	{
		this.travelingDirection.x = 0;
		this.travelingDirection.y = 0;
	}

	/*
	 * Updates the position of all of the snake. Loops through the body list
	 * from the end to the beginning to not have to store any more information
	 * than the current postion.
	 */
	public void update()
	{
		this.move(this.travelingDirection);
		((SnakeHead) this.body.get(0)).setDirection(this.travelingDirection);

		final List<SnakePart> partsToAddToGrid = new ArrayList<>();

		for (int i = this.body.size() - 1; i > 0; i--)
		{
			if (i - 1 >= 0)
			{
				final SnakePart current = this.body.get(i);
				final SnakePart last = this.body.get(i - 1);

				if (current.isInitialized())
				{
					this.world.remove((int) current.worldPosition.x, (int) current.worldPosition.y);
				} else
				{
					current.initialize();
				}

				current.worldPosition.x = last.worldPosition.x;
				current.worldPosition.y = last.worldPosition.y;

				partsToAddToGrid.add(current);
			}
		}

		for (int i = 0; i < partsToAddToGrid.size(); i++)
		{
			this.world.add((int) partsToAddToGrid.get(i).worldPosition.x, (int) partsToAddToGrid.get(i).worldPosition.y, partsToAddToGrid.get(i));
		}

		final SnakePart head = this.body.get(0);

		if (this.body.size() == 1)
		{
			this.world.remove((int) head.worldPosition.x, (int) head.worldPosition.y);
		}

		head.worldPosition.x = this.position.x;
		head.worldPosition.y = this.position.y;

		this.world.add((int) head.worldPosition.x, (int) head.worldPosition.y, head);
	}

	/*
	 * Resets the snake for when restarting the game.
	 */
	public void reset()
	{
		this.body.clear();
		this.travelingDirection.x = 0;
		this.travelingDirection.y = 0;
		this.body.add(new SnakeHead());
		this.body.get(0).worldPosition.x = (float) Math.floor(GameConstants.World.GRID_WIDTH / 2);
		this.position.x = (float) Math.floor(GameConstants.World.GRID_WIDTH / 2);
		this.body.get(0).worldPosition.y = (float) Math.floor(GameConstants.World.GRID_HEIGHT / 2);
		this.position.y = (float) Math.floor(GameConstants.World.GRID_HEIGHT / 2);
	}

}
