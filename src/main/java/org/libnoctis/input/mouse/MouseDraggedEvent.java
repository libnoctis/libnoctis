/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.
 *
 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.input.mouse;

import org.libnoctis.util.Vector2i;

/**
 * The Mouse Dragged Event
 *
 * <p>
 *     An event called when the mouse is dragging (move + click)
 *     a component.
 * </p>
 *
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public class MouseDraggedEvent extends MouseMoveEvent
{
    /**
     * The position of the first click
     */
    private Vector2i clickPos;

    /**
     * The time since the first click
     */
    private long timeSinceClick;

    /**
     * The Mouse Dragged Event
     *
     * @param pos The current mouse position
     * @param button The mouse button that clicked
     * @param motion The motion of the mouse
     * @param clickPos The position of the first click
     * @param timeSinceClick The time since the first click
     */
    public MouseDraggedEvent(Vector2i pos, MouseButton button, Vector2i motion, Vector2i clickPos, long timeSinceClick)
    {
        super(pos, button, motion);

        this.clickPos = clickPos;
        this.timeSinceClick = timeSinceClick;
    }

    /**
     * @return The position of the first click
     */
    public Vector2i getClickPos()
    {
        return clickPos;
    }

    /**
     * @return The time since the first click
     */
    public long getTimeSinceClick()
    {
        return timeSinceClick;
    }

    /**
     * @inheritDoc
     */
    public boolean shouldPassForSuperclassEvent()
    {
        return false;
    }
}
