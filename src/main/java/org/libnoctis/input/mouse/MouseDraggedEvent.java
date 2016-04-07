package org.libnoctis.input.mouse;


import org.libnoctis.util.Vector2i;


public class MouseDraggedEvent extends MouseMoveEvent
{
	private Vector2i clickPos;
	private long timeSinceClick;

	public MouseDraggedEvent(Vector2i pos, MouseButton button, Vector2i motion, Vector2i clickPos, long timeSinceClick)
	{
		super(pos, button, motion);
		this.clickPos = clickPos;
		this.timeSinceClick = timeSinceClick;
	}

	public Vector2i getClickPos()
	{
		return clickPos;
	}

	public long getTimeSinceClick()
	{
		return timeSinceClick;
	}

	public boolean shouldPassForSuperclassEvent()
	{
		return false;
	}
}
