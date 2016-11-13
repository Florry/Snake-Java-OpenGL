package se.florry.snake.ui.scene;

import se.florry.engine.constants.Constants;
import se.florry.engine.ui.model.UIScene;
import se.florry.snake.constants.GameConstants;
import se.florry.snake.model.Game;
import se.florry.snake.ui.component.LogoUIComponent;
import se.florry.snake.ui.component.gui.ScoreUIComponent;
import se.florry.snake.ui.component.menu.GameOverInstructionTextUiComponent;
import se.florry.snake.ui.component.menu.MenuBackgroundUIComponent;

/*
 * UIScene for displaying the game over screen.
 */
public class GameOverGUIScene extends UIScene
{

	private final Game game;

	public GameOverGUIScene(Game game)
	{
		this.game = game;
	}

	@Override
	public void prepare()
	{
		LogoUIComponent logo = new LogoUIComponent();
		logo.position.x = (Constants.Display.WIDTH / 2) - (GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE / 2);
		logo.position.y = 50;
		addUIComponent(logo);

		MenuBackgroundUIComponent menuBackgroundUIComponent = new MenuBackgroundUIComponent();
		menuBackgroundUIComponent.position.x = (Constants.Display.WIDTH / 2) - (GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE / 2);
		menuBackgroundUIComponent.position.x += ((GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE) - 300) / 2;
		menuBackgroundUIComponent.position.y = 200;
		addUIComponent(menuBackgroundUIComponent);

		GameOverInstructionTextUiComponent gameOverInstructionTextUiComponent = new GameOverInstructionTextUiComponent();
		gameOverInstructionTextUiComponent.position.x = (Constants.Display.WIDTH / 2)
				- (GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE / 2);
		gameOverInstructionTextUiComponent.position.x += ((GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE) - 300) / 2;
		gameOverInstructionTextUiComponent.position.x += 20;
		gameOverInstructionTextUiComponent.position.y = 200;
		addUIComponent(gameOverInstructionTextUiComponent);

		ScoreUIComponent scoreComponent = new ScoreUIComponent(this.game, 20, Constants.Colors.WHITE, true);
		scoreComponent.position.x = 100 + (Constants.Display.WIDTH / 2) - (GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE / 2);
		scoreComponent.position.y = 350;
		addUIComponent(scoreComponent);
	}

}
