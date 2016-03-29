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
package org.libnoctis.input;

/**
 * The Event Manager
 *
 * <p>
 *     The Event Manager can manage, check, and launch some
 *     events of classes.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class EventManager
{
    /**
     * Launch an event
     *
     * @param obj   The class where is the event method
     * @param event The class (ex: {@link org.libnoctis.input.mouse.NoctisMouseMoveEvent} of the event
     *
     * @see NoctisEvent
     */
    public void launchEvent(Object obj, Class<?> event)
    {
        // TODO: Event Manager
    }
}
