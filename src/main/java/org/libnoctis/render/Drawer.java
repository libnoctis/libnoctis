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
package org.libnoctis.render;

import org.libnoctis.components.NComponent;

/**
 * The Drawer
 *
 * <p>
 *     The Drawer is used to draw things, like shapes or
 *     images. It is used to render the components.
 * </p>
 *
 * @author Wytrem & Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Drawer
{
	/**
	 * Set the current color of the drawer
	 *
	 * @param color The drawing color
     */
	public abstract void setColor(Color color);

	/**
	 * Draw a rectangle
	 *
	 * @param x The x position of the rectangle
	 * @param y The y position of the rectangle
	 * @param width The width of the rectangle
     * @param height The height of the rectangle
     */
	public abstract void drawRect(int x, int y, int width, int height);

	public abstract boolean paintEveryFrame();

	/**
	 * Event called after the painting of the given component
	 *
	 * @param component The component that was painted
     */
	public void postPaint(NComponent component)
	{
	}

	/**
	 * Event called before the painting of the given component
	 *
	 * @param component The component that will be painted
     */
	public void prePaint(NComponent component)
	{
	}

	/**
	 * Render the given component
	 *
	 * @param component The component to render
     */
	public void render(NComponent component)
	{
	}
}
