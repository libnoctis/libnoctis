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
 * The Noctis Mouse Click Event
 * <p>
 * This event is executed when the mouse click somewhere
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class MouseClickedEvent extends MouseEvent
{
	/**
	 * The Noctis Mouse Click Event
	 *
	 * @param clickPos The position where the mouse clicked
	 * @param button The button that clicked
	 */
	public MouseClickedEvent(Vector2i clickPos, MouseButton button)
	{
		super(clickPos, button);
	}
}
