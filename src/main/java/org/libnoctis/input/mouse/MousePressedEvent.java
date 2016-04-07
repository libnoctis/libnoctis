package org.libnoctis.input.mouse;


import org.libnoctis.util.Vector2i;


public class MousePressedEvent extends MouseEvent
{
	public MousePressedEvent(Vector2i pos, MouseButton button)
	{
		super(pos, button);
	}
}
