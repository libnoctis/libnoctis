/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 * This file is part of Libnoctis.
 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.input.mouse;


import org.libnoctis.util.Vector2i;


/**
 * The Noctis Mouse Move Event
 * <p>
 * An event called when the mouse is moving. It contains its previous position,
 * if it is dragging (is clicked) and if it is, the button that is dragging. It
 * also contains the current mouse position
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class MouseMoveEvent extends MouseEvent
{
    /**
     * Movement since last event.
     */
    private Vector2i motion;

    /**
     * The Noctis Mouse Event
     *
     * @param pos The mouse position.
     * @param motion The movement since last event.
     */
    public MouseMoveEvent(Vector2i pos, Vector2i motion)
    {
        super(pos);
        this.motion = motion;
    }

    /**
     * The Noctis Mouse Event
     *
     * @param pos The mouse position.
     * @param button The button that is clicking (if dragging only).
     * @param motion The movement since last event.
     */
    protected MouseMoveEvent(Vector2i pos, MouseButton button, Vector2i motion)
    {
        super(pos, button);
        this.motion = motion;
    }

    /**
     * @return The movement since last event.
     */
    public Vector2i getMotion()
    {
        return motion;
    }
}
