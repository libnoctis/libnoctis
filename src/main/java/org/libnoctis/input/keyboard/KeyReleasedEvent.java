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

/**
 * The Key Released Event
 *
 * <p>
 *     An event called when a key is released.
 * </p>
 *
 * @author Litarvan
 * @version 0.1.0
 * @since 0.0.1
 */
public class KeyReleasedEvent extends KeyboardEvent
{
    /**
     * How long the key was pressed before it was released
     */
    private long pressTime;

    /**
     * The Key Released Event
     *
     * @param key The key that was released
     * @param pressTime How long the key was pressed before it was released
     */
    public KeyReleasedEvent(Key key, long pressTime)
    {
        super(key);

        this.pressTime = pressTime;
    }

    /**
     * @return How long the key was pressed before it was released
     */
    public long getPressTime()
    {
        return pressTime;
    }
}
