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
 * The Mouse Exited Event
 *
 * <p>
 *     This event is called when the mouse quit a component
 * </p>
 *
 * @author Wytrem
 * @version 0.1.0
 * @since 0.0.1
 */
public class MouseExitedEvent extends MouseEvent
{
    /**
     * @inheritDoc
     */
    public MouseExitedEvent(Vector2i pos)
    {
        super(pos);
    }
}
