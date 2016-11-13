package se.florry.snake.ui.component.menu;

import org.lwjgl.util.vector.Vector2f;

import se.florry.engine.model.Color;
import se.florry.engine.model.Size;
import se.florry.engine.ui.model.UIComponent;
import se.florry.engine.ui.model.UIQuad;

/*
 * Component for displaying the background, with border, for menu screens.
 */
public class MenuBackgroundUIComponent extends UIComponent
{

	public MenuBackgroundUIComponent()
	{
		super(null);
	}

	@Override
	public void prepare()
	{
		UIQuad menuBackgroundBorder = new UIQuad(new Size(302, 302), new Vector2f(-1, -1), new Color(148, 184, 214));
		UIQuad menuBackground = new UIQuad(new Size(300, 300), new Vector2f(), new Color(18, 80, 132));

		addElement(menuBackgroundBorder);
		addElement(menuBackground);
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
