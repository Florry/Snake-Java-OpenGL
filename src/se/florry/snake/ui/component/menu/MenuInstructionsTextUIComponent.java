package se.florry.snake.ui.component.menu;

import org.lwjgl.util.vector.Vector2f;

import se.florry.engine.model.TextModel;
import se.florry.engine.ui.model.UIComponent;

/*
 * Component for displaying the press enter to start game instructions on the start screen.
 */
public class MenuInstructionsTextUIComponent extends UIComponent
{

	public MenuInstructionsTextUIComponent()
	{
		super(null);
	}

	@Override
	public void prepare()
	{
		TextModel instructionText = new TextModel("Press enter", 10, new Vector2f(30, 100));
		TextModel instructionText2 = new TextModel("to", 10, new Vector2f(140, 120));
		TextModel startGameText = new TextModel("Start Game", 16, new Vector2f(12, 140));

		addElement(instructionText);
		addElement(instructionText2);
		addElement(startGameText);
	}

	@Override
	protected void update()
	{
	}

	@Override
	protected void initialised()
	{
	}

}
