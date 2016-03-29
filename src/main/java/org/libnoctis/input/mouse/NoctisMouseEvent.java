/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.

 * Libnoctis is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Libnoctis is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.input.mouse;

import org.libnoctis.util.Vector2i;

/**
 * The Noctis Mouse Event
 *
 * <p>
 *     This event is the parent of all the mouse event.
 *     It contains the click position, the clicked button, and a callback
 *     to execute after the button release.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoctisMouseEvent
{
    /**
     * The position where the mouse is
     */
    private Vector2i pos;

    /**
     * The button that made the click
     */
    private MouseButton button;

    /**
     * The callback, when the mouse will release
     */
    private Runnable callback;

    /**
     * The Noctis Mouse Event
     *
     * @param pos The position where the mouse is
     * @param button The button that clicked
     */
    public NoctisMouseEvent(Vector2i pos, MouseButton button)
    {
        this(pos, button, null);
    }

    /**
     * The Noctis Mouse Event
     *
     * @param pos The position where the mouse is
     * @param button The button that clicked
     * @param callback A callback to call after that the mouse was released, if it
     *                 contains a method with annotation NoctisEvent that take in param
     *                 NoctisClickEvent, it will be called.
     */
    public NoctisMouseEvent(Vector2i pos, MouseButton button, Runnable callback)
    {
        this.pos = pos;
        this.button = button;
        this.callback = callback;
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
    public MouseButton getButton()
    {
        return button;
    }

    /**
     * @return The callback (null by default) to call when the button is released
     */
    public Runnable getCallback()
    {
        return callback;
    }
}
