package org.libnoctis.input.mouse;

import org.libnoctis.util.Vector2i;

public class MouseReleasedEvent extends MouseEvent
{
	public MouseReleasedEvent(Vector2i pos, MouseButton button)
	{
		super(pos, button);
	}
}
