package org.libnoctis.render;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

public class Texture
{
	public static final int GL_TARGET = GL_TEXTURE_2D;
	
	/**
	 * This texture ID for OpenGL.
	 */
	private int textureId;
	
	public void bind()
	{
		glBindTexture(GL_TARGET, textureId);
	}
}
