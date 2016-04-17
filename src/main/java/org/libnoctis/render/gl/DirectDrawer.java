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
package org.libnoctis.render.gl;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.libnoctis.render.Color;
import org.libnoctis.render.Drawer;
import org.libnoctis.render.NTexture;


/**
 * The Direct Drawer
 *
 * <p>
 *     Draw things using OpenGL directly
 * </p>
 *
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public class DirectDrawer extends Drawer
{
	@Override
	public void setColor(Color color)
	{
		glColor4f(color.getRedFloat(), color.getGreenFloat(), color.getBlueFloat(), color.getAlphaFloat());
	}

	@Override
	public void drawRect(int x, int y, int width, int height)
	{
		glBegin(GL_QUADS);
		{
			glVertex2i(x, y);
			glVertex2i(x, y + height);
			glVertex2i(x + width, y + height);
			glVertex2i(x + width, y);
		}
		glEnd();
	}

	public void drawTexturedRect(int x, int y, int width, int height, int u, int v)
	{
		// TODO: Implement this
	}

	@Override
	public boolean shouldPaintEveryFrame()
	{
		return true;
	}

	@Override
	public void drawTexture(int x, int y, int width, int height, NTexture texture, TextureRegion icon)
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
		NTexture.bindNone();
	}
}
