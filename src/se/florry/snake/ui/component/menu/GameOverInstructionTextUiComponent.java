package se.florry.snake.ui.component.menu;

import org.lwjgl.util.vector.Vector2f;

import se.florry.engine.model.TextModel;
import se.florry.engine.ui.model.UIComponent;

/*
 * Component for displaying the Game over text(s) on the game over screen.
 */
public class GameOverInstructionTextUiComponent extends UIComponent
{

	public GameOverInstructionTextUiComponent()
	{
		super(null);
	}

	@Override
	public void prepare()
	{
		TextModel gameOverText = new TextModel("Game Over!", 10, new Vector2f(30, 100));

		addElement(gameOverText);
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
