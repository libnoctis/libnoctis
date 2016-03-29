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
 * The Noctis Mouse Move Event
 *
 * <p>
 *     An event called when the mouse is moving. It contains its previous
 *     position, if it is dragging (is clicked) and if it is, the button
 *     that is dragging. It also contains the current mouse position
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoctisMouseMoveEvent extends NoctisMouseEvent
{
    /**
     * The previous mouse position
     */
    private Vector2i previousPos;

    /**
     * If the mouse is dragging (mouse is clicked)
     */
    private boolean dragging;

    /**
     * The Noctis Mouse Event
     *
     * @param pos         The position where the mouse is
     * @param button      The button that is clicking (if dragging is true, else it is false)
     * @param previousPos The previous mouse position
     * @param dragging    If the mouse is dragging (mouse is clicked)
     */
    public NoctisMouseMoveEvent(Vector2i pos, MouseButton button, Vector2i previousPos, boolean dragging)
    {
        super(pos, button);

        this.previousPos = previousPos;
        this.dragging = dragging;
    }

    /**
     * The Noctis Mouse Event
     *
     * @param pos         The position where the mouse is
     * @param button      The button that clicked
     * @param callback    A callback to call after that the mouse was released, if it
     *                    contains a method with annotation NoctisEvent that take in param
     *                    NoctisClickEvent, it will be called.
     * @param previousPos The previous mouse position
     * @param dragging    If the mouse is dragging (mouse is clicked)
     */
    public NoctisMouseMoveEvent(Vector2i pos, MouseButton button, Runnable callback, Vector2i previousPos, boolean dragging)
    {
        super(pos, button, callback);

        this.previousPos = previousPos;
        this.dragging = dragging;
    }

    /**
     * @return The previous mouse position
     */
    public Vector2i getPreviousPos()
    {
        return previousPos;
    }

    /**
     * @return If the mouse is dragging (mouse is clicked)
     */
    public boolean isDragging()
    {
        return dragging;
    }
}
