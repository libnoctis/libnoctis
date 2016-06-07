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
package org.libnoctis.input.keyboard;

import org.libnoctis.input.NEvent;

/**
 * The Keyboard Event
 *
 * <p>
 *     Basic class for the keyboard events, containing
 *     just the key of the event.
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
 */
public abstract class KeyboardEvent extends NEvent
{
    /**
     * The key of the event
     */
    private Key key;

    /**
     * The Keyboard event
     *
     * @param key The key of the event
     */
    public KeyboardEvent(Key key)
    {
        this.key = key;
    }

    /**
     * @return The key of the event
     */
    public Key getKey()
    {
        return key;
    }
}
