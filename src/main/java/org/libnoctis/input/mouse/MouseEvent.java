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

import org.jetbrains.annotations.Nullable;
import org.libnoctis.input.NEvent;
import org.libnoctis.util.Vector2i;

/**
 * The Noctis Mouse Event
 *
 * <p>
 *     This event is the parent of all the mouse event. It contains the click
 *     position, the clicked button, and a callback to execute after the button
 *     release.
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
 */
public abstract class MouseEvent extends NEvent
{
    /**
     * The mouse position.
     */
    private Vector2i pos;

    /**
     * The button that made the event (null if none).
     */
    @Nullable
    private MouseButton button;

    /**
     * The Mouse Event
     *
     * @param pos The current mouse position
     */
    public MouseEvent(Vector2i pos)
    {
        this(pos, null);
    }

    /**
     * The Noctis Mouse Event
     *
     * @param pos The mouse position
     * @param button The button that clicked
     */
    public MouseEvent(Vector2i pos, MouseButton button)
    {
        this.pos = pos;
        this.button = button;
    }

    /**
     * @return The position where the mouse is
     */
    public Vector2i getPos()
    {
        return pos;
    }

    /**
     * @return The mouse button that clicked
     */
    @Nullable
    public MouseButton getButton()
    {
        return button;
    }
}
