package se.florry.snake.constants;

import se.florry.engine.constants.Constants;

public class GameConstants
{

	public final static class Game
	{

		public final static float BOARD_X_OFFSET = Constants.Display.WIDTH / 2 - World.GRID_WIDTH * World.GRID_PIXEL_SIZE / 2;
		public final static float BOARD_Y_OFFSET = Constants.Display.HEIGHT / 2 - World.GRID_HEIGHT * World.GRID_PIXEL_SIZE / 2;

	}

	public final static class World
	{

		public final static int GRID_PIXEL_SIZE = 20;
		public final static int GRID_WIDTH = 21;
		public final static int GRID_HEIGHT = 21;

	}

}
