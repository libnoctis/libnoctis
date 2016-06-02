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
package org.libnoctis.render.gl;

import org.libnoctis.render.Color;
import org.libnoctis.render.Drawer;


import static org.lwjgl.opengl.GL11.*;

/**
 * The Direct Drawer
 *
 * <p>
 *     Draw things using OpenGL direct render mode.
 * </p>
 *
 * @author Wytrem
 * @version 0.1.0
 * @since 0.0.1
 */
public class DirectDrawer extends Drawer
{
    /**
     * The current font to use with the drawString method
     */
    private GlFont currentFont;

    /**
     * The current drawing color
     */
    private Color currentColor;

    @Override
    public void setColor(Color color)
    {
        currentColor = color;
        glColor4f(color.getRedFloat(), color.getGreenFloat(), color.getBlueFloat(), color.getAlphaFloat());
    }

    @Override
    public GlFont getFont()
    {
        return currentFont;
    }

    @Override
    public void drawRect(int x, int y, int width, int height)
    {
        glDisable(GlTexture.TARGET);
        glBegin(GL_LINE_LOOP);
        {
            glVertex2i(x, y);
            glVertex2i(x, y + height);
            glVertex2i(x + width, y + height);
            glVertex2i(x + width, y);
        }
        glEnd();
        glEnable(GlTexture.TARGET);
    }

    @Override
    public boolean shouldPaintEveryFrame()
    {
        return true;
    }

    @Override
    public void drawTexture(int x, int y, int width, int height, GlTexture texture, TextureRegion icon)
    {
        texture.bind();
        glBegin(GL_QUADS);
        {
            glTexCoord2f(icon.getMinU(), icon.getMinV());
            glVertex2i(x, y);
            glTexCoord2f(icon.getMinU(), icon.getMaxV());
            glVertex2i(x, y + height);
            glTexCoord2f(icon.getMaxU(), icon.getMaxV());
            glVertex2i(x + width, y + height);
            glTexCoord2f(icon.getMaxU(), icon.getMinV());
            glVertex2i(x + width, y);
        }
        glEnd();
        GlTexture.bindNone();
    }

    @Override
    public void setFont(GlFont font)
    {
        this.currentFont = font;
    }

    @Override
    public Color getColor()
    {
        return currentColor;
    }

    @Override
    public void pushMatrix()
    {
        glPushMatrix();
    }

    @Override
    public void popMatrix()
    {
        glPopMatrix();
    }

    @Override
    public void translate(int x, int y)
    {
        glTranslatef(x, y, 0.0f);
    }

    @Override
    public void fillRect(int x, int y, int width, int height)
    {
        glDisable(GlTexture.TARGET);
        glBegin(GL_QUADS);
        {
            glVertex2i(x, y);
            glVertex2i(x, y + height);
            glVertex2i(x + width, y + height);
            glVertex2i(x + width, y);
        }
        glEnd();
        glEnable(GlTexture.TARGET);        
    }
}
