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
import org.libnoctis.render.gl.GlFont;
import org.libnoctis.render.gl.GlTexture;
import org.libnoctis.render.gl.TextureRegion;


/**
 * The Drawer
 * <p>
 * The Drawer is used to draw things, like shapes or
 * images. It is used to render the components.
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
     * Sets the current font of the drawer.
     * 
     * @param font The font to be rendered.
     * @see Drawer#drawString(String, int, int)
     */
    public abstract void setFont(GlFont font);

    /**
     * Draws the given string to the screen at the given location.
     * 
     * @param str The string to be rendered.
     * @param x The X coordinate of the top-left string quad.
     * @param y The Y coordinate of the top-left string quad.
     */
    public abstract void drawString(String str, int x, int y);

    /**
     * Draws a plain color rectangle.
     *
     * @param x The x position of the rectangle.
     * @param y The y position of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public abstract void drawRect(int x, int y, int width, int height);

    public abstract void drawTexture(int x, int y, int width, int height, GlTexture texture, TextureRegion icon);

    public void drawTexture(int x, int y, int width, int height, GlTexture texture)
    {
        drawTexture(x, y, width, height, texture, TextureRegion.WHOLE_TEXTURE);
    }

    /**
     * @return If the drawer should be called at every frame
     */
    public abstract boolean shouldPaintEveryFrame();

    /**
     * Draw a string to the given position with the current font
     *
     * @param x The x position of the text
     * @param y The y position of the text
     * @param string The string to draw
     */
    public abstract void drawString(int x, int y, String string);

    /**
     * @return The current used font
     */
    public abstract GlFont getFont();

    /**
     * @return The current used color
     */
    public abstract Color getColor();
    
    /**
     * Pushes the current matrix.
     */
    public abstract void pushMatrix();
    
    /**
     * Pops the current matrix.
     */
    public abstract void popMatrix();

    public abstract void translate(int x, int y);
    
    /**
     * Called after painting the given component
     *
     * @param component The component that was painted
     */
    public void postPaint(NComponent component)
    {
    }

    /**
     * Called before painting the given component
     *
     * @param component The component about to be be painted.
     */
    public void prePaint(NComponent component)
    {
    }

    /**
     * Renders the given component.
     *
     * @param component The component to be rendered.
     */
    public void render(NComponent component)
    {
    }

    /**
     * Called before rendering a frame.
     */
    public void preRender()
    {
        
    }

    /**
     * Called after rendering a frame.
     */
    public void postRender()
    {
        
    }
}
