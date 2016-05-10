/*
 * Copyright 2015-2016 Adrien "Litarvan" Navratil & Victor "Wytrem"
 *
 * This file is part of Libnoctis.
 *
 * Libnoctis is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Libnoctis is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Libnoctis. If not, see <http://www.gnu.org/licenses/>.
 */
package org.libnoctis.util;

import com.android.ninepatch.GraphicsUtilities;
import com.android.ninepatch.NinePatch;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.libnoctis.render.gl.GlTexture;

/**
 * The Noctis Nine Patch
 *
 * <p>
 *     A Libnoctis version of the nine patch. Based on the android
 *     official nine patch, it can be directly created by an image,
 *     and generated to a BufferedImage or a GlTexture.
 * </p>
 *
 * @author Litarvan
 * @version 1.0.0
 * @since 1.0.0
 */
public class NoctisNinePatch extends NinePatch
{
    /**
     * The last generated image
     */
    private BufferedImage lastGeneratedImage;

    /**
     * The last generated texture
     */
    private GlTexture lastGeneratedTexture;

    /**
     * The dimensions of the last generated image
     */
    private Vector2i lastGeneratedDimensions;

    /**
     * The Noctis Nine Patch
     *
     * @param image The nine patch image
     */
    public NoctisNinePatch(BufferedImage image)
    {
        super(image);
    }

    /**
     * Generate the nine patch for the given dimensions
     *
     * @param dimensions The dimensions of the image to generate
     *
     * @return The generated image
     */
    public BufferedImage generateFor(Vector2i dimensions)
    {
        return generateFor(dimensions.getX(), dimensions.getY());
    }

    /**
     * Generate the nine patch for the given dimensions
     *
     * @param width The width of the image to generate
     * @param height The height of the image to generate
     *
     * @return The generated image
     */
    public BufferedImage generateFor(int width, int height)
    {
        if (lastGeneratedDimensions.getX() == width && lastGeneratedDimensions.getY() == height)
            return lastGeneratedImage;

        BufferedImage image = GraphicsUtilities.createCompatibleImage(width, height);
        this.draw((Graphics2D) image.getGraphics(), 0, 0, width, height);

        this.lastGeneratedImage = image;
        this.lastGeneratedTexture = new GlTexture(image);
        this.lastGeneratedDimensions = new Vector2i(width, height);

        return image;
    }

    /**
     * Generate the nine patch for the given dimensions as a texture
     *
     * @param dimensions The dimensions of the texture to generate
     *
     * @return The generated texture
     */
    public GlTexture generateTextureFor(Vector2i dimensions)
    {
        return generateTextureFor(dimensions.getX(), dimensions.getY());
    }

    /**
     * Generate the nine patch for the given dimensions as a texture
     *
     * @param width The width of the texture to generate
     * @param height The height of the texture to generate
     *
     * @return The generated texture
     */
    public GlTexture generateTextureFor(int width, int height)
    {
        if (lastGeneratedDimensions.getX() != width || lastGeneratedDimensions.getY() != height)
            generateFor(width, height);

        return lastGeneratedTexture;
    }
}
