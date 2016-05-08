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

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

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
    public static final int TARGET = GL_TEXTURE_2D;

    /**
     * This texture ID for OpenGL.
     */
    private int textureId;

    /**
     * An OpenGL Texture.
     *
     * @param stream The image input stream.
     * @throws IOException If it failed to read the image.
     */
    public GlTexture(InputStream stream) throws IOException
    {
        this(ImageIO.read(stream));
    }

    /**
     * An OpenGL Texture
     *
     * @param image The image to load data from.
     */
    public GlTexture(BufferedImage image)
    {
        textureId = glGenTextures();
        upload(image);
    }

    /**
     * Uploads the given image to the graphics memory.
     * 
     * @param image The image to be uploaded.
     */
    public void upload(BufferedImage image)
    {
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(pixels.length * 4);

        for (int y = 0; y < image.getHeight(); y++)
        {
            for (int x = 0; x < image.getWidth(); x++)
            {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xff));
                buffer.put((byte) ((pixel >> 8) & 0xff));
                buffer.put((byte) ((pixel) & 0xff));
                buffer.put((byte) ((pixel >> 24) & 0xff));
            }
        }

        // DO NOT forget
        buffer.flip();

        bind();

        glTexImage2D(TARGET, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        glTexParameteri(TARGET, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(TARGET, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        glTexParameteri(TARGET, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(TARGET, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        bindNone();
    }

    /**
     * Binds this texture.
     */
    public void bind()
    {
        glBindTexture(TARGET, textureId);
    }

    /**
     * Unbinds the current texture.
     */
    public static void bindNone()
    {
        glBindTexture(TARGET, 0);
    }

    /**
     * @return This texture ID for OpenGL.
     */
    public int getId()
    {
        return textureId;
    }

    /**
     * Removes this texture from the graphic memory and frees its ID.
     */
    public void release()
    {
        glDeleteTextures(textureId);
    }

    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
        release();
    }
}