package org.libnoctis.render.gl;


import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class GlTexture
{
	public static final int GL_TARGET = GL_TEXTURE_2D;

	/**
	 * This texture ID for OpenGL.
	 */
	private int textureId;

	public GlTexture(InputStream stream) throws IOException
	{
		this(ImageIO.read(stream));
	}

	public GlTexture(BufferedImage image)
	{
		textureId = glGenTextures();
		//		update(image);
	}

	public void bind()
	{
		glBindTexture(GL_TARGET, textureId);
	}

	public static void bindNone()
	{
		glBindTexture(GL_TARGET, 0);
	}
}
