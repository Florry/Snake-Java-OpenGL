package se.florry.snake.model;

import se.florry.engine.model.Engine;

/*
 * The game API
 */
public final class Snake
{

	private final Engine engine;
	private final Game game;

	public Snake()
	{
		this.engine = new Engine();
		this.engine.setTitle("Snake - LWJGL");
		this.game = new Game(this.engine);
	}

	public void run()
	{
		this.game.run();
	}

}