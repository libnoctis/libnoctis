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
import org.libnoctis.render.gl.NFont;
import org.libnoctis.render.gl.TextureRegion;

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
	 * Sets the current color of the drawer.
	 *
	 * @param color The drawing color
     */
	public abstract void setColor(Color color);

	/**
	 * Draws a plain color rectangle.
	 *
	 * @param x The x position of the rectangle.
	 * @param y The y position of the rectangle.
	 * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
	public abstract void drawRect(int x, int y, int width, int height);

	public abstract void drawTexture(int x, int y, int width, int height, NTexture texture, TextureRegion icon);
	
	public void drawTexture(int x, int y, int width, int height, NTexture texture)
	{
		drawTexture(x, y, width, height, texture, TextureRegion.WHOLE_TEXTURE);
	}
	
	/**
	 * @return If the drawer should be called at every frame
     */
	public abstract boolean shouldPaintEveryFrame();
    /**
     * Draw a rectangle with the bound texture
     *
     * @param x The x position of the rectangle
     * @param y The y position of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     */
    public void drawTexturedRect(int x, int y, int width, int height)
    {
        drawTexturedRect(x, y, width, height, 0, 0);
    }

	/**
	 * Draw a string to the given position with the current font
	 *
	 * @param x The x position of the text
	 * @param y The y position of the text
	 * @param string The string to draw
	 */
	public abstract void drawString(int x, int y, String string);

	/**
	 * Set the current font
	 *
	 * @param font The font to set
     */
	public abstract void setFont(NFont font);

    /**
     * Draw a rectangle with the texture at the given x, y of the bound sprite
     *
     * @param x The x position of the rectangle
     * @param y The y position of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param u The x of the texture of the sprite
     * @param v The y of the texture of the sprite
     */
    public abstract void drawTexturedRect(int x, int y, int width, int height, int u, int v);


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
