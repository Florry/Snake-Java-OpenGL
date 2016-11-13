package se.florry.snake.main;

import se.florry.snake.model.Snake;

/*
 * Starts the game
 */
public class MainGameLoop
{

	public static void main(final String[] args)
	{
		final Snake game = new Snake();
		game.run();
	}

}
