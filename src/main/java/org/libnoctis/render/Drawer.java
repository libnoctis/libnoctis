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
package org.libnoctis.render;


import org.libnoctis.components.NComponent;


/**
 * The Drawer
 * <p>
 * The Drawer is used to draw things, like shapes or
 * images. It is used to render the components.
 * </p>
 *
 * @author Litarvan & Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Drawer
{
	public abstract void setColor(Color color);

	public abstract void drawRect(int x, int y, int width, int height);

	public abstract boolean paintEveryFrame();

	public void postPaint(NComponent component)
	{
		
	}

	public void prePaint(NComponent component)
	{

	}

	public void render(NComponent nComponent)
	{
		
	}
}
