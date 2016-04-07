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

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * An OpenGL Texture
 *
 * @author Wytrem
 * @version 1.0.0
 * @since 1.0.0
 */
public class GlTexture
{
	/**
	 * The target GL code
	 */
	public static final int GL_TARGET = GL_TEXTURE_2D;

	/**
	 * This texture ID for OpenGL.
	 */
	private int textureId;

	/**
	 * The OpenGL Texture
	 *
	 * @param stream The image input stream
	 *
	 * @throws IOException If it failed to read the image
     */
	public GlTexture(InputStream stream) throws IOException
	{
		this(ImageIO.read(stream));
	}

	/**
	 * The OpenGL Texture
	 *
	 * @param image The image to load
     */
	public GlTexture(BufferedImage image)
	{
		textureId = glGenTextures();
		//		update(image);
	}

	/**
	 * Bind this texture
	 */
	public void bind()
	{
		glBindTexture(GL_TARGET, textureId);
	}

	/**
	 * Unbind the current texture
	 */
	public static void bindNone()
	{
		glBindTexture(GL_TARGET, 0);
	}
}
