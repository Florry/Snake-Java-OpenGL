package se.florry.snake.ui;

import org.lwjgl.glfw.GLFW;

import se.florry.engine.model.Engine;
import se.florry.engine.utils.EngineUtils;
import se.florry.snake.model.ActiveScene;
import se.florry.snake.model.Game;
import se.florry.snake.ui.scene.GameOverGUIScene;
import se.florry.snake.ui.scene.GuiUIScene;
import se.florry.snake.ui.scene.MainMenuUIScene;

/*
 * Controller for all UI elements.
 */
public class UI
{

	private final Game game;
	private final Engine engine;
	private final MainMenuUIScene mainMenuScene;
	private final GuiUIScene guiUIScene;
	private final GameOverGUIScene gameOverGUIScene;

	private ActiveScene lastActiveScreen;
	private ActiveScene currentScene;

	private long nextInput;

	public UI(final Game game, final Engine engine)
	{
		this.game = game;
		this.engine = engine;
		this.mainMenuScene = new MainMenuUIScene();
		this.guiUIScene = new GuiUIScene(this.game);
		this.gameOverGUIScene = new GameOverGUIScene(this.game);

		this.currentScene = ActiveScene.MENU_SCENE;

		this.engine.input()
				.appPress(GLFW.GLFW_KEY_ENTER, pressed ->
				{
					if (this.currentScene.equals(ActiveScene.MENU_SCENE) || this.currentScene.equals(ActiveScene.GAME_OVER_SCENE))
					{
						this.changeScene(null);
					}
				});
	}

	public void changeScene(final ActiveScene scene)
	{
		if (scene != null)
		{
			this.currentScene = scene;
		} else
		{
			if (EngineUtils.getTime() >= this.nextInput)
			{
				this.nextInput = EngineUtils.getTime() + 200;

				if (!this.currentScene.equals(ActiveScene.GAME_SCENE))
				{
					this.currentScene = ActiveScene.GAME_SCENE;
				}
			}
		}
	}

	public void render()
	{
		if (this.lastActiveScreen != this.currentScene)
		{
			this.lastActiveScreen = this.currentScene;
			this.mainMenuScene.destroy();
			this.guiUIScene.destroy();

			switch (this.currentScene)
			{
			case MENU_SCENE:
				this.engine.input()
						.pause();
				break;
			case GAME_SCENE:
				this.game.restartGame();
				this.engine.input()
						.resume();
				break;
			case GAME_OVER_SCENE:
				this.engine.input()
						.pause();
				break;
			default:
				break;
			}
		}

		switch (this.currentScene)
		{
		case MENU_SCENE:
			this.mainMenuScene.render();
			break;
		case GAME_SCENE:
			this.guiUIScene.render();
			break;
		case GAME_OVER_SCENE:
			this.gameOverGUIScene.render();
			break;
		default:
			break;
		}
	}

}
