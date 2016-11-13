package se.florry.snake.ui.scene;

import se.florry.engine.constants.Constants;
import se.florry.engine.ui.model.UIScene;
import se.florry.snake.constants.GameConstants;
import se.florry.snake.ui.component.LogoUIComponent;
import se.florry.snake.ui.component.menu.MenuBackgroundUIComponent;
import se.florry.snake.ui.component.menu.MenuInstructionsTextUIComponent;

/*
 * GUIScene for displaying the start screen.
 */
public class MainMenuUIScene extends UIScene
{

	@Override
	public void prepare()
	{
		MenuBackgroundUIComponent menuBackgroundComponent = new MenuBackgroundUIComponent();
		menuBackgroundComponent.position.x = (Constants.Display.WIDTH / 2) - (GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE / 2);
		menuBackgroundComponent.position.x += ((GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE) - 300) / 2;
		menuBackgroundComponent.position.y = 200;
		addUIComponent(menuBackgroundComponent);

		MenuInstructionsTextUIComponent menuInstructionsTextUIComponent = new MenuInstructionsTextUIComponent();
		menuInstructionsTextUIComponent.position.x = (Constants.Display.WIDTH / 2) - (GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE / 2);
		menuInstructionsTextUIComponent.position.x += ((GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE) - 300) / 2;
		menuInstructionsTextUIComponent.position.y = 200;
		addUIComponent(menuInstructionsTextUIComponent);

		LogoUIComponent logo = new LogoUIComponent();
		logo.position.x = (Constants.Display.WIDTH / 2) - (GameConstants.World.GRID_WIDTH * GameConstants.World.GRID_PIXEL_SIZE / 2);
		logo.position.y = 50;

		addUIComponent(logo);
	}

}
