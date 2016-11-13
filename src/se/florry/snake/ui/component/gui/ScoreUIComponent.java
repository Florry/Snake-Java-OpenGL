package se.florry.snake.ui.component.gui;

import org.lwjgl.util.vector.Vector2f;

import se.florry.engine.constants.Constants;
import se.florry.engine.model.Color;
import se.florry.engine.model.TextModel;
import se.florry.engine.ui.model.UIComponent;
import se.florry.snake.model.Game;

/*
 * Component for displaying the current score of the player.
 */
public class ScoreUIComponent extends UIComponent
{

	private final Game game;
	private final int textSize;
	private final Color textColor;
	private final boolean shadows;

	private TextModel text;
	private final StringBuilder stringBuilder;

	public ScoreUIComponent(final Game game, final int size, final Color color, final boolean shadows)
	{
		super(null);
		this.game = game;
		this.textSize = size;
		this.textColor = color;
		this.shadows = shadows;
		this.stringBuilder = new StringBuilder();
	}

	public ScoreUIComponent(final Game game, final int size, final Color color)
	{
		this(game, size, color, false);
	}

	public ScoreUIComponent(final Game game, final int size)
	{
		this(game, size, Constants.Colors.WHITE, false);
	}

	@Override
	public void prepare()
	{
		this.text = new TextModel(this.stringBuilder.append("Score:")
				.append(this.game.getScore())
				.toString(), this.textSize, new Vector2f(), this.textColor, this.shadows);

		this.addElement(this.text);
	}

	@Override
	protected void update()
	{
		this.stringBuilder.delete(0, this.stringBuilder.length() > 0 ? this.stringBuilder.length() : 0);
		this.text.setSentence(this.stringBuilder.append("Score:")
				.append(this.game.getScore())
				.toString());
	}

	@Override
	protected void initialised()
	{
	}
}
