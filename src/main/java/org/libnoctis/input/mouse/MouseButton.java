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

/**
 * The Mouse Buttons
 *
 * <p>
 *     An enumeration of the different mouse buttons, used by the events.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public enum MouseButton
{
     /**
      * The left mouse button
      */
     LEFT,

     /**
      * The right mouse button
      */
     RIGHT,

     /**
      * The mouse wheel button
      */
     CENTER;

    /**
     * Get the MouseButton of the given id
     *
     * @param id The id of the mouse button to get
     *
     * @return The mouse button of the given id
     */
    public static MouseButton byId(int id)
    {
        if (id < 0)
        {
            return null;
        }

        return values()[id];
    }
}
