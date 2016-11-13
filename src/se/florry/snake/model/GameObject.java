package se.florry.snake.model;

import se.florry.engine.model.QuadModel;

/*
 * Extends the QuadModel with onDestroy functionality to be able to trigger something when 
 * a block is removed from the grid & implements a fast equals/hash code check.
 */
public class GameObject extends QuadModel
{

	public GameObject(final int size)
	{
		super(size);
	}

	public void onDestroy(final Game game)
	{
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (obj == this)
		{
			return true;
		} else if (obj instanceof GameObject)
		{
			final GameObject otherObj = (GameObject) obj;

			if (otherObj.getId()
					.equals(this.getId()))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 37;
		hash *= this.getId()
				.hashCode();
		return hash;
	}
}
