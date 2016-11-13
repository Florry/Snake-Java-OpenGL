package se.florry.snake.ui.scene;

import se.florry.engine.constants.Constants;
import se.florry.engine.ui.model.UIScene;
import se.florry.snake.constants.GameConstants;
import se.florry.snake.model.Game;
import se.florry.snake.ui.component.LogoUIComponent;
import se.florry.snake.ui.component.gui.ScoreUIComponent;

/*
 * Scene for displaying the gui.
 */
public class GuiUIScene extends UIScene
{

	private final Game game;

	public GuiUIScene(final Game game)
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

		ScoreUIComponent scoreComponent = new ScoreUIComponent(this.game, 20, Constants.Colors.WHITE, false);
		scoreComponent.position.x = (Constants.Display.WIDTH / 2) - (GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE / 2);
		scoreComponent.position.y = GameConstants.World.GRID_HEIGHT * GameConstants.World.GRID_PIXEL_SIZE + GameConstants.Game.BOARD_Y_OFFSET + 30;
		addUIComponent(scoreComponent);

	}

}
