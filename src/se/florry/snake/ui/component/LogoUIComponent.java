package se.florry.snake.ui.component;

import static se.florry.engine.constants.Constants.Colors.WHITE;

import org.lwjgl.util.vector.Vector2f;

import se.florry.engine.model.Color;
import se.florry.engine.model.TextModel;
import se.florry.engine.ui.model.UIComponent;

/*
 * Component for displaying the snake logo.
 */
public class LogoUIComponent extends UIComponent
{

	public LogoUIComponent()
	{
		super(null);
	}

	@Override
	public void prepare()
	{

		final Vector2f logoPosition = new Vector2f(0, 0);
		this.addBorder(logoPosition, 20);

		final TextModel logo = new TextModel("Snake", 64, logoPosition, WHITE, true);
		this.addElement(logo);

	}

	private void addBorder(final Vector2f logoPosition, final int borderSize)
	{
		final Color shadowColor = new Color(14, 44, 69);

		final TextModel logoBorder1 = new TextModel("Snake", 64, new Vector2f(logoPosition.x - borderSize, logoPosition.y - borderSize), shadowColor, false);
		final TextModel logoBorder2 = new TextModel("Snake", 64, new Vector2f(logoPosition.x + borderSize, logoPosition.y + borderSize), shadowColor, false);
		final TextModel logoBorder3 = new TextModel("Snake", 64, new Vector2f(logoPosition.x + borderSize, logoPosition.y), shadowColor, false);
		final TextModel logoBorder4 = new TextModel("Snake", 64, new Vector2f(logoPosition.x - borderSize, logoPosition.y), shadowColor, false);
		final TextModel logoBorder5 = new TextModel("Snake", 64, new Vector2f(logoPosition.x, logoPosition.y - borderSize), shadowColor, false);
		final TextModel logoBorder6 = new TextModel("Snake", 64, new Vector2f(logoPosition.x, logoPosition.y + borderSize), shadowColor, false);

		this.addElement(logoBorder1);
		this.addElement(logoBorder2);
		this.addElement(logoBorder3);
		this.addElement(logoBorder4);
		this.addElement(logoBorder5);
		this.addElement(logoBorder6);
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
