package se.florry.snake.model;

import se.florry.engine.material.Material;
import se.florry.engine.model.Engine;
import se.florry.engine.model.QuadModel;
import se.florry.engine.model.Size;
import se.florry.engine.utils.EngineUtils;
import se.florry.snake.constants.GameConstants;
import se.florry.snake.entity.Player;
import se.florry.snake.ui.UI;
import se.florry.snake.world.World;

public class Game
{
	private final Engine engine;
	private final World world;
	private final Player player;
	private final UI ui;

	private final QuadModel background;
	private final QuadModel boardBackground;
	private final QuadModel boardBorder;
	private final Material boardMaterial;

	private long lastTimePlayerProcessed;
	private int score;
	private float difficulty = 1;
	private boolean isPaused;

	protected Game(final Engine engine)
	{
		this.engine = engine;
		this.world = new World(this, this.engine);
		this.player = new Player(engine.input(), this.world, this);
		this.ui = new UI(this, this.engine);

		this.background = new QuadModel(new Size(1280, 720));
		this.background.color.r = 13;
		this.background.color.g = 57;
		this.background.color.b = 93;
		this.background.color.a = 255;

		this.boardBackground = new QuadModel(new Size(GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE,
				GameConstants.World.GRID_HEIGHT * GameConstants.World.GRID_PIXEL_SIZE));
		this.boardBackground.textureSize.x = GameConstants.World.GRID_WIDTH;
		this.boardBackground.textureSize.y = GameConstants.World.GRID_HEIGHT;
		this.boardBackground.position.x = GameConstants.Game.BOARD_X_OFFSET;
		this.boardBackground.position.y = GameConstants.Game.BOARD_Y_OFFSET;

		this.boardBorder = new QuadModel(new Size(GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE + 2,
				GameConstants.World.GRID_HEIGHT * GameConstants.World.GRID_PIXEL_SIZE + 2));
		this.boardBorder.position.x = GameConstants.Game.BOARD_X_OFFSET - 1;
		this.boardBorder.position.y = GameConstants.Game.BOARD_Y_OFFSET - 1;
		this.boardBorder.color.r = 148;
		this.boardBorder.color.g = 184;
		this.boardBorder.color.b = 214;
		this.boardBorder.color.a = 255;

		this.boardMaterial = new Material("grid");

		this.score = 0;

		this.addSoundEffects();
	}

	public void run()
	{
		this.world.reset();
		this.engine.run(deltaTime ->
		{
			this.processBackground();
			this.processPlayer();
			this.processWorld();
			this.processUI();
		});
	}

	/*
	 * Render all UI components from the UI controller.
	 */
	private void processUI()
	{
		this.ui.render();
	}

	/*
	 * Renders all backgrounds.
	 */
	private void processBackground()
	{
		this.background.render();

		this.boardBorder.render();
		this.boardMaterial.setMaterial();
		this.boardBackground.render();
		this.boardMaterial.disable();
	}

	/*
	 * Processes the player every xth of a second, based on the current
	 * difficulty.
	 */
	private void processPlayer()
	{
		if (EngineUtils.getTime() >= (double) this.lastTimePlayerProcessed + (100.0f - this.difficulty * 10))
		{
			if (!this.isPaused)
			{
				this.lastTimePlayerProcessed = EngineUtils.getTime();
				this.player.update();
			}
		}
	}

	/*
	 * Goes through all game objects in the grid array and processes them. Sets
	 * the position based on their position in the array.
	 */
	private void processWorld()
	{
		for (int x = 0; x < this.world.getGridLength(); x++)
		{
			for (int y = 0; y < this.world.getGridLength(x); y++)
			{
				if (this.world.get(x, y) != null)
				{
					final GameObject gameObject = this.world.get(x, y);

					gameObject.position.x = x * GameConstants.World.GRID_PIXEL_SIZE + GameConstants.Game.BOARD_X_OFFSET;
					gameObject.position.y = y * GameConstants.World.GRID_PIXEL_SIZE + GameConstants.Game.BOARD_Y_OFFSET;
					gameObject.render();
				}
			}
		}
	}

	/*
	 * Method being called on by the apple's onDestroy method. Adds score,
	 * extends the snake by one part and slighty increases the difficulty.
	 */
	public void appleEaten()
	{
		this.score++;
		this.player.extendBody();
		this.difficulty += 0.1f;
	}

	/*
	 * Resets the game; the world, the player and the score.
	 */
	public void gameOver()
	{
		this.ui.changeScene(ActiveScene.GAME_OVER_SCENE);
		this.player.stop();
		this.isPaused = true;
		this.engine.playSound("game-over");
	}

	/*
	 * Resets the game; the world, the player and the score.
	 */
	public void restartGame()
	{
		this.world.reset();
		this.player.reset();
		this.score = 0;
		this.difficulty = 1;
		this.isPaused = false;
		this.engine.playSound("game-start");
	}

	public void gameComplete()
	{
		this.isPaused = true;
		System.out.println("Game Completed!  " + this.score);
	}

	public int getScore()
	{
		return this.score;
	}

	/*
	 * Adds all sound effects to the engine.
	 */
	private void addSoundEffects()
	{
		this.engine.addSoundEffect("apple", "apple-pickup");
		this.engine.addSoundEffect("begin", "game-start");
		this.engine.addSoundEffect("gameover", "game-over");
	}

}
